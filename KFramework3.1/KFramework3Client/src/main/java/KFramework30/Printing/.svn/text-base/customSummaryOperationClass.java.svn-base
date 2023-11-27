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

public class customSummaryOperationClass extends Object {
    static  final String []operationNames = { "CNT", "SUM", "AVG", "" };
    
    // uses
    private KConfigurationClass  configuration;    
    private KLogClass            log;
    private KPrintDataTableClass      DBPrinter;    
    
    // has - defaulted    
    //An initialFieldsList's element is related to the same one in visual list 
    //via the same index. 
    private java.util.List             initialFieldsList;    
    private DefaultListModel           fieldListModel;   
    
    /** Creates new customSummaryOperationClass */
    public customSummaryOperationClass( KConfigurationClass configurationParam,
                                   KLogClass logParam, 
                                   JList fieldListParam, 
                                   KPrintDataTableClass DBPrinterParam ) 
    {
        // uses
        configuration = configurationParam;    
        log = logParam;
        DBPrinter = DBPrinterParam;    

        //has ---- defaulted        
        initialFieldsList = DBPrinter.getDefaultPrintingFields();
        //make an copy of initialFieldsList
        initialFieldsList =  new ArrayList( initialFieldsList );       
        fieldListModel = new DefaultListModel();   

        //MaAlejandro Vazquez, Ke Li, Behaven Patel, Maricela Islasst model editable
        fieldListParam.setModel( fieldListModel );            
        
    }
    
    /** initialize the source list and destination list */
    public void initializeLists( )       
    {       
        //Initialize list boxe        
        Iterator fields = initialFieldsList.iterator();   
        
        while( fields.hasNext() ){   
            printingFieldInfoClass fieldInfo = 
                    ( printingFieldInfoClass ) fields.next();  
                //add to the list
                String field = fieldInfo.readableName + " : " + 
                                        operationNames[fieldInfo.operation];
                fieldListModel.addElement( (Object)field );
        }
                     
    } 
    
        
    /** get the field information from destinationList at 'index' */
    public printingFieldInfoClass getFieldInfo( int index )  
    throws KExceptionClass
    {
        if( index >= fieldListModel.size() )
                throw new KExceptionClass(
                    "Summary field index : [" + index +  
                    "] is out of the boundary. \n" , null  );   
        
        printingFieldInfoClass fieldInfo = 
                  (printingFieldInfoClass) initialFieldsList.get(index);
        return fieldInfo;
    }   
    
   
    /** update the field info in visualFieldList at 'index' */
    public void updateVisualFieldList( int index )
    throws KExceptionClass
    {
        if( index >= fieldListModel.size() )
                throw new KExceptionClass(
                    "Summary field index : [" + index +  
                    "] is out of the boundary. \n" , null  );   
        
        //find the field
        printingFieldInfoClass fieldInfo =
                (printingFieldInfoClass) initialFieldsList.get( index ); 
        String element = fieldInfo.readableName + " : " + 
                                    operationNames[fieldInfo.operation];
        
        fieldListModel.set( index, (Object) element);
    }
    
    /** Tell DBPrinter the initial fields */
    public void setDefaultPrintingFields( )  
    {    
        DBPrinter.setDefaultPrintingFields( initialFieldsList );
    }    
    
}
