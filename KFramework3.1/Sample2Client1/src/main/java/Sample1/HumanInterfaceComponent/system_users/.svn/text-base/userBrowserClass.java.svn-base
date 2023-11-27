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


package Sample1.HumanInterfaceComponent.system_users;

//rtl
import KFramework30.Widgets.DataBrowser.UI.setOrderDialogClass;
import javax.swing.*;
import java.awt.*;


// utilities
import KFramework30.Widgets.*;
import KFramework30.Base.*;

// system
import KFramework30.Communication.persistentObjectManagerClass;
import ProblemDomainComponent.systemUserClass;


public class userBrowserClass extends KDataBrowserBaseClass {   
    
    // uses                       	
    
    // has

    public userBrowserClass(
            KConfigurationClass configurationParam,
            KLogClass logParam,
            JTable tableParam,
            java.awt.Window parentWindow ) throws KExceptionClass
    {
        
            // inherits
            super(
                    configurationParam, logParam,
                    true, tableParam, parentWindow,  
                    systemUserClass.class,
                    systemUserEditDialogClass.class
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
                    
                " USUARIO.SYSTEM_USER_ID, USUARIO.SYSTEM_USER_NAME, USUARIO.SYSTEM_USER_FULLNAME, USUARIO.SYSTEM_USER_EMAIL " ,
                
                // TABLAS
                " SYSTEMUSERCLASS USUARIO" ,
                
                // pk
                "SYSTEM_USER_ID"                                                                                
                    );   

             
            setColumnNames( "USUARIO", "SYSTEM_USER_ID", "ID" );
            setColumnNames( "USUARIO", "SYSTEM_USER_NAME", "Name" );
            setColumnNames( "USUARIO", "SYSTEM_USER_FULLNAME", "Full Name" );
            setColumnNames( "USUARIO", "SYSTEM_USER_EMAIL", "Email" );

           
            setDefaultOrder( " USUARIO.SYSTEM_USER_ID " );

            super.initializeTable();   

            adjustColumnType("ID", BROWSER_COLUMN_TYPE_NUMERIC);
            
            adjustColumnWidth( "ID", 100 );            
            adjustColumnWidth( "Name", 100 );                        
            adjustColumnWidth( "Full Name", 180 );            
            adjustColumnWidth( "Email", 100 );                        
            
            adjustColumnFont(  "Name",  new Font( "arial", Font.BOLD, 10  ) );            
    }        

   
}
