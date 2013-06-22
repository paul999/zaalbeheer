package nl.paul.sohier.ttv;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;


import nl.ttva66.Dag;
import nl.ttva66.DagRequest;
import nl.ttva66.ZaalDienstRequest;
import nl.ttva66.Zaaldienst;
import nl.ttva66.libary.ServerException;


public class API extends nl.ttva66.libary.API{
	public static /*Server*/void getServer() {

		

	}
	
	public static Dag getDag(DagRequest request) {
		/*Server srv = API.getServer();

		try {
			return srv.getSavedDag(request);
		} catch (Exception e) {
			return null;
		}*/
		return null;
	}

	public static Zaaldienst getZaalDienst(ZaalDienstRequest request) {
	/*	Server srv = API.getServer();

		try {
			return srv.getZaalDienst(request);
		} catch (Exception e) {
			return null;
		}*/
		return null;
	}	
	
	public static ArrayList<Zaaldienst> zaallijsten(DagRequest request) {
		ArrayList<Zaaldienst> list = new ArrayList<Zaaldienst>();

		GregorianCalendar dt = new GregorianCalendar(/*request.getJaar(),
				request.getMaand(), 1*/);
		dt.setTime(request.getDatum());

		/*Server srv = API.getServer();

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
		}*/

		return list;
	}

	public static String zaallijst(int[] lijst) {
		/*if (lijst.length == 0) {
			return null;
		}
*/
		String dt = "";
	/*	Server srv = API.getServer();

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
		}*/

		return dt;
	}	
}
