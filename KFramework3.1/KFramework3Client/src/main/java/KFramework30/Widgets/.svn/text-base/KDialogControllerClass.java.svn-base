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

package KFramework30.Widgets;

// framework
import KFramework30.Base.KCustomWidgetIntegrationIntrerface;
import KFramework30.Base.KBusinessObjectClass;
import KFramework30.Base.*;
import KFramework30.Communication.persistentObjectManagerClass;

//rtl
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.JDialog;


public class KDialogControllerClass {
    
    // constants
        
    // uses - passed
    private KConfigurationClass                                   configuration;
    private KLogClass                                             log;
    private Container                                             visualWidgetContainer;
    private Class<? extends KBusinessObjectClass >                pdcClassType;   
    private KDialogInterface                                      dialog;    
    private int                                                   dialogMode;
    
    private long                                                  businessObjectOID;
    private Map< String, Long >                                   buisnessObjectForeingOIDS;
        
    // has - defaulted
    private Vector                                                nonVisibleWidgets;
    persistentObjectManagerClass                                  persistentObjectManager;

    private KDialogEventCallbackInterface                         EventCallback;
    public void setEventCallback(KDialogEventCallbackInterface EventCallback) {
        this.EventCallback = EventCallback;
    }
    
    private int visualMappingMode   = KBusinessObjectClass.DONOT_IGNORE_MISSING_FIELDS;
    public void setVisualMappingMode(int visualMappingMode) {
        this.visualMappingMode = visualMappingMode;
    }
    
    
    //----------------------------------------------------------------------------------
    
    // inner classes
    public interface KDialogInterface{
        
        // constants
        public static final int EDIT_MODE = 0;
        public static final int CREATE_NEW_MODE = 1;
        
        // interface
        public void initializeDialog( int dialogModeParam, Long ID, Map foreingKeysMapParam ) throws KExceptionClass;
        public void setupTables( long businessObjectOID ) throws KExceptionClass;
        public void saveBrowserChanges();
                        
    }

    public interface KDialogEventCallbackInterface{
        
        public void preNewObject() throws Exception;
        public void postNewObject( KBusinessObjectClass businessObject ) throws Exception;
        
        public void prePushBack( KBusinessObjectClass businessObject ) throws Exception;
        public void postPushBack( KBusinessObjectClass businessObject ) throws Exception;

        public void preEdit() throws Exception ;
        public void postEdit( KBusinessObjectClass businessObject ) throws Exception;

        public void preUpdate( KBusinessObjectClass businessObject ) throws Exception ;
        public void postUpdate( KBusinessObjectClass businessObject )throws Exception;
        
                        
    }
    
    
    //----------------------------------------------------------------------------------
    // Interface ----
    
    public KDialogControllerClass(             
            KConfigurationClass configurationParam, KLogClass logParam, 
            Class<? extends KBusinessObjectClass > pdcTypeParam,
            KDialogInterface dialogParam,
            Container visualWidgetContainerParam            
             ) throws KExceptionClass{
        
        // uses - passed
        pdcClassType = pdcTypeParam;
        configuration = configurationParam;
        log = logParam;          
        visualWidgetContainer = visualWidgetContainerParam;
        dialog = dialogParam;
        
        // has - defaulted
        nonVisibleWidgets = new Vector();
        persistentObjectManager = new persistentObjectManagerClass( configuration, log );
        buisnessObjectForeingOIDS = new HashMap< String, Long >();
    }
    
    
    //==========================================================================
    // Call this manually to reload another object id, for example...
    
