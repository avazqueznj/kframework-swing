/*
This source code is part of the KFRAMEWORK  (http://k-framework.sourceforge.net/)
Copyright (C) 2001  Alejandro Vazquez, Ke Li
Feedback / Bug Reports avazqueznj@users.sourceforge.net

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



package KFramework30.Widgets.DataBrowser;

import KFramework30.Communication.dbTransactionClientClass;
import java.util.*;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.KDataBrowserBaseClass;
import java.awt.Window;




/** This class focuses on DB transaction tasks. Because the application forms 
 * the SQL statements dynamically, it stores column and table names, 
 * query criterion and display orders. There is a map, which matches 
 * human readable column names to database column names. It is responsible for three tasks.
 * 1. Setting parameters or criteria for SQL.
 *    It collects SQL sorting order and criteria in programmatic and user input methods separately. 
 * 2. Performing pre or post DB transaction operations:
 *    Preparing DB transaction, assembling SQL statement, asking for DB transaction 
 *    and retrieving data after transaction.
 * 3. Managing displayFieldName field name list.
 *    Because the visual table uses human readable column displayFieldNameIterator, the class 
 *    has a list to map column displayFieldNameIterator with DB table field names.
 * It delegates the actual DB transaction to dbTransactionClientClass.
*/

public class SQLPreprocessorClass extends Object {
     
    // uses
    private KConfigurationClass  configuration;    
    private KLogClass            log;       
    
    
    // has - defaulted
    private dbTransactionClientClass            dbTransactionClient;   //for reading
    
    private String                  selectSQL;          //SQL select clause 
    private String                  tableName;          //DB table name
    private String                  defaultWhere;       //Assistant SQL where clause for assembling      
    private String                  finalWhere;         //Assistant SQL where clause for assembling  
    private String                  finalOrder;         //Assistant SQL where clause for assembling      
    private String                  defaultOrderSQL;    //SQL order clause for programmatic input 
    private String                  customOrderSQL;     //SQL order clause for user input order  
    private String                  keyField;           //key field such as OID  
    private boolean                 showKeyField;       //wether contains key field as normal field in the record    

    private String                  mainCriteria;       //holding main criteria for SQL Where clause    
    private List                    mainBindingParameterList;  //contains the binding parameters for the primary  SQL criteria.
        
    private List                    customBindingParameterList; //contains the binding parameters for the temporary custom input SQL criteria.       
    private List                    customCriteriaList;     //holding user input criteria for SQL Where clause    
    
    
    public List                     headerFieldNameList;   //A list to map DB table field name and JTable displayFieldName name
     
    private int                     BindingParameterNo;
    private boolean                 firstLoadReady;
    private boolean		    SQLInitialized;

    private Window                  parentWindow;
    
    //----------------------------------------------------------------------    
    //Variable type for binding parameters of SQL query
    
    
    
    
    public class boundParameterClass {
        public String               parameterIndex;     
        public Object               parameterValue;
        
        public boundParameterClass( 
                String parameterIndexParam, Object parameterValueParam ){
            parameterIndex = parameterIndexParam;           
            parameterValue = parameterValueParam;
        }
    }
    
    
    //----------------------------------------------------------------------    
    //Variable type for binding DB table alias name and DB field name displayFieldName name
    public class fieldInformationClass extends java.lang.Object {
        public String aliasName;
        public String fieldName;
        public String headerName;
        public boolean columnEditable;
        
        public fieldInformationClass( String aliasParam, String fieldParam, 
                                        String headerParam ){
            aliasName = aliasParam;
            fieldName = fieldParam;
            headerName = headerParam;
            columnEditable = false;
        }
        
        public fieldInformationClass( String aliasParam, String fieldParam, 
                                      String headerParam, boolean columnEditableParam )
        {
            aliasName = aliasParam;
            fieldName = fieldParam;
            headerName = headerParam;
            columnEditable = columnEditableParam;
        }        
    }    

