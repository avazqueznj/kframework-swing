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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "ASHUR_CLIENTS")
public class ASHURClientClass 
extends KBusinessObjectClass
{
    
    @KID
    @Id
    @Basic(optional = false)
    @Column(name = "CLIENT_ID")
    private Long clientId;
    
    @Column(name = "CLIENT_NAME")
    private String clientName;
    @Column(name = "CLIENT_ADDRESS1")
    private String clientAddress1;
    @Column(name = "CLIENT_ADDRESS2")
    private String clientAddress2;
    @Column(name = "CLIENT_CITY")
    private String clientCity;
    @Column(name = "CLIENT_STATE")
    private String clientState;
    @Column(name = "CLIENT_PROVINCE")
    private String clientProvince;
    @Column(name = "CLIENT_ZIP")
    private String clientZip;
    @Column(name = "CLIENT_COUNTRY")
    private String clientCountry;
    @Column(name = "CLIENT_PHONE")
    private String clientPhone;
    @Column(name = "CLIENT_FAX")
    private String clientFax;
    @Column(name = "CLIENT_CONTACT_NAME")
    private String clientContactName;
    @Column(name = "CLIENT_CONTACT_DEPARTMENT")
    private String clientContactDepartment;
    @Column(name = "CLIENT_CONTACT_PHONE")
    private String clientContactPhone;
    @Column(name = "CLIENT_CONTACT_FAX")
    private String clientContactFax;
    @Column(name = "CLIENT_CONTACT_EMAIL")
    private String clientContactEmail;
    @Column(name = "CLIENT_TYPE_ID")
    private Long clientTypeId;
    @Column(name = "CLIENT_LAB_ID")
    private String clientLabId;
    @Column(name = "CLIENT_NOTES")
    private String clientNotes;

    public ASHURClientClass() throws KExceptionClass {
    }

//=================================================================    

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress1() {
        return clientAddress1;
    }

    public void setClientAddress1(String clientAddress1) {
        this.clientAddress1 = clientAddress1;
    }

    public String getClientAddress2() {
        return clientAddress2;
    }

    public void setClientAddress2(String clientAddress2) {
        this.clientAddress2 = clientAddress2;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public String getClientProvince() {
        return clientProvince;
    }

    public void setClientProvince(String clientProvince) {
        this.clientProvince = clientProvince;
    }

    public String getClientZip() {
        return clientZip;
    }

    public void setClientZip(String clientZip) {
        this.clientZip = clientZip;
    }

    public String getClientCountry() {
        return clientCountry;
    }

    public void setClientCountry(String clientCountry) {
        this.clientCountry = clientCountry;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientFax() {
        return clientFax;
    }

    public void setClientFax(String clientFax) {
        this.clientFax = clientFax;
    }

    public String getClientContactName() {
        return clientContactName;
    }

    public void setClientContactName(String clientContactName) {
        this.clientContactName = clientContactName;
    }

    public String getClientContactDepartment() {
        return clientContactDepartment;
    }

    public void setClientContactDepartment(String clientContactDepartment) {
        this.clientContactDepartment = clientContactDepartment;
    }

    public String getClientContactPhone() {
        return clientContactPhone;
    }

    public void setClientContactPhone(String clientContactPhone) {
        this.clientContactPhone = clientContactPhone;
    }

    public String getClientContactFax() {
        return clientContactFax;
    }

    public void setClientContactFax(String clientContactFax) {
        this.clientContactFax = clientContactFax;
    }

    public String getClientContactEmail() {
        return clientContactEmail;
    }

    public void setClientContactEmail(String clientContactEmail) {
        this.clientContactEmail = clientContactEmail;
    }

    public Long getClientTypeId() {
        return clientTypeId;
    }

    public void setClientTypeId(Long clientTypeId) {
        this.clientTypeId = clientTypeId;
    }

    public String getClientLabId() {
        return clientLabId;
    }

    public void setClientLabId(String clientLabId) {
        this.clientLabId = clientLabId;
    }

    public String getClientNotes() {
        return clientNotes;
    }

    public void setClientNotes(String clientNotes) {
        this.clientNotes = clientNotes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ASHURClientClass)) {
            return false;
        }
        ASHURClientClass other = (ASHURClientClass) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }
    

}
