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

// rtl
import javax.swing.table.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;

// ale
import KFramework30.Base.*;


public class tableHeaderRendererClass 
extends javax.swing.JTable
implements  TableCellRenderer{ 
            
    //first column of array holds column labels and operation results in JLabels
    //second column holds operations in String. label related operational field is null.
    //third column holds format code.
    private Object[][]  tableData;
    
    protected String      column_name;     // or field name displayed in JTable

    /** Creates new tableHeaderRendererClass */
    public tableHeaderRendererClass( int row, String columnName ) 
    {
        super( row, 1 );
        
        tableData = new Object[row][];
        for( int i=0; i<row; i++ ) {
            tableData[i] = new Object[] { new javax.swing.JLabel(), null, null };
        }
        
        column_name = columnName;
        
        //----------------------------------------------------------------------
        //set center alignment
    
        TableColumn column = getColumnModel().getColumn( 0 );                         
        TableCellRenderer render = getDefaultRenderer(column.getClass());
         
        if( render instanceof JLabel ){
            ((JLabel) render).setHorizontalAlignment( JLabel.CENTER );
            ((JLabel) render).setBackground( new Color(220,220,220) );
        }
    }
    
    
    public String getColumnName()
    {
        return column_name;
    }
    
      
    //TableCellRenderer interface implementation
    public java.awt.Component getTableCellRendererComponent(           
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column){    
                    
                return this;
    }
    
    // set row text 
    public void setText( int row, String text ) 
    {
        getModel().setValueAt( text, row, 0 );
        tableData[row][0] = new javax.swing.JLabel( text );
    }
    
    public Object[][] getOperations()
    {
        return tableData;
    }
    
   
    public void setOperationAt( String operation, int formatCode, int row )
    {
        tableData[row][1] = new String( operation );
        tableData[row][2] = new Integer( formatCode );
    }
    
    
    public void setLabelAt( String label, int row )
    {
        tableData[row][0] = new javax.swing.JLabel( label );
    }
    
    
    public void update( tableModelClass tableModel )
    throws KExceptionClass
    {
        // load result
        for( int i=0; i<tableData.length; i++ ) 
            getModel().setValueAt( ((JLabel)tableData[i][0]).getText(), i, 0 );    
    }
    
}
