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

//rtl
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// ale utils
import KFramework30.Base.*;

public class summaryActionListenerClass 
            implements ActionListener, ListSelectionListener {

        //uses
	private KConfigurationClass configuration;
	private KLogClass log; 

        private javax.swing.JList fieldList;        
        private javax.swing.JLabel fieldNameLabel;
        private javax.swing.JComboBox operationComboBox;
        private javax.swing.JTextField prefixTextField;
        private javax.swing.JTextField suffixTextField;
        private javax.swing.JComboBox precisionComboBox;   
        
        private java.awt.Window parentWindow;
        
        // has - defaulted
        private customSummaryOperationClass listFiller;
        
    /** Creates new summaryActionListenerClass */
    public summaryActionListenerClass(KConfigurationClass configurationParam, 
                                       KLogClass logParam, 
                                       java.awt.Window parentWindowParam,
                                       JList fieldListParam,
                    /*going to change*/JPanel panelParam,
                                       KPrintDataTableClass DBPrinterParam ) 
    throws KExceptionClass   
    {
        //uses
        configuration = configurationParam;
        log = logParam;
        
      
        fieldList = fieldListParam;
        
        //initialize the components
        Component[] componentArray = panelParam.getComponents();
        
        parentWindow = parentWindowParam;
        
        int i;
        fieldNameLabel = null;
        for( i=0; i<componentArray.length; i++ ) 
            if( componentArray[i].getName() == "fieldNameLabel" ) {
                fieldNameLabel = ( JLabel ) componentArray[i];
                break;
            }            
   
        operationComboBox = null;
        for( i=0; i<componentArray.length; i++ ) 
            if( componentArray[i].getName() == "operationComboBox" ) {
                operationComboBox = ( JComboBox ) componentArray[i];
                break;
            }
 
        prefixTextField = null;
        for( i=0; i<componentArray.length; i++ ) 
            if( componentArray[i].getName() == "prefixTextField" ) {
                prefixTextField = ( JTextField ) componentArray[i];
                break;
            }        

        suffixTextField = null;
        for( i=0; i<componentArray.length; i++ ) 
            if( componentArray[i].getName() == "suffixTextField" ) {
                suffixTextField = ( JTextField ) componentArray[i];
                break;
            }        

        precisionComboBox = null;
        for( i=0; i<componentArray.length; i++ ) 
            if( componentArray[i].getName() == "precisionComboBox" ) {
                precisionComboBox = ( JComboBox ) componentArray[i];
                break;
            } 
             
        // has - defaulted
        //fill the source list
        listFiller = new customSummaryOperationClass( configuration, log, 
                                fieldList, DBPrinterParam );  
        
        listFiller.initializeLists();
        
        //set destination list to single selection
        fieldList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        
        //Select the first item.
        DefaultListModel fieldListModel = (DefaultListModel) fieldList.getModel();
        if( fieldListModel.getSize() != 0 )
            fieldList.setSelectedIndex( 0 ); 
        
        setOperationPanel();
            
    }

    public void actionPerformed(java.awt.event.ActionEvent e ) {
        String command = e.getActionCommand();
        
        if( command.equals( "apply" ) )
            sumApplyButtonActionPerformed( e );                         
    }
    
  
  
    private void sumApplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
      try {
          if( !fieldList.isSelectionEmpty() ) {             
              int index = fieldList.getSelectedIndex();
              //get the selected field info.
              printingFieldInfoClass fieldInfo =
                                    listFiller.getFieldInfo( index ); 
            
            fieldInfo.prefix = prefixTextField.getText();
            fieldInfo.suffix = suffixTextField.getText();

            fieldInfo.operation = operationComboBox.getSelectedIndex();
            fieldInfo.precision = precisionComboBox.getSelectedIndex();
            
            listFiller.updateVisualFieldList( index );
          }
             
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    };   
  }

  
    public void setDefaultPrintingFields()
    {
        listFiller.setDefaultPrintingFields();
    }
    
        /** Process for list selectoin change */
    public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        try {
            setOperationPanel();
        }
        catch( Exception error	){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
        };   

    }
    
    
    private void setOperationPanel()
    throws KExceptionClass    
    {
        printingFieldInfoClass fieldInfo = 
            new printingFieldInfoClass( );
        
        if( !fieldList.isSelectionEmpty() ) {             
            int index = fieldList.getSelectedIndex();
            //get the selected field info.
            fieldInfo = listFiller.getFieldInfo( index ); 
        }

        fieldNameLabel.setText(fieldInfo.readableName);
        prefixTextField.setText(fieldInfo.prefix);
        suffixTextField.setText(fieldInfo.suffix);

        operationComboBox.setSelectedIndex( fieldInfo.operation );
        precisionComboBox.setSelectedIndex( fieldInfo.precision );  
        
    }
    
}


