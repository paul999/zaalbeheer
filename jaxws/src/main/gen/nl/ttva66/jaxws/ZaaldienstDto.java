
package nl.ttva66.jaxws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zaaldienstDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zaaldienstDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aantal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="canlogin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="diensts" type="{http://www.ttva66.nl/wsdl}dienstDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dinsdag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="donderdag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maandag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="naam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vrijdag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="woensdag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="zaterdag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="zondag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zaaldienstDto", propOrder = {
    "aantal",
    "canlogin",
    "diensts",
    "dinsdag",
    "donderdag",
    "email",
    "id",
    "maandag",
    "naam",
    "password",
    "vrijdag",
    "woensdag",
    "zaterdag",
    "zondag"
})
public class ZaaldienstDto {

    protected int aantal;
    protected boolean canlogin;
    @XmlElement(nillable = true)
    protected List<DienstDto> diensts;
    protected boolean dinsdag;
    protected boolean donderdag;
    protected String email;
    protected Integer id;
    protected boolean maandag;
    protected String naam;
    protected String password;
    protected boolean vrijdag;
    protected boolean woensdag;
    protected boolean zaterdag;
    protected boolean zondag;

    /**
     * Gets the value of the aantal property.
     * 
     */
    public int getAantal() {
        return aantal;
    }

    /**
     * Sets the value of the aantal property.
     * 
     */
    public void setAantal(int value) {
        this.aantal = value;
    }

    /**
     * Gets the value of the canlogin property.
     * 
     */
    public boolean isCanlogin() {
        return canlogin;
    }

    /**
     * Sets the value of the canlogin property.
     * 
     */
    public void setCanlogin(boolean value) {
        this.canlogin = value;
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
     * {@link DienstDto }
     * 
     * 
     */
    public List<DienstDto> getDiensts() {
        if (diensts == null) {
            diensts = new ArrayList<DienstDto>();
        }
        return this.diensts;
    }

    /**
     * Gets the value of the dinsdag property.
     * 
     */
    public boolean isDinsdag() {
        return dinsdag;
    }

    /**
     * Sets the value of the dinsdag property.
     * 
     */
    public void setDinsdag(boolean value) {
        this.dinsdag = value;
    }

    /**
     * Gets the value of the donderdag property.
     * 
     */
    public boolean isDonderdag() {
        return donderdag;
    }

    /**
     * Sets the value of the donderdag property.
     * 
     */
    public void setDonderdag(boolean value) {
        this.donderdag = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
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
     * Gets the value of the maandag property.
     * 
     */
    public boolean isMaandag() {
        return maandag;
    }

    /**
     * Sets the value of the maandag property.
     * 
     */
    public void setMaandag(boolean value) {
        this.maandag = value;
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
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the vrijdag property.
     * 
     */
    public boolean isVrijdag() {
        return vrijdag;
    }

    /**
     * Sets the value of the vrijdag property.
     * 
     */
    public void setVrijdag(boolean value) {
        this.vrijdag = value;
    }

    /**
     * Gets the value of the woensdag property.
     * 
     */
    public boolean isWoensdag() {
        return woensdag;
    }

    /**
     * Sets the value of the woensdag property.
     * 
     */
    public void setWoensdag(boolean value) {
        this.woensdag = value;
    }

    /**
     * Gets the value of the zaterdag property.
     * 
     */
    public boolean isZaterdag() {
        return zaterdag;
    }

    /**
     * Sets the value of the zaterdag property.
     * 
     */
    public void setZaterdag(boolean value) {
        this.zaterdag = value;
    }

    /**
     * Gets the value of the zondag property.
     * 
     */
    public boolean isZondag() {
        return zondag;
    }

    /**
     * Sets the value of the zondag property.
     * 
     */
    public void setZondag(boolean value) {
        this.zondag = value;
    }

}
