package nl.paul.sohier.ttv.libary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;

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
		Server srv = API.getServer(frame);

		try {
			return srv.getSavedDag(request);
		} catch (Exception e) {
			return null;
		}
	}

	public static ZaalDienst getZaalDienst(ZaalDienstRequest request,
			JFrame frame) {
		Server srv = API.getServer(frame);

		try {
			return srv.getZaalDienst(request);
		} catch (Exception e) {
			return null;
		}
	}

	private static boolean displayed = false;

	public static Server getServer(JFrame frame) {
		URL url;
		try {
			url = new URL("http://91.196.170.37:9999/ws/hello?wsdl");

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
	private static Properties properties;

	public static ArrayList<ZaalDienst> zaallijsten(DagRequest request,
			JFrame frame) {
		ArrayList<ZaalDienst> list = new ArrayList<ZaalDienst>();

		GregorianCalendar dt = new GregorianCalendar(request.getJaar(),
				request.getMaand(), 1);

		Server srv = API.getServer(frame);

		for (int i = 1; i <= dt
				.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++) {

			DagRequest r = new DagRequest(i, request.getMaand(),
					request.getJaar());
			Dag dag = (Dag) API.items.get(r);

			if (dag == null) {
				dag = srv.getSavedDag(r);

				if (dag == null) {
					throw new RuntimeException("Kon " + r
							+ " niet ophalen van de server.");
				}
			}

			for (int j = 0; j < 3; j++) {
				int t[] = dag.getDeelZaalDienst(j);

				for (int k = 0; k < t.length; k++) {
					ZaalDienstRequest zr = new ZaalDienstRequest();

					ZaalDienst z = (ZaalDienst) API.items.get(zr);

					if (z == null) {
						z = srv.getZaalDienst(zr);

						if (z == null) {
							// Should not happen?
							throw new RuntimeException(
									"There is no result found at the server?");
						}
						API.items.add(z);
					}
					list.add(z);

				}
			}
		}

		return list;
	}

	public static String zaallijst(int[] lijst, JFrame frame) {
		if (lijst.length == 0) {
			return null;
		}

		String dt = "";
		Server srv = API.getServer(frame);

		for (int i = 0; i < lijst.length; i++) {
			ZaalDienstRequest r = new ZaalDienstRequest(lijst[i]);
			ZaalDienst zt = (ZaalDienst) API.items.get(r);

			if (zt == null) {
				zt = srv.getZaalDienst(r);

				if (zt == null) {
					// Should not happen?
					throw new RuntimeException(
							"There is no result found at the server?");
				}
				API.items.add(zt);
			}

			if (i != 0) {
				dt += ", ";
			}
			dt += zt.getNaam();
		}

		return dt;
	}

	public static Team[] getTeams(int[] teams) {
		Team[] team = new Team[teams.length];

		return team;
	}

	public static Properties getProperties() {

		if (API.properties == null) {
			// set up real properties
			properties = new Properties();
			FileInputStream appStream;
			try {
				appStream = new FileInputStream(getSettingsDirectory()
						+ "app.properties");
				properties.load(appStream);
				appStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();

				File f = new File(getSettingsDirectory() + "app.properties");
				if (!f.exists()) {
					try {
						f.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
						return null;
					}
				}
				return getProperties();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

		}
		return properties;

	}

	public static void saveProperties() {
		saveProperties(properties);
	}

	@SuppressWarnings("deprecation")
	public static void saveProperties(Properties p) {
		FileOutputStream defaultsOut;
		try {
			defaultsOut = new FileOutputStream(getSettingsDirectory()
					+ "app.properties");
			p.save(defaultsOut, "---No Comment---");
			defaultsOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getSettingsDirectory() {
		String userHome = System.getProperty("user.home");
		if (userHome == null) {
			throw new IllegalStateException("user.home==null");
		}
		File home = new File(userHome);
		File settingsDirectory = new File(home, ".ttv");
		if (!settingsDirectory.exists()) {
			if (!settingsDirectory.mkdir()) {
				throw new IllegalStateException(settingsDirectory.toString());
			}
		}
		return settingsDirectory.getAbsolutePath() + "/";
	}

	public static String get(String name) {
		Object tmp = null;

		tmp = getProperties().get(name);

		if (tmp == null) {
			return "";
		}
		return tmp.toString();

	}

	public static void put(String key, String value) {
		if (value != null) {
			getProperties().put(key, value);
		}
	}

	/**
	 * Generate a MD5 string 
	 * 
	 * @param s Data to encrypt
	 * @return
	 */
	public static String md5(String s) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(s.getBytes(), 0, s.length());
			BigInteger i = new BigInteger(1, m.digest());
			return String.format("%1$032x", i);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
