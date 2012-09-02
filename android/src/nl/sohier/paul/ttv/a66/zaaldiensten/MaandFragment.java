/**
 * 
 */
package nl.sohier.paul.ttv.a66.zaaldiensten;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;

import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * @author paul
 * 
 */
public class MaandFragment extends Fragment {
	private static final String tag = "SimpleCalendarViewActivity";

	private GridView calendarView;
	private GridCellAdapter adapter;
	private int month, year;
	private View view;
	Zaalbeheer zb;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		zb = (Zaalbeheer) getActivity();

		view = inflater.inflate(R.layout.month_main, container, false);
		Bundle arg = getArguments();

		Log.d("ttv", "Dag gekregen: " + arg.getInt("maand"));

		month = arg.getInt("maand");
		year = arg.getInt("jaar");

		init();

		return view;
	}

	private void init() {
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: "
				+ year);

		calendarView = (GridView) view.findViewById(R.id.calendar);

		// Initialised
		adapter = new GridCellAdapter(this.getActivity().getBaseContext(),
				R.id.calendar_day_gridcell, month, year);
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);

	}

	public class GridCellAdapter extends BaseAdapter implements OnClickListener {
		private static final String tag = "GridCellAdapter";
		private final Context _context;

		private final List<WeekDag> list;

		private Button gridcell;

		// Days in Current Month
		public GridCellAdapter(Context context, int textViewResourceId,
				final int month, final int year) {
			super();
			this._context = context;
			this.list = new ArrayList<WeekDag>();

			Log.d(tag, "==> Passed in Date FOR Month: " + month + " "
					+ "Year: " + year);
			Calendar calendar = Calendar.getInstance();

			Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
			
			GregorianCalendar cal = new GregorianCalendar(year, month, 1);

			int nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);


			// Draw calendar
			for (int i = 1; i <= nod; i++) {
				
				zb.addQueue(new DagRequest(i, month, year));
			}
			

			// Print Month
			printMonth(month, year);

 

		}

		public WeekDag getItem(int position) {
			return list.get(position);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		/**
		 * Prints Month
		 * 
		 * @param mm
		 * @param yy
		 */
		private void printMonth(int currentMonth, int yy) {
			Log.d(tag, "==> printMonth: mm: " + currentMonth + " " + "yy: "
					+ yy);
			// Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
			GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
			Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

			list.add(new WeekDag("Mon", Color.BLACK, false));
			list.add(new WeekDag("Tue", Color.BLACK, false));
			list.add(new WeekDag("Wed", Color.BLACK, false));
			list.add(new WeekDag("Thu", Color.BLACK, false));
			list.add(new WeekDag("Fri", Color.BLACK, false));
			list.add(new WeekDag("Sat", Color.BLACK, false));
			list.add(new WeekDag("Sun", Color.BLACK, false));

			int nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			int column = cal.get(GregorianCalendar.DAY_OF_WEEK);

			if (column == 1) {
				column = 8;
			}

			for (int i = 0; i < column - 2; i++) {
				list.add(new WeekDag("", Color.BLACK, false));
			}

			// Draw calendar
			for (int i = 1; i <= nod; i++) {
				list.add(new WeekDag(String.valueOf(i), Color.RED, true));
			}

		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) _context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.month_cell, parent, false);
			}

			// Get a reference to the Day gridcell
			gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
			gridcell.setOnClickListener(this);

			// ACCOUNT FOR SPACING

			WeekDag day = list.get(position);
			String theday = day.getText();

			// Set the Day GridCell
			gridcell.setText(theday);

			if (day.isClick()) {
				gridcell.setTag(theday);
			} else {
				gridcell.setTag("nc");
			}

			gridcell.setTextColor(day.getKleur());

			return row;
		}

		@Override
		public void onClick(View view) {
			String day = (String) view.getTag();
			Log.d("ttv", "Selected: " + day);

			if (day.equals("nc")) {
				// Non day clicked. Return.
				return;
			}

			Log.d(tag, "Parsed Date: ");

		}
	}


}
