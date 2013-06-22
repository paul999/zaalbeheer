
package nl.ttva66.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="diensts" type="{http://www.ttva66.nl/wsdl}dienst" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eind" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="naam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opens" type="{http://www.ttva66.nl/wsdl}open" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type", propOrder = {
    "diensts",
    "eind",
    "id",
    "naam",
    "opens",
    "start"
})
public class Type {

    @XmlElement(nillable = true)
    protected List<Dienst> diensts;
    protected int eind;
    protected Integer id;
    protected String naam;
    @XmlElement(nillable = true)
    protected List<Open> opens;
    protected int start;

    /**
     * Gets the value of the diensts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the diensts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiensts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dienst }
     * 
     * 
     */
    public List<Dienst> getDiensts() {
        if (diensts == null) {
            diensts = new ArrayList<Dienst>();
        }
        return this.diensts;
    }

    /**
     * Gets the value of the eind property.
     * 
     */
    public int getEind() {
        return eind;
    }

    /**
     * Sets the value of the eind property.
     * 
     */
    public void setEind(int value) {
        this.eind = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the naam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Sets the value of the naam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaam(String value) {
        this.naam = value;
    }

    /**
     * Gets the value of the opens property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opens property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpens().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Open }
     * 
     * 
     */
    public List<Open> getOpens() {
        if (opens == null) {
            opens = new ArrayList<Open>();
        }
        return this.opens;
    }

    /**
     * Gets the value of the start property.
     * 
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     */
    public void setStart(int value) {
        this.start = value;
    }

}
