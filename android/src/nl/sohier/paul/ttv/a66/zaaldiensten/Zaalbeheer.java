package nl.sohier.paul.ttv.a66.zaaldiensten;

import java.util.GregorianCalendar;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
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

public class Zaalbeheer extends FragmentActivity implements OnNavigationListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

	private int realMonth;

	private int realDay;

	private int currentMonth;

	private int currentYear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaalbeheer);
        
		// Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); // Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); // Get day
		realMonth = cal.get(GregorianCalendar.MONTH); // Get month      
		currentMonth = realMonth;
		currentYear = cal.get(GregorianCalendar.YEAR);
        
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(currentMonth, true);
        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.action_list,
                android.R.layout.simple_spinner_dropdown_item);
        
        
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, this);
        actionBar.setSelectedNavigationItem(1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_zaalbeheer, menu);
        return true;
    }

    


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    	private String[] months = { "januari", "februari", "maart", "april", "mei",
				"juni", "juli", "augustus", "september", "oktober", "november",
				"december" };

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
        	Log.d("ttv", "Item: " + i);
            
            
            Fragment fragment = new MaandFragment();
            Bundle args = new Bundle();
            args.putInt("maand", i );
            args.putInt("jaar", currentYear);
            fragment.setArguments(args);
            
            return fragment;
        }

        @Override
        public int getCount() {
            return months.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
    		
    		
    		if (months[position] != null)
    		{
    			return months[position];
    		}
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        public DummySectionFragment() {
        }

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            Bundle args = getArguments();
            textView.setText(Integer.toString(args.getInt(ARG_SECTION_NUMBER)));
            return textView;
        }
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Log.d("ttv", "Changed year? :)");
		return false;
	}
}