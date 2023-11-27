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

package Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo;


// kframework
import Sample1.desktopAccessInterface;
import KFramework30.Widgets.DataBrowser.tableModelClass;
import Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util.dataViewerBrowserInterface;
import KFramework30.Widgets.*;
import KFramework30.Base.*;
import KFramework30.Widgets.TreeView.*;

//jfree
import KFramework30.Widgets.TreeView.KTreeViewerClass.treeNodeClass;
import Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util.customDateRangeDialogClass;
import Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util.fieldSelectorInterface;
import Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util.fieldSelectionChangedInterface;
import java.awt.event.ActionListener;
import org.jfree.data.time.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;


// rtl
import java.util.*;
import java.awt.*;
import java.awt.print.*;
import java.text.*;
import javax.swing.event.*;
import javax.swing.*;



// system
public class LimsDataNavigatorFrameClass 
extends javax.swing.JInternalFrame
implements 
    Sample1.desktopToolbarListenerInterface, 
    KFramework30.Widgets.TreeView.KTreeViewerListenerInterface,
    fieldSelectionChangedInterface,
    KDataBrowserBaseClass.tableToolbarActionPerformedNotificationInterface,
    InternalFrameListener
{
    
        // constants

        public static final int GRAPHIC_VIEWER_MODE_LIMS = 1;
                
        public static final int VIEW_PAST_30 = 0;
        public static final int VIEW_PAST_60 = 1;        
        public static final int VIEW_PAST_90 = 2;   
        public static final int VIEW_PAST_6MONTHS = 3;
        public static final int VIEW_PAST_YEAR = 4;
        public static final int VIEW_ALL = 5;
        public static final int VIEW_CUSTOM = 6;        
        

        // uses
	private KConfigurationClass                     configuration;
	private KLogClass                               log;	
        private desktopAccessInterface           mainToolbar;

	// has - defaulted
        private fieldSelectorInterface                  fieldSelector;
        private java.util.List< KTreeViewerClass.treeNodeClass >        selectedFieldsData;

        private int                                     browserMode;
        private LimsDataNavigatorBrowserClass           dataBrowser;
        
        private int                                     fastFilterStatus = VIEW_ALL;       
        private String                                  customRangeStartDate;
        private String                                  customRangeEndDate;
        private ChartPanel                              chartPanel;
        
        private boolean                                 showTickLabels = false;
        
 
        // ------------------------------------------------------------------

        public ActionListener getDesktopToolbarActionListener() {
            return( null );
        }       
        
        // ------------------------------------------------------------------
        
        public void internalFrameOpened(InternalFrameEvent e) {}
        public void internalFrameClosing(InternalFrameEvent e) {}
        public void internalFrameClosed(InternalFrameEvent e) {}
        public void internalFrameIconified(InternalFrameEvent e) {}
        public void internalFrameDeiconified(InternalFrameEvent e) {}
        public void internalFrameActivated(InternalFrameEvent e) {
                mainToolbar.enableDelete( false );
                mainToolbar.enableNew( false );
                mainToolbar.enableEdit( false );                
                mainToolbar.enableSort( false );
                mainToolbar.enableFilter( false ); 
                mainToolbar.enableRefresh( false );
                mainToolbar.enablePrint( false );
                mainToolbar.enableExport( false );
        }
        public void internalFrameDeactivated(InternalFrameEvent e) {
                mainToolbar.enableDelete( false );
                mainToolbar.enableNew( false );
                mainToolbar.enableEdit( false );
                mainToolbar.enableSort( false );
                mainToolbar.enableFilter( false );  
                mainToolbar.enableRefresh( false );
                mainToolbar.enablePrint( false ); 
                mainToolbar.enableExport( false );
        }        
        
        
        // ------------------------------------------------------------------        
        
    /** Creates new form LimsDataNavigatorFrameClass */
    public LimsDataNavigatorFrameClass(        
        KConfigurationClass configurationParam, KLogClass logParam,
        desktopAccessInterface  mainToolbarParam       
        )         
    throws KExceptionClass
    {     
                        
        // uses
	configuration = configurationParam;
	log = logParam;	
        mainToolbar = mainToolbarParam;       
        
        // has - defaulted
        customRangeStartDate = new String();
        customRangeEndDate = new String();
        selectedFieldsData =  new ArrayList< treeNodeClass >();
        browserMode = dataViewerBrowserInterface.DAILY_VIEW;   
                
        initComponents ();
                       
        //-------------------------------------------------
                
        finalSetup();

        ButtonGroup rawCalcGroup = new ButtonGroup();
        rawCalcGroup.add( calcDataButton );
        rawCalcGroup.add( rawDataButton );                
                        
        setupTable();                        
                                
        fieldSelector = new LimsDataNavigatorTreeSelectorClass(
            configuration, log, mainToolbar.getDesktopsWindow(), 
            fieldSelectorTree, this, this );   
        
          
        addInternalFrameListener( this ); 
                                    

        log.log( this, "constructed successfully." );        
        
    }
    
 //---------------------------------------------------------------------------     
        
    private void setupTable( )
    throws KExceptionClass
    {

        //newButton.setEnabled( true);
        editButton.setEnabled( true);
        //deleteButton.setEnabled( true);
        sortButton.setEnabled( true);
        filterButton.setEnabled( true);  
        printButton.setEnabled( true);   
        exportCSVButton.setEnabled( true);   
       
        fastFilterStatus = VIEW_ALL;             
        dateRangeDropdown.setSelectedItem( "View all" );
                            
        editButton.setEnabled( false );            
        calcDataButton.setEnabled( false );            
        rawDataButton.setEnabled( false );            
               
        reloadData();
    }    
    
    
    //---------------------------------------------------------------------------     
    
    public void finalSetup()
    throws KExceptionClass
    {        
        
        // sample container button
        //newButton.setIcon( new ImageIcon( systemResources.getImage( "/resources/new1.gif" ) ) );
        editButton.setIcon( new javax.swing.ImageIcon(getClass().getResource( "/resources/edit1.gif" ) ) );
        //deleteButton.setIcon( new ImageIcon( systemResources.getImage( "/resources/delete1.gif" ) ) );
        sortButton.setIcon( new javax.swing.ImageIcon(getClass().getResource( "/resources/sort1.gif" ) ) );
        filterButton.setIcon( new javax.swing.ImageIcon(getClass().getResource( "/resources/filter1.gif" ) ) );        
        printButton.setIcon( new javax.swing.ImageIcon(getClass().getResource( "/resources/print1.gif" ) ) );    
        exportCSVButton.setIcon( new javax.swing.ImageIcon(getClass().getResource( "/resources/export1.gif" ) ) );                            
        printGraphButton.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/print1.gif" ) ) );                    
        

        
	// clean jtables
	JTable.setModel(new javax.swing.table.DefaultTableModel () );               
     
    }
    
    //---------------------------------------------------------------------------     
    
    
    // from tree viewer interface, used by inheritor from tree viewer (user object), 
    // not the treeViewer ... not used tree view is R/O
    public void treeViewerEvent(
        final int action,
        final int level,
        final String levelID,
        final KTreeViewerClass.treeNodeClass node) {
    }    
    
   //---------------------------------------------------------------------------         
        
    // from interface used by field selector
    @Override
    public void fieldSelectionChanged( java.util.List< KTreeViewerClass.treeNodeClass > selectedFields ){
        
        selectedFieldsData = selectedFields;        
        reloadData();
    }    

   //---------------------------------------------------------------------------             
    
    public void tableToolbarButtonClickedNotification( String action ){

        try{
        
            if( action.equals( KDataBrowserBaseClass.FILTER_ACTION ) ) drawChart();

            if( action.equals( KDataBrowserBaseClass.SORT_ACTION ) ) drawChart();            
            
        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(), error );
        };               
        
    }    

   //---------------------------------------------------------------------------         
    
    public void reloadData(){
        
        try{
            
                // --------------------------------------------------------
                // remove old table 
                        

                if( dataBrowser != null ){
                                        
                    JTable.removeMouseListener( dataBrowser );
                    //newButton.removeActionListener( dataBrowser );                
                    editButton.removeActionListener( dataBrowser );                
                    //deleteButton.removeActionListener( dataBrowser );
                    sortButton.removeActionListener( dataBrowser );
                    filterButton.removeActionListener( dataBrowser );     
                    printButton.removeActionListener( dataBrowser );   
                    exportCSVButton.removeActionListener( dataBrowser );     
                }
    
                // clean jtables
                JTable.setModel(new javax.swing.table.DefaultTableModel () );               
                                                
                //setup element table                                                                         
                dataBrowser =  new LimsDataNavigatorBrowserClass(         
                    configuration, log, 
                    JTable,                    
                    "",  
                    mainToolbar
                    );                    

                 
                // set selected fields                
                ( (dataViewerBrowserInterface) dataBrowser ).setSelectedFields( selectedFieldsData, browserMode );                                
                
               
                // load all the data...
                dataBrowser.setCacheSize( 5000 );               
                dataBrowser.addButtonActionListener( this );
                
                // filters
                if( fastFilterStatus == VIEW_PAST_30 ){                    
                    dataBrowser.setDefaultCriteria( " ( ref.DATE_DATE > SYSDATE - 30) and ( ref.DATE_DATE < SYSDATE ) " );
                }

                if( fastFilterStatus == VIEW_PAST_60 ){                    
                    dataBrowser.setDefaultCriteria( " ( ref.DATE_DATE > SYSDATE - 60) and ( ref.DATE_DATE < SYSDATE ) " );
                }
                
                if( fastFilterStatus == VIEW_PAST_90 ){                    
                    dataBrowser.setDefaultCriteria( " ( ref.DATE_DATE > SYSDATE - 90) and ( ref.DATE_DATE < SYSDATE ) " );
                }

                if( fastFilterStatus == VIEW_PAST_6MONTHS ){                    
                    dataBrowser.setDefaultCriteria( " ( ref.DATE_DATE > SYSDATE - 182) and ( ref.DATE_DATE < SYSDATE ) " );
                }

                if( fastFilterStatus == VIEW_PAST_YEAR ){                    
                    dataBrowser.setDefaultCriteria( " ( ref.DATE_DATE > SYSDATE - 365) and ( ref.DATE_DATE < SYSDATE ) " );
                }
                
                if( fastFilterStatus == VIEW_ALL ){                                    
                }

                if( fastFilterStatus == VIEW_CUSTOM ){   
                    
                    throw new KExceptionClass( "Not implemented in demo ...", null );
                                        
//                    dataBrowser.setDefaultCriteria( 
//                        " ( " +
//                        " DATE_DATE >= TO_DATE( '" + customRangeStartDate + "' , 'mm/dd/yyyy' ) " +
//                        " AND " +
//                        " DATE_DATE <= TO_DATE( '" + customRangeEndDate + "' , 'mm/dd/yyyy' ) " +
//                        " ) \n"
//                        );                    
                }
                
               
                dataBrowser.initializeTable();                     
                
                //newButton.addActionListener( dataBrowser );                
                editButton.addActionListener( dataBrowser );                
                //deleteButton.addActionListener( dataBrowser );
                sortButton.addActionListener( dataBrowser );
                filterButton.addActionListener( dataBrowser );     
                printButton.addActionListener( dataBrowser );   
                exportCSVButton.addActionListener( dataBrowser );   

                // --------------------------------------------------------                
                drawChart();
                // --------------------------------------------------------                                
                    
        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(), error );
        };               
                        
        
    }
  
        
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    public void drawChart()
    throws KExceptionClass
    {
          
        log.log( this, ">>>>>>>>> DRAWING" );
        
        // remove old chart
        if( chartPanel != null ){ 
                graphPanel.remove( chartPanel );                                                               
                chartPanel = null;
                graphPanel.revalidate();                    
        }
        
        if( selectedFieldsData.size() > 0 )
        {

            // load data from multi browser
            tableModelClass tableDataModel = dataBrowser.tableModel;
            int rowCount = tableDataModel.getRowCount();            

            // formatter
            SimpleDateFormat dateFormatter = null;            

            if( configuration.getField( "databaseType" ).equals( "oracle" ) ){  
                
                dateFormatter = new SimpleDateFormat ("MM/dd/yyyy");             
                
            }else if(configuration.getField( "databaseType" ).equals( "mssql" ) ){   
                
                dateFormatter = new SimpleDateFormat ("yyyy-MM-dd");                                 
                
            }else{
                throw new KExceptionClass( "databaseType not recognized " + configuration.getField( "databaseType" ), null );
            }                    

     

// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++            
// JFREE stuff begins here            
            
            
    // ++++++++++++++++++++++++++++++++++++++++++++++++
    // Make a vector with date units for the date axis
            
            log.log( this, "Figure dates" );
            
            Vector< Day > dateColumn = new Vector< Day >();                                    
            for( int rowIndex = 0; rowIndex < rowCount; rowIndex++ ){

                try{
                                       
                    String dateString = (String) tableDataModel.getValueAt( rowIndex, 0 );                
                    Date rowDate = dateFormatter.parse( dateString );                                                                               
                    
                    dateColumn.add( new Day( rowDate ) );                    
                    log.log( this, "Adding " + dateString );
                    
                }catch( ParseException parseError ){                  
                    throw new KExceptionClass( parseError.toString() , null );
                }                    
            }

                                              
        // +++++++++++++++++++++++++++++            
        //  create a data set, keeps the series  
            
            TimeSeriesCollection mainDataset = new TimeSeriesCollection();
            
        // +++++++++++++++++++++++++++++            
        // make data series                    
                        
            // read data, a serie per column
            log.log( this, "Adding data" );
            for( int columnIndex = 1; columnIndex < tableDataModel.getColumnCount(); columnIndex++ ){
                     
                // make a serie
                TimeSeries dataByDateSerie = new TimeSeries( tableDataModel.getColumnName(columnIndex), Day.class );
                
                // read data
                for( int rowIndex = 0; rowIndex < tableDataModel.getRowCount(); rowIndex++ ){

                    try{

                        // read value
                        String rowValue = (String) tableDataModel.getValueAt( rowIndex, columnIndex );
                        
                        //  parse value string
                        StringTokenizer tokenizer = new StringTokenizer( rowValue );
                        
                        String decodedValue = new String();
                        if( tokenizer.hasMoreTokens() ){ 
                            decodedValue = tokenizer.nextToken();
                         
                            if( decodedValue.equals( "<" ) && tokenizer.hasMoreTokens() ){    
                                
                                String valueToken = tokenizer.nextToken();
                                
                                try{                                    
                                    
                                    decodedValue = "" + ( KMetaUtilsClass.getDecimalNumericValueFromString( valueToken ) / 2 );                                                                    
                                    
                                }catch( Exception error ){                                    
                                    
                                    decodedValue = valueToken;                                                                    
                                }
                            }
                        }
                        
                        // data raw
                        double currentRecordValue = KMetaUtilsClass.getDecimalNumericValueFromString( decodedValue );
                        
                        // make serie
                        dataByDateSerie.add( dateColumn.get(rowIndex), currentRecordValue );
                        
                        log.log( this, "Adding " + currentRecordValue );
                        
                        log.log( this, tableDataModel.getColumnName( columnIndex ) + " --> " + ((String) tableDataModel.getValueAt( rowIndex, columnIndex )).trim() );
                        
                    }catch( Exception parseError ){                                
                        
                        log.log( this, parseError.toString() + "|" + tableDataModel.getColumnName( columnIndex ) + " !!!!!! --> " + ((String) tableDataModel.getValueAt( rowIndex, columnIndex )).trim() );                        
                    }

                
                }

                // add to dataset
                mainDataset.addSeries( dataByDateSerie );
                
            } // for each column


            log.log( this, "Done grabing data, build chart ###############################  " );

        // get a chart

            JFreeChart chart = 
                ChartFactory.createTimeSeriesChart( 
                    "Results by Date", "Date", "Result", mainDataset, true, false, false);
            
        
        // customize
                        
            chart.setAntiAlias( true );                                        
            
            XYPlot plot = chart.getXYPlot();    
            
            plot.getRangeAxis().setTickMarksVisible(true);                                
            plot.getRangeAxis().setTickMarkStroke( new BasicStroke( 5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
            
            plot.getRangeAxis().setAutoTickUnitSelection(true);
            plot.getRangeAxis().setTickLabelsVisible(true);
            
                        
            
            final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            renderer.setShapesVisible( true );  

            if( showTickLabels ){
                renderer.setBaseItemLabelsVisible(true);
                renderer.setBaseItemLabelGenerator( new StandardXYItemLabelGenerator() );
            }
                        
            plot.setRenderer( renderer );
            
            
        //  deploy
            
            chartPanel = new ChartPanel(chart);
            graphPanel.add( chartPanel, BorderLayout.CENTER );

           
        }// if data in browser        

        
        graphPanel.revalidate();    
        

    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        printGraphButton = new javax.swing.JButton();
        statusBar = new javax.swing.JPanel();
        statusBarMessage = new javax.swing.JLabel();
        mainsplitPane = new javax.swing.JSplitPane();
        mainPanel = new javax.swing.JPanel();
        mainTabPanel = new javax.swing.JTabbedPane();
        graphPanel = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        printGraphButton1 = new javax.swing.JButton();
        printGraphButton2 = new javax.swing.JButton();
        showLabels1 = new javax.swing.JToggleButton();
        browserPanel = new javax.swing.JPanel();
        browserToolbar = new javax.swing.JToolBar();
        editButton = new javax.swing.JButton();
        sortButton = new javax.swing.JButton();
        filterButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        exportCSVButton = new javax.swing.JButton();
        browserTableScroller = new javax.swing.JScrollPane();
        JTable = new javax.swing.JTable();
        filterBar = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        String[] dateRanges = {
            "Past 30days" ,
            "Past 60 days" ,
            "Past 90 days" ,
            "Past 6 months" ,
            "Past year",
            "View all",
            "Custom range"
        };

        dateRangeDropdown = new javax.swing.JComboBox( dateRanges );
        calcDataButton = new javax.swing.JToggleButton();
        rawDataButton = new javax.swing.JToggleButton();
        treeViewPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fieldSelectorTree = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        addManyButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        removeManyButton = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        topBar = new javax.swing.JLabel();

        printGraphButton.setFont(new java.awt.Font("Arial", 0, 10));
        printGraphButton.setText("print");
        printGraphButton.setToolTipText("Edit element");
        printGraphButton.setActionCommand("edit");
        printGraphButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printGraphButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        printGraphButton.setMinimumSize(new java.awt.Dimension(35, 35));
        printGraphButton.setPreferredSize(new java.awt.Dimension(35, 35));
        printGraphButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        printGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printGraphButtonActionPerformed(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        statusBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        statusBar.setToolTipText("");
        statusBar.setMinimumSize(new java.awt.Dimension(30, 30));
        statusBar.setName(""); // NOI18N
        statusBar.setLayout(new java.awt.BorderLayout());

        statusBarMessage.setPreferredSize(new java.awt.Dimension(128, 20));
        statusBarMessage.setMinimumSize(new java.awt.Dimension(128, 20));
        statusBarMessage.setText("   ");
        statusBarMessage.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        statusBarMessage.setFont(new java.awt.Font("Arial", 0, 10));
        statusBar.add(statusBarMessage, java.awt.BorderLayout.EAST);

        getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);

        mainsplitPane.setOneTouchExpandable(true);

        mainPanel.setLayout(new java.awt.BorderLayout());

        mainTabPanel.setFont(new java.awt.Font("Dialog", 0, 10));

        graphPanel.setLayout(new java.awt.BorderLayout());

        jToolBar2.setFloatable(false);
        jToolBar2.setMinimumSize(new java.awt.Dimension(68, 41));
        jToolBar2.setPreferredSize(new java.awt.Dimension(500, 41));

        printGraphButton1.setFont(new java.awt.Font("Arial", 0, 10));
        printGraphButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print1.gif"))); // NOI18N
        printGraphButton1.setText("print");
        printGraphButton1.setToolTipText("Edit element");
        printGraphButton1.setActionCommand("edit");
        printGraphButton1.setFocusable(false);
        printGraphButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printGraphButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        printGraphButton1.setMaximumSize(new java.awt.Dimension(80, 40));
        printGraphButton1.setMinimumSize(new java.awt.Dimension(80, 40));
        printGraphButton1.setPreferredSize(new java.awt.Dimension(80, 40));
        printGraphButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        printGraphButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printGraphButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(printGraphButton1);

        printGraphButton2.setFont(new java.awt.Font("Arial", 0, 10));
        printGraphButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/apply1.gif"))); // NOI18N
        printGraphButton2.setText("Excel/CSV");
        printGraphButton2.setToolTipText("Edit element");
        printGraphButton2.setActionCommand("edit");
        printGraphButton2.setFocusable(false);
        printGraphButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printGraphButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        printGraphButton2.setMaximumSize(new java.awt.Dimension(80, 40));
        printGraphButton2.setMinimumSize(new java.awt.Dimension(80, 40));
        printGraphButton2.setPreferredSize(new java.awt.Dimension(80, 40));
        printGraphButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        printGraphButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printGraphButton2ActionPerformed(evt);
            }
        });
        jToolBar2.add(printGraphButton2);

        showLabels1.setFont(new java.awt.Font("Arial", 0, 10));
        showLabels1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/analysis1.gif"))); // NOI18N
        showLabels1.setText("show labels");
        showLabels1.setFocusable(false);
        showLabels1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showLabels1.setMaximumSize(new java.awt.Dimension(80, 40));
        showLabels1.setMinimumSize(new java.awt.Dimension(80, 40));
        showLabels1.setPreferredSize(new java.awt.Dimension(80, 40));
        showLabels1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showLabels1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLabels1ActionPerformed(evt);
            }
        });
        jToolBar2.add(showLabels1);

        graphPanel.add(jToolBar2, java.awt.BorderLayout.NORTH);

        mainTabPanel.addTab("Graphical data", null, graphPanel, "");

        browserPanel.setLayout(new java.awt.BorderLayout());

        browserToolbar.setFloatable(false);
        browserToolbar.setMinimumSize(new java.awt.Dimension(68, 41));
        browserToolbar.setPreferredSize(new java.awt.Dimension(18, 41));

        editButton.setFont(new java.awt.Font("Arial", 0, 10));
        editButton.setText("edit");
        editButton.setToolTipText("Edit element");
        editButton.setEnabled(false);
        editButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editButton.setMinimumSize(new java.awt.Dimension(35, 35));
        editButton.setPreferredSize(new java.awt.Dimension(35, 35));
        editButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        browserToolbar.add(editButton);

        sortButton.setFont(new java.awt.Font("Arial", 0, 10));
        sortButton.setText("sort");
        sortButton.setToolTipText("Sort table");
        sortButton.setEnabled(false);
        sortButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sortButton.setMinimumSize(new java.awt.Dimension(35, 35));
        sortButton.setPreferredSize(new java.awt.Dimension(35, 35));
        sortButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        browserToolbar.add(sortButton);

        filterButton.setFont(new java.awt.Font("Arial", 0, 10));
        filterButton.setText("filter");
        filterButton.setToolTipText("Filter table");
        filterButton.setEnabled(false);
        filterButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filterButton.setMinimumSize(new java.awt.Dimension(35, 35));
        filterButton.setPreferredSize(new java.awt.Dimension(35, 35));
        filterButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        browserToolbar.add(filterButton);

        printButton.setFont(new java.awt.Font("Arial", 0, 10));
        printButton.setText("print");
        printButton.setToolTipText("Print report");
        printButton.setEnabled(false);
        printButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printButton.setMinimumSize(new java.awt.Dimension(35, 35));
        printButton.setPreferredSize(new java.awt.Dimension(35, 35));
        printButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        browserToolbar.add(printButton);

        exportCSVButton.setFont(new java.awt.Font("Arial", 0, 10));
        exportCSVButton.setText("Excel");
        exportCSVButton.setToolTipText("Download to Excel");
        exportCSVButton.setActionCommand("export");
        exportCSVButton.setEnabled(false);
        exportCSVButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportCSVButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        exportCSVButton.setMaximumSize(null);
        exportCSVButton.setMinimumSize(new java.awt.Dimension(35, 35));
        exportCSVButton.setPreferredSize(new java.awt.Dimension(35, 35));
        exportCSVButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        browserToolbar.add(exportCSVButton);

        browserPanel.add(browserToolbar, java.awt.BorderLayout.NORTH);

        JTable.setFont(new java.awt.Font("Arial", 0, 10));
        JTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        JTable.setRowSelectionAllowed(false);
        browserTableScroller.setViewportView(JTable);

        browserPanel.add(browserTableScroller, java.awt.BorderLayout.CENTER);

        mainTabPanel.addTab("Data table", null, browserPanel, "");

        mainPanel.add(mainTabPanel, java.awt.BorderLayout.CENTER);

        filterBar.setPreferredSize(new java.awt.Dimension(18, 30));
        filterBar.setMinimumSize(new java.awt.Dimension(68, 41));
        filterBar.setFloatable(false);

        jLabel1.setText("Date range");
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 10));
        filterBar.add(jLabel1);

        dateRangeDropdown.setFont(new java.awt.Font("Dialog", 0, 10));
        dateRangeDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateRangeDropdownActionPerformed(evt);
            }
        });
        filterBar.add(dateRangeDropdown);

        calcDataButton.setSelected(true);
        calcDataButton.setText("Calculated");
        calcDataButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        calcDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcDataButtonActionPerformed(evt);
            }
        });
        filterBar.add(calcDataButton);

        rawDataButton.setText("Raw");
        rawDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rawDataButtonActionPerformed(evt);
            }
        });
        filterBar.add(rawDataButton);

        mainPanel.add(filterBar, java.awt.BorderLayout.NORTH);

        mainsplitPane.setRightComponent(mainPanel);

        treeViewPane.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(fieldSelectorTree);

        treeViewPane.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setPreferredSize(new java.awt.Dimension(100, 60));

        addManyButton.setFont(new java.awt.Font("Dialog", 0, 10));
        addManyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bottles1_on.gif"))); // NOI18N
        addManyButton.setText("add");
        addManyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addManyButton.setMargin(new java.awt.Insets(5, 1, 5, 1));
        addManyButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        addManyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addManyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addManyButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Dialog", 0, 10));
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/refresh1.gif"))); // NOI18N
        clearButton.setText("remove all");
        clearButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clearButton.setMargin(new java.awt.Insets(5, 1, 5, 1));
        clearButton.setMaximumSize(new java.awt.Dimension(70, 50));
        clearButton.setMinimumSize(new java.awt.Dimension(70, 50));
        clearButton.setPreferredSize(new java.awt.Dimension(70, 50));
        clearButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        clearButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        removeManyButton.setFont(new java.awt.Font("Dialog", 0, 10));
        removeManyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bottles1_off.gif"))); // NOI18N
        removeManyButton.setText("remove");
        removeManyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeManyButton.setMargin(new java.awt.Insets(5, 1, 5, 1));
        removeManyButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        removeManyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeManyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeManyButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(addManyButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(removeManyButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(clearButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(addManyButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(removeManyButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(clearButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        treeViewPane.add(jPanel1, java.awt.BorderLayout.NORTH);

        mainsplitPane.setLeftComponent(treeViewPane);

        getContentPane().add(mainsplitPane, java.awt.BorderLayout.CENTER);

        topPanel.setPreferredSize(new java.awt.Dimension(10, 30));

        topBar.setFont(new java.awt.Font("Bauhaus 93", 3, 18));
        topBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/topBar.jpg"))); // NOI18N
        topBar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        topBar.setMinimumSize(new java.awt.Dimension(100, 55));
        topBar.setPreferredSize(new java.awt.Dimension(100, 55));
        topBar.setVerifyInputWhenFocusTarget(false);

        org.jdesktop.layout.GroupLayout topPanelLayout = new org.jdesktop.layout.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(topBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(topPanelLayout.createSequentialGroup()
                .add(topBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

  private void removeManyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeManyButtonActionPerformed

       try{

            fieldSelector.removeManyFields();

        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
        };               
      
      
  }//GEN-LAST:event_removeManyButtonActionPerformed

  private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed

      
       try{

            fieldSelector.clearFieldSelection();

        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
        };               
      
      
  }//GEN-LAST:event_clearButtonActionPerformed

    
  
  private void addManyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addManyButtonActionPerformed

      
        try{

            fieldSelector.addManyFields();            

        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
        };               
      
      
  }//GEN-LAST:event_addManyButtonActionPerformed

  private void rawDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rawDataButtonActionPerformed

        try{

                browserMode = dataViewerBrowserInterface.HOURLY_VIEW;      
                
                reloadData();
                            

        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
        };               
      
      
  }//GEN-LAST:event_rawDataButtonActionPerformed

  private void calcDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcDataButtonActionPerformed

        try{

                browserMode = dataViewerBrowserInterface.DAILY_VIEW;                
                
                reloadData();
                            

        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
        };               
      
      
  }//GEN-LAST:event_calcDataButtonActionPerformed
      
  
  
    
             
    
    
    
  private void printGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printGraphButtonActionPerformed

      try{

                if( chartPanel == null ) return;

                // get print job and page format
                PrinterJob printerJob = PrinterJob.getPrinterJob();                
                PageFormat pageFormat = printerJob.defaultPage();

                // page format defaults
                pageFormat.setOrientation( PageFormat.LANDSCAPE ); 
                Paper paper = pageFormat.getPaper();
                //paper.setImageableArea( 40, 40, paper.getWidth() - 40 , paper.getHeight() - 40 );
                pageFormat.setPaper( paper );        
                
                // show page format window
                PageFormat finalPageFormat = pageFormat;                
                finalPageFormat = printerJob.pageDialog( pageFormat );                
                if( finalPageFormat == pageFormat ){
                    throw new KExceptionClass( "\n*** PrintJob cancelled ***\n", null );
                }
                
                // prepare to print
                printerJob.setPrintable( chartPanel, finalPageFormat );
            
                // print
                printerJob.print();

        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
        };               
            
      
  }//GEN-LAST:event_printGraphButtonActionPerformed

  
  
  private void dateRangeDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateRangeDropdownActionPerformed

            
      
        try{
    
            if( ((String)dateRangeDropdown.getSelectedItem()).equals( "Past 30days" ) ){                
                
                fastFilterStatus = VIEW_PAST_30;
            }else
            if( ((String)dateRangeDropdown.getSelectedItem()).equals( "Past 60 days" ) ){
                
                fastFilterStatus = VIEW_PAST_60;                
            }else
            if( ((String)dateRangeDropdown.getSelectedItem()).equals( "Past 90 days" ) ){
                
                fastFilterStatus = VIEW_PAST_90;                
            }else

                
            if( ((String)dateRangeDropdown.getSelectedItem()).equals( "Past 6 months" ) ){
                
                fastFilterStatus = VIEW_PAST_6MONTHS;                
            }else
            if( ((String)dateRangeDropdown.getSelectedItem()).equals( "Past year" ) ){
                
                fastFilterStatus = VIEW_PAST_YEAR;                
            }else
            if( ((String)dateRangeDropdown.getSelectedItem()).equals( "View all" ) ){
                
                fastFilterStatus = VIEW_ALL;                                                 
            }else
            if( ((String)dateRangeDropdown.getSelectedItem()).equals( "Custom range" ) ){
                
                fastFilterStatus = VIEW_CUSTOM;      
                                
                // pop for dates
                customDateRangeDialogClass customDateRangeDialog
                    = new customDateRangeDialogClass(
                        configuration, log, mainToolbar.getDesktopsWindow()
                    );

                 customDateRangeDialog.setStartDate( customRangeStartDate );
                 customDateRangeDialog.setEndDate( customRangeEndDate );                    
                
                
                if( customDateRangeDialog.showDialog() == customDateRangeDialog.STATUS_OK ){
                    
                    // oDATE_DATE
                    customRangeStartDate = customDateRangeDialog.getStartDate();
                    customRangeEndDate = customDateRangeDialog.getEndDate();                    
                    
                } else {
                    
                    // it was cancelled
                    fastFilterStatus = VIEW_ALL;                                                                     
                    dateRangeDropdown.setSelectedItem( "View all" );
                }
                
                
            }else                                
            {
                
                throw new KExceptionClass(
                    "*** Could not update date range ***\n" +
                    "Range selected is invalid. [" + ((String)dateRangeDropdown.getSelectedItem()) + "] " , null
                );                
            };


            reloadData();
            
        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
        };               

      
  }//GEN-LAST:event_dateRangeDropdownActionPerformed

  private void printGraphButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printGraphButton1ActionPerformed
      // TODO add your handling code here:
      
      try{

                if( chartPanel == null ) return;

                // get print job and page format
                PrinterJob printerJob = PrinterJob.getPrinterJob();                
                PageFormat pageFormat = printerJob.defaultPage();

                // page format defaults
                pageFormat.setOrientation( PageFormat.LANDSCAPE ); 
                Paper paper = pageFormat.getPaper();
                //paper.setImageableArea( 40, 40, paper.getWidth() - 40 , paper.getHeight() - 40 );
                pageFormat.setPaper( paper );        
                
                // show page format window
                PageFormat finalPageFormat = pageFormat;                
                finalPageFormat = printerJob.pageDialog( pageFormat );                
                if( finalPageFormat == pageFormat ){
                    throw new KExceptionClass( "\n*** PrintJob cancelled ***\n", null );
                }
                
                // prepare to print
                printerJob.setPrintable( chartPanel, finalPageFormat );
            
                // print
                printerJob.print();

        }catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
        };           
      
      
  }//GEN-LAST:event_printGraphButton1ActionPerformed

  private void showLabels1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLabels1ActionPerformed
      // TODO add your handling code here:
      
      try{
          
          if( showTickLabels ) showTickLabels = false; else showTickLabels = true;

          drawChart();
          
    }catch( Exception error ){

            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(),  error );
    };      
      
  }//GEN-LAST:event_showLabels1ActionPerformed

  private void printGraphButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printGraphButton2ActionPerformed
      // TODO add your handling code here:
      
      dataBrowser.exportCSVButtonActionPerformed();
      
  }//GEN-LAST:event_printGraphButton2ActionPerformed

   
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable;
    private javax.swing.JButton addManyButton;
    private javax.swing.JPanel browserPanel;
    private javax.swing.JScrollPane browserTableScroller;
    private javax.swing.JToolBar browserToolbar;
    private javax.swing.JToggleButton calcDataButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox dateRangeDropdown;
    private javax.swing.JButton editButton;
    private javax.swing.JButton exportCSVButton;
    private javax.swing.JTree fieldSelectorTree;
    private javax.swing.JToolBar filterBar;
    private javax.swing.JButton filterButton;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTabbedPane mainTabPanel;
    private javax.swing.JSplitPane mainsplitPane;
    private javax.swing.JButton printButton;
    private javax.swing.JButton printGraphButton;
    private javax.swing.JButton printGraphButton1;
    private javax.swing.JButton printGraphButton2;
    private javax.swing.JToggleButton rawDataButton;
    private javax.swing.JButton removeManyButton;
    private javax.swing.JToggleButton showLabels1;
    private javax.swing.JButton sortButton;
    private javax.swing.JPanel statusBar;
    private javax.swing.JLabel statusBarMessage;
    private javax.swing.JLabel topBar;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel treeViewPane;
    // End of variables declaration//GEN-END:variables

}
