package nl.paul.sohier.ttv;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JButton;

import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.paul.sohier.ttv.classes.DagenCollectie;
import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Dag;

public class start {

	private JFrame frame;
	private JTable table;
	private JLabel lblMonth;
	private DefaultTableModel mtblCalendar;
	private JProgressBar progressBar;

	private int realYear, realMonth, realDay, currentYear, currentMonth;

	private DagenCollectie dagen;

	private Stack<Dag> queue;

	private int size = 0;

	private int[][] data;

	private Thread t;
	static start window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public start() {
		initialize();

		t = new Thread(new runQueue());
		t.start();

		refreshCalendar(realMonth, realYear); // Refresh calendar

	}

	private void addQueue(Dag d) {
		queue.add(d);

		interruptThread();

	}

	private void interruptThread() {
		synchronized (t) {
			System.out.print("Interrupt");
			t.interrupt();
		}
	}

	public class runQueue implements Runnable {

		public void run() {

			while (true) {
				System.out.println("Running");

				if (queue.size() != 0) {
					size += queue.size();

					progressBar.setMaximum(size);
					progressBar.setStringPainted(true);

					Iterator<Dag> itr = queue.iterator();

					while (itr.hasNext()) {

						Task t = new Task(queue.pop());
						t.execute();
					}

				} else {
					try {
						synchronized (this) {
							this.wait();
						}

					} catch (InterruptedException e1) {

					}

				}
			}
		}
	}

	class Task extends SwingWorker<Void, Void> {
		private Dag dag;

		public Task(Dag d) {
			dag = d;
		}

		/*
		 * Main task. Executed in background thread.
		 */
		@Override
		public Void doInBackground() {
			Dag dagt = null;

			if (dagt == null) {
				dagt = API.createDag(dag.getDag(), dag.getMaand(),
						dag.getJaar());
			}

			dagen.addDag(dagt);

			return null;

		}

		/*
		 * Executed in event dispatching thread
		 */
		@Override
		public void done() {
			int progress = progressBar.getValue() + 1;
			progressBar.setValue(progress);

			size--;

			if (size == 0) {
				refreshCalendar(currentMonth, currentYear);

			}

		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dagen = new DagenCollectie();

		queue = new Stack<Dag>();

		frame = new JFrame();
		frame.setTitle("TTV Alexandria zaalbeheer");
		// frame.setBounds(100, 100, 450, 300);
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mtblCalendar = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1038831398502625463L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table = new JTable(mtblCalendar);
		JScrollPane stblCalendar = new JScrollPane(table);

		frame.getContentPane().add(stblCalendar, BorderLayout.CENTER);

		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		realYear = cal.get(GregorianCalendar.YEAR); // Get year
		currentMonth = realMonth; // Match month and year
		currentYear = realYear;

		// Add headers
		String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		for (int i = 0; i < 7; i++) {
			mtblCalendar.addColumn(headers[i]);
		}

		table.getParent().setBackground(table.getBackground()); // Setbackground

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());

				// Going to check if value is a day.

