package nl.ttva66.dto;

import java.util.HashSet;
import java.util.Set;

import nl.ttva66.entities.Item;
import nl.ttva66.entities.ZaalDienstInterface;

public class ZaaldienstDto implements java.io.Serializable,
		ZaalDienstInterface, Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String naam;
	private String email;
	private int aantal;
	private boolean maandag;
	private boolean dinsdag;
	private boolean woensdag;
	private boolean donderdag;
	private boolean vrijdag;
	private boolean zaterdag;
	private boolean zondag;
	private String password;
	private boolean canlogin;
	private Set<DienstDto> diensts = new HashSet<DienstDto>(0);

	public ZaaldienstDto() {
	}

	public ZaaldienstDto(String naam, String email, int aantal,
			boolean maandag, boolean dinsdag, boolean woensdag,
			boolean donderdag, boolean vrijdag, boolean zaterdag,
			boolean zondag, String password, boolean canlogin) {
		this.naam = naam;
		this.email = email;
		this.aantal = aantal;
		this.maandag = maandag;
		this.dinsdag = dinsdag;
		this.woensdag = woensdag;
		this.donderdag = donderdag;
		this.vrijdag = vrijdag;
		this.zaterdag = zaterdag;
		this.zondag = zondag;
		this.password = password;
		this.canlogin = canlogin;
	}

	public ZaaldienstDto(String naam, String email, int aantal,
			boolean maandag, boolean dinsdag, boolean woensdag,
			boolean donderdag, boolean vrijdag, boolean zaterdag,
			boolean zondag, String password, boolean canlogin,
			Set<DienstDto> diensts) {
		this.naam = naam;
		this.email = email;
		this.aantal = aantal;
		this.maandag = maandag;
		this.dinsdag = dinsdag;
		this.woensdag = woensdag;
		this.donderdag = donderdag;
		this.vrijdag = vrijdag;
		this.zaterdag = zaterdag;
		this.zondag = zondag;
		this.password = password;
		this.canlogin = canlogin;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAantal() {
		return this.aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public boolean isMaandag() {
		return this.maandag;
	}

	public void setMaandag(boolean maandag) {
		this.maandag = maandag;
	}

	public boolean isDinsdag() {
		return this.dinsdag;
	}

	public void setDinsdag(boolean dinsdag) {
		this.dinsdag = dinsdag;
	}

	public boolean isWoensdag() {
		return this.woensdag;
	}

	public void setWoensdag(boolean woensdag) {
		this.woensdag = woensdag;
	}

	public boolean isDonderdag() {
		return this.donderdag;
	}

	public void setDonderdag(boolean donderdag) {
		this.donderdag = donderdag;
	}

	public boolean isVrijdag() {
		return this.vrijdag;
	}

	public void setVrijdag(boolean vrijdag) {
		this.vrijdag = vrijdag;
	}

	public boolean isZaterdag() {
		return this.zaterdag;
	}

	public void setZaterdag(boolean zaterdag) {
		this.zaterdag = zaterdag;
	}

	public boolean isZondag() {
		return this.zondag;
	}

	public void setZondag(boolean zondag) {
		this.zondag = zondag;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCanlogin() {
		return this.canlogin;
	}

	public void setCanlogin(boolean canlogin) {
		this.canlogin = canlogin;
	}

	public Set<DienstDto> getDiensts() {
		return this.diensts;
	}

	public void setDiensts(Set<DienstDto> diensts) {
		this.diensts = diensts;
	}
}