    // ------------------------------------------------------------------------
    // PRIVATE INTERFACE
    
    
    /**     prepare DB transaction */ 
    private void bindDBtransaction( 
        dbTransactionClientClass dbTransaction,
        String sql )
    throws KExceptionClass 
    { 
        dbTransaction.reset();
        
        //Prepares the SQL string
        dbTransaction.prepare( sql ); 
        
        //binding main parameters
        Iterator parameters = mainBindingParameterList.iterator();                        
        while( parameters.hasNext() ){
            // save sql parameters
            boundParameterClass currentBoundParameter = ( boundParameterClass ) parameters.next();                   
            dbTransaction.dynamicBind( currentBoundParameter.parameterIndex, currentBoundParameter.parameterValue );
        }    
        
        //binding sub parameters
        parameters = customBindingParameterList.iterator();                        
        while( parameters.hasNext() ){
            // save sql parameters
            boundParameterClass currentBoundParameter = ( boundParameterClass ) parameters.next();                   
            dbTransaction.dynamicBind( currentBoundParameter.parameterIndex, currentBoundParameter.parameterValue );
        }          
    }
   

        /**     prepare DB transaction */ 
    private void bindDefaultDBtransaction( 
        dbTransactionClientClass dbTransaction,
        String sql )
    throws KExceptionClass 
    { 
        dbTransaction.reset();
        
        //Prepares the SQL string
        dbTransaction.prepare( sql ); 
        
        //binding main parameters
        Iterator parameters = mainBindingParameterList.iterator();                        
        while( parameters.hasNext() ){
            // save sql parameters
            boundParameterClass currentboundParameter = ( boundParameterClass ) parameters.next();                   
            dbTransaction.dynamicBind( currentboundParameter.parameterIndex, currentboundParameter.parameterValue );
        }    
        
    }

    
        
    //----------------------------------------------------------------------    
    /** Creates new SQLPreprocessorClass */
    public SQLPreprocessorClass( 
	KConfigurationClass configurationParam, KLogClass logParam, 
	boolean showKeyFieldParam, Window parentWindowParam ) 
    throws KExceptionClass    
    {
        // inherits
        super();

        // uses
        configuration = configurationParam;
        log = logParam;
        showKeyField = showKeyFieldParam;
        parentWindow = parentWindowParam;
        
        // has - defaulted         
        selectSQL = "";          //SQL select clause 
        tableName = "";          //DB table name
        defaultWhere = "";       //Assistant SQL where clause for assembling      
        finalWhere = "";         //Assistant SQL where clause for assembling  
        finalOrder = "";         //Assistant SQL where clause for assembling          
        defaultOrderSQL = "";    //SQL order clause for programmatic input 
        customOrderSQL = "";     //SQL order clause for user input order   
        keyField = "";           //key field such as OID  
        mainCriteria = "";       //holding main criteria for SQL Where clause
    
                
        mainBindingParameterList = new ArrayList( );
        customBindingParameterList = new ArrayList( );
        customCriteriaList = new ArrayList( );
        headerFieldNameList = new  ArrayList();    
        
        dbTransactionClient =  new dbTransactionClientClass( configuration, log ); 
                
        BindingParameterNo = 1;
        firstLoadReady = false;
	SQLInitialized = false;           
    }

    
    //----------------------------------------------------------------------
    /** Following methods are pre or post DB transaction operations:
        preparing DB transaction, DB transaction and retrieving data after transaction*/
    
    /** Assemble final SQL clause from SQL select, where, order clauses.  */    
    public void assembleSQL( )
    throws KExceptionClass
    {    

	if( !SQLInitialized ){
		throw new KExceptionClass(
			"*** Could not initialize db browser ***\n" +
			"SQL statement was not initialized."  , null 
		);
        };
        
        //assembling 'WHERE' clause
        // full control on main criteria
        // criteria is String not LIST now
        // -ale
                
        finalWhere = "";
                        
        // assemble custom filter
        String customWhereSQL = "";
        Iterator parameters = customCriteriaList.iterator(); 
        while( parameters.hasNext() ){
            customWhereSQL += parameters.next();                   
            if( parameters.hasNext() ) customWhereSQL += " OR ";
        }                        
        
        
        if( mainCriteria.equals( "" ) ){            
            finalWhere = "";
            defaultWhere = "";            
        }else{
            finalWhere   = " WHERE (" + mainCriteria + " ) \n";            
            defaultWhere = " WHERE (" + mainCriteria + " ) \n";
        };
                            

        if( !customCriteriaList.isEmpty() ){
            if( mainCriteria.equals( "" ) ){            
                finalWhere   = " WHERE (" + customWhereSQL + " ) \n";            
            }else{
                finalWhere   = 
                    " WHERE (" + mainCriteria + " ) \n" +
                    " AND \n" +
                    " ( " + customWhereSQL + " ) \n";
            };
        };            
                
        //assembling 'ORDER' clause
        finalOrder = defaultOrderSQL;
        if( !customOrderSQL.equals( "" ) ) finalOrder = customOrderSQL;
        
        // final assembling
        //SQL = " SELECT " + selectSQL + " FROM " + tableName + finalWhere + finalOrder;             
    }  
    
            
    
