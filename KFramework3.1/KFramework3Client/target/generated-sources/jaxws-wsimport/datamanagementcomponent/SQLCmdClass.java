
package datamanagementcomponent;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SQLCmdClass complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SQLCmdClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="session_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SQL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="start_row" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="lengh" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="params" type="{http://DataManagementComponent/}FieldClass" maxOccurs="unbounded"/>
 *         &lt;element name="paramTypes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SQLCmdClass", propOrder = {
    "sessionId",
    "sql",
    "startRow",
    "lengh",
    "params",
    "paramTypes"
})
public class SQLCmdClass {

    @XmlElement(name = "session_id", required = true)
    protected String sessionId;
    @XmlElement(name = "SQL", required = true)
    protected String sql;
    @XmlElement(name = "start_row")
    protected long startRow;
    protected int lengh;
    @XmlElement(required = true)
    protected List<FieldClass> params;
    @XmlElement(required = true)
    protected List<String> paramTypes;

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the sql property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSQL() {
        return sql;
    }

    /**
     * Sets the value of the sql property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSQL(String value) {
        this.sql = value;
    }

    /**
     * Gets the value of the startRow property.
     * 
     */
    public long getStartRow() {
        return startRow;
    }

    /**
     * Sets the value of the startRow property.
     * 
     */
    public void setStartRow(long value) {
        this.startRow = value;
    }

    /**
     * Gets the value of the lengh property.
     * 
     */
    public int getLengh() {
        return lengh;
    }

    /**
     * Sets the value of the lengh property.
     * 
     */
    public void setLengh(int value) {
        this.lengh = value;
    }

    /**
     * Gets the value of the params property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the params property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldClass }
     * 
     * 
     */
    public List<FieldClass> getParams() {
        if (params == null) {
            params = new ArrayList<FieldClass>();
        }
        return this.params;
    }

    /**
     * Gets the value of the paramTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paramTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParamTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getParamTypes() {
        if (paramTypes == null) {
            paramTypes = new ArrayList<String>();
        }
        return this.paramTypes;
    }

}
