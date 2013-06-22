
package nl.ttva66.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dag complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dag">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="diensts" type="{http://www.ttva66.nl/wsdl}dienst" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="opens" type="{http://www.ttva66.nl/wsdl}open" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="opmerkingen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="team" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dag", propOrder = {
    "datum",
    "diensts",
    "id",
    "opens",
    "opmerkingen",
    "team"
})
public class Dag {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datum;
    @XmlElement(nillable = true)
    protected List<Dienst> diensts;
    protected Integer id;
    @XmlElement(nillable = true)
    protected List<Open> opens;
    protected String opmerkingen;
    protected String team;

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

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
     * Gets the value of the opmerkingen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpmerkingen() {
        return opmerkingen;
    }

    /**
     * Sets the value of the opmerkingen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpmerkingen(String value) {
        this.opmerkingen = value;
    }

    /**
     * Gets the value of the team property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeam() {
        return team;
    }

    /**
     * Sets the value of the team property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeam(String value) {
        this.team = value;
    }

}