    //----------------------------------------------------------------------  
    /**   Initialize SQL statement
        This method is called only once after constructor, and before any other
	method. 
    */
	public void initializeSQLQuery(  
		String SQLSelect, String DBTable, String keyFieldParam
	) 
	throws KExceptionClass
	{
		if( SQLInitialized ){
			throw new KExceptionClass(
				"*** Could not initialize db browser ***\n" +
				"SQL is already initialized." , null 
			);
		};

	        selectSQL = SQLSelect;
	        tableName = DBTable;
	        keyField = keyFieldParam;

		SQLInitialized = true;
	}


    //----------------------------------------------------------------------  
    /** Following methods provide operations on displayFieldName field name list  */

    //----------------------------------------------------------------------
    /**   get displayFieldName names   
        This method is called only once after first load. 
        It is good time to check field names in headerFieldNameList list
        against DB returned field names.*/
    /* Builds the initial databaseFieldName-headerName list */
    public java.util.List getHeaderNames() 
    throws KExceptionClass
    {
        java.util.List headerNames = new ArrayList();          
        
        if( !firstLoadReady )
            throw new KExceptionClass( 
			"*** Could not initialize db browser ***" +
			"Can not initialize header names before load. " , null 
		);

        //check whether headerFieldNameList has valid field names.
        Iterator names = headerFieldNameList.iterator();                        
        while( names.hasNext() ){
            
            fieldInformationClass fieldInformation = ( fieldInformationClass ) names.next(); 
            boolean fieldNameFound = false;

            //go through each DB returned field name to find match
            Iterator fieldNames = dbTransactionClient.columnNamesList.iterator(); 
            while( fieldNames.hasNext() ){
                String field_name = ( String ) fieldNames.next();  
                if( field_name.equals( fieldInformation.fieldName ) ) {
                    fieldNameFound = true;
                    break;
                }
            }
        
            if( !fieldNameFound )
                throw new KExceptionClass( "*** Header name error **** \n" +
                        "Cannot build the headers, field name not in db transaction  [" 
                        + fieldInformation.fieldName + "] "  , null );            
        }      
        
        //FINAL Fill of the displayFieldName list
        Iterator fieldNames = dbTransactionClient.columnNamesList.iterator(); 
        while( fieldNames.hasNext() ){

	    // set field name
            String field_name = ( String ) fieldNames.next(); 
            
	    // set displayFieldName name, may be fake name
            if( showKeyField || !field_name.equals( keyField ) )
                //find related displayFieldName name from field name
                headerNames.add( getHeaderName( field_name ) );
        }
        
        return( headerNames );
    }
    

    
     //----------------------------------------------------------------------
    
    /** Set up human readable column names.
        And keep table alias name with the
        DB table field name and JTable displayFieldName name. 
        This method should be called before load.                   */    
    /* Sets a headerName for given dbName, hreaderList is updated. */
    
    public void setColumnNames( String aliasName, String fieldName,        
                                String headerName ) 
    throws KExceptionClass    
    {  
        setColumnNames( aliasName, fieldName,        
                        headerName, false ) ;
    }
    
    public void setColumnNames( String aliasName, String fieldName,        
                                String headerName, boolean colEditable ) 
    throws KExceptionClass    
    {  
        //After 'load', no column names can be set.
        if( firstLoadReady )
            throw new KExceptionClass( 
                        "*** Column name error **** \n" +
                        "After 'load', no column names can be set!!!" , null  );     
                
        //check whether headerName is already in the list
        Iterator names = headerFieldNameList.iterator();                        
        while( names.hasNext() ){
            fieldInformationClass fieldInformation = ( fieldInformationClass ) names.next();                   
            if( headerName.equals( fieldInformation.headerName ) )
                throw new KExceptionClass( "*** Column name error **** \n" +
                        "duplicated column name  [" + headerName + "] " , null  );            
        }      
        
        if( aliasName.length() == 0 )
            headerFieldNameList.add( new fieldInformationClass( "", fieldName, headerName, colEditable ) );             
        else
            headerFieldNameList.add( new fieldInformationClass( aliasName + ".", fieldName, headerName, colEditable ) );                         
    }

