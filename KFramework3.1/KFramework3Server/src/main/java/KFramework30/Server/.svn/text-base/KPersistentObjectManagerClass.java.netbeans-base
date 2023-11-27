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

import ProblemDomainComponent.systemUserClass;
import ProblemDomainComponent.systemLogClass;
import javax.persistence.*;
import KFramework30.Base.*;
import KFramework30.Server.KFrameworkServerEngineClass.StatusEnum;
import java.sql.ResultSetMetaData;
import java.util.*;


public class KPersistentObjectManagerClass{
       
    // uses
    protected KConfigurationClass                   configuration;
    protected KPersistentObjectManagerInterface     cascadeDeletionLogicHandler;
                 
    // has - defaulted
    protected Map< String, KBusinessObjectClass  >  objectModelCache;
    private   List< String >                        subscribedObjectNames = new Vector();

        
    // contants   
    

    // Exceptions
    
        public static class KPOMObjectNotFoundException
        extends KExceptionClass
        {
            public KPOMObjectNotFoundException( String message ){
                super(message , null );
            }
        }    

        
        public static class integrityExceptionClass
        extends KExceptionClass{                
            public integrityExceptionClass( String message ){                    
                super( "Data Integreity Rule Violation: " + message, null );
            }
        }
        
    
    // interfaces
        
        public interface  KPersistentObjectManagerInterface{
            
            public void runCascadeDeletionBusinessLogic(                   
                    KBusinessObjectClass  deletee, String OIDname, long OID,
                    KBusinessObjectClass  child, KLogClass log
                    ) throws integrityExceptionClass, KExceptionClass; 
         
        }
                                    
        
        
    //----------------------------------------------------------------------    
    // interface
    
     
    /** Creates new persistentObjectManager */
    public KPersistentObjectManagerClass( KConfigurationClass configurationParam ) 
    throws KExceptionClass{

        // inherits
        super();

        // uses
        configuration = configurationParam;
                                
        // has
        objectModelCache = new HashMap< String, KBusinessObjectClass  >();         
                                     
    }
        

    
    /** register class for integrity verification */
    public void subscribePDCforIntegrityEnforcement( String ClassName ){
        
        subscribedObjectNames.add( ClassName );
                    
    }
            
    //----------------------------------------------------------------------                          
    
    
  /**  Useful to get a valid record primary key to initialize Foreign Keys */
     public long getDefaultId( KTransactionClass transaction, KLogClass log, String IDfield, String Table ) 
     throws KExceptionClass {
         
        return getDefaultId( transaction, log, IDfield, Table, " SELECT MIN( " + IDfield + " ) as " + IDfield + " FROM " + Table + " WHERE " + IDfield + "!= -1 " );
     }    
    
     /**  Useful to get a valid record primary key to initialize Foreign Keys */
     public long getDefaultId(  KTransactionClass transaction,  KLogClass log, String IDfield, String Table, String SQLcommand ) 
     throws KExceptionClass{

            long result = -1;
                        
                KDBQueryClass query = new KDBQueryClass( configuration, log, transaction );
                    
                try{

                    
                    query.prepare( SQLcommand );
                    query.executeQuery();
                    
                    if ( query.fetch() ){
                                           
                        result = KMetaUtilsClass.getIntegralNumericValueFromString( query.getField( IDfield ) );                                                
                    }
                    else 
                        throw new KExceptionClass( "*** Not data in table "  +  Table +  " ***\n" , null );	                   
    
                   
                    
                }catch( Exception error ){

                    throw new KExceptionClass(
                            "*** Could not get default value for " + Table + "****\n" +
                            error.toString(), error
                            );                   
                }finally{
                    
                    query.close();
                }
            
              return result;  
        }
         

    
    
    //----------------------------------------------------------------------            
    
