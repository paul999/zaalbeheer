package nl.sohier.paul.ttv.a66.zaaldiensten;

import nl.sohier.paul.ttv.a66.zaaldiensten.MaandFragment.GridCellAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;

public class OptieFragment  extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {



//			view = inflater.inflate(R.layout.month_main, container, false);
			
	        ScrollView scroller = new ScrollView(getActivity());
	        TextView text = new TextView(getActivity());
	        int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
	                4, getActivity().getResources().getDisplayMetrics());
	        text.setPadding(padding, padding, padding, padding);
	        scroller.addView(text);
	        text.setText("Selecteer maand");

			return scroller;
		}
}
