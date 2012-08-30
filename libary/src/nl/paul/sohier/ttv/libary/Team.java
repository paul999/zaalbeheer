/**
 * 
 */
package nl.paul.sohier.ttv.libary;

/**
 * @author paul
 *
 */
public class Team implements Item, TeamInterface {
	boolean changed = false;
	private int id;
	private String team;
	
	@Override
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team)
	{
		changed = true;
		this.team = team;
	}

	@Override
	public boolean isChanged() {
		return changed;
	}

	/* (non-Javadoc)
	 * @see nl.paul.sohier.ttv.libary.Item#setChanged(boolean)
	 */
	@Override
	public void setChanged(boolean changed) {
		this.changed = changed;

	}

	/* (non-Javadoc)
	 * @see nl.paul.sohier.ttv.libary.Item#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see nl.paul.sohier.ttv.libary.Item#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

}