    /** Login to authenticate a session. Not abstract, the POM can be used with out front end and sessions built by hand and then called initialize*/
    public  KBusinessObjectClass login( KLogClass log, systemUserClass userTryingToLogin, KSessionClass session  ) 
    throws KExceptionClass{
        
        // might not be required to override
        
        return( null );
    };   
    
    
    // ----------------------------------------------------------------    
    // ----------------------------------------------------------------
    // GENERIC POM actions

    
            // --------------------------------------------------------------
            public long push_back( KTransactionClass transaction, KLogClass log, KBusinessObjectClass ProblemDomainObject )  
            throws Exception
            {
                // versioning
                if( ProblemDomainObject.versionFieldset ){
                    // initilize versioning
                    ProblemDomainObject.setField(ProblemDomainObject.versionField, 0 );
                }
                                                                             
                // JPA  session.getEntityTransaction().remove/persist etc  -> is the JPA entity manager of the sesson
                transaction.getEntityManager().persist( ProblemDomainObject );
                transaction.getEntityManager().flush();
                // Business Logic                                       

                logAction( transaction, "NEW", ProblemDomainObject, ProblemDomainObject.toFullString() );                        

                log.log( this, "Push back the new [" + ProblemDomainObject.getClass().getName() + "]into DB complete." ); 

                return ProblemDomainObject.getOID();
            }

            // --------------------------------------------------------------
                        
            /** Get the object in DB */
            public KBusinessObjectClass copy( KTransactionClass transaction, KLogClass log, String objClass, long id ) 
            throws Exception {

                String message = "Copying object: [" + objClass + "] object id: [" + id + "]";
                log.log( this, message ); 
                
                KBusinessObjectClass ProblemDomainObject = 
                        (KBusinessObjectClass) transaction.getEntityManager().find(
                            Class.forName(  objClass  ), id );

                if( ProblemDomainObject == null){
                    throw new KPOMObjectNotFoundException(
                            "*** Could not retrive object from DB ***\n" +
                            "Object " + objClass  +  " [" + id + "] does not exist, or it was removed"  
                            );
                }

               if( ProblemDomainObject.getOID() != id){
                    throw new KExceptionClass(

                            "*** Could not retrive object from DB ***" +
                            "Retrived Object ID is [" + ProblemDomainObject.getOID() + "] instead of [" + id + "]."  , null 

                            );
                }                        
                
              
                log.log( this, "Copy complete." );
                
                return ProblemDomainObject;

            }                                
                    

            // --------------------------------------------------------------

