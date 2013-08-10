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
 * Open generated by hbm2java
 */
@Entity
@Table(name = "open", catalog = "ttv")
public class Open implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Dag dag;
	private Type type;
	private boolean open;

	public Open() {
	}

	public Open(Dag dag, Type type) {
		this.dag = dag;
		this.type = type;
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
	@JoinColumn(name = "type", nullable = false)
	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(name = "open")
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}