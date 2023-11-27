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

import KFramework30.Base.KBusinessObjectClass.KID;
import KFramework30.Base.KExceptionClass;
import java.awt.Component;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@TableGenerator( // SCOPE is Global to PU
    name="KIDGenerator", 
    table="SEQUENCE", 
    pkColumnName="SEQ_NAME", 
    valueColumnName="SEQ_COUNT", 
    pkColumnValue="SEQ_GEN_TABLE", 
    allocationSize=1)    
@Table(name = "SAMPLE_FACTURA_STATUS")
public class sample_factura_statusClass 
extends KFramework30.Base.KBusinessObjectClass
 {
    
    @KID
    @Id
    @GeneratedValue( strategy = javax.persistence.GenerationType.TABLE, generator="KIDGenerator" )    
    @Column(name = "FACSTATUS_ID")
    private long facstatusId;
    @Column(name = "FACSTATUS_STATUS")
    private String facstatusStatus;
    
    @KObjectVersion
    @Column(name = "version")
    private long version;
    
    public sample_factura_statusClass() 
    throws KExceptionClass{        
    };
    
    // getter setter -------------------------------------------------------------------        
    
    public long getFacstatusId() { return facstatusId;}
    public void setFacstatusId(long facstatusId) { this.facstatusId = facstatusId;}
    
    public String getFacstatusStatus() {return facstatusStatus;}
    public void setFacstatusStatus(String facstatusStatus) {this.facstatusStatus = facstatusStatus;}

        
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }
    

}
