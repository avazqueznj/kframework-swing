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

package KFramework30.Widgets.DataBrowser;

// utilities
import KFramework30.Base.*;


public class recordClass extends Object {

    // uses
       
    // has - defaulted 
    
    //A array of record fields.
    private Object[]        data;
    
    //The number of fields.
    private int      recordLength;
    
    
    //----------------------------------------------------------------------
    // PUBLIC INTERFACE
    
    /** Creates new recordClass    */
    public recordClass( int length ) 
    {
        recordLength = length;
        
        data = new Object[ recordLength ];
        
        for( int i = 0; i < recordLength; i++ ) {
            data[i] = new String();
        }
    }
    
    /**  Return length of record   */
    public int getRecordLength( )
    {
        return recordLength;
    }
    
    /**  Get field value at index: i   */
    public Object getValueAt( int i )
    {
        return data[i];
    }
     
    
    /**  Set field value at index: i   */
    public void setValueAt(  int i, Object value )
    throws KExceptionClass
    {
        if( i >= recordLength ) {
            throw new KExceptionClass( 
                    "*** Table display error **** \n" +
                    "Invalid index when setting record value!"  , null );
        }     
        
        data[i] = value;
    }  
    
    @Override
    public String toString(){
        
        String result = new String();
        for( int index = 0; index < data.length; index++ ){
            result += data[ index ] + " | ";
        }
        
        return( result );
    }
   
}
