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
import KFramework30.Widgets.DataBrowser.cellWriterInterface;
import KFramework30.Widgets.DataBrowser.cellRenderingHookInterface;
import KFramework30.Widgets.DataBrowser.SQLPreprocessorClass;
import KFramework30.Widgets.DataBrowser.KBrowserDataWriterInterface;
import javax.swing.*;
import java.awt.Cursor;
import java.awt.Component;
import java.util.*;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.TreeView.*;

/**     This class represents any type tabular result from database query.  
 *      It implements the TableModel interface that specifies the methods 
 *      the JTable will use to interrogate a tabular data model. In other words, 
 *      it manages a data model that is displayed in JTable. 
 *
 *      The algorithm this class uses is following:
 *      The visual table has a virtual size. The table model has 
 *      a cache list to cache data from DB. The visual table size 
 *      is much bigger than the cache size. When a certain row's data 
 *      needs to be displayed by the visual table, a Sql request with the starting 
 *      row and the amount of rows needed is sent. The starting row index is 
 *      calculated to make sure that the requested row's index is in the middle of 
 *      returned data from DB request. 
 *      When the server reaches the end of the table, the total length of 
 *      the table is sent back to the visual table. The visual table is 
 *      readjusted accordingly.
 *
 *      The database transaction issues are delegated to SQLPreprocessorClass. 
 */


public class tableModelClass extends javax.swing.table.AbstractTableModel {

    //Constants
    static final String NO_CHANGE = "0";
    static final String CHANGED = "1";
    
    // uses
    private KConfigurationClass             configuration;    
    private KLogClass                       log;  
    private java.awt.Window                 parentWindow;        //for displaying error message
    private SQLPreprocessorClass            SQLPreprocessor;
    private cellWriterInterface             cellWriter = null;
    public  cellRenderingHookInterface      cellDataCalcHook1 = null;   
    private JTable                          visualTable;
    
    // has - defaulted   
    private int                         startRow;           //The first record index in DB's table
    public int                          cacheSize;
    private int                         visualTableSize;
    private int                         defaultVisualTableSize;
    private List                        headers;            //Table header    
    private List                        columnType;         //Table column types            
    private List                        tableDataCache;          //Cache list    
    private int                         visibleColumnCount;  // getColumnCount
      
    //Variables for updating cache 
    private boolean                     tableRedraw;     //true if the query reaches the end of DB table
                                                         //the visual table size is assigned as the DB table one.
                                                         //the visual table starts redrawing.
    private boolean                     loadExceptionOccur; //true if load function throw exception.   
        
    private java.util.List              treeViews;    
    
    //----------------------------------------------------------------------
    // PRIVATE INTERFACE
    
    //----------------------------------------------------------------------
    
    //----------------------------------------------------------------------
    // PUBLIC INTERFACE
    
    
    /** Creates new tableModelClass */
    public tableModelClass( KConfigurationClass configurationParam, KLogClass logParam,  
                            java.awt.Window parentWindowParam, SQLPreprocessorClass SQLPreprocessorParam,
                            JTable visualTableParam ) 
    throws KExceptionClass
    {
        // inherits
        super();

        // uses
        configuration = configurationParam;
        log = logParam;
        parentWindow = parentWindowParam;
        SQLPreprocessor = SQLPreprocessorParam;
        visualTable = visualTableParam;
        
        // has - defaulted 
        startRow = 0;
        cacheSize = Integer.parseInt(configuration.getField( "data_cache_size" ));
        defaultVisualTableSize = Integer.parseInt(configuration.getField( "visual_table_size" ));
        visualTableSize = defaultVisualTableSize;
        headers = new ArrayList( );
        columnType = new ArrayList( );
        tableDataCache = new ArrayList< recordClass >();        
        loadExceptionOccur = false;
        tableRedraw = false;        
      
        treeViews = new ArrayList();   
        visibleColumnCount = 0; // all default
        
        log.log( this, "Constructed successfully." );
    }
    

