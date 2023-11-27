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

// k
import KFramework30.Base.*;
import KFramework30.Communication.*;
import KFramework30.Printing.*;

// java rtl
import java.util.*;
import java.awt.*;
import java.awt.print.*;

import ProblemDomainComponent.*;


public class sdgBatchPrintingDialogClass 
extends javax.swing.JDialog 
{
    
	// uses
	private KConfigurationClass                      configuration;
	private KLogClass                                log;	
        private long[]                                   selectedSDGs;
        private java.awt.Window                          parentWindow;

	// has - defaulted  
        static PrinterJob labelPrinterJob = null;
        static PageFormat labelPageFormat = null;

        
    /** Creates new form samplePrintDialogClass */
    public sdgBatchPrintingDialogClass( 
        KConfigurationClass configurationParam, KLogClass logParam,      
        java.awt.Window  parentWindowParam,
        long[] selectedSDGsParam ) 
        throws KExceptionClass
    {
        
        super( parentWindowParam, java.awt.Dialog.ModalityType.DOCUMENT_MODAL );
        initComponents ();
        pack ();
        
        // width, height, position
        setSize( 460, 400 );        
        KMetaUtilsClass.centerInScreen( this );	
        
        // uses
	configuration = configurationParam;
	log = logParam;        	
        selectedSDGs = selectedSDGsParam;
        parentWindow = parentWindowParam;
        
        // has - defaulted
        
	// setup

	// setup clients browser
        
	printButton.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/print1.gif")) );
                                      
	topLabel.setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/topBar.jpg")) );
               
   
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        banner = new javax.swing.JLabel();
        topLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        printButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        sampleAllButton = new javax.swing.JCheckBox();
        sampleMethodSummary = new javax.swing.JCheckBox();
        sampleCOA = new javax.swing.JCheckBox();
        sampleComments = new javax.swing.JCheckBox();
        sampleChronicle = new javax.swing.JCheckBox();
        sampleCoverLetter = new javax.swing.JCheckBox();
        sdgCocButton = new javax.swing.JCheckBox();
        sdgLabChronicle = new javax.swing.JCheckBox();
        sdgInvoiceButton = new javax.swing.JCheckBox();
        printDSGlabelsButton = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SDG reports batch printing");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        banner.setFont(new java.awt.Font("Arial", 0, 24));
        banner.setForeground(java.awt.Color.white);
        banner.setText("SDG reports batch printing");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Select reports to print ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 10))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        printButton.setFont(new java.awt.Font("Dialog", 0, 10));
        printButton.setText("PRINT");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        progressBar.setToolTipText("Printing progress");
        progressBar.setStringPainted(true);

        sampleAllButton.setFont(new java.awt.Font("Dialog", 0, 10));
        sampleAllButton.setText("Print all sample reports");
        sampleAllButton.setEnabled(false);
        sampleAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sampleAllButtonActionPerformed(evt);
            }
        });

        sampleMethodSummary.setFont(new java.awt.Font("Dialog", 0, 10));
        sampleMethodSummary.setText("Sample Method Summary");
        sampleMethodSummary.setEnabled(false);

        sampleCOA.setFont(new java.awt.Font("Dialog", 0, 10));
        sampleCOA.setText("Sample COA / Comments");
        sampleCOA.setEnabled(false);

        sampleComments.setFont(new java.awt.Font("Dialog", 0, 10));
        sampleComments.setText("Sample Comments");
        sampleComments.setEnabled(false);

        sampleChronicle.setFont(new java.awt.Font("Dialog", 0, 10));
        sampleChronicle.setText("Sample chronicle");
        sampleChronicle.setEnabled(false);

        sampleCoverLetter.setFont(new java.awt.Font("Dialog", 0, 10));
        sampleCoverLetter.setText("Sample Cover Letter");
        sampleCoverLetter.setEnabled(false);

        sdgCocButton.setFont(new java.awt.Font("Dialog", 0, 10));
        sdgCocButton.setText("C.O.C.");
        sdgCocButton.setEnabled(false);

        sdgLabChronicle.setFont(new java.awt.Font("Dialog", 0, 10));
        sdgLabChronicle.setText("Receipt");
        sdgLabChronicle.setEnabled(false);

        sdgInvoiceButton.setFont(new java.awt.Font("Dialog", 0, 10));
        sdgInvoiceButton.setText("Invoice");

        printDSGlabelsButton.setFont(new java.awt.Font("Dialog", 0, 10));
        printDSGlabelsButton.setText("Container Labels");
        printDSGlabelsButton.setActionCommand("labels");
        printDSGlabelsButton.setEnabled(false);

        jLabel2.setText("SDG reports");
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 10));

        jLabel3.setText("SDG samples reports");
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 10));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(jLabel2)
                .add(94, 94, 94)
                .add(jLabel3))
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(sdgCocButton)
                .add(93, 93, 93)
                .add(sampleAllButton))
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(sdgLabChronicle)
                .add(102, 102, 102)
                .add(sampleCoverLetter))
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(sdgInvoiceButton)
                .add(104, 104, 104)
                .add(sampleChronicle))
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(printDSGlabelsButton)
                .add(60, 60, 60)
                .add(sampleComments))
            .add(jPanel1Layout.createSequentialGroup()
                .add(179, 179, 179)
                .add(sampleCOA))
            .add(jPanel1Layout.createSequentialGroup()
                .add(179, 179, 179)
                .add(sampleMethodSummary))
            .add(jPanel1Layout.createSequentialGroup()
                .add(14, 14, 14)
                .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 355, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(134, 134, 134)
                .add(printButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(5, 5, 5)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(sdgCocButton))
                    .add(sampleAllButton))
                .add(2, 2, 2)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(sdgLabChronicle))
                    .add(sampleCoverLetter))
                .add(2, 2, 2)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(sdgInvoiceButton))
                    .add(sampleChronicle))
                .add(2, 2, 2)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(printDSGlabelsButton))
                    .add(sampleComments))
                .add(2, 2, 2)
                .add(sampleCOA)
                .add(7, 7, 7)
                .add(sampleMethodSummary)
                .add(2, 2, 2)
                .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(printButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(70, 70, 70)
                .add(banner))
            .add(topLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1024, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 400, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(banner))
                    .add(topLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 300, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void sampleAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sampleAllButtonActionPerformed


      try{
          
          if( sampleAllButton.isSelected() ){
              
                sampleCoverLetter.setSelected( true );
                sampleChronicle.setSelected( true );
                sampleComments.setSelected( true );
                sampleCOA.setSelected( true );
                sampleMethodSummary.setSelected( true );

                sampleCoverLetter.setEnabled( false );
                sampleChronicle.setEnabled( false );
                sampleComments.setEnabled( false );
                sampleCOA.setEnabled( false );
                sampleMethodSummary.setEnabled( false );
                
                
          }else{
              
                sampleCoverLetter.setSelected( false );
                sampleChronicle.setSelected( false );
                sampleComments.setSelected( false );
                sampleCOA.setSelected( false );
                sampleMethodSummary.setSelected( false );  

                sampleCoverLetter.setEnabled( true );
                sampleChronicle.setEnabled( true );
                sampleComments.setEnabled( true );
                sampleCOA.setEnabled( true );
                sampleMethodSummary.setEnabled( true );
                
          }
          
        }
        catch( Exception error	){
            
                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
        } 
            
      
      
  }//GEN-LAST:event_sampleAllButtonActionPerformed

  
    public void progress(int progress, String display) {
        progressBar.setValue( progress );
        progressBar.setString( display );
        progressBar.paint( progressBar.getGraphics() );                    
    }
  
  
  private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed


      try{
          
            log.log( this, "Batch printing starting..." );

          
                      
            // calc progress steps
            int progressBarSteps = ( 100 / selectedSDGs.length );
            int currentProgress = 0;
            
            progress( currentProgress, "Printing..." );
          
            persistentObjectManagerClass persistentObjectManager = 
                    new persistentObjectManagerClass( configuration, log );             
                        
            
            log.log( this, "For each SDG..." );            
            
            // for each SDG
            for( int index = 0; index < selectedSDGs.length; index++ ){

//                // print sdg reports
//                if( sdgCocButton.isSelected() ){
//                    printCOC( selectedSDGs[ index ], configuration, log, this );
//                }
                                
                if( sdgInvoiceButton.isSelected() ){
                       printInvoice( selectedSDGs[ index ], configuration, log, this );
                }
                

//                if( printDSGlabelsButton.isSelected() ){
//                    
//                    //----------------------------------------------------------
//                    
//                    if( labelPrinterJob == null || labelPageFormat == null ){
//                        
//                        labelPrinterJob = PrinterJob.getPrinterJob();                
//                        labelPageFormat = labelPrinterJob.defaultPage();
//
//
//                        if( !labelPrinterJob.printDialog() ){
//                            throw new KExceptionClass( "\n*** PrintJob is cancelled ***\n", null );
//                        };  
//                    }
                    
                    //----------------------------------------------------------                    
                    
                    
//                    printContainerLabels( 
//                        selectedSDGs[ index ], configuration, log, this, 
//                        labelPrinterJob, labelPageFormat
//                        );                    
//                }
                
                
                    
                currentProgress += progressBarSteps;                
                progress( currentProgress, "Printing..." );                

            }

            // done.            
            
            progress( 100, "Printing... complete." );                            

            setVisible (false);
            dispose ();
            
            
        }
        catch( Exception error	){
            
                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
        } 
                    
            
      
  }//GEN-LAST:event_printButtonActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible (false);
        dispose ();
    }//GEN-LAST:event_closeDialog

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel banner;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton printButton;
    private javax.swing.JCheckBox printDSGlabelsButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JCheckBox sampleAllButton;
    private javax.swing.JCheckBox sampleCOA;
    private javax.swing.JCheckBox sampleChronicle;
    private javax.swing.JCheckBox sampleComments;
    private javax.swing.JCheckBox sampleCoverLetter;
    private javax.swing.JCheckBox sampleMethodSummary;
    private javax.swing.JCheckBox sdgCocButton;
    private javax.swing.JCheckBox sdgInvoiceButton;
    private javax.swing.JCheckBox sdgLabChronicle;
    private javax.swing.JLabel topLabel;
    // End of variables declaration//GEN-END:variables

