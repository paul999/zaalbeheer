package nl.paul.sohier.ttv.libary;

/**
 * @author paul
 * 
 */
public class Dag implements DagInterface, Item {
	private int dag = 0;
	private int maand = 0;
	private int jaar = 0;
	private boolean changed = false;
	private boolean saved = false;
	
	private boolean open[] = {false,false,false};
	private int zaaldienst[] = {0, 0, 0};

	public Dag(DagRequest d) {
		dag = d.getDag();
		maand = d.getMaand();
		jaar = d.getJaar();

	}

	public Dag() {
		
	}

	/**
	 * @return the open
	 */
	public boolean[] getOpen() {
		return open;
	}
	public boolean getDeelOpen(int i)
	{
		return open[i];
	}
	public int getDeelOpeni(int i)
	{
		return open[i] ? 1 : 0;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(boolean[] open) {
		changed = true;
		this.open = open;
	}
	public void setOpen(int optie, boolean open)
	{
		changed = true;
		this.open[optie] = open;
	}

	/**
	 * @return the zaaldienst
	 */
	public int[] getZaaldienst() {
		return zaaldienst;
	}

	/**
	 * @param zaaldienst the zaaldienst to set
	 */
	public void setZaaldienst(int[] zaaldienst) {
		changed = true;
		this.zaaldienst = zaaldienst;
	}

	/**
	 * @param dag the dag to set
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}

	/**
	 * @param maand the maand to set
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}

	/**
	 * @param jaar the jaar to set
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	/**
	 * @param changed the changed to set
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * @return the dag
	 */
	public int getDag() {
		return dag;
	}


	/**
	 * @return the maand
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 * @return the jaar
	 */
	public int getJaar() {
		return jaar;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		DagInterface ob = null;
		if (obj instanceof Dag) {
			ob = (Dag) obj;
		} else if (obj instanceof DagRequest) {
			ob = (DagRequest) obj;
		} else
			return false;

		if (getJaar() != ob.getJaar())
			return false;

		if (getDag() != ob.getDag())
			return false;

		if (getMaand() != ob.getMaand())
			return false;

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getDag() + "/" + (getMaand() + 1) + "/" + getJaar();
	}

	/**
	 * @return the changed
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * @param saved
	 *            the saved to set
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	/**
	 * @return the saved
	 */
	public boolean isSaved() {
		return saved;
	}
}