    public void initializeDialog( 
            int dialogModeParam, long businessObjectOIDParam, Map foreingKeysMapParam ) 
    throws KExceptionClass{
        
        // uses
        dialogMode = dialogModeParam;
        businessObjectOID = businessObjectOIDParam;
        buisnessObjectForeingOIDS = foreingKeysMapParam;
                
        try{      
                                          
            if( dialogMode == KDialogInterface.CREATE_NEW_MODE ) {
              
                newObject();

            }
            else if( dialogMode == KDialogInterface.EDIT_MODE ) {
              
                edit();
                dialog.setupTables( businessObjectOID );          

            }
            else 
                throw new KExceptionClass( 
                    " *** could not display Window *** \n " +
                    " Invalid operation mode" ,null );     
        
        }catch( Exception error ){            
            
            throw new KExceptionClass( "Could not initialize dialog ", error );
            
        } 
        
    }
    
          
    //==========================================================================
    // These are normally automatically handeled, but public acces offered for cetain situations
    
            public void newObject()throws KExceptionClass
            {
                try{

                    if( EventCallback != null )  EventCallback.preNewObject();
                    
                    KBusinessObjectClass businessObject = pdcClassType.newInstance();
                    
                    KMetaUtilsClass.setProgressBarValue1( 25 );
                    businessObject = persistentObjectManager.createNew4( businessObject );
                    
                    
                    // map foreing keys
                    KMetaUtilsClass.setProgressBarValue1( 50 );
                    if( buisnessObjectForeingOIDS != null ){                        
                        Set listOfForeingKeys = buisnessObjectForeingOIDS.keySet();                        
                        Iterator foreingKeyIterator = listOfForeingKeys.iterator();                        
                        while( foreingKeyIterator.hasNext() ){                            
                            String nextForeingKeyName = (String) foreingKeyIterator.next();                            
                            businessObject.setField( nextForeingKeyName, buisnessObjectForeingOIDS.get( nextForeingKeyName ) );                        
                        }
                    }
                    
                    KMetaUtilsClass.setProgressBarValue1( 75 );
                    businessObject.displayVisualize( 
                            visualWidgetContainer, nonVisibleWidgets, visualMappingMode );
                    
                    if( EventCallback != null )  EventCallback.postNewObject( businessObject  );

                }catch( Exception error ){            
                    throw new KExceptionClass( "Could not create new object", error );
                }
                
                KMetaUtilsClass.setProgressBarValue1( 100 );
            }
                    
            public long pushBack()
            throws KExceptionClass
            {
                
                
                try{
                                                            
                    KBusinessObjectClass businessObject = pdcClassType.newInstance();
                    
                    KMetaUtilsClass.setProgressBarValue1( 25 );
                    businessObject.materializeFromDisplay( visualWidgetContainer, nonVisibleWidgets, visualMappingMode);
                                        
                    if( EventCallback != null )  EventCallback.prePushBack( businessObject );
                    
                    KMetaUtilsClass.setProgressBarValue1( 50 );                                        
                    businessObject = persistentObjectManager.push_back4( businessObject );                     
                    businessObjectOID = businessObject.getOID();
                                        
                    KMetaUtilsClass.setProgressBarValue1( 75 );                    
                    dialogMode = KDialogInterface.EDIT_MODE; 
                    
                    if( EventCallback != null )  EventCallback.postPushBack( businessObject );
                    
                    edit( ); 
                                                        
                }catch( Exception error ){            
                    
                    throw new KExceptionClass( "Could not create new object", error );
                }                             

                KMetaUtilsClass.setProgressBarValue1( 100 );                    
                
                return( businessObjectOID );
            }

            public void edit( )
            throws KExceptionClass                                                            
            {
                
                try{    
                    
                    if( EventCallback != null )  EventCallback.preEdit();
                    
                    KBusinessObjectClass businessObject = pdcClassType.newInstance();  
                    
                    KMetaUtilsClass.setProgressBarValue1( 25 );
                    businessObject = persistentObjectManager.copy4( businessObjectOID, pdcClassType );

                    KMetaUtilsClass.setProgressBarValue1( 75 );
                    businessObject.displayVisualize( 
                            visualWidgetContainer, nonVisibleWidgets, visualMappingMode );
                    
                    if( EventCallback != null )  EventCallback.postEdit( businessObject );
                    
                }catch( Exception error ){            
                    
                    throw new KExceptionClass( "Could not create new object", error );
                }                             
                     
                KMetaUtilsClass.setProgressBarValue1( 100 );
            }