    // >>>>>>>>>>> *******
    //----------------------------------------------------------------------
    /**   Execute the query and get the results.  */
    public void load( int rowIndex )
    throws KExceptionClass
    {
        // calculate start of new cache, if not enough rows for top half of cache 
        // then start from 0
        int halfCache = cacheSize / 2 ;
        int DBStartRow = 0;       
        if( rowIndex > halfCache ) DBStartRow = rowIndex - halfCache;

        //Do query to DB
        try{
            
            log.log( this, "Requesting: " + cacheSize + " rows from index " + DBStartRow + " >>> " );
            SQLPreprocessor.load( DBStartRow, cacheSize );  
            
        }catch( Exception loadError ){
            
            //set load failed flag, kill window                        
            loadExceptionOccur = true;         
            visualTableSize = 0;
            tableDataCache = new ArrayList< recordClass >();        
            fireTableDataChanged();   
            
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( loadError ) );
            // show error message
            throw new KExceptionClass( "Could not load table data! " , loadError );

        }

        //Set up table header at the first load.
        if( headers.isEmpty() ) {
            headers = SQLPreprocessor.getHeaderNames();     
        }

        //---------

        //Load rows into the cache list.
        //Key field values are in the cache list as the last field in each record.
        tableDataCache.clear();            
        while( SQLPreprocessor.nextRowValue() ) { 

            //Second last extra field is the flag of record changed.
            //Last extra field is for key field value witch is hidden from show
            recordClass record = new recordClass( headers.size() + 2 );

            //Second last extra field is the flag of record changed.
            record.setValueAt(  headers.size(), NO_CHANGE );

            SQLPreprocessor.getRowValue( record );
            tableDataCache.add( record ); 
        }

        log.log( this, "cacheList size = " + tableDataCache.size());

        //---------

        if( 
            // Last requested row number        
            ( DBStartRow + cacheSize )  > 
            // Last replied row number
            ( SQLPreprocessor.getloadedStartRowIndex() + SQLPreprocessor.getloadedRowCount() ) 
            ){ 
                // It is the end of table.
                // The visual table is readjusted accordingly. 
                visualTableSize = SQLPreprocessor.getloadedStartRowIndex() + SQLPreprocessor.getloadedRowCount();   
                fireTableDataChanged(); 
                tableRedraw = true;
            }

