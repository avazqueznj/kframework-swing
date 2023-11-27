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

// rtl
import java.util.*;
import java.awt.*;
import java.text.*;

// utils
import KFramework30.Base.*;
import KFramework30.Communication.*;

public class KPrintDataTableClass extends Object {

        // constants
        public static final int COUNT = 0;
        public static final int SUM = 1;
        public static final int AVERAGE = 2; 
        public static final int NOTHING = 3; 
        private static final String BREAK = "< break >"; 

        public static final int HEADER_TYPE_NOHEADER = 0; // NO HEADER ITS UP TO YOU
        public static final int HEADER_TYPE_INLINEHEADER = 1;  // HEADER INLINE WITH DBPRINTER                     
        public static final int HEADER_TYPE_TOPPAGEHEADER = 2;  // HEADER AS PART OF PAGEHEADER
        
        
	// uses
	private KConfigurationClass              configuration;
	private KLogClass                        log;
        private dbTransactionClientClass         dbTransactionClient;   
        private KPrintJobClass                   printJob;
       
        private int                              startRow;
        private int                              endRow;
        
        //section padding and spacing
        public int                               verticalSpace;
        public int                               horizontalSpace;
        public int                               indentSpace;
    
        private Font                             headerFont;
        
        // has - defaulted
        
        private int                              headerMode;
        
        private java.util.List                   fieldList; 
        private java.util.List                   printingFieldList; 
    
    
    /** Creates new KDBPrinterClass */
    public KPrintDataTableClass( 
            KConfigurationClass configurationParam, KLogClass logParam, 
            dbTransactionClientClass dbTransactionParam,
            KPrintJobClass printJobParam, int startRowParam, int endRowParam )
    throws KExceptionClass
    {
        //uses
        configuration = configurationParam;
        log = logParam;
        dbTransactionClient = dbTransactionParam;
        printJob = printJobParam;
        
        startRow = startRowParam;
        endRow = endRowParam;
        
        verticalSpace = Integer.parseInt( configuration.getField( "vertical_space" ) );
        horizontalSpace = Integer.parseInt( configuration.getField( "horizontal_space" ) );
        indentSpace = Integer.parseInt( configuration.getField( "indent_space" ) );
        
        headerFont = new Font( "arial", Font.BOLD , 8 );
        
        // has - defaulted
        
        headerMode = HEADER_TYPE_TOPPAGEHEADER;
        
        fieldList = new ArrayList();
        printingFieldList = new ArrayList();
    }

    public void setHeadersMode( int modeParam ){
        
        headerMode = modeParam;
    }
    
    public void setVerticalSpace( int unit )
    {
        if( unit > 0 )
            verticalSpace = unit;        
    }
    
    public void setHorizontalSpace( int unit )
    {
        if( unit > 0 )
            horizontalSpace = unit;        
    }

    public void setColumnHeaderFont( Font fontParam ){        
        headerFont = fontParam;
    }
    
    public void addField( String name, String readableName, int fieldWidth, int alignment )
    throws KExceptionClass
    {
        if( !dbTransactionClient.columnNamesList.contains( name ) )
                throw new KExceptionClass(
                    "Field [" + name + "] cannot be printed out. \n" +
                    "It is not in the database!", null );   
        
        fieldList.add( new printingFieldInfoClass( 
            name, readableName, fieldWidth, alignment ) );
    }

    
    public void addField( String name, String readableName, int fieldWidth )
    throws KExceptionClass
    {
        addField( name, readableName, fieldWidth, KPrintSectionClass.LEFT );
    }     

    
    public void addBreak()
    {
        printingFieldList.add( 
            new printingFieldInfoClass( BREAK, BREAK, 0, KPrintSectionClass.LEFT ) );
    }       

    // deprecated
    public void setBreak(){ addBreak(); }       
 
