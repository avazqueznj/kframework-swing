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

// framework
import KFramework30.Base.*;
import KFramework30.Printing.*;

// app
import Sample1.HumanInterfaceComponent.sample_cliente.clientFrameClass;
import Sample1.HumanInterfaceComponent.sample_factura.facturaFrameClass;
import Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.LimsDataNavigatorFrameClass;
import Sample1.HumanInterfaceComponent.system_mail.mailFrameClass;
import Sample1.HumanInterfaceComponent.system_log.systemLogFrameClass;
import Sample1.HumanInterfaceComponent.system_users.userFrameClass;
import Sample1.HumanInterfaceComponent.system_users.changePasswordEditDialogClass;
import Sample1.HumanInterfaceComponent.LIMSReportsDemo.*;
import Sample1.HumanInterfaceComponent.sample_product.productFrameClass;

// rtl
import java.applet.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.print.PrinterJob;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Locale;
import javax.swing.*;

// misc

       
public class KFrameWorkDesktopClass 
extends javax.swing.JApplet 
implements desktopAccessInterface, ActionListener
{

    // has -  defaulted
    public KConfigurationClass                  configuration;
    public KLogClass                            log;  
                    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        StatusBar = new javax.swing.JPanel();
        desktopStatusLabel = new javax.swing.JLabel();
        mainProgressIcon = new javax.swing.JLabel();
        DesktopToolbar = new javax.swing.JToolBar();
        newButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        sortButton = new javax.swing.JButton();
        filterButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        printButton = new javax.swing.JButton();
        exportButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        refreshButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MainMenu = new javax.swing.JMenu();
        clientItem = new javax.swing.JMenuItem();
        facturasItem = new javax.swing.JMenuItem();
        productCatalogue = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        SampleLIMSDataNavigator = new javax.swing.JMenuItem();
        SampleLIMSReports = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JSeparator();
        cambioPasswordMenu = new javax.swing.JMenuItem();
        jSeparator131 = new javax.swing.JSeparator();
        setPage = new javax.swing.JMenuItem();
        setPrinter = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JSeparator();
        Exit = new javax.swing.JMenuItem();
        MenuEdit = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        editMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        sortMenuItem = new javax.swing.JMenuItem();
        filterMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        printMenuItem = new javax.swing.JMenuItem();
        exportCsvMenuItem = new javax.swing.JMenuItem();
        refreshMenuItem = new javax.swing.JMenuItem();
        MenuConf = new javax.swing.JMenu();
        menuCatalogosAdmin = new javax.swing.JMenu();
        Usuarios = new javax.swing.JMenuItem();
        mail = new javax.swing.JMenuItem();
        logViewer = new javax.swing.JMenuItem();
        MenuHelp = new javax.swing.JMenu();
        openHelp = new javax.swing.JMenuItem();

        desktop.setBackground(java.awt.SystemColor.activeCaptionBorder);
        desktop.setMinimumSize(new java.awt.Dimension(10, 10));
        desktop.setName("desktop"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/DesktopBackground.jpg"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setBounds(-40, 0, 2000, 800);
        desktop.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        StatusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        StatusBar.setAutoscrolls(true);
        StatusBar.setName("StatusBar"); // NOI18N

        desktopStatusLabel.setFont(new java.awt.Font("Verdana", 0, 10));
        desktopStatusLabel.setText("Starting...");
        desktopStatusLabel.setName("desktopStatusLabel"); // NOI18N

        mainProgressIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainProgressIcon.setName("mainProgressIcon"); // NOI18N

        javax.swing.GroupLayout StatusBarLayout = new javax.swing.GroupLayout(StatusBar);
        StatusBar.setLayout(StatusBarLayout);
        StatusBarLayout.setHorizontalGroup(
            StatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatusBarLayout.createSequentialGroup()
                .addComponent(desktopStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                .addComponent(mainProgressIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        StatusBarLayout.setVerticalGroup(
            StatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
            .addComponent(mainProgressIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        DesktopToolbar.setFloatable(false);
        DesktopToolbar.setRollover(true);
        DesktopToolbar.setFont(new java.awt.Font("Tahoma", 0, 12));
        DesktopToolbar.setMaximumSize(new java.awt.Dimension(367, 42));
        DesktopToolbar.setName("DesktopToolbar"); // NOI18N

        newButton.setFont(new java.awt.Font("Verdana", 0, 10));
        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/new1.jpg"))); // NOI18N
        newButton.setText("New");
        newButton.setToolTipText("Crea nuevo elemento");
        newButton.setActionCommand("new");
        newButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        newButton.setEnabled(false);
        newButton.setFocusable(false);
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        newButton.setMaximumSize(new java.awt.Dimension(40, 40));
        newButton.setName("newButton"); // NOI18N
        newButton.setPreferredSize(new java.awt.Dimension(40, 40));
        newButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DesktopToolbar.add(newButton);

        editButton.setFont(new java.awt.Font("Verdana", 0, 10));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit1.jpg"))); // NOI18N
        editButton.setText("Edit");
        editButton.setActionCommand("edit");
        editButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        editButton.setEnabled(false);
        editButton.setFocusable(false);
        editButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        editButton.setMaximumSize(new java.awt.Dimension(40, 40));
        editButton.setName("editButton"); // NOI18N
        editButton.setPreferredSize(new java.awt.Dimension(40, 40));
        editButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DesktopToolbar.add(editButton);

        deleteButton.setFont(new java.awt.Font("Verdana", 0, 10));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete1.jpg"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.setActionCommand("delete");
        deleteButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        deleteButton.setEnabled(false);
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        deleteButton.setMaximumSize(new java.awt.Dimension(40, 40));
        deleteButton.setName("deleteButton"); // NOI18N
        deleteButton.setPreferredSize(new java.awt.Dimension(40, 40));
        deleteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DesktopToolbar.add(deleteButton);

        jSeparator1.setMaximumSize(new java.awt.Dimension(15, 32767));
        jSeparator1.setName("jSeparator1"); // NOI18N
        jSeparator1.setPreferredSize(new java.awt.Dimension(15, 0));
        DesktopToolbar.add(jSeparator1);

        sortButton.setFont(new java.awt.Font("Verdana", 0, 10));
        sortButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sort1.jpg"))); // NOI18N
        sortButton.setText("Sort");
        sortButton.setActionCommand("sort");
        sortButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sortButton.setEnabled(false);
        sortButton.setFocusable(false);
        sortButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sortButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        sortButton.setMaximumSize(new java.awt.Dimension(40, 40));
        sortButton.setName("sortButton"); // NOI18N
        sortButton.setPreferredSize(new java.awt.Dimension(40, 40));
        sortButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DesktopToolbar.add(sortButton);

        filterButton.setFont(new java.awt.Font("Verdana", 0, 10));
        filterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/filter1.jpg"))); // NOI18N
        filterButton.setText("Filter");
        filterButton.setActionCommand("filter");
        filterButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        filterButton.setEnabled(false);
        filterButton.setFocusable(false);
        filterButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filterButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        filterButton.setMaximumSize(new java.awt.Dimension(40, 40));
        filterButton.setName("filterButton"); // NOI18N
        filterButton.setPreferredSize(new java.awt.Dimension(40, 40));
        filterButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DesktopToolbar.add(filterButton);

        jSeparator2.setMaximumSize(new java.awt.Dimension(15, 32767));
        jSeparator2.setName("jSeparator2"); // NOI18N
        jSeparator2.setPreferredSize(new java.awt.Dimension(15, 0));
        DesktopToolbar.add(jSeparator2);

        printButton.setFont(new java.awt.Font("Verdana", 0, 10));
        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print1.jpg"))); // NOI18N
        printButton.setText("Print");
        printButton.setActionCommand("print");
        printButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        printButton.setEnabled(false);
        printButton.setFocusable(false);
        printButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        printButton.setMaximumSize(new java.awt.Dimension(60, 40));
        printButton.setName("printButton"); // NOI18N
        printButton.setPreferredSize(new java.awt.Dimension(60, 40));
        printButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DesktopToolbar.add(printButton);

        exportButton1.setFont(new java.awt.Font("Verdana", 0, 10));
        exportButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/export1.jpg"))); // NOI18N
        exportButton1.setText("Export");
        exportButton1.setActionCommand("exportcsv");
        exportButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        exportButton1.setEnabled(false);
        exportButton1.setFocusable(false);
        exportButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        exportButton1.setMaximumSize(new java.awt.Dimension(60, 40));
        exportButton1.setName("exportButton1"); // NOI18N
        exportButton1.setPreferredSize(new java.awt.Dimension(60, 40));
        exportButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DesktopToolbar.add(exportButton1);

        jSeparator3.setMaximumSize(new java.awt.Dimension(15, 32767));
        jSeparator3.setName("jSeparator3"); // NOI18N
        jSeparator3.setPreferredSize(new java.awt.Dimension(15, 0));
        DesktopToolbar.add(jSeparator3);

        refreshButton.setFont(new java.awt.Font("Verdana", 0, 10));
        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/refresh1.jpg"))); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.setActionCommand("refresh");
        refreshButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        refreshButton.setEnabled(false);
        refreshButton.setFocusable(false);
        refreshButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        refreshButton.setMaximumSize(new java.awt.Dimension(60, 40));
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.setPreferredSize(new java.awt.Dimension(60, 40));
        refreshButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DesktopToolbar.add(refreshButton);

        jMenuBar1.setFont(new java.awt.Font("Verdana", 0, 12));
        jMenuBar1.setName("jMenuBar1"); // NOI18N

        MainMenu.setText("Start");
        MainMenu.setAutoscrolls(true);
        MainMenu.setFont(new java.awt.Font("Verdana", 0, 10));
        MainMenu.setName("Opciones"); // NOI18N

        clientItem.setFont(new java.awt.Font("Verdana", 0, 10));
        clientItem.setText("Clients");
        clientItem.setName("clientItem"); // NOI18N
        clientItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientItemActionPerformed(evt);
            }
        });
        MainMenu.add(clientItem);

        facturasItem.setFont(new java.awt.Font("Verdana", 0, 10));
        facturasItem.setText("Invoices");
        facturasItem.setName("facturasItem"); // NOI18N
        facturasItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facturasItemActionPerformed(evt);
            }
        });
        MainMenu.add(facturasItem);

        productCatalogue.setFont(new java.awt.Font("Verdana", 0, 10));
        productCatalogue.setText("Product catalog");
        productCatalogue.setName("productCatalogue"); // NOI18N
        productCatalogue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCatalogueActionPerformed(evt);
            }
        });
        MainMenu.add(productCatalogue);

        jSeparator6.setName("jSeparator6"); // NOI18N
        MainMenu.add(jSeparator6);

        SampleLIMSDataNavigator.setFont(new java.awt.Font("Verdana", 0, 10));
        SampleLIMSDataNavigator.setText("Sample LIMS Data Navigator");
        SampleLIMSDataNavigator.setName("SampleLIMSDataNavigator"); // NOI18N
        SampleLIMSDataNavigator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SampleLIMSDataNavigatorActionPerformed(evt);
            }
        });
        MainMenu.add(SampleLIMSDataNavigator);

        SampleLIMSReports.setFont(new java.awt.Font("Verdana", 0, 10));
        SampleLIMSReports.setText("Sample LIMS reporter");
        SampleLIMSReports.setName("SampleLIMSReports"); // NOI18N
        SampleLIMSReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SampleLIMSReportsActionPerformed(evt);
            }
        });
        MainMenu.add(SampleLIMSReports);

        jSeparator17.setName("jSeparator17"); // NOI18N
        MainMenu.add(jSeparator17);

        cambioPasswordMenu.setFont(new java.awt.Font("Verdana", 0, 10));
        cambioPasswordMenu.setText("Change User Password");
        cambioPasswordMenu.setName("cambioPasswordMenu"); // NOI18N
        cambioPasswordMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioPasswordMenuActionPerformed(evt);
            }
        });
        MainMenu.add(cambioPasswordMenu);

        jSeparator131.setName("jSeparator131"); // NOI18N
        MainMenu.add(jSeparator131);

        setPage.setFont(new java.awt.Font("Verdana", 0, 10));
        setPage.setText("Page Settings");
        setPage.setName("setPage"); // NOI18N
        setPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPageActionPerformed(evt);
            }
        });
        MainMenu.add(setPage);

        setPrinter.setFont(new java.awt.Font("Verdana", 0, 10));
        setPrinter.setText("Printer Settings");
        setPrinter.setName("setPrinter"); // NOI18N
        setPrinter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPrinterActionPerformed(evt);
            }
        });
        MainMenu.add(setPrinter);

        jSeparator8.setName("jSeparator8"); // NOI18N
        MainMenu.add(jSeparator8);

        Exit.setFont(new java.awt.Font("Verdana", 0, 10));
        Exit.setText("Exit System");
        Exit.setName("Exit"); // NOI18N
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        MainMenu.add(Exit);

        jMenuBar1.add(MainMenu);

        MenuEdit.setText("Edit");
        MenuEdit.setFont(new java.awt.Font("Verdana", 0, 10));
        MenuEdit.setName("Edicion"); // NOI18N

        newMenuItem.setFont(new java.awt.Font("Verdana", 0, 10));
        newMenuItem.setText("New");
        newMenuItem.setActionCommand("new");
        newMenuItem.setEnabled(false);
        newMenuItem.setName("newMenuItem"); // NOI18N
        MenuEdit.add(newMenuItem);

        editMenuItem.setFont(new java.awt.Font("Verdana", 0, 10));
        editMenuItem.setText("Edit");
        editMenuItem.setActionCommand("edit");
        editMenuItem.setEnabled(false);
        editMenuItem.setName("editMenuItem"); // NOI18N
        MenuEdit.add(editMenuItem);

        deleteMenuItem.setFont(new java.awt.Font("Verdana", 0, 10));
        deleteMenuItem.setText("Delete");
        deleteMenuItem.setActionCommand("delete");
        deleteMenuItem.setEnabled(false);
        deleteMenuItem.setName("deleteMenuItem"); // NOI18N
        MenuEdit.add(deleteMenuItem);

        jSeparator4.setName("jSeparator4"); // NOI18N
        MenuEdit.add(jSeparator4);

        sortMenuItem.setFont(new java.awt.Font("Verdana", 0, 10));
        sortMenuItem.setText("Sort");
        sortMenuItem.setActionCommand("sort");
        sortMenuItem.setEnabled(false);
        sortMenuItem.setName("sortMenuItem"); // NOI18N
        MenuEdit.add(sortMenuItem);

        filterMenuItem.setFont(new java.awt.Font("Verdana", 0, 10));
        filterMenuItem.setText("Filter");
        filterMenuItem.setActionCommand("filter");
        filterMenuItem.setEnabled(false);
        filterMenuItem.setName("filterMenuItem"); // NOI18N
        filterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterMenuItemActionPerformed(evt);
            }
        });
        MenuEdit.add(filterMenuItem);

        jSeparator5.setName("jSeparator5"); // NOI18N
        MenuEdit.add(jSeparator5);

        printMenuItem.setFont(new java.awt.Font("Verdana", 0, 10));
        printMenuItem.setText("Print Report");
        printMenuItem.setActionCommand("print");
        printMenuItem.setEnabled(false);
        printMenuItem.setName("printMenuItem"); // NOI18N
        MenuEdit.add(printMenuItem);

        exportCsvMenuItem.setFont(new java.awt.Font("Verdana", 0, 10));
        exportCsvMenuItem.setText("Export Table Data");
        exportCsvMenuItem.setActionCommand("exportcsv");
        exportCsvMenuItem.setEnabled(false);
        exportCsvMenuItem.setName("exportCsvMenuItem"); // NOI18N
        MenuEdit.add(exportCsvMenuItem);

        refreshMenuItem.setFont(new java.awt.Font("Verdana", 0, 10));
        refreshMenuItem.setText("Refresh");
        refreshMenuItem.setActionCommand("refresh");
        refreshMenuItem.setEnabled(false);
        refreshMenuItem.setName("refreshMenuItem"); // NOI18N
        MenuEdit.add(refreshMenuItem);

        jMenuBar1.add(MenuEdit);

        MenuConf.setText("Configuration");
        MenuConf.setFont(new java.awt.Font("Verdana", 0, 10));
        MenuConf.setName("MenuConf"); // NOI18N

        menuCatalogosAdmin.setText("System Admin Menu");
        menuCatalogosAdmin.setEnabled(false);
        menuCatalogosAdmin.setFont(new java.awt.Font("Verdana", 0, 10));
        menuCatalogosAdmin.setName("menuCatalogosAdmin"); // NOI18N

        Usuarios.setFont(new java.awt.Font("Verdana", 0, 10));
        Usuarios.setText("Configure Users");
        Usuarios.setName("Usuarios"); // NOI18N
        Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosActionPerformed(evt);
            }
        });
        menuCatalogosAdmin.add(Usuarios);

        mail.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        mail.setText("E-mail");
        mail.setName("mail"); // NOI18N
        mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mailActionPerformed(evt);
            }
        });
        menuCatalogosAdmin.add(mail);

        MenuConf.add(menuCatalogosAdmin);

        logViewer.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        logViewer.setText("View Log");
        logViewer.setName("logViewer"); // NOI18N
        logViewer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logViewerActionPerformed(evt);
            }
        });
        MenuConf.add(logViewer);

        jMenuBar1.add(MenuConf);

        MenuHelp.setText("Help");
        MenuHelp.setFont(new java.awt.Font("Verdana", 0, 10));
        MenuHelp.setName("Ayuda"); // NOI18N

        openHelp.setFont(new java.awt.Font("Verdana", 0, 10));
        openHelp.setText("See Manual");
        openHelp.setName("openHelp"); // NOI18N
        openHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openHelpActionPerformed(evt);
            }
        });
        MenuHelp.add(openHelp);

        jMenuBar1.add(MenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
            .addComponent(StatusBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(DesktopToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
      exitSystem();
}//GEN-LAST:event_ExitActionPerformed

private void filterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterMenuItemActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_filterMenuItemActionPerformed

private void setPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setPageActionPerformed
    openPageSetupDialog();        
}//GEN-LAST:event_setPageActionPerformed

private void setPrinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setPrinterActionPerformed
    openPrinterSetupDialog();
}//GEN-LAST:event_setPrinterActionPerformed

