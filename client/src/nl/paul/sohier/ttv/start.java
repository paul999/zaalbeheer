package nl.paul.sohier.ttv;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JButton;

import javax.swing.JOptionPane;
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
import java.awt.event.WindowEvent;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Collectie;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.Item;
import nl.paul.sohier.ttv.libary.Request;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;

public class start {

	private JFrame frame;
	private JTable table;
	private JLabel lblMonth;
	private DefaultTableModel mtblCalendar;
	private JProgressBar progressBar;

	private int realYear, realMonth, realDay, currentYear, currentMonth;

	private Collectie items;

	private Stack<Request> queue;
	private Stack<Request> wait;

	private int size = 0;

	private int[][] data;
	private Color[][] kleur;

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

	private void addQueue(Request d) {
		if (wait.contains(d) || queue.contains(d)) {
			return;
		}

		System.out.println("Adding dag: " + d + "queue size: " + queue.size()
				+ " wait size: " + wait.size());

		queue.add(d);

		interruptThread();

	}

	private void interruptThread() {
		synchronized (t) {
			t.interrupt();
		}
	}

	public class runQueue implements Runnable {

		public void run() {

			while (true) {
				System.out.println("running while...");
				if (queue.size() != 0) {
					size += queue.size();

					progressBar.setMaximum(size);
					progressBar.setStringPainted(true);

					Iterator<Request> itr = queue.iterator();

					while (itr.hasNext()) {

						Request request = queue.pop();
						wait.push(request);

						System.out.println("Creating task for " + request);

						Task t = new Task(request);
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
		private Request task;

		public Task(Request d) {
			task = d;
		}

		/*
		 * Main task. Executed in background thread.
		 */
		@Override
		public Void doInBackground() {

			System.out.println("Running dag for " + task);

			Item add = null;

			if (task instanceof DagRequest) {

				add = API.getDag((DagRequest) task);

			} else if (task instanceof ZaalDienstRequest) {
				add = API.getZaalDienst((ZaalDienstRequest) task);

			}

			else // Don't remove this else! (It will cause a infitite loop)
			{
				System.out.println("Task not defined.");
				throw new RuntimeException("Task not defined");
			}
			if (add == null) {
				System.out.println("Error?");
				return null;
			}

			items.add(add);

			wait.remove(task);

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

			if (size < 0) {
				size = 0;
			}

			if (size == 0) {
				refreshCalendar(currentMonth, currentYear);
			}

		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		items = new Collectie();

		queue = new Stack<Request>();
		wait = new Stack<Request>();

		frame = new JFrame();
		frame.setTitle("TTV Alexandria zaalbeheer");
		// frame.setBounds(100, 100, 450, 300);
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent winEvt) {
				// Perhaps ask user if they want to save any unsaved files
				// first.
				askSave();
			}
		});

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
		String[] headers = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
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

					EditDay frame = new EditDay(new DagRequest(data[row][col],
							currentMonth, currentYear));
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
		table.setRowHeight(100);
		mtblCalendar.setColumnCount(7);
		mtblCalendar.setRowCount(6);

		data = new int[7][7];
		kleur = new Color[7][7];

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
		mntmVoegPersoonToe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddZaalWacht w = new AddZaalWacht();
				w.setVisible(true);

			}
		});

		JMenuItem mntmSave = new JMenuItem("Opslaan");
		mnBestand.add(mntmSave);
		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});

		JMenuItem mntmRefresh = new JMenuItem("Vernieuwen");
		mnBestand.add(mntmRefresh);
		mntmRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				queue.removeAllElements();
				wait.removeAllElements();
				items.removeAll();
				refreshCalendar(currentMonth, currentYear);
			}
		});

		JMenuItem mntmAfsluiten = new JMenuItem("Afsluiten");
		mntmAfsluiten.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				askSave();

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

	int count = 0;

	private void refreshCalendar(int month, int year) {
		count++;
		System.out.println("Count for this function: " + count);
		// Variables
		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int nod, column; // Number Of Days, Start Of Month

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
				kleur[i][j] = new Color(255, 255, 255);
			}
		}

		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		column = cal.get(GregorianCalendar.DAY_OF_WEEK);

		if (column == 1) {
			column = 6;
		} else {
			column -= 2;
		}

		int row = 0;

		// Draw calendar
		for (int i = 1; i <= nod; i++) {
			try {

				mtblCalendar.setValueAt(i, row, column);
				data[row][column] = i;

				Color kl;

				if (column == 5 || column == 6) { // Week-end
					kl = new Color(255, 220, 220);
				} else { // Week
					kl = new Color(255, 255, 255);
				}

				if (i != 0) {
					DagRequest d = new DagRequest(i, currentMonth, currentYear);

					Dag dag = (Dag) items.get(d);

					if (dag == null) {
						kl = new Color(255, 255, 0);
						addQueue(d);
					} else {
						String vl = "<html>" + Integer.toString(i);
						vl += "<br />";

						boolean opn[] = dag.getOpen();
						int diensten[] = dag.getZaaldienst();

						vl += addDeel("Ochtend", opn[0], diensten[0]);
						vl += addDeel("Middag", opn[1], diensten[1]);
						vl += addDeel("Avond", opn[2], diensten[2]);

						if (open) {
							if (!zaaldienst) {
								kl = new Color(255, 0, 0);
							} else {
								kl = new Color(0, 255, 0);
							}
						} else {
							kl = new Color(0xd3, 0xd3, 0xd3);
						}
						open = false;
						zaaldienst = true;

						mtblCalendar.setValueAt(vl, row, column);
					}

					if (i == realDay && currentMonth == realMonth
							&& currentYear == realYear) { // Today
						kl = new Color(220, 220, 255);
					}

				}

				kleur[row][column] = kl;

				column++;

				if (column == 7) {
					column = 0;
					row++;
				}

			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}
		// Apply renderers
		table.setDefaultRenderer(table.getColumnClass(0),
				new tblCalendarRenderer());

	}

	private boolean open = false;
	private boolean zaaldienst = true;

	@SuppressWarnings("unused")
	private String addDeel(String deel, boolean opn, int dienst) {
		String vl = deel + ": ";

		vl += (opn) ? "Open" : "Gesloten";
		vl += "<br />";

		if (opn) {
			open = true;

			if (dienst == 0) {
				// Geen zaaldienst toegewezen.

				vl += "Geen zaaldienst toegewezen<br />";
				zaaldienst = false;
			} else {
				String data = "Ophalen...";
				ZaalDienstRequest r = new ZaalDienstRequest(dienst);

				if (r == null) {
					System.out.println("Null request created?" + dienst);
				}

				ZaalDienst dt = (ZaalDienst) items.get(r);
				if (dt == null) {
					addQueue(r);
				} else {
					data = dt.getNaam();
				}

				vl += "Zaaldienst: " + data + "<br />";
			}
		}
		return vl;
	}

	private void askSave() {
		System.out.println("askSave");

		if (items.changed()) {
			System.out.println("Non saved days");

			// Custom button text
			Object[] options = { "Save", "Discard", "Cancel" };
			int n = JOptionPane
					.showOptionDialog(
							frame,
							"Er zijn nog niet opgeslagen dagen, weet je zeker dat je wilt afsluiten?",
							"Afsluiten", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[2]);

			System.out.println("Waarde: " + n);

			switch (n) {
			case 0:
				save();
			case 1:
				System.exit(0);
			case 2:
			default:
				System.out.println("Return");
				return;
			}
		}

		System.exit(0);
	}

	private void save() {
		System.out.println("Save");
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

			setBackground(kleur[row][column]);
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
