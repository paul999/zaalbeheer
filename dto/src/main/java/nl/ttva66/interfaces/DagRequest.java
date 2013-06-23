package nl.ttva66.interfaces;

import nl.ttva66.dto.DagDto;

public class DagRequest implements DagInterface, Request {
	private int dag;
	private int maand;
	private int jaar;
	
	public DagRequest(int dag, int maand, int jaar) {
		this.dag = dag;
		this.jaar = jaar;
		this.maand = maand;
	}
	public DagRequest()
	{
		System.out.println("Non arg constructor");
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
		if (obj instanceof DagDto) {
			ob = (DagDto) obj;
		} else if (obj instanceof DagRequest) {
			ob = (DagRequest) obj;
		} else {
			return false;
		}

		if (ob.getJaar() != getJaar() || ob.getMaand() != getMaand()
				|| ob.getDag() != getDag()) {
			return false;
		}

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getDag() + "/" + this.getMaand() + "/" + this.getJaar();
	}


	public int getDag() {
		return dag;
	}


	public void setDag(int dag) {
		this.dag = dag;
	}


	public int getMaand() {
		return maand;
	}


	public void setMaand(int maand) {
		this.maand = maand;
	}


	public int getJaar() {
		return jaar;
	}


	public void setJaar(int jaar) {
		this.jaar = jaar;
	}
}
