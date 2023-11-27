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



package Sample1;

//rtl
import KFramework30.Base.*;
import KFramework30.Communication.persistentObjectManagerClass;
import ProblemDomainComponent.systemUserClass;
import java.util.*;

// ale utils
import java.util.Iterator;







public class logonScreenClass extends javax.swing.JDialog {

	// uses
	private KConfigurationClass configuration;
	private KLogClass log;

	// has - defaulted
	private boolean loginStatus = false;


        

    /** Creates new form logonScreenClass */
    public logonScreenClass(
	KConfigurationClass configurationParam,
	KLogClass logParam,
        java.awt.Window parentWindow
	// let propagate to caller
	) throws Exception
	{

	// inherits
        super( parentWindow, java.awt.Dialog.ModalityType.DOCUMENT_MODAL );  // modal
        initComponents ();

        setSize( 700, 490 );
        
        userNameField.requestFocus();

        // uses
	configuration = configurationParam;
	log = logParam;	

	// has - defaulted
	loginStatus = false;

	// custom code
        
        String propertiesString = new String();
        Iterator properties = System.getProperties().keySet().iterator();
        while( properties.hasNext() ){
            String currentProperty = (String) properties.next();
            propertiesString += currentProperty + "=" + System.getProperty( currentProperty ) + "\n";
        }

        propertiesString += "==========================\n";
        
        Iterator environ = System.getenv().keySet().iterator();                
        while( environ.hasNext() ){
            String currentEnviron = (String) environ.next();
            propertiesString += currentEnviron + "=" + System.getenv( currentEnviron ) + "\n";
        }
        
        
        log.log( this , propertiesString );
        
	log.log( this , "Constructed successfully!" );
    }

    //----------------------------------------------------------------------

	public boolean showLoginDialog(){    
	
		// show window
		show();
	
		// done return status
		return( loginStatus );
	}

    // -------------------------------------------------------------------------
        
        
        //inner class for focus control
        class focusLayeredPane extends javax.swing.JLayeredPane {
                public boolean isFocusCycleRoot()
                {
                    return true;
                }            
        }
        
        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        passwordField = new javax.swing.JPasswordField();
        userNameField = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        passwordLabel = new javax.swing.JLabel();
        passwordLabel2 = new javax.swing.JLabel();
        backgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setModal(true);
        setName("logonDialog"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Arial", 0, 10));

        userNameField.setFont(new java.awt.Font("Arial", 0, 10));
        userNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        okButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        okButton.setText("Login");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Arial", 1, 12));
        passwordLabel.setText("Username");
        passwordLabel.setRequestFocusEnabled(false);

        passwordLabel2.setFont(new java.awt.Font("Arial", 1, 12));
        passwordLabel2.setText("Password");
        passwordLabel2.setRequestFocusEnabled(false);

        backgroundImage.setBackground(new java.awt.Color(0, 0, 255));
        backgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/splash.jpg"))); // NOI18N
        backgroundImage.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(390, 390, 390)
                .add(passwordLabel))
            .add(layout.createSequentialGroup()
                .add(390, 390, 390)
                .add(userNameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(layout.createSequentialGroup()
                .add(390, 390, 390)
                .add(passwordLabel2))
            .add(layout.createSequentialGroup()
                .add(390, 390, 390)
                .add(passwordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(backgroundImage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 700, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(370, 370, 370)
                .add(passwordLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(userNameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(passwordLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(passwordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(okButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
            .add(backgroundImage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 480, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


  
  private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
// Add your handling code here:
  }//GEN-LAST:event_jTextField3ActionPerformed



    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog

	log.log( this, "Is closing..." );

        setVisible( false );
        dispose ();
    }//GEN-LAST:event_closeDialog

private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed

		log.log( this, "Logging user...." );

		try{

			// get a transaction
                        persistentObjectManagerClass loginTransaction 
				= new persistentObjectManagerClass( configuration, log );
                        
                        systemUserClass user = new systemUserClass();
                        user.setSystem_user_id( -1 );
                        user.setSystem_user_name( userNameField.getText() );
                        
                        user.setSystem_user_password( new String( passwordField.getPassword() ) );
                        
                        if(  userNameField.getText().equals( "" ) ){
                            throw new KExceptionClass(
                                    "User name is required"  ,null 
                                    );
                        }
                        
                        //After login, object gets all the attributes from DB, if login OK ...
                        configuration.setField( "SESSION_ID", "LOGIN" );
                        user = (systemUserClass) loginTransaction.login4( user );
                                                                                                                                                                                      
                        // ---------------------------------------------
                        // loading user setup
                                                                           
                        // save attr
			configuration.setField( "system_user_id", Long.toString(user.getSystem_user_id()  ) );
			configuration.setField( "system_user_name", user.getSystem_user_name() );                                                
			configuration.setField( "system_user_fullname", user.getSystem_user_name() );                                                                                                
                        configuration.setField( "system_user_role", user.getSystem_user_role() );                                                                                                                                                
                        configuration.setField( "system_user_email", user.getSystem_user_email() );                         
                        configuration.setField( "system_user_isadmin", user.getSystem_user_isadmin() );   

                        loginStatus = true;

		        setVisible( false );
        		dispose ();
		}
		catch( Exception error ){
					
			// save to disk
			log.log( this, KMetaUtilsClass.getStackTrace( error ) );

			// show to screen
			String message;
			message = "***Could not log in... ***\n";
			message += "[";
			message += error.toString();
			message += "]";
                        
			KMetaUtilsClass.showErrorMessageFromException( getOwner(), error );
		}    
}//GEN-LAST:event_okButtonActionPerformed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundImage;
    private javax.swing.JButton okButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwordLabel2;
    private javax.swing.JTextField userNameField;
    // End of variables declaration//GEN-END:variables


}
