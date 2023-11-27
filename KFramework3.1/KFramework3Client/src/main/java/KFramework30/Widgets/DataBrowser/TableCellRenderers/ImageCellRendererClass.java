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
import java.text.DecimalFormat;
import java.text.NumberFormat;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.KDataBrowserBaseClass;
import KFramework30.Widgets.DataBrowser.tableModelClass;
import java.util.Locale;
import javax.swing.table.DefaultTableCellRenderer;


public class ImageCellRendererClass 
extends KTableCellRendererBaseClass
{
    
    // uses
    int                    width;
    int                    height;
    
    // has
    Font                   columnFont;
    int                    columnAligment;
    
        
        
    public ImageCellRendererClass(           
            tableModelClass tableModelParam,
            KLogClass logParam, int widthParam, int heightParam ) throws KExceptionClass{
                                            
            super(  tableModelParam, logParam );

            // uses
            width = widthParam;
            height = widthParam;
            
            // has
        
            
    }           
    
    //===========================================================================
    
    @Override
    public  int getColumnType(){
        
        return( KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_IMAGE );
    } 
    
    
    //===========================================================================    
    
    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value,
        boolean isSelected, boolean hasFocus,
        int row, int column){
            
            // get a default one
            renderer = ( new DefaultTableCellRenderer(  ) ).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
                     

            ((JLabel)renderer).setText( "" ); //clear it
            ((JLabel)renderer).setHorizontalAlignment(columnAligment);
            
            // intercalate row colors
            if( row % 2 == 0 && !isSelected ){
                ((JLabel)renderer).setBackground( new Color( 0xE9, 0xFF, 0xE9 ) );
            }
                            
            // render
            ((JLabel)renderer).setSize( width, height );
            
            
            if( value != null  ){
                ((JLabel)renderer).setIcon( new ImageIcon( (byte[]) value ) );
            }
         
            // will deliver to hook
            return( super.getTableCellRendererComponent(
                table,  value, isSelected,  hasFocus, row,  column) );
            
        }


    
    //===========================================================================        
    
    public void setDataType( int columnTypeParam ) 
    throws KExceptionClass{
        
            // check type of data to be displayed... only one supported in this 
            switch( columnTypeParam ){


                case KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_IMAGE:
                        break;   

                default:                    
                throw new KExceptionClass(
                    "Could not set renderer data type. Type specified is invalid."  , null 
                );
            }            
           
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
