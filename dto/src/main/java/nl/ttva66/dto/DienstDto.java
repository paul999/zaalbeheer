package nl.ttva66.dto;

import nl.ttva66.interfaces.Item;


public class DienstDto implements java.io.Serializable, Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer dag;
	private Integer zaaldienst;
	private TypeDto type;
	private boolean definitief;
	private boolean backup;

	public DienstDto() {
	}

	public DienstDto(Integer dag, Integer zaaldienst, TypeDto type,
			boolean definitief, boolean backup) {
		this.dag = dag;
		this.zaaldienst = zaaldienst;
		this.type = type;
		this.definitief = definitief;
		this.backup = backup;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDag() {
		return this.dag;
	}

	public void setDag(Integer dag) {
		this.dag = dag;
	}

	public Integer getZaaldienst() {
		return this.zaaldienst;
	}

	public void setZaaldienst(Integer zaaldienst) {
		this.zaaldienst = zaaldienst;
	}

	public TypeDto getType() {
		return this.type;
	}

	public void setType(TypeDto type) {
		this.type = type;
	}

	public boolean isDefinitief() {
		return this.definitief;
	}

	public void setDefinitief(boolean definitief) {
		this.definitief = definitief;
	}

	public boolean isBackup() {
		return this.backup;
	}

	public void setBackup(boolean backup) {
		this.backup = backup;
	}

}
