
package nl.ttva66.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import nl.ttva66.interfaces.ZaalDienstRequest;


/**
 * <p>Java class for getZaaldienstById complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getZaaldienstById">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.ttva66.nl/wsdl}zaalDienstRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getZaaldienstById", propOrder = {
    "arg0"
})
public class GetZaaldienstById {

    protected ZaalDienstRequest arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link ZaalDienstRequest }
     *     
     */
    public ZaalDienstRequest getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaalDienstRequest }
     *     
     */
    public void setArg0(ZaalDienstRequest value) {
        this.arg0 = value;
    }

}