        startRow = SQLPreprocessor.getloadedStartRowIndex();                        


    
        log.log( this, "visualTableSize = " + visualTableSize );  

    }

    //-----------------------------------------------------------------------
        
    public List getTableDataCache(){        
        
        return( tableDataCache );
    }
    
    public List getTableDataHeaders(){        
        
        return( headers );
    }    
        

    
    //-------------------------------------------------------------------------
   
    public void setCellWriter( cellWriterInterface cellWriterParam ) 
    { cellWriter = cellWriterParam; }
    
    //-------------------------------------------------------------------------

    public void setCellDisplayHook( cellRenderingHookInterface cellDisplayHookParam ) 
    { cellDataCalcHook1 = cellDisplayHookParam; }
    
         
    //----------------------------------------------------------------------
    /**   Return the key field value at index.  */
    public long getKeyValue( int index ) throws KExceptionClass
    {       
        long returnValue;    

	// Translate jtable index to cache index
        int listIndex = index - startRow;
        
	// out of cache ?
        if( listIndex >= tableDataCache.size() || listIndex < 0  ){
		// yes, return -1
		returnValue = -1;
        }
	else{
		// no, locate correponding key
                //The last hidden field in the record is the key field value.
                String keyValue = (String) ( ( recordClass ) tableDataCache.get( listIndex ) ).getValueAt( headers.size() + 1 );
		returnValue = KMetaUtilsClass.getIntegralNumericValueFromString( keyValue );
	}
            
        return returnValue;
    }    
        
    //----------------------------------------------------------------------
    /**   Return the field value at index.  */
    public Object getFiedlValue( int index, int sqlColumnIndex )
    {       
        Object returnValue = null;

	// Translate jtable index to cache index
        int listIndex = index - startRow;
        
	// in cache ?
        if( listIndex < tableDataCache.size() ){
            //get index column.
            recordClass reg = ( recordClass ) tableDataCache.get( listIndex );
            if ( reg.getRecordLength() > sqlColumnIndex )
                returnValue = reg.getValueAt( sqlColumnIndex );
	}
            
        return returnValue;
    }    
    //----------------------------------------------------------------------
    /** Return visible table size  */
    public int getRowCount( )
    {
        return visualTableSize;
    } 
    
    //----------------------------------------------------------------------
    /** Return column size  */
    public int getColumnCount( )
    {
        
        if( visibleColumnCount == 0 ){
            
            return headers.size();     
            
        }else{
            
            return visibleColumnCount;
        }
        
       
    }  
    
    
    //-----------------------------------------------------------------------
    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) 
    {   
               
        // if editable 


            // find field data
            String columnName = getColumnName( col );
            Iterator headerInfo =  SQLPreprocessor.headerFieldNameList.iterator();
            while( headerInfo.hasNext() ){                
                
                SQLPreprocessorClass.fieldInformationClass currentHeaderInfo 
                    = (SQLPreprocessorClass.fieldInformationClass) headerInfo.next();
                
                if( currentHeaderInfo.headerName.equals( columnName ) ){
                    
                    return( currentHeaderInfo.columnEditable );
                }
            }

        
        
        return( false );
    }  
    


    
    @Override
     public Class getColumnClass(int index) {
        return getValueAt(0, index).getClass();
    }

    //---------------------------------------------------------------------
    public  Vector getColumnData(int column_index)
    {
        int row_count = getRowCount( );
        Vector column_values = new Vector<Object>();
        
        for( int i = 0; i < row_count; i++ )
            column_values.add( getValueAt( i, column_index ) );
        
        return column_values;
    }
    
    //----------------------------------------------------------------------
    /** Return table value at row , col  */
    public Object getValueAt( int row, int col )
    {
        // load failed before, no more trying...
        if( loadExceptionOccur || ( row >= visualTableSize ) ) return( "" );
 
        // do we need a new page?
        try{             
		if( 
			// less than cache lower limit...
			( ( row < startRow ) 
			|| 
			// Beyond cache upper limit...
			( row >= startRow + tableDataCache.size()) ) 
                        // Stop unnecessary loading, because of Jtable readjusting 
                        // its visual table size and redrawing the entire table.
                        && !tableRedraw
                        
		// yes, get new cache...
		){ 
			load( row );        
		}
                
                
            // verify requested cell in cache, or beyond data rows
            if( 
                    // greater than lower limit
                    ( row >= startRow ) 
                    && 
                    // less than upper limit...
                    ( row < ( startRow + tableDataCache.size() ) ) 
            ){
                    tableRedraw = false;

                    Object cellValue = ( ( recordClass ) tableDataCache.get( row-startRow ) ).getValueAt( col );


                    return ( cellValue );
            }
            else{
                    // just show as blank
                    return( "" );            
            }                
        }
        catch( Exception error	){
                            
		// log error
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );
                
                // show error message
		KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );
                  
                return( "" );            
	}   
        

    }  
    
    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) 
    {
  
            try{
                    recordClass record =  ( recordClass ) tableDataCache.get( row - startRow );                    
                    String currentValue = (String)record.getValueAt( col );
                    
                    // 
                    if( !currentValue.equals( value ) ) {  
                        
                        // set field. if value is a String type, it is itself returned.
                        record.setValueAt( col, value.toString() );                        
                        
                        //set changed flag
                        record.setValueAt( record.getRecordLength( ) - 2, CHANGED );     
                        
                        //This new value may cause some other changes.                        
                        if( cellWriter != null ) {
                            if( cellWriter.cellWrite( col, value.toString(), record ) )                            
                                //repaint table
                                fireTableDataChanged(); 
                        }                                                                   

                    }
                    
            }
            catch( Exception error	){

                    // log error
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    // show error message
                    KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
            }               
    }    
    
    //----------------------------------------------------------------------
    /** Return column name at col  */
    public String getColumnName( int col )
    {
        return ( String ) headers.get( col );
    }   

    //----------------------------------------------------------------------
    /** Return the value at the specific row, under the column name  */
    public String getTheFieldValue( int row, String ColumnName )
    throws KExceptionClass
    {
        Iterator columnNames = headers.iterator();      
        int col = -1;
        boolean find = false;
        
        // find the corresponding column index
        while( columnNames.hasNext() ){                
            
            String column_name = ( String ) columnNames.next();             
            col++;
            
            //Add the key value to the last record field
            if( column_name.equals( ColumnName ) ) {
                find = true;
                break;
            }
        }   
        
        if( ! find )
            throw new KExceptionClass( 
			"*** Visual Table error **** \n" +
                        "Column name [ " + ColumnName + " ] is not in the table!"  , null );
        
        return ( String ) getValueAt( row, col );
    }   

    
    //----------------------------------------------------------------------
    /** Save changed browser data to DB.  */
    public void saveChanges( KBrowserDataWriterInterface dataWriter )
    throws KExceptionClass
    {
        if( dataWriter == null )
            throw new KExceptionClass( 
			"*** Cannot save changes **** \n" +
                        "Data writer is invalid!"  , null );
            
        log.log( this, "Write browser changes to DB... " );  
        
        //gather data for writing back to DB 
        java.util.List changeableFieldList = new ArrayList(); 
        java.util.List changeableValueList = new ArrayList(); 
        int columnIndexes[] = new int[headers.size()];     //for keeping changeable column's position in the table model
        int column_index = -1, i = 0;
        
        //get changeable db field names  
        Iterator columnNames = headers.iterator();          
        while( columnNames.hasNext() ){ 
            column_index++;
            
            //field_name = 'tableAlias' + '.' + DB field name
            String field_name = SQLPreprocessor.getFullFieldName( ( String ) columnNames.next() );
            StringTokenizer tokenizer = new StringTokenizer(field_name, ".");
           
            changeableFieldList.add( tokenizer.nextToken() );
            //keep column position in the table model
            columnIndexes[i++] = column_index;
        
        }

        //get writeable db field values as recordClass format. 
        //values are in the same order as field  names in changeableFieldList. 
        Iterator cacheData = tableDataCache.iterator();  
        
        while( cacheData.hasNext() ){ 
            
            recordClass entireRecord = (recordClass)cacheData.next();
            //check changed flag
            if( entireRecord.getValueAt( entireRecord.getRecordLength( )-2 ).equals( CHANGED ) ) {
                //Last extra field is for key field value (OID)
                recordClass changedRecord = new recordClass( changeableFieldList.size() + 1 );
                
                //copy changed values  
                for( int j=0; j<changeableFieldList.size(); j++ ) 
                    changedRecord.setValueAt( j, entireRecord.getValueAt(columnIndexes[j]) );
                    
                //set OID in the last
                changedRecord.setValueAt(
                    changedRecord.getRecordLength()-1, 
                    entireRecord.getValueAt( entireRecord.getRecordLength()-1 ));            
              
                changeableValueList.add( changedRecord );
            }                        
            
        }
        
        //save changes to DB
        dataWriter.save( changeableFieldList, changeableValueList );
    }
        
    
    //----------------------------------------------------------------------   
    /**   Reload the table as the beginning.  */
    public void resetToDefaults() 
    throws KExceptionClass
    {
        SQLPreprocessor.clearAllCustomSettings();
        
        refresh();  
    }
   
    
    //----------------------------------------------------------------------   
    /**   Reload the table as the new setting applied.  */
    public void refresh() 
    throws KExceptionClass
    {
        
        SQLPreprocessor.assembleSQL();             
        visualTableSize = defaultVisualTableSize;
        load( 0 );             
    }    

    
    //----------------------------------------------        
    
    public recordClass getRecord( long OID )
    throws KExceptionClass
    {
        
        recordClass result = null;
        
        Iterator recordIterator = tableDataCache.iterator();        
        while( recordIterator.hasNext() ){
            
            recordClass currentRecord = ( recordClass ) recordIterator.next();
            
            if( KMetaUtilsClass.getIntegralNumericValueFromString( (String) currentRecord.getValueAt( currentRecord.getRecordLength() - 1 ) ) == OID ){
                
                result = currentRecord;
                
                break;
            };
            
        };
        
        
        if( result == null ){
            throw new KExceptionClass(
                "Could not read data in browser\n" +
                "Requested OID is not in cached rows." , null 
            );
        };
        
        return( result );
    }
        
    
    //----------------------------------------------        
    
    public void setVisibleColumnCount( int visibleColumnCountParam ){
    
        visibleColumnCount = visibleColumnCountParam;
    
    }
    
    //----------------------------------------------        
    
}

