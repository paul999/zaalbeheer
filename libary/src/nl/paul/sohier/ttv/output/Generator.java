package nl.paul.sohier.ttv.output;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

import nl.paul.sohier.ttv.libary.DagRequest;

public class Generator {

	private Output generator;
	private JFrame frame;
	private DagRequest dag;
	private ProgressMonitor progressMonitor;

	public Generator(JFrame jf, Output generator, DagRequest dag,
			ProgressMonitor progressMonitor) {
		this.frame = jf;
		this.generator = generator;
		this.dag = dag;
		this.progressMonitor = progressMonitor;
	}

	public void genereer() {
		genereer(false);
	}

	public boolean genereer(boolean temp) {
		ProgressMonitor pm = null;

		try {

			generator.askDirectory(temp);

			if (this.progressMonitor == null) {
				pm = new ProgressMonitor(frame,

				"Bezig met genereren PDF...", "Bezig met genereren PDF...", 0,
						31);
				pm.setMillisToDecideToPopup(0);
				generator.setBar(pm);
				
			} else {
				generator.setBar(this.progressMonitor);
			}

			generator.generate(dag);

			generator.save();
			if (this.progressMonitor == null) {
				progressMonitor.close();
			}

		} catch (OutputException e) {
			String msg = e.getMessage();
			progressMonitor.close();

			JOptionPane.showMessageDialog(frame, msg,
					"Fout bij genereren PDF.", JOptionPane.ERROR_MESSAGE);
			return false;

		}
		return true;
	}
}
