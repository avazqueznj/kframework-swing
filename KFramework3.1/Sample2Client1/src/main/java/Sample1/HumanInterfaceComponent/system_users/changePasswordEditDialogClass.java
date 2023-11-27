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



package Sample1.HumanInterfaceComponent.system_users;


// rtl
import KFramework30.Base.*;

// app
import KFramework30.Communication.persistentObjectManagerClass;
import ProblemDomainComponent.systemUserClass;

/**
 *Created on Apr 20, 2009
 * changePasswordEditDialogClass  -- Pantalla para cambio de password
 * @author MIH
 */
public class changePasswordEditDialogClass extends javax.swing.JDialog 
{
        
    // uses
    private KConfigurationClass  configuration;
    private KLogClass            log;

    
    // has defaulted
    private systemUserClass     usuario;   
    
    
    //start of object persistent functions--------------------------------------------------------------------------

            public void editUser( )
            throws KExceptionClass
            {
                usuario = new systemUserClass();               

                persistentObjectManagerClass persistentObjectManager =
                        new persistentObjectManagerClass( configuration, log );            

                long id = KMetaUtilsClass.getIntegralNumericValueFromString( configuration.getField( "system_user_id") );
                usuario = (systemUserClass) persistentObjectManager.copy4( id, usuario.getClass() );
                
                system_user_id.setText(
                        KMetaUtilsClass.toDecimalString( usuario.getSystem_user_id() ));
                system_user_name.setText(usuario.getSystem_user_name() );
            }

            public void updateUser( )
            throws KExceptionClass
            {
                persistentObjectManagerClass persistentObjectManager =
                        new persistentObjectManagerClass( configuration, log );  
                
                usuario.setSystem_user_password( new String( new_password.getPassword() ) );
                usuario = (systemUserClass) persistentObjectManager.update4( usuario.getSystem_user_id(), usuario );
            }

    //end of object persistent functions------------------------------------------------------

    /** Creates new form changePasswordEditDialogClass */
    public changePasswordEditDialogClass( 
        KConfigurationClass configurationParam,
        KLogClass logParam, java.awt.Window parentWindow )
        throws KExceptionClass
    {
        
        super( parentWindow, java.awt.Dialog.ModalityType.DOCUMENT_MODAL );
        initComponents ();
        pack ();
        
        KMetaUtilsClass.centerInScreen( this );
        
        // uses
        configuration = configurationParam;
        log = logParam;
        
        // has - defaulted         
        
        
        // Obtiene los datos del usuario
        //edit user
        editUser( );

        log.log( this, "Obtiene los datos del usuario" );
        
    }
    
    
    //-----------------------------------------------------------------------
   
     private void ApplyChanges() {
        
        
      try{
          
         validateData(); // Valida que los datos capturados sean correctos
      
         updateUser();
         setVisible (false);
         dispose ();

      }
      catch( Exception error ){
          // log error
          log.log( this, KMetaUtilsClass.getStackTrace( error ) );
          // show error message
          KMetaUtilsClass.showErrorMessageFromException( getOwner(), error );
      } 
    }   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel idLbl = new javax.swing.JLabel();
        javax.swing.JLabel usuarioLbl = new javax.swing.JLabel();
        javax.swing.JLabel originalLbl = new javax.swing.JLabel();
        system_user_id = new javax.swing.JLabel();
        system_user_name = new javax.swing.JTextField();
        original_password = new javax.swing.JPasswordField();
        new_password = new javax.swing.JPasswordField();
        javax.swing.JLabel newPasswordLbl = new javax.swing.JLabel();
        javax.swing.JLabel confirmaPasswordLbl = new javax.swing.JLabel();
        password_confirmation = new javax.swing.JPasswordField();
        textLabel = new javax.swing.JLabel();
        topLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Change Password ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        idLbl.setFont(new java.awt.Font("Arial", 0, 10));
        idLbl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        idLbl.setText("ID");

        usuarioLbl.setFont(new java.awt.Font("Arial", 0, 10));
        usuarioLbl.setText("Username");

        originalLbl.setFont(new java.awt.Font("Arial", 0, 10));
        originalLbl.setText("Current Password");

