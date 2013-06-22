package nl.ttva66.dto;

public class TypeDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String naam;
	private int start;
	private int eind;

	public TypeDto() {
	}

	public TypeDto(String naam, int start, int eind) {
		this.naam = naam;
		this.start = start;
		this.eind = eind;
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
}