     //----------------------------------------------------------------------        
    /** get related dbfield name via displayFieldName name */    
    public String getFieldName1( String headerName )
    throws KExceptionClass
    {
        String fieldName = new String();
        
        Iterator names = headerFieldNameList.iterator();                        
        while( names.hasNext() ){
            fieldInformationClass fieldInformation = ( fieldInformationClass ) names.next();                   
            if( headerName.equals( fieldInformation.headerName ) ) {
               
                
                //fieldName = fieldInformation.aliasName + fieldInformation.databaseFieldName;
                
                fieldName =  fieldInformation.fieldName.toUpperCase();                
                
                break;
            }  
        }
        
        //for each displayFieldName, there should be a related field name.
        if( fieldName.length() == 0 )
            throw new KExceptionClass( 
                    "*** Colunm name error **** \n" +
                    "invalid column name  [" + headerName + "] "  , null );          
        
        return fieldName;
    }
       
        
    // --------

    //>>
    
         //----------------------------------------------------------------------        
    /** get related dbfield name via displayFieldName name */    
    public int getFieldType2( int columnIndex )
    throws KExceptionClass
    {
        
        return( dbTransactionClient.getColumnType( columnIndex ) );        
    }
       

    public boolean isFieldNumeric( int columnIndex )
    throws KExceptionClass
    {
        
        return( dbTransactionClient.isColumnNumeric( columnIndex ) );        
    }
       
    
    //>>
    
    
    /** get related dbfield name via displayFieldName name */    
    public String getFullFieldName( String headerName )
    throws KExceptionClass
    {
        String fieldName = new String();
        
        Iterator names = headerFieldNameList.iterator();                        
        while( names.hasNext() ){
            fieldInformationClass fieldInformation = ( fieldInformationClass ) names.next();                   
            if( headerName.equals( fieldInformation.headerName ) ) {
               
                
                //fieldName = fieldInformation.aliasName + fieldInformation.databaseFieldName;
                
                fieldName = fieldInformation.aliasName + "\"" + fieldInformation.fieldName.toUpperCase() + "\"";                
                
                break;
            }  
        }
        
        
        //for each displayFieldName, there should be a related field name.
        if( fieldName.length() == 0 )
            throw new KExceptionClass( 
                    "*** Colunm name error **** \n" +
                    "invalid column name  [" + headerName + "] "  , null );          
        
        return fieldName;
    }
       
     
    //----------------------------------------------------------------------    
    /** get related displayFieldName name via dbfield name */    
    public String getHeaderName( String fieldName )
    {
        String headerName = new String();
        
        Iterator names = headerFieldNameList.iterator();                        
        while( names.hasNext() ){
            fieldInformationClass fieldInformation = ( fieldInformationClass ) names.next();                   
            if( fieldName.equals( fieldInformation.fieldName ) ) {
                headerName =  fieldInformation.headerName;
                break;
            }
        }           
        
        //for some of field name, there is no change for displayFieldName name,
        // and is not added to displayFieldName field name list yet.
        if( headerName.length() == 0 ) {
            //add it to displayFieldName field name list.
            headerFieldNameList.add( new fieldInformationClass( "", fieldName, fieldName ) );  
            headerName = fieldName;
        }
            
        return headerName;
    }
    
        
    //----------------------------------------------------------------------
    /** Following methods are setting parameters or criteria for SQL  */
 
    
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    
    /**
        BINDING
    */
    public void bindDefaultParameter(String parameterName,Object parameterValue){    
        mainBindingParameterList.add( new boundParameterClass( parameterName, parameterValue ) );
    }
     
    /**   Add the criteria for SQL query.  */   
    public void setDefaultCriteria( String criteria ){ mainCriteria = criteria; }    
    
