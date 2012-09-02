package nl.sohier.paul.ttv.a66.zaaldiensten;

import nl.paul.sohier.ttv.libary.DagRequest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class Srv {
	private String METHOD_NAME;
	private SoapObject request;
	private int arg = 0;

	public Srv(String METHOD_NAME) {
		this.METHOD_NAME = METHOD_NAME;
		this.request = new SoapObject("http://server.ttv.sohier.paul.nl/",
				METHOD_NAME);
	}

	public void addProp(String name, Object value) {
		PropertyInfo propInfo = new PropertyInfo();
		propInfo.setName("arg" + arg);
		arg++;
		propInfo.setValue(value);
		propInfo.type = value.getClass();

		request.addProperty(propInfo);

	}

	public SoapObject send() {

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.setOutputSoapObject(request);

		// envelope.xsd = "http://91.196.170.37:9999/ws/hello?xsd";

		envelope.addMapping("http://server.ttv.sohier.paul.nl/",
				DagRequest.class.getSimpleName(), DagRequest.class);
		// Marshal floatMarshal = new MarshalFloat();
		// floatMarshal.register(envelope);

		envelope.dotNet = false;

		HttpTransportSE androidHttpTransport = new HttpTransportSE(
				"http://91.196.170.37:9999/ws/hello?wsdl");
		androidHttpTransport.debug = true;

		try {
			androidHttpTransport.call("http://server.ttv.sohier.paul.nl/"
					+ METHOD_NAME, envelope);

			SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();

			return resultsRequestSOAP; 

		} catch (Exception e) {
			Log.d("ttv", "Exception :(", e);

			return null;
		}
	}
}
