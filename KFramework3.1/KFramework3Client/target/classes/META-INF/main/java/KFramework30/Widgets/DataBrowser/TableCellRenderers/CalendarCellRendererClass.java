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


package KFramework30.Widgets.DataBrowser.TableCellRenderers;

//rtl
import KFramework30.Widgets.DataBrowser.KTableCellRendererBaseClass;
import javax.swing.*;
import java.awt.*;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.KDataBrowserBaseClass;
import KFramework30.Widgets.DataBrowser.tableModelClass;
import com.toedter.calendar.JDateChooser;


public class CalendarCellRendererClass 
extends KTableCellRendererBaseClass
{
    
    // uses
    int                    columnType;
    
    // has
    Font                   columnFont;
    int                    columnAligment;
    
        
        
    public CalendarCellRendererClass(
            tableModelClass tableModelParam,
            KLogClass logParam ) throws KExceptionClass{
                                            
            super(  tableModelParam, logParam );
            
          

                  
              columnType = KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_DATE;


    }           
    
    //===========================================================================
    
    @Override
    public  int getColumnType(){
        
        return( columnType );
    } 
    
    
    //===========================================================================    
    
    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value,
        boolean isSelected, boolean hasFocus,
        int row, int column){
            
            
            try {
                
                // get a default one
                renderer = new JDateChooser( 
                        KMetaUtilsClass.stringToDate( 
                            KMetaUtilsClass.KDEFAULT_LONG_DATE_TIME_FORMAT, (String) value ) );
                
            } catch (KExceptionClass ex) {
                
                renderer = new JDateChooser( );

            }
            
            


            ((JDateChooser)renderer).setFont( getColumnFont() );

            
          // will deliver to hook
            return( super.getTableCellRendererComponent(
                table,  value, isSelected,  hasFocus, row,  column) );
            
        }


    
    //===========================================================================        
    
    public void setDataType( int columnTypeParam ) 
    throws KExceptionClass{
        
            // check type of data to be displayed...
            switch( columnTypeParam ){

                 
                case KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_DATE:

                        break;   

                default:                    
                throw new KExceptionClass(
                    "Could not set renderer data type. Type specified is invalid."  , null 
                );
            }            
            columnType = columnTypeParam;        
    }



    //===========================================================================            

    // in case of dynamic renderers, we need to colne it each time or the
    // customization will carry on on all cells.
    // the cloned will be used and passed for customization, then discarded
    

    
    //===========================================================================      
    
    public int getColumnAligment() {
        return columnAligment;
    }

    public void setColumnAligment(int columnAligment) {
        this.columnAligment = columnAligment;
    }

    public Font getColumnFont() {
        return columnFont;
    }

    public void setColumnFont(Font columnFont) {
        this.columnFont = columnFont;
    }    
    
//===========================================================================          
}
