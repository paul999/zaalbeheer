package nl.paul.sohier.ttv;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.ttva66.client.Service;
import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.DienstDto;
import nl.ttva66.dto.OpenDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.interfaces.ZaalDienstRequest;

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

public class EditDay extends JFrame implements ActionListener, DocumentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267299796421679215L;
	private JPanel contentPane;

	private DagDto dag;
	private MainWindow parent;

	private JLabel lblZaaldienst;
	private JButton btnOpslaan;
	private JButton button;

	private JLabel lblTeams;
	private JLabel lblTeamZaaldienst;
	private JTextField teamzaaldienst;
	private JLabel lblOpmerkingen;
	private JTextArea opmerkingen;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private ArrayList<ZaaldienstDto> diensten;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public EditDay(DagRequest request, MainWindow parent) {
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
		// setContentPane(contentPane);

		dag = (DagDto) API.items.get(request);
		Service srv = API.getServer();

		if (dag == null) {
			dag = srv.getDagByDate(request);

			if (dag == null) {
				throw new RuntimeException("Missing dag");
			}

			API.items.add(dag);

		}

		ColumnSpec[] cp = new ColumnSpec[2 + (dag.getOpens().size() * 2)];

		cp[0] = FormFactory.RELATED_GAP_COLSPEC;
		cp[1] = FormFactory.DEFAULT_COLSPEC;

		for (int i = 0, loc = 2; i < dag.getOpens().size(); i++, loc += 2) {
			cp[loc] = FormFactory.RELATED_GAP_COLSPEC;
			cp[loc + 1] = FormFactory.DEFAULT_COLSPEC;
		}

		contentPane
				.setLayout(new FormLayout(cp, new RowSpec[] {
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblDatum = new JLabel("Datum:");
		contentPane.add(lblDatum, "2, 4");

		JLabel lblDate = new JLabel(dag.toString());
		contentPane.add(lblDate, "4, 4, left, default");

		int start = 4;

		for (DienstDto dto : dag.getDienst()) {
			ensureZd(dto.getZaaldienst());
		}
		List<Integer> zd = srv.listZaaldiensten();
		diensten = new ArrayList<ZaaldienstDto>();
		for (Integer item : zd) {
			ensureZd(item);

			ZaaldienstDto d = (ZaaldienstDto) API.items
					.get(new ZaalDienstRequest(item));

			if (d == null) {
				System.out.println("Request is null? " + item);
			}

			diensten.add(d);
		}

		model mo = new model(diensten);

		for (final OpenDto open : API.sortOpen(dag.getOpens())) {
			JLabel lbl = new JLabel(open.getType().getNaam() + " ("
					+ open.getType().getStart() + " - "
					+ open.getType().getEind() + ")");
			contentPane.add(lbl, start + ", 6, left, default");

			final JCheckBox r = new JCheckBox("");
			r.setSelected(open.isOpen());
			contentPane.add(r, start + ", 8, left, default");

			final JList<ZaaldienstDto> list = new JList<ZaaldienstDto>();
			list.setVisibleRowCount(4);

			list.setModel(mo);
			list.setVisible(open.isOpen());
			contentPane.add(list, start + ", 10, fill, fill");
			
			ArrayList<Integer> listSelect = new ArrayList<Integer>();

			for (DienstDto dienst : dag.getDienst()) {
				if (dienst.getType().getId() == open.getType().getId()) {
					int zi = 0;
					for (ZaaldienstDto z : diensten) {
						if (dienst.getZaaldienst() == z.getId()) {
								listSelect.add(zi);
							break;
						}
						zi++;
					}
				}
			}
			int[] ls = new int[listSelect.size()];
			int ki = 0;
			for (int k : listSelect) {
				ls[ki] = k;
				ki++;
			}

			r.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Open: " + open.getType().getNaam()
							+ " sel " + r.isSelected());
					System.out.println("Opens: " + dag.getOpens());
					Set<OpenDto> dt = dag.getOpens();
					dt.remove(open);
					open.setOpen(r.isSelected());
					dt.add(open);

					r.setSelected(open.isOpen());
					list.setVisible(open.isOpen());
				}
			});
			
			list.setSelectedIndices(ls);

			list.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent arg0) {

					Set<DienstDto> dt = dag.getDienst();

					Iterator<DienstDto> it = dt.iterator();
					
					
					ArrayList<DienstDto> oldDienst = new ArrayList<DienstDto>(); 
					
					while (it.hasNext()) {
						DienstDto d = it.next();
 
						if (d.getType().getId() == open.getType().getId()) {
							for (int sel : list.getSelectedIndices()) {
								if (diensten.get(sel).getId().equals(d.getZaaldienst()))
								{
									oldDienst.add(d);
								}
							}
							
							it.remove();
						}
					}

					for (int sel : list.getSelectedIndices()) {
						ZaaldienstDto choosen = diensten.get(sel);
						
						DienstDto dienst = null;
						
						for (DienstDto d : oldDienst)
						{
							if (choosen.getId().equals(d.getZaaldienst()))
							{
								System.out.println("gevonden voor " + choosen.getNaam());
								dienst = d;
								break;
							}
						}

						if (dienst == null)
						{
							System.out.println("Niet gevonden... " + choosen.getNaam());
							dienst = new DienstDto();
							dienst.setDag(dag.getId());
							dienst.setDefinitief(false);
							dienst.setType(open.getType());
							dienst.setZaaldienst(choosen.getId());
						}
						dt.add(dienst);
					}
					dag.setDienst(dt);

					for (DienstDto x : dt) {
						System.out.println("Naam: "
								+ ((ZaaldienstDto) API.items
										.get(new ZaalDienstRequest(x
												.getZaaldienst()))).getNaam());
					}
					dt = null;
				}
			});
		
			start += 2;
		}

		JLabel lblZaalOpen = new JLabel("Zaal open");
		contentPane.add(lblZaalOpen, "2, 8");

		lblZaaldienst = new JLabel("Zaaldienst");
		contentPane.add(lblZaaldienst, "2, 10");


		lblTeams = new JLabel("Teams thuis:");
		contentPane.add(lblTeams, "2, 12");

		lblTeamZaaldienst = new JLabel("Team zaaldienst");
		contentPane.add(lblTeamZaaldienst, "2, 14, fill, default");

		teamzaaldienst = new JTextField();
		contentPane.add(teamzaaldienst, "4, 14, fill, default");
		teamzaaldienst.setColumns(10);

		String tm = dag.getTeam();

		if (tm == null || tm.equals("null")) {
			tm = "";
		}

		teamzaaldienst.setText(tm);

		teamzaaldienst.addActionListener(this);
		teamzaaldienst.getDocument().addDocumentListener(this);

		lblNewLabel = new JLabel(
				"<html>Opmerkingen worden niet weergeven op het zaalsschema,<br />deze zijn enkel informatief voor de maker van het schema</html>");
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

	}

	private void ensureZd(int zd) {
		ZaalDienstRequest rq = new ZaalDienstRequest(zd);

		Service srv = API.getServer();

		if (API.items.get(rq) == null) {
			System.out.println("Integer: " + zd);
			ZaaldienstDto d = srv.getZaaldienstById(rq);

			System.out.println("Result van server: " + d);	
			System.out.println("ID: " + d.getId());

			API.items.add(d);
		}
	}

	@SuppressWarnings("deprecation")
	private void save() {
		dag.setOpmerkingen(this.opmerkingen.getText());
		dag.setTeam(this.teamzaaldienst.getText());
		
		new ChangeDienstOpties(parent, dag).show();
		
		dispose();
	}

	protected void askSave() {
		// Custom button text
		Object[] options = { "Save", "Discard", "Cancel" };
		int n = JOptionPane
				.showOptionDialog(
						this,
						"Er zijn nog niet opgeslagen dagen, weet je zeker dat je wilt afsluiten?",
						"Afsluiten", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
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

	class btnSave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			save();
		}
	}

	class btnCancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....
			askSave();
		}
	}

	@SuppressWarnings("rawtypes")
	class model extends AbstractListModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2596297853796654240L;
		String[] values;
		List<ZaaldienstDto> dt;

		public model(List<ZaaldienstDto> zt) {

			dt = zt;
			values = new String[dt.size()];
			for (int i = 0; i < dt.size(); i++) {
				ZaaldienstDto d = dt.get(i);
				System.out.println("Naam: " + d.getNaam());

				values[i] = d.getNaam();
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
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
