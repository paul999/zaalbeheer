package nl.paul.sohier.ttv.libary;

import java.util.GregorianCalendar;

public class API {
	public static Dag createDag(int d, int m, int j)
	{
		Dag dag = new Dag(d, m, j);
		
		// Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(j, m, d);

		int som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
		if (som == 1)
		{
			dag.setOpen(false);
		}
		else
		{
			dag.setOpen(true);
		}
		
		return dag;
		
	}
}
