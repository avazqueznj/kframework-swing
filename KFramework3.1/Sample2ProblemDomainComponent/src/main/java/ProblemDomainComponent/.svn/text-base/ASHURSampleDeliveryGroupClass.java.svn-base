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
import KFramework30.Base.*;

import java.awt.Component;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "ASHUR_SAMPLEDELIVERYGROUP")
public class ASHURSampleDeliveryGroupClass 
extends KBusinessObjectClass
{

    @KID
    @Id
    @Basic(optional = false)
    @Column(name = "SDG_ID")
    private Long sdgId;
    
    @Column(name = "SDG_LAB_ID")
    private String sdgLabId;
    @Column(name = "SDG_DESCRIPTION")
    private String sdgDescription;
    @Column(name = "PROJECT_ID")
    private Long projectId;
    @Column(name = "BILLTO_ID")
    private Long billtoId;
    @Column(name = "SDG_RECEIVEDBY")
    private String sdgReceivedby;
    @Column(name = "SDG_RECEIVED_DATE")
    @Temporal(TemporalType.DATE)
    private Date sdgReceivedDate;
    @Column(name = "SDG_RECEIVED_TIME")
    @Temporal(TemporalType.DATE)
    private Date sdgReceivedTime;
    @Column(name = "SDG_DELIVERY_METHOD")
    private String sdgDeliveryMethod;
    @Column(name = "SDG_HAZARDOUS")
    private String sdgHazardous;
    @Column(name = "SDG_STATUS")
    private String sdgStatus;
    @Column(name = "SDG_TAT_DATE")
    @Temporal(TemporalType.DATE)
    private Date sdgTatDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SDG_SAMPLE_TOTAL")
    private BigDecimal sdgSampleTotal;
    @Column(name = "SDG_TAT_MULTIPLIER")
    private BigDecimal sdgTatMultiplier;
    @Column(name = "SDG_PRICE")
    private BigDecimal sdgPrice;
    @Column(name = "SDG_USER_DISCOUNT")
    private BigDecimal sdgUserDiscount;
    @Column(name = "SDG_USER_PRICE")
    private BigDecimal sdgUserPrice;
    @Column(name = "SDG_COMMENTS")
    private String sdgComments;
    @Column(name = "SDG_LAB_COMMENTS")
    private String sdgLabComments;
    @Column(name = "SDG_PRINTED_DATE")
    @Temporal(TemporalType.DATE)
    private Date sdgPrintedDate;
    @Column(name = "SDG_INVOICED_DATE")
    @Temporal(TemporalType.DATE)
    private Date sdgInvoicedDate;
    @Column(name = "SDG_MAILED_DATE")
    @Temporal(TemporalType.DATE)
    private Date sdgMailedDate;

    public ASHURSampleDeliveryGroupClass() 
    throws KExceptionClass{
    }


// =============================================================    
    
    public Long getSdgId() {
        return sdgId;
    }

    public void setSdgId(Long sdgId) {
        this.sdgId = sdgId;
    }

    public String getSdgLabId() {
        return sdgLabId;
    }

    public void setSdgLabId(String sdgLabId) {
        this.sdgLabId = sdgLabId;
    }

    public String getSdgDescription() {
        return sdgDescription;
    }

    public void setSdgDescription(String sdgDescription) {
        this.sdgDescription = sdgDescription;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getBilltoId() {
        return billtoId;
    }

    public void setBilltoId(Long billtoId) {
        this.billtoId = billtoId;
    }

    public String getSdgReceivedby() {
        return sdgReceivedby;
    }

    public void setSdgReceivedby(String sdgReceivedby) {
        this.sdgReceivedby = sdgReceivedby;
    }

    public Date getSdgReceivedDate() {
        return sdgReceivedDate;
    }

    public void setSdgReceivedDate(Date sdgReceivedDate) {
        this.sdgReceivedDate = sdgReceivedDate;
    }

    public Date getSdgReceivedTime() {
        return sdgReceivedTime;
    }

    public void setSdgReceivedTime(Date sdgReceivedTime) {
        this.sdgReceivedTime = sdgReceivedTime;
    }

    public String getSdgDeliveryMethod() {
        return sdgDeliveryMethod;
    }

    public void setSdgDeliveryMethod(String sdgDeliveryMethod) {
        this.sdgDeliveryMethod = sdgDeliveryMethod;
    }

    public String getSdgHazardous() {
        return sdgHazardous;
    }

    public void setSdgHazardous(String sdgHazardous) {
        this.sdgHazardous = sdgHazardous;
    }

    public String getSdgStatus() {
        return sdgStatus;
    }

    public void setSdgStatus(String sdgStatus) {
        this.sdgStatus = sdgStatus;
    }

    public Date getSdgTatDate() {
        return sdgTatDate;
    }

    public void setSdgTatDate(Date sdgTatDate) {
        this.sdgTatDate = sdgTatDate;
    }

    public BigDecimal getSdgSampleTotal() {
        return sdgSampleTotal;
    }

    public void setSdgSampleTotal(BigDecimal sdgSampleTotal) {
        this.sdgSampleTotal = sdgSampleTotal;
    }

    public BigDecimal getSdgTatMultiplier() {
        return sdgTatMultiplier;
    }

    public void setSdgTatMultiplier(BigDecimal sdgTatMultiplier) {
        this.sdgTatMultiplier = sdgTatMultiplier;
    }

    public BigDecimal getSdgPrice() {
        return sdgPrice;
    }

    public void setSdgPrice(BigDecimal sdgPrice) {
        this.sdgPrice = sdgPrice;
    }

    public BigDecimal getSdgUserDiscount() {
        return sdgUserDiscount;
    }

    public void setSdgUserDiscount(BigDecimal sdgUserDiscount) {
        this.sdgUserDiscount = sdgUserDiscount;
    }

    public BigDecimal getSdgUserPrice() {
        return sdgUserPrice;
    }

    public void setSdgUserPrice(BigDecimal sdgUserPrice) {
        this.sdgUserPrice = sdgUserPrice;
    }

    public String getSdgComments() {
        return sdgComments;
    }

    public void setSdgComments(String sdgComments) {
        this.sdgComments = sdgComments;
    }

    public String getSdgLabComments() {
        return sdgLabComments;
    }

    public void setSdgLabComments(String sdgLabComments) {
        this.sdgLabComments = sdgLabComments;
    }

    public Date getSdgPrintedDate() {
        return sdgPrintedDate;
    }

    public void setSdgPrintedDate(Date sdgPrintedDate) {
        this.sdgPrintedDate = sdgPrintedDate;
    }

    public Date getSdgInvoicedDate() {
        return sdgInvoicedDate;
    }

    public void setSdgInvoicedDate(Date sdgInvoicedDate) {
        this.sdgInvoicedDate = sdgInvoicedDate;
    }

    public Date getSdgMailedDate() {
        return sdgMailedDate;
    }

    public void setSdgMailedDate(Date sdgMailedDate) {
        this.sdgMailedDate = sdgMailedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sdgId != null ? sdgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ASHURSampleDeliveryGroupClass)) {
            return false;
        }
        ASHURSampleDeliveryGroupClass other = (ASHURSampleDeliveryGroupClass) object;
        if ((this.sdgId == null && other.sdgId != null) || (this.sdgId != null && !this.sdgId.equals(other.sdgId))) {
            return false;
        }
        return true;
    }


    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }

}
