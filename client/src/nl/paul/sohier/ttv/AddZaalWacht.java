package nl.paul.sohier.ttv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.JButton;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.server.Server;

import javax.swing.JCheckBox;

public class AddZaalWacht extends JFrame implements DocumentListener,
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5361543074415667952L;
	private JPanel contentPane;
	private JTextField jnaam;
	private JTextField jEmail;
	private JTextField aantal;
	private ZaalDienst dienst;
	private JCheckBox chckbxZaterdag;
	private JCheckBox chckbxDonderdag;
	private JCheckBox chckbxDinsdag;
	private JCheckBox chckbxZondag;
	private JCheckBox chckbxVrijdag;
	private JCheckBox chckbxWoensdag;
	private JCheckBox chckbxMaandag;
	private JButton btnOpslaan;
	private JButton btnAnnuleren;

	/**
	 * Create the frame.
	 */
	public AddZaalWacht() {
		setTitle("Zaalwacht toevoegen");

		dienst = new ZaalDienst();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 549, 296);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// Perhaps ask user if they want to save any unsaved files
				// first.
				askSave();
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
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
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblNaam = new JLabel("Naam");
		contentPane.add(lblNaam, "2, 2, right, default");

		jnaam = new JTextField();
		contentPane.add(jnaam, "4, 2, fill, default");
		jnaam.setColumns(10);

		jnaam.getDocument().addDocumentListener(this);

		JLabel lblEmail = new JLabel("Email");
		contentPane.add(lblEmail, "2, 4, right, default");

		jEmail = new JTextField();
		contentPane.add(jEmail, "4, 4, fill, default");
		jEmail.setColumns(10);
		jEmail.getDocument().addDocumentListener(this);

		JLabel lblAantalZaaldiensten = new JLabel("Aantal zaaldiensten");
		contentPane.add(lblAantalZaaldiensten, "2, 6, right, default");

		aantal = new JTextField();
		aantal.setText("1");
		contentPane.add(aantal, "4, 6, fill, default");
		aantal.setColumns(10);
		aantal.getDocument().addDocumentListener(this);

		JLabel lblDagen = new JLabel("dagen");
		contentPane.add(lblDagen, "2, 8, right, top");

		JPanel panel = new JPanel();
		contentPane.add(panel, "4, 8, left, top");
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		chckbxMaandag = new JCheckBox("Maandag");
		panel.add(chckbxMaandag, "2, 2");
		chckbxMaandag.addActionListener(this);

		chckbxWoensdag = new JCheckBox("Woensdag");
		panel.add(chckbxWoensdag, "4, 2");

		chckbxVrijdag = new JCheckBox("Vrijdag");
		panel.add(chckbxVrijdag, "6, 2");

		chckbxZondag = new JCheckBox("Zondag");
		panel.add(chckbxZondag, "8, 2");

		chckbxDinsdag = new JCheckBox("Dinsdag");
		panel.add(chckbxDinsdag, "2, 4");

		chckbxDonderdag = new JCheckBox("Donderdag");
		panel.add(chckbxDonderdag, "4, 4");

		chckbxZaterdag = new JCheckBox("Zaterdag");
		panel.add(chckbxZaterdag, "6, 4");

		btnOpslaan = new JButton("Opslaan");
		contentPane.add(btnOpslaan, "2, 10");

		btnAnnuleren = new JButton("Annuleren");
		contentPane.add(btnAnnuleren, "4, 10, left, default");

		btnOpslaan.addActionListener(new btnSave());
		btnAnnuleren.addActionListener(new btnCancel());

	}

	protected void askSave() {
		// TODO Auto-generated method stub

	}

	class btnSave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....
			save();
		}
	}

	class btnCancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO: Check if we want to validate the changed fields before
			// Actually canceling it...
			dispose();
		}
	}

	public void save() {
		Server srv = API.getServer(this);
		
		btnOpslaan.setEnabled(false);
		btnOpslaan.setText("Bezig met opslaan...");
		btnAnnuleren.setVisible(false);
		
		ZaalDienst saved = srv.saveZaalDienst(dienst);
		
		if (saved != null && saved.isSaved())
		{
			dispose();
		}
		else
		{
			JOptionPane.showMessageDialog(this,
				    "Er is iets misgegaan bij het opslaan, probeer het later nogmaals",
				    "Fout tijdens opslaan...",
				    JOptionPane.ERROR_MESSAGE);

			btnOpslaan.setEnabled(true);
			btnOpslaan.setText("Opslaan");
			btnAnnuleren.setVisible(true);			
			
		}

	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		upd();

	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		upd();

	}

	private void upd() {
		System.out.println("Going to update yuo...");
		
		boolean[] tmp = {false, false, false, false, false, false, false};
		tmp[0] = chckbxMaandag.isSelected();
		tmp[1] = chckbxDinsdag.isSelected();
		tmp[2] = chckbxWoensdag.isSelected();
		tmp[3] = chckbxDonderdag.isSelected();
		tmp[4] = chckbxVrijdag.isSelected();
		tmp[5] = chckbxZaterdag.isSelected();
		tmp[6] = chckbxZondag.isSelected();
		
		dienst.setDagen(tmp);
		dienst.setNaam(jnaam.getText());
		dienst.setEmail(jEmail.getText());
		dienst.setAantal(Integer.parseInt(aantal.getText()));
		
		
	}

}
