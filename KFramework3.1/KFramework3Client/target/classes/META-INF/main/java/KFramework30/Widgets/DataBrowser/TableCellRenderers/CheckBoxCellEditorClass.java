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
import KFramework30.Widgets.DataBrowser.KTableCellEditorBaseClass;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.KDataBrowserBaseClass;
import KFramework30.Widgets.DataBrowser.tableModelClass;


public class CheckBoxCellEditorClass 
extends KTableCellEditorBaseClass
{
    
        // uses
        public int                  columnType;        //info on column data type      
        
        Font                   columnFont;
        int                    columnAligment;        

        // Contructor
        public CheckBoxCellEditorClass(                
                tableModelClass tableModelParam,
                KLogClass logParam  )
        throws KExceptionClass        
        {
            super( tableModelParam, logParam );

            // uses
            columnType =  KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_CHARACTER;

            // has defaulted
            
            
            // setup
            
            setClickCountToStart( 1 );
        }        
        
      //---------------------------------------------------------        
      // Interface

        @Override
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column){ 
                    
   
                editor = new JCheckBox();
                    
            if( ((String) value).equals( "true" ) ) 
                ((JCheckBox)editor).setSelected(true);
                    else
                  ((JCheckBox)editor).setSelected(false);                
                    

        
                    
                // the default is OK     
                return(
                        super.getTableCellEditorComponent(
                        table,  value,  isSelected,  row,  column) 
                        );
        }        
        
  
    
      //---------------------------------------------------------       
        
        @Override
        public Object getCellEditorValue() {
            
            String result = "";
            
            if( ((JCheckBox) editor).isSelected() ) result = "true"; else result = "false";          

            return ( result );              
                
           
        }
        
    //---------------------------------------------------------                           

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

    public int getColumnType() {
        return KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_DATE;
    }

    public void setColumnType(int columnType) throws KExceptionClass {
        
            switch( columnType ){

                   
                case 
                        KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_CHARACTER:
                        break;   

                default:                    
                throw new KExceptionClass(
                    "Could not set renderer data type. Type specified is invalid."  , null 
                );
            }        
        this.columnType = columnType;
    }
        
        //---------------------------------------------------------       
        
        
        

}
