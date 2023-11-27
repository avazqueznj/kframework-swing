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

package KFramework30.Printing;


//rtl
import java.awt.*;
import java.awt.print.*;
import java.util.*;
import javax.swing.*;

// ale utils
import KFramework30.Base.*;
import javax.swing.filechooser.*;

// system

public class KPrintJobClass 
implements Printable
{

        // constants
        public static final int LEFT = 0;
        public static final int CENTER = 1;
        public static final int RIGHT = 2;    

        public static final int PRINT_JOB_STARTED = 0;
        public static final int PRINT_JOB_FINISHED = 1;                       
                        
        public static final int OUTPUT_PRINTER = 0;
        public static final int OUTPUT_CSV = 1;        
        
        // uses
	private KConfigurationClass              configuration;
	private KLogClass                        log;
        
        private int                             printOutput;
        
        // has - defaulted               
        private int                             startLine;        
        public int                              topMargin;
        public int                              leftMargin;
        public int                              bottomMargin;
        private Vector                          headerSections;
        private int                             headerAligment;        
        private Vector                           footerSections;
        private int                             footAligment;                        
        private int                             printJobState;
        public persistentTextClass              exportFile;
        
        //------------ ale port to PrinterJob
        
        // has
        private java.util.List                  pages;
        private printJobPageClass               currentPage;
        private Font                            defaultFont;

        public int                             MAX_X = -1;
        public int                             MAX_Y = -1;        
        
        public static int                      PRINTJOB_CONFIG_DEFAULTS = 0;
        public static int                      PRINTJOB_CONFIG_USER = 1;        
        public static int                      PRINTJOB_CONFIG_PROMPT = 2;                
        
        private int                             printJobConfig = PRINTJOB_CONFIG_PROMPT;
        
        // global defaults
        public static PrinterJob               defaultPrinterJob;
        public static PageFormat               defaultPageFormat;

        // instance copies
        private PrinterJob                      printerJob;
        private PageFormat                      pageFormat;
        
        
        
        class printedSectionClass{
            // uses
            public KPrintSectionClass section;
            public int x;
            public int y;
            public printedSectionClass( KPrintSectionClass sectionParam, int xParam, int yParam ){         
                // uses
                section = sectionParam;
                x = xParam;
                y = yParam;
            }
        }

        
        
        class printJobPageClass{
            
            // uses
            private java.util.List  headerSections;        
            private java.util.List  sections;        
            private java.util.List  footerSections;        
            
            private KLogClass        log;
            
            private Font            startFont;                        
            private Font            endFont;            
            
            // has
            
            public printJobPageClass( KLogClass logParam, Font startFontParam ){                
                // uses
                
                headerSections = new ArrayList();            
                sections = new ArrayList();            
                footerSections = new ArrayList();            
                
                log = logParam;
                startFont = startFontParam;
                endFont = startFontParam;
                // has
            }
            
            public void addSection( KPrintSectionClass section, int xParam, int yParam ){                         
                printedSectionClass printedSection = new printedSectionClass( section, xParam, yParam );
                sections.add( printedSection );                
                if( section.currentFont != null ) endFont = section.currentFont;
            }

            
            public void addHeaderSection( KPrintSectionClass section, int xParam, int yParam ){                         
                printedSectionClass printedSection = new printedSectionClass( section, xParam, yParam );
                headerSections.add( printedSection );                
            }            
            public void addFooterSection( KPrintSectionClass section, int xParam, int yParam ){                         
                printedSectionClass printedSection = new printedSectionClass( section, xParam, yParam );
                footerSections.add( printedSection );                
            }            

            
            //Print out truly
            public void print( Graphics graphics, PageFormat pageFormat, int pageIndex )
            throws KExceptionClass
            {                 
                
                log.log( this, "Printing page sections" );
                
                // set default font
                graphics.setFont( startFont );
                                
                    
                Iterator section = headerSections.iterator();                  
                while( section.hasNext() ){
                    printedSectionClass currentSection = ( printedSectionClass ) section.next();                    
                    currentSection.section.print( graphics, currentSection.x, currentSection.y, MAX_X, MAX_Y );
                }                
                
                section = sections.iterator();                  
                while( section.hasNext() ){
                    printedSectionClass currentSection = ( printedSectionClass ) section.next();                    
                    currentSection.section.print( graphics, currentSection.x, currentSection.y, MAX_X, MAX_Y );
                }                
                
                section = footerSections.iterator();                  
                while( section.hasNext() ){
                    printedSectionClass currentSection = ( printedSectionClass ) section.next();                    
                    currentSection.section.print( graphics, currentSection.x, currentSection.y, MAX_X, MAX_Y );
                }                

                
                // page number
                graphics.drawString( String.valueOf( pageIndex + 1 ), MAX_X - 30,  MAX_Y - 25 );                
                                    
            }
 
        
            //Print out truly
            public void exportPage()
            throws KExceptionClass
            {                 
                
                log.log( this, "Printing page sections" );

                Iterator section = headerSections.iterator();                  
                while( section.hasNext() ){
                    printedSectionClass currentSection = ( printedSectionClass ) section.next();                    
                    currentSection.section.export( exportFile );
                }                
                
                
                section = sections.iterator();                  
                while( section.hasNext() ){
                    printedSectionClass currentSection = ( printedSectionClass ) section.next();                    
                    currentSection.section.export( exportFile );
                }                

                section = footerSections.iterator();                  
                while( section.hasNext() ){
                    printedSectionClass currentSection = ( printedSectionClass ) section.next();                    
                    currentSection.section.export( exportFile );
                }                
                
                
            }
 
        } // page class
        
        //------------ ale port to PrinterJob        
        

    //-------------------------------------------------------------------------        
    //-------------------------------------------------------------------------        
    //-------------------------------------------------------------------------                
    // INTERFACE
        
        
    //-------------------------------------------------------------------
    // PRINTABLE
    
    public int print( Graphics graphics, PageFormat pageFormat, int pageIndex )
    throws PrinterException
    {
        if( ( pageIndex + 1 ) > pages.size() ) return Printable.NO_SUCH_PAGE;
        
        // for each page
        try{
            
            // reset clip
            graphics.setClip( 0 , 0, MAX_X, MAX_Y );
        
            ( ( printJobPageClass ) pages.get( pageIndex ) ).print( graphics, pageFormat, pageIndex );
            
        }catch( KExceptionClass error ){
            throw new PrinterException( error.toString() );
        };
            
        return( Printable.PAGE_EXISTS );
    }
    
    // PRINTABLE
    //-------------------------------------------------------------------    
    

    static public void init(){
        
        if( KPrintJobClass.defaultPrinterJob == null || KPrintJobClass.defaultPageFormat == null ){

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            KPrintJobClass.setPrintingDefaults( 
                printerJob, printerJob.defaultPage()
            );
        };
                
    }
    
    static public void openPrinterSetupDialog()
    throws KExceptionClass
    {
       
        if( defaultPrinterJob == null || defaultPageFormat == null ){

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            setPrintingDefaults( 
                printerJob, printerJob.defaultPage()
            );
        };

        KPrintJobClass.defaultPrinterJob.printDialog();

        
    }

    static public boolean openPageSetupDialog()
    throws KExceptionClass    
    {

        boolean pageSet = false;

        if( defaultPrinterJob == null || defaultPageFormat == null ){

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            setPrintingDefaults( 
                printerJob, printerJob.defaultPage()
            );
        };

        KPrintJobClass.defaultPageFormat = 
            KPrintJobClass.defaultPrinterJob.pageDialog( KPrintJobClass.defaultPageFormat );

        if( KPrintJobClass.defaultPageFormat != null ) pageSet = true;
        
        return( pageSet );
    }     
    

// =========================================================================================    
    
    /** Creates new KPrintJobClass */
    public KPrintJobClass(KConfigurationClass configurationParam,KLogClass logParam ) 
    throws KExceptionClass
    {
        // uses
        configuration = configurationParam;
        log = logParam;

                //*********************************************************************
                // Make defaults if needed
                if( KPrintJobClass.defaultPrinterJob == null || KPrintJobClass.defaultPageFormat == null ){

                    PrinterJob printerJob = PrinterJob.getPrinterJob();

                    KPrintJobClass.setPrintingDefaults( 
                        printerJob, printerJob.defaultPage()
                    );
                };
                //*********************************************************************                
        

        if( configuration.getField( "PRINT_OUTPUT" ).equals( "PRINTER" ) ){    

            printOutput = OUTPUT_PRINTER;

        }else
        if( configuration.getField( "PRINT_OUTPUT" ).equals( "CSV" ) ){         

            printOutput = OUTPUT_CSV;    

        }else{
            
            throw new KExceptionClass(
                "*** Could not print ***\n" +
                "Invalid print output mode", null
            );
        };
        

        // has  
        topMargin = Integer.valueOf( configuration.getField( "top_margin" ) ).intValue();
        leftMargin =  Integer.valueOf( configuration.getField( "left_margin" ) ).intValue();
        bottomMargin =  Integer.valueOf( configuration.getField( "bottom_margin" ) ).intValue();
        startLine = topMargin;

        if( KPrintJobClass.defaultPrinterJob == null || KPrintJobClass.defaultPageFormat == null ){
            
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            
            KPrintJobClass.setPrintingDefaults( 
                printerJob, printerJob.defaultPage()
            );
            
        };
        
        
        pages  =  new ArrayList();
        defaultFont = new Font( "arial", Font.PLAIN, 8 );
        currentPage = new printJobPageClass( log, defaultFont );                
        exportFile = new persistentTextClass();
        
        // defaults
        headerSections = new Vector();
        footerSections = new Vector();
        
        // display dialogs by default for backward compatibility
        printJobConfig = PRINTJOB_CONFIG_PROMPT;
        
        printJobState = PRINT_JOB_FINISHED;
        
        log.log( this, "printJobClass is constructed." );
    }
    
    

    //-------------------------------------------------------------------
    // CONTROL

    // STATIC INIT
    static public void setPrintingDefaults(
        PrinterJob defaultPrinterJobParam, PageFormat defaultPageFormatParam   
        ){
        
        defaultPrinterJob = defaultPrinterJobParam;
        defaultPageFormat = defaultPageFormatParam;                      
            
    }
    
    // dont prompt
    public void usePrintingDefaults()
    throws KExceptionClass
    {

                //*********************************************************************
                // Make defaults if needed
                if( KPrintJobClass.defaultPrinterJob == null || KPrintJobClass.defaultPageFormat == null ){

                    PrinterJob printerJob = PrinterJob.getPrinterJob();

                    KPrintJobClass.setPrintingDefaults( 
                        printerJob, printerJob.defaultPage()
                    );
                };
                //*********************************************************************                
        
        printJobConfig = PRINTJOB_CONFIG_DEFAULTS;
    }

    // dont promp, and use these
    public void useSpecificPrintingDefaults(
        PrinterJob printerJobParam, PageFormat pageFormatParam           
        )        
    throws KExceptionClass
    {
                    
        printerJob = printerJobParam;
        pageFormat = pageFormatParam;           
        
        printJobConfig = PRINTJOB_CONFIG_USER;
    }

    
    public void setOutput( int printOutputParam ){
        
        printOutput = printOutputParam;
    }
    
    //-------------------------------------------------------------------
    
    public void setPrintJobMode( int mode ){
        
        //PRINTJOB_CONFIG_PROMPT
        printJobConfig = mode;      
        
        // setPrintJobMode( KPrintJobClass.PRINTJOB_CONFIG_PROMPT );
    }
    
    
    // PageFormat.PORTRAIT / PageFormat.LANDSCAPE
    public void startPrintJob()
    throws KExceptionClass
    {   
        log.log( this, "Starting print job" );
        
        if( printJobState != PRINT_JOB_FINISHED ){
                throw new KExceptionClass(
                    "Could not start new print job. Print job in progress.", null
                );
        };
        
                
        // Export to excel?                        
        if( printOutput == OUTPUT_CSV ){ 

                JFileChooser chooser = new JFileChooser();
                
                /*
                ExampleFileFilter filter = new ExampleFileFilter();                
                filter.addExtension("jpg");
                filter.addExtension("gif");
                filter.setDescription("JPG & GIF Images");
                chooser.setFileFilter(filter);
                 */
                
                int returnVal = chooser.showSaveDialog( null );
                
                if( returnVal == JFileChooser.APPROVE_OPTION) {
                    
                    exportFile.fileName = chooser.getSelectedFile().getAbsolutePath() + ".csv";
                   
                }else{
                    
                    throw new KExceptionClass( "*** Printing cancelled ***", null );
                }


                

                MAX_X = 10000;
                MAX_Y = 1000;
                
                
            } else {
                       
                // POP PRINTER DIALOGS
                if( printJobConfig == PRINTJOB_CONFIG_PROMPT ){


                    // has - defaults
                    printerJob = defaultPrinterJob;                                                         
                    if( !printerJob.printDialog() ){
                        throw new KExceptionClass( "\n*** PrintJob is cancelled ***\n", null );
                    };  
                    
                    pageFormat = KPrintJobClass.defaultPrinterJob.pageDialog( KPrintJobClass.defaultPageFormat );                    
                    if( pageFormat == KPrintJobClass.defaultPageFormat ){
                        throw new KExceptionClass( "\n*** PrintJob is cancelled ***\n", null );
                    };
                    
                    // remove default margins
                    Paper paper = pageFormat.getPaper();
                    paper.setImageableArea( 0, 0, paper.getWidth(), paper.getHeight() );
                    pageFormat.setPaper( paper );                    
                    
                    // get sizes
                    MAX_X = (int) pageFormat.getImageableWidth(); 
                    MAX_Y = (int) pageFormat.getImageableHeight();                                        

//log.logMode = KLogClass.console;                                        
//log.log( this, " X " + pageFormat.getImageableWidth() + " Y " + pageFormat.getImageableHeight() );
//log.logMode = KLogClass.off;                                        
                    
                    printerJob.setPrintable( this, pageFormat );
                    
                    
                }else
                if( printJobConfig == PRINTJOB_CONFIG_DEFAULTS )                    
                {
                  
                    // NO VERBOSE PRINT
                    
                    printerJob = defaultPrinterJob;
                    pageFormat = defaultPageFormat;                      
                      
                    // remove default margins
                    Paper paper = pageFormat.getPaper();
                    paper.setImageableArea( 0, 0, paper.getWidth(), paper.getHeight() );
                    pageFormat.setPaper( paper );                    
                    
                    // get sizes
                    MAX_X = (int) pageFormat.getImageableWidth(); 
                    MAX_Y = (int) pageFormat.getImageableHeight();                                        
                    
//log.logMode = KLogClass.console;                                        
//log.log( this, " X " + pageFormat.getImageableWidth() + " Y " + pageFormat.getImageableHeight() );
//log.logMode = KLogClass.off;                                        

                    printerJob.setPrintable( this, pageFormat );
                    
                }else
                if( printJobConfig == PRINTJOB_CONFIG_USER )                    
                {
                  
                    // NO VERBOSE PRINT
                                          
                    // remove default margins
                    Paper paper = pageFormat.getPaper();
                    paper.setImageableArea( 0, 0, paper.getWidth(), paper.getHeight() );
                    pageFormat.setPaper( paper );                    
                    
                    // get sizes
                    MAX_X = (int) pageFormat.getImageableWidth(); 
                    MAX_Y = (int) pageFormat.getImageableHeight();                                        
                    
//log.logMode = KLogClass.console;                                        
//log.log( this, " X " + pageFormat.getImageableWidth() + " Y " + pageFormat.getImageableHeight() );
//log.logMode = KLogClass.off;                                        

                    printerJob.setPrintable( this, pageFormat );
                    
                }else{
                 
                    throw new KExceptionClass(
                        "*** Could not start printjob ***\n " +
                        "Invalid config defaults mode", null
                    );
                }

            
                
                
            }; // excel or printer

            
        // last thing, after all above was successful
        printJobState = PRINT_JOB_STARTED;        
    }
        
    public int getDetailTop(){
        
        int result = 0;
        
        Iterator header = headerSections.iterator();
        while( header.hasNext() ){

            result += ((KPrintSectionClass) header.next()).getHeight();
        }

        result += topMargin;
        
        return( result );
    }
    
    public int getDetailBottom(){
        
        int result = 0;
        
        Iterator footer = footerSections.iterator();
        while( footer.hasNext() ){

            result += ((KPrintSectionClass) footer.next()).getHeight();
        }

        result += bottomMargin;
        
        return( result );
    }

    
    //make up the page not real printing.
    public void printSection( KPrintSectionClass section, int aligment )
    throws KExceptionClass
    {        
        
        if( printJobState != PRINT_JOB_STARTED ){
                throw new KExceptionClass(
                    "Could not print section. Print job has not been started.", null
                );
        };
                
        int sectionHeight = section.getHeight();
        int sectionWidth = section.getWidth();
                        
        if( sectionHeight > ( MAX_Y - ( getDetailTop() + getDetailBottom() ) ) ){
            throw new KExceptionClass( 
                "*** Printing section error **** \n" +
                "The printing section is too tall for the page!", null  );            
        };

        
        log.log( this, "Printing section..." );
        
        int y = 0;
        int x = calculateAlignment( sectionWidth, aligment );
                
        if( 
            (startLine + sectionHeight 
            > 
            MAX_Y - getDetailBottom()  
            ) && printOutput != OUTPUT_CSV ) { 
                
            formFeed();
        };
                    
        y = startLine;

        currentPage.addSection( section, x, y );        
        
        startLine += sectionHeight;  
        
        log.log( this, "Printing section done." );
    }


    public void formFeed()
    throws KExceptionClass
    {

            // finish page...   
            printHeader();
            printFoot();                            

            
            // save page
            pages.add( currentPage );
            // get another one
            currentPage = new printJobPageClass( log, currentPage.endFont );
            // reset
            startLine = getDetailTop();
    }
    
    
    public void submitPrintJob()
    throws KExceptionClass
    {
        log.log( this, "Finishing print job" );
        
        if( printJobState != PRINT_JOB_STARTED ){
                throw new KExceptionClass(
                    "Could not finish print job. Print job has not been started."   , null                 
                );
        };
        
        // save last page
        if( currentPage.sections.size() > 0 ){ 
            
            // finish last page
            printHeader();
            printFoot();                                    
            
            pages.add( currentPage );
        };
        
        //------------------------------------------
           
        if( printOutput == OUTPUT_PRINTER ){
            
            try{

                printerJob.print();

            }catch( PrinterException error ){

                String message = "*** Could not print ***\n";
                message += error.toString();            
                throw new KExceptionClass( message, error );
            };
            
        }else{
            
            // print pages manually
            Iterator page = pages.iterator();
            while( page.hasNext() ){                
                ( (printJobPageClass) page.next() ).exportPage();
            };
            
            // ignore print job, just save to disk
            exportFile.writeToDisk();
        };
        
        //------------------------------------------        
        
        printJobState = PRINT_JOB_FINISHED;        
                
        log.log( this, "Print job finished." );
    }    
    
    
    // CONTROL    
    //-------------------------------------------------------------------

    
    
    

    //-------------------------------------------------------------------
    // PAGE CONFIG
    

    public void setDefaultFont( Font font )
    throws KExceptionClass
    {
        defaultFont = font;

/*      Lims too late  
        if( printJobState == PRINT_JOB_STARTED ){
            throw new KExceptionClass(
                "*** Could not set print job default font ***\n" +
                "Job is already started."
            );
        };
 */

        if( printJobState == PRINT_JOB_STARTED ){
            currentPage.startFont =defaultFont;
        };
        

    }
 
    public Font getDefaultFont(){
        return( defaultFont );
    }
    
    public void setTopMargin( int margin )
    {         
        if( startLine <= topMargin  )   startLine = margin;
        
        topMargin = margin;
    } 
        
    public void setLeftMargin( int margin )
    {
        leftMargin = margin;
    }
    
    public int getLeftMargin( )
    {
        return leftMargin;
    }    
    
    public void setBottomMargin( int margin )
    {
        bottomMargin = margin;
    }     
    

    // font metrics
    public int getDefaultFontHeight()
    throws KExceptionClass
    {
        if( printJobState != PRINT_JOB_STARTED ){
                throw new KExceptionClass(
                    "Could not get default font height. \n" + 
                    "Print job has not been started.", null 
                );
        };
        
        return( 12 );
    }
  

    
    
    //-------------------------------------------------------------------
    // FOOTER HEADER
    

    public void clearHeader( )
    {        
        log.log( this, "Setting header..." );
        
        headerSections = new Vector();
                
        startLine = getDetailTop();
    }
    
    public void clearFooter( )
    {        
        log.log( this, "Setting foot..." );

        footerSections = new Vector();

    }   
        
    //*********************************************************************
    
    public void SetHeader( KPrintSectionClass section, int aligment )
    {        
        log.log( this, "Setting header..." );
        
        clearHeader();
        
        headerSections.add( section );
        
        headerAligment = aligment;
        
        startLine = getDetailTop();
    }
    
    public void SetFooter( KPrintSectionClass section, int aligment )
    {        
        
        clearFooter();
        
        log.log( this, "Setting foot..." );

        footerSections.add( section );

        footAligment = aligment;
    }    

    //*********************************************************************    
    
    public void addHeader( KPrintSectionClass section, int aligment )
    {        
        log.log( this, "Setting header..." );
        
        headerSections.add( section );
        
        headerAligment = aligment;
        
        startLine = getDetailTop();
    }
    
    public void addFooter( KPrintSectionClass section, int aligment )
    {        
        log.log( this, "Setting foot..." );

        footerSections.add( section );

        footAligment = aligment;
    }    
    
    private int calculateAlignment( int sectionWidth, int aligment )
    throws KExceptionClass
    {
        
        int x = 0;
        
        switch( aligment ){
            
            case LEFT:
                x = leftMargin;
                break;
                
            case RIGHT:
                x = MAX_X - sectionWidth;                
                break;
                
            case CENTER:
                x = ( MAX_X - sectionWidth ) / 2;                
                break;
                
            default:
                throw new KExceptionClass(
                    "*** Could not print  ***\n" +
                    "Invalid aligment for section." , null
                );
        };
               
        return x;
    }
    
   
    
    private void printHeader()
    throws KExceptionClass
    {        
            
        int headerY = topMargin;
        
        Iterator header = headerSections.iterator();
        while( header.hasNext() ){

            KPrintSectionClass currentSection = (KPrintSectionClass) header.next();
            
            int x = calculateAlignment( currentSection.getWidth(), headerAligment );                
            
            currentPage.addHeaderSection( currentSection, x, headerY );

            headerY += currentSection.getHeight();
        }        
        
    }

    
    private void printFoot()
    throws KExceptionClass    
    {       
        int footerY = MAX_Y - bottomMargin;
        
        Iterator footer = footerSections.iterator();
        while( footer.hasNext() ){

            KPrintSectionClass currentSection = (KPrintSectionClass) footer.next();
            
            int x = calculateAlignment( currentSection.getWidth(), footAligment );            
            
            currentPage.addFooterSection( currentSection, x, footerY - currentSection.getHeight() );

            footerY -= currentSection.getHeight();
        }              

    }    
    

    // FOOTER HEADER
    //-------------------------------------------------------------------

    

}
