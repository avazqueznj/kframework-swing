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



package Sample1.HumanInterfaceComponent.system_mail;

//rtl
import javax.swing.*;
import java.awt.*;


// utilities
import KFramework30.Widgets.*;
import KFramework30.Base.*;

// system
import KFramework30.Communication.persistentObjectManagerClass;
import ProblemDomainComponent.systemMailClass;



public class mailBrowserClass extends KDataBrowserBaseClass {   
        
    // uses                       	
    
    // has

    public mailBrowserClass(
            KConfigurationClass configurationParam,
            KLogClass logParam,
            JTable tableParam,
            java.awt.Window parentWindow ) throws KExceptionClass
    {
        
            // inherits
            super(
                    configurationParam, logParam,
                    true, tableParam, parentWindow,  
                    systemMailClass.class,
                    mailEditDialogClass.class
            );  
            
            // uses                   
	    // has     
    }        


    public void initializeTable()
    throws KExceptionClass
    {	      
            //------------------------------------------------------------------
            //Get daily category
            
            super.initializeSQLQuery( 
                    
                " MAIL_ID , MAIL_DATE, MAIL_FROM,   MAIL_RECIPIENT, MAIL_SUBJECT, MAIL_DATA_TYPE , MAIL_STATUS, "+
                " MAIL_STATUS_DESCRIPTION " ,
                
                // TABLAS
                " SYSTEMMAILCLASS MAIL",  
                
                // pk
                "MAIL_ID"                                                                                
                    );   

            setColumnNames( "", "MAIL_ID", "id" );             
            setColumnNames( "", "MAIL_DATE", "fecha" );            
            setColumnNames( "", "MAIL_FROM", "de" );
            setColumnNames( "", "MAIL_RECIPIENT", "para" );                       
            setColumnNames( "", "MAIL_SUBJECT", "sujeto" );
            setColumnNames( "", "MAIL_DATA_TYPE", "format" );            
            setColumnNames( "", "MAIL_STATUS", "status" );
            setColumnNames( "", "MAIL_STATUS_DESCRIPTION", "nota" );            
            
            setDefaultOrder( " mail_id " );
            
            super.initializeTable();   
                        
            adjustColumnWidth( "id", 60 );
            adjustColumnWidth( "fecha", 60 );
            adjustColumnWidth( "de", 100 );            
            adjustColumnWidth( "para", 100 );
            adjustColumnWidth( "sujeto", 200 );
            adjustColumnWidth( "format", 60 );            
            adjustColumnWidth( "status", 100 );            
            adjustColumnWidth( "nota", 100 );                        
            
            adjustColumnFont(  "id",  new Font( "arial", Font.PLAIN, 10  ) );            
            adjustColumnFont(  "fecha",  new Font( "arial", Font.PLAIN, 10 )  );                        
            adjustColumnFont(  "de",  new Font( "arial", Font.PLAIN, 10 )  );                        
            adjustColumnFont(  "para",  new Font( "arial", Font.PLAIN, 10 )  );            
            adjustColumnFont(  "sujeto",  new Font( "arial", Font.PLAIN, 10 )  );                        
            adjustColumnFont(  "format",  new Font( "arial", Font.PLAIN, 10 )  );                                    
            adjustColumnFont(  "status",  new Font( "arial", Font.PLAIN, 10 )  );                        
            adjustColumnFont(  "nota",  new Font( "arial", Font.PLAIN, 10 )  );                                    
                        
    }        
 
}
