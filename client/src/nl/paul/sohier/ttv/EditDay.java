package nl.paul.sohier.ttv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.server.Server;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class EditDay extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267299796421679215L;
	private JPanel contentPane;

	private Dag dag;
	private JCheckBox ochtend;
	private JCheckBox middag;
	private JCheckBox avond;
	private JComboBox dienstochtend;
	private JComboBox dienstmiddag;
	private JComboBox dienstavond;
	private JLabel lblZaaldienst;
	private JButton btnOpslaan;
	private JButton button;
	private ZaalDienst[] s;
	private int[] ids;
	
	/**
	 * Create the frame.
	 */
	public EditDay(DagRequest request) {

		setTitle("Aanpassen dag: " + request);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// Perhaps ask user if they want to save any unsaved files
				// first.
				askSave();
			}
		});

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		Server srv = API.getServer();
		dag = srv.getSavedDag(request);

		contentPane.setLayout(new FormLayout(
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblDatum = new JLabel("Datum:");
		contentPane.add(lblDatum, "2, 2");

		JLabel lblDate = new JLabel(dag.toString());
		contentPane.add(lblDate, "4, 2, left, default");

		JLabel lblOchtend = new JLabel("Ochtend");
		contentPane.add(lblOchtend, "4, 4, left, default");

		JLabel lblMiddag = new JLabel("Middag");
		contentPane.add(lblMiddag, "6, 4, left, default");

		JLabel lblAvond = new JLabel("Avond");
		contentPane.add(lblAvond, "8, 4, left, default");

		JLabel lblZaalOpen = new JLabel("Zaal open");
		contentPane.add(lblZaalOpen, "2, 6");

		ochtend = new JCheckBox("");

		contentPane.add(ochtend, "4, 6, left, default");

		middag = new JCheckBox("");
		contentPane.add(middag, "6, 6, left, default");

		avond = new JCheckBox("");
		contentPane.add(avond, "8, 6, left, default");

		lblZaaldienst = new JLabel("Zaaldienst");
		contentPane.add(lblZaaldienst, "2, 8");

		dienstochtend = new JComboBox();
		contentPane.add(dienstochtend, "4, 8, left, default");

		dienstmiddag = new JComboBox();
		contentPane.add(dienstmiddag, "6, 8, left, default");

		dienstavond = new JComboBox();
		contentPane.add(dienstavond, "8, 8, left, default");

		btnOpslaan = new JButton("Opslaan");
		contentPane.add(btnOpslaan, "2, 10, left, default");

		btnOpslaan.addActionListener(new btnSave());
		

		button = new JButton("Annuleren");
		contentPane.add(button, "4, 10, left, default");
		button.addActionListener(new btnCancel());

		ochtend.addActionListener(this);
		middag.addActionListener(this);
		avond.addActionListener(this);

		// Set initial values.
		ochtend.setSelected(dag.getDeelOpen(0));
		middag.setSelected(dag.getDeelOpen(1));
		avond.setSelected(dag.getDeelOpen(2));
		
		s = srv.getAlleZaalDiensten();
		
		ids = new int[s.length+1];
		ids[0] = -1;
		
		System.out.println(s.length);
		
		dienstochtend.addItem("Selecteer");
		dienstmiddag.addItem("Selecteer");
		dienstavond.addItem("Selecteer");
		int[] zt = dag.getZaaldienst();
		
		for (int i = 0; i < s.length; i++)
		{
			if (s[i] != null)
			{
				dienstochtend.addItem(s[i].getNaam());
				dienstmiddag.addItem(s[i].getNaam());
				dienstavond.addItem(s[i].getNaam());
				
				ids[i + 1] = s[i].getId();
				
				int id = s[i].getId();
				
				
				
				if (id == zt[0])
				{
					dienstochtend.setSelectedIndex(i + 1);
				}
				
				if (id == zt[1])
				{
					dienstmiddag.setSelectedIndex(i + 1);
					
				}
				
				if (id == zt[2])
				{
					dienstavond.setSelectedIndex(i + 1);
				}
			}
			else
			{
				System.out.println("null? :(");
			}
		}
		

		upd();

	}

	protected void askSave() {

		System.out.println("askSave");

		if (dag.isChanged()) {
			System.out.println("Non saved days");

			// Custom button text
			Object[] options = { "Save", "Discard", "Cancel" };
			int n = JOptionPane
					.showOptionDialog(
							this,
							"Er zijn nog niet opgeslagen dagen, weet je zeker dat je wilt afsluiten?",
							"Afsluiten", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[2]);

			System.out.println("Waarde: " + n);

			switch (n) {
			case 0:
				save();
			case 1:
				dispose();
			case 2:
			default:
				System.out.println("Return");
				return;
			}
		}

		dispose();

	}

	class btnSave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....
			if (dag.isChanged())
			{
				System.out.println("Need to save...");
				save();
			}
			else
			{
				dispose();
			}
		}
	}
	
	class btnCancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....
			askSave();
		}
	}	

	public void save() {
		btnOpslaan.setEnabled(false);
		button.setVisible(false);
		btnOpslaan.setText("Bezig met opslaan...");
		
		Server srv = API.getServer();
		Dag dt = srv.saveDag(dag);
		if (dt == null || !dt.isSaved())
		{
			JOptionPane.showMessageDialog(this,
				    "Er is iets misgegaan bij het opslaan, probeer het later nogmaals",
				    "Fout tijdens opslaan...",
				    JOptionPane.ERROR_MESSAGE);

			btnOpslaan.setEnabled(true);
			btnOpslaan.setText("Opslaan");
			button.setVisible(true);				
		}
		else
		{
			dispose();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		upd();

	}

	private void upd() {
		boolean[] open = { false, false, false };

		open[0] = ochtend.isSelected();
		open[1] = middag.isSelected();
		open[2] = avond.isSelected();

		dag.setOpen(open);
		
		boolean hide = true;

		if (open[0]) {
			dienstochtend.setVisible(true);
			hide = false;
		} else {
			dienstochtend.setVisible(false);
		}
		if (open[1]) {
			dienstmiddag.setVisible(true);
			hide = false;
		} else {
			dienstmiddag.setVisible(false);
		}
		if (open[2]) {
			dienstavond.setVisible(true);
			hide = false;
		} else {
			dienstavond.setVisible(false);
		}
		
		if (hide)
		{
			lblZaaldienst.setVisible(false);
		}
		else
		{
			lblZaaldienst.setVisible(true);
		}
	}
}