        system_user_id.setFont(new java.awt.Font("Arial", 0, 10));
        system_user_id.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        system_user_id.setName("system_user_id"); // NOI18N

        system_user_name.setEditable(false);
        system_user_name.setFont(new java.awt.Font("Arial", 0, 10));
        system_user_name.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        system_user_name.setName("system_user_name"); // NOI18N

        original_password.setFont(new java.awt.Font("Arial", 0, 10));
        original_password.setName("system_user_password"); // NOI18N

        new_password.setFont(new java.awt.Font("Arial", 0, 10));
        new_password.setName("system_user_password"); // NOI18N

        newPasswordLbl.setFont(new java.awt.Font("Arial", 0, 10));
        newPasswordLbl.setText("New Password");

        confirmaPasswordLbl.setFont(new java.awt.Font("Arial", 0, 10));
        confirmaPasswordLbl.setText("Confirm New Password");

        password_confirmation.setFont(new java.awt.Font("Arial", 0, 10));
        password_confirmation.setName("system_user_password"); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(system_user_id, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(idLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(usuarioLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(system_user_name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 280, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(jPanel1Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(originalLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(original_password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(newPasswordLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(100, 100, 100)
                .add(confirmaPasswordLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(4, 4, 4)
                .add(new_password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(50, 50, 50)
                .add(password_confirmation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(system_user_id, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(idLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(usuarioLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(system_user_name, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(10, 10, 10)
                .add(originalLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(original_password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(newPasswordLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(confirmaPasswordLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(new_password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(password_confirmation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBounds(5, 57, 400, 140);
        jLayeredPane1.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textLabel.setFont(new java.awt.Font("Verdana", 2, 24));
        textLabel.setForeground(java.awt.Color.gray);
        textLabel.setText("Change Password");
        textLabel.setBounds(160, 0, 280, 50);
        jLayeredPane1.add(textLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        topLabel.setFont(new java.awt.Font("Dialog", 1, 24));
        topLabel.setForeground(java.awt.Color.white);
        topLabel.setMaximumSize(new java.awt.Dimension(100, 44));
        topLabel.setMinimumSize(new java.awt.Dimension(100, 44));
        topLabel.setBounds(0, 0, 500, 55);
        jLayeredPane1.add(topLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        okButton.setFont(new java.awt.Font("Arial", 0, 10));
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        okButton.setBounds(410, 80, 80, 20);
        jLayeredPane1.add(okButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        CancelButton.setFont(new java.awt.Font("Arial", 0, 10));
        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });
        CancelButton.setBounds(410, 110, 80, 20);
        jLayeredPane1.add(CancelButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 490, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
        
  private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
      ApplyChanges();
  }//GEN-LAST:event_okButtonActionPerformed
  
  private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
      setVisible (false);
      dispose ();
  }//GEN-LAST:event_CloseButtonActionPerformed
  
  
  
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible (false);
        dispose ();
    }//GEN-LAST:event_closeDialog
    
    // Valida los datos requeridos y que sean correctos
    public void validateData() throws KExceptionClass{
        
        //Valida que el password proporciondo corresponda al actual
        if (new String(original_password.getPassword()).equals(""))
            throw new KExceptionClass("Error, enter old password" ,null );
        
        if (!new String(original_password.getPassword()).equals(usuario.getSystem_user_password()))
            throw new KExceptionClass( "Password has been used already" ,null );
        
        //Valida que el password de confirmaciÃ³n correponda a la confirmaciÃ³n
        if (new String( new_password.getPassword()).equals(""))
            throw new KExceptionClass("Error null password" ,null );
        
        if (new String( password_confirmation.getPassword()).equals(""))
            throw new KExceptionClass("Please enter password confirmation" ,null );
        
        if (!new String(new_password.getPassword()).equals( new String(password_confirmation.getPassword()) ))
            throw new KExceptionClass("New password does not match confirmation" ,null );
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField new_password;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField original_password;
    private javax.swing.JPasswordField password_confirmation;
    private javax.swing.JLabel system_user_id;
    private javax.swing.JTextField system_user_name;
    private javax.swing.JLabel textLabel;
    private javax.swing.JLabel topLabel;
    // End of variables declaration//GEN-END:variables
    
}
