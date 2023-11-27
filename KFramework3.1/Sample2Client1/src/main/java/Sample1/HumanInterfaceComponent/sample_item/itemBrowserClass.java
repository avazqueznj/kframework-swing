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

package Sample1.HumanInterfaceComponent.sample_item;



import javax.swing.*;
import java.awt.*;

// utilities
import KFramework30.Widgets.*;
import KFramework30.Base.*;
import KFramework30.Communication.persistentObjectManagerClass;

// system
import ProblemDomainComponent.sample_facturaClass;
import Sample1.HumanInterfaceComponent.system_mail.*;
import ProblemDomainComponent.sample_clientClass;
import ProblemDomainComponent.sample_itemClass;
import Sample1.HumanInterfaceComponent.sample_product.*;
import java.util.HashMap;


public class itemBrowserClass 
extends KDataBrowserBaseClass {   
    
        // constants
        public static final int                ITEM_BY_INVOICE = 0;
    
	// uses
        private int                             mode;
        private long                            parent_id;

	// has - defaulted
    
    /** Creates new viajeBrowserClass */
    public itemBrowserClass(
            KConfigurationClass configurationParam,
            KLogClass logParam,
            int modeParam,
            long parent_idParam,
            JTable JTableParam,
            java.awt.Window parentWindow ) throws KExceptionClass
    {
        
        
            // inherits
            super(
                    configurationParam, logParam,
                    true, JTableParam, parentWindow,  
                    sample_itemClass.class,
                    itemEditDialogClass.class
            );  
            
            // uses   
            mode = modeParam;
            parent_id = parent_idParam;
            
	    // has
            
            
    }        


    public void initializeTable()
    throws KExceptionClass
    {	      
              
            // set the SQL
            super.initializeSQLQuery( 
                
                // 1 campos                    
                " prod.prod_name , item.item_quantity , item.item_cost , ( item.item_quantity * item.item_cost )  as total , item.item_id ",
                
                // 2 tablas and joins                                             
                " sample_item item   " +
                " left join sample_product prod on item.prod_id = prod.prod_id "  ,
                                                   
                // 3 llave principal (mayusculas!)
                "ITEM_ID" 
                                                                                             
                    );   

            // define column settings
            setColumnNames( "prod", "PROD_NAME", "Description" );               
            setColumnNames( "item", "ITEM_QUANTITY", "Quantity" );               
            setColumnNames( "item", "ITEM_COST", "Unit Cost" );     
            setColumnNames( "", "TOTAL", "TOTAL" );  
                       
            setDefaultOrder( " ITEM_ID " );

            // only one mode ITEM_BY_INVOICE
            setDefaultCriteria( " item.fac_id = ? " );
            bindDefaultParameter1( ":v1", parent_id );
              
            // hide the link id
            setVisibleColumnCount( 4 );
            
            // load data
            super.initializeTable();   
            
            // some customization
            adjustColumnType("Unit Cost", BROWSER_COLUMN_TYPE_CURRENCY );
            adjustColumnType("TOTAL", BROWSER_COLUMN_TYPE_CURRENCY );
            
            adjustColumnWidth( "Quantity", 100 );              
            adjustColumnFont(  "Quantity",  new Font( "arial", Font.BOLD, 10  ) );             
            adjustColumnForegroundColor( "Quantity", Color.BLUE  );
            
    }        

    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------
    // Event handling
    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------


  public void newButtonActionPerformed()
  {       
      
      try{
              long prodId;
              {
                  
                       //--------------------------------------------------
                       // Open Product catalogue
                       productBrowserClass productCatalog = new productBrowserClass(
                                configuration, log, new javax.swing.JTable(), getParentWindow()  );
                       productCatalog.initializeTable();   
                                              
                       selectDialogClass selector = new selectDialogClass( 
                               configuration, log, getParentWindow(), productCatalog, "Product Catalog");
                       
                       // dont want to allow this, for exaple
                       selector.getNewButton().setEnabled(false);
                       selector.getDeleteButton().setEnabled(false);                       
                       
                       prodId = selector.showDialog();
                       
                       if( prodId == -1 ) throw new KExceptionClass( "You must select a product ", null);      
                       
                       // Open Product catalogue                       
                       //--------------------------------------------------
                       
                }
   
                HashMap foreingKeys = new HashMap();
                foreingKeys.put( "facId", parent_id );       
                foreingKeys.put( "prodId", prodId );    
                super.newButtonActionPerformed( foreingKeys );
        }
        catch( Exception error	){           
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                KMetaUtilsClass.showErrorMessageFromException( getParentWindow(), error );
        }
   
                       
  }
  
  

    
}
