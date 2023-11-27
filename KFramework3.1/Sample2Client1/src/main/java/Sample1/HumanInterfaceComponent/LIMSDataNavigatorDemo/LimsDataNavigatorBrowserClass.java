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

//rtl
import KFramework30.Widgets.DataBrowser.recordClass;
import KFramework30.Widgets.DataBrowser.tableHeaderRendererClass;
import KFramework30.Widgets.DataBrowser.UI.setOrderDialogClass;
import KFramework30.Widgets.DataBrowser.cellRenderingHookInterface;
import Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util.dataViewerBrowserInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.util.*;

// utilities
import KFramework30.Base.*;
import KFramework30.Communication.*;
import KFramework30.Printing.*;
import KFramework30.Widgets.*;
import KFramework30.Widgets.TreeView.KTreeViewerClass.treeNodeClass;
import Sample1.desktopAccessInterface;

// system


public class LimsDataNavigatorBrowserClass 
extends KDataBrowserBaseClass 
implements dataViewerBrowserInterface, cellRenderingHookInterface
{   
                                        
     //Constants
                                        
    // uses                       	   
    private Sample1.desktopAccessInterface           desktopAccess;
    public java.util.List< treeNodeClass >                  selectedFields;
    
    // has   
    private String                  CSVreportHeaders = "DATE , ";


    
    
        public void cellEditHook(int row, int col, boolean isSelected, Component editor, String ColumnName, String value, recordClass record, KLogClass log) throws KExceptionClass {
              // not customizing the editor
        }    
        
        // dynamic cell rendering
        public void cellRenderingHook(
                int row, int col, boolean isSelected, 
                Component renderer, String ColumnName, 
                String value, recordClass record, KLogClass log) throws KExceptionClass {

            
            if( value.substring( 0, 1 ).equals( "<" ) ){
                
                StringTokenizer tokenizer = new StringTokenizer( value, " " );
                
                int tokenCount =  tokenizer.countTokens();
                
                // ------------------------------------------------
                
                if( tokenCount == 1 ){
                    
                    // pass <
                    value = tokenizer.nextToken();
                    
                  
                }
                
                // ------------------------------------------------                
                
                if( tokenCount == 2 ){
                    
                    // pass <                    
                    value = tokenizer.nextToken();                    
                    String numericValueString = tokenizer.nextToken();
                                        
                    try{
                        
                        value += " " + ( KMetaUtilsClass.getCurrencyNumericValueFromString( numericValueString ) / 2 );
                        
                    }catch( Exception error ){
                        
                        value += " " + numericValueString;
                    }
                    
               
                }

                // ------------------------------------------------                
                
                if( tokenCount >= 3 ){

                    // pass <                    
                    value = tokenizer.nextToken();                    
                    String numericValueString = tokenizer.nextToken();
                                        
                    try{
                        
                        value += " " + ( KMetaUtilsClass.getDecimalNumericValueFromString( numericValueString )  / 2 );
                        
                    }catch( Exception error ){
                        
                        value += " " + numericValueString;
                        
                    }
                    
                    // =============================
                        
                    while( tokenizer.hasMoreTokens() ){
                        
                        value += " " + tokenizer.nextToken();
                    }
                    
                   
                }

                
                // ------------------------------------------------                            
                
            }
            
            
           
        }
    
    
    /** Creates new dailyMeasureBrowserClass */
    public LimsDataNavigatorBrowserClass(
        KConfigurationClass configurationParam,KLogClass logParam,
        JTable tableParam,
        String id,
        desktopAccessInterface desktopAccessParam) throws KExceptionClass
    {
        
            // inherits
            super(
                    configurationParam, logParam,
                    false, tableParam, desktopAccessParam.getDesktopsWindow(), null, null
            );  
            
            // uses            
            desktopAccess = desktopAccessParam;
            
            
	    // has
            selectedFields = new Vector();
            
    }
    
    public String makeDatabaseColumnName( String fieldName ){
        
        String result = "";
        
        for( int index = 0; index < fieldName.length(); index++ ){
            
            char currentChar = fieldName.charAt( index );
            
            if( Character.isLetter( currentChar ) ){
                
                result += currentChar;
                
            }else
            if( Character.isDigit( currentChar ) ){
                
                result += currentChar;
                
            }else                
            if( Character.isWhitespace( currentChar ) ){
                
                result += currentChar;
                
            }else
            if( 
                (currentChar == ',' )||
                (currentChar == '*' )||                  
                (currentChar == '-' )||                  
                
                (currentChar == '(' )||                
                (currentChar == ')' )

            ){
                
                result += currentChar;
                
            }else{
                
                result += " ";
            }
                
            
        } // for chars
        
        if( result.length() > 20 ) result = result.substring( 0, 20 );
        
        return( result );
    }
    
    public void initializeTable()
    throws KExceptionClass
    {	    
                    
            if( selectedFields.size() == 0 ) return;
                        
            // find reference                
            String SQLSelectColumns = "";
            String SQLFromTables = "";
            String SQLWhereClause = "";                  
               
    //************            
    //A            
            // set reference table for date axis

            if( configuration.getField( "databaseType" ).equals( "oracle" ) ){                    
                
                    SQLSelectColumns =       
                            " ref.DATE_DATE AS KEYX, \n" +            
                            " to_char( ref.DATE_DATE, 'mm/dd/yyyy HH24:MI' ) as DATE_DATE, \n";                  

                    SQLFromTables =  
                         " ( \n" +

                            " select distinct  "
                            + "to_date( to_char( nvl( SAMPLE_SAMPLING_DATE, sysdate ) , 'mm/dd/yyyy' )"
                            + "||' '||"
                            + "to_char( SAMPLE_SAMPLING_TIME, 'HH24:MI' ), 'mm/dd/yyyy HH24:MI' ) "
                  
                            + "as DATE_DATE " +

                            " from PLANTREPORT_LIMS_IMPORT " +

                        " ) ref \n";

                    
                    
            }else if(configuration.getField( "databaseType" ).equals( "mssql" ) ){      
                
                    //    Mon DD YYYY HH:MIAM (or PM) Default SELECT CONVERT(VARCHAR(20), GETDATE(), 100) 
                    //    DD Mon YYYY 1 - SELECT CONVERT(VARCHAR(11), GETDATE(), 106) AS [DD MON YYYY]              

                                
                    SQLSelectColumns =       
                            " ref.DATE_DATE AS KEYX, \n" +            
                            //" to_char( ref.DATE_DATE, 'mm/dd/yyyy HH24:MI' ) as DATE_DATE, \n";                  
                            " CONVERT( VARCHAR(30), ref.DATE_DATE, 120 ) as DATE_DATE, \n";                  
                    

                    SQLFromTables =  
                         " ( \n" +

                            " select "
                            
                                + " distinct SAMPLE_SAMPLING_DATE as DATE_DATE " +

                            " from PLANTREPORT_LIMS_IMPORT  where SAMPLE_SAMPLING_DATE is not null " +

                        " ) ref \n";

                      
                
                
            }else{
                
                throw new KExceptionClass( "databaseType not recognized " + configuration.getField( "databaseType" ), null );
            }
                       
            // build columns 

            int serial = 0;
            Iterator< treeNodeClass > selectedFieldDataIterator = selectedFields.iterator();                  
            while( selectedFieldDataIterator.hasNext() ) {

                serial++;
                
                // get current fields data
                AbstractMap selectedFieldData = selectedFieldDataIterator.next().rawData;

                // figure column name for oracle
                String rawfieldName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase();
                
                String fieldName = "\"" + makeDatabaseColumnName( rawfieldName ) + serial + "\"";                                
                String fieldName_q = "\"" + makeDatabaseColumnName( rawfieldName ) + serial + "_q\"";                                
                String fieldName_val = "\"" + makeDatabaseColumnName( rawfieldName ) + serial + "_val\"";                
                String fieldName_qual = "\"" + makeDatabaseColumnName( rawfieldName ) + serial + "_qual\"";                                

            // SELECT
                
                // table columns
                if( configuration.getField( "databaseType" ).equals( "oracle" ) ){                      
                
                        SQLSelectColumns += 
                            " col" + serial  + "." + fieldName_q    + "||' '||" +
                            " col" + serial  + "." + fieldName_val  + "||' '||" +
                            " col" + serial  + "." + fieldName_qual +                
                            " as " + fieldName;
                        
                }else if(configuration.getField( "databaseType" ).equals( "mssql" ) ){                              

                        SQLSelectColumns += 
                            " col" + serial  + "." + fieldName_q    + "+' '+" +
                            " col" + serial  + "." + fieldName_val  + "+' '+" +
                            " col" + serial  + "." + fieldName_qual +                
                            " as " + fieldName;                                        
                }else{
                    throw new KExceptionClass( "databaseType not recognized " + configuration.getField( "databaseType" ), null );
                }                    
                        
                
                if( selectedFieldDataIterator.hasNext() ) SQLSelectColumns += ", \n"; 

            // FROM                

//************                
//B
                if( configuration.getField( "databaseType" ).equals( "oracle" ) ){                                    
                
                SQLFromTables += 
                        " left outer join ( \n" +

                            " select \n" +


                                    " to_date( to_char( SAMPLE_SAMPLING_DATE, 'mm/dd/yyyy ' )||to_char( SAMPLE_SAMPLING_TIME, 'HH24:MI' ), 'mm/dd/yyyy HH24:MI' ) as KEY3, " +


                                    "  ANALYSIS_RESULT_Q as " + fieldName_q + ", \n" +                                
                                    "  ANALYSIS_RESULT_RESULT as " + fieldName_val + ", \n" +                                
                                    "  ANALYSIS_RESULT_QUAL as " + fieldName_qual + ", \n" +                                                                    
                                    
                                    "  ANALYSIS_RESULT_RESULT_NUMVAL as " + fieldName + " \n" +                                


                            " from \n" +
                            

                                    "	PLANTREPORT_LIMS_IMPORT  \n" +

                            "  where \n" +                                                     
                                            // filter select fields
                                            " ( " +

                                            " client_name = :client_name" + serial + " " +
                                            " and \n" +

                                            " sample_client_id = :sample_client_id" + serial +  " " +
                                            " and \n" +

                                            " analysis_name = :analysis_name" + serial +  " " +
                                            
                                            " ) \n" +
                                                               
                        " ) " +

                            // alias for table
                            " col" + serial + " ON  ref.DATE_DATE = col" + serial + ".KEY3  "   
                        ;

                }else if(configuration.getField( "databaseType" ).equals( "mssql" ) ){      

                    // from clause to get columns
                    SQLFromTables += 
                            " left join ( \n" +

                                " select \n" +


                                        "  SAMPLE_SAMPLING_DATE  as KEY3, " +


                                        "  isnull( cast( ANALYSIS_RESULT_Q as varchar ), '' ) as " + fieldName_q + ", \n" +                                
                                        "  isnull( cast( ANALYSIS_RESULT_RESULT as varchar ), '' ) as " + fieldName_val + ", \n" +                                
                                        "  isnull( cast( ANALYSIS_RESULT_QUAL as varchar ), '' ) as " + fieldName_qual + ", \n" +                                                                    

                                        "  ANALYSIS_RESULT_RESULT_NUMVAL as " + fieldName + " \n" +                                


                                " from \n" +


                                        "	PLANTREPORT_LIMS_IMPORT  \n" +

                                "  where \n" +


    // change to parmas!!!!                            
                                                // filter select fields
                                                " ( " +

                                                " client_name = ? " +
                                                " and \n" +

                                                " sample_client_id = ? " +
                                                " and \n" +

                                                " analysis_name = ? " +

                                                " ) \n" +

                            " ) " +

                            // alias for table
                            " col" + serial + " ON ref.DATE_DATE = col" + serial + ".KEY3  "    
                            
                            ;                    
                    
                }else{
                    
                    throw new KExceptionClass( "databaseType not recognized " + configuration.getField( "databaseType" ), null );
                }
                

                // ----------------------------------------------------------------------                
                                       
                bindDefaultParameter1( ":client_name" + serial, (String) selectedFieldData.get( "CLIENT_NAME" ) );
                bindDefaultParameter1( ":sample_client_id" + serial, (String) selectedFieldData.get( "SAMPLE_CLIENT_ID" ) );
                bindDefaultParameter1( ":analysis_name" + serial, (String) selectedFieldData.get( "ANALYSIS_NAME" ) );
                
                // ----------------------------------------------------------------------


            } // while fields
            

            // ===============================================                        
            // filter not all nullez

                SQLWhereClause += "  ( \n";

                serial = 0;
                selectedFieldDataIterator = selectedFields.iterator();                  
                while( selectedFieldDataIterator.hasNext() ) {

                    serial++;                

                    //=====================================================================                
                    // get data
                    AbstractMap selectedFieldData = selectedFieldDataIterator.next().rawData;                                    

                    // figure name
                    String fieldName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase();                
                    fieldName = makeDatabaseColumnName( fieldName ) + serial;
                    String displayName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase() + " :" + serial;
                    //=====================================================================

                    //**
                    SQLWhereClause += "( \"" + fieldName + "\" is not null)";

                    if( selectedFieldDataIterator.hasNext() ) SQLWhereClause += " or \n"; 
                }


                SQLWhereClause += " ) \n";                    

            // ===============================================                                            
            
            
            super.initializeSQLQuery(  
                    SQLSelectColumns,
                    SQLFromTables ,
                    "KEYX" );               

                    
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                
            setColumnNames( "ref", "DATE_DATE", "DATE" );  
            
            if( getDefaultCriteria().equals(  "" ) )
                setDefaultCriteria( SQLWhereClause );                                     
            else
                setDefaultCriteria( getDefaultCriteria() + " AND " + SQLWhereClause );                                                     
            
            setTableFont( new Font( "arial", Font.PLAIN, 10 ) );                        
            setDefaultOrder( "ref.DATE_DATE DESC " );
            
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++            
                                                                     
            // date column header            
            tableHeaderRendererClass dateRenderer = new tableHeaderRendererClass( 4, "DATE" );
            dateRenderer.setLabelAt( "", 0 );
            dateRenderer.setLabelAt( "Client", 1 );
            dateRenderer.setLabelAt( "Description", 2 );
            dateRenderer.setLabelAt( "Analysis", 3 );                                
            adjustHeaderRenderer( dateRenderer );                                        
            
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++            
// set display name for columns
            
            serial = 0;
            selectedFieldDataIterator = selectedFields.iterator();                  
            while( selectedFieldDataIterator.hasNext() ) {

                serial++;                
                
                //=====================================================================                
                // get data
                AbstractMap selectedFieldData = selectedFieldDataIterator.next().rawData;                                    
                
                // figure name
                String fieldName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase();                
                fieldName = makeDatabaseColumnName( fieldName ) + serial;
                String fakeName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase() + " :" + serial;
                //=====================================================================
                
                
                // set column name
                setColumnNames( "col" + serial, fieldName , fakeName  );                                            
                
                // date column header       
                tableHeaderRendererClass headerRenderer = new tableHeaderRendererClass( 4, fakeName ); // fake name
                headerRenderer.setLabelAt( "", 0 );
                headerRenderer.setLabelAt( ((String) selectedFieldData.get( "CLIENT_NAME" )), 1 );
                headerRenderer.setLabelAt( ((String) selectedFieldData.get( "SAMPLE_CLIENT_ID" )), 2 );
                headerRenderer.setLabelAt( ((String) selectedFieldData.get( "ANALYSIS_NAME" )), 3 );                                
                adjustHeaderRenderer( headerRenderer );                                        

                String currentHeader =
                    ((String) selectedFieldData.get( "CLIENT_NAME" )) + " " +
                    ((String) selectedFieldData.get( "SAMPLE_CLIENT_ID" )) + " " +
                    ((String) selectedFieldData.get( "ANALYSIS_NAME" ));
                               
                CSVreportHeaders += currentHeader.replace( ',', '-' ) + ",";
            }
            
            
            
            
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++                        
            
            super.initializeTable(); 
            
            adjustColumnWidth( "DATE", 130 );          
            adjustColumnType( "DATE", BROWSER_COLUMN_TYPE_DATE );            
            
            // set colors and widths
            serial = 0;
            boolean colorized = true;
            selectedFieldDataIterator = selectedFields.iterator();                  
            while( selectedFieldDataIterator.hasNext() ) {
                   
                serial++;
                
                // ========================================================================
                // get data and name
                AbstractMap selectedFieldData = selectedFieldDataIterator.next().rawData;                                   
                String displayName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase() + " :" + serial;                
                // ========================================================================
                
                adjustColumnWidth( displayName, 130 );   
                
                // set column type
                adjustColumnType( displayName, BROWSER_COLUMN_TYPE_NUMERIC2 );
                
                
                if( colorized ){
                                     

                    adjustColumnBackgroundColor( displayName, new Color( 200, 255, 200 ) );
                    colorized = false;                    
                    
                }else{                    
                    
                    colorized = true;
                };
                
            }

    }            
    
    // ----------------------------------------------------------------------    

    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------
    // Event handling
    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------

    

    
    public void actionPerformed(java.awt.event.ActionEvent event ){
        try {
            String command = event.getActionCommand();
            
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

    //---------------------------------------------------
    // customization ?        
            
            else if( command.equals( "export" ) )
                exportCSVButtonActionPerformed();

            
            notifyListeners( command );
            
        } catch (KExceptionClass error) {
                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException(  desktopAccess.getDesktopsWindow(), error );
        }
    }    
    

    
    public void mouseDoubleClickPerformed( java.awt.event.MouseEvent event ) { 
    
        
        try {
   
            event.consume();            
                            
            //edit
            editButtonActionPerformed();
   
        }
        catch( Exception error	){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( desktopAccess.getDesktopsWindow(), error );
        }     
                        
    }   
    

      // ---------------------------------------------------------------------------
    
    
    public void newButtonActionPerformed()
    {
    
    }
  

    
    //--------------------------------
   
    public void editButtonActionPerformed()
    {

        
        try{


                softRefresh();

        }
        catch( Exception error	){


                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException(  desktopAccess.getDesktopsWindow(), error );
        }             

    }

    
    
  //--------------------------------------------------------------------
    
  private long getSelectedCellId()
  throws KExceptionClass
  {
      
      long result = 0;
               
        return( result );
    }


    
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    
  public void deleteButtonActionPerformed() 
  {
      
  }
    

  // ---------------------------------------------------------------------------  

  public void sortButtonActionPerformed() 
  {

	try{

            // ---------------------------------
            // set order dialog
            
            log.log( this, "Openning setOrderScreen..." );          
          
            setOrderDialogClass setOrderScreen =
                    new setOrderDialogClass( configuration, log, desktopAccess.getDesktopsWindow(), this );                                            			
            
            log.log( this, "Openning setOrderScreen completed." );                                  
            
            setOrderScreen.setVisible( true );
                        
            // set order dialog
            // --------------------------------------------------------

        }
        catch( Exception error	){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( desktopAccess.getDesktopsWindow(), error );
        }         

  }
  
  
  
  // ---------------------------------------------------------------------------
  
  public void exportCSVButtonActionPerformed(){

           
           try{               
              
		log.log( this, "Start print job..." );
                
                // --------------------------------------------------------
                
                // get a Db transaction  
                dbTransactionClientClass dbTransaction = new dbTransactionClientClass( configuration, log );                                 
                prepareTransactionWithBrowserSQL(dbTransaction);                
                dbTransaction.executeQuery( 0, 655356 );
                
                // --------------------------------------------------------

                // get a KePrintJob
                KPrintJobClass printJob = 
                    new KPrintJobClass( configuration, log );  
                    
                printJob.usePrintingDefaults();                
                printJob.setOutput( KPrintJobClass.OUTPUT_CSV );                   
                                
                printJob.startPrintJob();	    
                
                printJob.setDefaultFont( new Font("Arial", Font.PLAIN, 8) );                                        
                printJob.setTopMargin( 10 );                    
                printJob.setLeftMargin( 25 );
                printJob.setBottomMargin( 20 );
                
                               
                // header section 

                KPrintSectionClass headerSection = new KPrintSectionClass(configuration, log, 500, 90 );

                // headers
                headerSection.setFont( new Font( "arial", Font.PLAIN, 9 ) );                
                headerSection.printText( "Mount Holly Municipal Utilities Authority ", 370, 30 );
                headerSection.printText( KMetaUtilsClass.time(), 442, 40 );
                
                headerSection.setFont( new Font( "arial", Font.PLAIN, 12) ); 
                headerSection.printText( "REPORT", 212, 50 );
                
                headerSection.printText( CSVreportHeaders , 212, 60 );                                
                
                headerSection.printLine( 212, 74, 292, 74);
                
                printJob.SetHeader( headerSection, KPrintJobClass.CENTER );

                
                // ---------------------------------------------------------------------------------------

                
	        // setup the DB printer
                KPrintDataTableClass DBPrinter = new KPrintDataTableClass( 
			   configuration, log, 
                           dbTransaction, printJob, 0, 655356 );

                // add static field
                DBPrinter.addField( "DATE_DATE", "DATE", 40 );                
                DBPrinter.setPrintingField( "DATE" );
                
                DBPrinter.setHeadersMode( KPrintDataTableClass.HEADER_TYPE_NOHEADER );     
                
                // add dynamic
                int serial = 0;
                Iterator selectedFieldDataIterator = selectedFields.iterator();                  
                while( selectedFieldDataIterator.hasNext() ) {

                    serial++;                

                    AbstractMap selectedFieldData = ( AbstractMap ) selectedFieldDataIterator.next();                                    

                    String fieldName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase();                
                    fieldName = makeDatabaseColumnName( fieldName ) + serial;

                    String fakeName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase() + " :" + serial;
                    
                    DBPrinter.addField( 
                        fieldName, fakeName, 40 
                    );
                    
                    
                    DBPrinter.setPrintingField( fakeName );                                        
                }
                
                
                // DBPrinter.addSummary( "LAB ID", KDBPrinterClass.COUNT, "#", null, 0 );

                //List of default fields
                

                // dynamic
                // ==================================================================
                    
                                        
                // print SQL data
                DBPrinter.print();

                // done!
                printJob.submitPrintJob();                          


                log.log( this, "Print job handler finished." );
                
            }
            catch( Exception error	){

		// log error
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );		
		// show error message
		KMetaUtilsClass.showErrorMessageFromException( desktopAccess.getDesktopsWindow(), error );

	  }  // try                   
   }
            
            
  // ---------------------------------------------------------------------------            
  
   public void printButtonActionPerformed() {
   

           try{               
               		
		log.log( this, "Start print job..." );
                
                // --------------------------------------------------------
                
                // get a Db transaction  
                dbTransactionClientClass dbTransaction = new dbTransactionClientClass( configuration, log );                 
                prepareTransactionWithBrowserSQL(dbTransaction);
                dbTransaction.executeQuery( 0, 655356 );
                
                // --------------------------------------------------------

                // get a KePrintJob
                KPrintJobClass printJob = new KPrintJobClass( configuration, log );                      
	    
                printJob.usePrintingDefaults();                
                                                
                // ---------------------------------------------------------------------------------------

                
	        // setup the DB printer
                KPrintDataTableClass DBPrinter = new KPrintDataTableClass( 
			   configuration, log, 
                           dbTransaction, printJob, 0, 655356 );

                // add static field
                DBPrinter.addField( "DATE_DATE", "DATE", 40 );
                
                // static
                DBPrinter.setPrintingField( "DATE" );
                
                
                // add dynamic
                // add dynamic
                int serial = 0;
                Iterator selectedFieldDataIterator = selectedFields.iterator();                  
                while( selectedFieldDataIterator.hasNext() ) {

                    serial++;                

                    AbstractMap selectedFieldData = ( AbstractMap ) selectedFieldDataIterator.next();                                    

                    String fieldName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase();                
                    fieldName = makeDatabaseColumnName( fieldName ) + serial;

                    String fakeName = ((String) selectedFieldData.get( "ANALYSIS_NAME" )).toUpperCase() + " :" + serial;
                    
                    DBPrinter.addField( 
                        fieldName, fakeName, 40 
                    );
                    
                    
                    DBPrinter.setPrintingField( fakeName );                                        
                }
                
                
                
                // --------------------------------------------------------            
    
                // open print settings
                /*
                printSettingDialogClass printSettingDialog =
                        new printSettingDialogClass( configuration, log,
                                        KMetaUtilsClass.getParentFrame( component ),
                                        DBPrinter ); 
                log.log( this, "Openning print setting screen completed." );                                  
            
                if( printSettingDialog.dialogCloseResult( ) ) 
                */

                {         
                    
                    // header section 
                    KPrintSectionClass headerSection = new KPrintSectionClass(configuration, log, 500, 90 );

                    // headers
                    headerSection.setFont( new Font( "arial", Font.PLAIN, 9) );                
                    headerSection.printText( "SAMPLE Municipal Utilities Authority ", 370, 30 );
                    headerSection.printText( KMetaUtilsClass.time(), 442, 40 );

                    headerSection.setFont( new Font( "arial", Font.PLAIN, 12) ); 
                    headerSection.printText( "REPORT", 212, 50 );
                    
                    printJob.SetHeader( headerSection, KPrintJobClass.CENTER );                    
                    
                    //--------------------------------------------------
                    // Print!!!
                    //--------------------------------------------------                    
                                        
                    // start job
                    printJob.startPrintJob();
                    printJob.setDefaultFont( new Font("Arial", Font.PLAIN, 8) );                                        

                    
                    printJob.setTopMargin( 10 );                    
                    printJob.setLeftMargin( 25 );
                    printJob.setBottomMargin( 20 );

		    // print SQL data
                    DBPrinter.print();

		    // done!
                    printJob.submitPrintJob();                          

                }

                log.log( this, "Print job handler finished." );
                
            }
            catch( Exception error	){

		// log error
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );		
		// show error message
		KMetaUtilsClass.showErrorMessageFromException( desktopAccess.getDesktopsWindow(), error );

	  }  // try                   
       
       
   }

   @Override
   public void setSelectedFields( java.util.List< treeNodeClass > selectedFieldsParam, int view_modeParam ) {
      
            selectedFields = new ArrayList< treeNodeClass >( selectedFieldsParam );
            
           
   }



   
   
   // -------------------------------------------------------------------------
   
}
