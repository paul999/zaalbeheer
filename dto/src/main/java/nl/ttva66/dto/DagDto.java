package nl.ttva66.dto;

// Generated 22-jun-2013 16:02:31 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import nl.ttva66.interfaces.DagInterface;
import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.interfaces.Item;

public class DagDto implements java.io.Serializable, DagInterface, Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String team;
	private String opmerkingen;
	private int dag;
	private int maand;
	private int jaar;
	private Set<OpenDto> opens = new HashSet<OpenDto>(0);
	private Set<DienstDto> dienst = new HashSet<DienstDto>();
	
	public DagDto() {
	}

	public DagDto(String team, String opmerkingen, int dag, int maand, int jaar) {
		this.team = team;
		this.opmerkingen = opmerkingen;
		this.dag = dag;
		this.jaar = jaar;
		this.maand = maand;
	}

	public DagDto(String team, String opmerkingen, int dag, int maand,
			int jaar, Set<OpenDto> opens) {
		this.team = team;
		this.opmerkingen = opmerkingen;
		this.dag = dag;
		this.jaar = jaar;
		this.maand = maand;
		this.opens = opens;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public Set<OpenDto> getOpens() {
		return this.opens;
	}

	public void setOpens(Set<OpenDto> opens) {
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

	@Override
	public String toString() {
		return this.getDag() + "/" + this.getMaand() + "/" + this.getJaar();
	}

	public Set<DienstDto> getDienst() {
		return dienst;
	}

	public void setDienst(Set<DienstDto> dienst) {
		this.dienst = dienst;
	}

}
