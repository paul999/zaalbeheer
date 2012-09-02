package nl.sohier.paul.ttv.a66.zaaldiensten;


public class WeekDag {
	private String text;
	private int kleur;
	private boolean click;
	
	public WeekDag(String text, int black, boolean click)
	{
		this.setText(text);
		this.setKleur(black);
		this.setClick(click);
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the kleur
	 */
	public int getKleur() {
		return kleur;
	}

	/**s
	 * @param kleur the kleur to set
	 */
	public void setKleur(int kleur) {
		this.kleur = kleur;
	}

	/**
	 * @return the click
	 */
	public boolean isClick() {
		return click;
	}

	/**
	 * @param click the click to set
	 */
	public void setClick(boolean click) {
		this.click = click;
	}
}
