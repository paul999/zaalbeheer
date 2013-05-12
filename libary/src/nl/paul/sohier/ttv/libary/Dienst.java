package nl.paul.sohier.ttv.libary;

// Generated 11-mei-2013 20:58:55 by Hibernate Tools 3.4.0.CR1

/**
 * Dienst generated by hbm2java
 */
public class Dienst implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5923283947180899944L;
	private Integer id;
	private Dag dag;
	private ZaalDienst zaaldienst;
	private Type type;
	private boolean definitief;
	private boolean backup;

	public Dienst() {
	}

	public Dienst(Dag dag, ZaalDienst zaaldienst, Type type,
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

	public Dag getDag() {
		return this.dag;
	}

	public void setDag(Dag dag) {
		this.dag = dag;
	}

	public ZaalDienst getZaaldienst() {
		return this.zaaldienst;
	}

	public void setZaaldienst(ZaalDienst zaaldienst) {
		this.zaaldienst = zaaldienst;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
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