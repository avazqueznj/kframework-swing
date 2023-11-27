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
import KFramework30.Base.KExceptionClass;
import java.awt.Component;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "SAMPLE_CLIENT")
public class sample_clientClass 
extends KBusinessObjectClass 
{
    
    @KID
    @Id   
    @GeneratedValue( strategy = javax.persistence.GenerationType.TABLE, generator="KIDGenerator" )    
    @Column(name = "CLIENT_ID")
    private long clientId;
    
    
    @Column(name = "CLIENT_NAME")
    private String clientName;
    
    @Column(name = "CLIENT_ADDRESS")
    private String clientAddress;
    
    @Column(name = "CLIENT_EXPRESS_DELIVERY")
    private String clientExpressDelivery;    

    @Column(name = "CLIENT_DISCOUNT")
    private String clientDiscount;
    
    @KObjectVersion
    @Column(name = "version")
    private long version;
    
    public sample_clientClass() throws KExceptionClass {
    }


    
    //---------------------------------------------------------------------------

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;                
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }    
    
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    
    public String getClientExpressDelivery() {
        return clientExpressDelivery;
    }

    public void setClientExpressDelivery(String clientExpressDelivery) {
        this.clientExpressDelivery = clientExpressDelivery;
    }    
    
    public String getClientDiscount() {
        return clientDiscount;
    }

    public void setClientDiscount(String clientDiscount) {
        this.clientDiscount = clientDiscount;
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
