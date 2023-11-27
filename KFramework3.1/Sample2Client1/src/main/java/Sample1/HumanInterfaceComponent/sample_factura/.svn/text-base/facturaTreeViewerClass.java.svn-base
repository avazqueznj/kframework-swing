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

package Sample1.HumanInterfaceComponent.sample_factura;

//rtl
import KFramework30.Widgets.TreeView.*;
import KFramework30.*;
import KFramework30.Base.*;
import KFramework30.Communication.*;
import Sample1.HumanInterfaceComponent.sample_factura.facturaBrowserClass;
import java.awt.Component;
import java.awt.Cursor;

import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.tree.*;

public class facturaTreeViewerClass 
extends KTreeViewerClass
{
    
    // uses    

    private JTree                       tree;
    private facturaBrowserClass         browser;
    
    // has - defaulted
    
    // --------------------------------------------------------          
    /** Creates new applicationTreeViewClass */
    public facturaTreeViewerClass(
        KConfigurationClass configurationParam,KLogClass logParam,
        java.awt.Window parentWindowParam, JTree treeParam,
        ImageIcon rootIcon,
        facturaBrowserClass browserParam )throws KExceptionClass
    {                
        // inherits
        super( configurationParam, logParam,  parentWindowParam, treeParam, rootIcon, "All Invoices" );                    
                      
        // uses
        tree = treeParam;
        browser = browserParam;
        
        // has - defaulted
        
        //--------------------------------------------------------------
        
        {
        dbTransactionClientClass factStatusLevel = new dbTransactionClientClass( configuration, log );                         
        
        factStatusLevel.prepare( 
             //                     V--  PK         V-- label
            " select distinct fac.FACSTATUS_ID, facstatus.FACSTATUS_STATUS " +
            " from SAMPLE_FACTURA fac" +
            " join SAMPLE_FACTURA_STATUS facstatus on fac.FACSTATUS_ID = facstatus.FACSTATUS_ID " +
            " order by facstatus.FACSTATUS_STATUS "    
            );                        
        
        // bind here if necessary
        // factStatusLevel.bind( xx , xx )  
                
        addLevel(
            // PK name, node label, trx, load mode, icon
            "FACSTATUS_ID", "FACSTATUS_STATUS", factStatusLevel , 
            KTreeViewerClass.NODE_MODE_AUTO, //-- load all data on display
            null // new ImageIcon( systemResources.getImage( "application.jpg" ) ) 
            );
        }
              
        //--------------------------------------------------------------
        
        {
        dbTransactionClientClass cliStatusLevel = new dbTransactionClientClass( configuration, log );                         
        
        cliStatusLevel.prepare(  
             //                     V--  PK         V-- label    V-- FK to parent above                
            " select distinct cli.client_id, cli.client_name, fac.FACSTATUS_ID " +
            " from SAMPLE_CLIENT cli " +
            " left join SAMPLE_FACTURA fac on fac.client_id = cli.client_id " +
            " order by cli.client_name " 
            );                        
        
        // bind here if necessary
        // factStatusLevel.bind( xx , xx )  
                
        addLevel(
            // PK name, node label, trx, load mode, icon
            "CLIENT_ID", "CLIENT_NAME", cliStatusLevel , 
            KTreeViewerClass.NODE_MODE_AUTO, //-- load all data on display
            null // new ImageIcon( systemResources.getImage( "application.jpg" ) ) 
            );
        }        
        
        //--------------------------------------------------------------
        
        {
        dbTransactionClientClass factLevel = new dbTransactionClientClass( configuration, log );                         
        
        factLevel.prepare(  
             //               V--  PK V-- label  V-- FK to parent1 V-- FK to parent 2
            " select distinct FAC_ID, FAC_NAME, CLIENT_ID, FACSTATUS_ID " +
            " from SAMPLE_FACTURA  " +
                
            // on demand, then ad where with FKs, they will be bound automatically ---
            " where CLIENT_ID = ? and FACSTATUS_ID = ?"  // <- ONLY REQUIRED IF ONDEMAND - binding automatic
            );   
        
        // bind here if necessary
        // factStatusLevel.bind( xx , xx )          
        
        addLevel(
            // PK name, node label, trx, load mode, icon
            "FAC_ID", "FAC_NAME", factLevel , 
            KTreeViewerClass.NODE_MODE_ONDEMAND, //-- DEFER LOAD UNTIL CLICK
            null // new ImageIcon( systemResources.getImage( "application.jpg" ) ) 
            );
        }
                                       
        //--------------------------------------------------------------
        
        initialize();
                
        setLevelPopMenu( -1, null );   // root level
        setLevelPopMenu(  0, null ); // ok new, no edit, nop delete
        setLevelPopMenu(  1, new KtreeNodePopupMenuClass( true, false, false ) ); // ok new, no edit, nop delete
        setLevelPopMenu(  2, new KtreeNodePopupMenuClass( false, true, true ) ); // no new, ok edit, ok delete
                                              
        // set select mode
        treeParam.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );   
        
        
    }    
    
    //---------------------------------------------------------------------------
    // for user to overrride
      
    @Override
    public Component getNodeRenderer( 
            String ID, int level, String label, treeNodeClass selectedNode, 
            defaultNodeRendererClass defaultNodeRenderer  ){         
        
        if( level == 2 ){ // if we are in factura level
            
           if( selectedNode.parent.parent.label.equals( "OUTSTANDING" ) ){
               defaultNodeRenderer.
                       setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/new1.jpg")));
           }else
           if( selectedNode.parent.parent.label.equals( "VOID" ) ){
                defaultNodeRenderer.
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/delete1.jpg")));               
           }
           else
           // otherwise
           defaultNodeRenderer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit1.jpg")));               
        }
        
        return( defaultNodeRenderer );
    }
    
    
    
    
    public void mouseClickPerformed( String ID, int level, String label, treeNodeClass selectedNode ){
   
        
        try{
            
            
            
            if( level == -1 ){ //root is selected...
                browser.clearDefaultCriteria();                 
                browser.refresh();
            }
                        
            if( level == 0 ) { // status level
                browser.clearDefaultCriteria();                 
                browser.setDefaultCriteria( " status.facstatus_id = ? " ); 
                browser.bindDefaultParameter1( "1", KMetaUtilsClass.getDecimalNumericValueFromString( selectedNode.ID ) );
                browser.refresh();
            }
        
            if( level == 1 ) { // status level
                browser.clearDefaultCriteria();                 
                browser.setDefaultCriteria( " status.facstatus_id = ? and fac.client_id = ? " ); 
                browser.bindDefaultParameter1( "2", KMetaUtilsClass.getDecimalNumericValueFromString( selectedNode.parent.ID ) );                                
                browser.bindDefaultParameter1( "1", KMetaUtilsClass.getDecimalNumericValueFromString( selectedNode.ID ) );
                browser.refresh();
            }

            if( level == 2 ) { // status level
                browser.clearDefaultCriteria();                 
                browser.setDefaultCriteria( " status.facstatus_id = ? and fac.client_id = ? and fac_id = ?" ); 
                browser.bindDefaultParameter1( "3", KMetaUtilsClass.getDecimalNumericValueFromString( selectedNode.parent.parent.ID ) );                                                
                browser.bindDefaultParameter1( "2", KMetaUtilsClass.getDecimalNumericValueFromString( selectedNode.parent.ID ) );                                
                browser.bindDefaultParameter1( "1", KMetaUtilsClass.getDecimalNumericValueFromString( selectedNode.ID ) );
                browser.refresh();
            }
            
            
        }
        catch( Exception error	){
            
              
                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
        }        
        
        
    }    

    @Override
    public void newButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode ){
    
        browser.newButtonActionPerformed();
    }
    @Override
    public void editButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode ){
        try {            
            browser.editButtonActionPerformed( KMetaUtilsClass.getIntegralNumericValueFromString( ID ) );            
        } catch (KExceptionClass error) {            
            log.log( this,  error.toString() );
            KMetaUtilsClass.showErrorMessageFromException(parentWindow, error);
        }
    }
    @Override
    public void deleteButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode ){
        
        try {            
            browser.deleteButtonActionPerformed( KMetaUtilsClass.getIntegralNumericValueFromString( ID ) );            
        } catch (KExceptionClass error) {            
            log.log( this,  error.toString() );
            KMetaUtilsClass.showErrorMessageFromException(parentWindow, error);
        }

    }
    @Override
    public void mouseRightClickPerformed(String ID, int level, String label, treeNodeClass selectedNode, 
            JPopupMenu popMenu, MouseEvent event) { }
        
  
    //---------------------------------------------------------------
}




