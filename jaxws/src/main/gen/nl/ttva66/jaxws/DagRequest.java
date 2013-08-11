
package nl.ttva66.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dagRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dagRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dag" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jaar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maand" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dagRequest", propOrder = {
    "dag",
    "jaar",
    "maand"
})
public class DagRequest {

    protected int dag;
    protected int jaar;
    protected int maand;

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

}
