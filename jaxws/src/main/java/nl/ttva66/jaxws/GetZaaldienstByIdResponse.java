
package nl.ttva66.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import nl.ttva66.dto.ZaaldienstDto;


/**
 * <p>Java class for getZaaldienstByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getZaaldienstByIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.ttva66.nl/wsdl}zaaldienstDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getZaaldienstByIdResponse", propOrder = {
    "_return"
})
public class GetZaaldienstByIdResponse {

    @XmlElement(name = "return")
    protected ZaaldienstDto _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link ZaaldienstDto }
     *     
     */
    public ZaaldienstDto getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaaldienstDto }
     *     
     */
    public void setReturn(ZaaldienstDto value) {
        this._return = value;
    }

}
