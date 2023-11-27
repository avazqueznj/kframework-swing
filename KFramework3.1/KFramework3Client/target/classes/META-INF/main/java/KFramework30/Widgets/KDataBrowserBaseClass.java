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


package KFramework30.Widgets;


//rtl
import KFramework30.Base.KBusinessObjectClass;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.lang.reflect.Constructor;
import javax.swing.JDialog;


// utilities
import KFramework30.Base.*;
import KFramework30.Printing.*;
import KFramework30.Communication.dbTransactionClientClass;
import KFramework30.Communication.persistentObjectManagerClass;
import KFramework30.Widgets.DataBrowser.TableCellRenderers.*;
import KFramework30.Widgets.DataBrowser.recordClass;
import KFramework30.Widgets.DataBrowser.tableModelClass;
import KFramework30.Widgets.DataBrowser.tableHeaderRendererClass;
import KFramework30.Widgets.DataBrowser.UI.setOrderDialogClass;
import KFramework30.Widgets.DataBrowser.UI.setCriteriaDialogClass;
import KFramework30.Widgets.DataBrowser.cellWriterInterface;
import KFramework30.Widgets.DataBrowser.cellRenderingHookInterface;
import KFramework30.Widgets.DataBrowser.SQLPreprocessorClass;
import KFramework30.Widgets.DataBrowser.KBrowserDataWriterInterface;
import KFramework30.Widgets.KDialogControllerClass.KDialogInterface;
import KFramework30.Widgets.DataBrowser.KTableCellEditorBaseClass;
import KFramework30.Widgets.DataBrowser.KTableCellRendererBaseClass;

  
public class KDataBrowserBaseClass extends Object 
    implements ActionListener, MouseListener{   

    //Constant
    public static final int CUSTOM_CRITERIA_ROW_COUNT = 50;    //For keeping previous custom input criteria   
    
    public static final int BROWSER_COLUMN_TYPE_CHARACTER = 0;
    public static final int BROWSER_COLUMN_TYPE_NUMERICNOFORMAT = 1;
    public static final int BROWSER_COLUMN_TYPE_NUMERIC = 2;
    public static final int BROWSER_COLUMN_TYPE_NUMERIC2 = 3;    
    public static final int BROWSER_COLUMN_TYPE_CURRENCY = 4;    
    public static final int BROWSER_COLUMN_TYPE_DATE = 5;  
    public static final int BROWSER_COLUMN_TYPE_TIME = 6;  
    public static final int BROWSER_COLUMN_TYPE_IMAGE = 7;    
    
    // uses
    protected KConfigurationClass           configuration;    
    protected KLogClass                     log;   
    protected JTable                        visualTable;    //The visual table maps to a DB table.
    
    protected boolean                       listenerRegistered = false;
    protected java.util.List                listenerList;
    
    KBrowserDataWriterInterface             dataWriter; //  if writable, writer      
    Class<? extends KBusinessObjectClass>   pdcObjectClass;
    Class<? extends JDialog>                pdcEditorClass;
    
    // has - defaulted
    private SQLPreprocessorClass        SQLPreprocessor;  //The object handles the data and operations related to DB query.  
    public  tableModelClass             tableModel;     //The table model manages data from the DB table.
    private java.util.List              labelOperationList; //mapping SQL operations and result display components.
    private String[][]                  customCriteriaRowData;    //For keeping previous custom input criteria   
    private java.util.List              customOrderData;    //For keeping previous custom input order
    private java.util.List              tableHeaderRendererList; //list of special table header renderers
    private ListSelectionModel          selectionModel;
    
    private java.util.Map               columnDefaultEditor;    
    private java.util.Map               columnDefaultRender;    //The map contains column name and boolean flag pair for 
                                                                //custom column redender setting. true means to use default render.
                                                                // default value for the flag is false...    
    
    private boolean                     tableLoaded = false;    
    private NumberFormat                currencyFormatter;
    private NumberFormat                decimalFormatter;    
    
    private boolean                     doubleClickEnabled = true;
    private long                        previousTime = 0;    //for mouse double click
    private long                        previousX    = -1;
    private long                        previousY    = -1;

    private java.awt.Window              parentWindow;        //for displaying error message    

    private boolean                      autoRefreshEnabled = true;
    
                    public Window getParentWindow() {
                        return parentWindow;
                    }
          
    
                    // -------------------------------------------------------------
                    // inner class
                    // To report state
                    // -------------------------------------------------------------          
                    public interface tableToolbarActionPerformedNotificationInterface
                    {        
                        public abstract void tableToolbarButtonClickedNotification( String action );
                    }

                    // ----------------------------------------------------------------------
                    // inner class
                    // Variable type for binding JLabel and SQL operation
                    // -------------------------------------------------------------                                                           
                    private class labelOperationClass{

                        // uses
                        Object visualComponent;
                        String operation;
                        int displayType;
                        boolean reflectCustomFilter;

                        public labelOperationClass( 
                            Object  visualComponentParam,
                            String  operationParam,
                            int     displayTypeParam,
                            boolean reflectCustomFilterParam
                            ){

                            // uses                
                            visualComponent = visualComponentParam;

                            operation = operationParam;
                            displayType = displayTypeParam;
                            reflectCustomFilter = reflectCustomFilterParam;
                            // has - defaulted
                        }
                    }


    
    //----------------------------------------------------------------------
    // PUBLIC INTERFACE
             
    /** Creates new KDataBrowserBaseClass */
    public KDataBrowserBaseClass( 
        KConfigurationClass configurationParam, KLogClass logParam, 
	boolean showKeyFieldParam, JTable tableParam, 
        java.awt.Window parentWindowParam,
        Class<? extends KBusinessObjectClass> pdcObjectClassParam, // to delete them
        Class<? extends JDialog> pdcEditorClassParam )  // to open the new and editwindows
    throws KExceptionClass
    {
        // inherits
        super();

        // uses
        configuration = configurationParam;
        log = logParam;
        parentWindow = parentWindowParam;
        visualTable = tableParam;        
        pdcEditorClass = pdcEditorClassParam;
        pdcObjectClass = pdcObjectClassParam;                                       
        
        // has - defaulted 
        SQLPreprocessor = new SQLPreprocessorClass(
                configuration, log,
                showKeyFieldParam, parentWindow );
        
        tableModel = new tableModelClass( 
		configuration, log, 
                parentWindow, SQLPreprocessor,
                visualTable
		);
        
        selectionModel = visualTable.getSelectionModel();
        
        currencyFormatter = NumberFormat.getCurrencyInstance( Locale.getDefault() );                    
        decimalFormatter = new DecimalFormat( "0.00000" );
        
        labelOperationList = new ArrayList();       
        customOrderData = new ArrayList();  
        
        columnDefaultRender = new HashMap();
        
        listenerList = new ArrayList();
        tableHeaderRendererList  = new ArrayList();
         
        //establish mouse click mechanism
        visualTable.addMouseListener( this );             
        visualTable.setRowHeight( 25 );
        
        doubleClickEnabled = true;
        
        log.log( this, "Constructed successfully." );
    }
    
    //----------------------------------------------------------------------        
    
    public void setDoubleClickEnabled( boolean doubleClickEnabledParam ){
        doubleClickEnabled = doubleClickEnabledParam;
    }
    
    //----------------------------------------------------------------------    
    
    public void addButtonActionListener( tableToolbarActionPerformedNotificationInterface listenerParam ){
        
        listenerList.add( listenerParam );
        listenerRegistered = true;        
    }
        

    //-------------------------------------------------------------------------
    public JTable getJTable()
    {
        return visualTable;
    }
    
    //-------------------------------------------------------------------------
    
    public void setCellWriter( cellWriterInterface cellWriterParam ) 
    {   tableModel.setCellWriter( cellWriterParam ); }
    
    //-------------------------------------------------------------------------

    public void setCellDisplayHook( cellRenderingHookInterface cellDisplayHookParam ) 
    {   tableModel.setCellDisplayHook( cellDisplayHookParam ); }
    
    
    //-------------------------------------------------------------------------    
    
    
    
    /** called manually at child class discretion
     */
    public void notifyListeners( String actionParam ){
        
        if( !listenerRegistered ) return;
                
        Iterator listener = listenerList.iterator();
        while( listener.hasNext() ){
            ( ( tableToolbarActionPerformedNotificationInterface ) listener.next() ).tableToolbarButtonClickedNotification( actionParam );            
        }
    }

    //----------------------------------------------------------------------

	/**   Initialize SQL statement
		This method is called only once after constructor, and before any other
		method. 
	*/
	public void initializeSQLQuery(  
		String SQLSelect, String DBTable, String keyFieldParam
	) throws KExceptionClass 
	{
		// pass down...
		SQLPreprocessor.initializeSQLQuery( SQLSelect, DBTable, keyFieldParam ) ;
	}

            
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------        
     
        
    /**   Add the criteria for SQL query.  */   
    public void setDefaultParameters( java.util.List parameters )
    throws KExceptionClass
    {                    
        SQLPreprocessor.setDefaultParameters( parameters );
    }    
    
    /**   Add the criteria for SQL query.  */   
    public java.util.List getDefaultParameters()
    throws KExceptionClass{    
        
        return( SQLPreprocessor.getDefaultParameters() );
    }   
                
    public void bindDefaultParameter1(String parameterName,Object parameterValue)
    {    
        SQLPreprocessor.bindDefaultParameter( parameterName, parameterValue );
    }

     

    //----------------------------------------------------------------------    
    
    // no concatenating operator, let programmer deicide that
    public void setDefaultCriteria( String criteria )
    throws KExceptionClass
    {    
        SQLPreprocessor.setDefaultCriteria( criteria );
    }    
    public String getDefaultCriteria()
    {    
        return( SQLPreprocessor.getDefaultCriteria() );
    }        
           
    public void clearDefaultCriteria(){
        
        SQLPreprocessor.clearDefaultCriteria();                
    }
    
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------    
    
    public void setDefaultOrder(String order)
    {    
        SQLPreprocessor.setDefaultOrder( order );
    }   
    
    public void clearDefaultOrder()
    {    
        SQLPreprocessor.clearDefaultOrder();        
    }  
    
    //----------------------------------------------------------------------                     
    //----------------------------------------------------------------------
    
    /** Following two methods are for temporary custom sorting and filtering */
    public void bindCustomParameter1(String parameterName,Object parameterValue)
    {    
        SQLPreprocessor.bindCustomParameter( parameterName, parameterValue );
    }    
    
    
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------    
    
    // OR is concatenating operator
    public void addCustomCriteria(java.util.List filters) throws KExceptionClass      
    {    
        SQLPreprocessor.addCustomCriteria( filters );
    }  
    
    public void addCustomCriteria( String filter ) throws KExceptionClass      
    {    
        SQLPreprocessor.addCustomCriteria( filter );
    }  
    
    public void clearCustomCriteria()
    {    
        SQLPreprocessor.clearCustomCriteria( );
    }    

    //----------------------------------------------------------------------
    //----------------------------------------------------------------------    
        
    public void setCustomOrder(java.util.List orderList) throws KExceptionClass    
    {    
        SQLPreprocessor.setCustomOrder( orderList );
    }    
       
    
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------    
                
    //----------------------------------------------------------------------
    /** Following methods are table related data operations  */
    
    /** Load the data into table */
    public void initializeTable( )
    throws KExceptionClass
    {      
       
	// set up of table
        SQLPreprocessor.assembleSQL();
        tableModel.load( 0 );
        visualTable.setModel( tableModel );
        visualTable.createDefaultColumnsFromModel();                                  
        
        //initialize column type to CHARACTER.            
        for( int i = 0; i < tableModel.getColumnCount(); i++ ) {
            
                // set default renderer LABEL
                TableColumn theColumn =  visualTable.getColumn( tableModel.getColumnName(i) );                                
	              
                // set renderer default
                KTableCellRendererBaseClass newRenderer = new TextBoxCellRendererClass( BROWSER_COLUMN_TYPE_CHARACTER, tableModel, log );                                                               
                theColumn.setCellRenderer( ( TableCellRenderer ) newRenderer );
                
                // set editor defult
                theColumn.setCellEditor( new TextBoxCellEditorClass( BROWSER_COLUMN_TYPE_CHARACTER, tableModel, log )  );
                
                // set numeric editor
                if( SQLPreprocessor.isFieldNumeric( i ) ){                             
                    TextBoxCellRendererClass renderer = ( TextBoxCellRendererClass )getColumnCellRenderer( tableModel.getColumnName( i ) );
                    renderer.setDataType( BROWSER_COLUMN_TYPE_NUMERIC );                                        
                    log.log( this, " auto setting " + tableModel.getColumnName( i ) + " to numeric" );
                }
                
                                   
        } // next for        

        
        //has to be signed here
        tableLoaded = true;              
        
        //display JLabels and table headers
        calculateOperatios( );
        
        // Install custim column header renderers
        Iterator renderers = tableHeaderRendererList.iterator();
        while( renderers.hasNext() ) {            
            tableHeaderRendererClass renderer = (tableHeaderRendererClass)renderers.next();                        
            TableColumnModel colModel = visualTable.getColumnModel();
            TableColumn col = colModel.getColumn( colModel.getColumnIndex( renderer.getColumnName() ) );
            col.setHeaderRenderer( renderer );             
        }                  
        
        //initialize custom input criteria rowData
        customCriteriaRowData = new String[CUSTOM_CRITERIA_ROW_COUNT][tableModel.getColumnCount()];        
    
    }
    

    //Only one table 'tableAlias' can be written back to DB.
    public void setBrowserSaveListener(  KBrowserDataWriterInterface  dataWriterParam    )
    {
        dataWriter = dataWriterParam;
                       
        log.log( this, "Set browser read and write "  );  
    }
   
    
   //----------------------------------------------------------------------
    
    
    public void stopTableCellEditing(){
        
        KMetaUtilsClass.stopTableCellEditing( visualTable );
    }
    
    public void saveBrowserChanges(  )
    throws KExceptionClass
    {
        
        stopTableCellEditing();
        
        if( dataWriter != null ){
            
            tableModel.saveChanges( dataWriter );    
            
        }
        
        refresh();
    }
    
    //----------------------------------------------------------------------
    
    public boolean isLoaded(){ return( tableLoaded ); }    

    //----------------------------------------------------------------------
    
    /**   Reload the table as the beginning.  */
    public void resetToDefaults() 
    throws KExceptionClass
   {    
        tableModel.resetToDefaults();    
       
        //Force the table goes back to first row on the screen.
        visualTable.scrollRectToVisible( new Rectangle());
        tableModel.fireTableDataChanged(); 
       
        //update JLabels and table headers
        calculateOperatios( ); 
        
        //repaint frame
        parentWindow.repaint();        
    }

    //----------------------------------------------------------------------
    
    
    /**   Reload the table as the new setting applied.  */
    public void refresh() 
    throws KExceptionClass
   {    
        tableModel.refresh();    
       
        //Force the table goes back to first row on the screen.
        visualTable.scrollRectToVisible( new Rectangle());
        tableModel.fireTableDataChanged(); 
        
        //update JLabels or recalculate table header renderers
        calculateOperatios( );        
        
        //repaint frame
        visualTable.repaint();   
                
    }
        
    /**   Reload the table as the new setting applied.  */
    public void softRefresh() 
    throws KExceptionClass
   {    
        // reload cache from this point
        tableModel.load( visualTable.getSelectedRow() );    

        // notify jtable
        tableModel.fireTableDataChanged(); 
        
        //update JLabels or recalculate table header renderers
        calculateOperatios( );        
        
        //repaint frame
        parentWindow.repaint();          
    }

    /**   Reload the table as the new setting applied.  */
    public void displayRefresh() 
    throws KExceptionClass
   {    

        // notify jtable
        tableModel.fireTableDataChanged(); 
                
        //repaint frame
        parentWindow.repaint();          
    }
    
    
    
    //----------------------------------------------------------------------   
    /**     Following methods are for keeping the extra info to support SQL query.  */
    
    /** This method is for retrieving previous custom input criteria. 
        It should be called after initializeTable method called */         
    public String[][] GetCustomCriteriaRowData( )
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'GetCustomCriteriaRowData' method is called in wrong order !"  , null );   
       
       return    customCriteriaRowData;           
    }

    //----------------------------------------------------------------------

    /** This method is for keeping previous custom input criteria. 
        And change the column header color if the column's criteria is set.*/    
    public void setCustomCriteriaRowData(String[][] data)
    {      
        // for keeping previous custom input criteria
        customCriteriaRowData = data;             
        
        //check criteria for each column
        for( int i = 0; i < tableModel.getColumnCount(); i++ ) {
            
            //find the column renderer
            TableColumnModel colModel = visualTable.getColumnModel();                    
            TableColumn column = colModel.getColumn( i ); 
            TableCellRenderer renderer = column.getHeaderRenderer( );                                  

            boolean defaultBehavior = true;
            for( int j = 0; j < CUSTOM_CRITERIA_ROW_COUNT; j++ ) {
                
                // make a renderer if we lacking one
                if( customCriteriaRowData[j][i] != null &&
                    customCriteriaRowData[j][i].trim().length()!=0 ) {                                                

                    if( renderer == null ) {
                        //set customized render
                        renderer = new DefaultTableCellRenderer();
                        column.setHeaderRenderer( renderer );
                        ( (JLabel) renderer ).setHorizontalAlignment( JLabel.CENTER );
                    }
                    
                    //change header color
                    ( (JComponent) renderer ).setForeground( new Color(0,0,200) );    

                    defaultBehavior = false;                                                          
                    break;
                }
            }   //end of for loop for each row
            
            // reset to original color
            if( defaultBehavior && renderer != null ) {
                if( renderer instanceof DefaultTableCellRenderer )
                    //let JTableHeader's defaultRenderer to handle the header
                    column.setHeaderRenderer( null );
                else 
                    //change header color to black
                    ( (JComponent) renderer ).setForeground( new Color(0,0,0) );                  
            }
            
        }   // end of for loop for each column
        
        //repaint frame
        visualTable.getTableHeader().resizeAndRepaint();
    }
               
    
    //----------------------------------------------------------------------

    /** This method is for retrieving previous custom input orders. */
    public java.util.List GetCustomOrderData( )
    {      
       return    customOrderData;           
    }    
 
    
    //----------------------------------------------------------------------
        
    /**   Return the key field value of current selected table row.  */
    public long getSelectedRowKey()
    throws KExceptionClass
    {
        int index = visualTable.getSelectedRow();
        if( index == -1 ) {
            throw new KExceptionClass( 
                        "\nPlease select a record" , null 
                        );
        }
            
        return tableModel.getKeyValue( index );
    } 
    
    
    //--------------------------------------------------------------------------    
    
    
        /**   Return the key field value of current selected table row.  */
    public long getRowOID( int row )
    throws KExceptionClass
    {            
        return tableModel.getKeyValue( row );
    } 


    //----------------------------------------------------------------------
        
    /**   Return the key field values of current multi selected table rows.  */
    public Vector getMultiSelectedRowKeys()
    throws KExceptionClass
    {
        
        int[] indexes = visualTable.getSelectedRows();
        if( indexes.length == 0 ) {
            throw new KExceptionClass( 
                        "\nYou need to chose records!" , null 
                        );
        }
         
        Vector selectedKeys = new Vector();
        for( int i=0; i<indexes.length; i++ ){
            selectedKeys.add( new Long(tableModel.getKeyValue(indexes[i])) );            
        }
        
        return selectedKeys;
    } 
    
    public Vector getMultiSelectedRowKeysAsStrings()
    throws KExceptionClass
    {
        
        int[] indexes = visualTable.getSelectedRows();
        if( indexes.length == 0 ) {
            throw new KExceptionClass( 
                        "\nYou need to chose records!" , null 
                        );
        }
         
        Vector selectedKeys = new Vector();
        for( int i=0; i<indexes.length; i++ ){
            selectedKeys.add( String.valueOf(tableModel.getKeyValue(indexes[i])) );            
        }
        
        return selectedKeys;
    }     

    //----------------------------------------------------------------------
        
    /**   Return the visual header of current selected table column.  */
    public String getSelectedColumnVisualHeader()
    throws KExceptionClass
    {                    
        int index = visualTable.getSelectedColumn();
        if( index == -1 ) {
            throw new KExceptionClass( 
                        "\nYou need to chose records!" , null 
                        );
        }
        
        return ( visualTable.getColumnName(index));
    }
          
    
   //------------------------------------------------------------------------------
    
    /**   Set up label and operation list  
          This method should be called before first call to method calculateOperation() */          
    public void saveSQLOperation( 
        Object visualComponent, 
        String sqlOperation,
        boolean reflectCustomFilter ) 
    throws KExceptionClass{    
        
        saveSQLOperation( visualComponent, sqlOperation, BROWSER_COLUMN_TYPE_NUMERIC, reflectCustomFilter );
    }
    
    
    /**   Set up label and operation list  
          This method should be called before first call to method calculateOperation() */          
    public void saveSQLOperation( 
        Object visualComponent, 
        String sqlOperation,
        int dataType, boolean reflectCustomFilter ) 
    throws KExceptionClass    
   {    
       if( tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'saveLabelOperation' method is called in wrong order !"  , null );

        switch( dataType ){

            case BROWSER_COLUMN_TYPE_CHARACTER:                    
            case BROWSER_COLUMN_TYPE_NUMERICNOFORMAT:
            case BROWSER_COLUMN_TYPE_NUMERIC:
            case BROWSER_COLUMN_TYPE_NUMERIC2:                
            case BROWSER_COLUMN_TYPE_DATE:
            case BROWSER_COLUMN_TYPE_TIME:                
            case BROWSER_COLUMN_TYPE_CURRENCY:                    
                    break;                                    
            default:                    
            throw new KExceptionClass(
                "Could not set label operation data type. Type specified is invalid." , null 
            );
        }
       
        // save operation
        labelOperationList.add( 
            new labelOperationClass( visualComponent, sqlOperation, dataType, reflectCustomFilter ) );        
    }
   
    
   //----------------------------------------------------------------------

    /** Sets the Font for the table 
        This method can be called before or after initializeTable method called */    
    public void setTableFont( Font font ) 
    {
        visualTable.setFont( font );
    }
    
    
   //----------------------------------------------------------------------

    /** Get the renderer of a column by column name 
     */    
    private TableCellRenderer getColumnCellRenderer( String columnName ) 
    throws KExceptionClass            
    {       
        TableColumn theColumn;
        
        try{
            
            theColumn =  visualTable.getColumn( columnName );        
            
        }catch( Exception error ){
            
            String message = new String( 
                            "Error while configuring table.\n" + 
                            "Could not get renderer by column name, column name not found [" 
                            + columnName + "]" );
            
            throw new KExceptionClass( message, error );
        };
                       
        return theColumn.getCellRenderer();
       
    }

    /** Get the renderer of a column by column name 
     */    
    private TableCellEditor getColumnCellEditor( String columnName ) 
    throws KExceptionClass            
    {       
        TableColumn theColumn;
        
        try{
            
            theColumn =  visualTable.getColumn( columnName );        
            
        }catch( Exception error ){
            
            String message = new String( 
                            "Error while configuring table.\n" + 
                            "Could not get renderer by column name, column name not found [" 
                            + columnName + "]" );
            
            throw new KExceptionClass( message, error );
        };
                       
        return theColumn.getCellEditor();
       
    }    
    
    //----------------------------------------------------------------------    
    /** Following methods are for customizing columns drawing  */

    
    //----------------------------------------------------------------------

    
    
    /** Set the Font for the column 
        This method should be called after initializeTable method called */     
    public void adjustColumnFont( String columnName, Font font ) 
    throws KExceptionClass        
    {   
        // Call to method -- ( Jlabel )renderer.setFont( font ) does not work.
        // DefaultTableCellRenderer's implementation of getTableCellRendererComponent executes
        // setFont(table.getFont()). 
        //There is no effect on the column, no matter what font set to the component.        
 
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'setColumnFont' method is called in wrong order !"  , null );        
       
        KTableCellRendererBaseClass renderer = ( KTableCellRendererBaseClass )getColumnCellRenderer( columnName );
        if( renderer != null ) renderer.setColumnFont(font);
        
               
	
    }

        
    /** Sets the width for the column 
        This method should be called after initializeTable method called */ 
    public void adjustColumnWidth( String columnName, int width ) 
    throws KExceptionClass    
   {    
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'setColumnWidth' method is called in wrong order !" , null  );
       
        TableColumn theColumn;
        
        try{                        
        	theColumn =  visualTable.getColumn( columnName );
        	theColumn.setPreferredWidth( width );
        }
        catch( Exception error	){
		// log error
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
		String message;
		message = "*** Colud not display table *** [";
		message += error.toString();
		message += "]\n Column not found in display -> [" + columnName + "]";
		KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
	};   
        

    }
           
    
    
    //----------------------------------------------------------------------

    /** Set the data type for the column 
        This method should be called after initializeTable method called */     
    public void adjustColumnType( String columnName, int type ) 
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'setColumnType' method is called in wrong order !"  , null );               
        
        KTableCellRendererBaseClass renderer = ( KTableCellRendererBaseClass )getColumnCellRenderer( columnName );        
        if( renderer != null && renderer instanceof TextBoxCellRendererClass ) ( (TextBoxCellRendererClass)renderer ).setDataType( type );

        KTableCellEditorBaseClass editor = ( KTableCellEditorBaseClass )getColumnCellEditor( columnName );        
        if( editor != null && editor instanceof TextBoxCellEditorClass  )  ((TextBoxCellEditorClass ) editor).setColumnType(type);
        
        
        log.log( this, " user setting " + columnName + " to " + type );                    
    }    
    

    
    //----------------------------------------------------------------------
    
    /** Sets the justification for the column 
        This method should be called after initializeTable method called */        
    public void adjustColumnJustification( String columnName, int alignment ) 
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'setColumnJustification' method is called in wrong order !"  , null );        
       
        KTableCellRendererBaseClass renderer = ( KTableCellRendererBaseClass )getColumnCellRenderer( columnName );
	if( renderer != null ) renderer.setHorizontalAlignment( alignment );   
        
    }    

    //----------------------------------------------------------------------   
    
    /** Sets the foreground color for the column 
        This method should be called after initializeTable method called */         
    public void adjustColumnForegroundColor( String columnName, Color fgColor )
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'setColumnForegroundColor' method is called in wrong order !"  , null );        
               
        KTableCellRendererBaseClass renderer = ( KTableCellRendererBaseClass )getColumnCellRenderer( columnName );
	if( renderer != null ) renderer.setForeground( fgColor );
        
           
        
    }
        
        
    //----------------------------------------------------------------------   
    
    /** Sets the background color for the column 
        This method should be called after initializeTable method called */       
    public void adjustColumnBackgroundColor( String columnName, Color bgColor ) 
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'setColumnBackgroundColor' method is called in wrong order !"  , null );        
                       
        KTableCellRendererBaseClass renderer = ( KTableCellRendererBaseClass )getColumnCellRenderer( columnName );        
	if( renderer != null ) renderer.setBackground( bgColor );
 
    } 
 
    
    
    //----------------------------------------------------------------------   
    
    /** Build a list to match DB table field name with table alias name and 
     JTable header name. This method should be called before initializeTable.
     Added variable columnDefaultRender is a hash map, which contains header name 
     and Boolean flag pair for custom column render setting. True means to use default render. 
     Default value for the flag is false..*/ 
    
    public void setColumnNames( 
        String aliasName, String fieldName, String headerName ) 
    throws KExceptionClass    
    {  
       if( tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'setColumnNames' method is called in wrong order !"  , null );        
                               
        SQLPreprocessor.setColumnNames( aliasName, fieldName, headerName ); 
    }   
    
         
    
    public void setColumnNames( String aliasName, String fieldName, String headerName, 
            boolean colEditable ) 
            
    throws KExceptionClass
    {
      if( tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'setColumnNames' method is called in wrong order !"  , null );      
       
       
       SQLPreprocessor.setColumnNames( aliasName, fieldName, headerName, colEditable );
       
        
    }
  
    
    //----------------------------------------------------------------------   
 
    /**     Following methods retrieve or display data for components */
     
    /** Gets the table column names 
        This method should be called after initializeTable.*/          
    public void getColumnNames( java.util.List nameList ) 
    throws KExceptionClass     
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'getColumnNames' method is called in wrong order !"  , null );        
              
        if( nameList != null  ) {
            int len = tableModel.getColumnCount( );

            for( int i = 0; i < len; i++ )
                nameList.add( tableModel.getColumnName( i ) );
        }
    }
    
    
    //----------------------------------------------------------------------
    /** Return the table value at the selected row under the column name  */
    public String getSelectedFieldValue( String ColumnName )
    throws KExceptionClass
    {
        int row = visualTable.getSelectedRow();
        if( row == -1 ) {
            throw new KExceptionClass( "\nDebe seleccionar un registro!" , null  );
        }
        
        return tableModel.getTheFieldValue( row, ColumnName );
    }   
    
     //----------------------------------------------------------------------   


    public boolean isColumnNumeric(String columnName ) throws KExceptionClass{
        
            int columnType = getColumnType( columnName );
        
            switch( columnType ){
            case KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERICNOFORMAT:                
            case KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERIC:
            case KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_NUMERIC2:                    
            case KDataBrowserBaseClass.BROWSER_COLUMN_TYPE_CURRENCY:                    
                return true;
            }
            
            return false;
    }
       
    public int getColumnType( String columnName ) 
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'getColumnType' method is called in wrong order !" , null  );        
             
        KTableCellRendererBaseClass renderer = ( KTableCellRendererBaseClass )getColumnCellRenderer( columnName );
	if( renderer != null )   
	    return renderer.getColumnType();
        else
            return BROWSER_COLUMN_TYPE_CHARACTER;
        
        
    }


     //----------------------------------------------------------------------   
     
    /** Gets the current SQL
        This method should be called after initializeTable.*/         
    public void prepareTransactionWithBrowserSQL( dbTransactionClientClass dbTransaction )  
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'getSQL' method is called in wrong order !"  , null );        
			
       SQLPreprocessor.prepareDBTransactionForTable( dbTransaction );
    }


    //----------------------------------------------------------------------   
     
    /** Gets the current SQL transaction with default criteria */         
    public void prepareDefaultDBTransactionForTable( dbTransactionClientClass dbTransaction )  
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'getSQL' method is called in wrong order !"  , null );        
			
       SQLPreprocessor.prepareDBTransactionForTable( dbTransaction );
    }

    //----------------------------------------------------------------------   
     
    /** Gets the current SQL
        This method should be called after initializeTable.*/         
    public void prepareDefaultDBTransactionForTable( dbTransactionClientClass dbTransaction, String orderBy )  
    throws KExceptionClass        
    {
       if( !tableLoaded )
            throw new KExceptionClass( 
			"*** Method calling error **** \n" +
                        "'getSQL' method is called in wrong order !"  , null );        
			
       SQLPreprocessor.prepareDefaultDBTransactionForTable( dbTransaction, orderBy );
    }
    
    
     //----------------------------------------------------------------------       
    
    /** Gets the current SQL
        This method should be called after initializeTable.*/             
    public void prepareCustomFieldsDBTransaction(
        String customFields, dbTransactionClientClass dbTransaction, boolean reflectCustomFilter )        
    throws KExceptionClass        
    {
       
        if( !tableLoaded )
            throw new KExceptionClass( 
                "*** Method calling error **** \n" +
                "'getSQL' method is called in wrong order !"  , null );        
			
        SQLPreprocessor.prepapreCustomFieldsDBTransaction( 
            customFields, dbTransaction, reflectCustomFilter );               
    }
    
    
    //=======================================================================    
    
  public void setColumnRenderer( String columnName, KTableCellRendererBaseClass  renderer )
    throws KExceptionClass   
    {    

        TableColumn column = visualTable.getColumn( columnName );                                               
        
        column.setCellRenderer(renderer);
                
    }

    public void setColumnEditor( String columnName, KTableCellEditorBaseClass  CellEditor )
    throws KExceptionClass   
    {    

        TableColumn column = visualTable.getColumn( columnName ); 
        
                       
        //Check column is editable...
        if( !tableModel.isCellEditable(0, column.getModelIndex() ) )
            throw new KExceptionClass( 
                    "\nColumn [" + column.getModelIndex() + " ] is not editable!" , null  );                 
        
        column.setCellEditor(CellEditor);
    }
    
    
    
    //=======================================================================
    
    /**
     * Save renderers and subscribe operations
     */
    public void adjustHeaderRenderer( tableHeaderRendererClass renderer )
    throws KExceptionClass   
    {    
        //add renderer into list
        tableHeaderRendererList.add( renderer );
        
        // put opers in oper list
        Object[][] operArray = renderer.getOperations();
                
        // 0 -> result
        // 1 -> operation
        // 2 -> format code
        for( int i = 0; i < operArray.length ; i++ ) {
            if( operArray[i][1] != null )               
                saveSQLOperation( operArray[i][0], (String)operArray[i][1], 
                        ((Integer) operArray[i][2]).intValue(), true );        
        }
        
    }
    
    //----------------------------------------------------------------------
    // PRIVATE INTERFACE  
    
    /**   Execute SQL operations and display results in visual components.  */
    private void calculateOperatios( ) 
    throws KExceptionClass    
   {    
        //get sql query
        //display the result
        
        int index = 0;
        String OperationsWithCustomFilter = "";
        String OperationsWithoutCustomFilter = "";
        Iterator labelList = labelOperationList.iterator();                        
        while( labelList.hasNext() ){            
            
            index++;            
            // get next operation
            labelOperationClass currentOperation = ( labelOperationClass ) labelList.next();               
            
            // assemble operations
            if( currentOperation.reflectCustomFilter ) {
                if( OperationsWithCustomFilter.length()>0 ) OperationsWithCustomFilter += ", ";
                OperationsWithCustomFilter += 
                        currentOperation.operation + " AS CUSTOMFILTER" + index;                
            }
            else {
                if( OperationsWithoutCustomFilter.length()>0 ) OperationsWithoutCustomFilter += ", ";
                OperationsWithoutCustomFilter += 
                        currentOperation.operation + " AS NOCUSTOMFILTER" + index;
            }

        }
                   
        Map dbResult = new HashMap();
            
        // execute query with customFilter
        if( OperationsWithCustomFilter.length() > 0 )
            dbResult.putAll( SQLPreprocessor.executeSQLOperation( 
                OperationsWithCustomFilter, true ) );

        // execute query without customFilter
        if( OperationsWithoutCustomFilter.length() > 0 )
            dbResult.putAll( SQLPreprocessor.executeSQLOperation( 
                OperationsWithoutCustomFilter, false ) );
                  
        // show results        
        String result = ""; 
        index = 0;        
        labelList = labelOperationList.iterator();                        
        while( labelList.hasNext() ){            
            
            index++;   
            // get next operation
            labelOperationClass currentOperation = ( labelOperationClass ) labelList.next();  

            if( currentOperation.reflectCustomFilter ) 
                result = ( String )dbResult.get( "CUSTOMFILTER" + index );           
            else
                result = ( String )dbResult.get( "NOCUSTOMFILTER" + index );       
                        
            // formatting
            switch( currentOperation.displayType ){

                case BROWSER_COLUMN_TYPE_DATE:                    

                case BROWSER_COLUMN_TYPE_TIME:                                        

                case BROWSER_COLUMN_TYPE_CHARACTER:                    
                    break;
                    
                    
                                                                                        
                    
                case BROWSER_COLUMN_TYPE_NUMERICNOFORMAT:
                case BROWSER_COLUMN_TYPE_NUMERIC:
                    if( currentOperation.visualComponent instanceof JLabel )
                        ((JLabel)currentOperation.visualComponent).setHorizontalAlignment( SwingConstants.RIGHT );    
                    
                    if( currentOperation.visualComponent instanceof JTextField )
                        ((JTextField)currentOperation.visualComponent).setHorizontalAlignment( JTextField.RIGHT );                      
                    
                    break;                    


                case BROWSER_COLUMN_TYPE_NUMERIC2:                        
                    // get number
                    try{

                        double numericValue = KMetaUtilsClass.getDecimalNumericValueFromString( result );
                        result = decimalFormatter.format( numericValue );

                    }catch( Exception error ){                
                        
                        result = decimalFormatter.format( 0 );
                    }
                    
                    if( currentOperation.visualComponent instanceof JLabel )
                        ((JLabel)currentOperation.visualComponent).setHorizontalAlignment( SwingConstants.RIGHT );       
                    
                    if( currentOperation.visualComponent instanceof JTextField )
                        ((JTextField)currentOperation.visualComponent).setHorizontalAlignment( JTextField.RIGHT );   
                    
                    break;                                                                                           
                    
                case BROWSER_COLUMN_TYPE_CURRENCY:                        
                    // get number
                    try{

                        double numericValue = KMetaUtilsClass.getDecimalNumericValueFromString( result );
                        result = KMetaUtilsClass.toCurrencyString( numericValue );

                    }catch( Exception error ){                
                        
                         result = KMetaUtilsClass.toCurrencyString( 0 );
                    }
                    
                    if( currentOperation.visualComponent instanceof JLabel )
                        ((JLabel)currentOperation.visualComponent).setHorizontalAlignment( SwingConstants.RIGHT );     
                    
                    if( currentOperation.visualComponent instanceof JTextField )
                        ((JTextField)currentOperation.visualComponent).setHorizontalAlignment( JTextField.RIGHT );   
                    
                    break;                                                                                           
                    
            } // end switch   
            
            // show result
            if( currentOperation.visualComponent instanceof JLabel )
                ((JLabel)currentOperation.visualComponent).setText( result );
            
            if( currentOperation.visualComponent instanceof JTextField )
                ((JTextField)currentOperation.visualComponent).setText( result );            
            
        }// end of while loop
        
        //update table header renderers
        Iterator renderers = tableHeaderRendererList.iterator();
        while( renderers.hasNext() ){
            ( ( tableHeaderRendererClass ) renderers.next() ).update( tableModel );            
        }  
        
    }       
    
    
    public AbstractMap evaluateOperation( String SQLformula, boolean applyCustomFilters )
    throws KExceptionClass
    {
        
        HashMap dbResult = new HashMap();

        dbResult.putAll( SQLPreprocessor.executeSQLOperation( SQLformula, applyCustomFilters ) );
        
        return( dbResult );
    }
        
    public long dataBaseRowCount( boolean applyCustomFilters )
    throws KExceptionClass    
    {        
        return( 
            KMetaUtilsClass.getIntegralNumericValueFromString(                 
                ( String ) ( evaluateOperation( " COUNT( ALL 1 ) AS COUNT1 " , applyCustomFilters ) ).get( "COUNT1" ) 
                ) 
            );
    }
    
    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------
    // Generic PDC object operation events dispatching
    // and operations empty implementations
    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------

    static public final String NEW_ACTION = "new";
    static public final String EDIT_ACTION = "edit";
    static public final String DELETE_ACTION = "delete";    
    static public final String SAVE_ACTION = "save";  
    static public final String COPY_ACTION = "copy"; 
    static public final String SORT_ACTION = "sort";
    static public final String FILTER_ACTION = "filter";
    static public final String REFRESH_ACTION = "refresh";    
    static public final String PRINT_ACTION = "print";  
    static public final String CSV_ACTION = "exportcsv";  
    static public final String MOUSE_DBL_CLICK = "double_click";   
            
    public void actionPerformed(java.awt.event.ActionEvent event ){
        
        try{
            
            String command = event.getActionCommand();

            stopTableCellEditing();

            if( command.equals( NEW_ACTION ) )
                newButtonActionPerformed();

           else if( command.equals( EDIT_ACTION ) )
                editButtonActionPerformed();      

            else if( command.equals( DELETE_ACTION ) )
                deleteButtonActionPerformed();    

            else if( command.equals( SAVE_ACTION ) )
                saveButtonActionPerformed();     

            else if( command.equals( COPY_ACTION ) )
                copyButtonActionPerformed();    

            else if( command.equals( SORT_ACTION ) )
                sortButtonActionPerformed();      

            else if( command.equals( FILTER_ACTION ) )
                filterButtonActionPerformed();    

            else if( command.equals( REFRESH_ACTION ) )
                refreshButtonActionPerformed();             

            else if( command.equals( PRINT_ACTION ) )
                printButtonActionPerformed();       
            
            else if( command.equals(CSV_ACTION ) )
                exportcsvButtonActionPerformed();       
            

            notifyListeners( command );
            
            if ( isAutoRefreshEnabled() )
            refresh(); 


        }catch( Exception error ){
                        
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
            
        }
    }    

    public boolean isAutoRefreshEnabled() {
        return autoRefreshEnabled;
    }

    public void setAutoRefreshEnabled(boolean refreshEnabled) {
        autoRefreshEnabled = refreshEnabled;
    }

    

    static public final String MOUSE_RELEASED_ACTION = "mouseReleased";
    static public final String MOUSE_ENTER_ACTION = "mouseEntered";    
    static public final String MOUSE_PRESSED_ACTION = "mousePressed";
    static public final String MOUSE_EXITED_ACTION = "mouseExited";
    static public final String MOUSE_DOUBLECLICK_ACTION = "doubleclick";    
       
    
    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------
    // Default event handlers
    // override in child for implementation
    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------
    
         public void newButtonActionPerformed( ){
            newButtonActionPerformed( new HashMap() );
         }    
         public void newButtonActionPerformed( Map foreingKeyMap ) 
              {       

                  try{
                      
                    KMetaUtilsClass.cursorWait( parentWindow );
                                        
                    JDialog Dialog = getPDCEditorWidow();                                       

                    ((KDialogInterface)Dialog).initializeDialog( KDialogInterface.CREATE_NEW_MODE, -1L, foreingKeyMap );                                                                                           

                    KMetaUtilsClass.cursorNormal( parentWindow );               
                    Dialog.setVisible( true );                    
                    
                }
                catch( KExceptionClass error	){
                    log.log( this, error.longMessage );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                }                                              
                catch( Exception error	){
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                }finally{
                      
                    KMetaUtilsClass.cursorNormal( parentWindow );                    
                }                    


              }    

        //*************************************************
         
        public void editButtonActionPerformed(){
        
                try{
                    
                    editButtonActionPerformed( getSelectedRowKey() ); 
        
                }
                catch( KExceptionClass error	){
                    log.log( this, error.longMessage );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                }                                                                      
                catch( Exception error	){
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                }                    
        
        }                
        public void editButtonActionPerformed( long id ) 
              {       

                try{
                    
                    KMetaUtilsClass.cursorWait( parentWindow );                                      
                    
                    JDialog Dialog = getPDCEditorWidow();

                    ((KDialogInterface)Dialog).initializeDialog( KDialogInterface.EDIT_MODE, id, null );                                                                                           

                    KMetaUtilsClass.cursorNormal( parentWindow );               
                    Dialog.setVisible( true );     
                    
                }
                catch( KExceptionClass error	){
                    log.log( this, error.longMessage );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                }                                                                      
                catch( Exception error	){
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                } 
                finally{                
                    
                    KMetaUtilsClass.cursorNormal( parentWindow );                       
                }                   
                    
              }           
        
        //*************************************************
        
        public void deleteButtonActionPerformed()  { 
            
                try{
                                                              
                    deleteButtonActionPerformed( getSelectedRowKey() ); 
            
                }
                catch( KExceptionClass error	){
                    log.log( this, error.longMessage );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                }                                                                      
                catch( Exception error	){
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                }               
            
        }                
        public void deleteButtonActionPerformed( long id ) 
              {
                  
                 try{
                                                                                 
                    String message = "Are you sure you want to delete?";

                    if( KMetaUtilsClass.showConfirmationMessage( parentWindow,  message ).equals( " OK " ) ){            

                        persistentObjectManagerClass persistentObjectManager 
                            = new persistentObjectManagerClass( configuration, log ); 

                        KMetaUtilsClass.cursorWait( parentWindow );            
                        persistentObjectManager.delete4( id, pdcObjectClass.getName() );
                        KMetaUtilsClass.cursorNormal( parentWindow );              
                    }

                }
                catch( KExceptionClass error	){
                    log.log( this, error.longMessage );		                
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );            
                }                                                                       
                catch( Exception error	){
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );            
                }finally{
                    
                    KMetaUtilsClass.cursorNormal( parentWindow );                                         
                }                      

              }            
    
