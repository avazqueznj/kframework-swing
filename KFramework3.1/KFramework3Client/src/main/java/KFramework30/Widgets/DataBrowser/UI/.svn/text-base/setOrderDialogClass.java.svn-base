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

//rtl
import KFramework30.Widgets.DataBrowser.UI.customOrderClass;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

// ale utils
import KFramework30.Base.*;
import KFramework30.Widgets.KDataBrowserBaseClass;



public class setOrderDialogClass extends javax.swing.JDialog 
                implements ListSelectionListener{

        //uses
	private KConfigurationClass configuration;
	private KLogClass log;
        private java.awt.Window parentWindow;
        
	// has - defaulted
	private customOrderClass listFiller;
        
    /** Creates new form setOrderDialogClass */
    public setOrderDialogClass( KConfigurationClass configurationParam, KLogClass logParam,  
                                java.awt.Window parentWindowParam,
                                KDataBrowserBaseClass tableFillerParam ) 
    throws KExceptionClass                                    
    {
        
        super( parentWindowParam, java.awt.Dialog.ModalityType.DOCUMENT_MODAL );
        
        //uses
        configuration = configurationParam;
        log = logParam;    
        parentWindow = parentWindowParam;
       
        //has ---- default
        
        initComponents ();   
        pack ();        
        // width, height, position
        setSize( 650, 293 );
        KMetaUtilsClass.centerInScreen( this );                
  
        //fill the source list
        listFiller = new customOrderClass( configuration, log, sourceList, destinationList, tableFillerParam );        
        listFiller.fillList();
        
        //set destination list to single selection
        destinationList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        
        //Registering listener class
        sourceList.addListSelectionListener( this );
        destinationList.addListSelectionListener( this ); 
        
        //Select the first item.
        if( sourceList.getModel().getSize() != 0 )
            sourceList.setSelectedIndex( 0 ); 

        setButtonStates();      
        
        // set pictures
        // ToRight.setIcon( new ImageIcon( systemResources.getImage( "toright1.jpg" ) ) );
        // ToLeft.setIcon( new ImageIcon( systemResources.getImage( "toleft1.jpg" ) ) );
        // up.setIcon( new ImageIcon( systemResources.getImage( "up1.jpg" ) ) );
        // down.setIcon( new ImageIcon( systemResources.getImage( "down1.jpg" ) ) );
        
        
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        setOrderPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scrollerFrom = new javax.swing.JScrollPane();
        sourceList = new javax.swing.JList();
        ToRight = new javax.swing.JButton();
        ToLeft = new javax.swing.JButton();
        clearAll = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        scrollerTo = new javax.swing.JScrollPane();
        destinationList = new javax.swing.JList();
        up = new javax.swing.JButton();
        down = new javax.swing.JButton();
        ASCDSC = new javax.swing.JButton();
        OK = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sorting");
        setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        setModal(true);
        setName("Set order"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        setOrderPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sort", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 10));
        jLabel2.setText("Available Columns");

        sourceList.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        scrollerFrom.setViewportView(sourceList);

        ToRight.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        ToRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/toright1.jpg"))); // NOI18N
        ToRight.setText("Add");
        ToRight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ToRight.setMargin(new java.awt.Insets(2, 2, 2, 2));
        ToRight.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToRightActionPerformed(evt);
            }
        });

        ToLeft.setFont(new java.awt.Font("Arial", 0, 10));
        ToLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/toleft1.jpg"))); // NOI18N
        ToLeft.setText("Remove");
        ToLeft.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ToLeft.setMargin(new java.awt.Insets(2, 2, 2, 2));
        ToLeft.setMinimumSize(new java.awt.Dimension(50, 50));
        ToLeft.setPreferredSize(new java.awt.Dimension(50, 50));
        ToLeft.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ToLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToLeftActionPerformed(evt);
            }
        });

        clearAll.setFont(new java.awt.Font("Arial", 0, 10));
        clearAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/remove1.jpg"))); // NOI18N
        clearAll.setText("Clear");
        clearAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clearAll.setMargin(new java.awt.Insets(2, 2, 2, 2));
        clearAll.setMaximumSize(new java.awt.Dimension(50, 50));
        clearAll.setMinimumSize(new java.awt.Dimension(50, 50));
        clearAll.setPreferredSize(new java.awt.Dimension(50, 50));
        clearAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel3.setText("Sort");

        scrollerTo.setFont(new java.awt.Font("Arial", 0, 10));

        destinationList.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        scrollerTo.setViewportView(destinationList);

        up.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        up.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/up1.jpg"))); // NOI18N
        up.setText("Move Up");
        up.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        up.setMargin(new java.awt.Insets(2, 2, 2, 2));
        up.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upActionPerformed(evt);
            }
        });

        down.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        down.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/down1.jpg"))); // NOI18N
        down.setText("Move Down");
        down.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        down.setMargin(new java.awt.Insets(1, 1, 1, 1));
        down.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downActionPerformed(evt);
            }
        });

        ASCDSC.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        ASCDSC.setText("ASC/DSC");
        ASCDSC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ASCDSC.setMargin(new java.awt.Insets(2, 2, 2, 2));
        ASCDSC.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ASCDSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASCDSCActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout setOrderPanelLayout = new org.jdesktop.layout.GroupLayout(setOrderPanel);
        setOrderPanel.setLayout(setOrderPanelLayout);
        setOrderPanelLayout.setHorizontalGroup(
            setOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(setOrderPanelLayout.createSequentialGroup()
                .add(3, 3, 3)
                .add(setOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(scrollerFrom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(setOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ToRight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(ToLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(clearAll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(setOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(setOrderPanelLayout.createSequentialGroup()
                        .add(70, 70, 70)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(setOrderPanelLayout.createSequentialGroup()
                        .add(scrollerTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 190, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(setOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(up, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(down, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(ASCDSC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
        );
        setOrderPanelLayout.setVerticalGroup(
            setOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(setOrderPanelLayout.createSequentialGroup()
                .add(setOrderPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(setOrderPanelLayout.createSequentialGroup()
                        .add(32, 32, 32)
                        .add(ToRight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(ToLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(clearAll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(setOrderPanelLayout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(scrollerTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(setOrderPanelLayout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(up, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(5, 5, 5)
                        .add(down, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 34, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(46, 46, 46)
                        .add(ASCDSC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(setOrderPanelLayout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(scrollerFrom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setOrderPanel.setBounds(5, 5, 630, 210);
        jLayeredPane1.add(setOrderPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        OK.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        OK.setText("Ok");
        OK.setName(""); // NOI18N
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        Cancel.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 633, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(451, Short.MAX_VALUE)
                .add(OK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(Cancel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(OK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Cancel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void clearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearAllActionPerformed
      
    try{

        DefaultListModel sourceListModel = (DefaultListModel) sourceList.getModel();
        DefaultListModel destinationListModel = (DefaultListModel) destinationList.getModel();
        int size = destinationListModel.size();
        
        for( int i = 0; i < size; i++ ) 
            //add to source list
            sourceListModel.addElement( destinationListModel.get(i) );   

        //clear all in destination list
        destinationListModel.clear();
        setButtonStates();  
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    }   
    
  }//GEN-LAST:event_clearAllActionPerformed

  private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
// Add your handling code here:
    try {      
        listFiller.applyOrder( );
    }
    catch( Exception error	){             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    };   
    
    //close dialog
    setVisible (false);
    dispose ();      
  }//GEN-LAST:event_OKActionPerformed

  private void downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downActionPerformed
     
      try {
          
        DefaultListModel destinationListModel = (DefaultListModel) destinationList.getModel();

        if( !destinationList.isSelectionEmpty() ) {  
            int index = destinationList.getSelectedIndex();

            //not last item
            if( index != destinationListModel.getSize( ) - 1 ) {
                destinationListModel.insertElementAt( destinationList.getSelectedValue(), index+2 );
                destinationList.setSelectedIndex( index+2 ); 
                destinationListModel.remove( index );  
            }
        }  
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    }
    
  }//GEN-LAST:event_downActionPerformed

     private void setButtonStates()
     {
        //enable and disable components
        if( sourceList.isSelectionEmpty() )
            ToRight.setEnabled( false );
        else
            ToRight.setEnabled( true );
        
        
        if( destinationList.isSelectionEmpty() ) {
            
            ToLeft.setEnabled( false );
            up.setEnabled( false );
            down.setEnabled( false );
            ASCDSC.setEnabled( false );
        }
        else {
            
            ToLeft.setEnabled( true );
            up.setEnabled( true );
            down.setEnabled( true );
            ASCDSC.setEnabled( true );
        }  
        
        if( ( (DefaultListModel) destinationList.getModel() ).size() == 0 ) {
            clearAll.setEnabled( false );
        }
        else {
            clearAll.setEnabled( true );           
        }
        
     }
    
  private void ToLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToLeftActionPerformed
      
      try{
          
        int index = 0;
        DefaultListModel sourceListModel = (DefaultListModel) sourceList.getModel();
        DefaultListModel destinationListModel = (DefaultListModel) destinationList.getModel();

        while( !destinationList.isSelectionEmpty() ) {
            index = destinationList.getSelectedIndex();

            //add to destination list
            sourceListModel.addElement(destinationList.getSelectedValue());
            int size = sourceListModel.getSize();
            sourceList.setSelectedIndex( size - 1 ); 

            //delete from source list
            destinationListModel.remove( index );     
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

        setButtonStates();  
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    }    
  }//GEN-LAST:event_ToLeftActionPerformed

  private void upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upActionPerformed

      try {
          
        DefaultListModel destinationListModel = (DefaultListModel) destinationList.getModel();

        if( !destinationList.isSelectionEmpty() ) {  
            int index = destinationList.getSelectedIndex();

            //not first item
            if( index != 0 ) {
                destinationListModel.insertElementAt( destinationList.getSelectedValue(), index-1 );
                destinationListModel.remove( index+1 );  
                destinationList.setSelectedIndex( index-1 ); 
            }
        }
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    }      
      
  }//GEN-LAST:event_upActionPerformed

  private void ToRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToRightActionPerformed

      try{
          
        int index = 0;
        DefaultListModel sourceListModel = (DefaultListModel) sourceList.getModel();
        DefaultListModel destinationListModel = (DefaultListModel) destinationList.getModel();

        while( !sourceList.isSelectionEmpty() ) {
            index = sourceList.getSelectedIndex();

            //add to destination list
            destinationListModel.addElement(sourceList.getSelectedValue());
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

        setButtonStates(); 
    }
    catch( Exception error	){
             
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    }      
    
  }//GEN-LAST:event_ToRightActionPerformed

  private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed

        setVisible (false);
        dispose ();      
  }//GEN-LAST:event_CancelActionPerformed

  private void toLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toLeftActionPerformed
// Add your handling code here:
  }//GEN-LAST:event_toLeftActionPerformed

  private void toRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toRightActionPerformed
     

            
        
   /*  int index = sourceList.getSelectedIndex();

      if( index != -1 ) //{
        String source = sourceList.getModel().getElementAt( index );
       // ResultList.getModel().
  
      String source = sourceList.getSelectedValue();
        if( source != null )*/
          //  ResultList.
  }//GEN-LAST:event_toRightActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible (false);
        dispose ();
    }//GEN-LAST:event_closeDialog

    
    
    
    
    private void ASCDSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASCDSCActionPerformed

        
        toggleSortOrder();
        
    }//GEN-LAST:event_ASCDSCActionPerformed


public void toggleSortOrder(){
    
        try {

            DefaultListModel destinationListModel = (DefaultListModel) destinationList.getModel();

            if( !destinationList.isSelectionEmpty() ) {  
                
                // save index
                int index = destinationList.getSelectedIndex();

                // get current value at index
                String selectedField = (String) destinationList.getSelectedValue();
                
                // get sort mode
                String sortMode = selectedField.substring( selectedField.length() - 3 , selectedField.length() );
                
                // toggle
                if(  sortMode.equals( "DSC" ) ){
                    
                    sortMode = "ASC";
                    
                }else{
                    
                    sortMode = "DSC";                    
                    
                }
                
                // set new value
                destinationListModel.setElementAt( 
                        selectedField.substring( 0, selectedField.length() - 3 ) + sortMode, 
                        index);
                
            }
        }
        catch( Exception error	){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
        }      
    
    
}    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ASCDSC;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton OK;
    private javax.swing.JButton ToLeft;
    private javax.swing.JButton ToRight;
    private javax.swing.JButton clearAll;
    private javax.swing.JList destinationList;
    private javax.swing.JButton down;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane scrollerFrom;
    private javax.swing.JScrollPane scrollerTo;
    private javax.swing.JPanel setOrderPanel;
    private javax.swing.JList sourceList;
    private javax.swing.JButton up;
    // End of variables declaration//GEN-END:variables

    /** Process for lists selectoin change */
    public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        //enable and disable components
        setButtonStates();  
    }
    
}

