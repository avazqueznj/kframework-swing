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

import Sample1.desktopToolbarListenerInterface;
import Sample1.desktopAccessInterface;
import KFramework30.Widgets.*;
import KFramework30.Base.*;
import java.awt.event.ActionListener;
import javax.swing.event.*;

/**
 *
 * @author  Administrator
 */
public class userFrameClass extends javax.swing.JInternalFrame 
implements desktopToolbarListenerInterface,InternalFrameListener
{

	// uses
	private KConfigurationClass              configuration;
	private KLogClass                        log;
        private desktopAccessInterface    mainToolbar;

	// has - defaulted
	private userBrowserClass                 browser;

                
        public void internalFrameOpened(InternalFrameEvent e) {}
        public void internalFrameClosing(InternalFrameEvent e) {}
        public void internalFrameClosed(InternalFrameEvent e) {}
        public void internalFrameIconified(InternalFrameEvent e) {}
        public void internalFrameDeiconified(InternalFrameEvent e) {}
        public void internalFrameActivated(InternalFrameEvent e) {
                mainToolbar.enableDelete( true );
                mainToolbar.enableNew( true );
                mainToolbar.enableEdit( true );                
                mainToolbar.enableSort( true );
                mainToolbar.enableFilter( true ); 
                mainToolbar.enableRefresh( true );
                mainToolbar.enablePrint( true );
                mainToolbar.enableExport( true );
        }
        public void internalFrameDeactivated(InternalFrameEvent e) {
                mainToolbar.enableDelete( false );
                mainToolbar.enableNew( false );
                mainToolbar.enableEdit( false );
                mainToolbar.enableSort( false );
                mainToolbar.enableFilter( false );  
                mainToolbar.enableRefresh( false );
                mainToolbar.enablePrint( false ); 
                mainToolbar.enableExport( false );
        }
        
        public ActionListener getDesktopToolbarActionListener() {
            return( browser );
        }
        
        
    public userFrameClass(KConfigurationClass configurationParam,KLogClass logParam,
            desktopAccessInterface systemDesktopParam ) throws KExceptionClass
    {        
        initComponents ();
        
        // uses
	configuration = configurationParam;
	log = logParam;
        mainToolbar = systemDesktopParam;
        
	// has defaulted
	browser = new userBrowserClass( configuration, log, usuariosTable, mainToolbar.getDesktopsWindow()  );                           
        browser.initializeTable();        
               
        addInternalFrameListener( this );            

        log.log( this, "UsuariosFrame constructed successfully." );     
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jToolBar1 = new javax.swing.JToolBar();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        banner = new javax.swing.JLabel();
        topImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usuariosTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        grandTotal = new javax.swing.JLabel();

        jInternalFrame1.setName("jInternalFrame1"); // NOI18N
        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(32769, 32769));
        jToolBar1.setMinimumSize(new java.awt.Dimension(0, 0));
        jToolBar1.setName("jToolBar1"); // NOI18N
        jToolBar1.setPreferredSize(new java.awt.Dimension(1024, 45));

        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1028, 44));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1028, 44));
        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        banner.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        banner.setForeground(java.awt.Color.gray);
        banner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/topBar.jpg"))); // NOI18N
        banner.setText("Lista de usuarios");
        banner.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        banner.setName("banner"); // NOI18N
        banner.setBounds(0, 0, 850, 40);
        jLayeredPane1.add(banner, javax.swing.JLayeredPane.DEFAULT_LAYER);

        topImage.setName("topImage"); // NOI18N
        topImage.setBounds(0, 0, 0, 43);
        jLayeredPane1.add(topImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.add(jLayeredPane1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        usuariosTable.setFont(new java.awt.Font("Arial", 0, 10));
        usuariosTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        usuariosTable.setName("usuariosTable"); // NOI18N
        jScrollPane1.setViewportView(usuariosTable);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setToolTipText("");
        jPanel1.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        grandTotal.setFont(new java.awt.Font("Arial", 0, 10));
        grandTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        grandTotal.setMinimumSize(new java.awt.Dimension(128, 20));
        grandTotal.setName("grandTotal"); // NOI18N
        grandTotal.setPreferredSize(new java.awt.Dimension(128, 20));
        jPanel1.add(grandTotal, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JLabel grandTotal;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel topImage;
    private javax.swing.JTable usuariosTable;
    // End of variables declaration//GEN-END:variables

}
