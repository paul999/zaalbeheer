package nl.paul.sohier.ttv.libary;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.GregorianCalendar;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import nl.paul.sohier.ttv.server.Server;

public class API {
	public static Dag getDag(DagRequest request) {
		System.out.println("Getting dag from server...");

		Server srv = API.getServer();

		try {
			return srv.getSavedDag(request);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static ZaalDienst getZaalDienst(ZaalDienstRequest request) {
		System.out.println("Getting zaaldienst from server...");

		Server srv = API.getServer();

		try {
			return srv.getZaalDienst(request);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static Server getServer() {
		URL url;
		try {
			url = new URL("http://localhost:9999/ws/hello?wsdl");
		} catch (MalformedURLException e) {

			return null;
		}

		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://server.ttv.sohier.paul.nl/",
				"ServerImplService");

		Service service = null;
		try {
			service = Service.create(url, qname);
			return service.getPort(Server.class);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public static Dag createStandardDag(DagRequest d) {
		Dag dag = new Dag(d);

		GregorianCalendar cal = new GregorianCalendar(d.getJaar(),
				d.getMaand(), d.getDag());

		int lt = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
		System.out.println("Request:  " + d + " weekdag: " + lt);

		boolean[] open = { false, false, false };

		switch (lt) {
		case 3: // dinsdag
			open[1] = true;
		case 2:
		case 4:
		case 5:
		case 6:
			open[2] = true;
			break;
		case 7:
			open[0] = true;
			open[1] = true;
			break;
		default:

		}
		dag.setOpen(open);
		dag.setId(-1);
		dag.setChanged(false);

		return dag;
	}
}
