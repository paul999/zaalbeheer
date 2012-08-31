package nl.paul.sohier.ttv.libary;

/**
 * @author paul
 * 
 */
public class Dag implements DagInterface, Item {
	private int dag = 0;
	private int maand = 0;
	private int jaar = 0;
	private int id = -1;
	private boolean changed = false;
	private boolean saved = false;

	private boolean open[] = { false, false, false };
	private int[] ochtend = {};
	private int[] middag = {};
	private int[] avond = {};
	private int[] teams = {};
	private String team;
	private String opmerkingen;

	public Dag(DagRequest d) {
		if (d == null)
		{
			throw new RuntimeException("Dagrequest is missing.");
		}
			
		dag = d.getDag();
		maand = d.getMaand();
		jaar = d.getJaar();

	}

	public Dag() {

	}

	/**
	 * @return the open
	 */
	public boolean[] getOpen() {
		return open;
	}

	public boolean getDeelOpen(int i) {
		return open[i];
	}

	public int getDeelOpeni(int i) {
		return open[i] ? 1 : 0;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(boolean[] open) {
		changed = true;
		this.open = open;
	}

	public void setOpen(int optie, boolean open) {
		changed = true;
		this.open[optie] = open;
	}
	
	public void setTeams(int[] teams)
	{
		changed = true;
		this.teams = teams;
	}
	public int[] getTeams()
	{
		return teams;
	}

	public int[] getDeelZaalDienst(int i) {
		switch (i) {
		case 0:
			return ochtend;
		case 1:
			return middag;
		case 2:
			return avond;
		}
		return null;
	}

	/**
	 * @param zaaldienst
	 *            the zaaldienst to set
	 */
	public void setZaaldienst(int[][] zaaldienst) {

		if (zaaldienst.length != 3) {
			throw new RuntimeException("zaaldienst moet 3 waarde hebben");
		}

		changed = true;
		ochtend = zaaldienst[0];
		middag = zaaldienst[1];
		avond = zaaldienst[2];
	}

	/**
	 * @param dag
	 *            the dag to set
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}

	/**
	 * @param maand
	 *            the maand to set
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}

	/**
	 * @param jaar
	 *            the jaar to set
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	/**
	 * @param changed
	 *            the changed to set
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * @return the dag
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * @return the maand
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 * @return the jaar
	 */
	public int getJaar() {
		return jaar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		DagInterface ob = null;
		if (obj instanceof Dag) {
			ob = (Dag) obj;
		} else if (obj instanceof DagRequest) {
			ob = (DagRequest) obj;
		} else
			return false;

		if (getJaar() != ob.getJaar())
			return false;

		if (getDag() != ob.getDag())
			return false;

		if (getMaand() != ob.getMaand())
			return false;

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getDag() + "/" + (getMaand() + 1) + "/" + getJaar();
		/*
		 * + " Open: " + open[0] + " " + open[1] + " " + open[2] + " dienst: " +
		 * zaaldienst[0] + " " + zaaldienst[1] + " " + zaaldienst[2] + " ";
		 */
	}

	/**
	 * @return the changed
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * @param saved
	 *            the saved to set
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	/**
	 * @return the saved
	 */
	public boolean isSaved() {
		return saved;
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
	
	/**
	 * @param ochtend the ochtend to set
	 */
	public void setOchtend(int[] ochtend) {
		this.ochtend = ochtend;
	}

	/**
	 * @param middag the middag to set
	 */
	public void setMiddag(int[] middag) {
		this.middag = middag;
	}

	/**
	 * @param avond the avond to set
	 */
	public void setAvond(int[] avond) {
		this.avond = avond;
	}

	/**
	 * @return the ochtend
	 */
	public int[] getOchtend() {
		return ochtend;
	}

	/**
	 * @return the middag
	 */
	public int[] getMiddag() {
		return middag;
	}

	/**
	 * @return the avond
	 */
	public int[] getAvond() {
		return avond;
	}

	/**
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * @param team the team to set
	 */
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * @return the opmerkingen
	 */
	public String getOpmerkingen() {
		return opmerkingen;
	}

	/**
	 * @param opmerkingen the opmerkingen to set
	 */
	public void setOpmerkingen(String opmerkingen) {
		this.opmerkingen = opmerkingen;
	}
	
}
