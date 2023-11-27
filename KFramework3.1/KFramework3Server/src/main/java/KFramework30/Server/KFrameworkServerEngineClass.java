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


// ke
import KFramework30.Base.*;
import KFramework30.Base.KActiveObjectClass.KActiveObjectInterface;
import ProblemDomainComponent.systemUserClass;
import ProblemDomainComponent.systemMailClass;


// mail
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.smtp.*;

// rtl
import java.util.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.ResultSetMetaData;


public class KFrameworkServerEngineClass 
implements KActiveObjectInterface
{
    
         

	// uses
	private KConfigurationClass             configuration;
	private KLogClass			mainLog;
        private sessionContainerClass           sessionContainer;
        private KFrameworkServerInterface       userServer;
        
	// has - defaulted       
        private KActiveObjectClass              systemMonitor;
        private KXMLSerializerClass               serializer;    
               
        public enum StatusEnum { 

            OBJECTMANAGER_OK ("SUCCESS:OBJECT"),
            SQL_OK ("SUCCESS:SQL"),                
            SERVER_ERROR ("ERROR");     

            final String code;  

            StatusEnum (String code) {  
                this.code = code;  
            } 
        };    
        

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

            public final String action;  

            ActionEnum (String action) {  
                this.action = action;  
            }  
        };        
   
        // interfaces
        public interface KFrameworkServerInterface{

            public abstract void initialize( KLogClass log ) throws KExceptionClass;

            public abstract KBusinessObjectClass processObjectRequest(
                    String action, 
                    String objClass, 
                    KBusinessObjectClass incommingObject,
                    long objID,
                    KSessionClass session, 
                    KLogClass log  )
                    throws KExceptionClass;
            
            public abstract ResultSetClass processSQLRequest(
                    SQLMessageClass ReceivedCmd, KSessionClass session, KLogClass log  )
                    throws KExceptionClass;
                        
            public void runPeriodicBatchProcessing( KLogClass log ) throws KExceptionClass;
            
            public abstract KBusinessObjectClass authenticateUser( systemUserClass userTryingToLogin, KSessionClass session, KLogClass log  )
                    throws KExceptionClass;                   
                                    
        }        
        
      
   // ------------------------------------------------------------------------
   // Public Interface
   
  
   public  KFrameworkServerEngineClass(
	 KConfigurationClass configurationParam, 
         KLogClass mainLogParam, 
         sessionContainerClass sessionContainerParam, 
         KFrameworkServerInterface userServerParam
         )
         throws Exception
   {

        System.out.println( "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );       
        System.out.println( "+  KFrameWork Starting...                                +" );
        System.out.println( "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );           

        // uses
       
        configuration = configurationParam;
        mainLog = mainLogParam;
        sessionContainer = sessionContainerParam;
        userServer = userServerParam;
             

        // has 
        serializer = new KXMLSerializerClass( configurationParam, mainLogParam );
        
        try{


            //  monitor batch processor
            systemMonitor = new
                KActiveObjectClass( configuration, mainLog, 
                    Long.parseLong( configuration.getField( "clearer_delay_mins" ) ) * 1000 * 60, 1000 // sec sample
                    );            
            systemMonitor.addTask( this );
            systemMonitor.addTask( sessionContainer );
            systemMonitor.start();            
            systemMonitor.doResume();

            mainLog.log( this, "Initializing... complete." );

        }catch( KExceptionClass error ){

                    mainLog.log( this, KMetaUtilsClass.getStackTrace( error ) );

                    throw error;
        }

            
     }     
   
    
  
   public void destroy(){

        System.out.println( "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );       
        System.out.println( "+  KFrameWork Stopping...                                +" );
        System.out.println( "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );        
              
        systemMonitor.doKill();
        
        System.out.println( "Wait for monitor to stop..." );        
        int waitSecondsCount = 0;
        while( systemMonitor.getStatus() != KActiveObjectClass.statusEnum.KILLED ){            
                                    
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException ex) {
            }
            
            waitSecondsCount++;
            if( waitSecondsCount > 10 ) break; // something is wrong, better quit.
        }

        System.out.println( "Wait for monitor to stop... Done, now close sessions ..." );        
        
        // check sessions .....
        try{                       
                sessionContainer.removeAllSessions( );

        }catch( KExceptionClass error ){

                mainLog.log( this, error.toString() );
        }
                
        System.out.println( "Shutdown finished >>>>>>>>>>>>>>>>>>>>>>>>" );                

    }

              
    public ObjectMessageClass ObjectRequest( 
                ObjectMessageClass messageReceived,            
                KLogClass log
                ) {
                
                try {    
                                                                             
                    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                    
                    // READ INCOMMING MESSAGE
                    
                        // not null ?
                        if(messageReceived == null || messageReceived.equals( "" ) ) throw new KExceptionClass( "Could not process client message, invalid message format",null );                      
                        
                        // check dialect
                        if( !messageReceived.protocolversion.equals( KMetaUtilsClass.TRANSPORT_DIALECT_VERSION ) ){
                                throw new KExceptionClass( 
                                        "Could not process request. Dialect requested " + messageReceived.protocolversion + " is not supported. " +
                                        "Supported dialect is " + KMetaUtilsClass.TRANSPORT_DIALECT_VERSION ,null );                                                  
                        }
                    
                        
// materialize                        
                    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                        
                                                            
                       KBusinessObjectClass incommingObject = null;                                                
                        

                    if( messageReceived.XMLSerialization != null ){    
                        incommingObject = (KBusinessObjectClass) serializer.materializeObject( messageReceived.XMLSerialization, log );    
                    }

                    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    // SESSION FIGURE                
                        
                        log.log( this, "***** Web service dispatching client request --> [" + messageReceived.action + "->" + messageReceived.objClass + "]"   );
                        
                        KSessionClass session;                        
                        KBusinessObjectClass resultObject = null;        
                                                                        
                        // LOGIN ->
                        if( messageReceived.action.equals(ActionEnum.LOGIN.action) ) {
                              
                            // materialize request
                            if( !messageReceived.objClass.equals( systemUserClass.class.getName() ))
                                throw new KExceptionClass( "*** Could not log in to the system ***\n" + "Invalid object type received for login" , null );

                                
                            systemUserClass userTryingToLogin = (systemUserClass) incommingObject;
                           
                            
                            // start unlogged session
                            session = new KSessionClass( configuration, sessionContainer.log );  
                                
                            // login session
                            resultObject = userServer.authenticateUser( (systemUserClass) userTryingToLogin, session, log );                                 
                                            
                            // if all OK add it to session tracking
                            sessionContainer.addSession( session );

                        } 
                        // LOAD SESSION ->
                        else{ 
                                                           
                            // grab session                            
                            log.log( this, "Check  session... " + messageReceived.session_id + ">>>>>>>>" );                                                        
                            session = sessionContainer.getSession( messageReceived.session_id );                                                                                                                                                                    

                        }
                            
                            // done session ...
                            
                    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                            
                    // EXECUTE COMMAND
                        
                             
                        if( messageReceived.action.equals(ActionEnum.LOGIN.action) ) {

                             // let go..
                        }                                
                        else{
                            
                             resultObject = userServer.processObjectRequest( 
                                     messageReceived.action, messageReceived.objClass, incommingObject,  messageReceived.objID, session, log  );
                                                         
                        }


                    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                            
                    // EXECUTE RETURN RESPONSE
                                                
                        
                        log.log( this, "Assembling response..." );                    

                        // get a transport
                        ObjectMessageClass returnMsg = new ObjectMessageClass();
                        List<FieldClass> returnAttrList = new ArrayList<FieldClass>();
                                                               

                        

                       // assemble response ....
                       returnMsg.status = StatusEnum.OBJECTMANAGER_OK.code;
                       returnMsg.desc = "Service done!";
                       if( resultObject != null ){
                            returnMsg.objClass = resultObject.getClass().getName();
                            returnMsg.objID = resultObject.getOID();
                       }
                       returnMsg.action = messageReceived.action;                      
                       returnMsg.session_id = session.getID();   
                       
                       //serial                       
                       returnMsg.XMLSerialization = serializer.serializeObject( resultObject, log );
                                                          
                       log.log( this, "Servicing Object request!! ..done! +++++++++++++++++++++++++++++++" );                                   
                                                                   
                       mainLog.addLog( log );
                       
                       return returnMsg;
                       
                   // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                                   
                   
            } catch (Exception ex) {
                
                             
               String message = "";
               if( ex instanceof KExceptionClass ){                   
                   
                log.log( this, ( (KExceptionClass) ex ).getLongMessage() );                                                   
                message = ( (KExceptionClass) ex ).getMessage();
                
               }else{                   
                   
                log.log( this, KMetaUtilsClass.getStackTrace( ex ) );
                message = ex.toString();
               }
                       
               // inicialize return message
               ObjectMessageClass returnMsg = new ObjectMessageClass();
               returnMsg = new ObjectMessageClass();              
               returnMsg.status = StatusEnum.SERVER_ERROR.code;
               returnMsg.desc = message;
               returnMsg.XMLSerialization = null;
                             
               
               mainLog.addLog( log );
               return returnMsg;
               
            }               

         
        
    }
    
    
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @WebMethod(operationName = "SQLRequest")
    public ResultSetClass SQLRequest(@WebParam(name = "ReceivedCmd")
    SQLMessageClass ReceivedCmd, KLogClass log) {                
               
        
        try {   
            
            log.log( this, "Servicing SQL request!! ++++++++++++++++++++++++++++++++++++" );    

            if( ReceivedCmd == null || ReceivedCmd.equals( "" ) ) throw new KExceptionClass( "Could not process client message, invalid message format",null );                                      

            KSessionClass session = sessionContainer.getSession( ReceivedCmd.session_id );

            ResultSetClass result =  userServer.processSQLRequest( ReceivedCmd, session, log );   
                                
            mainLog.addLog(log); 
            
            return result;
                                
         }catch (Exception ex) {    
             
               String message = "";
               if( ex instanceof KExceptionClass ){                   
                   
                log.log( this, ( (KExceptionClass) ex ).getLongMessage() );                                                   
                message = ( (KExceptionClass) ex ).getMessage();
                
               }else{                   
                   
                log.log( this, KMetaUtilsClass.getStackTrace( ex ) );
                message = ex.toString();
               }        
             
            ResultSetClass result = new ResultSetClass();             
            result.status = StatusEnum.SERVER_ERROR.code;
            result.desc = message;
            result.lengh = 0;
            
            mainLog.addLog(log);   
            
            return result;
         }                                
              
    }

    public void run() {
                
     
        try{   
                                 
                // create a local log                 
                KLogClass log =  new KLogClass( configuration );                                               
                log.setLogMode( KLogClass.off );      
                log.setMaxLogLines( Integer.parseInt( configuration.getField( "sublog_maxloglines" ) ) );


                log.log( this, "MONITOR Starting..."   );

                KSessionClass anonymousSession = new KSessionClass( configuration, log );                
                
                KTransactionClass transaction1 = new KTransactionClass(configuration, log, anonymousSession.getID(), configuration.getField( "db_pu" ) );
                                
                
              // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                            
              // EXECUTE COMMAND

                try{                                

                    log.log( this, "MONITOR Running... "   );
                    
                  
                // ----------------------------------------------------------------------------------------------
                // LOG TRIM ->   
                    {

                        log.log( this, "DB Log trimmer ----------------------- "   );

                        // grab pending mails
                        KDBQueryClass query = new KDBQueryClass( configuration, log, transaction1 );
                        query.prepare( " delete from systemLogClass where log_id < ( select max( log_id ) - " + configuration.getField( "maxDbLogLines" )  +" from systemLogClass ) " );
                        query.executeModify();                                

                        transaction1.commitCurrentTransaction();

                        query.close();

                        log.log( this, "DB Log trimmer ----------------------- "   );                                

                    } // trimmer


                    
                // ----------------------------------------------------------------------------------------------                    
                // MAILER ->
                    if( configuration.getField( "mail_enabled" ).equals( "true" ) ){

                        log.log( this, "Mailer ----------------------- "   );                                

                        // grab pending mails
                        KDBQueryClass query = new KDBQueryClass( configuration, log,  transaction1 );
                        query.prepare( " select mail_id from systemmailClass where mail_status = '" + systemMailClass.STATUS_NEW + "' order by mail_id " );

                        query.executeQuery();

                        while( query.fetch() ){

                            log.log( this, ">>>Sending Mail ..." + query.getField( "mail_id" ) );

                            // materializeObject
                            systemMailClass mail = transaction1.getEntityManager().find( systemMailClass.class, KMetaUtilsClass.getIntegralNumericValueFromString( query.getField( "mail_id" ) ) );


                            // send
                            try{

                                // configure session

                                    Properties props = new Properties();

                                    props.put("mail.smtp.host", configuration.getField( "mail_server_address" ) );

                                // for GMAIL

                                    // TLS si est disponible
                                    // props.put("mail.smtp.starttls.enable", "true");

                                    // Puerto de gmail para envio de correos
                                    // props.setProperty("mail.smtp.port","587");

                                    props.put("mail.smtp.auth", configuration.getField( "mail.smtp.auth" ) );

                                // get a session

                                    Session session = Session.getInstance( props, null );
                                    session.setDebug( false );

                                // make a message object

                                    MimeMessage mailMessage = new MimeMessage( session );

                                    mailMessage.setFrom(new InternetAddress( mail.getMail_from() ) );

                                    // set recipients
                                    StringTokenizer recipients = new StringTokenizer( mail.getMail_recipient(), "," , false );                                                                                        
                                    int totalRecipients = recipients.countTokens();                                            
                                    InternetAddress[] address = new InternetAddress[ totalRecipients ];                                            
                                    for( int index = 0; index < totalRecipients; index++ ){
                                        String nextRecipient = recipients.nextToken().trim();
                                        address[ index ] = new InternetAddress( nextRecipient );
                                        log.log( this, "Setting recipient [" + nextRecipient + "]" );                                                
                                    }


                                    mailMessage.setRecipients( Message.RecipientType.TO, address );    

                                    mailMessage.setSubject( mail.getMail_subject() );
                                    mailMessage.setSentDate( new Date() );

                                    // create and fill the first message part
                                    MimeBodyPart mailPart1 = new MimeBodyPart();
                                    mailPart1.setContent( mail.getMail_data(), mail.getMail_data_type() );

                                    // create the Multipart and its parts to it
                                    Multipart messageMultipartContainer = new MimeMultipart();
                                    messageMultipartContainer.addBodyPart( mailPart1 );

                                    // add the Multipart to the message
                                    mailMessage.setContent( messageMultipartContainer );

                                // connect

                                    SMTPTransport transport = (SMTPTransport) session.getTransport( "smtp" );

                                // send

                                    if( configuration.getField( "mail.smtp.auth" ).equals( "true" ) ){

                                        transport.connect(
                                                configuration.getField( "mail_server_address" ) , 
                                                configuration.getField( "mail_server_user" ), 
                                                configuration.getField( "mail_server_password" )
                                                );

                                    }else{

                                        transport.connect();                                                
                                    }

                                    transport.sendMessage( mailMessage, mailMessage.getAllRecipients() );                                                                                

                                    log.log( this, "Sending Mail ... done!" );   

                                    // update status                                
                                    mail.setMail_status( systemMailClass.STATUS_SENT );                                        

                            // send catch
                            }catch( Exception error ){

                                // update status                                
                                mail.setMail_status( systemMailClass.STATUS_ERROR );
                                mail.setMail_status_description( error.toString() );                                    
                                log.log( this, KMetaUtilsClass.getStackTrace( error ) );

                            }


                        } // loop

                        query.close();

                        log.log( this, "Mailer ----------------------- "   );                                                                               

                    }else{

                        log.log( this, "Mailer ---- not enabled!! "   );                                        

                    }// mailer

                    
                    // TRX ================================ 
                    transaction1.commitCurrentTransaction();
                    // TRX ================================  
                    

                    // ----------------------------------------------------------------------------------------------
                    // User stuff ->                        
       
                    userServer.runPeriodicBatchProcessing( log );
                    
                    // ----------------------------------------------------------------------------------------------    
                    // Done!!!                    
                    
                   
                // moni catch
                }catch( Exception error ){

                    try{

                        log.log( this, "TX ROLLBACK <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" );                        

                        // TRX ================================   
                        transaction1.rollbackCurrentTransaction();
                        // TRX ================================  

                    }catch( Exception error2 ){

                            String message = " *** Error ***  " + error.toString() + "\n" +
                                             " *** Error2 ***  " + error2.toString() + "\n";

                            throw new KExceptionClass( message, error );

                    }

                    throw error;

                }finally{

                    // SHUTDOWN

                    log.log( this, "MONITOR Shutting down ... "   );

                    // TRX ================================   
                    transaction1.close();
                    // TRX ================================   
                    
                    log.log( this, "MONITOR Shutting down ... done!"   );    
                    
                    // paint log
                    mainLog.addLog( log );

                }


        // init catch
        } catch ( Exception ex ) {

           String message = "*** GRAVE Monitor Error **** \n"  + KMetaUtilsClass.getStackTrace(ex);           
           mainLog.log( this, message);

        }   


    }
        
    
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    @Override
    public void afterPaused() throws KExceptionClass {
    }
    
}