            /** Update the object in DB */
            public long update( KTransactionClass transaction, KLogClass log, KBusinessObjectClass updatedProblemDomainObject )
            throws Exception
            {

                String message = "Updating object: [" + updatedProblemDomainObject.getClass().getName() + "], object id: [" + updatedProblemDomainObject.getOID() + "].";
                log.log( this, message ); 
                
                // JPA  session.getEntityTransaction().remove/persist etc  -> is the JPA entity manager of the sesson                
                // find old object ....
                KBusinessObjectClass currentProblemDomainObject = 
                        (KBusinessObjectClass) transaction.getEntityManager().find(
                            Class.forName(  updatedProblemDomainObject.getClass().getName()  ), updatedProblemDomainObject.getOID() );

                if( currentProblemDomainObject == null){
                    throw new KPOMObjectNotFoundException(
                            "*** Could not update object ***\n" +
                            "Object [" + updatedProblemDomainObject.getOID() + "] does not exist, or it was removed" 
                            );
                }   
                                                    
                if( updatedProblemDomainObject.getOID() == -1 || updatedProblemDomainObject.getOID() == 0 )
                    throw new KExceptionClass(
                            "This is a read only record and cannot be changed or deleted. (PK<1)"  , null 
                            ); 
                
                // versioning
                if( updatedProblemDomainObject.versionFieldset ){
                    
                    long currentObjectVersion = KMetaUtilsClass.getIntegralNumericValueFromString( currentProblemDomainObject.getFieldasString(currentProblemDomainObject.versionField) );
                    long updatedObjectVersion = KMetaUtilsClass.getIntegralNumericValueFromString( updatedProblemDomainObject.getFieldasString(updatedProblemDomainObject.versionField) );
                    
                    if( currentObjectVersion > updatedObjectVersion ){
                        throw new KExceptionClass(
                            "Can not update " + updatedProblemDomainObject.getClass().getName() + "." +
                            " The record was updated by another process since you openned it. Please close the editor and reload." , null 
                            ); 
                    }
                }

                            
                // diff
                String changes = currentProblemDomainObject.diff( updatedProblemDomainObject  );
                
                // shift versions 
                // versioning
                if( updatedProblemDomainObject.versionFieldset ){    
                    
                    updatedProblemDomainObject.setField(
                            updatedProblemDomainObject.versionField, 
                            KMetaUtilsClass.getIntegralNumericValueFromString( updatedProblemDomainObject.getFieldasString(updatedProblemDomainObject.versionField) ) + 1
                            );
                }
                                
                // MERGE -->
                transaction.getEntityManager().merge( updatedProblemDomainObject );
                transaction.getEntityManager().flush();
                
                if( !changes.equals( "" ) ){
                    logAction( transaction, "UPDATE", updatedProblemDomainObject, changes  );                                                                      
                }
                
                log.log( this, "Update complete." ); 

                return updatedProblemDomainObject.getOID();
            }

            
            // --------------------------------------------------------------            
            public long delete(  KTransactionClass transaction, KLogClass log, String objClass, long id )
            throws Exception
            {
                                
                String message = "Deleting object: [" + objClass + "] OID: [" + id  + "]";
                log.log( this, message ); 

                if( id == -1 || id == 0 )
                    throw new KExceptionClass(
                            "This is a read only record and cannot be changed or deleted."  , null 
                            );
                
                        // JPA  session.getEntityTransaction().remove/persist etc  -> is the JPA entity manager of the sesson                

                        // LOAD
                        KBusinessObjectClass ProblemDomainObject = 
                                (KBusinessObjectClass) transaction.getEntityManager().find(
                                    Class.forName(  objClass  ), id );

		                if( ProblemDomainObject == null){
		                    throw new KPOMObjectNotFoundException(
		                            "*** Could not delete object from DB ***\n" +
		                            "Object [" + id + "] does not exist, or it was removed already."  
		                            );
		                }
		                        
		                if( ProblemDomainObject.getOID() != id ){
		                    throw new KExceptionClass(
		                            "*** Could not delete object from DB ***\n" +
		                            "Found Object ID is [" + ProblemDomainObject.getOID() + "] in stead of [" + id + "]."  , null 
		                            );
		                }                              

                        
                        // check referential integrity
                        cascadeDeletion( transaction, log, objClass + ":" + id + ">> ",  ProblemDomainObject );
                        
                        // what ever, flush it ...
                        transaction.getEntityManager().flush();
                        
                 return id;
            } 

            
            // ----------------------------------------------------------------------------------------------            
            private void cascadeDeletion( KTransactionClass transaction,KLogClass log,  String indent, KBusinessObjectClass deletee  ) 
            throws KExceptionClass{
                                                                            
               try{           
                   
                // ================================================================

                if( objectModelCache.size() == 0 ) {
                    
                    log.log( this, "Building an object hierarchy cache..." );
                    
                    // build chache
                    Iterator<String> subscribedClasses = subscribedObjectNames.iterator();                    
                    while( subscribedClasses.hasNext() ){
                        String currentClass = subscribedClasses.next();
                        objectModelCache.put( currentClass, 
                                (KBusinessObjectClass) ( Class.forName( currentClass ).newInstance() )
                                );                                               
                    }
                    
                }
                               
                // ================================================================
               
               log.log( this, indent 
                        + "Preparing to delete " + deletee.getClass().getName() + ": " 
                        + deletee.getFieldasString( deletee.fieldNames.firstElement() ) );                   

                    // get PK name
                    String deleteeOIDname = deletee.OIDfield;

                    // get PK value
                    long deleteeOID = deletee.getOID();
                   
                   
                    // ========================================================================
                    // Scroll all objects
                    
                    Iterator< KBusinessObjectClass  > ProblemDomainObjects = objectModelCache.values().iterator();                    
                    while( ProblemDomainObjects.hasNext() ){   
                        
                        // get object to check
                        KBusinessObjectClass childObject =  ProblemDomainObjects.next();
                        
                    // CHECK pointer to PK by name
                        
                        // skip myself on PK
                        if( !childObject.getClass().getName().equals( deletee.getClass().getName() ) ){
                        
                            // check if this is my child, my child if it contains my pk field...as FK
                            log.log( this, indent + "Checking " + childObject.getClass().getName() + " for " + deleteeOIDname );
                            
                            if( childObject.fieldNames.contains( deleteeOIDname    ) ){

                                deleteChild( 
                                        transaction, log,
                                        indent, 
                                        deletee, deleteeOIDname, deleteeOID, 
                                        childObject );

                            } // if child
                            
                        }
                      
                    // =========
                        
                    // CHECK pointer to PK by alias  , alias are required because you might have 2 fks and they cant be named the same                      
                        
                        // Busca por los alias
                        if( childObject.repeatedFKaliasNames.containsKey( deleteeOIDname ) ){                                                       
                            
                            List PKaliasList = (List) childObject.repeatedFKaliasNames.get( deleteeOIDname );                            
                            
                            for ( int i = 0; i < PKaliasList.size(); i++) {
                                
                                String PKalias = (String) PKaliasList.get(i);
                                deleteChild(
                                        transaction, log, 
                                        indent,
                                        deletee, PKalias, deleteeOID, 
                                        childObject);
                            }
                            
                        } // if child
                        
                    } // loop children scroll
                    
                    // Scroll all objects
                    // ========================================================================                    
                    
                    
                    // no children, deleteme...
                    log.log( this,  indent + "*** Deleting " + deletee.getClass().getName() + " " + deleteeOID + " ... " );  

                    transaction.getEntityManager().remove( deletee  );   
                    transaction.getEntityManager().flush();
                    
                    logAction( transaction, "DELETE", deletee, deletee.toFullString()  );                     
                    
                    log.log( this, 
                            indent + "*** Deleting " + deletee.getClass().getName() + " " + deleteeOID + " ... done! " );  
                    
               
                // re entrante
                }catch( Exception error ){
                    
                    log.log( this, KMetaUtilsClass.getStackTrace(error) );                    
                    
                    // on reentrant dont concatenate errors       V
                    throw new KExceptionClass( error.toString(), null );                    
                }
                
                
            }
            
            
            // ----------------------------------------------------------------------------------------------            
            