    public void setPrintingField( String readableName )
    throws KExceptionClass
    {
        boolean found = false;
        Iterator fields = fieldList.iterator();	
        
        while( fields.hasNext() ){
            // get next field
            printingFieldInfoClass fieldInfo = ( printingFieldInfoClass ) fields.next();
            if( fieldInfo.readableName.equals( readableName ) )  { 
                printingFieldList.add( fieldInfo );
                found = true;
                break;
            }
        }
                        
        if( !found )
                throw new KExceptionClass(
                    "Field [" + readableName + "] cannot be printed out. \n" +
                    "It does not exist in the database!", null  );   
    }    
    

    public void addSummary( String fakeName, int operation, String prefix,
            String suffix, int precision )
    throws KExceptionClass
    {   
        boolean found = false;
        
        //fill in extra field info
        Iterator fields = fieldList.iterator();	
        while( fields.hasNext() ){
            // get next field
            printingFieldInfoClass fieldInfo = 
                        ( printingFieldInfoClass ) fields.next();
            if( fieldInfo.readableName.equals( fakeName ) ) {
                fieldInfo.operation = operation;
                fieldInfo.prefix = prefix==null ? "" : prefix;
                fieldInfo.suffix = suffix==null ? "" : suffix;
                fieldInfo.precision = precision;
                
                found = true;
                break;
            }
        } 
        
        if( !found )
                throw new KExceptionClass(
                    // what
                    "Field [" + fakeName + "] cannot be printed out. \n" +
                    // where
                    "It does not exist in the database!", null );           
            
    }
    
    
    public java.util.List getDefaultPrintingFields( )
    {
        return fieldList;
    }  
    
    void setDefaultPrintingFields( java.util.List  list )
    {
        if( list != null ) fieldList = list;
    }
    
    
    public java.util.List getPrintingFields( )
    {
        return printingFieldList;
    }

