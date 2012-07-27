package nl.paul.sohier.ttv;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import nl.paul.sohier.ttv.libary.API;

public class Settings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3396627819751956176L;
	private JPanel contentPane;
	private JTextField server;
	private JTextField username;
	private JTextField password;
	private JTextField port;
	private JTextField van;
	private JTextField vanemail;
	private JTextField naar;
	private JTextField naaremail;

	/**
	 * Create the frame.
	 */
	public Settings() {
		setTitle("Instellingen");
		
		setBounds(100, 100, 450, 300);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// Perhaps ask user if they want to save any unsaved files
				// first.
				dispose();
			}
		});
		
		
		pack();
		setExtendedState(Frame.MAXIMIZED_BOTH);		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Algemeen", null, panel, null);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,				},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEmailVan = new JLabel("e-mail van");
		panel.add(lblEmailVan, "2, 2, left, default");
		
		van = new JTextField();
		panel.add(van, "4, 2, left, default");
		van.setColumns(10);
		
		vanemail = new JTextField();
		panel.add(vanemail, "4, 4, left, default");
		vanemail.setColumns(10);
		
		naar = new JTextField();
		panel.add(naar, "4, 6, left, default");
		naar.setColumns(10);
		
		naaremail = new JTextField();
		panel.add(naaremail, "4, 8, left, default");
		naaremail.setColumns(10);
		
		JLabel lblEmailAdres = new JLabel("e-mail adres");
		panel.add(lblEmailAdres, "2, 4, left, default");
		
		JLabel lblEmailNaar = new JLabel("e-mail naar");
		panel.add(lblEmailNaar, "2, 6, left, default");
		
		JLabel lblEmailAdresNaar = new JLabel("e-mail adres naar");
		panel.add(lblEmailAdresNaar, "2, 8, left, default");
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("SMTP", null, panel_2, null);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,				},
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
				FormFactory.DEFAULT_ROWSPEC,				}));
		
		JLabel lblSmtpServer = new JLabel("SMTP Server");
		panel_2.add(lblSmtpServer, "2, 2, left, default");
		
		server = new JTextField();
		panel_2.add(server, "4, 2, left, default");
		server.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		panel_2.add(lblUsername, "2, 4, left, default");
		
		username = new JTextField();
		panel_2.add(username, "4, 4, left, default");
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panel_2.add(lblPassword, "2, 6, left, default");
		
		password = new JTextField();
		panel_2.add(password, "4, 6, left, default");
		password.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		panel_2.add(lblPort, "2, 8, left, default");
		
		port = new JTextField();
		panel_2.add(port, "4, 8, left, default");
		port.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton save = new JButton("Opslaan");
		panel_1.add(save, "2, 2");
		
		JButton btnAnnuleren = new JButton("Annuleren");
		
		btnAnnuleren.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}});
		
		panel_1.add(btnAnnuleren, "4, 2");
				
		server.setText(API.get("smtpserver"));
		username.setText(API.get("smtpusername"));
		password.setText(API.get("smtppassword"));
		port.setText(API.get("smtpport"));
		van.setText(API.get("van"));
		vanemail.setText(API.get("vanemail"));
		naar.setText(API.get("naar"));
		naaremail.setText(API.get("naaremail"));

		
		
		
		
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				API.put("smtpserver", server.getText().toString());
				API.put("smtpusername", username.getText().toString());
				API.put("smtppassword", password.getText().toString());
				API.put("smtpport", port.getText().toString());
				API.put("van", van.getText().toString());
				API.put("vanemail", vanemail.getText().toString());
				API.put("naar", naar.getText().toString());
				API.put("naaremail", naaremail.getText().toString());
				
				API.saveProperties();
				
				dispose();
				
			}});		
	}

}
