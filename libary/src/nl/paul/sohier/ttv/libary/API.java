package nl.paul.sohier.ttv.libary;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;

import nl.paul.sohier.ttv.server.Server;

public class API {
	public static Dag getDag(DagRequest request, JFrame frame) {
		System.out.println("Getting dag from server...");

		Server srv = API.getServer(frame);

		try {
			return srv.getSavedDag(request);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static ZaalDienst getZaalDienst(ZaalDienstRequest request,
			JFrame frame) {
		System.out.println("Getting zaaldienst from server...");

		Server srv = API.getServer(frame);

		try {
			return srv.getZaalDienst(request);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	private static boolean displayed = false;

	public static Server getServer(JFrame frame) {
		URL url;
		try {
			url = new URL("http://localhost:9999/ws/hello?wsdl");

			// 1st argument service URI, refer to wsdl document above
			// 2nd argument is service name, refer to wsdl document above
			QName qname = new QName("http://server.ttv.sohier.paul.nl/",
					"ServerImplService");

			Service service = null;

			service = Service.create(url, qname);
			return service.getPort(Server.class);
		} catch (Exception e) {

			if (!displayed) {
				displayed = true;

				API.createIssue("Server failed",
						"There was a error during the server request: ", e);

				frame.dispose();
				// gapi.issues.open("paul999", "zaalbeheer",
				// "Server not responding",
				// "The remote server is not responding. \nException: " +
				// e.getStackTrace());

				JOptionPane.showMessageDialog(frame,
						"Fout bij het verbinden met server: " + e.getMessage(),
						"Kon geen verbinding maken met de server",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1); // We exit here to make sure nothing bad
								// happens...
			}

			return null;
		}

	}

	public static Dag createStandardDag(DagRequest d) {
		Dag dag = new Dag(d);

		GregorianCalendar cal = new GregorianCalendar(d.getJaar(),
				d.getMaand(), d.getDag());

		int lt = cal.get(GregorianCalendar.DAY_OF_WEEK);

		System.out.println("Request:  " + d + " weekdag: " + lt);

		boolean[] open = { false, false, false };

		switch (lt) {
		case 3: // dinsdag
			open[1] = true;
		case 2:
		case 4:
		case 5:
		case 6:
			open[2] = true;
			break;
		case 7:
			open[0] = true;
			open[1] = true;
			break;
		default:

		}
		dag.setOpen(open);
		dag.setId(-1);
		dag.setChanged(false);

		return dag;
	}

	public static void createIssue(String title, String body, Exception e) {
		try {

			System.out.println("Hiero!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			e.printStackTrace(printWriter);

			body += "\n\n" + writer.toString();

			createIssue(title, body);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public static void createIssue(String title, String body) {
		GitHubClient client = new GitHubClient();

		client.setCredentials("ttvapp", "ttvapp1");
		IssueService issue = new IssueService(client);

		Issue is = new Issue();
		is.setTitle(title);
		is.setBody(body);

		try {
			issue.createIssue("paul999", "zaalbeheer", is);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static Collectie items;
	
	public static String zaallijst(int[] lijst, JFrame frame)
	{
		if (lijst.length == 0)
		{
			return null;
		}
		
		String dt = "";
		Server srv = API.getServer(frame);
		
		for (int i = 0; i < lijst.length; i++)
		{
			ZaalDienstRequest r = new ZaalDienstRequest(lijst[i]);
			ZaalDienst zt = (ZaalDienst)API.items.get(r);
			
			if (zt == null)
			{
				zt = srv.getZaalDienst(r);
				
				if (zt == null)
				{
					// Should not happen?
					throw new RuntimeException("There is no result found at the server?");
				}
				API.items.add(zt);
			}
			
			if (i != 0)
			{
				dt += ", ";
			}
			dt += zt.getNaam();
		}
		
		return dt;
	}
}
