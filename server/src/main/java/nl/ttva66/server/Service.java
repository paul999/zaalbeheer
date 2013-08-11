package nl.ttva66.server;

import javax.ejb.Remote;
import javax.jws.WebService;

import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.TypeDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.interfaces.ZaalDienstRequest;

@Remote
@WebService(targetNamespace = "http://www.ttva66.nl/wsdl")
public interface Service {

 public DagDto getDagByDate(DagRequest request);
 public ZaaldienstDto getZaaldienstById(ZaalDienstRequest request);
 public ZaaldienstDto login(String user, String password);
 public ZaaldienstDto saveZaaldienst(ZaaldienstDto dienst);
 public TypeDto createType(TypeDto type);
 public TypeDto[] listTypes();
 public DagDto saveDag(DagDto dto);
 public Integer[] listZaaldiensten();
}