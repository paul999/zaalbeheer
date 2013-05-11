package nl.paul.sohier.ttv.libary;

// Generated 11-mei-2013 20:58:55 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Type generated by hbm2java
 */
public class Type implements java.io.Serializable {

	private Integer id;
	private String naam;
	private int start;
	private int eind;
	private Set diensts = new HashSet(0);

	public Type() {
	}

	public Type(String naam, int start, int eind) {
		this.naam = naam;
		this.start = start;
		this.eind = eind;
	}

	public Type(String naam, int start, int eind, Set diensts) {
		this.naam = naam;
		this.start = start;
		this.eind = eind;
		this.diensts = diensts;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEind() {
		return this.eind;
	}

	public void setEind(int eind) {
		this.eind = eind;
	}

	public Set getDiensts() {
		return this.diensts;
	}

	public void setDiensts(Set diensts) {
		this.diensts = diensts;
	}

}
