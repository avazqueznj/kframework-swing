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

package Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo;

//rtl
import Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util.fieldSelectorInterface;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;

//K
import KFramework30.Base.*;
import KFramework30.Communication.*;
import KFramework30.Widgets.TreeView.*;
import Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util.fieldSelectionChangedInterface;


public class LimsDataNavigatorTreeSelectorClass 
extends KTreeViewerClass
implements fieldSelectorInterface
{

    
    // uses        
    private KTreeViewerListenerInterface    treeViewerListener;    
    private fieldSelectionChangedInterface  fieldSelectionListener;
    private JTree                           tree;
    private java.awt.Window                 parentWindow;
    
    // has - defaulted
    public java.util.List< treeNodeClass > selectedFields;
    
    
    // inner classes
    
            //----------------------------------------------                        
            
            public class fieldSelectorPopupMenuClass
            extends JPopupMenu
            implements ActionListener            
            {
                
                // uses
                treeNodeClass node;
                
                //has - defaulted
                JMenuItem addMenuItem;
                JMenuItem removeMenuItem;
                               
                
                fieldSelectorPopupMenuClass( 
                   treeNodeClass nodeParam
                    )
                throws KExceptionClass{           
                    
                    super( "Add/Remove variable" );
                    
                    // uses
                    node =  nodeParam;
                    
                    // has - defaulted
                    addMenuItem = new JMenuItem( "Add", new javax.swing.ImageIcon(getClass().getResource("/resources/add1.jpg"))  );
                    addMenuItem.setFont( new Font( "arial", Font.PLAIN, 10 ) );
                    addMenuItem.setActionCommand( "ADD" );
                    addMenuItem.addActionListener( this );
                    add( addMenuItem );                    
                                        
                    removeMenuItem = new JMenuItem( "Remove", new javax.swing.ImageIcon(getClass().getResource("/resources/remove1.jpg")) );                                                            
                    removeMenuItem.setFont( new Font( "arial", Font.PLAIN, 10 ) );                                        
                    removeMenuItem.setActionCommand( "REMOVE" );                    
                    removeMenuItem.addActionListener( this );                    
                    add( removeMenuItem );                    
                    
                    pack();
                }
                
                
                public void actionPerformed( java.awt.event.ActionEvent selectorPopMenuEvent ) {
                    
                                               
                    try{

                        String command = selectorPopMenuEvent.getActionCommand();
                        
                        if( command.equals( "ADD" ) ){
                            
                            selectedFields.add( node );
                            visualTree.repaint();                            
                            fieldSelectionListener.fieldSelectionChanged( selectedFields );
                        }

                        if( command.equals( "REMOVE" ) ){
                            
                            selectedFields.remove( node );                                                        
                            visualTree.repaint();                            
                            fieldSelectionListener.fieldSelectionChanged( selectedFields );                            
                        }
                        
                    }
                    catch( Exception error ){
                        
                            // log error
                            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                            // show error message
                            KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
                    };               
                        
                        
                }// end function
                
            } // end class
            
    
    // --------------------------------------------------------
    // Interface
                
    @Override
    public java.util.List< treeNodeClass > getSelectedFields() { return( selectedFields ); }    
    
    // --------------------------------------------------------

    
    /** Creates new applicationTreeViewClass */
    public LimsDataNavigatorTreeSelectorClass(
        KConfigurationClass configurationParam, KLogClass logParam,
        java.awt.Window parentWindowParam, JTree treeParam,
        KTreeViewerListenerInterface treeViewerListenerParam,       
        fieldSelectionChangedInterface fieldSelectionListenerParam) throws KExceptionClass
    {
        
        // inherits
        super( 
            configurationParam, logParam, 
            parentWindowParam, treeParam, 
            new javax.swing.ImageIcon( configurationParam.getClass().getResource("/resources/root.gif") ),
            "" );                    
                      
        // uses
        treeViewerListener = treeViewerListenerParam;  
        fieldSelectionListener = fieldSelectionListenerParam;
        tree = treeParam;
        parentWindow = parentWindowParam;
                
        // has - defaulted
        
        selectedFields = new ArrayList< treeNodeClass >();
        
        //-----------------------        
        // CLIENT_ID
                
        {
            
            dbTransactionClientClass level0 =  new dbTransactionClientClass( configuration, log );                 
           
            level0.prepare(

                " select distinct " +

                    " CLIENT_ID, upper( CLIENT_NAME ) as CLIENT_NAME, CLIENT_ID "  +

                " from PLANTREPORT_LIMS_IMPORT " +

                " order by upper( CLIENT_NAME ) "

                );        
                    

            addLevel(
                // PK name, node label, trx, load mode, icon
                "CLIENT_ID", "CLIENT_NAME", level0 , 
                KTreeViewerClass.NODE_MODE_AUTO, 
                null 
                );
        }
                
        //-----------------------  
        // SAMPLE_CLIENT_ID_PK

        {
            
            dbTransactionClientClass level1 = 
                    new dbTransactionClientClass( configuration, log );                 

        
                
            level1.prepare(

                " select distinct " +

                    " SAMPLE_CLIENT_ID, SAMPLE_CLIENT_ID as LABEL, CLIENT_ID " +                

                " from PLANTREPORT_LIMS_IMPORT " +

                " order by SAMPLE_CLIENT_ID "

                );        

            addLevel(
                // PK name, node label, trx, load mode, icon
                "SAMPLE_CLIENT_ID", "LABEL", level1 , 
                KTreeViewerClass.NODE_MODE_AUTO, 
                null 
                );
                
            
        }

        //-----------------------  
        // ANALYSIS_NAME_PK
        
        {
            
            dbTransactionClientClass level3 = 
                    new dbTransactionClientClass( configuration, log );                 

           

            level3.prepare(

                 // FK -> PK -> LABEL
                " select distinct " +

                    // pk             label                    fk         fk                 useful
                    " ANALYSIS_NAME, ANALYSIS_NAME as LABEL , CLIENT_ID, SAMPLE_CLIENT_ID , client_name " +                


                " from PLANTREPORT_LIMS_IMPORT " +

                " where SAMPLE_CLIENT_ID = ? and CLIENT_ID = ?  " +

                " order by ANALYSIS_NAME "

                );        
                    
          
            addLevel(
                // PK name, node label, trx, load mode, icon
                "ANALYSIS_NAME", "LABEL", level3 , 
                KTreeViewerClass.NODE_MODE_ONDEMAND, 
                null  
                );
            
        }

                  
        //-----------------------          
        
        initialize();
               
        setLevelPopMenu( -1, null );               
        setLevelPopMenu( 0, null );        
        setLevelPopMenu( 1, null );        
        setLevelPopMenu( 2, null );        
                
        // override default select mode
        treeParam.getSelectionModel().setSelectionMode( TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION );        
        
    }    
    
    //---------------------------------------------------------------------------
    // for user to overrride
       
    
    public void mouseClickPerformed( String ID, int level, String label, treeNodeClass selectedNode ){}
    
    public void mouseRightClickPerformed( 
        String ID, int level, String label, 
        treeNodeClass clickedNode, JPopupMenu popupMenuParam, 
        java.awt.event.MouseEvent event        
        ){

            try{
            

                if( level == 2 ){


                    // prepare menu
                    fieldSelectorPopupMenuClass popupMenu 
                        = new fieldSelectorPopupMenuClass( clickedNode );
                    

                    popupMenu.removeMenuItem.setEnabled( false );
                    popupMenu.addMenuItem.setEnabled( true );
                    Iterator< treeNodeClass > selectedNodeIterator = selectedFields.iterator();
                    while( selectedNodeIterator.hasNext() ){          
                        
                        if( selectedNodeIterator.next() == clickedNode ){
                            popupMenu.removeMenuItem.setEnabled( true );
                            popupMenu.addMenuItem.setEnabled( false );
                            break;
                        }                        
                    }
                                                                
                    // display it                   
                    popupMenu.show( event.getComponent (), event.getX (), event.getY () );                    

                };
  
 
            } catch( Exception error ){
                
                    // log error
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    // show error message
                    KMetaUtilsClass.showErrorMessageFromException( parentWindow, error );
            };   
            
    }        

    public void newButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode ){}

    public void editButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode ){}

    public void deleteButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode ){}


        
    //---------------------------------------------------------------    

    
    
    @Override
    public Component getNodeRenderer(
        String ID, int level, String label, treeNodeClass selectedNode, defaultNodeRendererClass defaultNodeRenderer) {

        try{
            
            if( level ==2 ){
                                           
                ((DefaultTreeCellRenderer) defaultNodeRenderer).setIcon(  new javax.swing.ImageIcon(getClass().getResource("/resources/bottles1_off.gif")) );     
                Iterator< treeNodeClass > selectedNodeIterator = selectedFields.iterator();
                while( selectedNodeIterator.hasNext() ){          
                    if( selectedNodeIterator.next() == selectedNode ){
                       ((DefaultTreeCellRenderer) defaultNodeRenderer).setIcon( new javax.swing.ImageIcon(getClass().getResource("/resources/bottles1_on.gif")) ); 
                        break;
                    }                        
                }                
                
            };
            
        } catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException( parentWindow,  error );
        };   
            
                    
        return( defaultNodeRenderer );
    }

    
    public void addManyFields() {
        
        // get selected nodes
        TreePath[] selection = tree.getSelectionPaths();
        
        if( selection == null  ) return;
        
        for( int index = 0; index < selection.length; index++ ){
            
            treeNodeClass selectedNode = (treeNodeClass) ( selection[index].getLastPathComponent() );
       
            if( selectedNode.levelIndex == 2 )   // make sure leaf node         
                selectedFields.add( selectedNode );                         
        }
                    
        
        // add
        visualTree.repaint();                            
        fieldSelectionListener.fieldSelectionChanged( selectedFields );
        
    }    
           
        
    public void removeManyFields() {
        
        // get selected nodes
        TreePath[] selection = tree.getSelectionPaths();
        for( int index = 0; index < selection.length; index++ ){
            
            treeNodeClass selectedNode = (treeNodeClass) ( selection[index].getLastPathComponent() );
       
            if( selectedNode.levelIndex == 2 )   // make sure leaf node         
                selectedFields.remove( selectedNode );
        }
                    
        
        // add
        visualTree.repaint();                            
        fieldSelectionListener.fieldSelectionChanged( selectedFields );

        
    }
    
    
    public void clearFieldSelection() {                              
        
        selectedFields = new ArrayList< treeNodeClass >();
        visualTree.repaint();                            
        fieldSelectionListener.fieldSelectionChanged( selectedFields );
        
    }
    
    
    //---------------------------------------------------------------
}




