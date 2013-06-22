
package nl.ttva66.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dienst complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dienst">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="backup" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dag" type="{http://www.ttva66.nl/wsdl}dag" minOccurs="0"/>
 *         &lt;element name="definitief" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.ttva66.nl/wsdl}type" minOccurs="0"/>
 *         &lt;element name="zaaldienst" type="{http://www.ttva66.nl/wsdl}zaaldienst" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dienst", propOrder = {
    "backup",
    "dag",
    "definitief",
    "id",
    "type",
    "zaaldienst"
})
public class Dienst {

    protected boolean backup;
    protected Dag dag;
    protected boolean definitief;
    protected Integer id;
    protected Type type;
    protected Zaaldienst zaaldienst;

    /**
     * Gets the value of the backup property.
     * 
     */
    public boolean isBackup() {
        return backup;
    }

    /**
     * Sets the value of the backup property.
     * 
     */
    public void setBackup(boolean value) {
        this.backup = value;
    }

    /**
     * Gets the value of the dag property.
     * 
     * @return
     *     possible object is
     *     {@link Dag }
     *     
     */
    public Dag getDag() {
        return dag;
    }

    /**
     * Sets the value of the dag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dag }
     *     
     */
    public void setDag(Dag value) {
        this.dag = value;
    }

    /**
     * Gets the value of the definitief property.
     * 
     */
    public boolean isDefinitief() {
        return definitief;
    }

    /**
     * Sets the value of the definitief property.
     * 
     */
    public void setDefinitief(boolean value) {
        this.definitief = value;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * Gets the value of the zaaldienst property.
     * 
     * @return
     *     possible object is
     *     {@link Zaaldienst }
     *     
     */
    public Zaaldienst getZaaldienst() {
        return zaaldienst;
    }

    /**
     * Sets the value of the zaaldienst property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zaaldienst }
     *     
     */
    public void setZaaldienst(Zaaldienst value) {
        this.zaaldienst = value;
    }

}