    /**   Add the criteria for SQL query.  */   
    public String getDefaultCriteria(){ return( mainCriteria ); }   
    
   /**   Clear the default criteria for SQL query.  */     
    public void clearDefaultCriteria()
    {    
        mainCriteria = "";
        mainBindingParameterList.clear();
    }    
    
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------    
    
    /**   Add the criteria for SQL query.  */   
    public void setDefaultParameters( java.util.List parameters ){                    
        mainBindingParameterList = parameters;
    }    
    
    /**   Add the criteria for SQL query.  */   
    public java.util.List getDefaultParameters(){            
        return( mainBindingParameterList );
    }   


    //----------------------------------------------------------------------    
    //----------------------------------------------------------------------
    
    
    /**   Set the default sorting order for SQL query.  */ 
    public void setDefaultOrder(String order)
    {    
        if( order != null && !order.equals(""))
            defaultOrderSQL = " ORDER BY " + order;
    }    
   
    
    /**   Clear the sorting order for SQL query.  */    
    public void clearDefaultOrder()
    {    
        defaultOrderSQL = "";
    }   
      
    
     //----------------------------------------------------------------------
     //----------------------------------------------------------------------
    
    /**   Set the whole custom sorting order for SQL query. 28SEP12 */ 
    public void setCustomOrder(java.util.List orderList) 
    throws KExceptionClass
    {    
        String order = new String();
        
        //get db field name via displayFieldName name
        Iterator displayFieldNameIterator = orderList.iterator();   
        while( displayFieldNameIterator.hasNext() ){
            
            String displayFieldName = ( String ) displayFieldNameIterator.next();           
            String sortMode = displayFieldName.substring( displayFieldName.length() - 3 , displayFieldName.length() );
            
            // get the db field name
            String databaseFieldName  = getFullFieldName( displayFieldName.substring( 0, displayFieldName.length() - 4 )  );
                  
            if( sortMode.equals( "DSC" ) ) sortMode = "DESC";
                
            order += databaseFieldName + " " + sortMode + ", ";
                
        }         
        
        if( order.length() != 0 )
        {
            order = order.substring( 0, order.length()- 2 );
            customOrderSQL = " ORDER BY " + order;
        }
        else
            customOrderSQL = ""; 
        
    }    
     
     //----------------------------------------------------------------------
     //----------------------------------------------------------------------    
    
   /**   Clear the custom criteria for SQL query.  */    
    public void clearCustomCriteria()
    {
        customBindingParameterList.clear();
        customCriteriaList.clear(); 
    }


