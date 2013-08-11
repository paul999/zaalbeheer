/**
 * 
 */
package nl.ttva66.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.interfaces.ZaalDienstRequest;
import nl.ttva66.jaxws.Service;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author paul_000
 * 
 */
public class EditZaalwacht extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8566966515062100328L;
	private JPanel contentPane;
	private JComboBox<ZaaldienstDto> select;

	public EditZaalwacht() {
		setTitle("selecteer zaalwacht om aan te passen.");

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

		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblSelect = new JLabel("Gebruiker");
		contentPane.add(lblSelect, "2, 2, right, default");

		select = new JComboBox<ZaaldienstDto>();
		contentPane.add(select, "4, 2, right, default");

		final JButton btnAanpassen = new JButton("Aanpassen");
		btnAanpassen.setEnabled(false);
		contentPane.add(btnAanpassen, "2, 4");
		btnAanpassen.addActionListener(new btnAanpassen());

		JButton btnAnnuleer = new JButton("Annuleer");
		contentPane.add(btnAnnuleer, "4, 4");
		btnAnnuleer.addActionListener(new btnCancel());

		this.setVisible(true);

		(new Thread() {

			@Override
			public void run() {
				Service srv = API.getServer();
				
				for (Integer i : srv.listZaaldiensten())
				{
					ZaaldienstDto dt = (ZaaldienstDto) API.items.get(new ZaalDienstRequest(i));
					
					if (dt == null)
					{
						dt =  srv.getZaaldienstById(new ZaalDienstRequest(i));
					}
					select.addItem(dt);
				}
				btnAanpassen.setEnabled(true);
			}
		}).start();
	}

	class btnCancel implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();

		}
	}

	class btnAanpassen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ZaaldienstDto dt = (ZaaldienstDto) select.getSelectedItem();
			
			dispose();
			new AddZaalWacht(dt).setVisible(true);
			
		}
	}
}
