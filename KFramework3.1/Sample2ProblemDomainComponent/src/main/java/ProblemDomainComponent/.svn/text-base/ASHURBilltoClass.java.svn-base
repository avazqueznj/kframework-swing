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
@Table(name = "ASHUR_BILLTO")
public class ASHURBilltoClass 
extends KBusinessObjectClass
{
    
    @KID
    @Id
    @Basic(optional = false)
    @Column(name = "BILLTO_ID")
    private Long billtoId;
    
    @Column(name = "BILLTO_NAME")
    private String billtoName;
    @Column(name = "BILLTO_ADDRESS1")
    private String billtoAddress1;
    @Column(name = "BILLTO_ADDRESS2")
    private String billtoAddress2;
    @Column(name = "BILLTO_CITY")
    private String billtoCity;
    @Column(name = "BILLTO_STATE")
    private String billtoState;
    @Column(name = "BILLTO_PROVINCE")
    private String billtoProvince;
    @Column(name = "BILLTO_ZIP")
    private String billtoZip;
    @Column(name = "BILLTO_COUNTRY")
    private String billtoCountry;
    @Column(name = "BILLTO_PHONE")
    private String billtoPhone;
    @Column(name = "BILLTO_FAX")
    private String billtoFax;
    @Column(name = "BILLTO_CARD_NUMBER")
    private String billtoCardNumber;
    @Column(name = "BILLTO_CARD_EXPIRATION")
    private String billtoCardExpiration;
    @Column(name = "BILLTO_CARD_OWNER")
    private String billtoCardOwner;
    @Column(name = "CLIENT_ID")
    private Long clientId;
    @Column(name = "BILLTO_NOTES")
    private String billtoNotes;

    public ASHURBilltoClass() throws KExceptionClass {
    }

//=================================================================    
    
    public Long getBilltoId() {
        return billtoId;
    }

    public void setBilltoId(Long billtoId) {
        this.billtoId = billtoId;
    }

    public String getBilltoName() {
        return billtoName;
    }

    public void setBilltoName(String billtoName) {
        this.billtoName = billtoName;
    }

    public String getBilltoAddress1() {
        return billtoAddress1;
    }

    public void setBilltoAddress1(String billtoAddress1) {
        this.billtoAddress1 = billtoAddress1;
    }

    public String getBilltoAddress2() {
        return billtoAddress2;
    }

    public void setBilltoAddress2(String billtoAddress2) {
        this.billtoAddress2 = billtoAddress2;
    }

    public String getBilltoCity() {
        return billtoCity;
    }

    public void setBilltoCity(String billtoCity) {
        this.billtoCity = billtoCity;
    }

    public String getBilltoState() {
        return billtoState;
    }

    public void setBilltoState(String billtoState) {
        this.billtoState = billtoState;
    }

    public String getBilltoProvince() {
        return billtoProvince;
    }

    public void setBilltoProvince(String billtoProvince) {
        this.billtoProvince = billtoProvince;
    }

    public String getBilltoZip() {
        return billtoZip;
    }

    public void setBilltoZip(String billtoZip) {
        this.billtoZip = billtoZip;
    }

    public String getBilltoCountry() {
        return billtoCountry;
    }

    public void setBilltoCountry(String billtoCountry) {
        this.billtoCountry = billtoCountry;
    }

    public String getBilltoPhone() {
        return billtoPhone;
    }

    public void setBilltoPhone(String billtoPhone) {
        this.billtoPhone = billtoPhone;
    }

    public String getBilltoFax() {
        return billtoFax;
    }

    public void setBilltoFax(String billtoFax) {
        this.billtoFax = billtoFax;
    }

    public String getBilltoCardNumber() {
        return billtoCardNumber;
    }

    public void setBilltoCardNumber(String billtoCardNumber) {
        this.billtoCardNumber = billtoCardNumber;
    }

    public String getBilltoCardExpiration() {
        return billtoCardExpiration;
    }

    public void setBilltoCardExpiration(String billtoCardExpiration) {
        this.billtoCardExpiration = billtoCardExpiration;
    }

    public String getBilltoCardOwner() {
        return billtoCardOwner;
    }

    public void setBilltoCardOwner(String billtoCardOwner) {
        this.billtoCardOwner = billtoCardOwner;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getBilltoNotes() {
        return billtoNotes;
    }

    public void setBilltoNotes(String billtoNotes) {
        this.billtoNotes = billtoNotes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billtoId != null ? billtoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ASHURBilltoClass)) {
            return false;
        }
        ASHURBilltoClass other = (ASHURBilltoClass) object;
        if ((this.billtoId == null && other.billtoId != null) || (this.billtoId != null && !this.billtoId.equals(other.billtoId))) {
            return false;
        }
        return true;
    }

    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }


}
