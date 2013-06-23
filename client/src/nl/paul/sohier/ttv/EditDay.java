package nl.paul.sohier.ttv;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.ttva66.client.Service;
import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.libary.ServerException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class EditDay extends JFrame implements ActionListener,
		ListSelectionListener, DocumentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267299796421679215L;
	private JPanel contentPane;

	private DagDto dag;
	private JCheckBox ochtend;
	private JCheckBox middag;
	private JCheckBox avond;
	private JLabel lblZaaldienst;
	private JButton btnOpslaan;
	private JButton button;
	private ZaaldienstDto[] s;
	private int[] ids;
	private start parent;
	private boolean load = false;
	private JList dienstochtend;
	private JList dienstmiddag;
	private JList dienstavond;
	private JLabel lblTeams;
	private JLabel lblTeamZaaldienst;
	private JList teamsthuis;
	private JTextField teamzaaldienst;
	private JLabel lblOpmerkingen;
	private JTextArea opmerkingen;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public EditDay(DagRequest request, start parent) {

		this.parent = parent;

		setTitle("Aanpassen dag: " + request);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// Perhaps ask user if they want to save any unsaved files
				// first.
				askSave();
			}
		});

		// setBounds(100, 100, 450, 300);
		pack();
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);

/*		Server srv = API.getServer();
		try {
			dag = srv.getSavedDag(request);
		} catch (ServerException e) {
			e.printStackTrace();
			return;
		}
*/
		dag = (DagDto) API.items.get(request);
		
		if (dag == null)
		{
			Service srv = API.getServer();
			
			dag = srv.getDagByDate(request);
			
			if (dag == null)
			{
				throw new RuntimeException("Missing dag");
			}
			
			API.items.add(dag);
			
		}
		
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblDatum = new JLabel("Datum:");
		contentPane.add(lblDatum, "2, 4");

		JLabel lblDate = new JLabel(dag.toString());
		contentPane.add(lblDate, "4, 4, left, default");

		JLabel lblOchtend = new JLabel("Ochtend");
		contentPane.add(lblOchtend, "4, 6, left, default");

		JLabel lblMiddag = new JLabel("Middag");
		contentPane.add(lblMiddag, "6, 6, left, default");

		JLabel lblAvond = new JLabel("Avond");
		contentPane.add(lblAvond, "8, 6, left, default");

		JLabel lblZaalOpen = new JLabel("Zaal open");
		contentPane.add(lblZaalOpen, "2, 8");

		ochtend = new JCheckBox("");

		contentPane.add(ochtend, "4, 8, left, default");

		middag = new JCheckBox("");
		contentPane.add(middag, "6, 8, left, default");

		avond = new JCheckBox("");
		contentPane.add(avond, "8, 8, left, default");

		lblZaaldienst = new JLabel("Zaaldienst");
		contentPane.add(lblZaaldienst, "2, 10");

		dienstochtend = new JList();
		dienstochtend.setVisibleRowCount(4);

		contentPane.add(dienstochtend, "4, 10, fill, fill");

		dienstmiddag = new JList();
		dienstmiddag.setVisibleRowCount(4);
		contentPane.add(dienstmiddag, "6, 10, fill, fill");

		dienstavond = new JList();
		dienstavond.setVisibleRowCount(4);
		contentPane.add(dienstavond, "8, 10, fill, fill");

		ochtend.addActionListener(this);
		middag.addActionListener(this);
		avond.addActionListener(this);

		dienstochtend.addListSelectionListener(this);
		dienstmiddag.addListSelectionListener(this);
		dienstavond.addListSelectionListener(this);

		// Set initial values.
		/*
		ochtend.setSelected(dag.getDeelOpen(0));
		middag.setSelected(dag.getDeelOpen(1));
		avond.setSelected(dag.getDeelOpen(2));*/

	/*	try {
			s = srv.getAlleZaalDiensten();
	//		t = srv.getAlleTeams();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}*/
		

		//ids = new int[s.length];

/*		int[] zochtend = dag.getDeelZaalDienst(0);
		int[] zmiddag = dag.getDeelZaalDienst(1);
		int[] zavond = dag.getDeelZaalDienst(2);
*/
/*		int[] lo = new int[zochtend.length];
		int[] lm = new int[zmiddag.length];
		int[] la = new int[zavond.length];
*/
		int o = 0, m = 0, a = 0;

	/*	for (int i = 0; i < s.length; i++) {
			if (s[i] != null) {
				// dienstochtend.addItem(s[i].getNaam());

				ids[i] = s[i].getId();

				if (inArray(zochtend, s[i].getId())) {
					lo[o] = i;
					o++;
				}

				if (inArray(zmiddag, s[i].getId())) {
					lm[m] = i;
					m++;
				}

				if (inArray(zavond, s[i].getId())) {
					la[a] = i;
					a++;
				}
			}
		}*/
		/*model mo = new model(s);

		dienstochtend.setModel(mo);
		dienstavond.setModel(mo);
		dienstmiddag.setModel(mo);*/

		/*dienstochtend.setSelectedIndices(lo);
		dienstmiddag.setSelectedIndices(lm);
		dienstavond.setSelectedIndices(la);
	*/	
//		int[] teams = dag.getTeams();
//		Team[] team = API.getTeams(teams);
 
		lblTeams = new JLabel("Teams thuis:");
		contentPane.add(lblTeams, "2, 12");

