/*
* This source code is part of the KFRAMEWORK  (http://k-framework.sourceforge.net/)
* Copyright (C) 2001  Alejandro Vazquez, Ke Li
* Feedback / Bug Reports vmaxxed@users.sourceforge.net
* 
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
* 
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package DataManagementComponent;


// JPA
import KFramework30.Server.KPersistentObjectManagerClass.integrityExceptionClass;
import javax.persistence.*;

// K
import KFramework30.Base.*;
import KFramework30.Server.*;

// app
import KFramework30.Server.KFrameworkServerEngineClass.ActionEnum;
import KFramework30.Server.KFrameworkServerEngineClass.KFrameworkServerInterface;
import KFramework30.Server.KPersistentObjectManagerClass.KPOMObjectNotFoundException;
import KFramework30.Server.KPersistentObjectManagerClass.KPersistentObjectManagerInterface;
import ProblemDomainComponent.*;

// rtl
import ProblemDomainComponent.sample_facturaClass;
import java.util.*;
import java.text.SimpleDateFormat;



public class userBusinessLogicComponentClass
implements KFrameworkServerInterface, KPersistentObjectManagerInterface
{
       
    // uses
    KConfigurationClass configuration;
  
    // has
    KPersistentObjectManagerClass   persistentObjectManager;
        
             /** Creates new persistentObjectManager */
            public userBusinessLogicComponentClass( KConfigurationClass configurationParam )		 
            throws KExceptionClass{

                // uses
                configuration = configurationParam;


                // has 

            }

    // -------------------------------------------------------------------------------------            
    @Override
    public void initialize( KLogClass log ) throws KExceptionClass{
    
        // has         
        persistentObjectManager = new KPersistentObjectManagerClass( configuration );    
        
        persistentObjectManager.setCascadeDeletionLogicHandler( this );
        
        persistentObjectManager.subscribePDCforIntegrityEnforcement( sample_clientClass.class.getName() );                
        persistentObjectManager.subscribePDCforIntegrityEnforcement( sample_facturaClass.class.getName()  );
        persistentObjectManager.subscribePDCforIntegrityEnforcement( sample_factura_statusClass.class.getName()  );            
        persistentObjectManager.subscribePDCforIntegrityEnforcement( sample_itemClass.class.getName()  );             
        persistentObjectManager.subscribePDCforIntegrityEnforcement( sample_productClass.class.getName()  );     
        
        
                
    }


    
    
    // -------------------------------------------------------------------------------------          
    @Override
    public KBusinessObjectClass processObjectRequest(
                    String action, String objClass, 
                    KBusinessObjectClass ProblemDomainObject,
                    long objID, KSessionClass session, KLogClass log )
                    throws KExceptionClass{
    
        log.log( this, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Processing request -- " + action );
        
        KBusinessObjectClass result = null;
                  
        // in this sample one transaction to one db, you might have more than one to many dbs...
        KTransactionClass transaction = new KTransactionClass( 
                configuration, log, session.getID(), configuration.getField( "db_pu" ) );

        try{
            

                //**************************************************************************************
                if( action.equals( ActionEnum.NEWOBJECT.action ) ){

                    result = buildBusinessObject(objClass, log, transaction );

                }else
                    
                    
                //**************************************************************************************
                if( action.equals( ActionEnum.PUSHOBJECT.action ) ){
                    
                        
                        // make sure pk is 0 for auto asign
                        ProblemDomainObject.setField(ProblemDomainObject.OIDfield, 0 ) ;

                        // execute push
                        persistentObjectManager.push_back( transaction, log, ProblemDomainObject );

                                //  -----------------------------------------------        
                                // BUSINESS RULES PUSH OBJECT -------------------

                                         // keep invoice totals updated
                                        if( ProblemDomainObject instanceof sample_itemClass  ){
                                            
                                            RULEupdateInvoiceTotal( transaction, log, ((sample_itemClass)ProblemDomainObject).getFacId() );
                                        } 

                                // BUSINESS RULES PUSH OBJECT -------------------
                                //  -----------------------------------------------    
                                        
                        result =  ProblemDomainObject;       

                }else
                    
                //**************************************************************************************
                if( action.equals( ActionEnum.COPYOBJECT.action ) ){
                    
                     result = persistentObjectManager.copy( transaction, log, objClass, objID );

                }else
                    
                    
                //**************************************************************************************
                if( action.equals( ActionEnum.UPDATEOBJECT.action ) ){
                                        
                    
                    // update 
                    persistentObjectManager.update(  transaction, log, ProblemDomainObject  );     
                    
                                //  -----------------------------------------------        
                                // BUSINESS RULES update OBJECT -------------------

                                        // keep invoice totals updated
                                        if( ProblemDomainObject instanceof sample_itemClass  ){
                                            
                                            RULEupdateInvoiceTotal( transaction, log, ((sample_itemClass)ProblemDomainObject).getFacId() );

                                        } 


                                // BUSINESS RULES update OBJECT -------------------
                                //  -----------------------------------------------                         
                                       
                   result =  ProblemDomainObject;       
                                        
                }else  
                    
                    
                //**************************************************************************************
                if( action.equals( ActionEnum.DELETEOBJECT.action ) ){
                    
                    
                                //  -----------------------------------------------        
                                // BUSINESS RULES update OBJECT -------------------

                                        // keep invoice totals updated
                                        if( objClass.equals( sample_itemClass.class.getName() ) ){

                                            // materialize, to see what invoice we are talking about
                                            sample_itemClass itemToDelete = (sample_itemClass) persistentObjectManager.copy( transaction, log, sample_itemClass.class.getName(), objID );
                                            
                                            // do delete
                                            persistentObjectManager.delete( transaction, log, objClass, objID );                                            
                                            
                                            // update
                                            RULEupdateInvoiceTotal( transaction, log, itemToDelete.getFacId() );
                                            
                                // BUSINESS RULES update OBJECT -------------------
                                //  -----------------------------------------------        
    
  
                                        }else{
                                        
                                            persistentObjectManager.delete( transaction, log, objClass, objID );                                            
                                            
                                        }
                                                                                                       
                }else
                    
                    
                //**************************************************************************************
                if( action.equals( ActionEnum.EXECUTEFUNCTION.action ) ){
                    
                    
                        // materialize incomming object

                        String message = "Executing function for object: [" + ProblemDomainObject.getClass().getName() + "] ";
                        log.log( this, message ); 


                            //  -----------------------------------------------        
                            // BUSINESS RULES update OBJECT -------------------


// pend reimplement

                            
                            // BUSINESS RULES update OBJECT -------------------
                            //  -----------------------------------------------                            

                }else
                
                    //**************************************************************************************
                {
                    
                    throw new KExceptionClass( "Could not dispatch action, invalid action code " + action, null );
                }
                
                result = transaction.detach( result );

                transaction.commitCurrentTransaction();
                
        }catch( Exception error ){
        
            log.log( this, "Could not dispatch request " + KMetaUtilsClass.getStackTrace(error) );
            throw new KExceptionClass( "Could not dispatch request " + error, error );
            
        }finally{
            
            transaction.close();
            
            log.log( this, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Processing request -- " + action + " Done! " );                            
        }
        
        return( result );
    
    }
    

    // -------------------------------------------------------------------------------------                

    @Override
    public ResultSetClass processSQLRequest(            
            SQLMessageClass ReceivedCmd, KSessionClass session, KLogClass log) throws KExceptionClass {

            log.log( this, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Processing SQL request --> " );                                    
        
            ResultSetClass result = null;
            
            // in this sample one transaction to one db, you might have more than one to many dbs...
            KTransactionClass transaction = new KTransactionClass(  
                    configuration, log, session.getID(), configuration.getField( "db_pu" ) );
                        
            try{
            
                // default implementation, you might do your own....
                result = persistentObjectManager.processSQLRequest( ReceivedCmd, transaction, log );
            
            }catch( Exception error ){

                log.log( this, "Could not dispatch request " + KMetaUtilsClass.getStackTrace(error) );
                throw new KExceptionClass( "Could not dispatch request " + error, error );

            }finally{

                transaction.close();

                log.log( this, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Processing SQL request --  Done! " );                            
            }            
            
            return result;
            
    }
    
       
    // -------------------------------------------------------------------------------------    
    
    public KBusinessObjectClass buildBusinessObject( String objClass, KLogClass log, KTransactionClass transaction ) throws KExceptionClass {
        
        KBusinessObjectClass obj = null;
        try{
        
            log.log( this, "Create new " + objClass );                     
            

            if( objClass.equals( systemUserClass.class.getName() ) ) {

                systemUserClass systemUser = new systemUserClass();            
                systemUser.setField( "system_user_id" , 0 );
                obj = systemUser;


            } else if(objClass.equals( systemMailClass.class.getName() ) ) {

                systemMailClass mail = new systemMailClass();            
                mail.setField( "mail_id" , 0 );
                mail.setField( "mail_status" , systemMailClass.STATUS_NEW );            
                SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = new Date();            
                mail.setField( "mail_date" , dateFmt.format(fecha) );            
                obj = mail;


            } else if( objClass.equals( sample_facturaClass.class.getName() ) ) {

                sample_facturaClass factura = new sample_facturaClass();
                factura.setFacstatusId( persistentObjectManager.getDefaultId( transaction, log, "FACSTATUS_ID", "SAMPLE_FACTURA_STATUS"  ) );          
                obj = factura;

            }         
            else{

                try {

                    // not found, default it
                    obj = (KBusinessObjectClass) ( Class.forName( objClass ).newInstance() );

                } catch (Exception ex) {

                    throw new KExceptionClass("Could not dynamically build new object in server", ex);
                }


            }

        }catch( Exception error){
            
               throw new KExceptionClass("Could not build " + objClass  + " object ", error );
        }
        
        return obj;
        
    }  
                    

                    
    // --------------------------------------------------------------
    @Override       
    public void runPeriodicBatchProcessing( KLogClass log ) throws KExceptionClass{

        log.log( this, "User BATCH Processing starting ... >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "   );


        try{ 
                // in this sample one transaction to one db, you might have more than one to many dbs...
                KTransactionClass transaction = new KTransactionClass( 
                        configuration, log, "SERVER_BATCH_DAEMON", configuration.getField( "db_pu" ) );
                
                try{

                    // -------------------------------------------------------------------
                    // SAMPLE SOME BATCH PROCESS ACCESSING DB
            
                    try{

                        systemUserClass  user = new systemUserClass();
                        user = (systemUserClass) persistentObjectManager.copy( transaction, log, systemUserClass.class.getName(), 1 );
                        log.log( this, user.toFullString() );

                    }catch( KPOMObjectNotFoundException SampleUserNotFound ){
                        // just an example ...
                    }


                    // SAMPLE SOME BATCH PROCESS ACCESSING DB
                    // -------------------------------------------------------------------                    
                    

                    transaction.commitCurrentTransaction(); 
                    
                    log.log( this, "User BATCH Processing done! >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "   );


                }catch( Exception error ){
                        
                    transaction.rollbackCurrentTransaction();   
                    throw error;

                }finally{

                    transaction.close();
                }

        
        }catch( Exception error){
            
               throw new KExceptionClass( "Could not run batch processing -->", error );
        }

    }

                    
    // -------------------------------------------------------------------------------------            
    @Override  
    public void runCascadeDeletionBusinessLogic(                    
            KBusinessObjectClass  deletee, String OIDname, long OID,
            KBusinessObjectClass child,  KLogClass log
            ) throws integrityExceptionClass, KExceptionClass{


        try{

            if( child.getClass() == sample_itemClass.class   ){

                throw new integrityExceptionClass(
                        " No allowed to erase " + deletee.getClass().getName() + " if " + child.getClass().getName() +
                        " persent "
                        );
            }                   


        }catch( Exception error ){
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );                    
            if( error.getClass() == integrityExceptionClass.class ) throw (integrityExceptionClass) error;
            throw new KExceptionClass(
                    "Can not delete " + deletee.getClass().getName() + ", error checking dependant objects.  " +
                    child.getClass().getName() + " \n" + error.toString() , null 
                    );
        }



    }


        // ========================================================================
        // Sample login Implementation

        public class accessToApplicationDeniedExceptionClass
        extends KExceptionClass{
            public accessToApplicationDeniedExceptionClass(){
                super( "Invalid credentials, Access denied.", null );
            }
        }
        
        @Override
        public  KBusinessObjectClass authenticateUser( systemUserClass userTryingToLogin, KSessionClass session, KLogClass log  ) 
        throws KExceptionClass
        {
            
            try{ 
                    // in this sample one transaction to one db, you might have more than one to many dbs...
                    KTransactionClass transaction = new KTransactionClass( 
                            configuration, log, "SERVER_LOGIN", configuration.getField( "db_pu" ) );


                try{                        
                        String user_name = userTryingToLogin.getFieldasString("system_user_name");                
                        log.log( this, " User [" + user_name + "] is logging in..." );

                        // ------------------------------------------------------------
                        //Try to find the user in DB ... you can do it any other way ...
                        EntityManager entityTransaction = transaction.getEntityManager();          
                        String SQL =  
                                " SELECT x FROM systemUserClass x WHERE " +
                                " x.system_user_name = ?1" +
                                " AND x.system_user_password = ?2";                      
                        Query q = entityTransaction.createQuery( SQL );                
                        q.setParameter(1, user_name);
                        q.setParameter(2, userTryingToLogin.getFieldasString("system_user_password"));
                        List< systemUserClass > results =  ( List< systemUserClass > ) q.getResultList();
                        // ------------------------------------------------------------                

                        if(results.isEmpty()){                    
                            // ****** FAIL LOGIN  ******                    
                            log.log( this, " User [" + user_name + "] is not authenticated!!!" );                                          
                            throw new accessToApplicationDeniedExceptionClass();                      
                        }

                        // ****** OK LOGIN  ******                
                        systemUserClass authenticatedUserInformation = results.get( 0 );                

                        // important
                        session.setSessionUniqueID( authenticatedUserInformation.getSystem_user_id() );

                        log.log( this, " User [" + user_name + "] [" + authenticatedUserInformation.getSystem_user_id() +  "]is authenticated!!!" ); 

                        return authenticatedUserInformation;  // returned to server, add here any info you want to be sent

                } 
                catch (Exception errorDuringLogin ) {

                    throw new KExceptionClass( "Could not login. " + errorDuringLogin.toString(), errorDuringLogin );
                    
                }finally{
                    
                    transaction.close();
                }
        
            }catch( Exception error){

                   throw new KExceptionClass( "Could not process login -->" + error, error );
            }                

        }    
        
        // Sample login Implementation
        // ========================================================================

        


// ========================================================================            
// BUSINESS LOGIC 

        public  void RULEupdateInvoiceTotal( KTransactionClass transaction, KLogClass log, long invoice_id  )
        throws KExceptionClass
        {


            try{

                log.log( this, "Updating invoice -> " +  invoice_id );

                // load invoice (locks by saving the version number, as it checks it on save )
                sample_facturaClass invoice = (sample_facturaClass) persistentObjectManager.copy( transaction, log, sample_facturaClass.class.getName(), invoice_id );

                // re-calc total                                        
                Query query = transaction.getEntityQuery( " SELECT SUM( item.itemQuantity * item.itemCost ) FROM sample_itemClass item WHERE item.facId = :invoice_id " );

                // set parameter
                query.setParameter( "invoice_id" , invoice_id );

                // execute!                    
                java.lang.Number returnObject = (java.lang.Number) query.getSingleResult();

                // get result
                Double result = 0.0;
                if( returnObject != null ){ result = returnObject.doubleValue(); }

                // Upadte invoice ..
                // update invoice, checks for verion id not changed during operation}
                // for optimistic locking...
                log.log( this, "New Invoice total:" + result );
                invoice.setFacTotal( result );
                persistentObjectManager.update( transaction, log, invoice );

            }catch( Exception error ){                

                // this will rollback the transaction automatically....
                throw new KExceptionClass("Error, could not update invoice " + invoice_id, error );                

            }

        }

// BUSINESS LOGIC         
// ========================================================================            

            
} // end POM


