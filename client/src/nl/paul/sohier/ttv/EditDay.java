package nl.paul.sohier.ttv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nl.paul.sohier.ttv.libary.Dag;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class EditDay extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267299796421679215L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public EditDay(Dag dag) {
		setTitle("Aanpassen dag: " + dag);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblDatum = new JLabel("Datum:");
		contentPane.add(lblDatum, "2, 2");
		
		JLabel lblDate = new JLabel(dag.toString());
		contentPane.add(lblDate, "4, 2");
		
		final JCheckBox chckbxOpen = new JCheckBox("Zaal geopend");
			
		contentPane.add(chckbxOpen, "2, 4");
		
		final JLabel lblZaaldienst = new JLabel("Zaaldienst");
		contentPane.add(lblZaaldienst, "2, 6, right, default");
		
		final JComboBox comboBox = new JComboBox();
		contentPane.add(comboBox, "4, 6, fill, default");
		
		JButton btnOpslaan = new JButton("Opslaan");
		contentPane.add(btnOpslaan, "2, 8");
		
		JButton btnAnnuleren = new JButton("Annuleren");
		contentPane.add(btnAnnuleren, "4, 8");
		
		btnOpslaan.addActionListener(new btnSave());
		btnAnnuleren.addActionListener(new btnCancel());
		
		chckbxOpen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxOpen.isSelected())
				{
					comboBox.setVisible(true);
					lblZaaldienst.setVisible(true);					
				}
				else
				{
					comboBox.setVisible(false);
					lblZaaldienst.setVisible(false);					
				}
				
			}});
		
		if (dag.isOpen())
		{
			chckbxOpen.setSelected(true);
		}
		else
		{
			comboBox.setVisible(false);
			lblZaaldienst.setVisible(false);
		}		
	}
	class btnSave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Save it...
		}
	}
	class btnCancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO: Check if we want to validate the changed fields before 
			// Actually canceling it...
			dispose();
		}
	}
}
