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

package KFramework30.Widgets.DataBrowser.UI;

import KFramework30.Widgets.DataBrowser.UI.customCriteriaClass;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;


// ale utils
import KFramework30.Base.*;
import KFramework30.Widgets.KDataBrowserBaseClass;

public class setCriteriaDialogClass extends javax.swing.JDialog {

        //uses
	private KConfigurationClass  configuration;
	private KLogClass            log;
        private java.awt.Window      parentWindow;
        
        // has - defaulted   
        private customCriteriaClass tableModel;


        /**     Inner classes for cell input validation.
                Sets up a change-validated text field. It makes the customized text field 
                the editor for all columns that contain numeric data type.  */

        class criteriaDialogNumberFieldClass extends JTextField {
            protected Document createDefaultModel() {
                return new numberDocumentClass();
            }
        }


        class numberDocumentClass extends PlainDocument {

            public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException 
            {
                char[] source = str.toCharArray();
                char[] result = new char[source.length];
                int j = 0;

                //Followed are only characters allowed to type in.
                for (int i = 0; i < result.length; i++) {
                    if ( source[i] == 'N' || source[i] == 'n' ||
                         source[i] == 'O' || source[i] == 'o' ||
                         source[i] == 'T' || source[i] == 't' ||
                         source[i] == '<' || source[i] == '>' ||
                         source[i] == '.' || source[i] == ' ' ||
                         source[i] == ',' || 
                         Character.isDigit(source[i])  )
                        result[j++] = source[i];
                    else {
                        //toolkit.beep();
                    }
                }    
                super.insertString(offs, new String(result, 0, j), a);
            }
                
        }

     //-------------------------------------------------------------------------------       
    //Private interface
        
 
        
    /** Creates new form setCriteriaDialogClass */
    public setCriteriaDialogClass( KConfigurationClass configurationParam, KLogClass logParam, 
                                   java.awt.Window parentWindowParam,
                                   KDataBrowserBaseClass tableFillerParam)
    throws KExceptionClass                                       
    {
        super( parentWindowParam, java.awt.Dialog.ModalityType.DOCUMENT_MODAL );
        
        
        
        //uses
        configuration = configurationParam;
        log = logParam;
        parentWindow = parentWindowParam;
        
        initComponents (); 
        pack ();   
        
        // width, height, position
        setSize( 700, 500 );
        KMetaUtilsClass.centerInScreen( this );				
         
        //fill the criteria table
        tableModel = new customCriteriaClass( configuration, log, this, tableFillerParam );
        criteriaTable.setModel( tableModel );
        
        // set editors for numeric columns of criteria table.
        int columnCount = tableModel.getColumnCount( );
        
        for( int i = 0; i < columnCount; i++ ) {
            
            String columnName = tableModel.getColumnName( i );
           
            if (columnName.equals("Select")) {
                //Elimina la columna de seleccion
                criteriaTable.removeColumn( criteriaTable.getColumn( columnName ) );
            } else {
            
                if( tableFillerParam.isColumnNumeric(columnName) ) {                                                    
                    criteriaTable.getColumn( columnName ).setCellEditor(
                        new DefaultCellEditor( new criteriaDialogNumberFieldClass() ) );
                }

                criteriaTable.getColumn( columnName ).setPreferredWidth( 150 );            
            }
            
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        criteriaTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Clear = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        OKButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setTitle("Filter");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        criteriaTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(criteriaTable);

        jPanel2.setPreferredSize(new java.awt.Dimension(2048, 80));

        Clear.setFont(new java.awt.Font("Arial", 0, 10));
        Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/remove1.jpg"))); // NOI18N
        Clear.setText("Reset");
        Clear.setActionCommand("clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        CancelButton.setFont(new java.awt.Font("Arial", 0, 10));
        CancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/remove1.jpg"))); // NOI18N
        CancelButton.setText("Cancel");
        CancelButton.setActionCommand("cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        OKButton.setFont(new java.awt.Font("Arial", 0, 10));
        OKButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/go1.jpg"))); // NOI18N
        OKButton.setText("OK");
        OKButton.setActionCommand("ok");
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 10));
        jLabel1.setText("Enter your filter criteria on each column. Match is by partial data. Example: Enter 'Jers' for New Jersey or Jersey"); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 10));
        jLabel2.setText("For numbers, you can enter a range. Example: '>2 , <10' for 2,3,4 to 10  Each row is an 'OR'."); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 620, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 620, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(jPanel2Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(OKButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(CancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(Clear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel2)))
                .add(6, 6, 6)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(OKButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(CancelButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(Clear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 639, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 639, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 352, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        try {
            KMetaUtilsClass.stopTableCellEditing( criteriaTable );
            tableModel.clearAll();
        }
        catch( Exception error	){
		// log error
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
		KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
	};       
  }//GEN-LAST:event_ClearActionPerformed


  private void OKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonActionPerformed
        
    boolean success = false;
      
    try {                                   
            KMetaUtilsClass.stopTableCellEditing( criteriaTable );
            
            // if data not valid show fancy error message and return false
            if( tableModel.isValidTableData() ){
                
                tableModel.processCriteria();                                     
                success = true;
            };
                        
        }
        catch( Exception error	){
		// log error
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
		String message;
		message = "*** Could not apply filter *** \n";
		message += "Clean the criteria and try again.  \n";                
		//message += error.toString();
		//message += "]";
		KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
	};     
        
        //If isValidTableData method faild, leave the dialog open.
        if( success ){
            setVisible (false);
            dispose (); 
        }
        
  }//GEN-LAST:event_OKButtonActionPerformed

  private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        setVisible (false);
        dispose ();
  }//GEN-LAST:event_CancelButtonActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible (false);
        dispose ();
    }//GEN-LAST:event_closeDialog



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton Clear;
    private javax.swing.JButton OKButton;
    private javax.swing.JTable criteriaTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
 
}
