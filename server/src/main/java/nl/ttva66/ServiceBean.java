package nl.ttva66;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.entities.Dag;
import nl.ttva66.entities.Open;
import nl.ttva66.entities.Type;
import nl.ttva66.entities.Zaaldienst;

@Stateless
@WebService(serviceName = "Service", targetNamespace = "http://www.ttva66.nl/wsdl", endpointInterface = "nl.ttva66.Service")
@SOAPBinding(style = Style.DOCUMENT)
public class ServiceBean implements Service {

	@PersistenceContext(name = "zaaldienst")
	private EntityManager em;


	@Override
	public DagDto getDagByDate(Date datum) {
		System.out.println("Getting data for datum: " + datum);
		Query result = em
 				.createQuery("select X from Dag as X where datum = :datum");
		result.setParameter("datum", datum);

		try {
			Dag zt = (Dag) result.getSingleResult();
			return Convert.DagToDto(zt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Date not exists yet");
		
		// No date found yet, lets create a standard date (And persist it!)
		Dag dag = new Dag();
		
		Query rs = em
				.createQuery("select X from Type as X");
		
		dag.setDatum(datum);
		
		em.persist(dag);
		
		@SuppressWarnings("unchecked")
		List<Type> tp = rs.getResultList();
		
		Set<Open> op = new HashSet<Open>();
		for (Type deel : tp)
		{
			Open open = new Open();
			open.setOpen(false);
			open.setType(deel);
			open.setDag(dag);
			
			op.add(open);
		}
		dag.setOpens(op);
		em.persist(dag);
		
		
		return Convert.DagToDto(dag);
		
	}

	@Override
	public ZaaldienstDto login(String user, String password) {
		System.out.println(String.format("User: %s Passwd: $s", user, password));

		Query result = em
				.createQuery("select X from Zaaldienst as X where email = :user and password = :password and canLogin = true");
		result.setParameter("user", user);
		result.setParameter("password", password);

		try {
			Zaaldienst zt = (Zaaldienst) result.getSingleResult();
			return Convert.ZaaldientToDto(zt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}