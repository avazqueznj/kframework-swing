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

package Sample1.HumanInterfaceComponent.LIMSReportsDemo;

//rtl
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.*;


// system
import java.awt.event.ActionListener;


public class sdgBrowserClass
extends KDataBrowserBaseClass
implements ActionListener 
{
    //Constants
    private static final String CLOSED_STATUS = "CLOSED";
    
    public static final int ALL_VIEW = 1;
    public static final int PROJECT_VIEW = 2;
    public static final int CLIENT_VIEW = 3;  
    
    // uses                       	
    private KConfigurationClass	configuration;
    private KLogClass		log;   
    private java.awt.Window     parentWindow;
   
    // has
    private int view_mode;
    
    private long project_id;
    private long client_id;
    
    /** Creates new sdgBrowserClass */
    public  sdgBrowserClass( 
            KConfigurationClass configurationParam, KLogClass logParam, 
            JTable tableParam, 
            java.awt.Window parentWindowParam,
            JProgressBar ProgressBarParam )
    throws KExceptionClass
    {
            // inherits
            super(
                    configurationParam, logParam,
                    true, tableParam, parentWindowParam, null, null
            );
            
            
	    // uses
            configuration = configurationParam;
            log = logParam;
            parentWindow = parentWindowParam;                      

	    // has - defaulted
            view_mode = ALL_VIEW;            
            project_id = -1;
            client_id = -1;
    }


    /** Creates new sdgBrowserClass for the specific project */
    public sdgBrowserClass( 
            KConfigurationClass configurationParam, KLogClass logParam, 
            JTable tableParam, 
            java.awt.Window parentWindowParam,
	    long keyParam, int modeParam             )            
    throws KExceptionClass
    {
            // inherits // inherits  (KALI: Switched true to false to deactivate ID)
            super(
                    configurationParam, logParam,
                    true, tableParam, parentWindowParam, null, null
            );
            
            
	    // uses
            configuration = configurationParam;
            log = logParam;
            parentWindow = parentWindowParam;         
            

	    // has - defaulted  
            view_mode = modeParam;
            project_id = -1;
            client_id = -1;
          
            if( view_mode == PROJECT_VIEW ) project_id = keyParam;                    
            else if ( view_mode == CLIENT_VIEW ) client_id = keyParam;

            setDoubleClickEnabled( false );
    }

    
    public void initializeTable()
    throws KExceptionClass
    {			

            //check security 
            long system_user_id = configuration.getLongField( "system_user_id" );

//            if( view_mode == PROJECT_VIEW )  {
//                
//                //project is selected
//                super.initializeSQLQuery( 
//
//                     "sdg.SDG_ID, " + 
//                     "sdg.SDG_STATUS,"+
//                     "sdg.SDG_LAB_ID, " +                                           
//                     "client.CLIENT_NAME, " +
//                     "project.PROJECT_NAME, " +
//                     "sdg.SDG_DESCRIPTION, " +                     
//                     "sdg.SDG_DELIVERY_METHOD, "+
//                     "TO_CHAR( sdg.SDG_RECEIVED_DATE, 'MM/DD/YYYY' ) AS SDG_RECEIVED_DATE, " +
//                     "TO_CHAR( sdg.SDG_RECEIVED_TIME, 'HH:MI PM' ) AS SDG_RECEIVED_TIME, " +
//                     "sdg.SDG_RECEIVEDBY, "+
//                     
//                     "TO_CHAR( sdg.SDG_PRINTED_DATE, 'MM/DD/YYYY' ) AS SDG_PRINTED_DATE, " +                     
//                     "TO_CHAR( sdg.SDG_INVOICED_DATE, 'MM/DD/YYYY' ) AS SDG_INVOICED_DATE, " +                     
//                     "TO_CHAR( sdg.SDG_MAILED_DATE, 'MM/DD/YYYY' ) AS SDG_MAILED_DATE, " +                                          
//                     
//                     //"sdg.SDG_TAT_DESCRIPTION, "+
//                     "TO_CHAR( sdg.SDG_TAT_DATE, 'MM/DD/YYYY' ) AS SDG_TAT_DATE, " +
//                     //"report_type.REPORT_TYPE_NAME, "+
//                     //"TO_CHAR( ???.MAILED_DATE, 'MM/DD/YYYY' ) AS MAILED_DATE, " +
//                     //Totals
//                     "sdg.SDG_SAMPLE_TOTAL, "+
//                     "sdg.SDG_TAT_MULTIPLIER, "+
//                     "sdg.SDG_PRICE, " +
//                     "sdg.SDG_USER_DISCOUNT, " +
//                     "sdg.SDG_USER_PRICE "
//                     //"TO_CHAR( ???.INVOICED_DATE, 'MM/DD/YYYY' ) AS INVOICED_DATE, " +
//                     ,                      
//
//                     "ASHUR_CLIENTS client, ASHUR_PROJECT project, ASHUR_SAMPLEDELIVERYGROUP sdg " ,
//
//                     "SDG_ID" );                       
//
//                setDefaultCriteria(   
//                    // select project
//                    " ( sdg.PROJECT_ID ) = :v1 " + 
//                    " AND " +
//
//                    // get project info
//                    " ( project.PROJECT_ID = sdg.PROJECT_ID ) " + 
//                    " AND " +
//
//                    // get client info, from project
//                    " ( client.CLIENT_ID = project.CLIENT_ID ) "
//                );            
//
//            bindDefaultParameter( ":v1", String.valueOf( project_id ) );  
//
//        }else if( view_mode == CLIENT_VIEW )  {
//            
//                //project is selected
//                super.initializeSQLQuery( 
//
//                     "sdg.SDG_ID, " + 
//                     "sdg.SDG_STATUS,"+
//                     "sdg.SDG_LAB_ID, " +                                           
//                     "client.CLIENT_NAME, " +
//                     "project.PROJECT_NAME, " +
//                     "sdg.SDG_DESCRIPTION, " +                     
//                     "sdg.SDG_DELIVERY_METHOD, "+
//                     "TO_CHAR( sdg.SDG_RECEIVED_DATE, 'MM/DD/YYYY' ) AS SDG_RECEIVED_DATE, " +
//                     "TO_CHAR( sdg.SDG_RECEIVED_TIME, 'HH:MI PM' ) AS SDG_RECEIVED_TIME, " +
//                     "sdg.SDG_RECEIVEDBY, "+
//                     
//                     "TO_CHAR( sdg.SDG_PRINTED_DATE, 'MM/DD/YYYY' ) AS SDG_PRINTED_DATE, " +                     
//                     "TO_CHAR( sdg.SDG_INVOICED_DATE, 'MM/DD/YYYY' ) AS SDG_INVOICED_DATE, " +                     
//                     "TO_CHAR( sdg.SDG_MAILED_DATE, 'MM/DD/YYYY' ) AS SDG_MAILED_DATE, " +                                          
//
//                     //"sdg.SDG_TAT_DESCRIPTION, "+
//                     "TO_CHAR( sdg.SDG_TAT_DATE, 'MM/DD/YYYY' ) AS SDG_TAT_DATE, " +
//                     //"report_type.REPORT_TYPE_NAME, "+
//                     //"TO_CHAR( ???.MAILED_DATE, 'MM/DD/YYYY' ) AS MAILED_DATE, " +
//                     //Totals
//                     "sdg.SDG_SAMPLE_TOTAL, "+
//                     "sdg.SDG_TAT_MULTIPLIER, "+
//                     "sdg.SDG_PRICE, " +
//                     "sdg.SDG_USER_DISCOUNT, " +
//                     "sdg.SDG_USER_PRICE "
//                     //"TO_CHAR( ???.INVOICED_DATE, 'MM/DD/YYYY' ) AS INVOICED_DATE, " +
//                     ,                      
//
//                     "ASHUR_CLIENTS client, ASHUR_PROJECT project, ASHUR_SAMPLEDELIVERYGROUP sdg " ,
//
//                     "SDG_ID" );                       
//
//                setDefaultCriteria(   
//                
//                    // for these projects
//                    " ( sdg.PROJECT_ID = project.PROJECT_ID ) " + 
//                    
//                    " AND " +
//
//                    // of these clients
//                    " ( project.CLIENT_ID = client.CLIENT_ID ) " +
//                    
//                    " AND " +
//                    
//                    // where
//                    " ( client.CLIENT_ID = :v1 ) "
//                    
//                );            
//
//            bindDefaultParameter( ":v1", String.valueOf( client_id ) );  
//
//        }else 
            
            
            if( view_mode == ALL_VIEW ) {
                
                // CONVERT( VARCHAR(10), GETDATE(), 102 )  YYYY.MM.DD ANSI
                // CONVERT(VARCHAR(8), GETDATE(), 108)  HH:MM:SS
                // CONVERT(VARCHAR(8), GETDATE(), 108)  HH:MM:SS
                                
                // all non secured
                
                if( configuration.getField( "databaseType" ).equals( "oracle" ) ){                    

                 super.initializeSQLQuery( 

                     "sdg.SDG_ID, " + 
                     "sdg.SDG_STATUS,"+
                     "sdg.SDG_LAB_ID, " +                                           
                     "client.CLIENT_NAME, " +
                     "project.PROJECT_NAME, " +
                     "sdg.SDG_DESCRIPTION, " +                     
                     "sdg.SDG_DELIVERY_METHOD, "+
                     "TO_CHAR( sdg.SDG_RECEIVED_DATE, 'MM/DD/YYYY' ) AS SDG_RECEIVED_DATE, " +
                     "TO_CHAR( sdg.SDG_RECEIVED_TIME, 'HH:MI PM' ) AS SDG_RECEIVED_TIME, " +
                     "sdg.SDG_RECEIVEDBY, "+
                     
                     "TO_CHAR( sdg.SDG_PRINTED_DATE, 'MM/DD/YYYY' ) AS SDG_PRINTED_DATE, " +                     
                     "TO_CHAR( sdg.SDG_INVOICED_DATE, 'MM/DD/YYYY' ) AS SDG_INVOICED_DATE, " +                     
                     "TO_CHAR( sdg.SDG_MAILED_DATE, 'MM/DD/YYYY' ) AS SDG_MAILED_DATE, " +                                          
                     
                     "TO_CHAR( sdg.SDG_TAT_DATE, 'MM/DD/YYYY' ) AS SDG_TAT_DATE, " +
                     //Totals
                     "sdg.SDG_SAMPLE_TOTAL, "+
                     "sdg.SDG_TAT_MULTIPLIER, "+
                     "sdg.SDG_PRICE, " +
                     "sdg.SDG_USER_DISCOUNT, " +
                     "sdg.SDG_USER_PRICE "
                        ,                      

                         "ASHUR_CLIENTS client, ASHUR_PROJECT project, ASHUR_SAMPLEDELIVERYGROUP sdg " ,

                         "SDG_ID" );                        
                
                    setDefaultCriteria(   

                            // get project info
                            " ( project.PROJECT_ID = sdg.PROJECT_ID ) " + 
                            
                            " AND " +
                            " ( client.CLIENT_ID = project.CLIENT_ID ) " +
                            
                            " AND " +
                            " ( project.PROJECT_ID <> 0 ) " //PROJECT_ID == 0 is for QC samples
                        );    
                    
                    
                }else if(configuration.getField( "databaseType" ).equals( "mssql" ) ){  

                        super.initializeSQLQuery( 

                         "sdg.SDG_ID, " + 
                         "sdg.SDG_STATUS,"+
                         "sdg.SDG_LAB_ID, " +                                           
                         "client.CLIENT_NAME, " +
                         "project.PROJECT_NAME, " +
                         "sdg.SDG_DESCRIPTION, " +                     
                         "sdg.SDG_DELIVERY_METHOD, "+
                         "CONVERT( VARCHAR(10), sdg.SDG_RECEIVED_DATE, 102 ) AS SDG_RECEIVED_DATE, " +
                         "CONVERT( VARCHAR(10), sdg.SDG_RECEIVED_TIME, 108 ) AS SDG_RECEIVED_TIME, " +
                         "sdg.SDG_RECEIVEDBY, "+

                         "CONVERT( VARCHAR(10), sdg.SDG_PRINTED_DATE, 102 ) AS SDG_PRINTED_DATE, " +                     
                         "CONVERT( VARCHAR(10), sdg.SDG_INVOICED_DATE, 102 ) AS SDG_INVOICED_DATE, " +                     
                         "CONVERT( VARCHAR(10), sdg.SDG_MAILED_DATE, 102 ) AS SDG_MAILED_DATE, " +                                          

                         "CONVERT( VARCHAR(10), sdg.SDG_TAT_DATE, 102 ) AS SDG_TAT_DATE, " +
                         //Totals
                         "sdg.SDG_SAMPLE_TOTAL, "+
                         "sdg.SDG_TAT_MULTIPLIER, "+
                         "sdg.SDG_PRICE, " +
                         "sdg.SDG_USER_DISCOUNT, " +
                         "sdg.SDG_USER_PRICE "
                            ,                      

                             "ASHUR_CLIENTS client, ASHUR_PROJECT project, ASHUR_SAMPLEDELIVERYGROUP sdg " ,

                             "SDG_ID" );                        

                        setDefaultCriteria(   

                                // get project info
                                " ( project.PROJECT_ID = sdg.PROJECT_ID ) " + 

                                " AND " +
                                " ( client.CLIENT_ID = project.CLIENT_ID ) " +

                                " AND " +
                                " ( project.PROJECT_ID <> 0 ) " //PROJECT_ID == 0 is for QC samples
                            );   
                    
                }else{
                    throw new KExceptionClass( "databaseType not recognized " + configuration.getField( "databaseType" ), null );
                }                    
                
            }

        //setColumnNames( "sdg", "SDG_ID", "SDG ID" );
        setColumnNames( "sdg", "SDG_STATUS", "STATUS" );
        setColumnNames( "sdg", "SDG_LAB_ID", "LAB ID" );            
        setColumnNames( "client", "CLIENT_NAME", "CLIENT" );
        setColumnNames( "project", "PROJECT_NAME", "PROJECT" );
        setColumnNames( "sdg", "SDG_DESCRIPTION", "DESCRIPTION" );        
        setColumnNames( "sdg", "SDG_DELIVERY_METHOD", "DELIVERY METHOD" );
        setColumnNames( "sdg", "SDG_RECEIVED_DATE", "DATE REC." );
        setColumnNames( "sdg", "SDG_RECEIVED_TIME", "TIME REC." );
        setColumnNames( "sdg", "SDG_RECEIVEDBY", "RECEIVED BY" );
        
        setColumnNames( "sdg", "SDG_PRINTED_DATE", "DATE PRINT" );               
        setColumnNames( "sdg", "SDG_INVOICED_DATE", "DATE INVOICED" );               
        setColumnNames( "sdg", "SDG_MAILED_DATE", "DATE MAILED" );                       
        
        //setColumnNames( "sdg", "SDG_TAT_DESCRIPTION", "TAT DESCRIPTION" );
        setColumnNames( "sdg", "SDG_TAT_DATE", "DUE DATE" );
        //setColumnNames( "report_type", "REPORT_TYPE_NAME", "REPORT TYPE" );
        //setColumnNames( MAILED DATE );
        
        setColumnNames( "sdg", "SDG_SAMPLE_TOTAL", "SAMPLE TOTALS" );
        setColumnNames( "sdg", "SDG_TAT_MULTIPLIER", "TAT MULT." );
        setColumnNames( "sdg", "SDG_PRICE", "SUB TOTAL" );
        setColumnNames( "sdg", "SDG_USER_DISCOUNT", "DISCOUNT" );
        setColumnNames( "sdg", "SDG_USER_PRICE", "TOTAL" );
        // //setColumnNames( INVOICED DATE );


        setTableFont( new Font( "arial", Font.PLAIN, 10 ) );                        
        setDefaultOrder( " sdg.sdg_status, sdg.SDG_LAB_ID " );

        super.initializeTable(); 

        //setColumnType( "SDG ID", NUMERIC );

        //setColumnWidth( "SDG ID", 60 );
        adjustColumnWidth( "STATUS", 60 );
        adjustColumnWidth( "LAB ID", 80 );            
        adjustColumnWidth( "CLIENT", 150 );
        adjustColumnWidth( "PROJECT", 150 );
        adjustColumnWidth( "DELIVERY METHOD", 100 );
        adjustColumnWidth( "DATE REC.", 60 ); adjustColumnType( "DATE REC.", BROWSER_COLUMN_TYPE_DATE );
        adjustColumnWidth( "TIME REC.", 60 ); adjustColumnType( "TIME REC.", BROWSER_COLUMN_TYPE_DATE );
        adjustColumnWidth( "RECEIVED BY", 60 );
        
        adjustColumnWidth( "DATE PRINT", 60 ); adjustColumnType( "DATE PRINT", BROWSER_COLUMN_TYPE_DATE );        
        adjustColumnWidth( "DATE INVOICED", 60 ); adjustColumnType( "DATE INVOICED", BROWSER_COLUMN_TYPE_DATE );        
        adjustColumnWidth( "DATE MAILED", 60 ); adjustColumnType( "DATE MAILED", BROWSER_COLUMN_TYPE_DATE );                
        
        adjustColumnWidth( "DUE DATE", 60 );
        adjustColumnWidth( "DESCRIPTION", 150 );
        
        
        adjustColumnFont( 
            "CLIENT", 
            new Font( "arial", Font.ITALIC | Font.BOLD, 14 ) 
            );
        adjustColumnFont( 
            "PROJECT", 
            new Font( "arial", Font.ITALIC | Font.BOLD, 14 ) 
            );
        
        adjustColumnType( "SDG_ID", BROWSER_COLUMN_TYPE_NUMERIC );        
        
        //totals    
        adjustColumnWidth( "SAMPLE TOTALS", 80 );
        adjustColumnType( "SAMPLE TOTALS", BROWSER_COLUMN_TYPE_CURRENCY );
        adjustColumnFont( "SAMPLE TOTALS", new Font( "arial", Font.ITALIC | Font.BOLD, 14 ) );
        
        adjustColumnWidth( "TAT MULT.", 50 );
        adjustColumnType( "TAT MULT.", BROWSER_COLUMN_TYPE_NUMERIC );
        adjustColumnFont( "TAT MULT.", new Font( "arial", Font.ITALIC | Font.BOLD, 14 ) );
        
        adjustColumnWidth( "SUB TOTAL", 80 );
        adjustColumnType( "SUB TOTAL", BROWSER_COLUMN_TYPE_CURRENCY );
        adjustColumnFont( "SUB TOTAL", new Font( "arial", Font.ITALIC | Font.BOLD, 14 ) );
        
        adjustColumnWidth( "DISCOUNT", 60 );
        adjustColumnType( "DISCOUNT", BROWSER_COLUMN_TYPE_NUMERIC );
        adjustColumnFont( "DISCOUNT", new Font( "arial", Font.ITALIC, 14 ) );
        
        adjustColumnWidth( "TOTAL", 80 );
        adjustColumnType( "TOTAL", BROWSER_COLUMN_TYPE_CURRENCY );
        adjustColumnFont( "TOTAL", new Font( "arial", Font.ITALIC | Font.BOLD, 14 ) );
            
        
    }

    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------
    // Event dispatching
    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------

    public void actionPerformed(java.awt.event.ActionEvent event ){
        try {
            String command = event.getActionCommand();
            
            if( command.equals( "new" ) )
                newButtonActionPerformed();

            else if( command.equals( "edit" ) )
                editButtonActionPerformed();      

            else if( command.equals( "delete" ) )
                deleteButtonActionPerformed();      

            else if( command.equals( "sort" ) )
                sortButtonActionPerformed();      

            else if( command.equals( "filter" ) )
                  filterButtonActionPerformed();      

            else if( command.equals( "refresh" ) )
                refreshButtonActionPerformed();   
            
            else if( command.equals( "print" ) )
                printButtonActionPerformed();
        } catch (KExceptionClass error) {
                           // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
        }
    }    

    
}