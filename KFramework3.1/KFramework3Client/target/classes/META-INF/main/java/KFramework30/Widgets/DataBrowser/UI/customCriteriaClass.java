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


package KFramework30.Widgets.DataBrowser.UI;

import KFramework30.Widgets.DataBrowser.filterClass;
import javax.swing.*;
import java.util.*;
import java.awt.Component;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.KDataBrowserBaseClass;

public class customCriteriaClass extends javax.swing.table.AbstractTableModel {
    
    // uses
    private KConfigurationClass     configuration;    
    private KLogClass               log;   
    private java.awt.Window         parentWindow;        //for displaying error message    
    private KDataBrowserBaseClass   targetBrowser;  
    private String[][]              rowData;    
    
    // has - defaulted
    private java.util.List      headers;            //Table header   
     
    
    //------------------------------------------------------------------------
    //PRIVATE INTERFACE
    
    /**     Check numeric data format.
            The format is operator (not, < and >) + decimal digit and 
            delimiter character ','. */
    private boolean isValidData( String data ) 
    {
        boolean returnVal = true;
        boolean pointFlag = false;        
                       
        StringTokenizer tokens = new StringTokenizer( data.trim(), "," );
     
        //for each criteria
        while( tokens.hasMoreTokens() ) {            
     
         String tempData = tokens.nextToken().trim();              
        
            //Data contained only spaces are ignored.
            if( tempData.length() != 0 ) {   
                //Check operator 'NOT', 'not', '<' and '>'.
                if( tempData.startsWith("NOT") || tempData.startsWith("not") ) 
                    tempData = tempData.substring( 3 );
                else if( tempData.startsWith("<") || tempData.startsWith(">") ) 
                    tempData = tempData.substring( 1 );

                tempData = tempData.trim();

                //Check digit format.
                if( tempData.length() != 0 ) {       
                    for( int i=0; i<tempData.length(); i++ ) {
                        if( pointFlag && tempData.charAt(i) == '.' ) {
                            returnVal = false;
                            break;
                        }
                        else if( !pointFlag && tempData.charAt(i) == '.' )
                            pointFlag = true;
                        else if( !Character.isDigit( tempData.charAt(i) ) ) {
                            returnVal = false;
                            break;
                        }                
                    }            
                }
                else
                    returnVal = false;
            } //end of if
         
        } //end of while
       
       return  returnVal;

    }

    //----------------------------------------------------------------------
    
    /**     Go through each cell. If it is numeric type, check the data format.
            If one is invalid format, display error message and return false. */
    public boolean isValidTableData()
    throws KExceptionClass     
    {
        boolean returnVal = true;
        
        for( int col = 0; col < headers.size(); col++ ) {  
            //Get type info for each column.
            String name = ( String ) headers.get( col );
            int columnType = targetBrowser.getColumnType( name );  
            
            for( int row = 0; row < rowData.length; row++ ) { 
            
                //For numeric cell, check the data format.
                if( 
                        ( 
                        columnType == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERICNOFORMAT  ||
                        columnType == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERIC  ||
                        columnType == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERIC2 ||
                        columnType == KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_CURRENCY 
                        ) &&
                    rowData[row][col] != null &&
                    rowData[row][col].length() != 0 &&
                    !isValidData( rowData[row][col] )
                    ) {
                        returnVal = false;
                        
                        // show error message
                        String message = "*** Invalid input data **** \n" + "'" +
                        rowData[row][col] + "' is invalid data at row [" + 
                        row + "], column [" + col + "].";   
                        KMetaUtilsClass.showErrorMessageFromText1( parentWindow, message );  
                }
            }
        } 
        
        return returnVal;
    }        

    //----------------------------------------------------------------------
    
    /** Creates new customCriteriaClass */
    public customCriteriaClass( KConfigurationClass configurationParam,KLogClass logParam,
                                java.awt.Window parentWindowParam, KDataBrowserBaseClass tableFillerParam ) 
    throws KExceptionClass                                    
    {
        // inherits
        super();
        
        configuration = configurationParam;
        log = logParam;
        parentWindow = parentWindowParam;        
        targetBrowser = tableFillerParam;
        
        //build up headers
        headers = new ArrayList( );
        targetBrowser.getColumnNames( headers );
        
        String[][] tempRowData = targetBrowser.GetCustomCriteriaRowData();
        rowData = new String[tempRowData.length][tempRowData[0].length]; 
        
        //Copy previous saved data
        for( int row = 0; row < tempRowData.length; row++ )        
            for( int col = 0; col < tempRowData[0].length; col++ ) 
                if( tempRowData[row][col] != null )
                    rowData[row][col] = new String( tempRowData[row][col] ); 
            
    }
 
    
    //----------------------------------------------------------------------
    /** Return visible table size  */
    public int getRowCount( )
    {
        return rowData.length;
    } 
    
    //----------------------------------------------------------------------
    /** Return column size  */
    public int getColumnCount( )
    {
        return headers.size();
    }  
    
    
    //----------------------------------------------------------------------
    /** Return table value at row , col  */
    public Object getValueAt( int row, int col )
    {
        return ( Object ) rowData[row][col];
    }  
    
    //----------------------------------------------------------------------
    /** Return column name at col  */
    public String getColumnName( int col )
    {
        return ( String ) headers.get( col );
    }   


    public boolean isCellEditable(int row, int col)
   { 
       return true; 
    }
        
        
    public void setValueAt(Object value, int row, int col) {
        rowData[row][col] = ( String ) value;
	fireTableCellUpdated(row, col);
    }
    
    
    //----------------------------------------------------------------------
    /**     Retrieve data from the table  */
    public void processCriteria( )  
    throws KExceptionClass    
    {
        //clear old criteria
        targetBrowser.clearCustomCriteria();
        
        //For each row, collect input criteria and put them into 'AND' list
        for( int row = 0; row < rowData.length; row++ ) { 
            java.util.List rowFilters = new ArrayList();
            
            for( int col = 0; col < headers.size(); col++ ) {
                
                String name = ( String ) headers.get( col );
                int columnType = targetBrowser.getColumnType( name );
                String tempData;
                
                if( rowData[row][col] != null 
                    //Data contained only spaces are ignored.
                    && ( (String)rowData[row][col] ).trim().length() != 0 ) {
                        
                    StringTokenizer tokens = new StringTokenizer( ((String)rowData[row][col] ).trim(), "," );
     
                    //for each criteria in the cell
                    while( tokens.hasMoreTokens() ) {                                                       
                        filterClass cellValue = new filterClass( 
                                name, tokens.nextToken().trim(), columnType );

                        rowFilters.add( cellValue );
                    }
                }
            } 
            
            if( rowFilters.size() != 0 )
                targetBrowser.addCustomCriteria( rowFilters );            
        }
        
        //Save custom input criteria
        targetBrowser.setCustomCriteriaRowData( rowData );
        
        //Apply criteria to DB query
        targetBrowser.refresh();
    }
    
    
    //----------------------------------------------------------------------
    /**     Clear all data in the table  */
    public void  clearAll()
    {
         for( int row = 0; row < rowData.length; row++ )            
            for( int col = 0; col < headers.size(); col++ ) 
                 rowData[row][col] = null;  
         
         fireTableDataChanged();
    }
    
}
