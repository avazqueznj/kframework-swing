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



package Sample1.HumanInterfaceComponent.system_mail;

import Sample1.desktopToolbarListenerInterface;
import Sample1.desktopAccessInterface;
import KFramework30.Base.*;
import KFramework30.Widgets.*;
import java.awt.event.ActionListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author  Administrator
 */
public class mailFrameClass extends javax.swing.JInternalFrame 
implements desktopToolbarListenerInterface, InternalFrameListener{

	// uses
	private KConfigurationClass              configuration;
	private KLogClass                        log;
        private desktopAccessInterface          mainToolbar;

	// has - defaulted
	private mailBrowserClass                 browser;

        
        public void internalFrameOpened(InternalFrameEvent e) {}
        public void internalFrameClosing(InternalFrameEvent e) {}
        public void internalFrameClosed(InternalFrameEvent e) {}
        public void internalFrameIconified(InternalFrameEvent e) {}
        public void internalFrameDeiconified(InternalFrameEvent e) {}
        public void internalFrameActivated(InternalFrameEvent e) {
                mainToolbar.enableDelete( false );
                mainToolbar.enableNew( false );
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
        
        
    public mailFrameClass(KConfigurationClass configurationParam,KLogClass logParam,
            desktopAccessInterface mainToolbarParam ) throws KExceptionClass
    {        
        initComponents ();
        
        // uses
	configuration = configurationParam;
	log = logParam;
        mainToolbar = mainToolbarParam;
        
	// has defaulted
	browser =  new mailBrowserClass( configuration, log, justificationTable, mainToolbar.getDesktopsWindow()  );                           
        browser.initializeTable();        
                       
        addInternalFrameListener( this );            

        log.log( this, "justificacion frame constructed successfully." );     
    }            


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        banner = new javax.swing.JLabel();
        topImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        justificationTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(32769, 32769));
        jToolBar1.setName("jToolBar1"); // NOI18N
        jToolBar1.setPreferredSize(new java.awt.Dimension(1024, 45));

        jLayeredPane1.setMaximumSize(new java.awt.Dimension(1028, 44));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1028, 44));
        jLayeredPane1.setName("jLayeredPane1"); // NOI18N

        banner.setFont(new java.awt.Font("Arial", 2, 24)); // NOI18N
        banner.setForeground(java.awt.Color.gray);
        banner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/topBar.jpg"))); // NOI18N
        banner.setText("e-mail");
        banner.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        banner.setName("banner"); // NOI18N
        banner.setBounds(0, 0, 1024, 40);
        jLayeredPane1.add(banner, javax.swing.JLayeredPane.DEFAULT_LAYER);

        topImage.setName("topImage"); // NOI18N
        topImage.setBounds(0, 0, 1024, 43);
        jLayeredPane1.add(topImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.add(jLayeredPane1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        justificationTable.setFont(new java.awt.Font("Arial", 0, 10));
        justificationTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        justificationTable.setName("justificationTable"); // NOI18N
        jScrollPane1.setViewportView(justificationTable);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable justificationTable;
    private javax.swing.JLabel topImage;
    // End of variables declaration//GEN-END:variables

}
