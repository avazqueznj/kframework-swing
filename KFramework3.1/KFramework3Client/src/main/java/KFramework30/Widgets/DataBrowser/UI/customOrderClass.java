/*
This source code is part of the KFRAMEWORK  (http://k-framework.sourceforge.net/)
Copyright (C) 2001  Alejandro Vazquez, Ke Li
Feedback / Bug Reports avazqueznj@users.sourceforge.net

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


package KFramework30.Widgets.DataBrowser.UI;

import javax.swing.*;

import java.util.*;

// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.KDataBrowserBaseClass;


public class customOrderClass extends java.lang.Object {

    // uses
    private KConfigurationClass  configuration;    
    private KLogClass            log; 
    
    private JList               visualSourceList;
    private JList               visualdestinationList;    
    private KDataBrowserBaseClass    tableFiller;    
    
    // has - defaulted
    private DefaultListModel           sourceListModel;     
    private DefaultListModel           destinationListModel;     

    
    /** Creates new listFillerClass */
    public customOrderClass(KConfigurationClass configurationParam,KLogClass logParam,
                            JList sourceListParam, JList destinationListParam, KDataBrowserBaseClass tableFillerParam) 
    {
        //Uses
        configuration = configurationParam;
        log = logParam;
        visualSourceList = sourceListParam;
        visualdestinationList = destinationListParam;
        tableFiller = tableFillerParam;
        
        //has ---- defaulted
        sourceListModel = new DefaultListModel();       
        destinationListModel = new DefaultListModel();               
    }
    
    /** Fill the lists with certain items */
    public void fillList( )
    throws KExceptionClass        
    {
        
        // get all columns
        java.util.List nameList = new ArrayList();
        tableFiller.getColumnNames( nameList );
        Iterator columnNames = nameList.iterator();   
        
        // get sort columns
        java.util.List customOrders = tableFiller.GetCustomOrderData();
        
        // fill source list
        while( columnNames.hasNext() ){   
            String column_name = ( String ) columnNames.next(); 
            if (!column_name.equals("Select") )
              if( !customOrders.contains( column_name ) )
                  sourceListModel.addElement( (Object)column_name + " ASC" );
        }
        
        // fill sort list
        Iterator orderNames = customOrders.iterator();   
        while( orderNames.hasNext() ){   
            String column_name = ( String ) orderNames.next();
            destinationListModel.addElement( (Object)column_name );
        }        
        
        // done ...
        visualSourceList.setModel( sourceListModel );      
        visualdestinationList.setModel( destinationListModel );  
    }  
 
    //----------------------------------------------------------------------   
    /**   Reload the table, because of changed SQL order.  */
    public void applyOrder( ) 
    throws KExceptionClass    
    {
        java.util.List orderList = tableFiller.GetCustomOrderData();
        orderList.clear();
        
        int size = destinationListModel.getSize( );

        // esta es la lista que guarda el SQL loader --->
        for( int i = 0; i < size; i++ ) {
            String name = ( String ) destinationListModel.get( i );
            orderList.add( name );
        }
        
        tableFiller.setCustomOrder( orderList );   // sets the SQL order
        
        tableFiller.refresh();  // refresh
    }

}
