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

package DataManagementComponent;

// KFramework
import KFramework30.Base.*;
import KFramework30.Server.*;
import KFramework30.Server.KFrameworkServerEngineClass.KFrameworkServerInterface;

// JAVAX
import java.net.URL;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;




public class KFrameworkInitiatorListener 
        implements ServletContextListener, 
        ServletContextAttributeListener, 
        HttpSessionListener, 
        HttpSessionAttributeListener, 
        ServletRequestListener, 
        ServletRequestAttributeListener       
{
    
    
    // uses
    
    
    // has
    private KConfigurationClass                 configuration;
    private KLogClass                           mainLog;
    private sessionContainerClass               sessionContainer;
    private KFrameworkServerEngineClass         KServer;


    // -------------------------------------------------------------------------
    
    @Override
    public void contextInitialized(ServletContextEvent contextInitializedEvent) {
       
            System.out.println( "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );       
            System.out.println( "+  KFrameworkInitiatorListener Initializing                      +" );
            System.out.println( "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" );  
                           
            try{


                // load config
                URL magnusURL = this.getClass().getClassLoader().getResource("/config/magnus.conf");
                System.out.println( magnusURL.toString() );  

                configuration = new KConfigurationClass();
                configuration.loadFromNetwork( magnusURL );                                                      
                configuration.parse();   
                
                // make sure DB stuff is OK ->
                Locale.setDefault(  new Locale( configuration.getField("locale_lang"), configuration.getField("local_region") ) );                

                // create a log
                mainLog =  new KLogClass( configuration );                 

                // get a session container
                sessionContainer = new sessionContainerClass( configuration, mainLog );              

                // user logic starts
                KFrameworkServerInterface userBusinessLogicComponent = new userBusinessLogicComponentClass( configuration );                
                userBusinessLogicComponent.initialize( mainLog );
                
                // build framework engine
                KServer = new KFrameworkServerEngineClass( configuration, mainLog, sessionContainer, userBusinessLogicComponent );
                
            // -----------
                
                ServletContext context = contextInitializedEvent.getServletContext();                
                context.setAttribute( "KConfigurationClass", configuration);
                context.setAttribute( "KFrameworkServerEngineClass", KServer);                

           }catch( Exception error ){

               System.out.println( 
                       "*** Server Initialization ERROR ***" +
                       KMetaUtilsClass.getStackTrace( error )
                       );

           }

                       
            System.out.println( "KFrameworkInitiatorListener Initializing Done ..  +++++++++++++++" );             
            
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextDestroyedEvent ) {
        
        System.out.println( "KFrameworkInitiatorListener Shutting down  ..  +++++++++++++++" );             
        
        KServer.destroy();        
        
        System.out.println( "KFrameworkInitiatorListener Shutdown Done ..  +++++++++++++++" );                     
    }


        
    //----------------------------------------------------------------------------------------
  @Override
    public void attributeAdded(ServletContextAttributeEvent arg0) {
       
    }
 @Override
    public void attributeRemoved(ServletContextAttributeEvent arg0) {
       
    }
 @Override
    public void attributeReplaced(ServletContextAttributeEvent arg0) {
       
    }
 @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        
    }
 @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
       
    }
 @Override
    public void attributeAdded(HttpSessionBindingEvent arg0) {
       
    }
 @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        
    }
 @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        
    }
 @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
       
    }
 @Override
    public void requestInitialized(ServletRequestEvent arg0) {
       
    }
 @Override
    public void attributeAdded(ServletRequestAttributeEvent arg0) {
       
    }
 @Override
    public void attributeRemoved(ServletRequestAttributeEvent arg0) {
       
    }
 @Override
    public void attributeReplaced(ServletRequestAttributeEvent arg0) {
       
    }
    
    
  
}
