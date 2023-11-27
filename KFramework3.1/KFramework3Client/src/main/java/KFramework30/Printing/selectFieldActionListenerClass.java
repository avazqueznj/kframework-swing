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

// ale utils
import KFramework30.Base.*;

public class selectFieldActionListenerClass implements ActionListener {

        //uses
	private KConfigurationClass configuration;
	private KLogClass log;    
        private javax.swing.JList destinationList;
        private javax.swing.JList sourceList;
        java.awt.Window parentWindow;
       
        
        // has - defaulted
        private customPrintFieldsClass listFiller;

    
    /** Creates new selectFieldActionListenerClass */
    public selectFieldActionListenerClass( KConfigurationClass configurationParam, 
                                           KLogClass logParam, 
                                           java.awt.Window parentWindowParam,
                                           JList sourceListParam, 
                                           JList destinationListParam,
                                           KPrintDataTableClass DBPrinterParam ) 
    {
        configuration = configurationParam;
        log = logParam;
        sourceList = sourceListParam;
        destinationList = destinationListParam;
        parentWindow = parentWindowParam;
        
        
        //fill the source list
        listFiller = new customPrintFieldsClass( configuration, log, 
                                sourceList, destinationList, DBPrinterParam );  
        
        listFiller.initializeLists();
        
        //set destination list to single selection
        destinationList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        
        //Select the first item.
        DefaultListModel sourceListModel = (DefaultListModel) sourceList.getModel();
        if( sourceListModel.getSize() != 0 )
            sourceList.setSelectedIndex( 0 );     
            
    }

    
    
     
    public void actionPerformed(java.awt.event.ActionEvent e ) {
        String command = e.getActionCommand();
        
        if( command.equals( "Up" ) )
            upButtonActionPerformed( e );
        else if( command.equals( "Down" ) )
            downButtonActionPerformed( e );
        else if( command.equals( "Clear" ) )
            clearButtonActionPerformed( e );      
        else if( command.equals( "Width" ) )
            widthButtonActionPerformed( e );    
        else if( command.equals( "to left" ) )
            ToLeftActionPerformed( e );   
        else if( command.equals( "to right" ) )
            toRightButtonActionPerformed( e );           
            
    }
    
  
    private void widthButtonActionPerformed(java.awt.event.ActionEvent evt) {
      try {
            int index = destinationList.getSelectedIndex();
            if( index >= 0 ) {
                int fieldWidth = listFiller.getFieldWidthAt( index );      
                String fieldName = listFiller.getFieldReadableNameAt( index );  
                
                changeWidthDialogClass changeWidthDialog =
                        new changeWidthDialogClass( parentWindow, fieldName, String.valueOf(fieldWidth) );          

                // width, height, position
                changeWidthDialog.setSize( 263, 163 );
                changeWidthDialog.setTitle("Change field width");
                KMetaUtilsClass.centerInScreen( changeWidthDialog );				

                log.log( this, "Openning change width dialog completed." );                                  

                String newWidth = changeWidthDialog.askForWidth();
                
                if( newWidth.length() != 0 )
                    listFiller.changeFieldWidthAt( index, newWidth );                  
            }
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    };   
  }

  private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        listFiller.clear( );
       
        DefaultListModel sourceListModel = (DefaultListModel) sourceList.getModel();
        if( sourceListModel.getSize() != 0 )
            sourceList.setSelectedIndex( 0 );                  
  }



  private void ToLeftActionPerformed(java.awt.event.ActionEvent evt) {
      
      try{
          
        int index = 0;
        DefaultListModel sourceListModel = (DefaultListModel) sourceList.getModel();
        DefaultListModel destinationListModel = (DefaultListModel) destinationList.getModel();

        while( !destinationList.isSelectionEmpty() ) {
            index = destinationList.getSelectedIndex();

            //add to source list
            String field = ( String ) destinationList.getSelectedValue();
            //remove field width
            int i = field.lastIndexOf( " : " );
            field = field.substring(0, i);
            sourceListModel.addElement( field );
            int size = sourceListModel.getSize();
            sourceList.setSelectedIndex( size - 1 ); 

            //delete from destination list
            listFiller.deleteFromDestinationList( index );      
        }      

        int size = destinationListModel.getSize();

         //Adjust the selection
        if ( size != 0 )  {
            //removed item in last position
            if ( index == size )
                index--;
            //otherwise select same index
            destinationList.setSelectedIndex(index);   
        }      
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );
    };      
    
  }

  private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {
      try {
  
        DefaultListModel destinationListModel = 
                        (DefaultListModel) destinationList.getModel(); 
        
        if( !destinationList.isSelectionEmpty() ) {  
            int index = destinationList.getSelectedIndex();
            
            //not last item
            if( index != destinationListModel.getSize( ) - 1 ) {
                listFiller.moveDestinationListItem( index, index+1 );
                destinationList.setSelectedIndex( index+1 ); 
            }
        }
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    };      
    
  }

  private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {
      
      try {
          
        if( !destinationList.isSelectionEmpty() ) {  
            int index = destinationList.getSelectedIndex();

            //not first item
            if( index != 0 ) {
                listFiller.moveDestinationListItem( index, index-1 );
                destinationList.setSelectedIndex( index-1 ); 
            }
        }
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    };      
    
  }

  private void toRightButtonActionPerformed(java.awt.event.ActionEvent evt) {

      try{
          
            int index = 0;
            DefaultListModel sourceListModel = (DefaultListModel) sourceList.getModel();
            DefaultListModel destinationListModel = (DefaultListModel) destinationList.getModel();
            
            while( !sourceList.isSelectionEmpty() ) {
                index = sourceList.getSelectedIndex();
                String fakeName = ( String ) sourceList.getSelectedValue();
                
                //add to destination list
                listFiller.addToDestinationList( fakeName );  

                int size = destinationListModel.getSize();
                destinationList.setSelectedIndex( size - 1 ); 

                //delete from source list
                sourceListModel.remove( index );     
            }      

            int size = sourceListModel.getSize();

            //Adjust the selection
            if ( size != 0 ) {    
                //removed item in last position
                if ( index == size )
                    index--;
                //otherwise select same index
                sourceList.setSelectedIndex(index); 
            } 
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    };   
     
  }


    public void setPrintingFields()
    {
        listFiller.setPrintingFields();
    }
    
}
