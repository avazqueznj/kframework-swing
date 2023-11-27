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

package Sample1.HumanInterfaceComponent.LIMSReportsDemo;


// kframework
import Sample1.desktopToolbarListenerInterface;
import Sample1.desktopAccessInterface;
import KFramework30.Communication.*;
import KFramework30.Widgets.*;
import KFramework30.Base.*;

// user

// rtl
import java.awt.event.ActionListener;
import javax.swing.event.*;
import javax.swing.*;

public class sdgEditFrameClass 
extends javax.swing.JInternalFrame
implements desktopToolbarListenerInterface, sdgTreeViewClass.sdgTreeViewListenerClass,
InternalFrameListener
{


	// uses
	private KConfigurationClass                      configuration;
	private KLogClass                                log;	
        private desktopAccessInterface            mainToolbar;          

	// has - defaulted
	private sdgBrowserClass				 sdgBrowser;
        private sdgTreeViewClass                         sdgTreeView;
        
   
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
        
    public sdgEditFrameClass(	
	KConfigurationClass configurationParam, KLogClass logParam, 
        desktopAccessInterface mainToolbarParam ) 
    throws Exception        
    {
        // uses
	configuration = configurationParam;
	log = logParam;	
        mainToolbar = mainToolbarParam;        
        
        
	// has defaulted        
        initComponents ();

                
	// setup  browser
	sdgBrowser =  new sdgBrowserClass( 
		configuration, log, 
		sdgBrowserTable, mainToolbar.getDesktopsWindow(),
		-1, sdgBrowserClass.ALL_VIEW                 );

        sdgBrowser.saveSQLOperation( 
                rowCountLabel, " count( sdg_id ) ", sdgBrowserClass.BROWSER_COLUMN_TYPE_NUMERIC, true );          
        
	sdgBrowser.initializeTable();

        sdgTreeView = new sdgTreeViewClass(
            configuration, log, mainToolbar.getDesktopsWindow(), sdgJTree, sdgBrowser, this );
        
//        sdgTreeView.setNewActionButton( newTreeNodeButton );     
//        sdgTreeView.setEditActionButton( editTreeNodeButton );
//        sdgTreeView.setDeleteActionButton( deleteTreeNodeButton );
        sdgTreeView.setRefreshActionButton( refreshTreeButton );        
                

        addInternalFrameListener( this );
                
	// final setup
//        newTreeNodeButton.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/new1.jpg")) );               
//        editTreeNodeButton.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/edit1.gif" ) ) );
//        deleteTreeNodeButton.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/delete1.gif" ) ) );
        refreshTreeButton.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/refresh1.gif" ) ) );
        
        batchPrintButton.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/print1.gif" ) ) );                
                        
	topImage.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/topBar.jpg" ) ) );        
        
    }

    public void batchPrint()
    throws KExceptionClass
    {
        
        // get selected rows
        JTable table = sdgBrowser.getJTable();        
        int[ ] selectedRows = table.getSelectedRows();
        
        if( selectedRows.length == 0 ){            
            throw new KExceptionClass(
                "*** Could not print ***\n" +
                "Please highlight some SGDs.\n", null
            );
        };
        
        // translate row id to sdg_oid
        long[ ] selectedSDGs = new long[ selectedRows.length ];
        for( int index = 0; index < selectedSDGs.length; index++ ){
            
            selectedSDGs[ index ] = sdgBrowser.getRowOID( selectedRows[ index ] );
        }
                        


        // open batch print dialog, pass selection
        sdgBatchPrintingDialogClass sdgBatchPrintingDialog = new sdgBatchPrintingDialogClass( 
            configuration, log, mainToolbar.getDesktopsWindow(),
            selectedSDGs 
            );
        
        sdgBatchPrintingDialog.setVisible( true );
        
        // done.
        
    }

    public void mouseClickPerformed( String ID, int level, String label) { }  // not used in demo 
    
    public ActionListener getDesktopToolbarActionListener() {
       return( sdgBrowser );
    }    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        banner = new javax.swing.JLabel();
        topImage = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        rowCountLabel = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        sdgTreeScroller = new javax.swing.JScrollPane();
        sdgJTree = new javax.swing.JTree();
        jToolBar2 = new javax.swing.JToolBar();
        refreshTreeButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        sdgScroller = new javax.swing.JScrollPane();
        sdgBrowserTable = new javax.swing.JTable();
        jToolBar3 = new javax.swing.JToolBar();
        batchPrintButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jToolBar1.setPreferredSize(new java.awt.Dimension(1024, 45));
        jToolBar1.setMinimumSize(new java.awt.Dimension(1024, 45));
        jToolBar1.setFloatable(false);

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(1024, 40));
        jLayeredPane1.setMinimumSize(new java.awt.Dimension(1024, 40));

        banner.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        banner.setForeground(java.awt.Color.white);
        banner.setText("Reporting  Interface");
        banner.setBounds(19, 1, 358, 45);
        jLayeredPane1.add(banner, new Integer(10));

        topImage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        topImage.setMinimumSize(new java.awt.Dimension(1024, 50));
        topImage.setPreferredSize(new java.awt.Dimension(1024, 50));
        topImage.setBounds(0, 0, 1024, 55);
        jLayeredPane1.add(topImage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setBounds(210, 23, 128, 80);
        jLayeredPane1.add(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.add(jLayeredPane1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 10));
        jPanel1.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(30, 30));

        rowCountLabel.setFont(new java.awt.Font("Verdana", 3, 14));
        rowCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rowCountLabel.setText("---");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(923, Short.MAX_VALUE)
                .add(rowCountLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(rowCountLabel)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setDividerLocation(250);

        jPanel2.setLayout(new java.awt.BorderLayout());

        sdgJTree.setFont(new java.awt.Font("Arial", 0, 10));
        sdgTreeScroller.setViewportView(sdgJTree);

        jPanel2.add(sdgTreeScroller, java.awt.BorderLayout.CENTER);

        jToolBar2.setFloatable(false);
        jToolBar2.setMinimumSize(new java.awt.Dimension(45, 45));
        jToolBar2.setPreferredSize(new java.awt.Dimension(308, 45));

        refreshTreeButton.setToolTipText("Delete shipping container");
        refreshTreeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refreshTreeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshTreeButton.setActionCommand("refresh");
        refreshTreeButton.setFont(new java.awt.Font("Arial", 0, 10));
        refreshTreeButton.setText("update");
        refreshTreeButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToolBar2.add(refreshTreeButton);

        jPanel2.add(jToolBar2, java.awt.BorderLayout.NORTH);

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        sdgBrowserTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        sdgBrowserTable.setFont(new java.awt.Font("Arial", 0, 10));
        sdgScroller.setViewportView(sdgBrowserTable);

        jPanel3.add(sdgScroller, java.awt.BorderLayout.CENTER);

        jToolBar3.setFloatable(false);
        jToolBar3.setMinimumSize(new java.awt.Dimension(45, 45));
        jToolBar3.setPreferredSize(new java.awt.Dimension(308, 45));

        batchPrintButton.setFont(new java.awt.Font("Arial", 0, 10));
        batchPrintButton.setText("Report Menu");
        batchPrintButton.setToolTipText("Create new shipping container");
        batchPrintButton.setActionCommand("new");
        batchPrintButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        batchPrintButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        batchPrintButton.setMaximumSize(new java.awt.Dimension(100, 50));
        batchPrintButton.setMinimumSize(new java.awt.Dimension(100, 50));
        batchPrintButton.setPreferredSize(new java.awt.Dimension(100, 50));
        batchPrintButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        batchPrintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchPrintButtonActionPerformed(evt);
            }
        });
        jToolBar3.add(batchPrintButton);

        jPanel3.add(jToolBar3, java.awt.BorderLayout.NORTH);

        jSplitPane1.setRightComponent(jPanel3);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

  
  private void batchPrintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchPrintButtonActionPerformed

        try{
      
            batchPrint();
            
        }catch( Exception error	){
            
                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( mainToolbar.getDesktopsWindow(), error );
        } 
      
      
  }//GEN-LAST:event_batchPrintButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JButton batchPrintButton;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JButton refreshTreeButton;
    private javax.swing.JLabel rowCountLabel;
    private javax.swing.JTable sdgBrowserTable;
    private javax.swing.JTree sdgJTree;
    private javax.swing.JScrollPane sdgScroller;
    private javax.swing.JScrollPane sdgTreeScroller;
    private javax.swing.JLabel topImage;
    // End of variables declaration//GEN-END:variables





}