            private void deleteChild(
                    KTransactionClass transaction,  KLogClass log, 
                    String indent, 
                    KBusinessObjectClass  deletee, String OIDname, long OID, 
                    KBusinessObjectClass childObject
                    ) throws KExceptionClass{
                // YES, grab all children
                
                String childTable = childObject.getClass().getName();
                childTable = childTable.substring( childTable.lastIndexOf(".") +  1 );                  
                
                String SQL =
                        " select x  " +
                        " from " + childTable  + " x " +
                        " where x." + OIDname + " =  ?1 ";    
                
                log.log( this, indent + "  Check for children ..."  + SQL );                                                                        
                
                Query query = transaction.getEntityManager().createQuery( SQL );                
                query.setParameter( 1, OID  );
                List< KBusinessObjectClass  > results = query.getResultList();                            

                log.log( this, indent + "  " + results.size() + " child objects found! ***" );

                // cascade  ----------
                for( KBusinessObjectClass child: results  ){
                    
                    if(  cascadeDeletionLogicHandler != null ) cascadeDeletionLogicHandler.runCascadeDeletionBusinessLogic(  deletee, OIDname, OID, child, log );
                    
                    cascadeDeletion( transaction, log, indent + childObject.getClass().getName() + ":" + child.getOID() + ">> ",  child );
                }                                                             
                // cascade  ----------                                                
                
            }
                        
            

            
    // POM actions                                
    // ----------------------------------------------------------------    
    // ----------------------------------------------------------------

            
          
            //----------------------------------------------------------------------                
    
            public void logAction( 
                    KTransactionClass transaction, 
                    String action, KBusinessObjectClass target, String message  )
                    throws KExceptionClass
            {

                // logger

                try{
                    
                    if( !configuration.getField( "audittrail" ).equals( "yes" ) ) return;
                    
                    systemLogClass loggedAction = new systemLogClass();

                    loggedAction.setLog_id( 0 );
                    loggedAction.setLog_user( transaction.getSESSION_ID() );        
                    loggedAction.setLog_time( KMetaUtilsClass.timeStamp() );
                    loggedAction.setLog_action( action );
                    loggedAction.setLog_object_id( target.getOID() );
                    loggedAction.setLog_object_class( target.getClass().getName() );

                    if( message.length() > 4000 ) message = message.substring( 0, 4000 );
                    loggedAction.setLog_object_message( message );        

                    transaction.getEntityManager().persist( loggedAction );
                    
                }catch( Exception error){                    
                    throw new KExceptionClass( "Can not add audit trial entry to database", error  );
                }
                

            }

             //----------------------------------------------------------------------   
            
            
   
