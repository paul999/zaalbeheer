package nl.ttva66.entities;

// Generated 22-jun-2013 16:02:31 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Dienst generated by hbm2java
 */
@Entity
@Table(name = "dienst", catalog = "ttv")
public class Dienst implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Dag dag;
	private Zaaldienst zaaldienst;
	private Type type;
	private boolean definitief;
	private boolean backup;

	public Dienst() {
	}

	public Dienst(Dag dag, Zaaldienst zaaldienst, Type type,
			boolean definitief, boolean backup) {
		this.dag = dag;
		this.zaaldienst = zaaldienst;
		this.type = type;
		this.definitief = definitief;
		this.backup = backup;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dag", nullable = false)
	public Dag getDag() {
		return this.dag;
	}

	public void setDag(Dag dag) {
		this.dag = dag;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", nullable = false)
	public Zaaldienst getZaaldienst() {
		return this.zaaldienst;
	}

	public void setZaaldienst(Zaaldienst zaaldienst) {
		this.zaaldienst = zaaldienst;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type", nullable = false)
	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(name = "definitief", nullable = false)
	public boolean isDefinitief() {
		return this.definitief;
	}

	public void setDefinitief(boolean definitief) {
		this.definitief = definitief;
	}

	@Column(name = "backup", nullable = false)
	public boolean isBackup() {
		return this.backup;
	}

	public void setBackup(boolean backup) {
		this.backup = backup;
	}

}
