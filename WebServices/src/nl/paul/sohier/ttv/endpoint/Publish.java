package nl.paul.sohier.ttv.endpoint;

import javax.xml.ws.Endpoint;

import nl.paul.sohier.ttv.server.ServerImpl;

//Endpoint publisher
public class Publish {

	public static void main(String[] args) {
		System.out.println("Starting server...");
		Endpoint.publish("http://127.0.0.1:9999/ws/hello", new ServerImpl());
	}

}