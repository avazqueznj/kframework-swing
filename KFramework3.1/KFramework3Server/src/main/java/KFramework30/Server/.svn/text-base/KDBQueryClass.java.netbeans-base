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
package KFramework30.Server;

import KFramework30.Base.KConfigurationClass;
import KFramework30.Base.KExceptionClass;
import KFramework30.Base.KLogClass;
import KFramework30.Base.KMetaUtilsClass;
import java.sql.*;

public class KDBQueryClass {
    
	// uses
	private KConfigurationClass configuration;
	private KLogClass log;
        private KTransactionClass sessionTransaction;
        

	// has - defaulted
	private static final int READY = 0;
	private static final int NOT_READY = 1;
	private static final int FINISHED_QUERY = 2;
	private static final int FINISHED_MODIFY = 3;
	private int status = NOT_READY;
	
        private String SQL = null;
        private PreparedStatement SQLStatement = null;
        
        private ResultSet resultSet = null;
        private ResultSetMetaData  metaData;
               
	// cursor for results
	private boolean fetchComplete = true;
        private long fetchCount = 0;
        private int columnCount = -1;
        
        
//-----------------------------------------------------------------------------
// INTERFACE


	//-----------------------------------------------------------------------------
	// PRIVATES
        
        
        
	//-----------------------------------------------------------------------------
	// PUBLICS        
    
    /** Creates a new instance of dbTransactionClass */
    public KDBQueryClass(        
        KConfigurationClass configParam, 
        KLogClass logParam,
        KTransactionClass sessionTransactionParam )
    {
        // uses
	configuration = configParam;
        log = logParam; 
        sessionTransaction = sessionTransactionParam;
        
	// has - defaulted
        status = NOT_READY;
	SQL = "";
        

	fetchComplete = true;

	log.log( this, "DBtransaction created successfuly!" );        
    }
    
    //----------------------------------------------------------------------

	/** 
		Prepares the SQL string
	*/
                            
	public void prepare( String SQLstring )
	throws KExceptionClass{

                log.log( this, "Prepareing statement [" + SQLstring + "]");
                
		// check transition
		if( status != NOT_READY && status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Call to prepare in a READY DB transaction.", null
			);
		}

		// set the SQL command
		SQL = SQLstring;                 
                
                try {
                    
                    SQLStatement = sessionTransaction.createSQLStatement( SQL );
                }
                catch( Exception err  ) {
            
                    String message = "Cannot prepare SQL statment.\n<br>" + err;
                    log.log( this, message + "Exception: " + err );
                    throw new KExceptionClass( message, err );
                } 
                
