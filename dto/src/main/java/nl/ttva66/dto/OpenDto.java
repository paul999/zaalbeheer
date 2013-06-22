package nl.ttva66.dto;

public class OpenDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer dag;
	private TypeDto type;
	private boolean open;

	public OpenDto() {
	}

	public OpenDto(Integer dag, TypeDto type) {
		this.dag = dag;
		this.type = type;
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

	public TypeDto getType() {
		return this.type;
	}

	public void setType(TypeDto type) {
		this.type = type;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
