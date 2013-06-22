package nl.ttva66.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import nl.ttva66.Dag;
import nl.ttva66.Zaaldienst;

/**
 * This class was generated by Apache CXF 2.3.1-patch-01
 * Sat Jun 22 19:24:11 CEST 2013
 * Generated source version: 2.3.1-patch-01
 * 
 */
 
@WebService(targetNamespace = "http://www.ttva66.nl/wsdl", name = "Service")
@XmlSeeAlso({ObjectFactory.class})
public interface Service {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.client.Login")
    @WebMethod
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.client.LoginResponse")
    public Zaaldienst login(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getDagByDate", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.client.GetDagByDate")
    @WebMethod
    @ResponseWrapper(localName = "getDagByDateResponse", targetNamespace = "http://www.ttva66.nl/wsdl", className = "nl.ttva66.client.GetDagByDateResponse")
    public Dag getDagByDate(
        @WebParam(name = "arg0", targetNamespace = "")
        javax.xml.datatype.XMLGregorianCalendar arg0
    );
}
