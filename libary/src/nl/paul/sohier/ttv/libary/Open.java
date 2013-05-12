package nl.paul.sohier.ttv.libary;

public class Open implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8991100745719258087L;
	private Integer id;

	private Integer type;
	private Integer dag;

	public Open() {
	}

	public Open(int type, int dag) {
		this.type = type;
		this.dag = dag;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDag() {
		return this.dag;
	}

	public void setDag(Integer dag) {
		this.dag = dag;
	}

}
