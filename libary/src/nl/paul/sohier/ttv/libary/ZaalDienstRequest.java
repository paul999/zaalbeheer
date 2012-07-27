package nl.paul.sohier.ttv.libary;

public class ZaalDienstRequest implements ZaalDienstInterface, Request{

	private int id;
	
	public ZaalDienstRequest()
	{
		
	}
	
	public ZaalDienstRequest(int i)
	{
		id = i;
	}

	/**
	 * @param id the id to set
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
	
	public String toString()
	{
		return "zaaldienst request with id " + id; 
	}

}