    /**   adds the databaseFieldName-criteria-operator fileds. Used by QBE screen */         
    public void addCustomCriteria( java.util.List filters ) throws KExceptionClass    
    {    
        if( filters.size() == 0  ) return;
        
        String criteria = "";
        
        //assemble each filter
        Iterator filterTrios = filters.iterator();                        
        while( filterTrios.hasNext() ){
            
            filterClass currentTrio = ( filterClass ) filterTrios.next();  
            //find real field names.
            String fieldName = ( String ) getFullFieldName( currentTrio.name );
            
            
            // ==================================================================
            //column type is character. 
            if( currentTrio.type == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_CHARACTER ) {
                
                criteria += " UPPER( " + fieldName + " ) ";
                currentTrio.value = currentTrio.value.toUpperCase();
                if( currentTrio.value.startsWith("NOT") ) {
                    currentTrio.value = currentTrio.value.substring( 3 );
                    currentTrio.value = currentTrio.value.trim();
                    criteria += "NOT ";               
                }
                
                criteria += "LIKE UPPER( ? ) "; 
                
                //binding parameters
                customBindingParameterList.add( 
                    new boundParameterClass( 
                        ":x" + String.valueOf( BindingParameterNo ), currentTrio.value + "%" ) );
                BindingParameterNo++;
            }
            
            
            // ==================================================================
            else if(
                ( currentTrio.type == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERICNOFORMAT ) ||                    
                ( currentTrio.type == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERIC ) ||
                ( currentTrio.type == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERIC2 ) ||
                ( currentTrio.type == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_CURRENCY )                    
            ){
                criteria += fieldName;
                if( currentTrio.value.startsWith("<") ) {
                    criteria += "< ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim();
                }
                else if( currentTrio.value.startsWith(">") ) {
                    criteria += "> ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim(); 
                }
                else if( currentTrio.value.startsWith("not") || currentTrio.value.startsWith("NOT") ) {
                    criteria += "<> ";
                    currentTrio.value = currentTrio.value.substring( 3 );
                    currentTrio.value = currentTrio.value.trim(); 
                }
                else
                    criteria += "= ";
                
                criteria += "? "; 
                                
                //binding parameters
                customBindingParameterList.add( 
                    new boundParameterClass( 
                        ":x" + String.valueOf( BindingParameterNo ), currentTrio.value ) ); 
                
                BindingParameterNo++;
            }            
            
            
            // ==================================================================
            else if( currentTrio.type == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_DATE ) {
                criteria += fieldName;
                
                
                if( currentTrio.value.startsWith( "=" ) ) {
                    criteria += " = ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim(); 
                    
                } else if( currentTrio.value.startsWith("<") ) {
                    criteria += "< ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim();
                }
                else if( currentTrio.value.startsWith(">") ) {
                    criteria += "> ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim(); 
                }
                else if( currentTrio.value.startsWith("not") || currentTrio.value.startsWith("NOT") ) {
                    criteria += "<> ";
                    currentTrio.value = currentTrio.value.substring( 3 );
                    currentTrio.value = currentTrio.value.trim(); 
                }
                else
                    criteria += "= ";
                
                
                
                //MIH PEND criteria += " TO_DATE( :x" + String.valueOf( BindingParameterNo ) + ", 'MM/DD/YYYY' ) "; 
                // sql serVER criteria += " CONVERT( DATETIME, ? , 103 ) "; 
                criteria += configuration.getField( "date_parsing_sql" ); 
                                                
                                
                //binding parameters
                customBindingParameterList.add( 
                    new boundParameterClass( ":x" + String.valueOf( BindingParameterNo ), currentTrio.value ) ); 
                
                BindingParameterNo++;
            }
            
            // ==================================================================      
            //column type is TIME. 
            else if( currentTrio.type == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_TIME ) {
                criteria += fieldName;
                
                if( currentTrio.value.startsWith( "=" ) ) {
                    criteria += " = ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim(); 
                    
                } else if( currentTrio.value.startsWith("<") ) {
                    criteria += "< ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim();
                }
                else if( currentTrio.value.startsWith(">") ) {
                    criteria += "> ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim(); 
                }
                else if( currentTrio.value.startsWith("not") || currentTrio.value.startsWith("NOT") ) {
                    criteria += "<> ";
                    currentTrio.value = currentTrio.value.substring( 3 );
                    currentTrio.value = currentTrio.value.trim(); 
                }
                else
                    criteria += "= ";
                
                //MIH criteria += " TO_DATE( :x" + String.valueOf( BindingParameterNo ) + ", 'HH:MI AM' ) " ; 
                // criteria += " CONVERT ( DATE, ?, 108 ) " ;  // Se requiere formatear el tiempo a hh24:mi:ss DE 'HH:MI AM'
                // criteria += " CAST( ? AS TIME) "; 
                criteria += configuration.getField( "time_parsing_sql" ); 
                
                
                //binding parameters
                customBindingParameterList.add( 
                    new boundParameterClass( ":x" + String.valueOf( BindingParameterNo ), currentTrio.value ) ); 
                
                BindingParameterNo++;
            }
            
            // ==================================================================            
            //column type is currency. 
            else if( currentTrio.type == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_CURRENCY ) {
                criteria += fieldName;
                
                if( currentTrio.value.startsWith( "=" ) ) {
                    criteria += " = ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim(); 
                    
                } else if( currentTrio.value.startsWith("<") ) {
                    criteria += "< ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim();
                }
                else if( currentTrio.value.startsWith(">") ) {
                    criteria += "> ";
                    currentTrio.value = currentTrio.value.substring( 1 );
                    currentTrio.value = currentTrio.value.trim(); 
                }
                else if( currentTrio.value.startsWith("not") || currentTrio.value.startsWith("NOT") ) {
                    criteria += "<> ";
                    currentTrio.value = currentTrio.value.substring( 3 );
                    currentTrio.value = currentTrio.value.trim(); 
                }
                else
                    criteria += "= ";
                

                criteria += "?" ; 
                                
                //binding parameters
                customBindingParameterList.add( 
                    new boundParameterClass( ":x" + String.valueOf( BindingParameterNo ), currentTrio.value ) ); 
                
                BindingParameterNo++;
                
            // ==================================================================
                
            } else {
                
                    throw new KExceptionClass(
                        "*** Could not set custom criteria ***\n" +
                        "Invalid field type code\n"    , null                       
                    );
            };
            
            if( filterTrios.hasNext() ) criteria += " AND ";
            
        } // loop
        
        customCriteriaList.add( criteria );       
  
    }
  
