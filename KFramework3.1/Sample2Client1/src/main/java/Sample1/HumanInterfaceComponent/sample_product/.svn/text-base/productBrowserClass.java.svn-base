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



package Sample1.HumanInterfaceComponent.sample_product;

//rtl
import javax.swing.*;
import java.awt.*;

// utilities
import KFramework30.Widgets.*;
import KFramework30.Base.*;
import KFramework30.Communication.persistentObjectManagerClass;

// system
import KFramework30.Widgets.DataBrowser.TableCellRenderers.ImageCellRendererClass;
import Sample1.HumanInterfaceComponent.system_mail.*;
import ProblemDomainComponent.sample_productClass;
import java.util.HashMap;


public class productBrowserClass 
extends KDataBrowserBaseClass {   
        
    // uses                       	
    
    // has

    /** Creates new viajeBrowserClass */
    public productBrowserClass(
            KConfigurationClass configurationParam,
            KLogClass logParam,
            JTable tableParam,
            java.awt.Window parentWindow           
            ) throws KExceptionClass
    {
        
            // inherits
            super(
                    configurationParam, logParam,
                    true, tableParam, parentWindow,  
                    sample_productClass.class,
                    productEditDialogClass.class
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
                " prod_id, prod_name, prod_cost, prod_picture ",
                
                // 2 tablas and joins                                             
                " sample_product prod   "  ,
                                                   
                // 3 llave principal (mayusculas!)
                "PROD_ID" 
                                                                                             
                    );   

            // define column settings
            setColumnNames( "prod", "PROD_ID", "id" );
            setColumnNames( "prod", "PROD_NAME", "Name" );
            setColumnNames( "prod", "PROD_COST", "Cost" );                                      
            setColumnNames( "prod", "PROD_PICTURE", "Picture" );                                      
                        
            setDefaultOrder( "  PROD_ID " );

            setRowsHeight( 150 );
            
            // load data
            super.initializeTable();   
            
            // some customization
            adjustColumnWidth( "id", 100 );
            adjustColumnWidth( "Name", 200 );  
            adjustColumnWidth( "Cost", 100 );  
            
            adjustColumnType( "Cost", BROWSER_COLUMN_TYPE_CURRENCY );            
            adjustColumnFont(  "Cost",  new Font( "arial", Font.BOLD, 10  ) );             
            adjustColumnForegroundColor( "Cost", Color.BLUE  );
            
            adjustColumnWidth( "Picture", 210 );  
            setColumnRenderer( "Picture", new ImageCellRendererClass( tableModel, log, 120, 150 ) );
            
    }        


    
}
