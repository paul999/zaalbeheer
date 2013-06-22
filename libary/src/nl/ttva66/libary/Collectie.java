package nl.ttva66.libary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import nl.ttva66.interfaces.Item;
import nl.ttva66.interfaces.Request;


public class Collectie {
	private ArrayList<Item>items;
	
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
			throw new RuntimeException("Cant add a null item!");
		}
		
		remove(add);
		waitForLock();
		items.add(add);
		removeLock();
	}

	/**
	 * @param delete
	 */
	public void remove(Item delete) {
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
		while (inUse)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
			}
		}
		inUse = true;
	}
	
	/**
	 * 
	 */
	private void removeLock()
	{
		inUse = false;
	}
	
	public Item getChanged()
	{
		waitForLock();
		Iterator<Item> itr = items.iterator();

		while (itr.hasNext()) {
			Item d = (Item) itr.next();

			if (false /*d.isChanged()*/) {
				removeLock();
				return d;
			}
		}
		removeLock();		
		
		return null;		
	}
	
	public boolean changed(){
		waitForLock();
		Iterator<Item> itr = items.iterator();

		while (itr.hasNext()) {
			Item d = (Item) itr.next();

			if (false /*d.isChanged()*/) {
				
				removeLock();
				return true;
			}
		}
		removeLock();		
		
		return false;
	}
	public void removeAll(){
		waitForLock();
		items.clear();
		removeLock();
	}
}
