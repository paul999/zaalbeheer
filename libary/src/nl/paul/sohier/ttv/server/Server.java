package nl.paul.sohier.ttv.server;
 
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.ServerException;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;

 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface Server{
 
	@WebMethod Dag getSavedDag(DagRequest dagRequest) throws ServerException;
	@WebMethod Dag saveDag(Dag dag) throws ServerException;
	@WebMethod boolean deleteDag(DagRequest dag) throws ServerException;
	
	@WebMethod ZaalDienst getZaalDienst(ZaalDienstRequest request) throws ServerException;
	@WebMethod ZaalDienst saveZaalDienst(ZaalDienst dienst) throws ServerException;
	
	@WebMethod ZaalDienst[] getAlleZaalDiensten() throws ServerException;
	@WebMethod ZaalDienst login(String user, String password) throws ServerException;
	

}