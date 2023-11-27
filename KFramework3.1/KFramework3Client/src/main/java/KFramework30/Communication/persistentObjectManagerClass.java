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
 * This class provides methods to create, update, delete and retrieve business 
 * objects, like system users and unit measures, from or into the database. 
 * It supports data persistence from object orientation.
 * The actual object transactions are delegated to persistentObjectTransactionClass. 
 * The front-end objects and transaction formatted data mapping is established 
 * via materializeObject and serializeObject functions.  
 *
 */

package KFramework30.Communication;


// rtl
import KFramework30.Base.KConfigurationClass;
import KFramework30.Base.KBusinessObjectClass;
import java.util.*;

// framework
import KFramework30.Base.*;
import KFramework30.Widgets.*;
import ProblemDomainComponent.systemUserClass;

public class persistentObjectManagerClass extends Object {

    //const 
    public static final String SERVER_ADDRRESS = "server_address";

    // uses
    private KConfigurationClass configuration;
    private KLogClass log;
    
    // has - defaulted
    private XMLpersistentObjectTransactionClass objectTransaction;

    /** Creates new persistentObjectManager */
    public persistentObjectManagerClass( 
		KConfigurationClass configurationParam,
		KLogClass logParam ) 
    throws KExceptionClass{

        // inherits
        super();

        // uses
        configuration = configurationParam;
        log = logParam;

        // has - defaulted
        objectTransaction = new XMLpersistentObjectTransactionClass( configurationParam, logParam );

        log.log( this, "Constructed successfully." );
    }


                                public KBusinessObjectClass login4( systemUserClass user )
                                throws KExceptionClass
                                {
                                    log.log( this, "A user trying to login!!!" );                                    
                                    String session_id = objectTransaction.login( user );   
                                    
               			    configuration.setField( "SESSION", session_id );
                                    configuration.setField( "SESSION_ID", session_id );                                
                                    configuration.setField( "SESSIONKEY", "obsoleted" );                                                        
                                    
                                    return (systemUserClass) objectTransaction.getReturnedObject();                                                                     
                                }
                                                                                                


                                public KBusinessObjectClass createNew4( KBusinessObjectClass object )
                                throws KExceptionClass
                                {
                                    objectTransaction.createNew( object );
                                    return( objectTransaction.getReturnedObject() );
                                }    
                                     
                                public KBusinessObjectClass push_back4( KBusinessObjectClass object )
                                throws KExceptionClass
                                {                                    
                                    objectTransaction.push_back( object );
                                    return( objectTransaction.getReturnedObject() );
                                }                                
                                public KBusinessObjectClass update4( long id, KBusinessObjectClass object )
                                throws KExceptionClass
                                {
                                    objectTransaction.update( id, object );
                                    return( objectTransaction.getReturnedObject() );                                    
                                }

                                public KBusinessObjectClass copy4( long id, Class< ? extends KBusinessObjectClass > copiedClass )
                                throws KExceptionClass
                                {
                                    objectTransaction.copy( id, copiedClass.getName() );
                                    return( objectTransaction.getReturnedObject() );                                    
                                }    

                                public void delete4( long id, String className )
                                throws KExceptionClass
                                {
                                    objectTransaction.delete( id, className );        
                                } 

                                
                                public  KBusinessObjectClass execute4( String functionName, KBusinessObjectClass object, Map params )
                                throws KExceptionClass
                                {                                                                                                                   
                                    objectTransaction.execute( object );
                                    return( objectTransaction.getReturnedObject() );                                    
                                }
                                
                                // GENERIC POM actions                                
                                // ----------------------------------------------------------------    
                                // ----------------------------------------------------------------

} // end POM


