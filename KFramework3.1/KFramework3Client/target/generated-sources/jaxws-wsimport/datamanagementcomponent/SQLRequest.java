
package datamanagementcomponent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SQLRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SQLRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReceivedCmd" type="{http://DataManagementComponent/}SQLCmdClass" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SQLRequest", propOrder = {
    "receivedCmd"
})
public class SQLRequest {

    @XmlElement(name = "ReceivedCmd")
    protected SQLCmdClass receivedCmd;

    /**
     * Gets the value of the receivedCmd property.
     * 
     * @return
     *     possible object is
     *     {@link SQLCmdClass }
     *     
     */
    public SQLCmdClass getReceivedCmd() {
        return receivedCmd;
    }

    /**
     * Sets the value of the receivedCmd property.
     * 
     * @param value
     *     allowed object is
     *     {@link SQLCmdClass }
     *     
     */
    public void setReceivedCmd(SQLCmdClass value) {
        this.receivedCmd = value;
    }

}
