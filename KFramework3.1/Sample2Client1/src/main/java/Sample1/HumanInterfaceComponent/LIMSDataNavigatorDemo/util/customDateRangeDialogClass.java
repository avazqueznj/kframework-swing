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

package Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util;

// rtl
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;


//K
import KFramework30.Base.*;
import KFramework30.Communication.*;
import KFramework30.Printing.*;
import KFramework30.Widgets.*;
import KFramework30.Widgets.TreeView.*;


public class customDateRangeDialogClass extends javax.swing.JDialog {

    // constants
    public static final int STATUS_OK = 0;
    public static final int STATUS_CANCEL = 1;    
    
    // uses
    private KConfigurationClass  configuration;
    private KLogClass            log;
    private java.awt.Window      parentWindow;
       
    // has
    private int                 status = STATUS_CANCEL;
    
    /** Creates new form newDailyMeasureMonthDialogClass */
    public customDateRangeDialogClass( 
        KConfigurationClass configurationParam, KLogClass logParam, java.awt.Window parentWindowParam
        ) throws KExceptionClass{
        
        super( parentWindowParam, java.awt.Dialog.ModalityType.DOCUMENT_MODAL );
        initComponents ();
        pack ();
        
        // uses
        configuration = configurationParam;
        log = logParam;
        parentWindow = parentWindowParam;
        
        // has
        
        // width, height, position
        setSize( 400, 250 );
        KMetaUtilsClass.centerInScreen( this );
        topLabel.setIcon( new javax.swing.ImageIcon(getClass().getResource( "/resources/topBar.jpg" ) ) );           
        
        KMetaUtilsClass.setFieldValidator( customRangeStartDate, KMetaUtilsClass.DATE_FIELD );
        KMetaUtilsClass.setFieldValidator( customRangeEndDate, KMetaUtilsClass.DATE_FIELD );                
    }

    public int showDialog(){ 
        
        super.setVisible( true );
        
        return( status );
    }
    
    public void setStartDate( String customRangeStartDateParam ){ 
        customRangeStartDate.setText( customRangeStartDateParam ); 
    }    
    
    public void setEndDate( String customRangeEndDateParam ){ 
        customRangeEndDate.setText( customRangeEndDateParam ); 
    }   

    public String getStartDate(){ return customRangeStartDate.getText(); }    
    public String getEndDate(){ return customRangeEndDate.getText(); }   
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        banner = new javax.swing.JLabel();
        topLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        customRangeEndDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        customRangeStartDate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        banner.setText("Custom date filter");
        banner.setForeground(java.awt.Color.gray);
        banner.setFont(new java.awt.Font("Arial", 0, 24));
        banner.setBounds(50, 5, 350, 45);
        jLayeredPane1.add(banner, new Integer(5));

        topLabel.setForeground(java.awt.Color.white);
        topLabel.setFont(new java.awt.Font("Dialog", 1, 24));
        topLabel.setBounds(0, 0, 1024, 55);
        jLayeredPane1.add(topLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setText("Start date");
        jLabel3.setFont(new java.awt.Font("Arial", 0, 10));

        okButton.setFont(new java.awt.Font("Arial", 0, 10));
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Arial", 0, 10));
        cancelButton.setText("CANCEL");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("End date");
        jLabel1.setFont(new java.awt.Font("Arial", 0, 10));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(35, 35, 35)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(customRangeStartDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(customRangeEndDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(65, 65, 65)
                .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(279, 279, 279)
                .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(customRangeStartDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(customRangeEndDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(8, 8, 8)
                .add(cancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBounds(9, 65, 400, 100);
        jLayeredPane1.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 423, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 178, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

    try{
             
        setVisible (false);
        dispose ();        
        
    } catch( Exception error ){
        
        // log error
        log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
        // show error message
        KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
    };
          
                  
  }//GEN-LAST:event_cancelButtonActionPerformed

  
  
  private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed

    try{


        if( customRangeStartDate.getText().equals( "" ) || customRangeEndDate.getText().equals( "" ) ){
          
            throw new KExceptionClass(
                "\n*** Could not apply date range ***\n" +
                "Start or end date is blank. You must fill both, start and end date.", null
            );
        };
        
        //KMetaUtilsClass.dateValidation( customRangeStartDate.getText() , "Start date" );        
        //KMetaUtilsClass.dateValidation( customRangeEndDate.getText() , "End date" );        
        
        status = STATUS_OK;
        
        setVisible (false);
        dispose ();        
        
        
    } catch( Exception error ){
        
        // log error
        log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
        // show error message
        KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
    };
      
      
  }//GEN-LAST:event_okButtonActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible (false);
        dispose ();
    }//GEN-LAST:event_closeDialog



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField customRangeEndDate;
    private javax.swing.JTextField customRangeStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel topLabel;
    // End of variables declaration//GEN-END:variables

    
    
    
}
