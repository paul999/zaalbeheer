package nl.paul.sohier.ttv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.server.Server;

import java.awt.Color;



public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5501368318148512261L;
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField password;
	private JLabel lblError;
	private JFrame frame;

	/**
	 * Create the frame.
	 */
	public Login() {
		frame = this;

		setTitle("Login");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// Perhaps ask user if they want to save any unsaved files
				// first.
				dispose();
			}
		});

		setBounds(100, 100, 400, 150);

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
		
		JLabel lblGebruikersnaam = new JLabel("Email");
		contentPane.add(lblGebruikersnaam, "2, 2, right, default");
		
		user = new JTextField();
		contentPane.add(user, "4, 2, fill, default");
		user.setColumns(10);
		
		JLabel lblWachtwoord = new JLabel("Wachtwoord");
		contentPane.add(lblWachtwoord, "2, 4, right, default");
		
		password = new JPasswordField();
		contentPane.add(password, "4, 4, fill, default");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "2, 6, 3, 1");
		lblError.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		contentPane.add(btnLogin, "2, 8");
		btnLogin.addActionListener(new btnLogin());
		
		
		JButton btnAnnuleer = new JButton("Annuleer");
		contentPane.add(btnAnnuleer, "4, 8");
		btnAnnuleer.addActionListener(new btnCancel());
		
		/*
		 * 
		 */

	}
	
	private void error(String error)
	{
		lblError.setText(error);
		lblError.setVisible(true);
	}
	
	class btnCancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....
			dispose();
			
		}
	}
	
	class btnLogin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Check if user is valid...
			String gbr = user.getText();
			@SuppressWarnings("deprecation")
			String pass = password.getText();
		
			
			if (gbr.isEmpty() || pass.isEmpty())
			{
				error("Gebruikersnaam/wachtwoord is leeg");
				return;
			}
			Server srv = API.getServer(frame);
			ZaalDienst t = srv.login(gbr, pass);
			
			if (t == null)
			{
				error("Foute gebruikersnaam/wachtwoord");
				return;
			}
			
			dispose();
			start.window = new start();
			start.window.frame.setVisible(true);
		}
	}	
}