/*
  		teamsthuis = new JList();
		teamsthuis.setVisibleRowCount(4);
		teamsthuis.setSelectedIndices(new int[] {});
		contentPane.add(teamsthuis, "4, 10, center, center");
*/
		
		lblTeamZaaldienst = new JLabel("Team zaaldienst");
		contentPane.add(lblTeamZaaldienst, "2, 14, fill, default");

		teamzaaldienst = new JTextField();
		contentPane.add(teamzaaldienst, "4, 14, fill, default");
		teamzaaldienst.setColumns(10);
		
		String tm = dag.getTeam();
		
		if (tm == null || tm.equals("null"))
		{
			tm = "";
		}
		
		teamzaaldienst.setText(tm);
		
		teamzaaldienst.addActionListener(this);
		teamzaaldienst.getDocument().addDocumentListener(this);
		
		lblNewLabel = new JLabel("<html>Opmerkingen worden niet weergeven op het zaalsschema,<br />deze zijn enkel informatief voor de maker van het schema</html>");
		contentPane.add(lblNewLabel, "2, 18, 7, 1");
		
		lblOpmerkingen = new JLabel("Opmerkingen");
		contentPane.add(lblOpmerkingen, "2, 16");
		
		opmerkingen = new JTextArea();
		contentPane.add(opmerkingen, "4, 16, 5, 1, fill, fill");
		opmerkingen.setText(dag.getOpmerkingen());
		opmerkingen.getDocument().addDocumentListener(this);
		

		btnOpslaan = new JButton("Opslaan");
		contentPane.add(btnOpslaan, "2, 20, left, default");

		button = new JButton("Annuleren");
		contentPane.add(button, "4, 20, left, default");
		button.addActionListener(new btnCancel());

		btnOpslaan.addActionListener(new btnSave());
		
		
		
		scrollPane = new JScrollPane(contentPane);
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setSize(getSize());
		setContentPane(scrollPane);

		load = true;
		upd();

	}

	private boolean inArray(int[] ar, int i) {
		for (int j = 0; j < ar.length; j++) {
			if (ar[j] == i) {
				return true;
			}
		}
		return false;
	}

	protected void askSave() {
//		if (dag.isChanged()) {
		if (true){
			// Custom button text
			Object[] options = { "Save", "Discard", "Cancel" };
			int n = JOptionPane
					.showOptionDialog(
							this,
							"Er zijn nog niet opgeslagen dagen, weet je zeker dat je wilt afsluiten?",
							"Afsluiten", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[2]);
			switch (n) {
			case 0:
				save();
			case 1:
				dispose();
			case 2:
			default:
				return;
			}
		}

		dispose();

	}

	class btnSave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....
			//if (dag.isChanged()) {
				save();
			//} else {
				dispose();
		//	}
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

	/*	Server srv = API.getServer();
		Dag dt = null;
		try {
			dt = srv.saveDag(dag);
		} catch (ServerException e) {

		}*/
//		if (dt == null || !dt.isSaved()) {
			JOptionPane
					.showMessageDialog(
							this,
							"Er is iets misgegaan bij het opslaan, probeer het later nogmaals",
							"Fout tijdens opslaan...",
							JOptionPane.ERROR_MESSAGE);

			btnOpslaan.setEnabled(true);
			btnOpslaan.setText("Opslaan");
			button.setVisible(true);
	/*	} else {
	/		parent.refresh();
			dispose();
		}*/

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		upd();
	}

	private void upd() {
		System.out.println("hiero");
		if (!load)
			return;
		boolean[] open = { false, false, false };

		open[0] = ochtend.isSelected();
		open[1] = middag.isSelected();
		open[2] = avond.isSelected();

	//	dag.setOpen(open);

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

		if (hide) {
			lblZaaldienst.setVisible(false);
		} else {
			lblZaaldienst.setVisible(true);
		}

		int[][] dienst = new int[3][];

		int[] o = dienstochtend.getSelectedIndices();
		int[] m = dienstmiddag.getSelectedIndices();
		int[] a = dienstavond.getSelectedIndices();

		dienst[0] = new int[o.length];
		dienst[1] = new int[m.length];
		dienst[2] = new int[a.length];

/*		model om = (model) dienstochtend.getModel();
		model mm = (model) dienstmiddag.getModel();
		model am = (model) dienstavond.getModel();

		if (o.length > 0) {
			int i = 0;

			for (int j = 0; j < o.length; j++) {
				dienst[0][i] = findId((String) om.getElementAt(o[i]));
				i++;
			}
		}

		if (m.length > 0) {
			int i = 0;

			for (int j = 0; j < m.length; j++) {
				dienst[1][i] = findId((String) mm.getElementAt(m[i]));
				i++;
			}
		}

		if (a.length > 0) {
			int i = 0;

			for (int j = 0; j < a.length; j++) {
				dienst[2][i] = findId((String) am.getElementAt(a[i]));
				i++;
			}
		}*/

	//	dag.setZaaldienst(dienst); // TODO Fix.
		dag.setTeam(teamzaaldienst.getText());
		dag.setOpmerkingen(opmerkingen.getText());

	}

	private int findId(String naam) {
		for (int i = 0; i < s.length; i++) {
			if (naam.equals(s[i].getNaam())) {
				return s[i].getId();
			}
		}
		return 0;
	}

	class model extends AbstractListModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2596297853796654240L;
		String[] values;
		ZaaldienstDto[] dt;

		public model(ZaaldienstDto[] zt) {

			dt = zt;
			values = new String[dt.length];
			for (int i = 0; i < dt.length; i++) {
				values[i] = dt[i].getNaam();
			}
		}

		public int getSize() {
			return values.length;
		}

		public Object getElementAt(int index) {
			return values[index];
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		upd();

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
}