            public void update( )
            throws KExceptionClass
            {
                
                try{    
                                        
                
                    KBusinessObjectClass businessObject = pdcClassType.newInstance();  

                    KMetaUtilsClass.setProgressBarValue1( 33 );
                    businessObject.materializeFromDisplay( 
                            visualWidgetContainer, nonVisibleWidgets, visualMappingMode );
                    
                    if( EventCallback != null )  EventCallback.preUpdate( businessObject );

                    KMetaUtilsClass.setProgressBarValue1( 75 );
                    businessObject = persistentObjectManager.update4( businessObjectOID, businessObject );

                    if( EventCallback != null )  EventCallback.postUpdate(businessObject );
                    
                    edit();
                                        
                
                }catch( Exception error ){            
                    
                    throw new KExceptionClass( "Could not create new object", error );
                }                             
                
                KMetaUtilsClass.setProgressBarValue1( 100 );
                
            }
            
  
    //==========================================================================   
            
     public void OKbuttonActionPerformed() throws KExceptionClass {
        
          try{                       

              KMetaUtilsClass.cursorWait( (JDialog) dialog );  
              
              KMetaUtilsClass.setProgressBarValue1( 25 );              
              dialog.saveBrowserChanges();                                       
              
              if( dialogMode == KDialogInterface.CREATE_NEW_MODE ){

                  KMetaUtilsClass.setProgressBarValue1( 50 );
                  pushBack();
                  
                  KMetaUtilsClass.setProgressBarValue1( 75 );
                  dialog.setupTables( businessObjectOID );    
                  


              }else if( dialogMode == KDialogInterface.EDIT_MODE ){

                  KMetaUtilsClass.setProgressBarValue1( 75 );
                  update();                  
              }
          
          }finally{
             
              KMetaUtilsClass.setProgressBarValue1( 100 );
              KMetaUtilsClass.cursorNormal( (JDialog) dialog );                                                
          }
          

    }               
          
    //==========================================================================        
     
    public Vector getNonVisibleWidgets() {
        return nonVisibleWidgets;
    }

    public void addNonVisibleWidget( Object nonVisibleWidget ) {        
        nonVisibleWidgets.add(nonVisibleWidget);
    }
    
    public void includeCustomWidgetForMapping( KCustomWidgetIntegrationIntrerface dateChooserAdapter) {
        addNonVisibleWidget( dateChooserAdapter );
    }
    
    
    //==========================================================================             

    public KBusinessObjectClass getCurrentObjectDisplayed() throws KExceptionClass{
        
         KBusinessObjectClass businessObject = null;
   
        try{
            
            businessObject = pdcClassType.newInstance();  

            businessObject.materializeFromDisplay( 
                    visualWidgetContainer, nonVisibleWidgets, visualMappingMode );
           
        }catch( Exception error ){            

            throw new KExceptionClass( "Could not create new object", error );
        }    
        
        return businessObject;
    }
    
    //------------    
    
    public void setCurrentObjectDisplayed( KBusinessObjectClass businessObject ) throws KExceptionClass{
        
   
        try{
            
            if( businessObject.getClass() != pdcClassType ){
                throw new KExceptionClass(
                        "Invalid object type to display by dialog controller."
                        + " Controller created for " + pdcClassType.getName() , null);
            }

            businessObject.displayVisualize( 
                    visualWidgetContainer, nonVisibleWidgets, visualMappingMode );
           
        }catch( Exception error ){            

            throw new KExceptionClass( "Could not create new object", error );
        }    
        
       
    }    

    //==========================================================================                  
    
    public int getDialogMode() {
        return dialogMode;
    }
    
    //==========================================================================                  
    
    public void setDialogMode( int dialogModeParam ) {
        dialogMode = dialogModeParam;
    }
        
    //==========================================================================                  

    public long getBusinessObjectOID() {
        return businessObjectOID;
    }


}
