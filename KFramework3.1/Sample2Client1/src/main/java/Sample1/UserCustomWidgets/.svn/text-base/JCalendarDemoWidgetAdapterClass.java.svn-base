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

package Sample1.UserCustomWidgets;

import KFramework30.Base.KCustomWidgetIntegrationIntrerface;
import KFramework30.Base.KExceptionClass;
import KFramework30.Base.KMetaUtilsClass;
import com.toedter.calendar.JDateChooser;

public class JCalendarDemoWidgetAdapterClass 
implements KCustomWidgetIntegrationIntrerface
{

    // uses 
    JDateChooser dateChooser;
    String fieldName;
    
    // has
    
    // interface
    
    public JCalendarDemoWidgetAdapterClass( JDateChooser widgetParam, String fieldNameParam ){  
        
        // uses
        dateChooser = widgetParam;
        fieldName = fieldNameParam;
        
        // has
    }
    
    // from abstract interface
    
    public String getName() throws KExceptionClass {
        return( fieldName ); 
    }

    public void setValue(String newValue) throws KExceptionClass {   
        if( newValue == null) return;
        if( newValue.equals("") ) return;
        dateChooser.setDate( KMetaUtilsClass.stringToDate( KMetaUtilsClass.KDEFAULT_LONG_DATE_TIME_FORMAT, newValue) ); 
    }

    public String getValue() throws KExceptionClass {
        return(
            KMetaUtilsClass.dateToString( KMetaUtilsClass.KDEFAULT_LONG_DATE_TIME_FORMAT, dateChooser.getDate() ) 
                );
    }

    public void enable() throws KExceptionClass {
        dateChooser.setEnabled(true);
    }

    public void disable() throws KExceptionClass {
        dateChooser.setEnabled(false);
    }
       
}
