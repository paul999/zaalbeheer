package nl.ttva66.client;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.OpenDto;
import nl.ttva66.dto.TypeDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.interfaces.ZaalDienstRequest;
import nl.ttva66.jaxws.Service;
import nl.ttva66.jaxws.Service_Service;

public class API extends nl.ttva66.libary.API {
	public static Service getServer() {

		try {

			Service_Service sv = new Service_Service();

			return sv.getServiceBeanPort();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(
							null,
							"Kon geen verbinding maken met de remote server. Maak je gebruik van de laatste versie?",
							"Fout tijdens open van de server.",
							JOptionPane.ERROR_MESSAGE);

			return null;
		}
	}

	public static DagDto getDag(DagRequest request) {
		System.out.println("Getting data for dag: " + request);
		Service srv = getServer();

		DagDto dt = srv.getDagByDate(request);

		System.out.println("Got back: " + dt);

		return dt;
	}

	public static ZaaldienstDto getZaalDienst(ZaalDienstRequest request) {
		Service srv = getServer();
		return srv.getZaaldienstById(request);
	}

	public static Set<TypeDto> sortType(List<TypeDto> types) {
		Set<TypeDto> odto = new TreeSet<TypeDto>(new Comparator<TypeDto>() {
			public int compare(TypeDto n1, TypeDto n2) {
				return n1.getSequence() - n2.getSequence();
			}
		});
		odto.addAll(types);
		return odto;
	}

	public static Set<OpenDto> sortOpen(Set<OpenDto> opens) {
		Set<OpenDto> odto = new TreeSet<OpenDto>(new Comparator<OpenDto>() {
			public int compare(OpenDto n1, OpenDto n2) {
				return n1.getType().getSequence() - n2.getType().getSequence();
			}
		});
		odto.addAll(opens);
		return odto;
	}
}
