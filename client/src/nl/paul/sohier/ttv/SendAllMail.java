package nl.paul.sohier.ttv;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.DagRequest;

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

	/**
	 * Create the frame.
	 */
	public SendAllMail(DagRequest request, start parent) {

		this.parent = parent;

		setTitle("E-mail sturen " + request);

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


		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
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
						FormFactory.DEFAULT_ROWSPEC, }));

		lblOnderwerp = new JLabel("Onderwerp");
		contentPane.add(lblOnderwerp, "2, 2, left, default");

		onderwerp = new JTextField();
		contentPane.add(onderwerp, "4, 2, fill, default");
		onderwerp.setColumns(10);

		lblCc = new JLabel("CC");
		contentPane.add(lblCc, "2, 4, left, default");

		cc = new JTextField();
		contentPane.add(cc, "4, 4, fill, default");
		cc.setColumns(10);

		lblBcc = new JLabel("BCC");
		contentPane.add(lblBcc, "2, 6, left, default");

		bcc = new JTextField();
		contentPane.add(bcc, "4, 6, fill, default");
		bcc.setColumns(10);

		lblBericht = new JLabel("Bericht");
		contentPane.add(lblBericht, "2, 8, left, top");

		bericht = new JEditorPane();
		contentPane.add(bericht, "4, 8, default, top");

		chckbxCcBestuur = new JCheckBox("CC Bestuur");
		contentPane.add(chckbxCcBestuur, "2, 10, left, default");

		chckbxLeesbevestiging = new JCheckBox("Leesbevestiging");
		contentPane.add(chckbxLeesbevestiging, "2, 12");

		chckbxVoegLijstAls = new JCheckBox("Voeg lijst als bijlage toe (PDF en Excel)");
		contentPane.add(chckbxVoegLijstAls, "2, 14");

		btnVerstuur = new JButton("Verstuur");
		contentPane.add(btnVerstuur, "2, 16");

		btnVerstuur.addActionListener(new btnVerstuur());

		button = new JButton("Annuleren");
		contentPane.add(button, "4, 16, left, default");
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

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

}
