package nl.paul.sohier.ttv.endpoint;

import javax.xml.ws.Endpoint;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.paul.sohier.ttv.server.ServerImpl;

//Endpoint publisher
public class Publish {

	public static void main(String[] args) {
		System.out.println("Starting server...");
		
		org.apache.log4j.Logger.getLogger("net.sf.hibernate").setLevel(org.apache.log4j.Level.WARN); 
		
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            SessionFactory sf = new Configuration()
                    .configure()
                    .buildSessionFactory();
            
            Endpoint.publish("http://127.0.0.1:9999/ws/hello", new ServerImpl(sf));
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }		
		
		
	}

}