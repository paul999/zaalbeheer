package nl.paul.sohier.ttv.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.ProgressMonitor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.server.Server;

public class PDF extends Save {

	private ProgressMonitor progressMonitor;
	private String tempDir;

	public PDF(JFrame jf) {
		frame = jf;
	}
	
	public void setBar(ProgressMonitor pg)
	{
		progressMonitor = pg;
	}

	@Override
	public void generate(DagRequest request) throws OutputException {
		if (request == null || request.getDag() != -1) {
			throw new OutputException("Ongeldige declaratie.");
		}

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		
		try {
			File tmp = File.createTempFile("ttv", ".pdf");
			tempDir = tmp.getAbsolutePath();

			tmp = null;
		} catch (IOException e) {
			throw new OutputException("Kon geen tijdelijke temp file maken");

		}

		if (tempDir == null) {
			throw new OutputException(
					"Het was niet mogelijk een tijdelijk bestand te maken");
		}

		try {
			@SuppressWarnings("unused")
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(tempDir));

			document.open();

			PdfPTable t = new PdfPTable(4);
			t.setHeaderRows(2);

			GregorianCalendar dt = new GregorianCalendar(request.getJaar(),
					request.getMaand(), 1);

			String[] months = { "januari", "februari", "maart", "april", "mei",
					"juni", "juli", "augustus", "september", "oktober",
					"november", "december" };

			PdfPCell c1 = new PdfPCell(new Phrase("Zaalwachten schema voor "
					+ months[dt.get(GregorianCalendar.MONTH)] + " "
					+ dt.get(GregorianCalendar.YEAR)));
			c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c1.setColspan(4);
			t.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					"Kan je niet? Zorg dan zelf voor een vervanging!"));
			c1.setColspan(4);
			c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			t.addCell(c1);

			t.addCell("Datum");
			t.addCell("Ochtend");
			t.addCell("Middag");
			t.addCell("Avond");

			t.completeRow();

			System.out.println("End: "
					+ dt.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));

			Server srv = API.getServer(frame);

			progressMonitor.setMaximum(dt
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));

			for (int i = 1; i <= dt
					.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); i++) {
				System.out.println(i);

				GregorianCalendar dat = new GregorianCalendar(
						request.getJaar(), request.getMaand(), i);

				String txt = "";
				switch (dat.get(GregorianCalendar.DAY_OF_WEEK)) {
				case GregorianCalendar.MONDAY:
					txt += "ma";
					break;
				case GregorianCalendar.TUESDAY:
					txt += "di";
					break;
				case GregorianCalendar.WEDNESDAY:
					txt += "wo";
					break;
				case GregorianCalendar.THURSDAY:
					txt += "do";
					break;
				case GregorianCalendar.FRIDAY:
					txt += "vr";
					break;
				case GregorianCalendar.SATURDAY:
					txt += "za";
					break;
				case GregorianCalendar.SUNDAY:
					txt += "zo";
					break;
				}
				txt += " " + i + " ";
				txt += months[dat.get(GregorianCalendar.MONTH)].substring(0, 3);

				Dag dag = srv.getSavedDag(new DagRequest(i, request.getMaand(),
						request.getJaar()));
				System.out.println(dag);

				
				boolean open[] = dag.getOpen();
				String[] zt = {
						API.zaallijst(dag.getDeelZaalDienst(0), frame),
						API.zaallijst(dag.getDeelZaalDienst(1), frame),
						API.zaallijst(dag.getDeelZaalDienst(2), frame) };

				if (dat.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY
						&& !open[0] && !open[1] && !open[2]) {
					continue;
				}

				t.addCell(txt);
				if (open[0]) {
					if (zt[0] != null) {
						t.addCell(zt[0]);
					} else {
						t.addCell("?");
					}
				} else {
					t.addCell("");
				}

				if (open[1]) {
					if (zt[1] != null) {
						t.addCell(zt[1]);
					} else {
						t.addCell("?");
					}
				} else {
					if (dat.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY) {
						t.addCell("Gesloten");

					} else {
						t.addCell("");
					}
				}

				if (open[2]) {
					if (zt[2] != null) {
						t.addCell(zt[2]);
					} else {
						t.addCell("?");
					}
				} else {
					t.addCell("Gesloten");
				}

				t.completeRow();
				progressMonitor.setProgress(i);
			}

			document.add(t);

			document.close();

		} catch (DocumentException e) {

			throw new OutputException("Fout tijdens het schrijven van de PDF");
		} catch (FileNotFoundException e) {

			throw new OutputException("Bestand bestaat niet");
		}
	}

	@Override
	public String getTempDir() {
		return tempDir;
	}

}