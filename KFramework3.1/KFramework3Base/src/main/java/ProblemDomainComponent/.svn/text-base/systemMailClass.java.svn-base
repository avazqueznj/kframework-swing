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

/**
 *
 * @author Alejandro
 */

@Entity
@TableGenerator( // SCOPE is Global to PU
    name="KIDGenerator", 
    table="SEQUENCE", 
    pkColumnName="SEQ_NAME", 
    valueColumnName="SEQ_COUNT", 
    pkColumnValue="SEQ_GEN_TABLE", 
    allocationSize=1)    
public class systemMailClass 
extends KBusinessObjectClass
{

    
    // uses
    
    // has - defaulted
    @KNoGUIbinding
    public final static String           STATUS_NEW = "NEW";
    @KNoGUIbinding
    public final static String           STATUS_SENT = "SENT";
    @KNoGUIbinding
    public final static String           STATUS_ERROR = "ERROR";
    
    
    //airline information.
    @KID
    @Id 
    @GeneratedValue( strategy = javax.persistence.GenerationType.TABLE, generator="KIDGenerator" )        
    private long         mail_id; 
    
    private String       mail_from; 
    private String       mail_date;  
    private String       mail_recipient;  
    private String       mail_subject;          
    private String       mail_data;          
    private String       mail_data_type;              
    private String       mail_status;              
    private String       mail_status_description;                  
    private String       mail_error_count;                      
    

   
    /** Creates new aerolineaClass */
    public systemMailClass() throws KExceptionClass{
                                    
        // initialize fields
    
        mail_id = -1;     
        mail_from = "";  
        mail_date = "";  
        mail_recipient = "";  
        mail_subject = "";          
        mail_data = "";          
        mail_data_type = "";              
        mail_status = "";              
        mail_status_description = "";                  
        mail_error_count = "";    

        editable = false;
    }
    

    // =============================================================================
    
    public String getMail_data() {
        return mail_data;
    }

    public void setMail_data(String mail_data) {
        this.mail_data = mail_data;
    }

    public String getMail_date() {
        return mail_date;
    }

    public void setMail_date(String mail_date) {
        this.mail_date = mail_date;
    }

    public String getMail_data_type() {
        return mail_data_type;
    }

    public void setMail_data_type(String mail_data_type) {
        this.mail_data_type = mail_data_type;
    }

    public String getMail_error_count() {
        return mail_error_count;
    }

    public void setMail_error_count(String mail_error_count) {
        this.mail_error_count = mail_error_count;
    }

    public String getMail_from() {
        return mail_from;
    }

    public void setMail_from(String mail_from) {
        this.mail_from = mail_from;
    }

    public long getMail_id() {
        return mail_id;
    }

    public void setMail_id(long mail_id) {
        this.mail_id = mail_id;
    }

    public String getMail_subject() {
        return mail_subject;
    }

    public void setMail_subject(String mail_subject) {
        this.mail_subject = mail_subject;
    }

    public String getMail_recipient() {
        return mail_recipient;
    }

    public void setMail_recipient(String mail_recipient) {
        this.mail_recipient = mail_recipient;
    }

    public String getMail_status_description() {
        return mail_status_description;
    }

    public void setMail_status_description(String mail_status_description) {
        this.mail_status_description = mail_status_description;
    }

    public String getMail_status() {
        return mail_status;
    }

    public void setMail_status(String mail_status) {
        this.mail_status = mail_status;
    }

    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }
    
    
    
    
}
