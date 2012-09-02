package nl.paul.sohier.ttv.libary;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class DagRequest implements DagInterface, Request, KvmSerializable {
	private int dag;
	private int maand;
	private int jaar;

	public DagRequest() {

	}

	public DagRequest(int dag, int maand, int jaar) {
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}

	/**
	 * @param dag
	 *            the dag to set
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}

	/**
	 * @return the dag
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * @param maand
	 *            the maand to set
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}

	/**
	 * @return the maand
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 * @param jaar
	 *            the jaar to set
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
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
		return getDag() + "/" + getMaand() + "/" + getJaar();
	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub

		switch (arg0) {
		case 0:
			return dag;
		case 1:
			return maand;
		case 2:
			return jaar;
		}

		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void getPropertyInfo(int arg0,
			@SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo arg2) {
		arg2.type = PropertyInfo.INTEGER_CLASS;
		
		switch (arg0) {
		case 0:
			arg2.name = "dag";
			break;
		case 1:
			arg2.name = "maand";
			break;
		case 2:
			arg2.name = "jaar";
			break;
		default:
			break;
		}

	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0:
			dag = Integer.parseInt(arg1.toString());
			break;
		case 1:
			maand = Integer.parseInt(arg1.toString());
			break;
		case 2:
			jaar = Integer.parseInt(arg1.toString());
			break;
		default:
			break;
		}
	}
}
