package nl.ttva66.dto;

// Generated 22-jun-2013 16:02:31 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import nl.ttva66.entities.DagInterface;
import nl.ttva66.entities.Item;



public class DagDto implements java.io.Serializable, DagInterface, Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String team;
	private String opmerkingen;
	private Date datum;
	private Set<OpenDto> opens = new HashSet<OpenDto>(0);

	public DagDto() {
	}

	public DagDto(String team, String opmerkingen, Date datum) {
		this.team = team;
		this.opmerkingen = opmerkingen;
		this.datum = datum;
	}

	public DagDto(String team, String opmerkingen, Date datum, Set<OpenDto> opens) {
		this.team = team;
		this.opmerkingen = opmerkingen;
		this.datum = datum;
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


	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Set<OpenDto> getOpens() {
		return this.opens;
	}

	public void setOpens(Set<OpenDto> opens) {
		this.opens = opens;
	}
}
