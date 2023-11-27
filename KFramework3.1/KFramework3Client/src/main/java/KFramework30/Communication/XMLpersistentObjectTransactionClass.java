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

/**
 *  This class handles object transactions to the remote database server and back.
 *
 */

package KFramework30.Communication;

// utilities
import KFramework30.Base.*;
import ProblemDomainComponent.systemUserClass;
import datamanagementcomponent.FieldClass;
import datamanagementcomponent.KFrameworkServerClassService;
import datamanagementcomponent.KFrameworkService01;
import datamanagementcomponent.ObjectMessageClass;
import java.net.URL;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import javax.xml.namespace.QName;


public class XMLpersistentObjectTransactionClass {
        
    
    private static final int            MINIMUM_ATTRIBUTES = 1;
       
    // uses
    private KConfigurationClass         configuration;
    private KLogClass                   log;
        
    // has - defaulted    
    private final clusterManagerClass   clusterManager;            
    private KBusinessObjectClass        returnedObject;
    private KXMLSerializerClass          serializer;    
                              
    //----------------------------------------------------------------------
    // Inner Classes

   
          // setvice request types
           public enum ActionEnum {
               
                LOGIN("LOGIN"),
                NEWOBJECT("NEWOBJECT"),  
                PUSHOBJECT("PUSHOBJECT"), 
                UPDATEOBJECT("UPDATEOBJECT") ,
                COPYOBJECT("COPYOBJECT"),  
                DELETEOBJECT("DELETEOBJECT"),  
                TOPOBJECT("TOPOBJECT") ,
                BOTTOMOBJECT("BOTTOMOBJECT"), 
                MESSAGE("MESSAGE"),
                EXECUTEFUNCTION("EXECUTEFUNCTION"); 

                final String action;  

                ActionEnum (String action) {  
                    this.action = action;  
                }  
            };

            // setvice status
            public enum StatusEnum { 
                
            OBJECTMANAGER_OK ("SUCCESS:OBJECT"),
            SQL_OK ("SUCCESS:SQL"),                
            SERVER_ERROR ("ERROR");     
   

                final String code;  

                StatusEnum (String code) {  
                    this.code = code;  
                } 
           };

    //----------------------------------------------------------------------
    // PUBLIC INTERFACE


            
            
    /** Creates new persistentObjectTransactionClass */
    public XMLpersistentObjectTransactionClass(       
            KConfigurationClass configurationParam, KLogClass logParam
    )
    throws KExceptionClass
    {
        // inherits
        super();

        // uses
        configuration = configurationParam;
        log = logParam;
             
        // has - defaulted 
        clusterManager = new clusterManagerClass( configurationParam, logParam );        
        serializer = new KXMLSerializerClass( configurationParam, logParam );
        
        log.log( this, "Constructed successfully." );
    }

            
            
            
    //----------------------------------------------------------------------
    // PRIVATE INTERFACE
    
    
            
    /** Execute the command, return results */
    private ObjectMessageClass sendRequest( long OID, ActionEnum requestType, String objectClass, KBusinessObjectClass requestObject )
    throws KExceptionClass
    {        
        
        
        String message = "Executing object transaction[" + requestType +
        "] for [" + objectClass + "]";
        log.log( this, message );

        ObjectMessageClass result = null;
        
        try {
            
            // create service request message
            ObjectMessageClass ObjectMessage = new ObjectMessageClass();       

            // bind parameters, header part
            ObjectMessage.setProtocolversion( KMetaUtilsClass.TRANSPORT_DIALECT_VERSION );
            ObjectMessage.setObjClass(objectClass);
            ObjectMessage.setObjID(OID);
            ObjectMessage.setAction(requestType.action);
            ObjectMessage.setSessionId( configuration.getField( "SESSION_ID" ) );
            
            //bind parameters, attribute part    
            switch (requestType) {
                
                case PUSHOBJECT: 					
                case UPDATEOBJECT: 
                case LOGIN:
                case EXECUTEFUNCTION:
                
                    ObjectMessage.setXMLSerialization( serializer.serializeObject( requestObject, log ) );
                    
                break;
            }
            
                                        
           while( true ){

               try{


                    result = clusterManager.getNextServerPort().objectRequest( ObjectMessage  );
                    break;

               }catch( Exception error ){

                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );                            
                    clusterManager.reportCurrentServerDead();

               }
           }
           

           
        } catch (Exception ex) {
            
           // TODO handle custom exceptions here
           message = "*** Error, could not send message to server.\n  ***" +
                     ex.toString();
           
           log.log( this, message );
           
           throw new KExceptionClass( message, ex );
        }

