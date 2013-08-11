package nl.ttva66.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.3.1-patch-01
 * Sun Aug 11 16:18:29 CEST 2013
 * Generated source version: 2.3.1-patch-01
 * 
 */
 
@WebService(targetNamespace = "http://www.ttva66.nl/wsdl", name = "Service")
@XmlSeeAlso({ObjectFactory.class})
public interface Service {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.Login")
    @WebMethod
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.LoginResponse")
    public nl.ttva66.jaxws.ZaaldienstDto login(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "saveDag", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.SaveDag")
    @WebMethod
    @ResponseWrapper(localName = "saveDagResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.SaveDagResponse")
    public nl.ttva66.jaxws.DagDto saveDag(
        @WebParam(name = "arg0", targetNamespace = "")
        nl.ttva66.jaxws.DagDto arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getDagByDate", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.GetDagByDate")
    @WebMethod
    @ResponseWrapper(localName = "getDagByDateResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.GetDagByDateResponse")
    public nl.ttva66.jaxws.DagDto getDagByDate(
        @WebParam(name = "arg0", targetNamespace = "")
        nl.ttva66.jaxws.DagRequest arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "saveZaaldienst", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.SaveZaaldienst")
    @WebMethod
    @ResponseWrapper(localName = "saveZaaldienstResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.SaveZaaldienstResponse")
    public nl.ttva66.jaxws.ZaaldienstDto saveZaaldienst(
        @WebParam(name = "arg0", targetNamespace = "")
        nl.ttva66.jaxws.ZaaldienstDto arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "listZaaldiensten", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.ListZaaldiensten")
    @WebMethod
    @ResponseWrapper(localName = "listZaaldienstenResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.ListZaaldienstenResponse")
    public java.util.List<java.lang.Integer> listZaaldiensten();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "listTypes", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.ListTypes")
    @WebMethod
    @ResponseWrapper(localName = "listTypesResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.ListTypesResponse")
    public java.util.List<nl.ttva66.jaxws.TypeDto> listTypes();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getZaaldienstById", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.GetZaaldienstById")
    @WebMethod
    @ResponseWrapper(localName = "getZaaldienstByIdResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.GetZaaldienstByIdResponse")
    public nl.ttva66.jaxws.ZaaldienstDto getZaaldienstById(
        @WebParam(name = "arg0", targetNamespace = "")
        nl.ttva66.jaxws.ZaalDienstRequest arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "createType", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.CreateType")
    @WebMethod
    @ResponseWrapper(localName = "createTypeResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.jaxws.CreateTypeResponse")
    public nl.ttva66.jaxws.TypeDto createType(
        @WebParam(name = "arg0", targetNamespace = "")
        nl.ttva66.jaxws.TypeDto arg0
    );
}