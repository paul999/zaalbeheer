
/*
 * 
 */

package nl.ttva66.jaxws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.3.1-patch-01
 * Sun Aug 11 16:18:29 CEST 2013
 * Generated source version: 2.3.1-patch-01
 * 
 */


@WebServiceClient(name = "Service", 
                  wsdlLocation = "http://zaalbeheer.ttva66.nl/server-0.0.3-SNAPSHOT/Service/ServiceBean?wsdl",
                  targetNamespace = "http://www.ttva66.nl/wsdl") 
public class Service_Service extends javax.xml.ws.Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://www.ttva66.nl/wsdl", "Service");
    public final static QName ServiceBeanPort = new QName("http://www.ttva66.nl/wsdl", "ServiceBeanPort");
    static {
        URL url = null;
        try {
            url = new URL("http://zaalbeheer.ttva66.nl/server-0.0.3-SNAPSHOT/Service/ServiceBean?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://zaalbeheer.ttva66.nl/server-0.0.3-SNAPSHOT/Service/ServiceBean?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public Service_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Service_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Service_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public Service_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }
    public Service_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public Service_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Service
     */
    @WebEndpoint(name = "ServiceBeanPort")
    public Service getServiceBeanPort() {
        return super.getPort(ServiceBeanPort, Service.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Service
     */
    @WebEndpoint(name = "ServiceBeanPort")
    public Service getServiceBeanPort(WebServiceFeature... features) {
        return super.getPort(ServiceBeanPort, Service.class, features);
    }

}
