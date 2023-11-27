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
@Table(name = "ASHUR_PROJECT")
public class ASHURProjectClass 
extends KBusinessObjectClass
 {
    
    @KID
    @Id
    @Basic(optional = false)
    @Column(name = "PROJECT_ID")
    private Long projectId;
    
    @Column(name = "PROJECT_NAME")
    private String projectName;
    @Column(name = "PROJECT_CLIENT_PROJECT_NUMBER")
    private String projectClientProjectNumber;
    @Column(name = "PROJECT_STATUS")
    private String projectStatus;
    @Column(name = "PROJECT_DESCRIPTION")
    private String projectDescription;
    @Column(name = "PROJECT_PO_NUMBER")
    private String projectPoNumber;
    @Column(name = "PROJECT_INVOICE_FREQUENCY")
    private String projectInvoiceFrequency;
    @Column(name = "CLIENT_ID")
    private Long clientId;
    @Column(name = "PROJECT_LAB_ID")
    private String projectLabId;
    @Column(name = "PROJECT_NOTES")
    private String projectNotes;

    public ASHURProjectClass()throws KExceptionClass {
    }

//=================================================================    

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectClientProjectNumber() {
        return projectClientProjectNumber;
    }

    public void setProjectClientProjectNumber(String projectClientProjectNumber) {
        this.projectClientProjectNumber = projectClientProjectNumber;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectPoNumber() {
        return projectPoNumber;
    }

    public void setProjectPoNumber(String projectPoNumber) {
        this.projectPoNumber = projectPoNumber;
    }

    public String getProjectInvoiceFrequency() {
        return projectInvoiceFrequency;
    }

    public void setProjectInvoiceFrequency(String projectInvoiceFrequency) {
        this.projectInvoiceFrequency = projectInvoiceFrequency;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getProjectLabId() {
        return projectLabId;
    }

    public void setProjectLabId(String projectLabId) {
        this.projectLabId = projectLabId;
    }

    public String getProjectNotes() {
        return projectNotes;
    }

    public void setProjectNotes(String projectNotes) {
        this.projectNotes = projectNotes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ASHURProjectClass)) {
            return false;
        }
        ASHURProjectClass other = (ASHURProjectClass) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }


}
