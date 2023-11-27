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

//rtl
import java.util.*;
import javax.swing.*;

//K
import KFramework30.Base.*;
import KFramework30.Communication.*;
import KFramework30.Widgets.*;
import KFramework30.Widgets.TreeView.*;

public class sdgTreeViewClass 
extends KTreeViewerClass 
{

       
    // inner class
    public interface sdgTreeViewListenerClass{
        
        public  void mouseClickPerformed( String ID, int level, String label );
    }    
    
    // uses
    private KDataBrowserBaseClass       dataBrowser;
    private sdgTreeViewListenerClass    statusListener;
    private java.awt.Window             parentWindow;
            
    // has
    private String              defaultFilter;
    private java.util.List      defaultParameters;
    

    
    /** Creates new usersTreeView */
    public sdgTreeViewClass(
        KConfigurationClass configurationParam, KLogClass logParam,
        java.awt.Window parentWindowParam,
        JTree treeParam, KDataBrowserBaseClass tableFillerParam,       
        sdgTreeViewListenerClass statusListenerParam
        )
    throws KExceptionClass
    {

        
        // inherits
        super(  configurationParam, logParam, parentWindowParam, 
                treeParam, new javax.swing.ImageIcon( configurationParam.getClass().getResource("/resources/root.gif") ),
                "ALL"  );                    
     
                      
        // uses
        dataBrowser = tableFillerParam;       
        statusListener = statusListenerParam;
        parentWindow = parentWindowParam;
        
        // has
        defaultParameters = new ArrayList();        
        
        //-----------------------        
        // setup
        
        // save filler parmas
        defaultFilter = dataBrowser.getDefaultCriteria();
        defaultParameters.addAll( dataBrowser.getDefaultParameters() );
        
        //-----------------------            
        // LEVELS
        
        // set levels in order from root to leaf
        
        // 
        dbTransactionClientClass level1dbTransaction = new dbTransactionClientClass( configuration, log );                 
        
        //** 
        level1dbTransaction.prepare( " select distinct sdg_status from ashur_sampledeliverygroup  " );        
        
        addLevel( 
            "SDG_STATUS", "SDG_STATUS", level1dbTransaction , 
            KTreeViewerClass.NODE_MODE_AUTO, null );
        
        
        //-----------------------
        
        // PROYECT 2
        dbTransactionClientClass level2dbTransaction = new dbTransactionClientClass( configuration, log );   
        
       
        //**  sdg_status
        level2dbTransaction.prepare(  

            " select distinct " +            
                
                " project.project_id ,  project.project_name,   sdg.sdg_status \n" +

            " from ashur_project project, ashur_clients client, ASHUR_SAMPLEDELIVERYGROUP sdg \n" +

            " where ( client.client_id = project.client_id )  AND  ( sdg.project_id = project.project_id ) " +

            " order by  project_name " 
                
            );   
      
        addLevel( 
            "PROJECT_ID", "PROJECT_NAME", level2dbTransaction,
            KTreeViewerClass.NODE_MODE_AUTO, null );        

        //-----------------------
          
        //** sdg_status , PROJECT_ID
        dbTransactionClientClass level3dbTransaction = new dbTransactionClientClass( configuration, log );                 
                
        //**
        level3dbTransaction.prepare( 
                
            " select " + 
                
                " sdg.sdg_id, sdg.sdg_description as SDG_LABEL, sdg_status, sdg.project_id  " +

            " from ASHUR_SAMPLEDELIVERYGROUP sdg  \n" +
                
                " where  sdg.project_id = ? and sdg_status = ? " +

            " order by sdg.sdg_description "

            );    

             
        addLevel( "SDG_ID", "SDG_LABEL", level3dbTransaction , 
            KTreeViewerClass.NODE_MODE_ONDEMAND, null );     // AL    
        
        //-----------------------
          
         //** sdg_status , PROJECT_ID
        dbTransactionClientClass level4dbTransaction = new dbTransactionClientClass( configuration, log ) ;                 
        
           
            
            level4dbTransaction.prepare( 
                    
                " select " + 
                    
                    " samp.sample_id, samp.SAMPLE_LAB_ID, " +
                    
                            " sdg_status , sdg.PROJECT_ID, sdg.sdg_id " +

                " from ashur_sample samp " +
                " left join ASHUR_SAMPLEDELIVERYGROUP sdg on samp.sdg_id = sdg.sdg_id  \n"  +
                    
                " where " +
                    " sdg.sdg_id = ? and sdg.PROJECT_ID = ? and sdg_status = ?  " +

                " order by samp.SAMPLE_LAB_ID " 
                    
                );                                
            
            
            
            
        addLevel( "SAMPLE_ID", "SAMPLE_LAB_ID", level4dbTransaction , 
            KTreeViewerClass.NODE_MODE_ONDEMAND, 
            new javax.swing.ImageIcon( configurationParam.getClass().getResource("/resources/bottles1.gif") ) 
                );

        
        //-----------------------        
          
        // TEST 5
        dbTransactionClientClass level5dbTransaction = 
		new dbTransactionClientClass( configuration, log ); 
        
                                   
            level5dbTransaction.prepare( 
                    
                " select " + 
                    
                    " link.link_id , link.analysis_name as LINK_LABEL, " +
                    
                            " sdg_status , sdg.PROJECT_ID, sdg.sdg_id, samp.sample_id " +

                " from  ASHUR_SAMPLE_ANALYSIS_LINK  link " +
                " left join ashur_sample samp on link.sample_id = samp.sample_id" +
                " left join ASHUR_SAMPLEDELIVERYGROUP sdg on samp.sdg_id = sdg.sdg_id  \n"  + 
                    
                " where " +
                                        
                    "   samp.sample_id = ?  and    sdg.sdg_id = ? and sdg.PROJECT_ID = ?  and sdg_status = ?  " +

                " order by link.analysis_description1 "                     
                    


                );   
            
        
        addLevel( "LINK_ID", "LINK_LABEL", level5dbTransaction , 
            KTreeViewerClass.NODE_MODE_ONDEMAND, 
            new javax.swing.ImageIcon( configurationParam.getClass().getResource("/resources/analysis1.gif") ) 
                );        
        
        

        //-----------------------        
        
        initialize();
    }
    
    @Override
    public void mouseRightClickPerformed( 
        String ID, int level, String label, 
        treeNodeClass selectedNode, JPopupMenu popupMenuParam,  java.awt.event.MouseEvent event    ){}
    
    @Override    
    public void mouseClickPerformed( 
        String ID, int level, String label, 
        treeNodeClass selectedNode  
        ){
        
        try{
            
            switch( level ){

                // root
                case -1:{

                    newActionButtonEnabled( false );
                    editActionButtonEnabled( false );  
                    deleteActionButtonEnabled( false );                    
                    
                    dataBrowser.clearDefaultCriteria();                
                    dataBrowser.setDefaultCriteria( defaultFilter );                
                    dataBrowser.setDefaultParameters( defaultParameters );                                    
                    dataBrowser.refresh();

                    break;
                }
                
                // status
                case 0:{

                    newActionButtonEnabled( false );
                    editActionButtonEnabled( false );  
                    deleteActionButtonEnabled( false );                                        
                    
                    dataBrowser.clearDefaultCriteria();                                 
                    String newDefaultFilter = " ( " + defaultFilter + " )\n and \n( sdg.sdg_status = ? ) ";                    
                    dataBrowser.setDefaultCriteria( newDefaultFilter );                                    
                    dataBrowser.setDefaultParameters( defaultParameters );                                    
                    dataBrowser.bindDefaultParameter1( ":status",  ID  );      
                    
                    /*
                    Vector sortList = new Vector();
                    sortList.add( " sdg.SDG_PRINTED_DATE " );                    
                    dataBrowser.setCustomOrder( sortList );
                    */
                    
                    dataBrowser.refresh();
                                        
                    break;
                }

                // project
                case 1:{

                    newActionButtonEnabled( false );
                    editActionButtonEnabled( false );  
                    deleteActionButtonEnabled( false );                                        
                    
                    dataBrowser.clearDefaultCriteria();                                 
                                                                       
                    String newDefaultFilter = " ( " + defaultFilter + " )\n and \n  ( sdg.project_id = ? and sdg.sdg_status = ? ) ";                    
                    dataBrowser.setDefaultCriteria( newDefaultFilter );                                                            
                          
                    dataBrowser.setDefaultParameters( defaultParameters );                                    
                    dataBrowser.bindDefaultParameter1( ":project_id", ID  );                                        
                    dataBrowser.bindDefaultParameter1( ":status_id", selectedNode.parent.ID  );                                        
                    dataBrowser.refresh();
                                        
                    break;
                }

                // sdg
                case 2:{

                    newActionButtonEnabled( true );
                    editActionButtonEnabled( true );  
                    deleteActionButtonEnabled( false );                                        
                    
                    dataBrowser.clearDefaultCriteria();      
                                            
                    String newDefaultFilter = " ( " + defaultFilter + " )\n and \n( sdg.sdg_id = ? ) ";                    
                    dataBrowser.setDefaultCriteria( newDefaultFilter );                                                        
 
                    dataBrowser.setDefaultParameters( defaultParameters );                                    
                    dataBrowser.bindDefaultParameter1( ":sdg", KMetaUtilsClass.getDecimalNumericValueFromString( ID ) );                                        
                    dataBrowser.refresh();
                                        
                    break;
                }

                // sample
                case 3:{

                    newActionButtonEnabled( true );
                    editActionButtonEnabled( true );  
                    deleteActionButtonEnabled( true );                                                            
                    
                    break;
                }
                
                // link
                case 4:{

                    newActionButtonEnabled( false );
                    editActionButtonEnabled( true );  
                    deleteActionButtonEnabled( true );                                                                                
                    break;
                }
                
                
            };
            
            statusListener.mouseClickPerformed( ID, level, label );
        
        } catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
        };   
        
    }
    

    //---------------------------------------------------------------
    //---------------------------------------------------------------
    //---------------------------------------------------------------    
    
    @Override
    public void newButtonActionPerformed(String ID, int level, String label, treeNodeClass selectedNode) {
    
        try{
        
            KMetaUtilsClass.cursorWait( parentWindow );

                switch( level ){

                // root
                case -1:{
                    break;
                }
                
                // status
                case 0:{                                        
                    break;
                }

                // projectstatus
                case 1:{                                        
                    break;
                }

                // sdg
                case 2:{
                    
                    
                    break;
                }

                // sample
                case 3:{

                    // ---------------------------------

/*                    
                    SALSelectAnalysisDialogClass SALSelectAnalysisDialog =
                                new SALSelectAnalysisDialogClass (
                                    configuration, log ,
                                    systemResources,                
                                    metaUtilsClass.getParentFrame( component ) );

                    long analysis_id = SALSelectAnalysisDialog.showDialog( );
                    if( analysis_id == -1 ) return;


                    // if we have sample

                    // add it
                    persistentObjectManagerClass persistentObjectManager =
                        new persistentObjectManagerClass( configuration, log ); 
                    sample_analysis_linkClass link = new sample_analysis_linkClass();        
                    persistentObjectManager.createNew( link, Long.parseLong( ID ), analysis_id );
                    persistentObjectManager.push_back( link );                                   
*/
                    
//                    long sample_id = Long.parseLong( ID );
//                    
//                    // if we are inside a sample view
//                    // ie, we have a sample ID
//                    
//                    SALSelectAnalysisDialogClass SALSelectAnalysisDialog =
//                                new SALSelectAnalysisDialogClass (
//                                    configuration, log ,
//                                    systemResources,                
//                                    metaUtilsClass.getParentFrame( component ) );
//
//                    Vector analysis_id = SALSelectAnalysisDialog.showSelectMultipleDialog( );
//                    if( analysis_id.size() == 0 ) return;
//
//                    // ------------------
//                              
//                    persistentObjectManagerClass persistentObjectManager =
//                        new persistentObjectManagerClass( configuration, log ); 
//                    
//                    Iterator analysis_idIterator = analysis_id.iterator();
//                    while( analysis_idIterator.hasNext() ){
//                    
//                        // add it
//                        sample_analysis_linkClass link = new sample_analysis_linkClass();        
//                        persistentObjectManager.createNew( 
//                            link, sample_id, ( (Long) analysis_idIterator.next() ).longValue()
//                            );
//                        persistentObjectManager.push_back( link );                                   
//                    }
//                    
//                    
//                    log.log( this, "New sample link." );                                  
//
//                        
//                    // refresh tree and table                        
//                    dataBrowser.refresh();                        
                    break;

                    // ---------------------------------                    
                }

                // link
                case 4:{
                    
                    break;
                }

                
                
            };
        
            KMetaUtilsClass.cursorNormal( parentWindow );            
            
        } catch( Exception error ){
            
                KMetaUtilsClass.cursorNormal( parentWindow );            

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
        };   
                
    }

    
    //---------------------------------------------------------------

    
    @Override
    public void editButtonActionPerformed(String ID, int level, String label, treeNodeClass selectedNode) {
    
        try{
        
            KMetaUtilsClass.cursorWait( parentWindow );           
            
            switch( level ){

                // root
                case -1:{
                    break;
                }
                
                // status
                case 0:{                                        
                    break;
                }

                // projectstatus
                case 1:{                                        
                    break;
                }

                // sdg
                case 2:{

//                    log.log( this, "Openning edit sdg screen..." );                      
//
//                        SDGEditDialogClass SDGEditDialog =
//                                new SDGEditDialogClass(
//                                        configuration, log ,
//                                        systemResources,
//                                        metaUtilsClass.getParentFrame( component ),
//                                        Long.parseLong( ID ), SDGEditDialogClass.EDIT_MODE,
//                                        ProgressBar );     
//
//                    SDGEditDialog.setTitle("Edit Sample Delivery Group");			
//                    SDGEditDialog.setVisible( true );
//
//                    log.log( this, "Openning edit sdg screen completed." );  			
//
//                    dataBrowser.refresh();
//                    
                    break;
                }

                // sample
                case 3:{

//                    log.log( this, "Openning edit sample screen..." );          
//
//                    sampleEditDialogClass sampleEditDialog =
//                            new sampleEditDialogClass( 
//                                    configuration, log,
//                                    systemResources,
//                                    metaUtilsClass.getParentFrame( component ), Long.parseLong( ID ), 
//                                    sampleEditDialogClass.EDIT_MODE );
//
//                    // width, height, position
//                    sampleEditDialog.setTitle("Edit Sample");
//                    sampleEditDialog.setVisible( true );
//
//                    log.log( this, "Openning edit sample screen completed." );                                  
//                    
//                    dataBrowser.refresh();                    
                    
                    break;
                }
                
                // link
                case 4:{                                        
                    
//                    sample_analysis_linkClass link = 
//                            new sample_analysis_linkClass();
//                    persistentObjectManagerClass persistentObjectManager = 
//                            new persistentObjectManagerClass( configuration, log ); 
//                    persistentObjectManager.copy( Long.parseLong( ID ), link );
//
//                    // materialize sample
//                    log.log( this, "materialize sample ." );
//
//                    sampleClass sample = new sampleClass();
//                    persistentObjectManager.copy( link.sample_id, sample );
//
//                    log.log( this, "Openning edit sample analysis link screen..." );  
//
//                    sampleAnalysisLinkEditDialogClass linkEditDialog =
//                                new sampleAnalysisLinkEditDialogClass( 
//                                        configuration, log ,
//                                        systemResources,
//                                        metaUtilsClass.getParentFrame( component ), 
//                                        sample.sdg_id, Long.parseLong( ID ), -1, 
//                                        sampleAnalysisLinkEditDialogClass.EDIT_MODE );
//
//                    // in batch view sample con not changed
//                    linkEditDialog.disableSampleComboBox();
//
//                    linkEditDialog.setTitle("Edit Sample Analysis Link");				
//                    linkEditDialog.setVisible( true );
//                    
//                    dataBrowser.refresh();                                        
                    
                    break;
                }
                
                
            };
            
            KMetaUtilsClass.cursorNormal( parentWindow );            
        
        } catch( Exception error ){
            
                KMetaUtilsClass.cursorNormal( parentWindow );               

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
        };   
        
    }

    
    //---------------------------------------------------------------
    
    @Override
    public void deleteButtonActionPerformed(String ID, int level, String label, treeNodeClass selectedNode) {
    
        try{
        
            KMetaUtilsClass.cursorWait( parentWindow );            
            
            switch( level ){

                // root
                case -1:{
                    break;
                }
                
                // status
                case 0:{                                        
                    break;
                }

                // projectstatus
                case 1:{                                        
                    break;
                }

                // sdg
                case 2:{
                    break;
                }

                // sample
                case 3:{

//                    if( metaUtilsClass.showConfirmationMessage( component, "Please confirm: try to delete sample." ).equals( " OK " ) ){
//
//                        persistentObjectManagerClass persistentObject = 
//                            new persistentObjectManagerClass( configuration, log ); 
//
//                        persistentObject.delete( Long.parseLong( ID ), sampleClass.className );
//
//                    }
//                    
//                    dataBrowser.refresh();                                            
                    break;
                }
                
                // link
                case 4:{
                    

//                    if( metaUtilsClass.showConfirmationMessage( component, "Are you sure you want to delete this analysis?" ).equals( " OK " ) ){
//
//                        persistentObjectManagerClass persistentObject = 
//                            new persistentObjectManagerClass( configuration, log ); 
//
//                        persistentObject.delete( Long.parseLong( ID ), sample_analysis_linkClass.className );
//                    }
//                    
//                    dataBrowser.refresh();                                                                    
                    break;
                }
                
                
            };
        
            KMetaUtilsClass.cursorNormal( parentWindow );                           
            
        } catch( Exception error ){

                KMetaUtilsClass.cursorNormal( parentWindow );                           
            
                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
            };          
    }
    
    //---------------------------------------------------------------    
    
}



//            if( configuration.getField( "databaseType" ).equals( "oracle" ) ){                    
//                
//            }else if(configuration.getField( "databaseType" ).equals( "mssql" ) ){      
//                
//            }else{
//                
//                throw new KExceptionClass( "databaseType not recognized " + configuration.getField( "databaseType" ), null );
//            }