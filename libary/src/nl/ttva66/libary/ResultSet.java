package nl.ttva66.libary;

public class ResultSet {

	private int result;
	private String text;
	private boolean failed;
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
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
	 * @return the failed
	 */
	public boolean isFailed() {
		return failed;
	}
	/**
	 * @param failed the failed to set
	 */
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
	
}
