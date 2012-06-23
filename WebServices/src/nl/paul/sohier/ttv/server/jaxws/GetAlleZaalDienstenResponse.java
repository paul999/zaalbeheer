
package nl.paul.sohier.ttv.server.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAlleZaalDienstenResponse", namespace = "http://server.ttv.sohier.paul.nl/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAlleZaalDienstenResponse", namespace = "http://server.ttv.sohier.paul.nl/")
public class GetAlleZaalDienstenResponse {

    @XmlElement(name = "return", namespace = "", nillable = true)
    private nl.paul.sohier.ttv.libary.ZaalDienst[] _return;

    /**
     * 
     * @return
     *     returns ZaalDienst[]
     */
    public nl.paul.sohier.ttv.libary.ZaalDienst[] getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(nl.paul.sohier.ttv.libary.ZaalDienst[] _return) {
        this._return = _return;
    }

}
