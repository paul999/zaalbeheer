package nl.paul.sohier.ttv.libary;



public class Dag {
	private boolean open;
	private Zaaldienst zaaldienst;
	private int dag = 0;
	private int maand = 0;
	private int jaar = 0;
	
	public Dag(int d, int m, int j)
	{
		dag = d;
		maand = m;
		jaar = j;
	}
	
	public Dag() {
		
	}

	/**
	 * @return the open
	 */
	public boolean isOpen() {
		return open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}
	/**
	 * @return the zaaldienst
	 */
	public Zaaldienst getZaaldienst() {
		return zaaldienst;
	}
	/**
	 * @param zaaldienst the zaaldienst to set
	 */
	public void setZaaldienst(Zaaldienst zaaldienst) {
		this.zaaldienst = zaaldienst;
	}
	/**
	 * @return the dag
	 */
	public int getDag() {
		return dag;
	}
	/**
	 * @param dag the dag to set
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}
	/**
	 * @return the maand
	 */
	public int getMaand() {
		return maand;
	}
	/**
	 * @param maand the maand to set
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}
	/**
	 * @return the jaar
	 */
	public int getJaar() {
		return jaar;
	}
	/**
	 * @param jaar the jaar to set
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
	
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		
		if (obj instanceof Dag)
		{
			Dag ob = (Dag)obj;
			
			if (getJaar() != ob.getJaar())
				return false;
			
			if (getDag() != ob.getDag())
				return false;
				
			if (getMaand() != ob.getMaand())
				return false;
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		return getDag() + "/" + getMaand() + "/" + getJaar();
	}
}
