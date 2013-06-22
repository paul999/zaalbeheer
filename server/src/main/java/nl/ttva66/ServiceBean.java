package nl.ttva66;

import java.util.Date;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@WebService(serviceName = "Service", targetNamespace = "http://www.ttva66.nl/wsdl", endpointInterface = "nl.ttva66.Service")
@SOAPBinding(style = Style.DOCUMENT)
public class ServiceBean implements Service {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Dag getDagByDate(Date datum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Zaaldienst login(String user, String password) {
		System.out.println(String.format("User: %s Passwd: $s", user, password));

		Query result = em
				.createQuery("select X from Zaaldienst as X where email = :user and password = :password and canLogin = true");
		result.setParameter("user", user);
		result.setParameter("password", password);

		try {
			Zaaldienst zt = (Zaaldienst) result.getSingleResult();
			return zt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}