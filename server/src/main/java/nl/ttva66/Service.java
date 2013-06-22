package nl.ttva66;

import java.util.Date;

import javax.ejb.Remote;
import javax.jws.WebService;

@Remote
@WebService(targetNamespace = "http://www.ttva66.nl/wsdl")
public interface Service {

 public Dag getDagByDate(Date datum);
}