                columnCount = -1;
                fetchComplete = true;
                status = READY;
                
	}
        
	//----------------------------------------------------------------------

	/**
		BINDING Parameters can be reassigned at any moment 
		and may be reset only partially				
	*/
	public void bind( int paramIndex, Object parameterValue )
	throws KExceptionClass{	

		// check transition
		if( status != READY && status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Cannot bind DB transaction. Transaction not READY or FINISHED_QUERY.", null
			);
		}                 

		// for the record
		{
			String message = new String();
			message = " Binding parameter ";
			message += paramIndex;
			message += " with [";
			message += parameterValue;
			message += "]";
			log.log( this, message );
		}

                try {
                    if( parameterValue instanceof Short )
                        SQLStatement.setShort( paramIndex, ((Short)parameterValue).shortValue() );
                    else if( parameterValue instanceof Integer )
                        SQLStatement.setInt( paramIndex, ((Integer)parameterValue).intValue() );  
                    else if( parameterValue instanceof Long )
                        SQLStatement.setLong( paramIndex, ((Long)parameterValue).longValue() ); 
                    else if( parameterValue instanceof Float )
                        SQLStatement.setFloat( paramIndex, ((Float)parameterValue).floatValue() );                       
                    else if( parameterValue instanceof Double )
                        SQLStatement.setDouble( paramIndex, ((Double)parameterValue).doubleValue() );   
                    else if( parameterValue instanceof String )
                        SQLStatement.setString( paramIndex, (String)parameterValue );   
                    else if( parameterValue instanceof java.sql.Date )
                        SQLStatement.setDate( paramIndex, (java.sql.Date)parameterValue );   
                    else if( parameterValue instanceof java.sql.Time )
                        SQLStatement.setTime( paramIndex, (java.sql.Time)parameterValue );  
                    else if( parameterValue instanceof java.sql.Timestamp )
                        SQLStatement.setTimestamp( paramIndex, (java.sql.Timestamp)parameterValue );                        
                    else
                        throw new KExceptionClass( "**** Query error **** \n" +
                            "Invalid parameter " + paramIndex + " binding with [" + parameterValue + "]", null  );                       

                }
                catch( Exception err  ) {
            
                    String message = "**** Query error **** \n" +
                        "Cannot bind parameter" + paramIndex + " with [" + parameterValue + "].\n";
                    log.log( this, message + "Exception: " + err );
                    throw new KExceptionClass( message, err );
                } 
                
                fetchComplete = true;
                
	}
        

	//----------------------------------------------------------------------

        
	public void executeQuery( )
	throws KExceptionClass
	{
                log.log( this, "Executing query...");
                
                fetchCount = 0;
                
		// check transition
		if( status != READY && status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Cannot execute DB query. The status is not READY or FINISHED_QUERY.", null
			);
		}

                try{
                    resultSet = SQLStatement.executeQuery();
                    
                    //get query results
                    metaData = resultSet.getMetaData();                    
                    columnCount = metaData.getColumnCount();                                                                             
                    
                }
                catch( Exception err  ) {
            
                    String message = "Cannot execute query.\n<br>" + err;
                    log.log( this, message + "Exception: " + err );
                    throw new KExceptionClass( message, err );
                } 
                               
                status = FINISHED_QUERY;
                fetchComplete = false;
                
                log.log( this, "Executing query... complete!");
                
        }

	//----------------------------------------------------------------------

        
	public void executeModify( )
	throws KExceptionClass
	{
                log.log( this, "Executing modify...");
                
		// check transition
		if( status != READY && status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Cannot execute DB query. The status is not READY or FINISHED_QUERY.", null
			);
		}

                try{
                    
                    SQLStatement.execute();
                                                                                                                   
                }
                catch( Exception err  ) {
            
                    String message = "Cannot execute modify.\n<br>" + err;
                    log.log( this, message + "Exception: " + err );
                    throw new KExceptionClass( message, err );
                } 
                               
                status = FINISHED_QUERY;
                fetchComplete = true;
                
                log.log( this, "Executing modify... complete!");
                
        }

        
	//----------------------------------------------------------------------

	/** Check for columns */
	public int columnCount()
	throws KExceptionClass
	{
		// check transition
		if( status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Cannot count columns in DB transaction. The status is not FINISHED_QUERY.", null
			);
		}

		return( columnCount );
	}
      
	//----------------------------------------------------------------------

	/** Check for columns */
	public ResultSetMetaData getMetaData()
	throws KExceptionClass
	{
		// check transition
		if( status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Cannot read metadata. The status is not FINISHED_QUERY." , null
			);
		}

		return( metaData );
	}    
        
	public ResultSet getResultSet()
	throws KExceptionClass
	{
		// check transition
		if( status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Cannot read metadata. The status is not FINISHED_QUERY." , null
			);
		}

		return( resultSet );
	}          

        //-----------------------------------------------------------------------
        
 	public String[] getColumnNames()
	throws KExceptionClass
	{
		// check transition
		if( status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Cannot return column names in DB transaction. The status is not FINISHED_QUERY.", null
			);
		}  
                
                String[] columnNames = new String[ columnCount ];
                try{                                    
                    
                    //column index starts at 1
                    for( int i=1; i<=columnCount; i++ ) {
                        columnNames[i-1] = 
                            metaData.getColumnName(i).trim();                                        
                    }
                    
                }
                catch( Exception err  ) {
            
                    String message = "**** Query error **** \n<br>" + err;
                    log.log( this, message + "\nException: " + err );
                    throw new KExceptionClass( message, err );
                }                 
                    
               return columnNames;
        }
        
	//----------------------------------------------------------------------

	/** Retrieve rows */
	public boolean fetch()
	throws KExceptionClass
	{
		// check transition
		if( status != FINISHED_QUERY ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"Cannot execute DB query. The status is not FINISHED_QUERY.", null
			);
		}

                // check not beyon set
		if( fetchComplete ){
			throw new KExceptionClass(
				"**** Query error **** \n" +
				"SQL fetch past indicated end of data.", null
			);
		}
                
                // last row, set end
                try {
                    if( !resultSet.next() ){                        
                        fetchComplete = true;
                        log.log( this, "No more rows..." + fetchCount );                        
                    }
                    
                fetchCount++;
                    
                }                
                catch( Exception err  ) {
            
                    String message = "**** Query error **** \n" +
                        "Cannot fetch next row.\n";
                    log.log( this, message + "Exception: " + err );
                    throw new KExceptionClass( message, err );
                } 
                
                //true - fetch OK.
                return( !fetchComplete );                
        }   
        
        
	//----------------------------------------------------------------------        
        
        public String getField( String fieldName )
        throws KExceptionClass
        {
            
            if( fetchComplete ){
		throw new KExceptionClass(
                    "**** Query error **** \n" +
                    "SQL fetch past indicated end of data.", null );
            }
            
            
            String fieldValue = null;
            try {
                       
              if( 
                      metaData.getColumnType( resultSet.findColumn(fieldName) ) == java.sql.Types.DECIMAL ||
                      metaData.getColumnType( resultSet.findColumn(fieldName) ) == java.sql.Types.DOUBLE || 
                      metaData.getColumnType( resultSet.findColumn(fieldName) ) == java.sql.Types.FLOAT  || 
                      metaData.getColumnType( resultSet.findColumn(fieldName) ) == java.sql.Types.NUMERIC
                      ){                                          
                  
                    // force internationalization, or will have issues
                    fieldValue = KMetaUtilsClass.toDecimalString( resultSet.getDouble(fieldName) );
                    
              }else{
                  
                    fieldValue = resultSet.getString( fieldName );                  
              }
                              
            }
            catch( Exception err  ) {
            
                String message = "**** Query error **** \n" +
                    "Cannot get field value for [" + fieldName + "].\n";
                log.log( this, message + "Exception: " + err );
                throw new KExceptionClass( message, err );
            }             
                
            return( fieldValue );
            
        }        
        
	//----------------------------------------------------------------------        
        
        public byte[] getBinaryField( String fieldName )
        throws KExceptionClass
        {
            
            if( fetchComplete ){
		throw new KExceptionClass(
                    "**** Query error **** \n" +
                    "SQL fetch past indicated end of data.", null );
            }
            
            byte[] result = null;
            
            try{
                
                Blob blob = resultSet.getBlob( fieldName );
                
                if( blob != null ){
                    
                    result = resultSet.getBlob( fieldName ).getBytes( 1L, (int) blob.length() ); // up tp 1 meg                    
                }
                                
                              
            }
            catch( Exception err  ) {
            
                String message = "**** Query error **** \n" +
                    "Cannot get field value for [" + fieldName + "].\n";
                log.log( this, message + "Exception: " + err );
                throw new KExceptionClass( message, err );
            }             
                
            return( result );
            
        }        
                
        
	//----------------------------------------------------------------------
        
        public String getUnformattedField( String fieldName )
        throws KExceptionClass
        {
            
            String fieldValue = "";
            
            if( fetchComplete ){
		throw new KExceptionClass(
                    "**** Query error **** \n" +
                    "SQL fetch past indicated end of data.", null );
            }
            
            
            try{
                    fieldValue = resultSet.getString( fieldName );                  

                              
            }catch( Exception err  ) {
            
                String message = "**** Query error **** \n" +
                    "Cannot get field value for [" + fieldName + "].\n";
                log.log( this, message + "Exception: " + err );
                throw new KExceptionClass( message, err );
            }             
                
            return( fieldValue );
            
        }          
        
        //-----------------------------------------------------------------------        
        
        /** Output the results as an HTML table, with 
         *  the column names as headings and the rest of
         *  the results filling regular data cells.   
         *  Call this function after executing query,
         *  entire query results will be in the table. */
        
        public String toHTMLTable( String headingColor )
        throws KExceptionClass
        {
            log.log( this, "providing HTML table from DB transaction...");
            
            // check transition
            if( status != FINISHED_QUERY ){
		throw new KExceptionClass(
			"**** Query error **** \n" +
			"Cannot provide HTML table from DB transaction. The status is not FINISHED_QUERY.", null
		);
            }
            
            if( fetchComplete ){
		throw new KExceptionClass(
                    "**** Query error **** \n" +
                    "SQL fetch past indicated end of data.", null );
            }            
        
            StringBuffer buffer = 
                new StringBuffer( "<TABLE BORDER=1>\n" );
            
            if( headingColor != null )
                buffer.append("    <TR BGCOLOR=\"" + headingColor + "\"> \n  " );
            else
                buffer.append("    <TR>\n  " );
            
            try {
                
                //get query results
                ResultSetMetaData  metaData = resultSet.getMetaData();
                    
                for( int col=1; col<=columnCount; col++ ) {
                    //get headers
                    String colName = metaData.getColumnName(col).trim();
                    buffer.append( "<TH>" + colName );
                }
                    
                //get rows
                while( resultSet.next() ) {
                     
                    buffer.append("\n    <TR>\n  " );
                    for( int col=1; col<=columnCount; col++ ) {
                        String cellData = resultSet.getString( col );
                        buffer.append("<TD>" + cellData );    
                    }                        
                }

            }
            catch( SQLException err  ) {
            
                String message = "Cannot provide HTML table.\n<br>" + err;
                log.log( this, message + "SQLException: " + err );
                throw new KExceptionClass( message, err );
            }                  
            
            buffer.append( "\n</TABLE>" );            
            fetchComplete = true;
            
            return buffer.toString();
        }
        
        //--------------------------------------------------------------------------
        
        public void close() throws KExceptionClass{
            
                       
            status = NOT_READY;           
            
            try {
                                                
                if( SQLStatement != null ){
                    
                    log.log( this, "Close Cursor..."  + SQL + ":" + SQLStatement.toString()  );                                    
                    sessionTransaction.removeSQLStatement( SQLStatement );
                }
                                                                               
            } catch (Exception error ) {
                
                log.log( this, "Could not close SQL statement 1" + error );
                throw new KExceptionClass( "Could not close SQL statement 1" + error , error );
            }
        }
        
        //--------------------------------------------------------------------------        
        
        @Override        
        public void finalize() throws Throwable{
            
            status = NOT_READY;
                        
            try {                                            
                
                close();
                
            } catch (Exception ex) {
                
            }
            
            super.finalize();                
        }

        
        //--------------------------------------------------------------------------                        
        
    public void lockRecord( String lock_name, String key_field_name, String table_name ) throws KExceptionClass {
        
            log.log( this, "Prepareing statement to LOCK ");

            // check transition
            if( status != NOT_READY && status != FINISHED_QUERY ){
                    throw new KExceptionClass(
                            "**** Query error **** \n" +
                            "Call to prepare in a READY DB transaction.", null
                    );
            }
            
            //---------------------------
            
            String sql = 
                      " select "+ key_field_name 
                    + " from "+ table_name 
                    + " where " +  key_field_name + " = ? " 
                    + " for update nowait";
            
            prepare( sql );
            
            bind( 1, lock_name );
            
            executeQuery();
    
            log.log( this, "Prepareing statement to LOCK ... done and aquired ------>" + key_field_name + " -> " +  lock_name );            
    }
    
        
        
        
        //--------------------------------------------------------------------------                
}
