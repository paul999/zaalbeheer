package nl.ttva66.interfaces;

import nl.ttva66.dto.ZaaldienstDto;

public class ZaalDienstRequest implements ZaalDienstInterface, Request{

	private Integer id;
	
	public ZaalDienstRequest()
	{
		
	}
	
	public ZaalDienstRequest(Integer i)
	{
		id = i;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		ZaalDienstInterface ob = null;
		if (obj instanceof ZaaldienstDto) {
			ob = (ZaaldienstDto) obj;
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
