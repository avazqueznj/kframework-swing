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


// TopLink
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
public class systemUserClass 
extends KBusinessObjectClass {

    // uses
    
    // has - defaulted
    
    //user info.    
    @Id 
    @GeneratedValue( strategy = javax.persistence.GenerationType.TABLE, generator="KIDGenerator" )    
    @KID
    private long         system_user_id;  
    
    private String       system_user_name;      
    private String       system_user_fullname;
    private String       system_user_password;
    private String       system_user_phone;
    private String       system_user_email;    
    
    private String       system_user_notes;
 
    private String       system_user_role;
    private String       system_user_isadmin;    
    
   
    
    /** Creates new analysisClass */
    public systemUserClass() 
    throws KExceptionClass{
        
        super();
                
        
        // initialize fields
	system_user_id = -1; 

	system_user_name = ""; 
	system_user_fullname = "";         
	system_user_password = "";
        system_user_phone = "";
        system_user_email = "";

        system_user_notes = "";
        
        system_user_role = "Default";  
        system_user_isadmin = "N";          
        
    }

    
        // ==========================================================================================
    
    
    public String getSystem_user_email() {
        return system_user_email;
    }

    public void setSystem_user_email(String system_user_email) {
        this.system_user_email = system_user_email;
    }

    public String getSystem_user_fullname() {
        return system_user_fullname;
    }

    public void setSystem_user_fullname(String system_user_fullname) {
        this.system_user_fullname = system_user_fullname;
    }

    public long getSystem_user_id() {
        return system_user_id;
    }

    public void setSystem_user_id(long system_user_id) {
        this.system_user_id = system_user_id;
    }

    public String getSystem_user_isadmin() {
        return system_user_isadmin;
    }

    public void setSystem_user_isadmin(String system_user_isadmin) {
        this.system_user_isadmin = system_user_isadmin;
    }

    public String getSystem_user_name() {
        return system_user_name;
    }

    public void setSystem_user_name(String system_user_name) {
        this.system_user_name = system_user_name;
    }

    public String getSystem_user_notes() {
        return system_user_notes;
    }

    public void setSystem_user_notes(String system_user_notes) {
        this.system_user_notes = system_user_notes;
    }

    public String getSystem_user_password() {
        return system_user_password;
    }

    public void setSystem_user_password(String system_user_password) {
        this.system_user_password = system_user_password;
    }

    public String getSystem_user_phone() {
        return system_user_phone;
    }

    public void setSystem_user_phone(String system_user_phone) {
        this.system_user_phone = system_user_phone;
    }

    public String getSystem_user_role() {
        return system_user_role;
    }

    public void setSystem_user_role(String system_user_role) {
        this.system_user_role = system_user_role;
    }
    

    // ID
    
    
           
    // ==========================================================================================

    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }
   
   
   
}
