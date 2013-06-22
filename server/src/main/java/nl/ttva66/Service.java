package nl.ttva66;

import java.util.Date;

import javax.ejb.Remote;
import javax.jws.WebService;

import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.ZaaldienstDto;

@Remote
@WebService(targetNamespace = "http://www.ttva66.nl/wsdl")
public interface Service {

 public DagDto getDagByDate(Date datum);
 public ZaaldienstDto login(String user, String password);
}