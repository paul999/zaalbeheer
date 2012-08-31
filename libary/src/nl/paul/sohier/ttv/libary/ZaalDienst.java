package nl.paul.sohier.ttv.libary;

public class ZaalDienst implements Item, ZaalDienstInterface {

	private String naam;
	private String email;
	private String password;
	private boolean[] dagen = { false, false, false, false, false, false, false };
	private int aantal;
	private boolean changed = false;
	private boolean saved = false;

	private int id = -1;

	public ZaalDienst(ZaalDienstRequest rt) {
		this.id = rt.getId();

	}

	public ZaalDienst() {

	}

	/**
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * @param naam
	 *            the naam to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
		changed = true;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
		changed = true;
	}

	@Override
	public boolean isChanged() {
		return changed;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {

		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		ZaalDienstInterface ob = null;
		if (obj instanceof ZaalDienst) {
			ob = (ZaalDienst) obj;
		} else if (obj instanceof ZaalDienstRequest) {
			ob = (ZaalDienstRequest) obj;
		} else
			return false;

		if (ob.getId() == this.getId()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param aantal
	 *            the aantal to set
	 */
	public void setAantal(int aantal) {
		this.aantal = aantal;
		changed = true;
	}

	/**
	 * @return the aantal
	 */
	public int getAantal() {
		return aantal;
	}

	/**
	 * @param dagen
	 *            the dagen to set
	 */
	public void setDagen(boolean[] dagen) {
		this.dagen = dagen;
		changed = true;
	}

	/**
	 * @return the dagen
	 */
	public boolean[] getDagen() {
		return dagen;
	}

	public void setDag(int dag, boolean status) {
		this.dagen[dag] = status;
		changed = true;
	}

	public boolean getDag(int dag) {
		if (dag > 6)
			throw new RuntimeException("Invalid value");
		return dagen[dag];
	}
	public int getDagi(int dag)
	{
		if (dag > 6)
			throw new RuntimeException("Invalid value");
		
		return (dagen[dag]) ? 1 : 0;
	}
	
	public void setChanged(boolean changed)
	{
		this.changed = changed;
	}

	/**
	 * @param saved the saved to set
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
		this.changed = false;
	}

	/**
	 * @return the saved
	 */
	public boolean isSaved() {
		return saved;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
