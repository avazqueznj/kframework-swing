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

package KFramework30.Widgets.TreeView;

//rtl
import KFramework30.Communication.dbTransactionClientClass;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;


// utilities
import KFramework30.Base.*;
import KFramework30.Widgets.*;

// system <- not here this is utils

public abstract class KTreeViewerClass
implements TreeSelectionListener, ActionListener, MouseListener, TreeCellRenderer
{
    
    // constants
    public static final int             NODE_MODE_AUTO = 0;
    public static final int             NODE_MODE_ONDEMAND = 1;                

    // uses
    protected KConfigurationClass       configuration;    
    protected KLogClass                 log;   
    protected java.awt.Window           parentWindow;        //for displaying error message    
    protected JTree                     visualTree;    //The visual table maps to a DB table.
    protected Icon                      rootIcon;
    protected String                    rootLabel;
    
    // has
    private   java.util.List            levelsList; // config
    private   treeNodeClass             root; // root nodes
    private   DefaultTreeModel          treeModel; 
    private   defaultNodeRendererClass  defaultNodeRenderer;    
    private   JPopupMenu                rootPopupMenu;          
    
    // inner
    
            //----------------------------------------------       
    
            class levelClass{   
                
                // uses
                public String                                           OIDfieldName;   
                public String                                           labelFieldName;
                public dbTransactionClientClass                         dbTransaction;
                public int                                              nodeMode;                
                public Icon                                             icon;                              
                public JPopupMenu                                       popupMenu;
                
                // has
                public java.util.List   levelData;
                public boolean          isOIDnumeric;                    
                
                
                // has
                // interface
                levelClass(
                    String OIDfieldNameParam, String labelParam, 
                    dbTransactionClientClass dbTransactionParam,
                    int nodeModeParam,
                    Icon    IconParam, 
                    JPopupMenu popupMenuParam
                ){
                    // uses
                    OIDfieldName = OIDfieldNameParam;
                    labelFieldName = labelParam;
                    dbTransaction = dbTransactionParam;                    
                    nodeMode = nodeModeParam;
                    icon = IconParam;
                    popupMenu = popupMenuParam;
                                        
                    // has
                    levelData = new ArrayList();
                    
                    // misc
                }
                
                //--------------------------------------
                
                public void releaseLevelData(){ levelData = new ArrayList(); }
                
                //--------------------------------------                
            }
    
   
            //----------------------------------------------            
            
            
            public class treeNodeClass
            implements TreeNode
            {
                // constants
                private static final int STATUS_CHILDREN_MATERIALIZED        = 0;
                private static final int STATUS_CHILDREN_NOT_MATERIALIZED    = 1;                
                                
                // uses
                public String           ID;
                public boolean          isIDnumeric;
                public String           IDname;
                public String           label;
                public int              levelIndex;                                   
                private int             nodeMode;
                public treeNodeClass    parent;
                public AbstractMap      rawData;                
                
                // has
                public java.util.List   children;
                public int positionIndex = -1;
                public int status;             
                
                /** Constructor ----------------------------- */
                treeNodeClass( 
                    String IDParam,                    
                    String IDNameParam, String labelParam, 
                    int levelIndexParam, int nodeModeParam, int positionIndexParam, 
                    treeNodeClass parentParam, 
                    AbstractMap rawDataParam
                    ){
                    
                    // uses
                    ID = IDParam;                   
                    IDname = IDNameParam;
                    label = labelParam;
                    levelIndex = levelIndexParam;
                    nodeMode = nodeModeParam;
                    positionIndex = positionIndexParam;
                    parent = parentParam;
                    
                    rawData = new HashMap();
                    rawData.putAll( rawDataParam );
                    
                    // has
                    children = new ArrayList();
                    status = STATUS_CHILDREN_NOT_MATERIALIZED;
                    
                    // misc
                }
                
                //----------------------------------------
                // Public interface
                
                
                public String toString(){ return( label ); }                
                                
                public void materializeChildrenByNode()
                throws KExceptionClass
                {
                    
                    // if last level then were done
                    if( levelIndex + 1 == levelsList.size() ) return;
                    
                    // get child level 
                    levelClass childLevel = ( levelClass ) levelsList.get( levelIndex + 1 );                                             
                    if( childLevel.nodeMode == NODE_MODE_ONDEMAND ) return;
                    
                    // in case of failure java will retry for ever
                    // so mark it success always..
                    status = STATUS_CHILDREN_MATERIALIZED;                           
                                        
                    // find data in level for this specific node                 
                    Iterator childLevelData = childLevel.levelData.iterator();
                    int childCount = 0;
                    while( childLevelData.hasNext() ){

                        HashMap currentChildNodeData = ( HashMap ) childLevelData.next();                                                

                        // if not root check this is our child
                        if( levelIndex != -1 ){
                            
                            // is current child form this parent? scan for all hiererchy ids
                            treeNodeClass parentNode = this;
                            boolean match = true;
                            while( true ){                                                                                            
                                if( !( (String) currentChildNodeData.get( parentNode.IDname ) ).equals( parentNode.ID ) ) match = false;                                
                                parentNode = parentNode.parent;                                
                                if ( parentNode.levelIndex == -1 ) break;
                            }
                            
                            if( !match ) continue;                            
                        };
                        
                        // add leaf to branch
                        
                        // get node data ( current data row ) - ( level config )
                        // checked at buffer build for perf
                        String nodeID = ( String ) currentChildNodeData.get( childLevel.OIDfieldName );                                                
                        String nodeLabel = ( String ) currentChildNodeData.get( childLevel.labelFieldName );
                        
                        String indent = "_________________________________________________";
                        
                        log.log( 
                            this, 
                            indent.substring( 0, ( levelIndex + 1 ) * 2 ) + "Building child node [" + nodeID + "][" + nodeLabel + "] " + 
                            "for parent [" + String.valueOf( ID ) + "][" + label + "]"
                            );                    

                        // create node
                        treeNodeClass childNode = 
                            new treeNodeClass( 
                                nodeID, childLevel.OIDfieldName , nodeLabel, levelIndex + 1, 
                                    childLevel.nodeMode, childCount, this, currentChildNodeData );
                        
                        childCount++;

                        // call get children, pass level id, parent 
                        // let him access levels
                        childNode.materializeChildrenByNode();
                        
                        // save child
                        children.add( childNode );
                                                
                    } // while children are loaded
                    
                    // done.
                    status = STATUS_CHILDREN_MATERIALIZED;       
                    
                } // materialize branch

                
                public void materializeChildrenOnDemandByNode()
                throws KExceptionClass
                {
                    // first thing, so that it will not be reentered on error
                    status = STATUS_CHILDREN_MATERIALIZED;
                    
                    // if last level then were done
                    if( levelIndex + 1 == levelsList.size() ) return;
                    
                    // get child level config object
                    levelClass childLevel = ( levelClass ) levelsList.get( levelIndex + 1 );        
                    
                    // reuse query
                    childLevel.dbTransaction.reusePrevious();
                    
                    // bind our ID to dbTransaction
                    treeNodeClass parentNode  = this;
                    while( true ){
                        
                        if( (( levelClass )levelsList.get( parentNode.levelIndex )).isOIDnumeric ){
                            
                            childLevel.dbTransaction.bind( ":parent_id", KMetaUtilsClass.getDecimalNumericValueFromString(parentNode.ID) );
                            
                        }else                        
                            childLevel.dbTransaction.bind( ":parent_id", parentNode.ID );
                        
                        parentNode = parentNode.parent;
                        if ( parentNode.levelIndex == -1 ) break;
                    }
                    
                    try{
                        // execute         
                        KMetaUtilsClass.cursorWait( parentWindow );
                        childLevel.dbTransaction.executeQuery( 0, 65536 );                    


                        // find each raw data row                    
                        int childCount = 0;
                        while( childLevel.dbTransaction.fetch() ){

                            // get node data ( current data row ) - ( level config )
                            // checked at buffer build for perf
                            String nodeID = (String) childLevel.dbTransaction.getField( childLevel.OIDfieldName );   

                            childLevel.isOIDnumeric = childLevel.dbTransaction.isColumnNumeric( childLevel.OIDfieldName );   

                            String nodeLabel = (String) childLevel.dbTransaction.getField( childLevel.labelFieldName );

                            String indent = "_________________________________________________";

                            log.log( 
                                this, 
                                indent.substring( 0, ( levelIndex + 1 ) * 2 ) + "Building child node [" + nodeID + "][" + nodeLabel + "] " + 
                                "for parent [" + ID + "][" + label + "]"
                                );                    

                            // create node
                            treeNodeClass childNode = 
                                new treeNodeClass( 
                                    nodeID, childLevel.OIDfieldName , nodeLabel, levelIndex + 1, 
                                        childLevel.nodeMode, childCount, this, childLevel.dbTransaction );

                            childCount++;

                            // call get children, pass level id, parent 
                            // let him access levels
                            childNode.materializeChildrenByNode();

                            // save child
                            children.add( childNode );                                                
                        };
                        
                    }finally{
                        KMetaUtilsClass.cursorNormal( parentWindow );                        
                    }                        
                }
                
                
                //----------------------------------------------------
                // TREE NODE INTERFACE
                
                public int getIndex(javax.swing.tree.TreeNode node ){
                    return( ( ( treeNodeClass ) node ).positionIndex );
                }
                
                public boolean getAllowsChildren(){
                    return( true );
                }
                                
                public boolean isLeaf(){                    
                    if( levelIndex + 1 == levelsList.size() ) return( true );                    
                    return( false );
                }
                
                public javax.swing.tree.TreeNode getParent(){
                    return( parent );
                }

                // on demand materialization needed ->
                
                // no need materialization, it already knows the index
                public javax.swing.tree.TreeNode getChildAt( int childIndex ){
                    return( ( treeNodeClass ) children.get( childIndex ) );
                }
                
                // materialize here
                public int getChildCount() {   
                    
                    int result = 0;
                    
                    try{
                        
                        if( status == STATUS_CHILDREN_NOT_MATERIALIZED ) materializeChildrenOnDemandByNode();
                        result =  children.size();
                        
                    } catch( Exception error ){
                        
                            // log error
                            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                            // show error message
                            KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );
                    };   
                    
                    return( result );
                }
                
                // I think this is and obsolete api.... (ENUMS) are deprecated
                public java.util.Enumeration children() {
                    return( null );
                }
                
                // TREE NODE INTERFACE
                //----------------------------------------------------               
                
            }
            
            
            //----------------------------------------------            

            public class defaultNodeRendererClass 
            extends DefaultTreeCellRenderer{
                
                public defaultNodeRendererClass(){}

                public Component getTreeCellRendererComponent(
                    JTree tree, Object node, boolean selected, boolean expanded,
                    boolean leaf, int row, boolean hasFocus) {

                    try{
                        
                        super.getTreeCellRendererComponent(
                            tree, node, selected, expanded, leaf, row, hasFocus);

                        // get node
                        treeNodeClass currentNode = ( treeNodeClass ) node;
                                                
                        // locate level
                        if( currentNode.levelIndex > -1 ){
                            // set default level icon
                            levelClass nodeLevel = ( levelClass ) levelsList.get( currentNode.levelIndex );
                            if( nodeLevel.icon != null ) setIcon( nodeLevel.icon );
                        };

                        // if root, set root icon and label
                        if( currentNode.levelIndex == -1 ){ 
                            setIcon( rootIcon ); 
                            setText( rootLabel);
                        }
                    
                    } catch( Exception error ){
                        
                            // log error
                            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                            // show error message
                            KMetaUtilsClass.showErrorMessageFromException(  parentWindow,  error );
                    };   
                                        
                    return( this );
                }
            }
            
            //----------------------------------------------                        
            
            public class KtreeNodePopupMenuClass
            extends JPopupMenu
            {
                // uses
                
                //has
                JMenuItem newMenuItem;
                JMenuItem editMenuItem;
                JMenuItem deleteMenuItem;   
                
                
                public KtreeNodePopupMenuClass( 
                         boolean allowNew , boolean allowEdit , boolean allowDelete )
                throws KExceptionClass{   
                    
                    super( "Edit node" );
                    
                   // has
                    if( allowNew ){
                        newMenuItem = new JMenuItem( "Add", new ImageIcon( getClass().getResource("/resources/new1.jpg" ) ) );
                        newMenuItem.setFont( new Font( "arial", Font.PLAIN, 10 ) );
                        newMenuItem.setActionCommand( "NEW" );
                        newMenuItem.addActionListener( KTreeViewerClass.this );
                        add( newMenuItem );  
                    }
                    
                    if( allowEdit ){
                        editMenuItem = new JMenuItem( "Edit", new ImageIcon( getClass().getResource("/resources/edit1.jpg" ) ) );
                        editMenuItem.setFont( new Font( "arial", Font.PLAIN, 10 ) );                    
                        editMenuItem.setActionCommand( "EDIT" );                    
                        editMenuItem.addActionListener( KTreeViewerClass.this );                    
                        add( editMenuItem );                    
                    }
                    
                    if( allowDelete ){
                        deleteMenuItem = new JMenuItem( "Delete", new ImageIcon( getClass().getResource("/resources/delete1.jpg" ) ) );                                                            
                        deleteMenuItem.setFont( new Font( "arial", Font.PLAIN, 10 ) );                                        
                        deleteMenuItem.setActionCommand( "DELETE" );                    
                        deleteMenuItem.addActionListener( KTreeViewerClass.this );                    
                        add( deleteMenuItem );                    
                    }
                    
                    pack();
                }
            }
            
            
            //----------------------------------------------                                    
            
    //----------------------------------------------
    // public interface
    
    public KTreeViewerClass(
        KConfigurationClass configurationParam, KLogClass logParam, 
        java.awt.Window parentWindowParam, 
        JTree treeParam,
        Icon rootIconParam, String rootLabelParam )
    throws KExceptionClass
    {

        // inherits
        super();

        // uses
        configuration = configurationParam;
        log = logParam;
        parentWindow = parentWindowParam;
        visualTree = treeParam;        
        rootIcon = rootIconParam;
        rootLabel = rootLabelParam;
        
        // has - defaulted
        levelsList = new ArrayList();                
        defaultNodeRenderer = new defaultNodeRendererClass();        
        
        // log
        log.log( this, "Creating new tree view." );        
            
    }

    //-------------------------------------------------------------------------
    public JTree getJTree()
    {
        return visualTree;
    }
    
    //----------------------------------------------        
    
    
    public void setLevelPopMenu( int level, JPopupMenu customPopParam )
    throws KExceptionClass
    {
        
        if( level > levelsList.size() - 1 ){
            
            throw new KExceptionClass(
                "*** Could not open field selector ***\n" +
                "Could not set POPUP menu, given level is out of bounds." , null 
            );
        };
        
        if( level == -1 ){
            
            rootPopupMenu = customPopParam;
            
        }else{
            
            // buffer level to load
            levelClass currentLevel = ( levelClass ) levelsList.get( level );                         
            currentLevel.popupMenu = customPopParam;
        }
    }
    
    public void addLevel( 
        String IDparam, String labelParam, 
        dbTransactionClientClass dbTransactionParam,
        int nodeModeParam,
        Icon iconParam )
        throws KExceptionClass
    {
                        
        // build new level, store it
        levelClass newLevel = new levelClass(
            IDparam, labelParam, dbTransactionParam,
            nodeModeParam, iconParam, null );
            
        if( levelsList.size() == 0 && nodeModeParam == NODE_MODE_ONDEMAND ){
            throw new KExceptionClass(
                "*** Could not initilize tree view ***\n" +
                "First level can not be declared 'ONDEMAND'\n" , null 
            );                            
        };
        
        levelsList.add( newLevel );
    }
    
    //----------------------------------------------    
    
    
    public void initialize()
    throws KExceptionClass
    {
        
        KMetaUtilsClass.cursorWait( parentWindow );
        
        try{
            

            if( levelsList.size() == 0 ){
                    throw new KExceptionClass(
                        "*** Could not initilize tree view ***\n" +
                        "There were no levels declared\n" , null 
                    );                
            };

            //-------------------------------------------------------------
            log.log( this, "Loading level data" );        

            // for each level, load data            
            for( int index = 0; index < levelsList.size(); index++ ){

                // buffer level to load
                levelClass currentLevel = ( levelClass ) levelsList.get( index );                      

                // clean in case of reload
                currentLevel.levelData = new ArrayList();

                // on demand?, then done.
                if( currentLevel.nodeMode == NODE_MODE_ONDEMAND ) continue;

                // load data
                currentLevel.dbTransaction.reusePrevious();             
                currentLevel.dbTransaction.executeQuery( 0, 65536 ); 

                // manual first fetch to verify query
                if( currentLevel.dbTransaction.fetch() ){

                    // dont check root level for parent
                    if( index > 0 ){                    
                        // parent pointer check
                        try{                        
                            currentLevel.dbTransaction.getField( ( ( levelClass ) levelsList.get( index - 1 ) ).OIDfieldName );

                        }catch( KExceptionClass error ){
                            throw new KExceptionClass(
                                "*** Could not initialize tree view ***\n" +
                                "Level [" + currentLevel.OIDfieldName  + "]" +
                                " query does not return parent pointer [" +
                                ( ( levelClass ) levelsList.get( index - 1 ) ).OIDfieldName +
                                "]\n" +
                                "\n" +
                                error, error
                            );
                        }                        
                    }

                    // OID check
                    try{

                        currentLevel.dbTransaction.getField( currentLevel.OIDfieldName );                    
                        currentLevel.isOIDnumeric = currentLevel.dbTransaction.isColumnNumeric( currentLevel.OIDfieldName );

                    }catch( KExceptionClass error ){
                        throw new KExceptionClass(
                            "*** Could not initialize tree view ***\n" +
                            "Level [" + currentLevel.OIDfieldName  + "]" +
                            " query does not return OID field [" +
                            currentLevel.OIDfieldName +
                            "]\n" +
                            "\n" +
                            error, error
                        );
                    }                        


                    // label check
                    try{

                        currentLevel.dbTransaction.getField( currentLevel.labelFieldName );

                    }catch( KExceptionClass error ){
                        throw new KExceptionClass(
                            "*** Could not initialize tree view ***\n" +
                            "Level [" + currentLevel.OIDfieldName  + "]" +
                            " query does not return label field [" +
                            currentLevel.labelFieldName +
                            "]\n" +
                            "\n" +
                            error, error
                        );
                    }                        


                    HashMap dataRow = new HashMap( currentLevel.dbTransaction );                
                    currentLevel.levelData.add( dataRow );

                    // verified!, continue loading
                    while( currentLevel.dbTransaction.fetch() ){                
                        dataRow = new HashMap( currentLevel.dbTransaction );                
                        currentLevel.levelData.add( dataRow );
                    };

                };

            }; // for buffer


            //---------------------------------------------------        
            log.log( this, "Building node tree..." );        

            // id, label, level, mode, position, parent
            treeNodeClass rootNode = new treeNodeClass( "-1", "", "", -1, NODE_MODE_AUTO, 0, null, new HashMap() );        

            rootNode.materializeChildrenByNode(); // >>>>>>>>>>>>>>>>> Start building the TREE

            //-------------------------------------------------------------
            log.log( this, "Preparing display" );                

            // set data model
            treeModel = new DefaultTreeModel( rootNode );                              
            visualTree.setModel( treeModel );                

            // show lines
            visualTree.putClientProperty( "JTree.lineStyle", "Angled" );                

            // set selection mode / listener
            visualTree.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );        
            visualTree.addTreeSelectionListener( this );

            // set cell rendering component
            visualTree.setCellRenderer( this );                

            // set mouser event dispatcher
            visualTree.addMouseListener( this );

            // set roots popupMenu
            rootPopupMenu = null;

            //-------------------------------------------------------------
            log.log( this, "Tree view is done>>>>>>>>>>>>>>>>>" );        
            
        }finally{
            
            KMetaUtilsClass.cursorNormal( parentWindow );
        }
    }             
    
    
   //---------------------------------------------------------------
   // button registration

    // EVENT HANDLING    
    
    private JButton newActionButton = new JButton();
    private JButton editActionButton = new JButton();
    private JButton deleteActionButton = new JButton();
    private JButton refreshActionButton = new JButton();    
    
    public void setNewActionButton( JButton button ){        
        newActionButton = button;
        newActionButton.setActionCommand( "NEW" );        
        newActionButton.addActionListener( this );
    }

    public void setEditActionButton( JButton button ){
        editActionButton = button;        
        editActionButton.setActionCommand( "EDIT" );        
        editActionButton.addActionListener( this );        
    }

    public void setDeleteActionButton( JButton button ){
        deleteActionButton = button;        
        deleteActionButton.setActionCommand( "DELETE" );        
        deleteActionButton.addActionListener( this );        
    }

    public void setRefreshActionButton( JButton button ){
        refreshActionButton = button;        
        refreshActionButton.setActionCommand( "REFRESH" );        
        refreshActionButton.addActionListener( this );        
    }
    
    
   //---------------------------------------------------------------    
    
    public void newActionButtonEnabled( boolean enabled ){        
        newActionButton.setEnabled( enabled );
    }

    public void editActionButtonEnabled( boolean enabled ){        
        editActionButton.setEnabled( enabled );
    }

    public void deleteActionButtonEnabled( boolean enabled ){        
        deleteActionButton.setEnabled( enabled );
    }
   
    public void refreshActionButtonEnabled( boolean enabled ){        
        refreshActionButton.setEnabled( enabled );
    }

   //---------------------------------------------------------------    
   // Tree mouser events handler
    
    public void mouseReleased( java.awt.event.MouseEvent event ){
        checkPopupEvent(event );
    }    
    
    public void mousePressed( java.awt.event.MouseEvent event ) {
        checkPopupEvent(event );        
    }
    
    public void mouseEntered( java.awt.event.MouseEvent event ) {
    }    
    
    public void mouseClicked( java.awt.event.MouseEvent event ) {
        checkPopupEvent(event );        
    }
        
    public void mouseExited( java.awt.event.MouseEvent event ) {
    }

    // ale
    public void checkPopupEvent( java.awt.event.MouseEvent event ){

        if( event.isPopupTrigger() ){

            // is there anything selected ?
            if( visualTree.getSelectionPath() == null ){
                /*
                KMetaUtilsClass.showErrorMessage( 
                    component, 
                        "*** Could not perform action ***\n" +
                        "Please select a node in the tree. \n"
                    );            
                 */
                return;
            };

            // get current selection        
            treeNodeClass selectedNode = ( treeNodeClass) visualTree.getSelectionPath().getLastPathComponent();  
                                 

            // show pop
            try{

                // find popup menu
                JPopupMenu popMenu;
                
                if( selectedNode.levelIndex == -1 )
                    popMenu = rootPopupMenu;
                else
                    popMenu = ( ( levelClass ) levelsList.get( selectedNode.levelIndex ) ).popupMenu;  
                
               
                // validate and display the popup menu                
                mouseRightClickPerformed( 
                    selectedNode.ID, selectedNode.levelIndex, 
                    selectedNode.label,  selectedNode, popMenu, event );                  
                
                if( popMenu == null ) return;
                
                if( popMenu instanceof KtreeNodePopupMenuClass ){

                    // default                    
                    ((KtreeNodePopupMenuClass) popMenu).show( event.getComponent (), event.getX (), event.getY () );
                    
                }else{
                    
                    // custom                    
                    popMenu.show( event.getComponent (), event.getX (), event.getY () );
                    
                };
                
                
            } catch( Exception error ){
                    // log error
                    log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                    // show error message
                    KMetaUtilsClass.showErrorMessageFromException(  parentWindow, error );
            };   
                            
        } // is pop trigger        
        
    }
    
    //---------------------------------------------------------------        
    // Tree action events
    
    private void reloadLevelCascade( int index )
    throws KExceptionClass
    {

        if( index > levelsList.size() - 1 ){
                throw new KExceptionClass(
                    "*** Tree view error ***\n" +
                    "Refresh cascade failed.\n"   , null               
                );
        };

        // get level
        levelClass level = ( levelClass ) levelsList.get( index );                            
        
        // on demand?
        if( level.nodeMode == NODE_MODE_ONDEMAND ){                                               
        };      

        // auto ?
        if( level.nodeMode == NODE_MODE_AUTO ){

            // reload buffer
            level.dbTransaction.reusePrevious();
            level.dbTransaction.executeQuery( 0, 65536 );                        

            // empty buffer
            level.levelData.clear();                        

            // reload buffer
            while( level.dbTransaction.fetch() ){                
                HashMap dataRow = new HashMap( level.dbTransaction );                
                level.levelData.add( dataRow );
            };

        };                          

        // refresh next....
        if( index < levelsList.size() - 1 ){
            reloadLevelCascade( index + 1 );          
        };
        
    }
    
    //--------------------------------------
    
    // Returns Node rendering object, called node by node
    public Component getTreeCellRendererComponent(
        JTree tree, Object node, boolean selected, boolean expanded,
        boolean leaf, int row, boolean hasFocus) {

        Component result = 
            defaultNodeRenderer.getTreeCellRendererComponent( 
                    tree, node, selected, expanded, leaf, row, hasFocus ) ;
            
        try{
            
            // get node to draw for
            treeNodeClass selectedNode = (treeNodeClass) node;
            
            // call user api for renderer if any
            result = getNodeRenderer( 
                selectedNode.ID, selectedNode.levelIndex, selectedNode.label, 
                selectedNode, (defaultNodeRendererClass) result );

                    
        } catch( Exception error ){

                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException(  parentWindow,  error );
        } 

        
        return( result );
    }    
    
    //--------------------------------------    
    
    public void actionPerformed( java.awt.event.ActionEvent event ){
        
        try{

            String command = event.getActionCommand();

            if( command.equals( "REFRESH" ) ){
                refreshButtonActionPerformed();        
                return;
            };

            // is there anything selected
            if( visualTree.getSelectionPath() == null ){
                KMetaUtilsClass.showErrorMessageFromText1( 
                         parentWindow, 
                        "*** Could not perform action ***\n" +
                        "Please select a node in the tree. \n"
                    );            
                return;
            };

            // get current selection        
            treeNodeClass selectedNode = ( treeNodeClass) visualTree.getSelectionPath().getLastPathComponent();                

            //--------------------------------------
            
            if( command.equals( "NEW" ) ){
                
                log.log( this, "<<<<<<<<<<<<<<<< NEW >>>>>>>>>>>>>>>>>>>>>" );

                newButtonActionPerformed( selectedNode.ID, selectedNode.levelIndex, selectedNode.label, selectedNode );              
                
                // if there are children...                
                if( selectedNode.levelIndex + 1 != levelsList.size() ){
                                        
                    // notify children may be invalid now...                        
                    selectedNode.status = treeNodeClass.STATUS_CHILDREN_NOT_MATERIALIZED;                                        
                    selectedNode.children = new ArrayList();
                                        
                    reloadLevelCascade( selectedNode.levelIndex + 1 );                    
                    
                    selectedNode.materializeChildrenByNode();

                };                                                   
                
                treeModel.nodeStructureChanged( selectedNode );

                log.log( this, "<<<<<<<<<<<<<<<< NEW DONE >>>>>>>>>>>>>>>>>>>>>" );                
                
                return;            
            };
            
            //--------------------------------------            
            
            if( command.equals( "EDIT" ) ){
                
                log.log( this, "<<<<<<<<<<<<<<<< EDIT >>>>>>>>>>>>>>>>>>>>>" );                

                editButtonActionPerformed( selectedNode.ID, selectedNode.levelIndex, selectedNode.label, selectedNode );                    

                
                //-----------------------------------------
                // parent notification, the child may be gone...
                                
                // notify children may be invalid now...
                selectedNode.parent.status = treeNodeClass.STATUS_CHILDREN_NOT_MATERIALIZED;                                        
                selectedNode.parent.children = new ArrayList();

                reloadLevelCascade( selectedNode.levelIndex );                                                        

                // materialize branch
                selectedNode.parent.materializeChildrenByNode();

                
                treeModel.nodeStructureChanged( selectedNode.parent );                
                
                                
                //-----------------------------------------                
                // Update selected node now...                
                                        
                // notify children may be invalid now...
                selectedNode.status = treeNodeClass.STATUS_CHILDREN_NOT_MATERIALIZED;                                        
                selectedNode.children = new ArrayList();                

                selectedNode.materializeChildrenByNode();                   
                
                treeModel.nodeStructureChanged( selectedNode );

                //-----------------------------------------
                                               
                log.log( this, "<<<<<<<<<<<<<<<< EDIT DONE >>>>>>>>>>>>>>>>>>>>>" );                                
                
                return;            
            };

            //--------------------------------------            
            
            if( command.equals( "DELETE" ) ){        
                
                log.log( this, "<<<<<<<<<<<<<<<< DELETE >>>>>>>>>>>>>>>>>>>>>" );                

                deleteButtonActionPerformed( selectedNode.ID, selectedNode.levelIndex, selectedNode.label, selectedNode );                    

                //-----------------------------------------
                // parent notification, the child may be gone...
                                
                // notify children may be invalid now...
                selectedNode.parent.status = treeNodeClass.STATUS_CHILDREN_NOT_MATERIALIZED;                                        
                selectedNode.parent.children = new ArrayList();

                reloadLevelCascade( selectedNode.levelIndex );                                                        

                // materialize branch
                selectedNode.parent.materializeChildrenByNode();
                
                treeModel.nodeStructureChanged( selectedNode.parent );                
                
                log.log( this, "<<<<<<<<<<<<<<<< DELETE DONE >>>>>>>>>>>>>>>>>>>>>" );                                
                
                return;            
            };

        }catch( Exception error ){
            
            // log error
            log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
            // show error message
            KMetaUtilsClass.showErrorMessageFromException(  parentWindow,  error );
        };

    }    

    
   //---------------------------------------------------------------
   // event dispatcher
    
    // for java
    public void valueChanged( javax.swing.event.TreeSelectionEvent event ) {  
        
        treeNodeClass selectedNode = ( treeNodeClass ) event.getPath().getLastPathComponent();
        
        if( selectedNode == null ) return;
        
        mouseClickPerformed( selectedNode.ID, selectedNode.levelIndex, selectedNode.label,  selectedNode );
    }
   
    
   //---------------------------------------------------------------   
   // User interface 
    
    // for user to override
    public abstract void mouseClickPerformed( 
            String ID, int level, String label, treeNodeClass selectedNode 
              );
    
    public abstract void mouseRightClickPerformed( String ID, int level, String label, 
            treeNodeClass selectedNode, JPopupMenu popMenu, java.awt.event.MouseEvent event );
    
    public abstract void newButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode );

    public abstract void editButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode );

    public abstract void deleteButtonActionPerformed( String ID, int level, String label, treeNodeClass selectedNode );
    
    // optional
    public void refreshButtonActionPerformed(){        
        try{
            initialize();
        } catch( Exception error ){
                // log error
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );		                
                // show error message
                KMetaUtilsClass.showErrorMessageFromException(  parentWindow,  error );
        };   
    }
    
    // channce to override renderers
    public Component getNodeRenderer( String ID, int level, String label, treeNodeClass selectedNode, defaultNodeRendererClass defaultNodeRenderer  ){ return( defaultNodeRenderer ); }
    
   //---------------------------------------------------------------    
    
}