				if (data[row][col] != 0) {
					// Here should we open a new frame.

					EditDay frame = new EditDay(dagen.getDag(new Dag(
							data[row][col], currentMonth, currentYear)));
					frame.setVisible(true);

				} else {
					return;
				}
			}
		});

		// No resize/reorder
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);

		// Single cell selection
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set row/column count
		table.setRowHeight(57);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		data = new int[7][7];

		JButton button_1 = new JButton(">");
		frame.getContentPane().add(button_1, BorderLayout.EAST);

		JButton button_2 = new JButton("<");
		frame.getContentPane().add(button_2, BorderLayout.WEST);

		button_2.addActionListener(new btnPrev_Action());
		button_1.addActionListener(new btnNext_Action());

		progressBar = new JProgressBar();
		frame.getContentPane().add(progressBar, BorderLayout.SOUTH);

		lblMonth = new JLabel("");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblMonth, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnBestand = new JMenu("Bestand");
		menuBar.add(mnBestand);

		JMenuItem mntmVoegPersoonToe = new JMenuItem("Voeg persoon toe");
		mnBestand.add(mntmVoegPersoonToe);

		JMenuItem mntmAfsluiten = new JMenuItem("Afsluiten");
		mntmAfsluiten.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);

			}

		});

		mnBestand.add(mntmAfsluiten);

		JMenu mnUitvoer = new JMenu("Uitvoer");
		menuBar.add(mnUitvoer);

		JMenuItem mntmScherm = new JMenuItem("Scherm");
		mnUitvoer.add(mntmScherm);

		JMenuItem mntmIcal = new JMenuItem("iCal");
		mnUitvoer.add(mntmIcal);

		JMenuItem mntmExcel = new JMenuItem("Excel");
		mnUitvoer.add(mntmExcel);

		JMenuItem mntmPdf = new JMenuItem("PDF");
		mnUitvoer.add(mntmPdf);
	}

	private void refreshCalendar(int month, int year) {
		// Variables
		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int nod, som; // Number Of Days, Start Of Month

		// Allow/disallow buttons

		// if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);}
		// //Too early
		// if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);}
		// //Too late
		String st = months[month] + " " + currentYear;

		lblMonth.setText(st); // Refresh the month label (at the top)
		// lblMonth.setBounds(160 - lblMonth.getPreferredSize().width / 2, 25,
		// 180, 25); // Re-align label with calendar
		// cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct
		// year in the combo box

		// Clear table
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				mtblCalendar.setValueAt(null, i, j);
				data[i][j] = 0;
			}
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		// Draw calendar
		for (int i = 1; i <= nod; i++) {
			int row = new Integer((i + som - 2) / 7);
			int column = (i + som - 2) % 7;
			mtblCalendar.setValueAt(i, row, column);
			data[row][column] = i;
		}

		// Apply renderers
		table.setDefaultRenderer(table.getColumnClass(0),
				new tblCalendarRenderer());

	}

	class tblCalendarRenderer extends DefaultTableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2563920070160037133L;

		public Component getTableCellRendererComponent(JTable table, Object v,
				boolean selected, boolean focused, int row, int column) {
			super.getTableCellRendererComponent(table, v, selected, focused,
					row, column);

			if (column == 0 || column == 6) { // Week-end
				setBackground(new Color(255, 220, 220));
			} else { // Week
				setBackground(new Color(255, 255, 255));
			}

			int value = (data[row][column]);

			if (value != 0) {
				Dag d = new Dag(value, currentMonth, currentYear);

				Dag dag = dagen.getDag(d);

				if (dag == null) {
					setBackground(new Color(255, 255, 0));
					addQueue(d);
				} else {
					String vl = "<html>" + Integer.toString(value);
					vl += "<br />";
					vl += (dag.isOpen()) ? "Open" : "Gesloten";

					if (dag.isOpen()) {

						setBackground(new Color(0, 255, 0));

						if (dag.getZaaldienst() == null) {
							// Geen zaaldienst toegewezen.
							setBackground(new Color(255, 0, 0));
							vl += "<br />Geen zaaldienst toegewezen";
						} else {
							vl += "<br />Zaaldienst: "
									+ dag.getZaaldienst().getNaam();
						}
					} else {
						setBackground(new Color(0xd3, 0xd3, 0xd3));
					}
					mtblCalendar.setValueAt(vl, row, column);
				}

				if (value == realDay && currentMonth == realMonth
						&& currentYear == realYear) { // Today
					setBackground(new Color(220, 220, 255));
				}

			}
			setBorder(null);
			setForeground(Color.black);
			return this;
		}
	}

	class btnPrev_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 0) { // Back one year
				currentMonth = 11;
				currentYear -= 1;
			} else { // Back one month
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}

	class btnNext_Action implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentMonth == 11) { // Foward one year
				currentMonth = 0;
				currentYear += 1;
			} else { // Foward one month
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
}
