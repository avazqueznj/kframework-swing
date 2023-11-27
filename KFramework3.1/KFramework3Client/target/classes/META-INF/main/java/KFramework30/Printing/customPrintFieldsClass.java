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

import javax.swing.*;
import java.util.*;

// ale utils
import KFramework30.Base.*;

public class customPrintFieldsClass extends java.lang.Object {

    // uses
    private KConfigurationClass  configuration;    
    private KLogClass            log;
    private KPrintDataTableClass      DBPrinter;    
    
    private JList               visualSourceList;
    private JList               visualDestinationList;    
   
    private java.util.List             initialFieldsList;   
    
    // has - defaulted 
    private java.util.List             printableFieldsList;     
    private DefaultListModel           sourceListModel;
    //destinationList displays the readable field names in printableFieldsList.
    // The two lists items are related to each other via the same index.
    private DefaultListModel           destinationListModel;   
    
    /** Creates new customPrintFieldsClass */
    public customPrintFieldsClass( KConfigurationClass configurationParam,
                                   KLogClass logParam, JList sourceListParam, 
                                   JList destinationListParam, 
                                   KPrintDataTableClass DBPrinterParam ) 
    {
        
        // uses
        configuration = configurationParam;    
        log = logParam;
        DBPrinter = DBPrinterParam;    

        visualSourceList = sourceListParam;
        visualDestinationList = destinationListParam;            
        
        initialFieldsList = DBPrinter.getDefaultPrintingFields();
                
        //has ---- defaulted
        printableFieldsList = DBPrinter.getPrintingFields();
        //make an copy of printableFieldsList
        printableFieldsList =  new ArrayList( printableFieldsList );
        
        sourceListModel = new DefaultListModel();       
        destinationListModel = new DefaultListModel();   

        //MaAlejandro Vazquez, Ke Li, Behaven Patel, Maricela Islasst model editable
        visualSourceList.setModel( sourceListModel );      
        visualDestinationList.setModel( destinationListModel );            
         
    }

    
    /** Fill the source list and empty destination list */
    public void clear( )      
    {
        sourceListModel.clear();
        destinationListModel.clear();
        printableFieldsList.clear();  
        
        //Initialize source list box        
        Iterator fields = initialFieldsList.iterator();   
        
        while( fields.hasNext() ){   
            printingFieldInfoClass fieldInfo = 
                    ( printingFieldInfoClass ) fields.next();  
            sourceListModel.addElement( (Object)fieldInfo.readableName );
        }        
        
    }
    
    
    /** initialize the source list and destination list */
    public void initializeLists( )       
    {       

        //Initialize destination list box
        Iterator fields = printableFieldsList.iterator();   
        
        while( fields.hasNext() ){   
                printingFieldInfoClass fieldInfo = 
                    ( printingFieldInfoClass ) fields.next();              
                String field = fieldInfo.readableName + " : " + fieldInfo.fieldWidth;
                destinationListModel.addElement( (Object)field );
        }                 

        //Initialize source list box        
        fields = initialFieldsList.iterator();   
        
        while( fields.hasNext() ){   
            printingFieldInfoClass fieldInfo = 
                    ( printingFieldInfoClass ) fields.next();  
            if( !printableFieldsList.contains( (Object)fieldInfo ) )
                sourceListModel.addElement( (Object)fieldInfo.readableName );
        }
                     
    } 
    
     
    /** Move the item inside of destination list */
    public void moveDestinationListItem( int fromIndex, int toIndex )  
    throws KExceptionClass
    {
        if( fromIndex == toIndex ) return;
        
        if( fromIndex >= destinationListModel.size() ||
            toIndex >= destinationListModel.size() ) 
                throw new KExceptionClass(
                "Printing fields indexes : [" + fromIndex + "] or [" + toIndex + 
                "] are out of the boundary. \n" , null  );   
        
        if( fromIndex > toIndex ) {
            //insert to new persition
            destinationListModel.add( toIndex, 
                                destinationListModel.get(fromIndex) );
            printableFieldsList.add( toIndex,
                                printableFieldsList.get(fromIndex) );        
            //delete the old one
            destinationListModel.remove( fromIndex+1 );  
            printableFieldsList.remove( fromIndex+1 );
        }
        else if( fromIndex < toIndex ){
            //insert to new persition
            destinationListModel.add( toIndex+1, 
                                destinationListModel.get(fromIndex) );
            printableFieldsList.add( toIndex+1,
                                printableFieldsList.get(fromIndex) );             
            //delete the old one
            destinationListModel.remove( fromIndex );  
            printableFieldsList.remove( fromIndex );
        }
    }
    
    
    /** Appends the specified element to the end of destination list */
    public void addToDestinationList( String fakeName )  
    {
        Iterator fields = initialFieldsList.iterator();   
        
        while( fields.hasNext() ){   
            printingFieldInfoClass fieldInfo = 
                    ( printingFieldInfoClass ) fields.next(); 
            if( fakeName.equals(fieldInfo.readableName) ) { 
                printableFieldsList.add( fieldInfo );   
                String field = fieldInfo.readableName + " : " + fieldInfo.fieldWidth;
                destinationListModel.addElement( (Object)field );
            }
        } 
    }
   
    
    /** Delete the specified element from the end of destination list */
    public void deleteFromDestinationList( int index )  
    throws KExceptionClass    
    {
        if( index >= destinationListModel.size() )
                throw new KExceptionClass(
                    "Printing field index : [" + index +  
                    "] is out of the boundary. \n" , null  );   
        
        destinationListModel.removeElementAt( index );
        printableFieldsList.remove( index );  
    }
    
    /** get the field readable name at index in printableFieldsList */
    public String getFieldReadableNameAt( int index )  
    {
        return ( (printingFieldInfoClass) 
                    printableFieldsList.get(index) ).readableName ;
    }    
  
    
    /** get the field width at index in printableFieldsList */
    public int getFieldWidthAt( int index )  
    {
        return ( (printingFieldInfoClass) 
                    printableFieldsList.get(index) ).fieldWidth;
    }
       
    
    /** save the field width at index in printableFieldsList */
    public void changeFieldWidthAt( int index, String width ) throws KExceptionClass  
    {
        printingFieldInfoClass fieldInfo = 
           (printingFieldInfoClass) printableFieldsList.get(index);

        //change the field width.
        fieldInfo.fieldWidth = (int) KMetaUtilsClass.getIntegralNumericValueFromString( width );
        //Replaces the new at the specified position in this list.
        String field = fieldInfo.readableName + " : " + width;
        destinationListModel.set( index, (Object) field );
    }    

    /** Tell DBPrinter the printing fields */
    public void setPrintingFields( )  
    {    
        DBPrinter.setPrintingFields( printableFieldsList );
    }
    
}
