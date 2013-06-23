package nl.ttva66;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.TypeDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.entities.Dag;
import nl.ttva66.entities.Open;
import nl.ttva66.entities.Type;
import nl.ttva66.entities.Zaaldienst;
import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.interfaces.ZaalDienstRequest;

@Stateless
@WebService(serviceName = "Service", targetNamespace = "http://www.ttva66.nl/wsdl", endpointInterface = "nl.ttva66.Service")
@SOAPBinding(style = Style.DOCUMENT)
public class ServiceBean implements Service {

	@PersistenceContext(name = "zaaldienst")
	private EntityManager em;
	
	@Override
	public TypeDto createType(TypeDto type)
	{
		Type tp = new Type();
		tp.setEind(type.getEind());
		tp.setNaam(type.getNaam());
		tp.setStart(type.getStart());
		
		em.persist(tp);
		
		Query result = em.createQuery("select X from Dag");
		
		@SuppressWarnings("unchecked")
		List<Dag> dag = result.getResultList();
		
		
		for (Dag deel : dag)
		{
			Open open = new Open();
			open.setOpen(false);
			open.setType(tp);
			open.setDag(deel);
			
			em.persist(open);
		}
		
		return Convert.TypeToDto(tp);
	}
	
	@Override
	public DagDto saveDag(DagDto dag)
	{
		Dag dg = Convert.DtoToDag(dag);
		
		for (Open op : dg.getOpens())
		{
			em.persist(op);
		}
		
		em.persist(dg);
		return Convert.DagToDto(dg);
	}


	@Override
	public DagDto getDagByDate(DagRequest datum) {
		System.out.println("Getting data for datum: " + datum);
		Query result = em
 				.createQuery("select X from Dag as X where dag = :dag and maand = :maand and jaar = :jaar");
		result.setParameter("dag", datum.getDag());
		result.setParameter("maand", datum.getMaand());
		result.setParameter("jaar", datum.getJaar());

		try {
			Dag zt = (Dag) result.getSingleResult();
			return Convert.DagToDto(zt);
		} catch (NoResultException e) {

		}
		System.out.println("Date not exists yet");
		
		// No date found yet, lets create a standard date (And persist it!)
		Dag dag = new Dag();
		
		Query rs = em
				.createQuery("select X from Type as X");
		
		dag.setDag(datum.getDag());
		dag.setMaand(datum.getMaand());
		dag.setJaar(datum.getJaar());
		
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
			em.persist(open);
		}
		dag.setOpens(op);
		em.persist(dag);
		
		System.out.println("size: " + dag.getOpens().size());
		
		
		return Convert.DagToDto(dag);
		
	}
	public ZaaldienstDto getZaaldienstById(ZaalDienstRequest request)
	{

		Query result = em
				.createQuery("select X from Zaaldienst as X where id = :id");
		result.setParameter("id", request.getId());

		try {
			Zaaldienst zt = (Zaaldienst) result.getSingleResult();
			return Convert.ZaaldientToDto(zt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
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