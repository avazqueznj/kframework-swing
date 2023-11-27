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
package ProblemDomainComponent;


// utilities
import KFramework30.Base.*;
import java.awt.Component;
import javax.persistence.*;


@Entity
@TableGenerator( // SCOPE is Global to PU
    name="KIDGenerator", 
    table="SEQUENCE", 
    pkColumnName="SEQ_NAME", 
    valueColumnName="SEQ_COUNT", 
    pkColumnValue="SEQ_GEN_TABLE", 
    allocationSize=1)    
public class systemLogClass 
extends KBusinessObjectClass
{

 
    //airline information.
    @KID
    @Id 
    @GeneratedValue( strategy = javax.persistence.GenerationType.TABLE, generator="KIDGenerator" )    
    private long         log_id;     
    private String       log_user;  
    private String       log_time;  
    private String       log_action;       
    private long         log_object_id;          
    private String       log_object_class;              
    private String       log_object_message;                  

    
   
    /** Creates new aerolineaClass */
    public systemLogClass() throws KExceptionClass{
     
// obsolete        
//        fieldNames.add( "log_id" );           
//        fieldNames.add( "log_user" );
//        fieldNames.add( "log_time" );     
//        fieldNames.add( "log_action" );
//        fieldNames.add( "log_object_id" );                    
//        fieldNames.add( "log_object_class" );            
//        fieldNames.add( "log_object_message" );
              
        // initialize fields
	log_id = -1;  
        
    
        log_user = "";  
        log_time = "";  
        log_action = "";       
        log_object_id = -1;          
        log_object_class = "";              
        log_object_message = "";         

        editable = false;
    }
    

    // ==============================================================================================
    
    public String getLog_action() {
        return log_action;
    }

    public void setLog_action(String log_action) {
        this.log_action = log_action;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public long getLog_object_id() {
        return log_object_id;
    }

    public void setLog_object_id(long log_object_id) {
        this.log_object_id = log_object_id;
    }

    public String getLog_object_message() {
        return log_object_message;
    }

    public void setLog_object_message(String log_object_message) {
        this.log_object_message = log_object_message;
    }

    public String getLog_time() {
        return log_time;
    }

    public void setLog_time(String log_time) {
        this.log_time = log_time;
    }

    public String getLog_user() {
        return log_user;
    }

    public void setLog_user(String log_user) {
        this.log_user = log_user;
    }

    public String getLog_object_class() {
        return log_object_class;
    }

    public void setLog_object_class(String log_object_class) {
        this.log_object_class = log_object_class;
    }

    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }
    
   
    
}