        log.log( this, ">>> remote persistent object transaction executed successfully!!" );
        return result;        
    }

    //----------------------------------------------------------------------

    /** get replied data and write them into properties. */
    private long  getReply(ObjectMessageClass returnMsg, long sentOID, ActionEnum action )
    throws KExceptionClass
    {   
        
        try {
            
            //check service returned status        
           if( !returnMsg.getStatus().equals( StatusEnum.OBJECTMANAGER_OK.code ) ){
               
                    throw new KExceptionClass( returnMsg.getDesc() , null  );
            } 
           
            returnedObject = (KBusinessObjectClass) serializer.materializeObject( returnMsg.getXMLSerialization(), log );
            
        } catch (Exception ex) {
            
           // TODO handle custom exceptions here
           String message = "*** Server has replied with error. ***";
           log.log( this, message );
           throw new KExceptionClass( message, ex );
        }            
            

        return returnMsg.getObjID();
    }    
    
    
    
    //----------------------------------------------------------------------    

     /**     User try to login     */
    public String login( systemUserClass systemUser )
    throws KExceptionClass
    {	
    
         // send req to server
        ObjectMessageClass returnMsg = sendRequest( -1, ActionEnum.LOGIN, systemUser.getClass().getName(), systemUser ); 
        
        if( !returnMsg.getStatus().equals( StatusEnum.OBJECTMANAGER_OK.code ) ){
            
            throw new KExceptionClass(
                    "Server Message: " +
                    returnMsg.getDesc() , null 
                    );
        }
        
        String session_id = returnMsg.getSessionId();        
        getReply( returnMsg, -1, ActionEnum.LOGIN ); 
        
        return session_id;
    }

    
    /**     Save new object     */
    public long push_back( KBusinessObjectClass KBusinessObject )
    throws KExceptionClass
    {	

         // send req to server
        ObjectMessageClass returnMsg = sendRequest( -1, ActionEnum.PUSHOBJECT, KBusinessObject.getClass().getName(),KBusinessObject );    

        // prepare reply data
        return getReply( returnMsg, -1, ActionEnum.PUSHOBJECT );     
    }
    

    //----------------------------------------------------------------------    

    /** Creating a new object and getting the default values back */
    public void createNew( KBusinessObjectClass KBusinessObject )
    throws KExceptionClass
    {
        
        // send req to server
        ObjectMessageClass returnMsg = sendRequest( -1, ActionEnum.NEWOBJECT, KBusinessObject.getClass().getName(), KBusinessObject );  

        // prepare for replied data
        getReply( returnMsg, -1, ActionEnum.NEWOBJECT );     
    }

    
 
    //----------------------------------------------------------------------

    /**     Modify the object     */
    public void update( long OID, KBusinessObjectClass KBusinessObject )
    throws KExceptionClass
    {	

        // send req to server
        ObjectMessageClass returnMsg = sendRequest( OID, ActionEnum.UPDATEOBJECT, KBusinessObject.getClass().getCanonicalName(), KBusinessObject );   

        // prepare reply data
        getReply( returnMsg, OID, ActionEnum.UPDATEOBJECT );
    }

    //----------------------------------------------------------------------

    /**     Delete the object     */
    public void delete( long OID, String objectClassParam )
    throws KExceptionClass
    {	
         // send req to server
        ObjectMessageClass returnMsg = sendRequest( OID, ActionEnum.DELETEOBJECT, objectClassParam, null );    

        // prepare reply data
        getReply( returnMsg, OID, ActionEnum.DELETEOBJECT );
    } 
    
    //----------------------------------------------------------------------    

    /**     Copy the object     */
    public void copy( long OID, String objectClassParam )
    throws KExceptionClass
    {	

        // send req to server
        ObjectMessageClass returnMsg = sendRequest( OID, ActionEnum.COPYOBJECT, objectClassParam, null );    

        // prepare reply data
        getReply( returnMsg, OID, ActionEnum.COPYOBJECT );
    }
    
    public void execute( KBusinessObjectClass KBusinessObject )
    throws KExceptionClass
    {	

        // send req to server
        ObjectMessageClass returnMsg = sendRequest( -1, ActionEnum.EXECUTEFUNCTION, KBusinessObject.getClass().getCanonicalName(), KBusinessObject );      

        // prepare reply data
        getReply( returnMsg, -1, ActionEnum.EXECUTEFUNCTION );
    }



    //----------------------------------------------------------------------            
 
    public KBusinessObjectClass getReturnedObject() {
        return returnedObject;
    }    
    
}