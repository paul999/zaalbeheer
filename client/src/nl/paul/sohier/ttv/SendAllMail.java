package nl.paul.sohier.ttv;

import java.awt.Frame;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;
import nl.paul.sohier.ttv.output.Generator;
import nl.paul.sohier.ttv.output.OutputException;
import nl.paul.sohier.ttv.output.PDF;
import nl.paul.sohier.ttv.server.Server;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

public class SendAllMail extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267299796421679215L;
	private JPanel contentPane;
	private JButton button;
	private start parent;
	private JLabel lblOnderwerp;
	private JCheckBox chckbxCcBestuur;
	private JLabel lblCc;
	private JTextField cc;
	private JTextField onderwerp;
	private JLabel lblBcc;
	private JTextField bcc;
	private JLabel lblBericht;
	private JEditorPane bericht;
	private JCheckBox chckbxLeesbevestiging;
	private JButton btnVerstuur;
	private JCheckBox chckbxVoegLijstAls;
	private JFrame frame;
	private DagRequest request;
	private JLabel lblTestMode;
	private JLabel testmode;

	/**
	 * Create the frame.
	 */
	public SendAllMail(DagRequest request, start parent) {
		frame = this;

		this.parent = parent;

		setTitle("E-mail sturen " + request);
		this.request = request;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// Perhaps ask user if they want to save any unsaved files
				// first.
				dispose();
			}
		});

		// setBounds(100, 100, 450, 300);
		pack();
		setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		lblOnderwerp = new JLabel("Onderwerp");
		contentPane.add(lblOnderwerp, "2, 2, left, default");

		onderwerp = new JTextField();
		contentPane.add(onderwerp, "4, 2, fill, default");
		onderwerp.setColumns(10);
		
		lblTestMode = new JLabel("Test mode");
		contentPane.add(lblTestMode, "2, 4");
		
		testmode = new JLabel("Nog bedenken");
		contentPane.add(testmode, "4, 4");
		testmode.setText((Boolean.parseBoolean(API.get("testmode")) ? "Ja" : "Nee"));

		lblCc = new JLabel("CC");
		contentPane.add(lblCc, "2, 6, left, default");

		cc = new JTextField();
		contentPane.add(cc, "4, 6, fill, default");
		cc.setColumns(10);

		lblBcc = new JLabel("BCC");
		contentPane.add(lblBcc, "2, 8, left, default");

		bcc = new JTextField();
		contentPane.add(bcc, "4, 8, fill, default");
		bcc.setColumns(10);

		lblBericht = new JLabel("Bericht");
		contentPane.add(lblBericht, "2, 10, left, top");

		bericht = new JEditorPane();
		contentPane.add(bericht, "4, 10, default, top");

		chckbxCcBestuur = new JCheckBox("CC Bestuur");
		contentPane.add(chckbxCcBestuur, "2, 12, left, default");

		chckbxLeesbevestiging = new JCheckBox("Leesbevestiging");
		contentPane.add(chckbxLeesbevestiging, "2, 14");

		chckbxVoegLijstAls = new JCheckBox(
				"Voeg lijst als bijlage toe (PDF en Excel)");
		contentPane.add(chckbxVoegLijstAls, "2, 16");

		btnVerstuur = new JButton("Verstuur");
		contentPane.add(btnVerstuur, "2, 18");

		btnVerstuur.addActionListener(new btnVerstuur());

		button = new JButton("Annuleren");
		contentPane.add(button, "4, 18, left, default");
		button.addActionListener(new btnCancel());

		if (API.get("smtpserver").equals("")) {
			this.dispose();

			JOptionPane.showMessageDialog(this,
					"SMTP server is niet opgeslagen in instellingen.",
					"Instellingen niet aanwezig", JOptionPane.ERROR_MESSAGE);
			return;

		}
		this.setVisible(true);

	}

	class btnCancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....
			dispose();
		}
	}

	class btnVerstuur implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....

			String subject = onderwerp.getText();
			String dcc = cc.getText();
			String dbcc = bcc.getText();
			String dmessage = bericht.getText();

			boolean testmode = Boolean.parseBoolean(API.get("testmode"));

			if (testmode) {
				dmessage += "\n\n\nRunning in test mode. Mail not send to users.";
			}

			boolean bestuur = chckbxCcBestuur.isSelected();
			boolean lees = chckbxLeesbevestiging.isSelected();
			boolean lijst = chckbxVoegLijstAls.isSelected();

			PDF generator = new PDF(frame);

			if (lijst) {
				// Laten we eerst maar een lijst genereren :).

				Generator g = new Generator(frame, generator, request, null);
				g.genereer(true);

			}
			System.out.println("Going to send email...");

			// Assuming you are sending email from localhost
			String host = API.get("smtpserver");

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.setProperty("mail.smtp.host", host);
			properties.setProperty("mail.user", API.get("smtpusername"));
			properties.setProperty("mail.password", API.get("smtppassword"));

			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties);

			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				message.setSentDate(new Date());
				message.addHeader("X-ttv-sender", "yes");

				// Set From: header field of the header.

				message.setFrom(new InternetAddress(API.get("vanemail"), API
						.get("van")));

				// Set To: header field of the header.
				message.addRecipient(
						Message.RecipientType.TO,
						new InternetAddress(API.get("naaremail"), API
								.get("naar")));

				String[] lcc;

				lcc = dcc.split(",");

				if (lcc != null && lcc.length > 0) {
					for (int i = 0; i < lcc.length; i++) {
						// Set CC header.
						if (!lcc[i].isEmpty()) {
							message.addRecipient(Message.RecipientType.CC,
									new InternetAddress(lcc[i]));
						}
					}
				}

				lcc = dbcc.split(",");

				if (lcc != null && lcc.length > 0) {
					for (int i = 0; i < lcc.length; i++) {
						// Set CC header.
						if (!lcc[i].isEmpty()) {
							message.addRecipient(Message.RecipientType.BCC,
									new InternetAddress(lcc[i]));
						}
					}
				}

				if (bestuur) {
					message.addRecipient(Message.RecipientType.CC,
							new InternetAddress("bestuur@ttv-alexandria.nl"));
				}

				/**
				 * Alle diensten die deze maand er zijn moeten nu opgehaald
				 * worden. Voor alle gebruikers wordt een aparte BCC aangemaakt.
				 */

				GregorianCalendar dt = new GregorianCalendar(request.getJaar(),
						request.getMaand(), 1);

				Server srv = API.getServer(frame);

				ArrayList<Integer> users = new ArrayList<Integer>();

				for (int i = 1; i <= dt
						.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++) {
					System.out.println(i);

					GregorianCalendar dat = new GregorianCalendar(
							request.getJaar(), request.getMaand(), i);

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
						int[] tmp = dag.getDeelZaalDienst(j);
						for (int k = 0; k < tmp.length; k++) {
							if (!users.contains(tmp[k]))
							{
								users.add(tmp[k]);
							}
						}
					}
				}
				
				for (int i = 0; i < users.size(); i++)
				{
					
					ZaalDienstRequest r = new ZaalDienstRequest(users.get(i));
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

					String naam = zt.getNaam();
					String email = zt.getEmail();
					
					if (testmode)
					{
						email = API.get("naaremail");
					}
					
					if (email.isEmpty())
					{
						throw new RuntimeException("Email adres voor gebruiker " + naam + " is leeg.");
					}
					
					System.out.println("Adding mail: " + email);
					message.addRecipient(Message.RecipientType.BCC,
							new InternetAddress(email, naam));					
				}

				// Set Subject: header field
				message.setSubject(subject);

				// Create the message part
				BodyPart messageBodyPart = new MimeBodyPart();

				// Fill the message
				messageBodyPart.setText(dmessage);

				// Create a multipar message
				Multipart multipart = new MimeMultipart();

				// Set text message part
				multipart.addBodyPart(messageBodyPart);

				if (lijst) {

					String[] months = { "januari", "februari", "maart",
							"april", "mei", "juni", "juli", "augustus",
							"september", "oktober", "november", "december" };

					// Part two is attachment
					messageBodyPart = new MimeBodyPart();
					File filename = generator.getFl();

					DataSource source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName("schema_"
							+ months[request.getMaand()] + ".pdf");
					multipart.addBodyPart(messageBodyPart);
				}

				// Send the complete message parts
				message.setContent(multipart);

				// Send message
				Transport.send(message);
				System.out.println("Sent message successfully....");

				frame.dispose();

				String t = "";

				if (testmode) {
					t += "\n\nTest mode ingeschakeld: Enkel verstuurd naar to email ipv users email!";
				}

				JOptionPane.showMessageDialog(frame,
						"De email is verstuurd naar iedereen die deze maand zaaldienst heeft."
								+ t, "Mail verstuurd",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (MessagingException mex) {
				mex.printStackTrace();

				frame.dispose();

				JOptionPane.showMessageDialog(frame,
						"Er was een error bij het versturen van de email.",
						"Kon mail niet versturen", JOptionPane.ERROR_MESSAGE);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();

				frame.dispose();

				JOptionPane.showMessageDialog(frame,
						"Er was een error bij het versturen van de email.",
						"Kon mail niet versturen", JOptionPane.ERROR_MESSAGE);
			}
			catch (RuntimeException e2)
			{
				e2.printStackTrace();

				frame.dispose();

				JOptionPane.showMessageDialog(frame,
						"Er gebeurde iets wat nite hoorde: " + e2.getMessage(),
						"Kon mail niet versturen", JOptionPane.ERROR_MESSAGE);				
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}
