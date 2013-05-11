package nl.paul.sohier.ttv;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.ServerException;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;
import nl.paul.sohier.ttv.server.Server;

public class API extends nl.paul.sohier.ttv.libary.API{
	public static Server getServer() {
		URL url;
		try {
			url = new URL("http://127.0.0.1:9999/ws/hello?wsdl");

			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://server.ttv.sohier.paul.nl/",
					"ServerImplService");

			Service service = null;

			service = Service.create(url, qname);
			return service.getPort(Server.class);
		} catch (MalformedURLException e) {

			if (!displayed) {
				displayed = true;

				API.createIssue("Server failed",
						"There was a error during the server request: ", e);

				throw new RuntimeException(
						"There was a error during a request to the server: "
								+ e.getMessage());
			}

			return null;
		}

	}
	
	public static Dag getDag(DagRequest request) {
		Server srv = API.getServer();

		try {
			return srv.getSavedDag(request);
		} catch (Exception e) {
			return null;
		}
	}

	public static ZaalDienst getZaalDienst(ZaalDienstRequest request) {
		Server srv = API.getServer();

		try {
			return srv.getZaalDienst(request);
		} catch (Exception e) {
			return null;
		}
	}	
	
	public static ArrayList<ZaalDienst> zaallijsten(DagRequest request) {
		ArrayList<ZaalDienst> list = new ArrayList<ZaalDienst>();

		GregorianCalendar dt = new GregorianCalendar(/*request.getJaar(),
				request.getMaand(), 1*/);
		dt.setTime(request.getDatum());

		Server srv = API.getServer();

		for (int i = 1; i <= dt
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++) {

			DagRequest r = new DagRequest(null); // TODO: DATUM
			Dag dag = (Dag) API.items.get(r);

			if (dag == null) {
				try {
					dag = srv.getSavedDag(r);
				} catch (ServerException e) {
					throw new RuntimeException("Kon " + r
							+ " niet ophalen van de server.");
				}

				if (dag == null) {
					throw new RuntimeException("Kon " + r
							+ " niet ophalen van de server.");
				}
			}

			for (int j = 0; j < 3; j++) {
				//int t[] = dag.getDeelZaalDienst(j);
				int t [] = new int[0];

				for (int k = 0; k < t.length; k++) {
					ZaalDienstRequest zr = new ZaalDienstRequest();

					ZaalDienst z = (ZaalDienst) API.items.get(zr);

					if (z == null) {
						try {
							z = srv.getZaalDienst(zr);
						} catch (ServerException e) {
							// Should not happen?
							throw new RuntimeException(
									"There is no result found at the server?");
						}

						if (z == null) {
							// Should not happen?
							throw new RuntimeException(
									"There is no result found at the server?");
						}
						API.items.add(z);
					}
					list.add(z);

				}
			}
		}

		return list;
	}

	public static String zaallijst(int[] lijst) {
		if (lijst.length == 0) {
			return null;
		}

		String dt = "";
		Server srv = API.getServer();

		for (int i = 0; i < lijst.length; i++) {
			ZaalDienstRequest r = new ZaalDienstRequest(lijst[i]);
			ZaalDienst zt = (ZaalDienst) API.items.get(r);

			if (zt == null) {
				try {
					zt = srv.getZaalDienst(r);
				} catch (ServerException e) {
					// Should not happen?
					throw new RuntimeException(
							"There is no result found at the server?");
				}

				if (zt == null) {
					// Should not happen?
					throw new RuntimeException(
							"There is no result found at the server?");
				}
				API.items.add(zt);
			}

			if (i != 0) {
				dt += ", ";
			}
			dt += zt.getNaam();
		}

		return dt;
	}	
}