    //----------------------------------------------------------------------
    
    /**   Add SQL to the WHERE clause  */   
    public void addCustomCriteria(String criteria)
    {    
        customCriteriaList.add( criteria );
    }    
    
    //----------------------------------------------------------------------
    /** Following methods are setting parameters or criteria for SQL  */
    
    /**
        BINDING
        Parameters can be reassigned at any moment 
        and may be reset only partially				
    */
    public void bindCustomParameter(String parameterName,Object parameterValue)
    {    
        customBindingParameterList.add( new boundParameterClass( parameterName, parameterValue ) );
    }
    
    //----------------------------------------------------------------------
    
    /**   Clear the custom settings for SQL query.  */    
    public void clearAllCustomSettings()
    {    
        customOrderSQL = ""; 
        clearCustomCriteria( );        
    }   
    
    
     //----------------------------------------------------------------------
     //----------------------------------------------------------------------
     //----------------------------------------------------------------------
    
       
    //----------------------------------------------------------------------
    /**   Execute the query and get the results.  */
    public void load( int startRow, int length )
    throws KExceptionClass
    {        
        bindDBtransaction( 
            dbTransactionClient, " SELECT " + selectSQL + " FROM " + tableName + finalWhere + finalOrder         
            );  

        //Query DB
        try{
            
            KMetaUtilsClass.cursorWait( parentWindow );
            KMetaUtilsClass.setProgressBarValue1( 60 );
            
            dbTransactionClient.executeQuery( startRow, length );  
            
            KMetaUtilsClass.setProgressBarValue1( 100 );
            
        }finally{        
            KMetaUtilsClass.cursorNormal( parentWindow );            
        }
        
        if( !dbTransactionClient.columnNamesList.contains( keyField ) ) {
            throw new KExceptionClass( 
                "*** Could not load data **** \n" +
                "Key field [" + keyField + "] was not found in result set" , null 
                );
        }    
        
        firstLoadReady = true;
        
        
        log.log( this, "dbTransactionClient rowCount = " + dbTransactionClient.rowCount());
        log.log( this, "dbTransactionClient startRow = " + dbTransactionClient.resultSetStart());                        
    }
    

    //----------------------------------------------------------------------
    /**   check whether therer is any loaded value   */
    public boolean nextRowValue()
    throws KExceptionClass    
    {   
        return dbTransactionClient.fetch();
    }

    
    //----------------------------------------------------------------------
    /**   get next loaded value   */
    public void getRowValue( recordClass record )
    throws KExceptionClass    
    {         
        if( record == null )
            throw new KExceptionClass(
                "*** Object reference error **** \n" +
                "The reference is null." , null  ); 
        
        int fieldIndex = 0;    
        Iterator columnNames = dbTransactionClient.columnNamesList.iterator();      
 
        // build current row
        while( columnNames.hasNext() ){                
            
            String column_name = ( String ) columnNames.next();             

            //Add the key value to the last record field
            if( column_name.equals( keyField ) )
                record.setValueAt( 
                        record.getRecordLength()-1, 
                        dbTransactionClient.getProperty( column_name ) ); 
            
            if( showKeyField || !column_name.equals(keyField) ) {
                record.setValueAt( 
                        fieldIndex++, 
                        dbTransactionClient.getProperty( column_name ) );
            }  
        } 
       
    }


