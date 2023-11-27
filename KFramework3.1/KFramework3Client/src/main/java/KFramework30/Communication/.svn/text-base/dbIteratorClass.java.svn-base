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

// utilities
import KFramework30.Base.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class dbIteratorClass extends Object {

    // uses
    private KConfigurationClass configuration;
    private KLogClass log;
    
    private String foreignKeyField;
    private long foreignKeyValue;
    private String tableName;

    
    
    // has
    private dbTransactionClientClass dbTransaction;
    private int state;
        
    final private int IDLE = 0;
    final private int LOADED = 1;    

    
    
        
    /** Creates new dbIteratorClass */
    public dbIteratorClass(  
        KConfigurationClass configurationParam, KLogClass logParam,     
        String foreignKeyFieldParam,  long foreignKeyValueParam, String tableNameParam
    )
    throws KExceptionClass    
    {
        
        // uses
        configuration = configurationParam;
        log = logParam;
        
        foreignKeyField = foreignKeyFieldParam;
        foreignKeyValue = foreignKeyValueParam;

        tableName = tableNameParam;       
               
        // get a Db transaction  
        dbTransaction = new dbTransactionClientClass(  configuration, log ); 
        
        state = IDLE;
    }

    
    public void load()
    throws KExceptionClass
    { 
        
        state = IDLE;
        
        String sql;
        sql = " SELECT * FROM ";
        sql += tableName;
        sql += " WHERE ";

        sql += " ( " + foreignKeyField + " = ? ) ";
        
  
        dbTransaction.prepare( sql );        
        dbTransaction.bind( "?", foreignKeyValue );
        
        dbTransaction.executeQuery( 0, 65536 );
        
        state = LOADED;
    }
    
    public boolean fetch()
    throws KExceptionClass    
    {
        
        if( state == IDLE ){
            throw new KExceptionClass(
                "Can not read data\n" +
                "DBiterator was not run" , null 
            );
        }
        
        return( dbTransaction.fetch() );
    }
    
        
    public String getAttribute( String attributeName )
    throws KExceptionClass        
    {        
        return( (String) dbTransaction.getField( attributeName ) );
    }

    public String getProperty( String attributeName )
    throws KExceptionClass        
    {        
        return( (String) dbTransaction.getField( attributeName ) );
    }

    public String getField( String attributeName )
    throws KExceptionClass        
    {        
        return( (String) dbTransaction.getField( attributeName ) );
    }
    
    //make field name lowercase and return the current row
    public Map<String, Object>  nextRowWithFliedNameInLowerCase( )       
    {               
        Map lowcase_key_map = new HashMap<String, Object>();
        
        Map _map = (HashMap)dbTransaction;
        
        if( _map != null) {
            Set<String> keys_set = _map.keySet();
            Iterator<String> key_iterator = keys_set.iterator();                               

            //change field name to lowercase...
            while( key_iterator.hasNext() ){

                    String theKey = key_iterator.next();
                    Object theValue = _map.get(theKey);
                    lowcase_key_map.put(theKey.toLowerCase(), theValue);                
                }
        }
        
        return lowcase_key_map;
    }
    
   public Map nextRow( )       
    {        
        return( new HashMap(dbTransaction) );
    }    
}
