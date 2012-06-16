package nl.paul.sohier.ttv.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import nl.paul.sohier.ttv.libary.Dag;

public class DagenCollectie {
	private ArrayList<Dag> dag;
	
	@SuppressWarnings("unused")
	private boolean inUse = false;

	public DagenCollectie() {
		dag = new ArrayList<Dag>();
	}

	public Dag getDag(Dag get) {
		
		waitForLock();
		
		Iterator<Dag> itr = dag.iterator();

		while (itr.hasNext()) {
			try {
				Dag d = (Dag) itr.next();

				if (d.equals(get)) {
					removeLock();
					return d;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				removeLock();
				return null;
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				removeLock();
				return null;
			}

		}
		removeLock();

		return null;
	}

	public void addDag(Dag add) {
		
		System.out.println("Adding dag " + add);
		removeDag(add);
		waitForLock();
		dag.add(add);
		removeLock();
	}

	private void removeDag(Dag delete) {
		waitForLock();
		Iterator<Dag> itr = dag.iterator();

		while (itr.hasNext()) {
			Dag d = (Dag) itr.next();

			if (d.equals(delete)) {
				itr.remove();
				return;
			}
		}
		removeLock();
	}
	
	private void waitForLock()
	{
		inUse = true;
	}
	private void removeLock()
	{
		inUse = false;
	}
}
