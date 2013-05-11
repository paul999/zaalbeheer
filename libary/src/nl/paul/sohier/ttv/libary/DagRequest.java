package nl.paul.sohier.ttv.libary;

public class DagRequest implements DagInterface, Request {
	private int dag;
	private int maand;
	private int jaar;

	public DagRequest() {

	}

	public DagRequest(int dag, int maand, int jaar) {
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}

	/**
	 * @param dag
	 *            the dag to set
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}

	/**
	 * @return the dag
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * @param maand
	 *            the maand to set
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}

	/**
	 * @return the maand
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 * @param jaar
	 *            the jaar to set
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
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
		return getDag() + "/" + getMaand() + "/" + getJaar();
	}
}
