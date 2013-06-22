package nl.paul.sohier.ttv.output;

import java.io.File;

import javax.swing.ProgressMonitor;

import nl.ttva66.DagRequest;


public interface Output {
	public void askDirectory(boolean temp);
	public void setBar(ProgressMonitor pg);
	public ProgressMonitor getBar();
	public void generate(DagRequest request) throws OutputException;
	public void save() throws OutputException;
	public String getFile();
	public void setFile(String file);
	public File getFl();
	public void setFl(File file);
	public void setDir(boolean dir);
	public boolean isDir();
	public String getTempDir();
}

