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

import KFramework30.Base.*;
import datamanagementcomponent.KFrameworkServerClassService;
import datamanagementcomponent.KFrameworkService01;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.xml.namespace.QName;

public class clusterManagerClass {
    
    // uses
    private KConfigurationClass configuration;
    private KLogClass log;
    
    // has
    static private boolean full_reset_mode = false;
    
    static private int currentUrlIndex = 0;
    static private int nextUrlIndex = 0;
    
    static private Vector< String > urlList;
    static private Map< String, KFrameworkService01 > serverPortList;

    //-----------------------------------------------------------------------    
    
    public clusterManagerClass( KConfigurationClass configurationParam, KLogClass logParam ) throws KExceptionClass {
        
        // uses
        configuration = configurationParam;
        log = logParam;
        
        // has
        if( urlList == null ){
            serverPortList = new HashMap< String, KFrameworkService01 >(  );
            loadUrls();
        }        
        
    }
   
    //-----------------------------------------------------------------------
    
    public void loadUrls() throws KExceptionClass{
       
            urlList = new Vector< String >();
            nextUrlIndex = 0;
            serverPortList = new HashMap< String, KFrameworkService01 >(  );

            int serverCount = 0;
            while(true){                    
                try{
                    
                    String nextServer = configuration.getField( "server_address" + serverCount ) ;
                    log.log( this, "Registering server :" + nextServer );
                    urlList.add( nextServer );
                    serverCount++;
                    
                }catch( Exception error ){                         
                    break;
                }                                        
            }

            if( serverCount == 0 ){
                throw new KExceptionClass( "Configuration error, no servers defined.", null );
            }        
    }
    
    //-----------------------------------------------------------------------    
    
    public synchronized  KFrameworkService01 getNextServerPort() throws KExceptionClass{    
                                                       
        log.log( this, "Connecting... " + urlList.size() + " servers online. Next Server -> " + nextUrlIndex );
        
        currentUrlIndex = nextUrlIndex;
        nextUrlIndex ++; if( nextUrlIndex >= urlList.size() ) nextUrlIndex = 0;                                                            
        String currentURL = urlList.get( currentUrlIndex );                
        KFrameworkService01 nextPort = serverPortList.get( urlList.get( currentUrlIndex ) );                   
                        
        // Connect ---------------------------------------------------------------------------------
        
        if( nextPort == null ){
            
            try{
                
                log.log( this, "First connection to + " + urlList.get( currentUrlIndex ) );
            
                KFrameworkServerClassService service = 
                        new KFrameworkServerClassService( 
                            new URL( urlList.get( currentUrlIndex ) ), 
                                new QName("http://DataManagementComponent/", 
                                    "KFrameworkServerClassService" ) );

                nextPort = service.getKFrameworkPort01();   
                
                serverPortList.put(  urlList.get( currentUrlIndex ), nextPort );
                
            }catch( Exception error ){
                throw new KExceptionClass( "Could not connect to " +  urlList.get( currentUrlIndex ) + error, error);
            }
        }
                        
        log.log( this, "Connected!! using node =>" + urlList.get( currentUrlIndex ) );
        

        
        return( nextPort );
    }
    
    //-----------------------------------------------------------------------    
    
    public synchronized void reportCurrentServerDead() throws KExceptionClass{
        
        
           log.log( this, "Node " + urlList.get( currentUrlIndex ) + " is not responding. It is now being removed from the list of servers " );

           serverPortList.remove( urlList.get(currentUrlIndex) );           

           urlList.remove( currentUrlIndex );
                      
           if( urlList.isEmpty() ){
               
               loadUrls();
               
               if( !full_reset_mode ){ 
                   full_reset_mode = true;                   
                   log.log( this, ">>>>>>> UUpps, no servers left, rescan, lets see if a server came back up before reporting error...." );
               }else{               
                   full_reset_mode = false;
                   throw new KExceptionClass( "ERROR: Could not contact any server, please try again later", null );
               }               
           }
                      
           nextUrlIndex = 0;
    }
     
    //-----------------------------------------------------------------------    
    
}
