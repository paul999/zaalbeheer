package nl.paul.sohier.ttv.libary;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author paul
 * 
 */
public class Dag implements DagInterface, Item { 
	private Integer id = -1;
	private String team;
	private String opmerkingen;
	private Date datum;
	
	private Set<?> diensts = new HashSet<Object>(0);
	private Set opens = new HashSet(0);

	public Dag() {
	}
	public Dag(DagRequest dag)
	{
		this.datum = dag.getDatum();
	}

	public Dag(String team,
			String opmerkingen, Date datum) {

		this.team = team;
		this.opmerkingen = opmerkingen;
		this.datum = datum;
	}

	public Dag(String team,
			String opmerkingen, Date datum, Set<?> diensts) {
		this.team = team;
		this.opmerkingen = opmerkingen;
		this.datum = datum;
		this.diensts = diensts;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeam() {
		return this.team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getOpmerkingen() {
		return this.opmerkingen;
	}

	public void setOpmerkingen(String opmerkingen) {
		this.opmerkingen = opmerkingen;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Set<?> getDiensts() {
		return this.diensts;
	}

	public void setDiensts(Set<?> diensts) {
		this.diensts = diensts;
	}

	public Set getOpens() {
		return this.opens;
	}

	public void setOpens(Set opens) {
		this.opens = opens;
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
}
