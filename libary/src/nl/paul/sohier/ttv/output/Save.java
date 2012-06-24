package nl.paul.sohier.ttv.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

abstract public class Save implements Output {
	protected JFrame frame;
	private String file;
	private File fl;
	private boolean dir = false;

	public void askDirectory() {
		// Create a file chooser
		final JFileChooser fc = new JFileChooser();

		// In response to a button click:
		int returnVal = fc.showOpenDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			System.out.println("Filename: " + file.getName());
			setFl(file);
			setFile(file.getName());
			setDir(true);
		}
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @param fl
	 *            the fl to set
	 */
	public void setFl(File fl) {
		this.fl = fl;
	}

	/**
	 * @return the fl
	 */
	public File getFl() {
		return fl;
	}

	/**
	 * @param dir
	 *            the dir to set
	 */
	public void setDir(boolean dir) {
		this.dir = dir;
	}

	/**
	 * @return the dir
	 */
	public boolean isDir() {
		return dir;
	}

	public void save() throws OutputException {

		try {

    	    File afile =new File(getTempDir());
    	    File bfile =getFl();
 
    	    FileInputStream inStream = new FileInputStream(afile);
    	    FileOutputStream outStream = new FileOutputStream(bfile);
 
    	    byte[] buffer = new byte[1024];
 
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
 
    	    	outStream.write(buffer, 0, length);
 
    	    }
 
    	    inStream.close();
    	    outStream.close();
 
    	    //delete the original file
    	    afile.delete();
 
		} catch (Exception e) {
			throw new OutputException("Kon PDF niet opslaan");
		}
	}
}
