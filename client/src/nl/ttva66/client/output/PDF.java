package nl.ttva66.client.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.ProgressMonitor;

import nl.ttva66.client.API;
import nl.ttva66.dto.DagDto;
import nl.ttva66.dto.DienstDto;
import nl.ttva66.dto.OpenDto;
import nl.ttva66.dto.TypeDto;
import nl.ttva66.dto.ZaaldienstDto;
import nl.ttva66.interfaces.DagRequest;
import nl.ttva66.interfaces.ZaalDienstRequest;
import nl.ttva66.jaxws.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDF extends Save {

	private ProgressMonitor progressMonitor;
	private String tempDir;
	private Service srv;

	public PDF(JFrame jf) {
		frame = jf;
	}

	public void setBar(ProgressMonitor pg) {
		progressMonitor = pg;
	}

	@Override
	public void generate(DagRequest request) throws OutputException {
		if (request == null || request.getDag() != -1) {
			throw new OutputException("Ongeldige declaratie.");
		}

		Document document = new Document(PageSize.A4, 10, 10, 10, 10);

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

			srv = API.getServer();
			Set<TypeDto> types = API.sortType(srv.listTypes());

			int cols = 2 + types.size();

			System.out.println("Col size: " + cols);

			PdfPTable t = new PdfPTable(cols);
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
			c1.setColspan(cols);
			t.addCell(c1);

			c1 = new PdfPCell(new Phrase(
					"Kan je niet? Zorg dan zelf voor een vervanging!"));
			c1.setColspan(cols);
			c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			t.addCell(c1);

			t.addCell("Datum");

			for (TypeDto dto : types) {
				t.addCell(dto.getNaam());
			}

			t.addCell("Team");

			t.completeRow();

			t.addCell("Tijden: ");
			for (TypeDto dto : types) {
				t.addCell(dto.getStart() + " - " + dto.getEind());
			}

			t.completeRow();

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

				DagRequest r = new DagRequest(i, request.getMaand(),
						request.getJaar());
				DagDto dag = (DagDto) API.items.get(r);

				if (dag == null) {
					dag = API.getDag(r);

					if (dag == null) {
						throw new OutputException("Kon " + r
								+ " niet ophalen van de server.");
					}
				}

				t.addCell(txt);

				try {
					Set<OpenDto> delen = API.sortOpen(dag.getOpens());
					System.out.println("DTO size open: " + delen.size());

					for (OpenDto deel : delen) {
						int cal = 0;
						if (deel.isOpen()) {
							Set<DienstDto> dienst = new HashSet<DienstDto>();

							for (DienstDto d : dag.getDienst()) {
								if (d.getType().getId() == deel.getType()
										.getId()) {
									dienst.add(d);
								}
							}
							String result = "";

							if (dienst.size() > 0) {

								for (DienstDto row : dienst) {
									if (row == null) {
										// Skip it.
										continue;
									}

									ZaalDienstRequest r2 = new ZaalDienstRequest(
											row.getZaaldienst());
									ZaaldienstDto zt = (ZaaldienstDto) API.items
											.get(r2);

									if (cal != 0) {
										result += ", ";
									}
									cal++;

									if (zt == null) {
										zt = srv.getZaaldienstById(r2);
									}

									result += zt.getNaam();

									if (row.isBackup()) {
										result += "(backup)";
									}
								}
							} else {
								result = "?";
							}
							t.addCell(result);
						} else {
							t.addCell("Gesloten");
						}
					}
				} catch (RuntimeException e) {
					throw new OutputException(
							"Zaaldienst kon niet gegenereerd worden.");
				} catch (Exception e) {
					throw new OutputException(
							"Zaaldienst kon niet gegenereerd worden.");
				}

				String team = dag.getTeam();

				if (team == null || team.equals("null"))
					team = "";

				t.addCell(team);

				t.completeRow();
				progressMonitor.setProgress(i);
			}

			document.add(t);

			document.close();

		} catch (DocumentException e) {

			throw new OutputException("Fout tijdens het schrijven van de PDF");
		} catch (FileNotFoundException e) {

			throw new OutputException("Bestand bestaat niet");
		} catch (Exception e1) {
			throw new OutputException("Generieke server error");
		}
	}

	@Override
	public String getTempDir() {
		return tempDir;
	}

	@Override
	public ProgressMonitor getBar() {
		return progressMonitor;
	}

}