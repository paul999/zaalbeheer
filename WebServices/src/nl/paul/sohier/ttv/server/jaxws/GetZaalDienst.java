
package nl.paul.sohier.ttv.server.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getZaalDienst", namespace = "http://server.ttv.sohier.paul.nl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getZaalDienst", namespace = "http://server.ttv.sohier.paul.nl/")
public class GetZaalDienst {

    @XmlElement(name = "arg0", namespace = "")
    private nl.paul.sohier.ttv.libary.ZaalDienstRequest arg0;

    /**
     * 
     * @return
     *     returns ZaalDienstRequest
     */
    public nl.paul.sohier.ttv.libary.ZaalDienstRequest getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(nl.paul.sohier.ttv.libary.ZaalDienstRequest arg0) {
        this.arg0 = arg0;
    }

}
