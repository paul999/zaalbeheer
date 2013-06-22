package nl.ttva66;

import java.util.Date;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@WebService(serviceName = "Service", targetNamespace = "http://www.ttva66.nl/wsdl", endpointInterface = "nl.ttva66.Service")
@SOAPBinding(style = Style.DOCUMENT)
public class ServiceBean implements Service {

	@PersistenceContext private EntityManager manager;

	@Override
	public Dag getDagByDate(Date datum) {
		// TODO Auto-generated method stub
		return null;
	}
	

}