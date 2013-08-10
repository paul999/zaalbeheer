package nl.paul.sohier.ttv;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import javax.swing.JButton;

import nl.ttva66.client.Service;
import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.DienstDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.interfaces.ZaalDienstRequest;

import javax.swing.JCheckBox;

public class ChangeDienstOpties extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5361543074415667952L;
	private JPanel contentPane;
	private DagDto dag;
	private MainWindow parent;

	/**
	 * Create the frame.
	 */
	public ChangeDienstOpties(MainWindow m, DagDto dag) {
		this.parent = m;
		this.dag = dag;

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 549, 296);

		/*
		 * addWindowListener(new java.awt.event.WindowAdapter() { public void
		 * windowClosing(WindowEvent winEvt) { save(); } });
		 */

		pack();
		setExtendedState(Frame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		int nr = dag.getDienst().size() * 2 + 2;

		RowSpec[] rp = new RowSpec[nr];

		for (int i = 0; i < nr; i += 2) {
			rp[i] = FormFactory.RELATED_GAP_ROWSPEC;
			rp[i + 1] = FormFactory.DEFAULT_ROWSPEC;
		}

		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, rp));

		int i = 2;
		for (final DienstDto dienst : dag.getDienst()) {
			JPanel panel = new JPanel();

			String nm = ((ZaaldienstDto) API.items.get(new ZaalDienstRequest(
					dienst.getZaaldienst()))).getNaam();
			panel.setBorder(BorderFactory.createTitledBorder(dienst.getType()
					.getNaam() + " " + nm));

			panel.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC, }));
			panel.add(new JLabel("Tijden"), "2, 2, left, default");
			panel.add(new JLabel(dienst.getType().getStart() + " - "
					+ dienst.getType().getEind()), "4, 2, left, default");

			panel.add(new JLabel("Definitief"), "2, 4, left, default");

			final JCheckBox def = new JCheckBox();
			panel.add(def, "4, 4, left, default");
			def.setSelected(dienst.isDefinitief());

			def.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dienst.setDefinitief(def.isSelected());
				}
			});

			panel.add(new JLabel("Backup"), "2, 6, left, default");
			final JCheckBox back = new JCheckBox();
			back.setSelected(dienst.isBackup());
			
			back.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dienst.setBackup(back.isSelected());
				}
			});			
			panel.add(back, "4, 6, left, default");

			contentPane.add(panel, "2, " + i + ", left, default");
			i += 2;
		}

		JButton button = new JButton("Opslaan");
		contentPane.add(button, "2, " + nr + ", left, default");
		button.addActionListener(new btnSave());

	}

	private void save() {
		Service srv = API.getServer();
		srv.saveDag(dag);
		parent.refresh();
		dispose();
	}

	class btnSave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it....
			save();
		}
	}
}