private void UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosActionPerformed
    openUserManager();
}//GEN-LAST:event_UsuariosActionPerformed

private void mailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mailActionPerformed
    openMailViewer();    
}//GEN-LAST:event_mailActionPerformed

private void logViewerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logViewerActionPerformed
    openSystemLogViewer();    
}//GEN-LAST:event_logViewerActionPerformed

private void cambioPasswordMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioPasswordMenuActionPerformed
    openChangePassword();    
}//GEN-LAST:event_cambioPasswordMenuActionPerformed

private void openHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openHelpActionPerformed
    
//  JSObject win = JSObject.getWindow(this);	
//  Object[] args = new Object[0];
//  win.call("openManual", args );	    
    
}//GEN-LAST:event_openHelpActionPerformed

private void facturasItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facturasItemActionPerformed

    openInternalFrame( facturaFrameClass.class, "Invoices" );             
        
}//GEN-LAST:event_facturasItemActionPerformed


    private void SampleLIMSDataNavigatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SampleLIMSDataNavigatorActionPerformed
                
    openInternalFrame( LimsDataNavigatorFrameClass.class, "DEMO Data Navigator" );             
        
    }//GEN-LAST:event_SampleLIMSDataNavigatorActionPerformed


private void clientItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientItemActionPerformed

    openInternalFrame( clientFrameClass.class, "Clients" );             
}
//GEN-LAST:event_clientItemActionPerformed


    private void SampleLIMSReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SampleLIMSReportsActionPerformed

    openInternalFrame( sdgEditFrameClass.class, "DEMO Reports" );                     
        
    }//GEN-LAST:event_SampleLIMSReportsActionPerformed

    private void productCatalogueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCatalogueActionPerformed

    openInternalFrame( productFrameClass.class, "Product Catalog" );                     
                
    }//GEN-LAST:event_productCatalogueActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar DesktopToolbar;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu MainMenu;
    private javax.swing.JMenu MenuConf;
    private javax.swing.JMenu MenuEdit;
    private javax.swing.JMenu MenuHelp;
    private javax.swing.JMenuItem SampleLIMSDataNavigator;
    private javax.swing.JMenuItem SampleLIMSReports;
    private javax.swing.JPanel StatusBar;
    private javax.swing.JMenuItem Usuarios;
    private javax.swing.JMenuItem cambioPasswordMenu;
    private javax.swing.JMenuItem clientItem;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel desktopStatusLabel;
    private javax.swing.JButton editButton;
    private javax.swing.JMenuItem editMenuItem;
    private javax.swing.JButton exportButton1;
    private javax.swing.JMenuItem exportCsvMenuItem;
    private javax.swing.JMenuItem facturasItem;
    private javax.swing.JButton filterButton;
    private javax.swing.JMenuItem filterMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator131;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JMenuItem logViewer;
    private javax.swing.JMenuItem mail;
    private javax.swing.JLabel mainProgressIcon;
    private javax.swing.JMenu menuCatalogosAdmin;
    private javax.swing.JButton newButton;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem openHelp;
    private javax.swing.JButton printButton;
    private javax.swing.JMenuItem printMenuItem;
    private javax.swing.JMenuItem productCatalogue;
    private javax.swing.JButton refreshButton;
    private javax.swing.JMenuItem refreshMenuItem;
    private javax.swing.JMenuItem setPage;
    private javax.swing.JMenuItem setPrinter;
    private javax.swing.JButton sortButton;
    private javax.swing.JMenuItem sortMenuItem;
    // End of variables declaration//GEN-END:variables

    
    public void init() {
        
        try {
                                   
            // uses
                         
            // has - defaulted                       
            String ConfigAddress = new String();
            
            
            try{
            
                System.out.println( "*********************************************" );                
                System.out.println( "KFramework starting..." );                
                System.out.println( "*********************************************" );                
                
                URL configURL = getClass().getResource("/config/clientMagnus.conf");                
                configuration = new KConfigurationClass( ConfigAddress );
                configuration.loadFromNetwork( configURL );
                
            }catch( Exception error ){
                
                System.out.println( "... could not get magnus from network:" );                
                System.out.println( KMetaUtilsClass.getStackTrace( error ) );                
                throw new KExceptionClass( "Could not locate a magnus.conf file", error );                  
                
            }
                                                        
            configuration.parse();

            // create a log
            log =  new KLogClass( configuration );

            //===========================================================================
            
            try{		
                
                Toolkit.getDefaultToolkit().setDynamicLayout(true);
                System.setProperty("sun.awt.noerasebackground", "true");
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                
                
                /*
                 *   This should work for all platforms
                 */
                try{
                        UIManager.setLookAndFeel(
                                 UIManager.getCrossPlatformLookAndFeelClassName()
                        );

                }catch( Exception error ){} // go with what ever is defaulted in the platform 
                
                                                
                    log.log( this, "Setting LOCALE" );                    
                    Locale.setDefault(  new Locale( configuration.getField("locale_lang"), configuration.getField("local_region") ) );                
                    
                    
            //===========================================================================                                
                        logonScreenClass logonScreen =
                                new logonScreenClass( 
                                        configuration, log, getDesktopsWindow()                                       
                                        );	                        
            //===========================================================================                        
                        
                        
                        // width, height, position
                        //logonScreen.setSize( 400, 380 );
                        KMetaUtilsClass.centerInScreen( logonScreen );				
                        if( !logonScreen.showLoginDialog() ){
                                throw new KExceptionClass(
                                        "\n" +
                                        "*** User authentication failed ***\n" +
                                        "*** System will not start ***\n" ,null 
                                );
                        }
                    
 
            //===========================================================================                        
                        
                    // we start .... 
                    initComponents();
                                
                    // hook menus
                    newMenuItem.addActionListener( this );
                    editMenuItem.addActionListener( this );
                    deleteMenuItem.addActionListener( this );
                    sortMenuItem.addActionListener( this );
                    filterMenuItem.addActionListener( this );
                    printMenuItem.addActionListener( this );
                    exportCsvMenuItem.addActionListener( this );
                    refreshMenuItem.addActionListener( this );

                    // hook buttons
                    newButton.addActionListener( this );
                    editButton.addActionListener( this );
                    deleteButton.addActionListener( this );
                    sortButton.addActionListener( this );
                    filterButton.addActionListener( this );
                    refreshButton.addActionListener( this );
                    printButton.addActionListener( this );
                    exportButton1.addActionListener( this );
                  

                    String statusBarMessage = new String();
                    statusBarMessage = "Connected as [ ";
                    statusBarMessage += configuration.getField( "system_user_name" );                                        
                    statusBarMessage += " (" + configuration.getField( "system_user_email" ) + ")";                    
                    statusBarMessage += " / ";
                    statusBarMessage += configuration.getField( "system_user_role" );                    
                    statusBarMessage += " ]";                                                                                               
                    desktopStatusLabel.setText( statusBarMessage );			

                    // FINAL MISC STUFF                               
                    desktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);   
                                        
                    KMetaUtilsClass.setMainProgressIcon( this, mainProgressIcon, configuration, log);
                                        
                                                        
                    // security                                                                           
                    if( configuration.getField( "system_user_role" ).equals( "Default" ) ){
                     
                        menuCatalogosAdmin.setEnabled( false );                        
                    }                    
                    if( configuration.getField( "system_user_role" ).equals( "Admin" ) ){
                     
                        menuCatalogosAdmin.setEnabled( true );                        
                    }                                        
                    else{                     
                        menuCatalogosAdmin.setEnabled( false );                        
                    }
                                                                                                   
                                                
            }
            // error after log
            catch( Exception error ){

                    // save to disk
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );

                    // re throw to shutdown
                    // use normal handler to show screen
                    throw error;
            }

            // done initializing...

        }
        // error before log...
        catch( Exception error ){

                // 1. show in console
                System.out.println( KMetaUtilsClass.getStackTrace( error ) );

                // show to screen
                KMetaUtilsClass.showErrorMessageFromException( getDesktopsWindow(), error );

                // Shutdown, and clean up...
                // ****************************************************
                removeAll();
        }          
           
    
    }

    

    // ------------------------------------------------------------------------- 
    
    void exitSystem(){
        
         if( 
            KMetaUtilsClass.showConfirmationMessage( getDesktopsWindow(),
               " Are you sure, do you want to exit the application ? "
                ).equals( " OK " )
            )
         
         {

            try{
                                
                AppletContext context = getAppletContext();
                //context.showDocument( new URL( "javascript:window.opener='x';window.close();" ) );                
                context.showDocument( new URL( "javascript:closeWindow();" ) );                
                    
                                           
            }catch( Exception error ){}
              
         }
        
    }   

    // ------------------------------------------------------------------------- 
    
    // manage all toolbar actions for all inner frames
    public void actionPerformed(java.awt.event.ActionEvent evt) {  

        try {

            desktopToolbarListenerInterface activeInternalFrame = ( desktopToolbarListenerInterface ) desktop.getSelectedFrame();

            if( activeInternalFrame != null ) {  
                if( activeInternalFrame.getDesktopToolbarActionListener() != null ){
                    // forward event to active browswer in active frame
                    activeInternalFrame.getDesktopToolbarActionListener().actionPerformed(evt);                                                            
                }                                
            }
        }
        catch( Exception error ){
                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( getDesktopsWindow(), error );
        };               

    }        

    @Override
    public boolean enableNew(boolean state) {
        boolean oldState = newButton.isEnabled();
        newButton.setEnabled( state );
        newMenuItem.setEnabled( state );
        return oldState;
    }

    @Override
    public boolean enableEdit(boolean state) {
        boolean oldState = editButton.isEnabled();
        editButton.setEnabled( state );
        editMenuItem.setEnabled( state );
        return oldState;
    }

    @Override
    public boolean enableDelete(boolean state) {
        boolean oldState = deleteButton.isEnabled();
        deleteButton.setEnabled( state );
        deleteMenuItem.setEnabled( state ); 
        return oldState;
    }

    @Override
    public boolean enableSort(boolean state) {
        boolean oldState = sortButton.isEnabled();
        sortButton.setEnabled( state );
        sortMenuItem.setEnabled( state );  
        return oldState;
    }

    @Override
    public boolean enableFilter(boolean state) {
        boolean oldState = filterButton.isEnabled();
        filterButton.setEnabled( state );
        filterMenuItem.setEnabled( state );  
        return oldState;
    }      

    @Override
    public boolean enablePrint(boolean state) {
        boolean oldState = printButton.isEnabled();
        printButton.setEnabled( state );
        printMenuItem.setEnabled( state );  
        return oldState;
    }
    
    @Override
    public boolean enableExport(boolean state) {
        boolean oldState = exportButton1.isEnabled();
        exportButton1.setEnabled( state );
        exportCsvMenuItem.setEnabled( state );  
        return oldState;
    }

    @Override
    public boolean enableRefresh(boolean state) {
        boolean oldState = refreshButton.isEnabled();
        refreshButton.setEnabled( state );
        refreshMenuItem.setEnabled( state );  
        return oldState;
    }  

    @Override
    public java.awt.Window getDesktopsWindow() {
        return( KMetaUtilsClass.getParentWindow( this ) );
    }
    
    @Override
    public JDesktopPane getDesktop() {
        return desktop;
    }
    
    
    // -------------------------------------------------------------------------                   
        
    public void openUserManager(){        
        openInternalFrame( userFrameClass.class, "System Users" );                
    }

    // -------------------------------------------------------------------------                   
               
    public void openMailViewer(){   
        
        openInternalFrame( mailFrameClass.class, "Mail" );        
    }    
    
    // -------------------------------------------------------------------------                   
    
    public void openSystemLogViewer(){           
        openInternalFrame( systemLogFrameClass.class, "System Log" );
    } 
    
    // -------------------------------------------------------------------------                   
        
    public void openChangePassword(){
        
        try{            
                // ---------------------------------
                log.log( this, "Openning change password screen..." );     
            
                changePasswordEditDialogClass changePassword 
                    = new changePasswordEditDialogClass(
                        configuration, log, getDesktopsWindow() );                    
                                            
                changePassword.setTitle("Change Password");			

                log.log( this, "Openning change password screen completed." );    
            
                changePassword.setVisible( true );                    
                
        

	}catch( Exception error ){
                                                                                                              
		// save to disk
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );	

		// show to screen
		KMetaUtilsClass.showErrorMessageFromException(  getDesktopsWindow(), error );				
	}      
    }
       
   
    public void openPrinterSetupDialog(){

        try{
        
            // initialize if required            
            if( KPrintJobClass.defaultPrinterJob == null || KPrintJobClass.defaultPageFormat == null ){

                PrinterJob printerJob = PrinterJob.getPrinterJob();
                KPrintJobClass.setPrintingDefaults( 
                    printerJob, printerJob.defaultPage()
                );
            };
                        
            KPrintJobClass.defaultPrinterJob.printDialog();

        }
        catch( Exception error ){                                                                                            
            
		// save to disk
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );	

		// show to screen
		KMetaUtilsClass.showErrorMessageFromException( getDesktopsWindow(),error );				
	}
        
    }

    // ------------------------------------------------------------------------- 
    
    public void openPageSetupDialog(){

        try{
        
            // initialize if required
            if( KPrintJobClass.defaultPrinterJob == null || KPrintJobClass.defaultPageFormat == null ){

                PrinterJob printerJob = PrinterJob.getPrinterJob();
                KPrintJobClass.setPrintingDefaults( 
                    printerJob, printerJob.defaultPage()
                );
            };
                        
            KPrintJobClass.defaultPageFormat = 
                KPrintJobClass.defaultPrinterJob.pageDialog( KPrintJobClass.defaultPageFormat );
                               
            
        }
        catch( Exception error ){
		                                                                                        
            
		// save to disk
		log.log( this, KMetaUtilsClass.getStackTrace( error ) );	

		// show to screen
		KMetaUtilsClass.showErrorMessageFromException( getDesktopsWindow(), error );				
	}
        
    }     
    
    //--------------------------------------------------------------------------
    
    @Override
    public JInternalFrame openInternalFrame( 
            Class< ? extends JInternalFrame > internalFrameClass, String internalFrameTitle 
            ){
        
            return doOpenInternalFrame( configuration, log,  this,  internalFrameClass, internalFrameTitle );
    }
            
    
    public static JInternalFrame doOpenInternalFrame( 
            KConfigurationClass configuration, KLogClass log, desktopAccessInterface desktopAccess, 
            Class< ? extends JInternalFrame > internalFrameClass, String internalFrameTitle 
            ){
    
        JInternalFrame result = null;
        
        try{

                result = KMetaUtilsClass.bringInternalFrameToFront(  desktopAccess.getDesktop(), internalFrameTitle );
            
		// bring to front if already there, or...
		if( result == null ){
		                                                       
                            
			// nop, then open it
			String message = new String();
			message = "Building window: [";
			message += internalFrameTitle;
			message += "]";
			log.log( null, message );
			
                        Class[]  frameConstructorRequiredParam   = new Class[] { KConfigurationClass.class, KLogClass.class, desktopAccessInterface.class };
                        Object[] frameConstructorActualArguments = new Object[] { configuration, log, desktopAccess };
                        Constructor frameConstructor;
                        try{
                            frameConstructor = internalFrameClass.getConstructor( frameConstructorRequiredParam );
                        }catch(NoSuchMethodException error){
                            throw new KExceptionClass( 
                                    " Window frame" + internalFrameClass.getName() + 
                                    " does not provide the required constructor, can not open it. " 
                                    , error );
                        }
                        
                        
                        result = (JInternalFrame) frameConstructor.newInstance( frameConstructorActualArguments );
			result.setTitle( internalFrameTitle );		
			KMetaUtilsClass.showInternalFrame( desktopAccess.getDesktop(), result );
                        
			log.log( null, "Window created successfully." );                                                                                                                               
		}

	}catch( Exception error ){
                                                                                                       
		log.log( null, "Could not open internal window + " + KMetaUtilsClass.getStackTrace( error ) );	
		KMetaUtilsClass.showErrorMessageFromText1( desktopAccess.getDesktopsWindow(), "Could not open internal window + " + KMetaUtilsClass.getStackTrace( error ) );
	}   
       
        return result;
    
    }                                            
    
        
    
    //--------------------------------------------------------------------------

    
}


