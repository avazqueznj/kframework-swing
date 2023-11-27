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

import KFramework30.Base.KBusinessObjectClass;
import KFramework30.Base.KBusinessObjectClass.KID;
import KFramework30.Base.KExceptionClass;
import java.awt.Component;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author avazquez
 */
@Entity
@TableGenerator( // SCOPE is Global to PU
    name="KIDGenerator", 
    table="SEQUENCE", 
    pkColumnName="SEQ_NAME", 
    valueColumnName="SEQ_COUNT", 
    pkColumnValue="SEQ_GEN_TABLE", 
    allocationSize=1)    
@Table(name = "SAMPLE_FACTURA")
public class sample_facturaClass 
extends KBusinessObjectClass
 {
    
    
    @Id
    @KID
    @GeneratedValue( strategy = javax.persistence.GenerationType.TABLE, generator="KIDGenerator" )    
    @Column(name = "FAC_ID")
    private long facId;
    
    @Column(name = "FAC_NAME")
    private String facName;
    
    @Column(name = "FAC_TOTAL")
    private double facTotal;
    
    @Column(name = "CLIENT_ID")
    private long clientId;
    
    @Column(name = "FAC_DATE")
    @Temporal(TemporalType.TIMESTAMP)   
    private Date facDate;
    
    @Column(name = "FACSTATUS_ID")
    private long facstatusId;
    
    @Column(name = "FAC_TERM")
    private String facTerm;
    
    @KObjectVersion
    @Column(name = "version")
    private long version;
    


    public sample_facturaClass()
    throws KExceptionClass
    {
        
        // initialize has
        facName = "";
        facTotal = 0L;
        
        fieldMaxSize.put( "facName", 200 );
                
        fieldTypes.put( "facTotal", CURRENCY_TYPE );
        fieldTypes.put( "clientId", NUMERIC_NOFORMAT );
        
        readOnlyFields.add("facTotal");
        
        // let others be set by server in create new
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public Date getFacDate() {
        return facDate;
    }

    public void setFacDate(Date facDate) {
        this.facDate = facDate;
    }

    public long getFacId() {
        return facId;
    }

    public void setFacId(long facId) {
        this.facId = facId;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getFacTerm() {
        return facTerm;
    }

    public void setFacTerm(String facTerm) {
        this.facTerm = facTerm;
    }

    public double getFacTotal() {
        return facTotal;
    }

    public void setFacTotal(double facTotal) {
        this.facTotal = facTotal;
    }

    public long getFacstatusId() {
        return facstatusId;
    }

    public void setFacstatusId(long facstatusId) {
        this.facstatusId = facstatusId;
    }



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
