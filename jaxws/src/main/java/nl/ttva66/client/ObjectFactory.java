
package nl.ttva66.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import nl.ttva66.Dag;
import nl.ttva66.Dienst;
import nl.ttva66.Open;
import nl.ttva66.Type;
import nl.ttva66.Zaaldienst;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nl.ttva66.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDagByDate_QNAME = new QName("http://www.ttva66.nl/wsdl", "getDagByDate");
    private final static QName _GetDagByDateResponse_QNAME = new QName("http://www.ttva66.nl/wsdl", "getDagByDateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nl.ttva66.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Open }
     * 
     */
    public Open createOpen() {
        return new Open();
    }

    /**
     * Create an instance of {@link Dag }
     * 
     */
    public Dag createDag() {
        return new Dag();
    }

    /**
     * Create an instance of {@link Zaaldienst }
     * 
     */
    public Zaaldienst createZaaldienst() {
        return new Zaaldienst();
    }

    /**
     * Create an instance of {@link GetDagByDate }
     * 
     */
    public GetDagByDate createGetDagByDate() {
        return new GetDagByDate();
    }

    /**
     * Create an instance of {@link GetDagByDateResponse }
     * 
     */
    public GetDagByDateResponse createGetDagByDateResponse() {
        return new GetDagByDateResponse();
    }

    /**
     * Create an instance of {@link Type }
     * 
     */
    public Type createType() {
        return new Type();
    }

    /**
     * Create an instance of {@link Dienst }
     * 
     */
    public Dienst createDienst() {
        return new Dienst();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDagByDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "getDagByDate")
    public JAXBElement<GetDagByDate> createGetDagByDate(GetDagByDate value) {
        return new JAXBElement<GetDagByDate>(_GetDagByDate_QNAME, GetDagByDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDagByDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "getDagByDateResponse")
    public JAXBElement<GetDagByDateResponse> createGetDagByDateResponse(GetDagByDateResponse value) {
        return new JAXBElement<GetDagByDateResponse>(_GetDagByDateResponse_QNAME, GetDagByDateResponse.class, null, value);
    }

}