    void setPrintingFields( java.util.List  list )
    {
        if( list != null ) printingFieldList = list;
    }
    
    
    public void print()
    throws KExceptionClass
    {
        
        int fontHeight = printJob.getDefaultFontHeight();
                        
        if( printingFieldList.size() == 0 )
            printingFieldList = fieldList;        
        
        int rowCount = -1;
        
        try{

            
                // ---------------------------------------------------------------                
                // ---------------------------------------------------------------
                if( headerMode != HEADER_TYPE_NOHEADER ){
                    
                    
                    //to print out DB rows or 'breaks' in a row, it is always left alignment
                    //so no need to get right width of a section
                    int sectionWidth = printJob.MAX_X - printJob.leftMargin;
                    int indent = 0; 
                    int startX = indent;

                    // go through fields in the row for each section
                    //create a new section
                    KPrintSectionClass section 
                        = new KPrintSectionClass(configuration, log, 
                            sectionWidth, verticalSpace + ( fontHeight * 2 ) );  
                    
                    // set header font
                    section.setFont( headerFont );                    
                    
                    // loop once to figure headers
                    Iterator field_iterator = printingFieldList.iterator();	                    
                    while( field_iterator.hasNext() ){

                        //section based on 'break' not a row    
                        //For each break in the row...                                             

                        // get next field
                        printingFieldInfoClass fieldInfo = 
                                    ( printingFieldInfoClass ) field_iterator.next();

                        if( 
                            ( fieldInfo.readableName.equals( BREAK ) ) ||
                            ( startX + fieldInfo.fieldWidth > sectionWidth )
                        ) {

                            // print break section
                            if( headerMode == HEADER_TYPE_INLINEHEADER ){
                                
                                printJob.printSection( section, KPrintJobClass.LEFT ); 
                                
                            }else
                            if( headerMode == HEADER_TYPE_TOPPAGEHEADER ){
                                
                                printJob.addHeader( section, KPrintJobClass.LEFT ); 
                                
                            }else{
                                
                                throw new KExceptionClass(
                                    "*** Cound not build table ***\n" +
                                    "Invalid heading printing mode.", null
                                );
                            }
                                
                                

                            //create a new section
                            section = new KPrintSectionClass( configuration, log, 
                                sectionWidth, verticalSpace + ( fontHeight * 2 ) );  

                            //set the indent for next line
                            indent += indentSpace;   
                            startX = indent;

                            if( fieldInfo.readableName.equals( BREAK ) ) continue;
                        }                                                                   
                        
                        section.printHeaderLabel( 
                            fieldInfo.readableName, startX, verticalSpace + fontHeight, fieldInfo.fieldWidth );

                        // calc next start
                        startX += fieldInfo.fieldWidth + horizontalSpace;                     

                    }   //end of while( field_iterator.hasNext() ) loop

                    // bottom line
                    section.printLine( 0, ( fontHeight + verticalSpace ) * 2, sectionWidth, ( fontHeight + verticalSpace ) * 2 );
                    
                    // print last section
                    if( headerMode == HEADER_TYPE_INLINEHEADER ){

                        printJob.printSection( section, KPrintJobClass.LEFT ); 

                    }else
                    if( headerMode == HEADER_TYPE_TOPPAGEHEADER ){

                        printJob.addHeader( section, KPrintJobClass.LEFT ); 

                    }else{

                        throw new KExceptionClass(
                            "*** Cound not build table ***\n" +
                            "Invalid heading printing mode." , null
                        );
                    }

                    
                }
                // ---------------------------------------------------------------
                // ---------------------------------------------------------------                
                
            
            
            //for each row            
            while( dbTransactionClient.fetch() ) {  
                
                
                rowCount++;
                if( rowCount < startRow ) continue;
                if( (endRow > 0) && (rowCount > endRow) ) break;                
               
                
                //to print out DB rows or 'breaks' in a row, it is always left alignment
                //so no need to get right width of a section
                int sectionWidth = printJob.MAX_X - printJob.leftMargin;
                int indent = 0; 
                int startX = indent;

                // go through fields in the row for each section
                //create a new section
                KPrintSectionClass section 
                    = new KPrintSectionClass(configuration, log, 
                        sectionWidth, fontHeight + verticalSpace); 
                
                section.setFont( printJob.getDefaultFont() );                                    
                
                // loop for each row print fields
                Iterator field_iterator = printingFieldList.iterator();	                
                while( field_iterator.hasNext() ){
                                        
                    //section based on 'break' not a row    
                    //For each break in the row...
                    String valueBuffer = new String();                        
                    
                    // get next field
                    printingFieldInfoClass fieldInfo = 
                                ( printingFieldInfoClass ) field_iterator.next();
                            
                    if( 
                        ( fieldInfo.readableName.equals( BREAK ) ) ||
                        ( startX + fieldInfo.fieldWidth > sectionWidth )
                    ) {
                    
                        // print break section
                        printJob.printSection( section, KPrintJobClass.LEFT ); 
                        
                        //create a new section
                        section = new KPrintSectionClass(
                            configuration, log, 
                                sectionWidth, fontHeight + verticalSpace ); 

                        //set the indent for next line
                        indent += indentSpace;   
                        startX = indent;
                        
                        if( fieldInfo.readableName.equals( BREAK ) ) continue;
                    }
                                                                    
                    valueBuffer = (String) dbTransactionClient.getProperty( fieldInfo.fieldName );                         
                    if( valueBuffer == null )
                        throw new KExceptionClass(
                            "Field [" + fieldInfo.fieldName + "] cannot be printed out. \n" +
                            "It is not in the database!", null );  
                    
                    // format                    
                    if( dbTransactionClient.isColumnNumeric( fieldInfo.fieldName ) ){
                        
                        // format a numeric value
                        valueBuffer = "" + KMetaUtilsClass.getDecimalNumericValueFromString( valueBuffer , fieldInfo.fieldName );
                        
                    }else{
                        
                        // format a text value
                        valueBuffer = "\"" + valueBuffer  + "\"";
                    }

                                                                   
                    section.printField( 
                        valueBuffer, startX, verticalSpace, fieldInfo.fieldWidth, fieldInfo.fieldAlignment );
                    
                    // calc next start
                    startX += fieldInfo.fieldWidth + horizontalSpace;  
                    
                    //Do operations
                    if( !valueBuffer.equals( "" ) ){
                        
                        if( fieldInfo.operation == NOTHING ){
                            continue;
                        }
                        else if( fieldInfo.operation == COUNT ){
                            fieldInfo.operationResult = rowCount + 1;
                        }
                        else if( fieldInfo.operation == AVERAGE || fieldInfo.operation == SUM ){    
                            try{
                                fieldInfo.operationResult += KMetaUtilsClass.getDecimalNumericValueFromString( valueBuffer );             
                            }catch( Exception error ){};
                        };
                    };
                    
                }   //end of while( field_iterator.hasNext() ) loop

                // print last section
                printJob.printSection( section, KPrintJobClass.LEFT );                             
                    
            }   //end of while( dbTransactionClient.fetch() ) loop    
            
            printSummarySection( rowCount );

            
        }
        catch( KExceptionClass error ){
            


            throw error;
        }                                             
        catch( Exception error ){
            


            log.log( this, KMetaUtilsClass.getStackTrace( error ) );
            
            throw new KExceptionClass( "*** Could not print table ***\nJVM->" + error.toString(), null );
        }                                             
              
    }
    
    
    private void printSummarySection( int rowCount )
    throws KExceptionClass
    {        
        //work on the fields
        Iterator field_iterator = printingFieldList.iterator();	
        int fontHeight = printJob.getDefaultFontHeight();
        
        //to print out DB rows or 'breaks' in a row, it is always left alignment
        //so no need to get right width of a section
        int sectionWidth = printJob.MAX_X - printJob.getLeftMargin(); 
        int indent = 0; 
        int startX = indent;
        boolean doPrintSummary =  true;   
        String valueBuffer;
        
        //create a new section
        KPrintSectionClass section = 
            new KPrintSectionClass(configuration, log, 
                sectionWidth, fontHeight + verticalSpace);           
        
        section.printLine( 0, 0, sectionWidth, 0 );
        
        // go through fields in the row for each section
        while( field_iterator.hasNext() ){

            //work out the section width, based on 'break' not a row                     

            // get next field
            printingFieldInfoClass fieldInfo = 
                        ( printingFieldInfoClass ) field_iterator.next();

            if( fieldInfo.readableName.equals(BREAK) ) {

                if( doPrintSummary == true )
                    // print break section
                    printJob.printSection( section, KPrintJobClass.LEFT ); 
                
                //create a new section
                section = new KPrintSectionClass(configuration, log, 
                                sectionWidth, fontHeight + verticalSpace); 

                //set the indent for next line
                indent += indentSpace;   
                startX = indent;
                doPrintSummary =  false; 
                continue;
            }
            
            if( fieldInfo.operation == NOTHING )  {  
                startX += fieldInfo.fieldWidth + horizontalSpace;   
                continue;
            }
            else               
                doPrintSummary =  true;
            
            if( fieldInfo.operation == AVERAGE && rowCount != -1 )
                fieldInfo.operationResult /= rowCount + 1;
            
            valueBuffer = fieldInfo.prefix;
            
            NumberFormat format = NumberFormat.getInstance();
            if( format instanceof DecimalFormat ) {
                ((DecimalFormat) format).setMinimumFractionDigits(fieldInfo.precision);
                ((DecimalFormat) format).setMaximumFractionDigits(fieldInfo.precision);
            }
            
            valueBuffer += format.format(fieldInfo.operationResult);
           
            valueBuffer += fieldInfo.suffix;
            
            section.printField( 
                valueBuffer, startX, verticalSpace, fieldInfo.fieldWidth, fieldInfo.fieldAlignment );
            
            startX += fieldInfo.fieldWidth + horizontalSpace;                                
        }

        //print out the summary section
        if( doPrintSummary )
            printJob.printSection( section, KPrintJobClass.LEFT ); 
    }
    
}
