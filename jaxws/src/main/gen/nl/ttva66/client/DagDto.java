
package nl.ttva66.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dagDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dagDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dienst" type="{http://www.ttva66.nl/wsdl}dienstDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="jaar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maand" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="opens" type="{http://www.ttva66.nl/wsdl}openDto" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "dagDto", propOrder = {
    "dag",
    "dienst",
    "id",
    "jaar",
    "maand",
    "opens",
    "opmerkingen",
    "team"
})
public class DagDto {

    protected int dag;
    @XmlElement(nillable = true)
    protected List<DienstDto> dienst;
    protected Integer id;
    protected int jaar;
    protected int maand;
    @XmlElement(nillable = true)
    protected List<OpenDto> opens;
    protected String opmerkingen;
    protected String team;

    /**
     * Gets the value of the dag property.
     * 
     */
    public int getDag() {
        return dag;
    }

    /**
     * Sets the value of the dag property.
     * 
     */
    public void setDag(int value) {
        this.dag = value;
    }

    /**
     * Gets the value of the dienst property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dienst property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDienst().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DienstDto }
     * 
     * 
     */
    public List<DienstDto> getDienst() {
        if (dienst == null) {
            dienst = new ArrayList<DienstDto>();
        }
        return this.dienst;
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
     * Gets the value of the jaar property.
     * 
     */
    public int getJaar() {
        return jaar;
    }

    /**
     * Sets the value of the jaar property.
     * 
     */
    public void setJaar(int value) {
        this.jaar = value;
    }

    /**
     * Gets the value of the maand property.
     * 
     */
    public int getMaand() {
        return maand;
    }

    /**
     * Sets the value of the maand property.
     * 
     */
    public void setMaand(int value) {
        this.maand = value;
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
     * {@link OpenDto }
     * 
     * 
     */
    public List<OpenDto> getOpens() {
        if (opens == null) {
            opens = new ArrayList<OpenDto>();
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
