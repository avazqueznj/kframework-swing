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

import KFramework30.Base.KActiveObjectClass;
import KFramework30.Base.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 *
 * @author Jan 28, 2009 created by Alejandro Vazquez, Ke Li, Behaven Patel, Maricela Islas
 */
public class sessionContainerClass 
implements KActiveObjectClass.KActiveObjectInterface
{

       
    // inner
    public class sessionNotFoundExceptionClass extends KExceptionClass{
        sessionNotFoundExceptionClass(){
            super(
                "Cannot retrieve session.\n" +
                "The session does not exist or it expired."  ,null 
                );
        }
    }
    
    // uses
    public KConfigurationClass  configuration = null;
    public KLogClass            log = null;

    // has - defaulted
    private Map<String, KSessionClass> sessionMap = null;
    
    /** Creates a new instance of sessionContainerClass */
    public sessionContainerClass( 
        KConfigurationClass configurationParam,
        KLogClass logParam ) {
        
        //use
        configuration = configurationParam;
        log = logParam;
        
        // has - defaulted
        sessionMap = new HashMap<String, KSessionClass>();
    }
    
    /** Synchronized method that adds a session to the container. */
    synchronized public void addSession( KSessionClass session ) {
        
        sessionMap.put( session.getID(), session );        
    }

    /** Synchronized method that gets a session from the container. */
    synchronized public KSessionClass getSession( String sessionId ) 
    throws KExceptionClass
    {
        if( !sessionMap.containsKey(sessionId) ){
            
            log.log( this, "Session " + sessionId + " does not exist in this node, restoring... " );            
            KSessionClass restoredSession = new KSessionClass( configuration, log, sessionId );            
            sessionMap.put( restoredSession.getID(), restoredSession );                 
            log.log( this, "Session " + sessionId + " was restored... " );
            
        }            
         
        return sessionMap.get(sessionId);  
    }

    /** Synchronized method that returns total sessions. */
    synchronized public int countSessions(){ return( sessionMap.size() ); }

    //...................

    public void run() throws KExceptionClass
    {
        
        synchronized( this ){
        
            long nowTime = System.currentTimeMillis();
            long sessionMax = Long.parseLong( configuration.getField( "session_idle_max_mins" ) ) * 1000 * 60;

            // backup sessions
            Vector< KSessionClass > sessions = new Vector< KSessionClass >();
            Iterator< KSessionClass > sessionsIterator = sessionMap.values().iterator();
            while( sessionsIterator.hasNext() ) {            
                sessions.add( sessionsIterator.next() );
            }

            // scan sessions
            Iterator< KSessionClass > sessionIteratorRemove = sessions.iterator();
            while( sessionIteratorRemove.hasNext() ){

                KSessionClass currentSession = sessionIteratorRemove.next();

                log.log( this, 
                        "Active Session: [" + currentSession.getID() + "]   " +
                        "Idle time: " +(nowTime - currentSession.getLastTime())/ ( 1000 * 60 ) + " mins from max " + ( sessionMax / ( 1000 * 60 ) )  );            

                //if the session is old, remove it...
                if( ( nowTime - currentSession.getLastTime() ) >= sessionMax ) {

                    log.log( this, "Removing session [" + currentSession.getID() + "].\n" );  

                    sessionMap.remove( currentSession.getID() );                

                }            
            }
            
        } // synch
            
            
      }        


    //...................

    synchronized public void removeAllSessions( )
    throws KExceptionClass
    {
     
        sessionMap.clear();
    }


    //...................
    
    public void afterPaused() throws KExceptionClass {
    }    
}

