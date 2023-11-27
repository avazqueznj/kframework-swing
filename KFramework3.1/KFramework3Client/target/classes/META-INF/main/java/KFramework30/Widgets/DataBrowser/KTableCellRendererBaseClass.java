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

//rtl
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.DataBrowser.tableModelClass;



public abstract class KTableCellRendererBaseClass 
extends DefaultTableCellRenderer 
{

    // uses
    public   tableModelClass              tableModel; 
    public   KLogClass                    log;
    public   Component                    renderer;

    // has 
             

    // Contructor
    public KTableCellRendererBaseClass(            
            tableModelClass tableModelParam,
            KLogClass logParam )
    throws KExceptionClass        
    {
        super( );

        // uses      
        tableModel = tableModelParam;
        log = logParam;       

        // has                            
    }

    
    
    //---------------------------------------------------------         
    
    public abstract int getColumnType(); // some other will query
   
    //---------------------------------------------------------       

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value,
        boolean isSelected, boolean hasFocus,
        int row, int column){    

            Component result = renderer;
            

            // see if we have a hook
            if( tableModel.cellDataCalcHook1 != null ){

                    try{
                                                
                        tableModel.cellDataCalcHook1.cellRenderingHook(
                            row, column, isSelected, result, 
                            tableModel.getColumnName( column ), (String) value, 
                            tableModel.getRecord( tableModel.getKeyValue( row ) ), log ) ;

                    }catch( Exception error ){

                        setText( "**ERROR**"  );                                                                                        
                        log.log( this, "**ERROR**:" + KMetaUtilsClass.getStackTrace( error ) );
                    }
            }


            return result;
    }

        
    //===========================================================================      
    
    public abstract int getColumnAligment();

    public abstract void setColumnAligment(int columnAligment) ;

    public abstract Font getColumnFont();

    public abstract void setColumnFont(Font columnFont);
    
//===========================================================================  

}

