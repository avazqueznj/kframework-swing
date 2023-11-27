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

import KFramework30.Server.ObjectMessageClass;
import KFramework30.Server.SQLMessageClass;
import KFramework30.Server.ResultSetClass;
import KFramework30.Base.*;
import KFramework30.Server.*;

import java.net.URL;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService( name="KFrameworkService01" , portName="KFrameworkPort01" )
public class KFrameworkServerClass 
{

    @Resource 
    private WebServiceContext context;    
    
    // ----------------------------------------------------------------------------------
    
    @WebMethod(operationName = "ObjectRequest")
    public ObjectMessageClass ObjectRequest(
		@WebParam(name = "messageReceived") 
		ObjectMessageClass messageReceived) {   
        
        // restore context
        ServletContext servletContext =    (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        KConfigurationClass configuration = (KConfigurationClass) servletContext.getAttribute( "KConfigurationClass" );
        KFrameworkServerEngineClass KServer = (KFrameworkServerEngineClass) servletContext.getAttribute( "KFrameworkServerEngineClass" );        
   
        ObjectMessageClass result = null;
        
        try{
            
         // create a local log just for this action
         KLogClass log =  new KLogClass( configuration );     // temporary log that will be added to main log later to avoid log messages mixing up                                           
         log.setLogMode( KLogClass.off );
         log.setLogCycleStartTime("");
         log.setMaxLogLines( Integer.parseInt( configuration.getField( "sublog_maxloglines" ) ) );
         log.log( this, "Servicing  request!! ++++++++++++++++++++++++++++++++++++\n\n" );                                 
         
         result = KServer.ObjectRequest( messageReceived, log );
         
       }catch( Exception error ){
                      
           System.out.println( 
                   "*** Server Objecy Execute ERROR ***" +
                   KMetaUtilsClass.getStackTrace( error )
                   );
           
       } 
        
        return( result );
        
    }
    
    
    // ----------------------------------------------------------------------------------
    
    
    @WebMethod(operationName = "SQLRequest")
    public ResultSetClass SQLRequest(@WebParam(name = "ReceivedCmd")
    SQLMessageClass ReceivedCmd) {                
              
        // restore context
        ServletContext servletContext =    (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
        KConfigurationClass configuration = (KConfigurationClass) servletContext.getAttribute( "KConfigurationClass" );
        KFrameworkServerEngineClass KServer = (KFrameworkServerEngineClass) servletContext.getAttribute( "KFrameworkServerEngineClass" );        

        
        ResultSetClass result = null;
        
        try{
            
         // create a local log just for this action
         KLogClass log =  new KLogClass( configuration );     // temporary log that will be added to main log later to avoid log messages mixing up                                           
         log.setLogMode( KLogClass.off );
         log.setLogCycleStartTime("");
         log.setMaxLogLines( Integer.parseInt( configuration.getField( "sublog_maxloglines" ) ) );
         log.log( this, "Servicing  request!! ++++++++++++++++++++++++++++++++++++\n\n" );                
                               
         
         result = KServer.SQLRequest(ReceivedCmd, log);
         
       }catch( Exception error ){
                      
           System.out.println( 
                   "*** Server Objecy Execute ERROR ***" +
                   KMetaUtilsClass.getStackTrace( error )
                   );
           
       } 
        
        return( result );        
               
        
    }
                
    // ----------------------------------------------------------------------------------
    
}
