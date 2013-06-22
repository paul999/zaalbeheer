package nl.paul.sohier.ttv;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import nl.ttva66.client.Service;
import nl.ttva66.client.Service_Service;
import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.ZaaldienstDto;

import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.interfaces.ZaalDienstRequest;


public class API extends nl.ttva66.libary.API{
	public static Service getServer() {

		Service_Service sv = new Service_Service();

		return sv.getServiceBeanPort();
	}
	
	public static DagDto getDag(DagRequest request) {
		System.out.println("Getting data for dag: " + request.getDatum());
		Service srv = getServer();
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(request.getDatum());
		XMLGregorianCalendar date2 = null;
		try {
			 date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
		
		}
		DagDto dt = srv.getDagByDate(date2);
		
		System.out.println("Got back: " + dt.getDatum());
		
		return dt;
	}

	public static ZaaldienstDto getZaalDienst(ZaalDienstRequest request) {
	/*	Server srv = API.getServer();

		try {
			return srv.getZaalDienst(request);
		} catch (Exception e) {
			return null;
		}*/
		return null;
	}	
	
	public static ArrayList<ZaaldienstDto> zaallijsten(DagRequest request) {
		ArrayList<ZaaldienstDto> list = new ArrayList<ZaaldienstDto>();

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
