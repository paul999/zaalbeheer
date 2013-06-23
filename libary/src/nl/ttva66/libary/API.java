package nl.ttva66.libary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;


import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;

public class API {

	protected static boolean displayed = false;


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
	 * @param s
	 *            Data to encrypt
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
