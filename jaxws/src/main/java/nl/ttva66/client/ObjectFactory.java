
package nl.ttva66.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.DienstDto;
import nl.ttva66.dto.OpenDto;
import nl.ttva66.dto.TypeDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.interfaces.ZaalDienstRequest;


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

    private final static QName _LoginResponse_QNAME = new QName("http://www.ttva66.nl/wsdl", "loginResponse");
    private final static QName _CreateTypeResponse_QNAME = new QName("http://www.ttva66.nl/wsdl", "createTypeResponse");
    private final static QName _GetDagByDate_QNAME = new QName("http://www.ttva66.nl/wsdl", "getDagByDate");
    private final static QName _ListZaaldiensten_QNAME = new QName("http://www.ttva66.nl/wsdl", "listZaaldiensten");
    private final static QName _SaveDag_QNAME = new QName("http://www.ttva66.nl/wsdl", "saveDag");
    private final static QName _GetZaaldienstById_QNAME = new QName("http://www.ttva66.nl/wsdl", "getZaaldienstById");
    private final static QName _CreateType_QNAME = new QName("http://www.ttva66.nl/wsdl", "createType");
    private final static QName _GetDagByDateResponse_QNAME = new QName("http://www.ttva66.nl/wsdl", "getDagByDateResponse");
    private final static QName _ListZaaldienstenResponse_QNAME = new QName("http://www.ttva66.nl/wsdl", "listZaaldienstenResponse");
    private final static QName _Login_QNAME = new QName("http://www.ttva66.nl/wsdl", "login");
    private final static QName _GetZaaldienstByIdResponse_QNAME = new QName("http://www.ttva66.nl/wsdl", "getZaaldienstByIdResponse");
    private final static QName _SaveDagResponse_QNAME = new QName("http://www.ttva66.nl/wsdl", "saveDagResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nl.ttva66.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DienstDto }
     * 
     */
    public DienstDto createDienstDto() {
        return new DienstDto();
    }

    /**
     * Create an instance of {@link DagDto }
     * 
     */
    public DagDto createDagDto() {
        return new DagDto();
    }

    /**
     * Create an instance of {@link SaveDag }
     * 
     */
    public SaveDag createSaveDag() {
        return new SaveDag();
    }

    /**
     * Create an instance of {@link TypeDto }
     * 
     */
    public TypeDto createTypeDto() {
        return new TypeDto();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link GetZaaldienstByIdResponse }
     * 
     */
    public GetZaaldienstByIdResponse createGetZaaldienstByIdResponse() {
        return new GetZaaldienstByIdResponse();
    }

    /**
     * Create an instance of {@link ZaalDienstRequest }
     * 
     */
    public ZaalDienstRequest createZaalDienstRequest() {
        return new ZaalDienstRequest();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link GetDagByDate }
     * 
     */
    public GetDagByDate createGetDagByDate() {
        return new GetDagByDate();
    }

    /**
     * Create an instance of {@link CreateTypeResponse }
     * 
     */
    public CreateTypeResponse createCreateTypeResponse() {
        return new CreateTypeResponse();
    }

    /**
     * Create an instance of {@link SaveDagResponse }
     * 
     */
    public SaveDagResponse createSaveDagResponse() {
        return new SaveDagResponse();
    }

    /**
     * Create an instance of {@link OpenDto }
     * 
     */
    public OpenDto createOpenDto() {
        return new OpenDto();
    }

    /**
     * Create an instance of {@link ListZaaldiensten }
     * 
     */
    public ListZaaldiensten createListZaaldiensten() {
        return new ListZaaldiensten();
    }

    /**
     * Create an instance of {@link GetZaaldienstById }
     * 
     */
    public GetZaaldienstById createGetZaaldienstById() {
        return new GetZaaldienstById();
    }

    /**
     * Create an instance of {@link ZaaldienstDto }
     * 
     */
    public ZaaldienstDto createZaaldienstDto() {
        return new ZaaldienstDto();
    }

    /**
     * Create an instance of {@link ListZaaldienstenResponse }
     * 
     */
    public ListZaaldienstenResponse createListZaaldienstenResponse() {
        return new ListZaaldienstenResponse();
    }

    /**
     * Create an instance of {@link DagRequest }
     * 
     */
    public DagRequest createDagRequest() {
        return new DagRequest();
    }

    /**
     * Create an instance of {@link CreateType }
     * 
     */
    public CreateType createCreateType() {
        return new CreateType();
    }

    /**
     * Create an instance of {@link GetDagByDateResponse }
     * 
     */
    public GetDagByDateResponse createGetDagByDateResponse() {
        return new GetDagByDateResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "createTypeResponse")
    public JAXBElement<CreateTypeResponse> createCreateTypeResponse(CreateTypeResponse value) {
        return new JAXBElement<CreateTypeResponse>(_CreateTypeResponse_QNAME, CreateTypeResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ListZaaldiensten }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "listZaaldiensten")
    public JAXBElement<ListZaaldiensten> createListZaaldiensten(ListZaaldiensten value) {
        return new JAXBElement<ListZaaldiensten>(_ListZaaldiensten_QNAME, ListZaaldiensten.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDag }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "saveDag")
    public JAXBElement<SaveDag> createSaveDag(SaveDag value) {
        return new JAXBElement<SaveDag>(_SaveDag_QNAME, SaveDag.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetZaaldienstById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "getZaaldienstById")
    public JAXBElement<GetZaaldienstById> createGetZaaldienstById(GetZaaldienstById value) {
        return new JAXBElement<GetZaaldienstById>(_GetZaaldienstById_QNAME, GetZaaldienstById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "createType")
    public JAXBElement<CreateType> createCreateType(CreateType value) {
        return new JAXBElement<CreateType>(_CreateType_QNAME, CreateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDagByDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "getDagByDateResponse")
    public JAXBElement<GetDagByDateResponse> createGetDagByDateResponse(GetDagByDateResponse value) {
        return new JAXBElement<GetDagByDateResponse>(_GetDagByDateResponse_QNAME, GetDagByDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListZaaldienstenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "listZaaldienstenResponse")
    public JAXBElement<ListZaaldienstenResponse> createListZaaldienstenResponse(ListZaaldienstenResponse value) {
        return new JAXBElement<ListZaaldienstenResponse>(_ListZaaldienstenResponse_QNAME, ListZaaldienstenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetZaaldienstByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "getZaaldienstByIdResponse")
    public JAXBElement<GetZaaldienstByIdResponse> createGetZaaldienstByIdResponse(GetZaaldienstByIdResponse value) {
        return new JAXBElement<GetZaaldienstByIdResponse>(_GetZaaldienstByIdResponse_QNAME, GetZaaldienstByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveDagResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttva66.nl/wsdl", name = "saveDagResponse")
    public JAXBElement<SaveDagResponse> createSaveDagResponse(SaveDagResponse value) {
        return new JAXBElement<SaveDagResponse>(_SaveDagResponse_QNAME, SaveDagResponse.class, null, value);
    }

}