// ----------------------------------------------------------------------        
    
         public JDialog getPDCEditorWidow() throws KExceptionClass{
             
              try{

                        log.log( this, "Openning editor " + pdcEditorClass.getName() );     

                        // locate the constructor
                        Class[]  editorConstructorRequiredParam   = new Class[] { KConfigurationClass.class, KLogClass.class, java.awt.Window.class };
                        Object[] editorConstructorActualArguments = new Object[] { configuration, log, parentWindow };
                        Constructor editorConstructor;
                        try{                            
                            editorConstructor = pdcEditorClass.getConstructor( editorConstructorRequiredParam );                            
                        }catch(NoSuchMethodException error){
                            throw new KExceptionClass( 
                                    "Object Editor " + pdcEditorClass.getName() + 
                                    " does not provide the required constructor (KConfigurationClass, KLogClass, java.awt.Frame) " 
                                    , error );
                        }
                        
                        return( (JDialog) editorConstructor.newInstance( editorConstructorActualArguments ) );
                }
                catch( Exception error	){

                        // log error
                        log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                        
                        // show error message
                        throw new KExceptionClass(  
                                "Error openning window " + pdcEditorClass.getName() + " :" + error.toString(), error );
                }
             
         }
    
    
    // ----------------------------------------------------------------------    
    
    public void saveButtonActionPerformed() throws KExceptionClass{
                   
            saveBrowserChanges();            
    }
    
   
    /** witht this api, in a selected set of rows in a column the value of the selected field is copied to the range selected  */
    public void copyButtonActionPerformed()
    {
         try{
             
            KMetaUtilsClass.stopTableCellEditing( visualTable );  
            
            //get copy range            
            int col = visualTable.getSelectedColumn();    
            int anchorRow = selectionModel.getAnchorSelectionIndex();           
            int[] rowArray = visualTable.getSelectedRows();  
  
            if( col == -1 ) 
                throw new KExceptionClass( "\nYou need to chose a field" , null  );  
            
            if( visualTable.isCellEditable(anchorRow,col) ) {
                              
                //copy select one to others
                Object value = visualTable.getValueAt( anchorRow, col );     
                
               
                for( int i=0; i<rowArray.length; i++ )                 
                    visualTable.setValueAt(value, rowArray[i], col);      
    

                visualTable.repaint();
            }
        }
        catch( Exception error	){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
        }           
    }
    
    public void sortButtonActionPerformed() throws KExceptionClass{
	

            log.log( this, "Openning setOrderScreen..." );                    
            setOrderDialogClass setOrderScreen =
                    new setOrderDialogClass( configuration, log, parentWindow, this );
                                            			            
            log.log( this, "Openning setOrderScreen completed." );                                  
            
            setOrderScreen.setVisible( true );
                             
    }
    
    public void filterButtonActionPerformed() throws KExceptionClass {
    
            log.log( this, "Openning setCriteriaScreen..." );          

            setCriteriaDialogClass setCriteriaScreen =
                    new setCriteriaDialogClass( configuration, log, parentWindow, this );

            log.log( this, "Openning setCriteriaScreen completed." );                                  

            setCriteriaScreen.setVisible( true );

    }
    
    public void refreshButtonActionPerformed() throws KExceptionClass {

        if ( !isAutoRefreshEnabled() ) refresh(); 
  
    }
    
    public void printButtonActionPerformed() throws KExceptionClass{

        print( "REPORT", "", false );
          
    }

    public void exportcsvButtonActionPerformed() throws KExceptionClass{

        print( "REPORT", "", true );
          
    }    
    
    // ----------------------------------------------------------------------            
    
    public void print( String report_name, String report_owner, boolean exportcsv )
    throws KExceptionClass
    {
        
        try{
            
            

            log.log( this, "Start print job..." );

            // --------------------------------------------------------

            // get a Db transaction  
            dbTransactionClientClass dbTransaction = new dbTransactionClientClass( configuration, log ); 
            prepareTransactionWithBrowserSQL( dbTransaction );
            dbTransaction.executeQuery( 0, 655356 );                

            // --------------------------------------------------------                
            // header section
            KPrintSectionClass headerSection = new KPrintSectionClass(configuration, log, 500, 90 );
            headerSection.setFont( new Font( "arial", Font.PLAIN, 9) ); 
            headerSection.printText(report_owner, 370, 30 );
            headerSection.printText( KMetaUtilsClass.time(), 442, 40 );
            headerSection.setFont( new Font( "arial", Font.PLAIN, 12) ); 
            headerSection.printText(report_name, 212, 50 );

            // --------------------------------------------------------        

            // get a KePrintJob
            KPrintJobClass printJob = 
                new KPrintJobClass( configuration, log ); 
            
            if( exportcsv ) printJob.setOutput( KPrintJobClass.OUTPUT_CSV );

            // setup the DB printer
            KPrintDataTableClass DBPrinter = new KPrintDataTableClass( configuration, log, 
                    dbTransaction, printJob, 0, 655356 );   

            // get header names
            String firstFieldName = "";
            java.util.List headerNames = SQLPreprocessor.getHeaderNames();
            Iterator headerIterator = headerNames.iterator();

            int countFields = 0;
            while( headerIterator.hasNext() ) {    

                countFields++;
                if( countFields > tableModel.getColumnCount() ) break;

                // Get  name
                String readableName = (String) headerIterator.next(); 

                // save name for summary
                if( firstFieldName.length() == 0 ) firstFieldName = readableName;

                String fieldName = SQLPreprocessor.getFieldName1( readableName );


                //get width
                TableColumn theColumn = visualTable.getColumn( readableName );
                int fieldWidth = (int)( theColumn.getPreferredWidth() * 0.65 );

                //get alignment
                int fieldAlignment = KPrintSectionClass.LEFT;
                KTableCellRendererBaseClass renderer = ( KTableCellRendererBaseClass )getColumnCellRenderer( readableName );
                if( renderer != null ) {

                     switch( renderer.getColumnAligment() ){

                        case SwingConstants.LEFT:
                            fieldAlignment = KPrintSectionClass.LEFT; 
                            break;

                        case SwingConstants.CENTER:
                            fieldAlignment = KPrintSectionClass.RIGHT; 
                            break;

                        case SwingConstants.RIGHT:
                            fieldAlignment = KPrintSectionClass.RIGHT; 
                            break;

                        default:
                           fieldAlignment = KPrintSectionClass.LEFT; 
                     }

                }                                       

                // add to dbprinter
                DBPrinter.addField( fieldName, readableName, fieldWidth, fieldAlignment );            

            }

            DBPrinter.addSummary( firstFieldName, KPrintDataTableClass.COUNT, null, "-Records", 0 );
            // --------------------------------------------------------            

            // open print settings
            printSettingDialogClass printSettingDialog =
                    new printSettingDialogClass( configuration, log, parentWindow, DBPrinter );          

            if( printSettingDialog.dialogCloseResult( ) ) {   

                //--------------------------------------------------
                // Print!!!
                //--------------------------------------------------  


                    // start job
                    printJob.startPrintJob();
                    printJob.setDefaultFont( new Font( "arial", Font.PLAIN, 9) );
                    printJob.SetHeader( headerSection, KPrintJobClass.CENTER );
                    printJob.setLeftMargin( 50 );
                    printJob.setBottomMargin( 40);

                    // print SQL data
                    DBPrinter.print();

                    // done!
                    printJob.submitPrintJob();      

            }

            log.log( this, "Print job handler finished." );    
            }catch( Exception error ){

        }finally{
            
            
        }
        
    }
    
    
    //-------------------------------------------------------------------------------
    
    // mouse
    public void mouseClickPerformed( java.awt.event.MouseEvent event ){}            
    
    public void mouseDoubleClickPerformed( java.awt.event.MouseEvent event ) 
    {
        if( doubleClickEnabled == true){
            
            try{    
                
                editButtonActionPerformed();
                notifyListeners( MOUSE_DBL_CLICK );   
                
                if ( isAutoRefreshEnabled() )
                refresh();

                
            }
            catch( Exception error ){
                    // log error
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    // show error message
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );
            }  
        }
        


    }  
    
    
    public void mouseClicked(java.awt.event.MouseEvent event) {    
        
        try {
            
           if( 
                    ( ( System.currentTimeMillis() - previousTime ) < Long.parseLong(configuration.getField( "double_click_timer" ) ) ) 
                    &&
                    ( event.getX() == previousX )
                    &&
                    ( event.getY() == previousY )                
                )
                mouseDoubleClickPerformed( event );    

            previousTime = System.currentTimeMillis();
            previousX = event.getX();
            previousY = event.getY();
            
        }
        catch( Exception error	){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
        }     
        
    }
    
    
    // ----------------------------------------------------------------------    
    // for user to override
    
    public void mouseReleased(java.awt.event.MouseEvent event ) { mouseReleasedPerformed( event ); }        
    
    public void mouseEntered(java.awt.event.MouseEvent event ) { mouseEnteredPerformed( event ); }
    
    public void mousePressed(java.awt.event.MouseEvent event ) { mousePressedPerformed( event ); }    
    
    public void mouseExited(java.awt.event.MouseEvent event ) { mouseExitedPerformed( event ); }       

    public void mouseReleasedPerformed( java.awt.event.MouseEvent event ){}    
    
    public void mouseEnteredPerformed( java.awt.event.MouseEvent event ){}
    
    public void mousePressedPerformed( java.awt.event.MouseEvent event ){}    
    
    public void mouseExitedPerformed( java.awt.event.MouseEvent event ){}


    
    // ---------------------------------------------------------------------------      

    public void setCacheSize( int size ){
        
            tableModel.cacheSize = size;
    }
    
    
    // ---------------------------------------------------------------------------          

    public recordClass getRecord( long OID )
    throws KExceptionClass    
    {
        
        return( tableModel.getRecord( OID ) ); 
    }
    
    // ---------------------------------------------------------------------------

        
    public java.util.List getDataCache(){        
        
        return( tableModel.getTableDataCache() );
    }    

    public java.util.List getTableDataHeaders(){        
        
        return( tableModel.getTableDataHeaders() );
    }    
    
    public String getTableDataAsString(){        
        
        String result = new String();
        
        // put headers
        {
            // for each header
            java.util.List headers = tableModel.getTableDataHeaders();
            Iterator headerIterator = headers.iterator();
            
            while( headerIterator.hasNext() ){
                
                result += headerIterator.next() + "\t";
            }
            
            result += "\n\n";
        }
        
        // ---------------------------------------------------------
        
        // put rows
        {   
            // for each header
            java.util.List data = tableModel.getTableDataCache();
            
            Iterator rowIterator = data.iterator();            
            while( rowIterator.hasNext() ){
                
                recordClass record = ( recordClass ) rowIterator.next();
                
                for( int index = 0; index < record.getRecordLength(); index++ ){
                    
                    result += record.getValueAt( index ) + "\t";
                            
                } // for each cell
                
                result += "\n";
                
            } // for each row
            
            result += "\n\n"; 
            
        }
        
        return( result );
    }    

    // ===========================================================
    
     public java.util.List getAllTableData(){        

        // for each header
        return( tableModel.getTableDataCache() );
                       
     }
    
    
    public String getTableDataAsHtmlTable(){        
        
        String result = new String();
        
        result = "<table border=2 >\n";
        
        // ---------------------------------------------------------        
        
            // put headers
            {
                // for each header
                java.util.List headers = tableModel.getTableDataHeaders();
                Iterator headerIterator = headers.iterator();

                result += "<tr>\n";
                while( headerIterator.hasNext() ){

                    result += "<td>";
                    result += "<b>" + headerIterator.next() + "</b>";
                }
                result += "</tr>\n";                
               
            }
        
        // ---------------------------------------------------------
        
            // put rows
            {   
                // for each header
                java.util.List data = tableModel.getTableDataCache();


                Iterator rowIterator = data.iterator();            
                while( rowIterator.hasNext() ){

                    recordClass record = ( recordClass ) rowIterator.next();

                    result += "<tr>";                    
                    for( int index = 0; index < record.getRecordLength(); index++ ){
                                                
                        result += "<td>";                        
                        result += record.getValueAt( index );
                        
                        if( index + 1 >= tableModel.getColumnCount() ) break;

                    } // for each cell
                    result += "</tr>\n";                    

                } // for each row

            }
        
        // ---------------------------------------------------------        
        
        result += "</table>";
        
        return( result );
    }    
    
    
    // ---------------------------------------------------------------------------              
    
    public void markChanged( long OID, String changedParam  )
    throws KExceptionClass        
    {
      
        recordClass record = getRecord( OID );
        
        record.setValueAt( record.getRecordLength() - 2, changedParam );
        
    }
    
    // ---------------------------------------------------------------------------          

    public void setVisibleColumnCount( int visibleColumnCountParam ){
        tableModel.setVisibleColumnCount(visibleColumnCountParam);
    }    
    
    // ---------------------------------------------------------------------------                  
    
    public void setRowsHeight( int height ){
     
        visualTable.setRowHeight( height );
    }    
    
    // ---------------------------------------------------------------------------                      
}

