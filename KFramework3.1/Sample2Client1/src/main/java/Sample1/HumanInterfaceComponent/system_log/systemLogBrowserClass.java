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

//rtl
import javax.swing.*;
import java.awt.*;


// utilities
import KFramework30.Widgets.*;
import KFramework30.Base.*;

// system
import KFramework30.Communication.persistentObjectManagerClass;
import ProblemDomainComponent.systemMailClass;



public class systemLogBrowserClass extends KDataBrowserBaseClass {   
        
    // uses                       	
    
    // has

    public systemLogBrowserClass(
            KConfigurationClass configurationParam,
            KLogClass logParam,
            JTable tableParam,
            java.awt.Window parentWindow ) throws KExceptionClass
    {
        
            // inherits
            super(
                    configurationParam, logParam,
                    true, tableParam, parentWindow,  
                    null, null
            );  
            
            // uses                   
	    // has   
            
            setDoubleClickEnabled( false );
    }        


    public void initializeTable()
    throws KExceptionClass
    {	      
            //------------------------------------------------------------------
            //Get daily category
            
            super.initializeSQLQuery( 
                                                          
                " LOG_ID , LOG_USER, LOG_TIME,   LOG_ACTION, LOG_OBJECT_ID, LOG_OBJECT_CLASS, "+
                " LOG_OBJECT_MESSAGE " ,
                
                // TABLAS
                " SYSTEMLOGCLASS LOG",  
                
                // pk
                "LOG_ID"                                                                                
                    );   

            setColumnNames( "", "LOG_ID", "id" );             
            setColumnNames( "", "LOG_USER", "Usuario" );            
            setColumnNames( "", "LOG_TIME", "Estampa" );
            setColumnNames( "", "LOG_ACTION", "Accion" );                       
            setColumnNames( "", "LOG_OBJECT_ID", "ID" );
            setColumnNames( "", "LOG_OBJECT_CLASS", "Tipo" );
            setColumnNames( "", "LOG_OBJECT_MESSAGE", "Datos" );            
            
            setDefaultOrder( " log_id " );

            
            super.initializeTable();   
            
            
            adjustColumnWidth( "id", 60 );
            adjustColumnWidth( "Usuario", 60 );
            adjustColumnWidth( "Estampa", 120 );            
            adjustColumnWidth( "Accion", 60 );
            adjustColumnWidth( "ID", 60 );
            adjustColumnWidth( "Tipo", 120 );            
            adjustColumnWidth( "Datos", 1000 );                        
            
            adjustColumnFont(  "id",  new Font( "arial", Font.PLAIN, 10  ) );            
            adjustColumnFont(  "Usuario",  new Font( "arial", Font.PLAIN, 10 )  );                        
            adjustColumnFont(  "Estampa",  new Font( "arial", Font.PLAIN, 10 )  );                        
            adjustColumnFont(  "Accion",  new Font( "arial", Font.PLAIN, 10 )  );            
            adjustColumnFont(  "ID",  new Font( "arial", Font.PLAIN, 10 )  );                        
            adjustColumnFont(  "Tipo",  new Font( "arial", Font.PLAIN, 10 )  );                        
            adjustColumnFont(  "Datos",  new Font( "arial", Font.PLAIN | Font.BOLD, 10 )  );                                    

    }        
 
}
