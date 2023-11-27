
package datamanagementcomponent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObjectMessageClass complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ObjectMessageClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="protocolversion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="session_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="objClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="objID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="XMLSerialization" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectMessageClass", propOrder = {
    "protocolversion",
    "status",
    "desc",
    "sessionId",
    "objClass",
    "objID",
    "action",
    "xmlSerialization",
    "tag"
})
public class ObjectMessageClass {

    @XmlElement(required = true)
    protected String protocolversion;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String desc;
    @XmlElement(name = "session_id", required = true)
    protected String sessionId;
    @XmlElement(required = true)
    protected String objClass;
    protected long objID;
    @XmlElement(required = true)
    protected String action;
    @XmlElement(name = "XMLSerialization", required = true)
    protected String xmlSerialization;
    @XmlElement(name = "TAG", required = true)
    protected String tag;

    /**
     * Gets the value of the protocolversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolversion() {
        return protocolversion;
    }

    /**
     * Sets the value of the protocolversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolversion(String value) {
        this.protocolversion = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

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
     * Gets the value of the objClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjClass() {
        return objClass;
    }

    /**
     * Sets the value of the objClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjClass(String value) {
        this.objClass = value;
    }

    /**
     * Gets the value of the objID property.
     * 
     */
    public long getObjID() {
        return objID;
    }

    /**
     * Sets the value of the objID property.
     * 
     */
    public void setObjID(long value) {
        this.objID = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the xmlSerialization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLSerialization() {
        return xmlSerialization;
    }

    /**
     * Sets the value of the xmlSerialization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLSerialization(String value) {
        this.xmlSerialization = value;
    }

    /**
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTAG() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTAG(String value) {
        this.tag = value;
    }

}
