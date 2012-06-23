package nl.paul.sohier.ttv.libary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Collectie {
	private ArrayList<Item>items;
	
	@SuppressWarnings("unused")
	private boolean inUse = false;

	/**
	 * 
	 */
	public Collectie() {
		items = new ArrayList<Item>();
	}

	/**
	 * @param Dag get
	 * @return
	 */
	public Item get(Request get) {
		
		waitForLock();
		
		Iterator<Item> itr = items.iterator();

		while (itr.hasNext()) {
			try {
				Object t = itr.next();
				Item d = (Item) t;
				
				if (d == null)
				{
					System.out.println("This is not good...");
					System.out.println(t);
				}

				if (d != null && d.equals(get)) {
					removeLock();
					return d;
				}
			} catch (NullPointerException e) {
				removeLock();
				return null;
			} catch (NoSuchElementException e) {
				removeLock();
				return null;
			}

		}
		removeLock();

		return null;
	}

	/**
	 * @param add
	 */
	public void add(Item add) {
		if (add == null)
		{
			System.out.println("Cant add a null item!");
			throw new RuntimeException("Cant add a null item!");
		}
		
		System.out.println("Adding dag " + add);
		remove(add);
		waitForLock();
		items.add(add);
		removeLock();
	}

	/**
	 * @param delete
	 */
	private void remove(Item delete) {
		waitForLock();
		Iterator<Item> itr = items.iterator();

		while (itr.hasNext()) {
			Item d = (Item) itr.next();

			if (d.equals(delete)) {
				itr.remove();
				removeLock();
				return;
			}
		}
		removeLock();
	}
	
	/**
	 * 
	 */
	private void waitForLock()
	{
		inUse = true;
	}
	
	/**
	 * 
	 */
	private void removeLock()
	{
		inUse = false;
	}
	
	public boolean changed(){
		waitForLock();
		Iterator<Item> itr = items.iterator();

		while (itr.hasNext()) {
			Item d = (Item) itr.next();

			if (d.isChanged()) {
				
				removeLock();
				return true;
			}
		}
		removeLock();		
		
		return false;
	}
	public void removeAll(){
		items.clear();
	}
}
