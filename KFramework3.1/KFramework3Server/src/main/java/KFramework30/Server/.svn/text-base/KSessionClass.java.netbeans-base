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

import java.sql.*;
import KFramework30.Base.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jan 28, 2009 created by Alejandro Vazquez, Ke Li, Behaven Patel, Maricela Islas
 */
public class KSessionClass  
{
    

    //uses
    private KConfigurationClass configuration;
    private KLogClass log;
    private long user_id = -1;
    
    // has - defaulted   
    private String SESSION_ID;    
    private long                            lastTime;          

    
    /*
       This is not updated across cluster nodes.
       The design asumess a stateless server, so that servers can be distributed geographically.
       Use this only in sigle server solutions, and even then, review your desgin and try for a stateless design.      
    */    
    private Map                              userData;

                      
            
    
    /** Creates a new instance of sessionClass, brand new for LOGIN and allocates new ID */
    public KSessionClass( 
            KConfigurationClass configurationParam, KLogClass logParam ) throws KExceptionClass
    {
        
        
        logParam.log( this, "+++++++++++++ New session ..." );     
        
        //uses
        configuration = configurationParam;
        log = logParam;
        
        // has - defaulted
        lastTime = System.currentTimeMillis();
        userData = new HashMap();
                        
        // do
        setSessionUniqueID( -1 );
        
        log.log( this, "Manager connecting ...  done!" );                  
            
    }

    // ==================================================================================    

    /** Restores lost session class, from session string ...*/
    public KSessionClass(             
            KConfigurationClass configurationParam, KLogClass logParam, String SESSION_IDParam
            ) throws KExceptionClass
    {
                
        logParam.log( this, "+++++++++++++ Restore session ..." );     
        
        //uses
        configuration = configurationParam;
        log = logParam;
        
        // has - defaulted
        lastTime = System.currentTimeMillis();
        userData = new HashMap();
                
        // set ids
        SESSION_ID = SESSION_IDParam;           
        StringTokenizer tokenizer = new StringTokenizer( SESSION_ID, ":", false );
        tokenizer.nextToken();
        user_id = ( KMetaUtilsClass.getIntegralNumericValueFromString( tokenizer.nextToken() ) );
                                        
        log.log( this, "Manager connecting ...  done!" );                  
            
    }    
   
    

    // ==================================================================================
    
    public long getLastTime(){ return lastTime;}
    
    // ==================================================================================    

    public String getID(){ return SESSION_ID;}
      
    // ==================================================================================       
         

    public long getUser_id() {
        return user_id;
    }
    
    // ================================================================================== 
    
    public static long sessionSequence = 0;

    // from anonymouis and from POM
    public void setSessionUniqueID( long user_idParam ) throws KExceptionClass {
        
        if( user_id != -1 ) throw new KExceptionClass("Could not set sessions user id, its already set", null );
        
        user_id = user_idParam;
        
        sessionSequence++;
        
        try{
        
            // set id    
            if( user_id != -1 ){
                SESSION_ID = "SESSION_KF2_" + java.net.InetAddress.getLocalHost().getHostName() + "." + sessionSequence + "." + KMetaUtilsClass.getCurrentTimeMilliseconds() + "_USER:" + user_id;
            }else{
                SESSION_ID = "SESSION_KF2_" + java.net.InetAddress.getLocalHost().getHostName() + "." + sessionSequence + "." + KMetaUtilsClass.getCurrentTimeMilliseconds() + "_USER:SERVER";            
            }

        }catch( Exception error ){
            throw new KExceptionClass( "Could not build session id" + error.toString() , error);
        }            
            
            
    }

            
    // ==================================================================================                 

    /*
       This is not updated across cluster nodes.
       The design asumess a stateless server, so that servers can be distributed geographically.
       Use this only in sigle server solutions, and even then, review your desgin for a stateless design.      
    */
    public Map getUserData() {
        return userData;
    }

    // ==================================================================================                     
}



        
