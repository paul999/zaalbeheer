package nl.sohier.paul.ttv.a66.zaaldiensten;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Stack;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Collectie;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.Item;
import nl.paul.sohier.ttv.libary.Request;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.ActionBar.OnNavigationListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class Zaalbeheer extends FragmentActivity implements
		OnNavigationListener {

	private static final int PROGRESS_DIALOG = 0;

	protected static final int DISMISS_DIALOG = 1;

	protected static final int DIALOG_SIZE = 2;

	private ProgressDialog progressDialog;

	protected Dialog onCreateDialog(int id) {
		Log.d("ttv", "oncreatedialog");
		switch (id) {
		case PROGRESS_DIALOG:
			progressDialog = new ProgressDialog(Zaalbeheer.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMessage("Loading...");
			progressDialog.setProgress(0);
			// progressDialog.show();
			return progressDialog;
		default:
			return null;
		}
	}

	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case PROGRESS_DIALOG:
			progressDialog.setProgress(0);
		}

	}

	private int realMonth;

	private int realDay;

	private int currentMonth;

	private int currentYear;

	private Stack<Request> queue;
	private Stack<Request> wait;

	private Thread t;

	private int size = 0;

	private GregorianCalendar cal;

	static Zaalbeheer zb;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zaalbeheer);
		zb = this;

		API.items = new Collectie();

		queue = new Stack<Request>();
		wait = new Stack<Request>();

		t = new Thread(new runQueue());
		t.start();

		// Get real month/year
		cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month
		currentMonth = realMonth;
		currentYear = cal.get(GregorianCalendar.YEAR);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.action_list,
				android.R.layout.simple_spinner_dropdown_item);

		actionBar.setListNavigationCallbacks(mSpinnerAdapter, this);
		actionBar.setSelectedNavigationItem(1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_zaalbeheer, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {

		int nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		DagRequest d[] = new DagRequest[nod];

		// Draw calendar
		for (int j = 1; j <= nod; j++) {

			d[j-1] = new DagRequest(j, currentMonth, currentYear);
		}
		addQueue(d);

		return false;
	}

	public void addQueue(Request d) {
		if (wait.contains(d) || queue.contains(d) || API.items.get(d) != null) {
			return;
		}

		queue.add(d);

		interruptThread();

	}

	public void addQueue(Request[] d) {
		for (int i = 0; i < d.length; i++) {
			if (wait.contains(d[i]) || queue.contains(d[i]) || API.items.get(d[i]) != null) {
				Log.d("ttv", "Already, queued, waiting or saved...");
				continue;
			}

			queue.add(d[i]);
		}

		interruptThread();
	}

	private void interruptThread() {
		synchronized (t) {
			t.interrupt();
		}
	}

	final static Handler handler = new Handler() {
		@SuppressWarnings("deprecation")
		public void handleMessage(Message msg) {
			switch (msg.arg2) {
			case SHOW_DIALOG:
				zb.showDialog(msg.arg1);
				break;
			case DISMISS_DIALOG:
				zb.dismissDialog(msg.arg1);
				break;
			case DIALOG_SIZE:
				zb.progressDialog.setMax(msg.arg1);
				zb.progressDialog.setProgress(0);
				break;
			}

		}
	};

	public static final int SHOW_DIALOG = 0;

	private void sendMsg(int a1, int a2) {
		Message m = handler.obtainMessage();
		m.arg1 = a1;
		m.arg2 = a2;
		handler.sendMessage(m);
	}

	public class runQueue implements Runnable {

		public void run() {

			while (true) {
				if (queue.size() != 0) {
					size += queue.size();

					sendMsg(PROGRESS_DIALOG, SHOW_DIALOG);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					sendMsg(queue.size(), DIALOG_SIZE);

					Iterator<Request> itr = queue.iterator();

					while (itr.hasNext()) {

						Request request = queue.pop();
						wait.push(request);

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

	class Task extends AsyncTask<Void, Void, Void> {
		private Request task;

		public Task(Request d) {
			task = d;
		}

		/*
		 * Main task. Executed in background thread.
		 */
		public Void doInBackground(Void... params) {
			Item add = null;

			if (task instanceof DagRequest) {

				Srv s = new Srv("getSavedDag");

				s.addProp("request", task);
				SoapObject t = s.send();

				add = convertDag(t);

			} else if (task instanceof ZaalDienstRequest) {
				// add = API.getZaalDienst((ZaalDienstRequest) task);

			}

			else // Don't remove this else! (It will cause a infitite loop)
			{
				throw new RuntimeException("Task not defined");
			}

			if (add == null) {

				return null;
			}

			API.items.add(add);

			wait.remove(task);
			done();

			return null;

		}

		/*
		 * Executed in event dispatching thread
		 */
		public void done() {
			int progress = progressDialog.getProgress() + 1;
			progressDialog.setProgress(progress);

			size--;

			if (size < 0) {
				size = 0;
			}

			Log.d("ttv", "Size: " + size);

			if (size == 0) {
				// Done.
				Log.d("ttv", "Dismissing dialog...");
				sendMsg(PROGRESS_DIALOG, DISMISS_DIALOG);
			}

		}

	}

	public static Dag convertDag(SoapObject c) {
		Dag d = new Dag();

		try {

			d.setChanged(Boolean.parseBoolean(c.getProperty("changed")
					.toString()));
			d.setDag(Integer.parseInt(c.getProperty("dag").toString()));
			d.setId(Integer.parseInt(c.getProperty("id").toString()));
			d.setJaar(Integer.parseInt(c.getProperty("jaar").toString()));
			d.setMaand(Integer.parseInt(c.getProperty("maand").toString()));

			boolean[] o = {
					Boolean.parseBoolean(c.getProperty("open").toString()),
					Boolean.parseBoolean(c.getProperty(6).toString()),
					Boolean.parseBoolean(c.getProperty(7).toString()) };
			d.setOpen(o);
			d.setSaved(Boolean.parseBoolean(c.getProperty("saved").toString()));
			d.setChanged(false);
		} catch (Exception e) {
			Log.d("ttv", "Parsing error...", e);
			return null;
		}

		return d;
	}

	/**
	 * @return the queue
	 */
	public Stack<Request> getQueue() {
		return queue;
	}

	/**
	 * @param queue
	 *            the queue to set
	 */
	public void setQueue(Stack<Request> queue) {
		this.queue = queue;
	}

	/**
	 * @return the wait
	 */
	public Stack<Request> getWait() {
		return wait;
	}

	/**
	 * @param wait
	 *            the wait to set
	 */
	public void setWait(Stack<Request> wait) {
		this.wait = wait;
	}
}