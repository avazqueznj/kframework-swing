/*
* This source code is part of the KFRAMEWORK  (http://k-framework.sourceforge.net/)
* Copyright (C) 2001  Alejandro Vazquez, Ke Li
* Feedback / Bug Reports vmaxxed@users.sourceforge.net
* 
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
* 
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package ProblemDomainComponent;

import KFramework30.Base.KBusinessObjectClass;
import KFramework30.Base.KExceptionClass;
import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "SAMPLE_PRODUCT")
public class sample_productClass 
extends KBusinessObjectClass
 {
    
        
    @Id
    @KID    
    @Basic(optional = false)
    @GeneratedValue( strategy = javax.persistence.GenerationType.TABLE, generator="KIDGenerator" )    
    @Column(name = "PROD_ID")
    private long prodId;
    
    @Column(name = "PROD_NAME")
    private String prodName;
    
    @Column(name = "PROD_COST")
    private double prodCost;
    
    @KObjectVersion
    @Column(name = "version")
    private long version;    
    
    @Lob
    @Column(name = "PROD_PICTURE")
    private byte[] prodPicture;    

// ==============================================================================    
            
    @Override
    public void materializeFromDisplay(Container visualDisplay, List extraList, int missingFieldsBehavior) throws KExceptionClass {
                
        super.materializeFromDisplay(visualDisplay, extraList, missingFieldsBehavior);
        
        if( prodCost <= 0 ) throw new KExceptionClass( "Product Cost must be assigned", null);
    }    
    
// ==============================================================================    

    public sample_productClass()
    throws KExceptionClass
    {
        fieldTypes.put( "prodCost", CURRENCY_TYPE );
        
    }
        
// ==============================================================================            
        
    public double getProdCost() {
        return prodCost;
    }

    public void setProdCost(double prodCost) {
        this.prodCost = prodCost;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    
   
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public byte[] getProdPicture() {
        return prodPicture;
    }

    public void setProdPicture(byte[] prodPicture) {
        this.prodPicture = prodPicture;
    }

    
    
    
    
    @Override
    public void validateInput(String currentField, Component currentComponent) throws KExceptionClass {
    }

    
}
