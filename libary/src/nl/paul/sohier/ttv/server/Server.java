package nl.paul.sohier.ttv.server;
 
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.Team;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;

 
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface Server{
 
	@WebMethod Dag getSavedDag(DagRequest dagRequest);
	@WebMethod Dag saveDag(Dag dag);
	@WebMethod boolean deleteDag(DagRequest dag);
	
	@WebMethod ZaalDienst getZaalDienst(ZaalDienstRequest request);
	@WebMethod ZaalDienst saveZaalDienst(ZaalDienst dienst);
	
	@WebMethod ZaalDienst[] getAlleZaalDiensten();
	@WebMethod Team[] getAlleTeams();
	@WebMethod ZaalDienst login(String user, String password);
	

}