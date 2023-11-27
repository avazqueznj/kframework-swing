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
import KFramework30.Base.KBusinessObjectClass.KID;
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
@Table(name = "SAMPLE_ITEM")
public class sample_itemClass 
extends KBusinessObjectClass        
 {
    
    
    @Id
    @KID
    @Basic(optional = false)
    @GeneratedValue( strategy = javax.persistence.GenerationType.TABLE, generator="KIDGenerator" )    
    @Column(name = "ITEM_ID")
    private long itemId;
    
    
    @Column(name = "ITEM_QUANTITY")
    private double itemQuantity;
    
    @Column(name = "ITEM_COST")
    private double itemCost;
    
    @Column(name = "FAC_ID")
    private long facId;
    
    @Column(name = "PROD_ID")
    private long prodId;
    
    @KObjectVersion
    @Column(name = "version")
    private long version;    

    public sample_itemClass()  
    throws KExceptionClass
    {
        
           // initialize fields
         itemId = -1;            
         itemQuantity = 1;
         facId = -1;
         prodId = -1;   
         
        //set max size
        fieldTypes.put( "itemQuantity" ,NUMERIC2_TYPE  );                
        fieldTypes.put( "itemCost" , CURRENCY_TYPE  );                
        fieldMaxSize.put( "item_description" ,30  );
                
        //readOnlyFields        
    
        editable = true;        
    }

    @Override
    public void materializeFromDisplay(Container visualDisplay, List extraList, int missingFieldsBehavior) throws KExceptionClass {
                       
        super.materializeFromDisplay(visualDisplay, extraList, missingFieldsBehavior);
        
        if( itemQuantity <= 0 ) throw new KExceptionClass("Product Quantity must be assigned", null);                
                
    }
    
    public long getFacId() {
        return facId;
    }

    public void setFacId(long facId) {
        this.facId = facId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public double getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(double itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long productId) {
        this.prodId = productId;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
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
