package nl.paul.sohier.ttv.output;

import java.io.File;

import nl.paul.sohier.ttv.libary.DagRequest;

public interface Output {
	public void askDirectory();
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