    //----------------------------------------------------------------------
    /**   get first loaded row index in DB table  */
    public int getloadedStartRowIndex(  )    
    throws KExceptionClass
    {    
        if( !firstLoadReady )
            throw new KExceptionClass(
                    "*** Data Loading error **** \n" +
                    "Can not return start row before data loading..." , null 
            );      
        
        return dbTransactionClient.resultSetStart();
    }
  
    
    //----------------------------------------------------------------------
    /**   get number of rows being loaded  */
    public int getloadedRowCount(  )    
    throws KExceptionClass
    {    
        if( !firstLoadReady )
            throw new KExceptionClass(
                    "*** Data Loading error **** \n" +
                    "Can not return row count before data loading..." , null 
            );      
        
        return dbTransactionClient.rowCount();
    } 

    //----------------------------------------------------------------------
    /**   prepare a DB transaction equivalent to this table  */
    public void prepareDBTransactionForTable( dbTransactionClientClass dbTransaction )    
    throws KExceptionClass
    {    
        if( !firstLoadReady )
            throw new KExceptionClass(
                    "*** Data Loading error **** \n" +
                    "Can not return db data before data loading..." , null 
            );      

        bindDBtransaction( 
            dbTransaction, " SELECT " + selectSQL + " FROM " + tableName + finalWhere + finalOrder
            );                    
    } 

    //----------------------------------------------------------------------
    /**   prepare a DB transaction equivalent to this table  */
    public void prepareDefaultDBTransactionForTable( dbTransactionClientClass dbTransaction, String orderBy )     
    throws KExceptionClass
    {    
        if( !firstLoadReady )
            throw new KExceptionClass(
                    "*** Data Loading error **** \n" +
                    "Can not return db data before data loading..." , null 
            );      

        bindDBtransaction( 
            dbTransaction, " SELECT " + selectSQL + " FROM " + tableName + defaultWhere + orderBy
            );                    
    } 

    
    //----------------------------------------------------------------------
    /**   prepare a DB transaction equivalent to this table considering dufault criteria  */
    public void prepareDefaultDBTransactionForTable( dbTransactionClientClass dbTransaction )    
    throws KExceptionClass
    {    
        if( !firstLoadReady )
            throw new KExceptionClass(
                    "*** Data Loading error **** \n" +
                    "Can not return db data before data loading..." , null 
            );      

        bindDBtransaction( 
            dbTransaction, " SELECT " + selectSQL + " FROM " + tableName +  defaultWhere + defaultOrderSQL 
            );                    
    } 


    //----------------------------------------------------------------------    
    /**     make SQL query for certain operation and return the result. */
    public Map executeSQLOperation( String operation, boolean reflectCustomFilter )
    throws KExceptionClass    
    {

        dbTransactionClientClass dbTransaction = new dbTransactionClientClass( configuration, log );                 
        String sql;
        
        if( reflectCustomFilter ){
            // do bind custom parameters            
            sql =  " SELECT " + operation + " FROM " + tableName + finalWhere;               
            bindDBtransaction( dbTransaction, sql );                         
                        
        } else {            
            // do NOT bind custom parameters                        
            sql =  " SELECT " + operation + " FROM " + tableName + defaultWhere;   
            bindDefaultDBtransaction( dbTransaction, sql ); 
        };
                
        //Query to DB
        dbTransaction.executeQuery( 0, 10 );  
        
        if( !dbTransaction.fetch() ) 
            throw new KExceptionClass( 
                "*** SQL total error **** \n" +
                "No row was returned." +
                "SQL: [" + sql + "]\n "  , null );
            
        return( dbTransaction );
        
    }    

    
    //----------------------------------------------------------------------
    /**   prepare a DB transaction equivalent to this table  
          with custom fields clause */
    

    public void prepapreCustomFieldsDBTransaction( 
        String customFields, dbTransactionClientClass dbTransaction, boolean reflectCustomFilter )    
    throws KExceptionClass
    {    
        if( !firstLoadReady )
            throw new KExceptionClass(
                    "*** Data Loading error **** \n" +
                    "Can not return db data before data loading..." , null 
            );      

        String sql;
        
        if( reflectCustomFilter ){
            // do bind custom parameters            
            sql =  " SELECT " + customFields + " FROM " + tableName + finalWhere;               
            bindDBtransaction( dbTransaction, sql );                         
            
        } else {            
            // do NOT bind custom parameters                        
            sql =  " SELECT " + customFields + " FROM " + tableName + defaultWhere;   
            bindDefaultDBtransaction( dbTransaction, sql );             
        };
                            
    } 
    
    //-------------------------------------------------------------------------------
    

    
}


