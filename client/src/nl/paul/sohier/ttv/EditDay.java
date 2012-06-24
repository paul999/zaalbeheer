package nl.paul.sohier.ttv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import javax.swing.JButton;
import javax.swing.JOptionPane;

import javax.swing.JList;
import javax.swing.AbstractListModel;

public class EditDay extends JFrame implements ActionListener, ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267299796421679215L;
	private JPanel contentPane;

	private Dag dag;
	private JCheckBox ochtend;
	private JCheckBox middag;
	private JCheckBox avond;
	private JLabel lblZaaldienst;
	private JButton btnOpslaan;
	private JButton button;
	private ZaalDienst[] s;
	private int[] ids;
	private start parent;
	private boolean load = false;
	private JList dienstochtend;
	private JList dienstmiddag;
	private JList dienstavond;

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

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		Server srv = API.getServer(this);
		dag = srv.getSavedDag(request);

		System.out.println("Dag voor editing: " + dag);

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

		dienstochtend = new JList();
		dienstochtend.setVisibleRowCount(4);

		contentPane.add(dienstochtend, "4, 8, fill, fill");

		dienstmiddag = new JList();
		dienstmiddag.setVisibleRowCount(4);
		contentPane.add(dienstmiddag, "6, 8, fill, fill");

		dienstavond = new JList();
		dienstavond.setVisibleRowCount(4);
		contentPane.add(dienstavond, "8, 8, fill, fill");

		btnOpslaan = new JButton("Opslaan");
		contentPane.add(btnOpslaan, "2, 10, left, default");

		btnOpslaan.addActionListener(new btnSave());

		button = new JButton("Annuleren");
		contentPane.add(button, "4, 10, left, default");
		button.addActionListener(new btnCancel());

		ochtend.addActionListener(this);
		middag.addActionListener(this);
		avond.addActionListener(this);
		
		dienstochtend.addListSelectionListener(this);
		dienstmiddag.addListSelectionListener(this);
		dienstavond.addListSelectionListener(this);
		

		// Set initial values.
		ochtend.setSelected(dag.getDeelOpen(0));
		middag.setSelected(dag.getDeelOpen(1));
		avond.setSelected(dag.getDeelOpen(2));

		s = srv.getAlleZaalDiensten();

		ids = new int[s.length];

		int[] zochtend = dag.getDeelZaalDienst(0);
		int[] zmiddag = dag.getDeelZaalDienst(1);
		int[] zavond = dag.getDeelZaalDienst(2);

		int[] lo = new int[zochtend.length];
		int[] lm = new int[zmiddag.length];
		int[] la = new int[zavond.length];

		int o = 0, m = 0, a = 0;

		for (int i = 0; i < s.length; i++) {
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
			} else {
				System.out.println("null? :(");
			}
		}
		model mo = new model(s);

		dienstochtend.setModel(mo);
		dienstavond.setModel(mo);
		dienstmiddag.setModel(mo);
		
		dienstochtend.setSelectedIndices(lo);
		dienstmiddag.setSelectedIndices(lm);
		dienstavond.setSelectedIndices(la);

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
			if (dag.isChanged()) {
				System.out.println("Need to save...");
				save();
			} else {
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

		Server srv = API.getServer(this);
		Dag dt = srv.saveDag(dag);
		if (dt == null || !dt.isSaved()) {
			JOptionPane
					.showMessageDialog(
							this,
							"Er is iets misgegaan bij het opslaan, probeer het later nogmaals",
							"Fout tijdens opslaan...",
							JOptionPane.ERROR_MESSAGE);

			btnOpslaan.setEnabled(true);
			btnOpslaan.setText("Opslaan");
			button.setVisible(true);
		} else {
			parent.refresh();
			dispose();
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		upd();
	}

	private void upd() {
		if (!load)
			return;
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
		
		model om = (model)dienstochtend.getModel();
		model mm = (model)dienstmiddag.getModel();
		model am = (model)dienstavond.getModel();
		
		if (o.length > 0)
		{
			int i = 0;
			
			for (int j = 0; j < o.length; j++)
			{
				dienst[0][i] = findId((String)om.getElementAt(o[i]));
				i++;
			}
		}
		
		if (m.length > 0)
		{
			int i = 0;
			
			for (int j = 0; j < m.length; j++)
			{
				dienst[1][i] = findId((String)mm.getElementAt(m[i]));
				i++;
			}
		}
		
		if (a.length > 0)
		{
			int i = 0;
			
			for (int j = 0; j < a.length; j++)
			{
				dienst[2][i] = findId((String)am.getElementAt(a[i]));
				i++;
			}
		}
		
		for (int i = 0; i < dienst.length; i++)
		{
			System.out.println("Dienst[i] = " + i);
			
			for (int j = 0; j < dienst[i].length; j++)
			{
				System.out.println("Data: " + dienst[i][j]);
			}
		}
		
		dag.setZaaldienst(dienst); // TODO Fix.

	}

	private int findId(String naam) {
		for (int i = 0; i < s.length; i++) {
			if (naam.equals(s[i].getNaam())) {
				System.out.println("Found you :D" + naam + " id: " + s[i].getId());
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
		ZaalDienst[] dt;

		public model(ZaalDienst[] zt) {

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
}
