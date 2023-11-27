/*
This source code is part of the KFRAMEWORK  (http://k-framework.sourceforge.net/)
Copyright (C) 2001  Alejandro Vazquez, Ke Li
Feedback / Bug Reports vmaxxed@users.sourceforge.net

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package KFramework30.Communication;

// K
import KFramework30.Base.*;


// runtime
import KFramework30.Base.KMetaUtilsClass.parameterTypeEnum;
import datamanagementcomponent.FieldClass;
import datamanagementcomponent.KFrameworkServerClassService;
import datamanagementcomponent.KFrameworkService01;
import datamanagementcomponent.RecordClass;
import datamanagementcomponent.ResultSetClass;
import datamanagementcomponent.SQLCmdClass;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import javax.xml.namespace.QName;


/**
	Class that handles SQL querying to remote db server
*/
public class dbTransactionClientClass 
extends HashMap {
        
	
        // uses
	private KConfigurationClass configuration;
	private KLogClass log;

	// has - defaulted
        clusterManagerClass clusterManager;
        private QueryStatusEnum status = QueryStatusEnum.NOT_READY;
	private String SQL;

	public static int DEFAULT_MAXROWS = -1;
	public int maxRows = 0;
	private int startRow = 0;	

        //SQL binding params'order stored in the list is 
        //according to the position in the SQL command
	private List< FieldClass > SQLparameters;
        private ResultSetClass resultSet = null;

	static private int currentServerIndex = 0;
        static private Vector< String > serverList;

	// cursor for results
	private int rowCount = 0;
	private boolean fetchComplete = true;
	private int resultCursor = 0;
        public  ArrayList< String > columnNamesList;
        private  ArrayList< Integer > columnTypesList;


        
        
        // setvice status
        public enum ServiceStatusEnum { 
            
            OBJECTMANAGER_OK ("SUCCESS:OBJECT"),
            SQL_OK ("SUCCESS:SQL"),                
            SERVER_ERROR ("ERROR");     


            final String code;  

            ServiceStatusEnum (String code) {  
                this.code = code;  
            } 
       }; 

        // dbTransaction status
        public enum QueryStatusEnum { 
            READY,
            NOT_READY,
            FINISHED_QUERY,
            FINISHED_MODIFY
        };   



        
//-----------------------------------------------------------------------------
// INTERFACE


	//-----------------------------------------------------------------------------
	// PRIVATES

	/** Execute the command for both QUERY and MODIFY */
	private void sendRequest( )
	throws KExceptionClass
	{
		// for the record and in case the
		// system dies in the try...
                {
                    String message = new String();
                    message = "Executing [";
                    message += SQL;
                    message += "]...";
                    log.log( this, message );
                }
                
                //Assemble SQL command class for SQL execute...
                SQLCmdClass Sqlcmd = new SQLCmdClass();
                Sqlcmd.setSessionId( configuration.getField( "SESSION_ID" ) );
                Sqlcmd.setSQL(SQL);
                Sqlcmd.setStartRow(startRow);
                Sqlcmd.setLengh(maxRows);
                
                // clear paramters and load ..
                List< FieldClass > list = Sqlcmd.getParams();
                list.clear();
                list.addAll(SQLparameters);
                
                try { // Call Web Service Operation
                   
                   while( true ){

                       try{


                            resultSet = clusterManager.getNextServerPort().sqlRequest( Sqlcmd ); 
                            break;

                       }catch( Exception error ){

                            log.log( this, KMetaUtilsClass.getStackTrace( error ) );                            
                            clusterManager.reportCurrentServerDead();

                       }
                   }
                   //---------------------------------------------------------------------                   
                           
                  
                } catch (Exception ex) {
                   // TODO handle custom exceptions here
                    String message = "*** Server Communication Error **** \n" + ex.toString();
                    log.log( this, message + ex.toString());                  
                    throw new KExceptionClass( message, null );
                }
                
                //Check service status
                if( !resultSet.getStatus().equals(ServiceStatusEnum.SQL_OK.code) ) {
                        String message = new String();
			message = 
				"*** Remote query error **** \n" +
				"Error, could not read data from server.\n" +
                                resultSet.getDesc();
			log.log( this, message );
			throw new KExceptionClass( message, null );    
                    
                }
                
                log.log( this, ">>> remote DB transaction executed successfully!!" );

		// **************************************************************
		// *** after SQL set, status is always OK                     ***
		// *** you may just execute again, or returnprepare or rebind ***
		// **************************************************************
        }



	//-----------------------------------------------------------------------------
	// PUBLICS


	/** Creates new remotedbTransactionClientClass */
	public dbTransactionClientClass( KConfigurationClass configurationParam, KLogClass logParam )
	throws KExceptionClass
	{
		super();
	
		// uses
		configuration = configurationParam;
		log = logParam;
       
                // has
                
                // setup cluster manager
                clusterManager = new clusterManagerClass( configurationParam, logParam );
                
		// has - defaulted
		//httpTransaction = new httpTransactionClass( configuration, log );
		status = QueryStatusEnum.NOT_READY;
		SQL = "";

		SQLparameters = new ArrayList();
		DEFAULT_MAXROWS = Integer.parseInt( configuration.getField( "sql_max_rows" ) );
                maxRows = DEFAULT_MAXROWS;
		fetchComplete = true;
                
                columnTypesList = new ArrayList< Integer >();                 
                columnNamesList = new ArrayList< String >();                
                                             
                //------------------------------------        
                
		log.log( this, "remoteDBtransaction created successfuly!" );
	}

	//----------------------------------------------------------------------

	/** 
		Prepares the SQL string, the sql string need not to be 
		reset to reuse the object. Just clear and reasign the parameters
	*/
	public void prepare( String SQLstring )
	throws KExceptionClass{

		// check transition
		if( status != QueryStatusEnum.NOT_READY ){
			throw new KExceptionClass(
				"*** Remote query error **** \n" +
				"Call to prepare in a READY dbTransaction." , null 
			);
		}


		// set the SQL command
		SQL = SQLstring;
		status = QueryStatusEnum.READY;
		SQLparameters = new ArrayList();
	}

	//----------------------------------------------------------------------

	/**
		BINDING
		Parameters can be reassigned at any moment 
		and may be reset only partially				
	*/


	public void dynamicBind( String parameterName, Object parameterValue )
	throws KExceptionClass{	


            if( parameterValue instanceof Integer ) bind( parameterName, (Integer)parameterValue ); else
            if( parameterValue instanceof Long ) bind( parameterName, (Long)parameterValue ); else
            if( parameterValue instanceof Float ) bind( parameterName, (Float)parameterValue ); else
            if( parameterValue instanceof Double ) bind( parameterName, (Double)parameterValue ); else
            if( parameterValue instanceof Date ) bind( parameterName, (Date)parameterValue ); else
            if( parameterValue instanceof String)   bind( parameterName, (String)parameterValue ); else                 
{
                throw new KExceptionClass( 
                        "Could not bind " + parameterName + " argument type not supported. "
                        + parameterValue.getClass().getName(), null );
            }
	}        
        
        
        
        //--------------
        
	public void bind( String parameterName, String parameterValue )
	throws KExceptionClass{	
            bind( parameterName, parameterValue, parameterTypeEnum.STRING  );
	}        
        
        //----------------
        
	public void bind( String parameterName, int parameterValue )
	throws KExceptionClass{	
            bind( parameterName, KMetaUtilsClass.toDecimalString( parameterValue ), parameterTypeEnum.INT );
	}
                
	public void bind( String parameterName, long parameterValue )
	throws KExceptionClass{	
            bind( parameterName, KMetaUtilsClass.toDecimalString( parameterValue ), parameterTypeEnum.LONG );
	}
        
	public void bind( String parameterName, float parameterValue )
	throws KExceptionClass{	
            bind( parameterName, KMetaUtilsClass.toDecimalString( parameterValue ), parameterTypeEnum.FLOAT );
	}
                
	public void bind( String parameterName, double parameterValue )
	throws KExceptionClass{	
            bind( parameterName, KMetaUtilsClass.toDecimalString( parameterValue ), parameterTypeEnum.DOUBLE );
	}

        //----------------
        
	public void bind( String parameterName, Date parameterValue )
	throws KExceptionClass{	
            bind( parameterName,  KMetaUtilsClass.dateToString( KMetaUtilsClass.KDEFAULT_LONG_DATE_TIME_FORMAT, parameterValue ), parameterTypeEnum.DATE );
	}
        
        
        // -------------------------------------------------------------------
        
	private void bind( String parameterName, String parameterValue, parameterTypeEnum ParameterTypeParam )
	throws KExceptionClass{	

		// check transition
		if( status != QueryStatusEnum.READY ){
			throw new KExceptionClass(
				"*** Remote query error **** \n" +
				"Could not bind remoteDBtransaction. Transaction not READY." , null 
			);
		}

                FieldClass parameter = new FieldClass();
                parameter.setFieldName( parameterName );
                parameter.setFieldValue( parameterValue );   
                parameter.setFieldType( ParameterTypeParam.toString() );
                SQLparameters.add( parameter );
                
		// for the record
		{
			String message = new String();
			message = " Binding [";
			message += parameterName;
			message += "] with [";
			message += parameterValue;
			message += "] ";
                        
			log.log( this, message );
		}
			
        }



	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------

	/** Execute/ reExecute the command, rows are expected to be returned */        
        public void reusePrevious()
	throws KExceptionClass
	{
		// check transition
		if( ( status != QueryStatusEnum.FINISHED_QUERY ) && ( status != QueryStatusEnum.READY ) ){
			throw new KExceptionClass(
				"*** Remote query error **** \n" +
				"Can not returnexecute dbTransaction. Command not ready."  , null 
			);
		}
                
		clear();
                ResultSetClass resultSet = null;
		
		fetchComplete = false;
                
                SQLparameters.clear();

                status = QueryStatusEnum.READY;                                
        }
        
	/** Execute the command, rows are expected to be returned */
	public void executeQuery( int startRowParam, int maxRowsParam )
	throws KExceptionClass
	{
		// check transition
		if( status != QueryStatusEnum.READY ){
			throw new KExceptionClass(
				"*** Remote query error **** \n" +
				"Can not execute dbTransaction. Command not ready." , null 
			);
		}

		// set defaults
		maxRows = maxRowsParam; 
		startRow = startRowParam;
	
		// send req to server
		sendRequest( );
                
		// prepare reply data
		clear();    

		// get cursor to returned data
		resultCursor = 0;	
                startRow = resultSet.getStart();
                rowCount = resultSet.getRecords().size();
                
                Iterator<String> columneName = resultSet.getColumnNames().iterator();
                Iterator<Integer> columneType = resultSet.getColumnTypes().iterator();
                
                String metaDataLog = new String();
                columnNamesList.clear();
                columnTypesList.clear();
                while( columneName.hasNext() ){
                    
                    String nextColumnName = columneName.next();
                    Integer nextColumnType = columneType.next();
                    
                    columnNamesList.add( nextColumnName );
                    columnTypesList.add( nextColumnType );
                    
                    metaDataLog += "{" + nextColumnName + ":" + getJdbcTypeName( nextColumnType ) + "}";
                }
                    
                log.log( this, metaDataLog );
                        
		// init fetch
		fetchComplete = false;
		status = QueryStatusEnum.FINISHED_QUERY;

		// done.
		String message = "Remote SQL transaction complete.";
		message += "[" + rowCount;
		message +=  "] rows returned.";
		log.log( this, message );
	}

	//----------------------------------------------------------------------

	/** Check for rows */
	public int rowCount()
	throws KExceptionClass
	{
		// check transition
		if( status != QueryStatusEnum.FINISHED_QUERY ){
			throw new KExceptionClass(
				"*** Remote query error **** \n" +
				"Can not count rows in dbTransaction. Command not QUERY_READY" , null 
			);
		};

		return( resultSet.getRecords().size() );
	}

	/** Check for rowStart */        
	public int resultSetStart()
	throws KExceptionClass
	{
		// check transition
		if( status != QueryStatusEnum.FINISHED_QUERY ){
			throw new KExceptionClass(
				"*** Remote query error **** \n" +
				"Can not return start row in dbTransaction. Command not QUERY_READY" , null 
			);
		}

		return( startRow );
	}


	//----------------------------------------------------------------------

	/** Retrieve rows */
	public boolean fetch()
	throws KExceptionClass
	{
		// check transition
		if( status != QueryStatusEnum.FINISHED_QUERY ){
			throw new KExceptionClass(
				"*** Remote query error **** \n" +
				"Can not execute dbTransaction. Command not QUERY_READY" , null 
			);
		}

		// erase any data
		clear();

		if( fetchComplete ){
			throw new KExceptionClass(
				"*** Remote query error **** \n" +
				"Remote SQL fetch past indicated end of data." , null 
			);
		}

		// check not at the end
                //resultCursor always point to record to be fetch currently
                if( resultCursor >= resultSet.getRecords().size() ){
			fetchComplete = true;
			return( false );
		}

		// get next row
                // Column names synchronize with record fields via List index
                RecordClass record = resultSet.getRecords().get(resultCursor);
                resultCursor++;
                
                List<String> column_names = resultSet.getColumnNames();
                
                int record_len = column_names.size();
                
                for( int i=0; i<record_len; i++ )
                    put( column_names.get(i), record.getRow().get(i) ) ;                

		// done
		return( true );
	}

	//----------------------------------------------------------------------

	/** Reset for resue */
	public void reset()
	throws KExceptionClass{
		

		status = QueryStatusEnum.NOT_READY;
		SQL = "";

		SQLparameters.clear();
                
		clear();
		//columnNamesList = new ArrayList();
		ResultSetClass resultSet = null;
                fetchComplete = true;
	}

        /** Manual access to sql string */
        public String getSQL(){ return( SQL ); }
        
        /** Manual access to sql string */        
        public void setSQL( String SQLParam ){ SQL = SQLParam; }
        
	//----------------------------------------------------------------------

	//----------------------------------------------------------------------
        
        public Object getProperty( String fieldName )
        throws KExceptionClass
        {
            
            if( !containsKey( fieldName ) ){
                
                String message = new String();
                message = "*** Remote query error **** \n";
                message += "Field not found in db query ";
                message += "[";
                message += fieldName;
                message += "]";

                log.log( this, message );
                throw new KExceptionClass( message, null );                
            }
            
            return( (Object) get( fieldName ) );
            
        }
        
	//----------------------------------------------------------------------               
        
        public Object getField( String fieldName )
        throws KExceptionClass        
        {
            return( getProperty( fieldName ) ); 
        }
        
        public int getColumnType( String fieldName )
        throws KExceptionClass        
        {
         
            int index = columnNamesList.indexOf( fieldName );            
            return columnTypesList.get( index );
        }
        
        public int getColumnType( int index )
        throws KExceptionClass        
        {         
            return columnTypesList.get( index );
        }        
        
        
        public boolean isColumnNumeric( String fieldName )
        throws KExceptionClass        
        {
            return isColumnNumeric( columnNamesList.indexOf( fieldName ) );
        }    
        
       public boolean isColumnNumeric( int index )
        throws KExceptionClass        
        {
                    
            int type = columnTypesList.get(index);
            
            switch( type ){
                case java.sql.Types.DECIMAL :
                case java.sql.Types.DOUBLE :                    
                case java.sql.Types.FLOAT :                                        
                case java.sql.Types.INTEGER :
                case java.sql.Types.NUMERIC :
                case java.sql.Types.REAL :                    
                case java.sql.Types.SMALLINT :                                        
                case java.sql.Types.TINYINT :
                    return true;
            }
            
            return false;
        }         
        
	//----------------------------------------------------------------------       
        
       
        static Map JDBCTypeNamesMap;  
       
        public static String getJdbcTypeName(int jdbcType) {
            
            if (JDBCTypeNamesMap == null) {
                JDBCTypeNamesMap = new HashMap();

                // Get all field in java.sql.Types
                Field[] fields = java.sql.Types.class.getFields();
                for (int i=0; i<fields.length; i++) {
                    try {
                        // Get field name
                        String name = fields[i].getName();

                        // Get field value
                        Integer value = (Integer)fields[i].get(null);

                        // Add to map
                        JDBCTypeNamesMap.put(value, name);
                    } catch (IllegalAccessException e) {
                    }
                }
            }

            // Return the JDBC type name
            return (String)JDBCTypeNamesMap.get(new Integer(jdbcType));
        }
             


        //------------------------------------------------------------------------------
        
        // not finished, not used ...        
        public String getHTMLTable() 
        throws KExceptionClass{
            
            boolean thereWasData = false;
            
            String table = new String();
            table += "<html>\n";
            table += "<table border=2 >\n";
            
            while( fetch() ){
                
                thereWasData = true;
                
                table += "<tr>\n";
                                                        
                    Iterator< String > clumnName = columnNamesList.iterator();
                    while( clumnName.hasNext() ){
                        
                        table += "<td>";                                        
                        table += getField( clumnName.next() );         
                    }
                                        
                table += "</tr>\n";                
            }
            
            table += "</table>\n";
            table += "</html>\n";
            
            if( !thereWasData ) table = null;
            
            return( table );
        }        
        
	//----------------------------------------------------------------------               
                                      
        public List<FieldClass> getSQLparameters() {
            return SQLparameters;
        }

        public void setSQLparameters(List<FieldClass> SQLparameters) {
            this.SQLparameters = SQLparameters;
        }

        
        //----------------------------------------------------------------------            
        
        
}
