package nl.paul.sohier.ttv.client;
 
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.server.Server;
 
public class HelloWorldClient{
 
	public static void main(String[] args) throws Exception {
 
		URL url = new URL("http://localhost:9999/ws/hello?wsdl");
 
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://libary.ttv.sohier.paul.nl/", "ServerImplService");
 
        Service service = Service.create(url, qname);
 
        Server hello = service.getPort(Server.class);
        
        Dag dg = hello.getSavedDag(new DagRequest(1,1,1912));
 
        System.out.println(dg);
 
    }
 
}