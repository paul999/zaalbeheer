
package nl.paul.sohier.ttv.server.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getSavedDag", namespace = "http://server.ttv.sohier.paul.nl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSavedDag", namespace = "http://server.ttv.sohier.paul.nl/")
public class GetSavedDag {

    @XmlElement(name = "arg0", namespace = "")
    private nl.paul.sohier.ttv.libary.DagRequest arg0;

    /**
     * 
     * @return
     *     returns DagRequest
     */
    public nl.paul.sohier.ttv.libary.DagRequest getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(nl.paul.sohier.ttv.libary.DagRequest arg0) {
        this.arg0 = arg0;
    }

}
