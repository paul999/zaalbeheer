package nl.paul.sohier.ttv.libary;

import java.util.Date;

public class DagRequest implements DagInterface, Request {
	private Date datum;
	public DagRequest() {

	}

	public DagRequest(Date datum) {
		this.datum = datum;
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

		if (getDatum() != ob.getDatum())
			return false;

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getDatum().toString();
	}

	@Override
	public Date getDatum() {
		// TODO Auto-generated method stub
		return null;
	}
}
