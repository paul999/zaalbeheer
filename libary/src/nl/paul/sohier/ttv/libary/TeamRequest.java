package nl.paul.sohier.ttv.libary;

public class TeamRequest implements TeamInterface, Request{
	
	private int id;

	public TeamRequest()
	{
		
	}
	
	public TeamRequest(int id)
	{
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		TeamInterface ob = null;
		if (obj instanceof Team) {
			ob = (Team) obj;
		} else if (obj instanceof TeamRequest) {
			ob = (TeamRequest) obj;
		} else
			return false;

		if (getId() != ob.getId())
			return false;

		return true;

	}

	@Override
	public String getTeam() {
		return "";
	}

	@Override
	public int getId() {
		return id;
	}

}
