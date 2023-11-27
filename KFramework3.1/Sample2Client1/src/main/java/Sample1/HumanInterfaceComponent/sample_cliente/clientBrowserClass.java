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



package Sample1.HumanInterfaceComponent.sample_cliente;

//rtl
import javax.swing.*;
import java.awt.*;

// utilities
import KFramework30.Widgets.*;
import KFramework30.Base.*;
import KFramework30.Communication.persistentObjectManagerClass;

// system
import ProblemDomainComponent.sample_clientClass;
import java.util.HashMap;


public class clientBrowserClass 
extends KDataBrowserBaseClass {   
    
    // uses                       	
    
    // has

    public clientBrowserClass(
            KConfigurationClass configurationParam,
            KLogClass logParam,
            JTable tableParam,
            java.awt.Window parentWindow ) throws KExceptionClass
    {
        
            // inherits
            super(
                    configurationParam, logParam,
                    true, tableParam, parentWindow,  
                    sample_clientClass.class,
                    clientEditDialogClass.class
            );  
            
            // uses                   
	    // has   
         
    }        


    public void initializeTable()
    throws KExceptionClass
    {	      
              
            // set the SQL
            super.initializeSQLQuery( 
                
                // 1 campos                    
                " client_id , client_name, client_address, CLIENT_EXPRESS_DELIVERY, CLIENT_DISCOUNT ",
                
                // 2 tablas and joins                                             
                " sample_client cli ",  
                
                // 3 llave principal (mayusculas!)
                "CLIENT_ID" 
                                                                                             
                    );   

            // define column settings
            setColumnNames( "cli", "CLIENT_ID", "id" );
            setColumnNames( "cli", "CLIENT_NAME", "Name" );
            setColumnNames( "cli", "CLIENT_ADDRESS", "Address" );                                      
            setColumnNames( "cli", "CLIENT_EXPRESS_DELIVERY", "Express" );
            setColumnNames( "cli", "CLIENT_DISCOUNT", "Discount" );                                      
                       
            setDefaultOrder( "  client_name " );

            
            // load data
            super.initializeTable();   
            
            // some customization
            adjustColumnWidth( "Name", 100 );
            adjustColumnWidth( "Address", 200 );                        
 
            adjustColumnType("id",  BROWSER_COLUMN_TYPE_NUMERICNOFORMAT );
    }        

    
}