    public ResultSetClass processSQLRequest(
            SQLMessageClass ReceivedCmd, KTransactionClass transaction1, KLogClass log) throws KExceptionClass {
       
                          
                // header
                log.log( this,  "SQL command : " + ReceivedCmd.SQL );
                log.log( this,  "start row : " + ReceivedCmd.start_row );
                log.log( this,  "Lengh : " + ReceivedCmd.lengh );
                log.log( this,  "session id : " + ReceivedCmd.session_id );

                // parameters
                List< FieldClass > paramsList = ReceivedCmd.params;
                for( int i =0; i< paramsList.size(); i++){

                    log.log( this,  
                            " Parameter [" + paramsList.get(i).fieldName + "]" +
                            "[" + paramsList.get(i).fieldValue + "]" +
                            "[" + paramsList.get(i).fieldType + "] A1"
                            );   
                }  

                // exec ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    
                KDBQueryClass query = new KDBQueryClass( configuration, log, transaction1 );                    
                ResultSetClass resultSet = new ResultSetClass();              
                
                try{
                      

                    // prepare command
                    query.prepare( ReceivedCmd.SQL );
                    
                    // bind                    
                    ListIterator< FieldClass > parameters = ReceivedCmd.params.listIterator();
                    int count = 1;
                    while( parameters.hasNext() ){  
                        
                        FieldClass parameter = parameters.next();
                        
                        if( parameter.fieldType.equals( KMetaUtilsClass.parameterTypeEnum.STRING.toString() ) ){
                            query.bind( count, parameter.fieldValue );
                        }else
                          
                        //--    
                            
                        if( parameter.fieldType.equals( KMetaUtilsClass.parameterTypeEnum.INT.toString() ) ){
                            query.bind( count, KMetaUtilsClass.getIntegralNumericValueFromString( parameter.fieldValue ) );
                        }else
                            
                        if( parameter.fieldType.equals( KMetaUtilsClass.parameterTypeEnum.LONG.toString() ) ){
                            query.bind( count, KMetaUtilsClass.getIntegralNumericValueFromString( parameter.fieldValue ) );
                        }else

                         //--
                            
                        if( parameter.fieldType.equals( KMetaUtilsClass.parameterTypeEnum.FLOAT.toString() ) ){
                            query.bind( count, KMetaUtilsClass.getDecimalNumericValueFromString( parameter.fieldValue ) );
                        }else
                            
                        if( parameter.fieldType.equals( KMetaUtilsClass.parameterTypeEnum.DOUBLE.toString() ) ){
                            query.bind( count, KMetaUtilsClass.getDecimalNumericValueFromString( parameter.fieldValue ) );
                        }else

                            
                        //--
                            
                        if( parameter.fieldType.equals( KMetaUtilsClass.parameterTypeEnum.DATE.toString() ) ){
                            query.bind( count, KMetaUtilsClass.stringToDate( KMetaUtilsClass.KDEFAULT_LONG_DATE_TIME_FORMAT,  parameter.fieldValue ) );
                        }else{
                                                        
                            throw new KExceptionClass(
                                    "Could not exec SQL, unkown param type :" + parameter.fieldType +
                                    " for parameter " + parameter.fieldName , null
                                    );
                        }
                            
                        
                        count++;
                    }
          
                    // actual exec
                    query.executeQuery();

                    
                    // ---------------------------------------------------------------------------
                    // get set meta data
                    
                    
                        ResultSetMetaData metaData = query.getMetaData();
                        String ColumnNamesLog = "Resultset: ";
                        for( int i = 0; i < query.columnCount(); i++ ){

                            ColumnNamesLog += "{" + query.getColumnNames()[ i ].toUpperCase() + "}" + metaData.getColumnTypeName( i + 1 ) + "->" + metaData.getColumnType( i + 1 );
                                  
                            resultSet.column_names.add( query.getColumnNames()[ i ].toUpperCase() );   
                            resultSet.column_types.add( metaData.getColumnType( i + 1 ) );
                           
                        } 

                        log.log( this, ColumnNamesLog );

                        
                        
                    // ---------------------------------------------------------------------------
                    // load data  
                        
                                        
                        log.log( this, "Loading  ... >>>>>>>>> \n\n" ); 
                        log.log( this,  "start row : " + ReceivedCmd.start_row );
                        log.log( this,  "Lengh : " + ReceivedCmd.lengh );
                        
                            
                            log.log( this, "Scroll to  ... " + ReceivedCmd.start_row );                             
                            if( ReceivedCmd.start_row > 0 ){ 
                                
                                if( !query.getResultSet().absolute( (int) ReceivedCmd.start_row ) ){                                    
                                    
                                    log.log( this, ".. past end of data!!" );                             
                                    
                                    // move last
                                    query.getResultSet().last();                            
                                    int lastRow = query.getResultSet().getRow();

                                    log.log( this, "End at  ... " + lastRow );                                                         

                                    //
                                    if( lastRow > ReceivedCmd.lengh ){ 

                                        ReceivedCmd.start_row = lastRow - ReceivedCmd.lengh;
                                        query.getResultSet().absolute( (int) ReceivedCmd.start_row );

                                        log.log( this,  "start row : " + ReceivedCmd.start_row );
                                        log.log( this,  "Lengh : " + ReceivedCmd.lengh );

                                    }else{

                                        query.getResultSet().first();
                                        ReceivedCmd.start_row = 0;

                                        log.log( this,  "Reset - start row : " + ReceivedCmd.start_row );
                                        log.log( this,  "Lengh : " + ReceivedCmd.lengh );                                
                                    }

                                    
                                    
                                }
                            }
                            
                        log.log( this,  "Get block ...." );                                                            
                         
                        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                        
                        // all rows
                        ArrayList<RecordClass> rows = resultSet.records;                                                                                    
                                                
                        int rowCount = 0;   
                        while( query.fetch() ){

                            // make record
                            RecordClass record = new RecordClass();

                            // read data for row
                     
                            ArrayList< Object > data = new ArrayList< Object >();                                                    
                            for( int i = 0; i < query.columnCount(); i++ ){

                                if( 
                                        metaData.getColumnType( i + 1 ) == java.sql.Types.BINARY  ||
                                        metaData.getColumnType( i + 1 ) == java.sql.Types.BLOB                                        
                                    ){                                                                      

                                    byte[] columnValue = query.getBinaryField( query.getColumnNames()[ i ] );

                                    if( columnValue == null ){

                                        data.add( new byte[ 0 ] );                                           

                                    }else{

                                        data.add( columnValue );                                    

                                    }                                    
                                    
                                }else{
                                    
                                    String columnValue = query.getField( query.getColumnNames()[ i ] );

                                    if( columnValue == null ){

                                        data.add( "" );                                           

                                    }else{

                                        data.add( columnValue );                                    

                                    }                                    
                                }
                                
                            }                                                     
                                                   

                            // save row
                            record.row = data;
                            rows.add( record );                          

                            rowCount++;

                                                       
                            if( rowCount >= ReceivedCmd.lengh ) break;
                                                       
                            
                        } // loading data loop
                        
                        

                    // load data  
                    // ---------------------------------------------------------------------------                        
                        
                        
                    log.log( this, "Returned " + rows.size() + " rows, from row  " + ReceivedCmd.start_row ); 
                    
                    // exec ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                    
                       

                    // prepare result message
                            
                            resultSet.status = StatusEnum.SQL_OK.code;
                            resultSet.desc = "Service done!";
                            resultSet.session_id = ReceivedCmd.session_id;                               
                            
                            resultSet.start = (int) ReceivedCmd.start_row;
                            resultSet.lengh = rowCount;
                            
                 
                    // shut down
                            
                       log.log( this, "Web service finished client request..." );                     
                       
                       log.log( this, "Servicing SQL request!! ..done! +++++++++++++++++++++++++++++++" );                                   

                    
                  
                                                                     
                                         
          }catch (Exception ex) {
                
               String message = "";
               if( ex instanceof KExceptionClass ){                   
                   
                log.log( this, ( (KExceptionClass) ex ).getLongMessage() );                                                   
                message = ( (KExceptionClass) ex ).getMessage();
                
               }else{                   
                   
                log.log( this, KMetaUtilsClass.getStackTrace( ex ) );
                message = ex.toString();
               }                   
               
               
                resultSet.status = StatusEnum.SERVER_ERROR.code;
                resultSet.desc = message;
                resultSet.lengh = 0;

               
                
            }finally{
                    
                    query.close();
            }
                  
            return resultSet;
                   
        
    }

    public void setCascadeDeletionLogicHandler(KPersistentObjectManagerInterface cascadeDeletionLogicHandler) {
        this.cascadeDeletionLogicHandler = cascadeDeletionLogicHandler;
    }
      
    
    
} // end POM


