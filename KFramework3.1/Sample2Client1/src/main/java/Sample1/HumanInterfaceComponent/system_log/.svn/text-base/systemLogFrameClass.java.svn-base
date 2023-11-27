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



package Sample1.HumanInterfaceComponent.system_log;



import Sample1.desktopToolbarListenerInterface;
import Sample1.desktopAccessInterface;
import KFramework30.Base.*;
import KFramework30.Widgets.*;
import java.awt.event.ActionListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;



public class systemLogFrameClass extends javax.swing.JInternalFrame 
implements desktopToolbarListenerInterface, InternalFrameListener{


	// uses
	private KConfigurationClass              configuration;
	private KLogClass                        log;
        private desktopAccessInterface          mainToolbar;

	// has - defaulted
	private systemLogBrowserClass            browser;

        public void internalFrameOpened(InternalFrameEvent e) {}
        public void internalFrameClosing(InternalFrameEvent e) {}
        public void internalFrameClosed(InternalFrameEvent e) {}
        public void internalFrameIconified(InternalFrameEvent e) {}
        public void internalFrameDeiconified(InternalFrameEvent e) {}
        public void internalFrameActivated(InternalFrameEvent e) {
                mainToolbar.enableDelete( false );
                mainToolbar.enableNew( false );
                mainToolbar.enableEdit( false );                
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
     

    public systemLogFrameClass(KConfigurationClass configurationParam,KLogClass logParam,
            desktopAccessInterface mainToolbarParam ) throws KExceptionClass

    {        
        initComponents ();
        
        // uses
	configuration = configurationParam;
	log = logParam;
        mainToolbar = mainToolbarParam;
        
	// has defaulted
	browser = new systemLogBrowserClass( configuration, log, justificationTable, mainToolbarParam.getDesktopsWindow()  );
        
        addInternalFrameListener( this );                       
        browser.initializeTable();        
               
        log.log( this, "justificacion frame constructed successfully." );     

    }            


    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        justificationTable = new javax.swing.JTable();
        topImage = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        justificationTable.setFont(new java.awt.Font("Arial", 0, 10));
        justificationTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        justificationTable.setName("justificationTable"); // NOI18N
        jScrollPane1.setViewportView(justificationTable);

        jTabbedPane1.addTab("Database Log", jScrollPane1);

        topImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/topBar.jpg"))); // NOI18N
        topImage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        topImage.setName("topImage"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
            .addComponent(topImage, javax.swing.GroupLayout.PREFERRED_SIZE, 682, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topImage, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable justificationTable;
    private javax.swing.JLabel topImage;
    // End of variables declaration//GEN-END:variables



}