//====================================================================================
    

    static public void printInvoice( 
        long sdg_id, 
        KConfigurationClass configuration, KLogClass log, Component caller )
    throws KExceptionClass
    {
        
        
            log.log( caller, "Print invoice..." );                                        
                       

            persistentObjectManagerClass persistentObjectManager = 
                    new persistentObjectManagerClass( configuration, log );             

            //materialize the sdg
            ASHURSampleDeliveryGroupClass sdg = new ASHURSampleDeliveryGroupClass();
            sdg = (ASHURSampleDeliveryGroupClass) persistentObjectManager.copy4( sdg_id, ASHURSampleDeliveryGroupClass.class );  

            //materialize the project
            ASHURProjectClass project = new ASHURProjectClass();
            project = (ASHURProjectClass) persistentObjectManager.copy4( sdg.getProjectId(), ASHURProjectClass.class );                        

            //materialize the client
            ASHURClientClass client = new ASHURClientClass();
            client = (ASHURClientClass) persistentObjectManager.copy4( project.getClientId(), ASHURClientClass.class );  
            
            //materialize the bill to
            ASHURBilltoClass billTo = new ASHURBilltoClass();
            billTo = (ASHURBilltoClass) persistentObjectManager.copy4( sdg.getBilltoId(), ASHURBilltoClass.class );   
            
            //materialize the report link and report type
            
                    
            // ---------------------------------------------------------------------------------------
            // ---------------------------------------------------------------------------------------
            //Header section
            KPrintSectionClass headerSection = new KPrintSectionClass(configuration, log, 520, 140);           

            // Header: Address "Mount Holly"
            headerSection.setFont( new Font( "arial", Font.PLAIN,                        8) ); 
            headerSection.printText("Kframework Municipal Utilities Authority ",   350, 40 );
            headerSection.printText("(Certification Number:1234)",              350, 50 );
            headerSection.printText("300 Saturn Road, PO BOX 486",                350, 60 );
            headerSection.printText("Mount Everest KA 08060",                         350, 70 );
            headerSection.printText("Phone 609-123-1223  Fax: 609-123-1234",        350, 80 );
            headerSection.printText("Email: vmaxxed@users.sourceforge.net",                        350, 90 );
            

            headerSection.setFont( new Font( "arial", Font.PLAIN,                       12) );
            headerSection.printText("Analytical Testing Service Summary ",          170, 100 );
            headerSection.printLine(170,114,363,114);            
            headerSection.setFont( new Font( "arial", Font.PLAIN,                        10) );
            headerSection.printText("Date:",                                          0, 120 );
            headerSection.printText( KMetaUtilsClass.time(),                          70, 120 );
            headerSection.printText("Summary #: ",                                  350, 120 );
            headerSection.printText(sdg.getSdgLabId(),                                 405, 120 );
            
            // ---------------------------------------------------------------------------------------
            
            // Address section
            KPrintSectionClass addressSection = new KPrintSectionClass(configuration, log, 520, 130 );                 
            addressSection.setFont( new Font( "arial", Font.BOLD, 10) );
            addressSection.printText("Bill To:",                                         0, 20 );
            addressSection.printText("Client Details:",                                170, 20 );
            addressSection.printText("Project Details:",                               350, 20 );
            
            // Address "Report-To"
            addressSection.setFont( new Font( "arial", Font.PLAIN, 8) );
            addressSection.printField(billTo.getBilltoName(),                   0, 35, 150 );
            addressSection.printField(billTo.getBilltoAddress1(),               0, 47, 150 );
            addressSection.printField(billTo.getBilltoCity(),                   0, 59, 150 );
            addressSection.printText(billTo.getBilltoState(),                   0, 71 );
            addressSection.printText(billTo.getBilltoZip(),                    30, 71 );
            addressSection.printText("Tel:",                                0, 83 );
            addressSection.printField(billTo.getBilltoPhone(),                 50, 83, 150 );
            addressSection.printText("CC #:",                               0, 95 );
            addressSection.printField(billTo.getBilltoCardNumber(),           50, 95, 150 );
            addressSection.printText("Expiration:",                         0, 107 );
            addressSection.printField(billTo.getBilltoCardExpiration(),       50, 107, 150 );
            addressSection.printText("Owner:",                              0, 119 );
            addressSection.printField(billTo.getBilltoCardOwner(),            50, 119, 150 );
            
            
            // Client details
            addressSection.setFont( new Font( "arial", Font.PLAIN, 8) );
            addressSection.printText("Name:",                             170, 35 );
            addressSection.printField(client.getClientName(),                 205, 35, 140 );
            addressSection.printText("Add:",                              170, 47 );
            addressSection.printField(client.getClientAddress1(),             205, 47, 140 );
            addressSection.printText("City:",                             170, 59 );
            addressSection.printField(client.getClientCity(),                 205, 59, 140 );
            addressSection.printText("State:",                            170, 71 );
            addressSection.printText(client.getClientState(),                 205, 71 );
            addressSection.printText(client.getClientZip(),                   235, 71 );
            addressSection.printText("Contact:",                          170, 83 );
            addressSection.printField(client.getClientContactName(),         205, 83, 140 );
            addressSection.printText("Phone:",                            170, 95 );
            addressSection.printField(client.getClientPhone(),                205, 95, 140 );
            
            // Header: Lab Details
            addressSection.setFont( new Font( "arial", Font.PLAIN, 8) );
            addressSection.printText("Proj Name:",                           350, 59 );
            addressSection.printField(project.getProjectName(),                  405, 59, 120 );
            addressSection.printText("Proj. Client #:",                      350, 47 );
            addressSection.printField(project.getProjectClientProjectNumber(), 405, 47, 120 );
            addressSection.printText("Proj. Desc.:",                         350, 71 );
            addressSection.printField(project.getProjectDescription(),           405, 71, 120 );
            addressSection.printText("Proj. PO #:",                          350, 35 );
            addressSection.printField(project.getProjectPoNumber(),             405, 35, 120 );
            
            
            
            
            
            
            //-----------------------------------------------------------------------------------------------------------
            //-----------------------------------------------------------------------------------------------------------
            //-----------------------------------------------------------------------------------------------------------
            
            // SDG SUMMARY
            KPrintSectionClass sdgSummarySection = new KPrintSectionClass(configuration, log, 520, 150 );
            
            sdgSummarySection.setFont( new Font( "arial", Font.PLAIN, 10) );
            sdgSummarySection.printText("This summary applies to the following Client Sample(s) submitted to the laboratory on:", 0, 1 );
            sdgSummarySection.printText( KMetaUtilsClass.dateToString(KMetaUtilsClass.KDEFAULT_DATE_FORMAT, sdg.getSdgReceivedDate()),    385,  1 );
            
            //sdgSummarySection.printText("Report type requested for Sample Group Submitted:", 0, 12 );
            //sdgSummarySection.printText("(SDG Report Type)",        240, 12 );
            
            sdgSummarySection.printText("TAT Requested for SDG Reportv due:", 0, 12 );
            //sdgSummarySection.printText("SDG REP. TAT DESC.",       160, 12 );                        
            
            sdgSummarySection.printText( KMetaUtilsClass.dateToString(KMetaUtilsClass.KDEFAULT_DATE_FORMAT, sdg.getSdgTatDate() ),          170, 12 );
            //sdgSummarySection.printText("Mail Date?",               410, 12 );
            
            // SDG  SUMMARY Totals
            
            
            sdgSummarySection.setFont( new Font( "arial", Font.PLAIN, 10) );
            
            // show gross total DB
            sdgSummarySection.printText("Sample Group Analysis Total.................",     30, 32 );
            sdgSummarySection.printField(KMetaUtilsClass.toCurrencyString( sdg.getSdgSampleTotal().doubleValue() ), 400, 32, 100,sdgSummarySection.RIGHT  );
            //headerSection.printField( sdg.sdg_lab_id,  415, 130, 200, headerSection.LEFT ); 
            //show TAT surcharge Calc            
            sdgSummarySection.printText("Sample Group Rush TAT Surcharge.............",     30, 44 );
            sdgSummarySection.printText(sdg.getSdgTatMultiplier().toString(),                            250, 44 );
            
            double tatSurchargeValue =  sdg.getSdgSampleTotal().doubleValue() * (  sdg.getSdgTatMultiplier().doubleValue()   - 1 );
            
            sdgSummarySection.printField(KMetaUtilsClass.toCurrencyString(tatSurchargeValue),                        400, 44,100,sdgSummarySection.RIGHT );
            
            // show total DB
            sdgSummarySection.printText("Sample Group Sub Total......................",     30, 56 );
            sdgSummarySection.printField(KMetaUtilsClass.toCurrencyString(  sdg.getSdgPrice().doubleValue() ),        400, 56,100,sdgSummarySection.RIGHT );
            
            //Show Discount Calc
            sdgSummarySection.printText("Sample Group Discount (%)................",       30, 68 );
            sdgSummarySection.printField( sdg.getSdgUserDiscount().toString() ,                                                     250, 68, 30, sdgSummarySection.LEFT );
            
            double sdgDiscountValue =  sdg.getSdgPrice().doubleValue()  *(  sdg.getSdgUserDiscount().doubleValue() / 100);
            
            sdgSummarySection.printField(KMetaUtilsClass.toCurrencyString(sdgDiscountValue),                         400, 68, 100,sdgSummarySection.RIGHT  );
            
            //Show Net user total from DB
            sdgSummarySection.setFont( new Font( "arial", Font.PLAIN|Font.BOLD, 10) );
            sdgSummarySection.printText("Sample Group Total Charges.................",       30, 90 );
            sdgSummarySection.printField(KMetaUtilsClass.toCurrencyString(sdg.getSdgUserDiscount().doubleValue() ),   400,  90, 100,sdgSummarySection.RIGHT  );
            
            
            sdgSummarySection.setFont( new Font( "arial", Font.PLAIN, 10) );
            sdgSummarySection.printText("SDG Summary Comments:",                             0, 110 );
            sdgSummarySection.printField(sdg.getSdgComments(),                           30, 122, 400 );
            
            
             
            
            
            
            //--------------------------------------------------------------------------------------
            //--------------------------------------------------------------------------------------

            // heading and Analytical Testing Service Summary
            // get a KePrintJob
            KPrintJobClass printJob = 
                new KPrintJobClass( configuration, log ); 

            PageFormat fixedPageFormat = ( PageFormat ) KPrintJobClass.defaultPageFormat.clone();
            fixedPageFormat.setOrientation( PageFormat.PORTRAIT );
            
            printJob.useSpecificPrintingDefaults(
                KPrintJobClass.defaultPrinterJob, fixedPageFormat );   

            
            
            // start job 
            printJob.setDefaultFont( new Font("Arial", Font.PLAIN, 10) );  
            printJob.setLeftMargin( 50 );
            printJob.setBottomMargin( 40 );
           
            printJob.startPrintJob();   //pop up system print dialog box            
            
            printJob.SetHeader( headerSection, KPrintJobClass.CENTER );                         
            
            printJob.printSection( addressSection, KPrintJobClass.CENTER );
            printJob.printSection( sdgSummarySection, KPrintJobClass.CENTER );
           
            
            //------------------------------------------------------------------
            //Get relevant samples
            
//** A            
            
                dbTransactionClientClass dbTransaction = 
                    new dbTransactionClientClass( configuration, log ); 
                
                 if( configuration.getField( "databaseType" ).equals( "oracle" ) ){                    

                        dbTransaction.prepare(

                            " select " +
                                 " sample.SAMPLE_CLIENT_ID , " + 
                                 " sample.SAMPLE_DESCRIPTION , " +  
                                 " sample.SAMPLE_MATRIX_CODE, " + 
                                 " sample.SAMPLE_TYPE_CODE , " + 
                                 " TO_CHAR( sample.SAMPLE_SAMPLING_DATE, 'MM/DD/YY' ) AS SAMPLE_SAMPLING_DATE," +
                                 " TO_CHAR( sample.SAMPLE_SAMPLING_TIME , 'HH24MI' ) AS SAMPLE_SAMPLING_TIME," + 
                                 " sample.SAMPLE_LAB_ID,  " +

                                 " TO_CHAR(sample.SAMPLE_TAT,'MM/DD/YY') AS SAMPLE_TAT,"+
                                 " sample.SAMPLE_TAT_MULTIPLIER, "+
                                 " TO_CHAR( sample.SAMPLE_USER_DISCOUNT, '999.99' ) AS SAMPLE_USER_DISCOUNT, "+
                                 " TO_CHAR( sample.SAMPLE_ANALYSES_TOTAL,'999999.99' ) AS SAMPLE_ANALYSES_TOTAL,  "+
                                 " TO_CHAR( sample.SAMPLE_ANALYSES_TOTAL*sample.SAMPLE_TAT_MULTIPLIER, '999999.99' ) AS SURCHARGE,"+
                                 " TO_CHAR ( sample.SAMPLE_TOTAL, '999999.99' ) AS SAMPLE_TOTAL, "+
                                 //" ROUND( sample.SAMPLE_TOTAL, 2 ) AS SAMPLE_TOTAL, "+
                                 " TO_CHAR( sample.SAMPLE_TOTAL*(sample.SAMPLE_USER_DISCOUNT/100),'999999.99' ) AS DISCOUNT, "+
                                 " TO_CHAR( sample.SAMPLE_USER_TOTAL, '999999.99' ) AS SAMPLE_USER_TOTAL, "+
                                 " samplingpoint.SAMPLINGPOINT_NAME,"+
                                 " samplingpoint.SAMPLINGPOINT_NUMBER, " +
                                 " samplingpoint.SAMPLINGPOINT_DESCRIPTION, "+
                                 " samplingpoint.SAMPLINGPOINT_ADDRESS1, "+
                                 " samplingpoint.SAMPLINGPOINT_CITY, "+
                                 " samplingpoint.SAMPLINGPOINT_STATE, "+
                                 " samplingpoint.SAMPLINGPOINT_ZIP, "+
                                 " sample.SAMPLE_ID  " +

                             // End Fields used in the receipt
                             //--------------------------------------------------
                             " from " +
                                " ashur_sample sample, ashur_sdg_samplingpoint_link link," + 
                                " ashur_samplingpoint samplingpoint " +
                            " where " +
                                "(" +
                                    "(sample.sdg_id= :v1) " +
                                    " AND " +
                                    "( link.link_id = sample.link_id )" +
                                    " AND " +                            
                                    "( samplingpoint.samplingpoint_id = link.samplingpoint_id ) " +                          
                                 ")" 
                        );
                        
                }else if(configuration.getField( "databaseType" ).equals( "mssql" ) ){  

                        dbTransaction.prepare(

                            " select " +
                                 " sample.SAMPLE_CLIENT_ID , " + 
                                 " sample.SAMPLE_DESCRIPTION , " +  
                                 " sample.SAMPLE_MATRIX_CODE, " + 
                                 " sample.SAMPLE_TYPE_CODE , " + 
                                
                             " CONVERT( VARCHAR(10),sample.SAMPLE_SAMPLING_DATE, 102 ) AS SAMPLE_SAMPLING_DATE," +
                             " CONVERT( VARCHAR(10),sample.SAMPLE_SAMPLING_TIME, 108 ) AS SAMPLE_SAMPLING_TIME," + 
                                
                                 " sample.SAMPLE_LAB_ID,  " +

                             " CONVERT( VARCHAR(10), sample.SAMPLE_TAT, 102 ) AS SAMPLE_TAT,"+
                                
                                 " sample.SAMPLE_TAT_MULTIPLIER, "+
                                
                             " CAST( sample.SAMPLE_USER_DISCOUNT AS MONEY ) AS SAMPLE_USER_DISCOUNT, "+
                             " CAST( sample.SAMPLE_ANALYSES_TOTAL AS MONEY  ) AS SAMPLE_ANALYSES_TOTAL,  "+
                             " CAST( sample.SAMPLE_ANALYSES_TOTAL*sample.SAMPLE_TAT_MULTIPLIER AS MONEY  ) AS SURCHARGE,"+
                             " CAST( sample.SAMPLE_TOTAL AS MONEY ) AS SAMPLE_TOTAL, "+                                 
                             " CAST( sample.SAMPLE_TOTAL*(sample.SAMPLE_USER_DISCOUNT/100) AS MONEY ) AS DISCOUNT, "+
                             " CAST( sample.SAMPLE_USER_TOTAL AS MONEY ) AS SAMPLE_USER_TOTAL, "+
                                
                                 " samplingpoint.SAMPLINGPOINT_NAME,"+
                                 " samplingpoint.SAMPLINGPOINT_NUMBER, " +
                                 " samplingpoint.SAMPLINGPOINT_DESCRIPTION, "+
                                 " samplingpoint.SAMPLINGPOINT_ADDRESS1, "+
                                 " samplingpoint.SAMPLINGPOINT_CITY, "+
                                 " samplingpoint.SAMPLINGPOINT_STATE, "+
                                 " samplingpoint.SAMPLINGPOINT_ZIP, "+
                                 " sample.SAMPLE_ID  " +

                             // End Fields used in the receipt
                             //--------------------------------------------------
                             " from " +
                                " ashur_sample sample, ashur_sdg_samplingpoint_link link," + 
                                " ashur_samplingpoint samplingpoint " +
                            " where " +
                                "(" +
                                    "(sample.sdg_id= ? ) " +
                                    " AND " +
                                    "( link.link_id = sample.link_id )" +
                                    " AND " +                            
                                    "( samplingpoint.samplingpoint_id = link.samplingpoint_id ) " +                          
                                 ")" 
                        );                    
                    
                }else{
                    throw new KExceptionClass( "databaseType not recognized " + configuration.getField( "databaseType" ), null );
                }                    
                                        

                dbTransaction.bind( ":v1",  sdg_id );                  

                log.log( caller, "Loading ashur sample, sample point data" );                                                                                                                
                dbTransaction.executeQuery( 0, 655356 );
                log.log( caller, "Loading complete." );                                                  

            // --------------------------------------------------------    
                
               //to get analysis info
                dbTransactionClientClass AnalysisdbTransaction = 
                    new dbTransactionClientClass( configuration, log ); 
                    
                //for each sample, print sample and get analysis info
                while( dbTransaction.fetch() ) { 
                    
                    
                    //print out sample info
                    KPrintSectionClass sampleSection = new KPrintSectionClass(configuration, log, 520, 90 ); 

                    sampleSection.setFont( new Font( "arial", Font.BOLD, 10) );
                    sampleSection.printText("Sample Details for Lab ID:",                          0, 1 );
                    sampleSection.printLine(0,12,210,12);
                    sampleSection.setFont( new Font( "arial", Font.ITALIC|Font.BOLD, 10) );
                    String valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_LAB_ID" );   
                    sampleSection.printText(valueBuffer,                                         125, 1 );
                    
                    sampleSection.setFont( new Font( "arial", Font.BOLD, 8) );
                    sampleSection.printText("Sample ID:",                                         0, 20 );
                    sampleSection.printText("Description:",                                       0, 30 );
                    sampleSection.printText("Matrix:",                                            0, 40 );
                    sampleSection.printText("Type:",                                              0, 50 );
                    sampleSection.printText("Date:",                                              0, 60 );
                    sampleSection.printText("Time:",                                              0, 70 );
                    
                    sampleSection.printText("Site Name:",                                       280, 20 );
                    sampleSection.printText("Site #:",                                          280, 30 );
                    sampleSection.printText("Site Desc.:",                                      280, 40 );
                    sampleSection.printText("Site Add.:",                                       280, 50 );
                    sampleSection.printText("Site City:",                                       280, 60 );
                    sampleSection.printText("State:",                                           280, 70 );

                    //sampleSection.printText("Lab ID:",                                           350, 20 );
                    
                     

                    sampleSection.setFont( new Font( "arial", Font.PLAIN, 8) );
                    
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_CLIENT_ID" ); 
                    sampleSection.printField(valueBuffer,                                    65, 20, 200 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_DESCRIPTION" ); 
                    sampleSection.printField(valueBuffer,                                    65, 30, 200 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_MATRIX_CODE" ); 
                    sampleSection.printText(valueBuffer,                                     65, 40 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_TYPE_CODE" ); 
                    sampleSection.printText(valueBuffer,                                     65, 50 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_SAMPLING_DATE" ); 
                    sampleSection.printText(valueBuffer,                                     65, 60 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_SAMPLING_TIME" ); 
                    sampleSection.printText(valueBuffer,                                     65, 70 );
                    
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLINGPOINT_NAME" ); 
                    sampleSection.printText(valueBuffer,                                   340, 20 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLINGPOINT_NUMBER" ); 
                    sampleSection.printText(valueBuffer,                                   340, 30 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLINGPOINT_DESCRIPTION" ); 
                    sampleSection.printText(valueBuffer,                                   340, 40 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLINGPOINT_ADDRESS1" ); 
                    sampleSection.printText(valueBuffer,                                   340, 50 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLINGPOINT_CITY" ); 
                    sampleSection.printText(valueBuffer,                                   340, 60 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLINGPOINT_STATE" ); 
                    sampleSection.printText(valueBuffer,                                   340, 70 ); 
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLINGPOINT_ZIP" ); 
                    sampleSection.printText(valueBuffer,                                   370, 70 );
                    
                    String sampleID = (String) dbTransaction.getProperty( "SAMPLE_ID" ); 

                    printJob.printSection( sampleSection, KPrintJobClass.CENTER );
                    
                    
                    //-----------------------------------------------------------------------------------------------
                    //-----------------------------------------------------------------------------------------------
                    
                    //This section prepares the format for sample-analysis
                    KPrintSectionClass sampleAnalysisSection = new KPrintSectionClass(configuration, log, 520,80 );
                    
                    sampleAnalysisSection.setFont( new Font( "arial", Font.PLAIN, 8) );
                    sampleAnalysisSection.printText("Sample Special Rush TAT requested due:", 30, 10 );
                    //sampleAnalysisSection.printText("(Rush Sample TAT Description??)",       170, 10 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_TAT" ); 
                    sampleAnalysisSection.printText(valueBuffer,                             185, 10 );
                    //sampleAnalysisSection.printText("Pending",                               460, 10 );
                    /*
                    sampleAnalysisSection.printText("Sample TAT surcharge :",                 30, 22 );
                    valueBuffer = dbTransaction.getProperty( "SAMPLE_TAT_MULTIPLIER" );
                    sampleAnalysisSection.printText(valueBuffer,                             130, 22 );
                    
                    sampleAnalysisSection.printText("Sample Discount applied (%):",          150, 22 );
                    valueBuffer = dbTransaction.getProperty( "SAMPLE_USER_DISCOUNT" ); 
                    sampleAnalysisSection.printText(valueBuffer,                             260, 22 );
                    */
                    sampleAnalysisSection.printText("Sample Rush TAT Surcharge",              30, 22 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_TAT_MULTIPLIER" );
                    sampleAnalysisSection.printText(valueBuffer,                             150, 22 );
                    valueBuffer = (String) dbTransaction.getProperty( "SURCHARGE" ); 
                    sampleAnalysisSection.printField(valueBuffer,                             230, 22,55, sampleAnalysisSection.RIGHT );
                    
                    sampleAnalysisSection.printText("Sample Sub Total",                       30, 34 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_TOTAL" );
                    sampleAnalysisSection.printField(valueBuffer,                             230, 34,55, sampleAnalysisSection.RIGHT );
                    
                    sampleAnalysisSection.printText("Sample Discount (%)",                    30, 46 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_USER_DISCOUNT" ); 
                    sampleAnalysisSection.printText(valueBuffer,                             150, 46 );
                    valueBuffer = (String) dbTransaction.getProperty( "DISCOUNT" );
                    sampleAnalysisSection.printField(valueBuffer,                             230, 46,55, sampleAnalysisSection.RIGHT );
                    
                    sampleAnalysisSection.setFont( new Font( "arial", Font.BOLD, 8) );                    
                    sampleAnalysisSection.printText("Sample Total Charges",                   30, 58 );
                    valueBuffer = (String) dbTransaction.getProperty( "SAMPLE_USER_TOTAL" );
                    sampleAnalysisSection.printField(valueBuffer,                             230, 58,55, sampleAnalysisSection.RIGHT );
                    
                    //----------------------------------------------------------------------------------------
                    KPrintSectionClass analysisHeadSection = new KPrintSectionClass(configuration, log, 520, 30 );
                    analysisHeadSection.setFont( new Font( "arial", Font.BOLD, 8) );
                    
                    analysisHeadSection.printText("Test-Analysis Name",                            30, 10 );
                    analysisHeadSection.printText("Method",                                       175, 10 );
                    analysisHeadSection.printText("Price",                                        265, 10 );
                    analysisHeadSection.printText("%Discount",                                    300, 10 );
                    analysisHeadSection.printText("Client Price",                                 375, 10 );

// obsoleted with autheaders
//printJob.printSection( analysisHeadSection, printJobClass.CENTER );
                    
                    //----------------------------------------------------------------------------------------
                    
                   //to get analysis info
                    AnalysisdbTransaction.reset(); 
                    
//** B                  
                    
                    if( configuration.getField( "databaseType" ).equals( "oracle" ) ){                                    

                       
                            AnalysisdbTransaction.prepare(   

                                " select " +
                                     " analysis_link.ANALYSIS_NAME , " + 
                                     " analysis_link.ANALYSIS_METHOD,"+
                                     " TO_CHAR( analysis_link.ANALYSIS_PRICE, '999999.99') AS ANALYSIS_PRICE , " +               
                                     " analysis_link.ANALYSIS_DISCOUNT," +                
                                     " TO_CHAR( analysis_link.ANALYSIS_CLIENT_PRICE, '999999.99') AS ANALYSIS_CLIENT_PRICE,  " +      
                                     " analysis_link.SAMPLE_ID  " +  
                                     //" ROUND( sample.SAMPLE_TOTAL, 2 ) AS SAMPLE_TOTAL, "+
                                     //" TO_CHAR(sample.SAMPLE_TAT,'99999.99') AS SAMPLE_TAT,"+

                                 // End Fields used in the receipt
                                 //--------------------------------------------------
                                 " from " +
                                    " ashur_sample sample, ashur_sample_analysis_link analysis_link " +
                                " where " +
                                    "(" +
                                        "(sample.sample_id = :v1 ) " +
                                        " AND " +                                    
                                        "( analysis_link.sample_id = sample.sample_id )" +   
                                    ")" 

                            );     

                    }else if(configuration.getField( "databaseType" ).equals( "mssql" ) ){  

                            AnalysisdbTransaction.prepare(   

                                " select " +
                                     " analysis_link.ANALYSIS_NAME , " + 
                                     " analysis_link.ANALYSIS_METHOD,"+
                                  " CAST( analysis_link.ANALYSIS_PRICE as money ) AS ANALYSIS_PRICE , " +               
                                     " analysis_link.ANALYSIS_DISCOUNT," +                
                                  " CAST( analysis_link.ANALYSIS_CLIENT_PRICE as money ) AS ANALYSIS_CLIENT_PRICE,  " +      
                                     " analysis_link.SAMPLE_ID  " +  
                                     //" ROUND( sample.SAMPLE_TOTAL, 2 ) AS SAMPLE_TOTAL, "+
                                     //" TO_CHAR(sample.SAMPLE_TAT,'99999.99') AS SAMPLE_TAT,"+

                                 // End Fields used in the receipt
                                 //--------------------------------------------------
                                 " from " +
                                    " ashur_sample sample, ashur_sample_analysis_link analysis_link " +
                                " where " +
                                    "(" +
                                        "(sample.sample_id = ? ) " +
                                        " AND " +                                    
                                        "( analysis_link.sample_id = sample.sample_id )" +   
                                    ")" 

                            );  

                    }else{
                        throw new KExceptionClass( "databaseType not recognized " + configuration.getField( "databaseType" ), null );
                    }                            


                          

                    AnalysisdbTransaction.bind( ":v1", KMetaUtilsClass.getIntegralNumericValueFromString(sampleID ) );   
                    
                    log.log( caller, "Loading ashur analysis data" );                                                                                                                
                    AnalysisdbTransaction.executeQuery( 0, 655356 );
                    log.log( caller, "Loading complete." );                                                  

                     
                    
                    // setup the DB printer
                    KPrintDataTableClass dbTable = new KPrintDataTableClass( 
                               configuration, log, 
                               AnalysisdbTransaction, printJob, 0, 655356 );
                
                    //print anasys info
                    dbTable.addField( "ANALYSIS_NAME",        "NAME",        125 );   
                    dbTable.addField( "ANALYSIS_METHOD",      "METHOD",       60 );
                    dbTable.addField( "ANALYSIS_PRICE",       "PRICE",        40 );        
                    dbTable.addField( "ANALYSIS_DISCOUNT",    "DISCOUNT",     40 );                        
                    dbTable.addField( "ANALYSIS_CLIENT_PRICE","CLIENT PRICE", 100, KPrintJobClass.RIGHT );                        
                    //DBPrinter.addField( "SAMPLE_ID",            "SAMPLE ID",    30 );  
                    
                    
                    dbTable.addSummary( "CLIENT PRICE", KPrintDataTableClass.SUM, "Total: $", null, 2 );
                    
                    
                    dbTable.setPrintingField( "NAME" );
                    dbTable.setPrintingField( "METHOD" );
                    dbTable.setPrintingField( "PRICE" );        
                    dbTable.setPrintingField( "DISCOUNT" );                        
                    dbTable.setPrintingField( "CLIENT PRICE" );                        
                    //DBPrinter.setPrintingField( "SAMPLE ID" );  
                    
                    
                    dbTable.setHorizontalSpace( 20 );
                    printJob.setDefaultFont( new Font("Arial", Font.PLAIN, 8) ); 
                    int oldLeftMargin = printJob.getLeftMargin();
                    printJob.setLeftMargin(75);              
                    dbTable.setHeadersMode( dbTable.HEADER_TYPE_INLINEHEADER );                 
                    
                    dbTable.print();
                    
                    printJob.setDefaultFont( new Font("Arial", Font.PLAIN, 10) );
                    printJob.setLeftMargin(oldLeftMargin);
                   
                    
                    printJob.printSection( sampleAnalysisSection, KPrintJobClass.CENTER );   

                } //end of while loop
                
                printJob.submitPrintJob();  
    
    }

//==========================================================================================    
    
}
