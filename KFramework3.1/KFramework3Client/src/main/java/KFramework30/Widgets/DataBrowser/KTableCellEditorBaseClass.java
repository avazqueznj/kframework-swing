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

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.DataBrowser.tableModelClass;


public abstract class KTableCellEditorBaseClass
extends DefaultCellEditor{

        // uses
        public tableModelClass      tableModel; 
        KLogClass                   log;
        
        // has
        public Component                   editor;
     

        // Contructor
        public KTableCellEditorBaseClass( 
                tableModelClass tableModelParam,
                KLogClass logParam  )
        throws KExceptionClass        
        {
            super( new JTextField() );

            // uses
            tableModel = tableModelParam;
            log = logParam;            


            setClickCountToStart( 1 );
        }

        //---------------------------------------------------------                        

        public abstract Object getCellEditorValue();

        
        //---------------------------------------------------------                

        // from interface
        @Override                        
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column){    
             
                Component result = editor;


                // see if we have a hook
                if( tableModel.cellDataCalcHook1 != null ){

                        try{

                            tableModel.cellDataCalcHook1.cellEditHook(
                                row, column, isSelected, result, 
                                tableModel.getColumnName( column ), (String) value, 
                                tableModel.getRecord( tableModel.getKeyValue( row ) ), log );

                        }catch( Exception error ){

                            log.log( this, "**ERROR**:" + KMetaUtilsClass.getStackTrace( error ) );
                        }
                }           

                return editor;
        }

    //===========================================================================      
    
    public abstract int getColumnAligment();

    public abstract void setColumnAligment(int columnAligment) ;

    public abstract Font getColumnFont();

    public abstract void setColumnFont(Font columnFont);
    
//===========================================================================          


    }

