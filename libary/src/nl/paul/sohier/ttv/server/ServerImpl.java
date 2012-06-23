package nl.paul.sohier.ttv.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebService;

import DataBase.DataBase;

import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;

//Service Implementation
@WebService(endpointInterface = "nl.paul.sohier.ttv.server.Server")
public class ServerImpl implements Server {

	@Override
	public Dag getSavedDag(DagRequest request) {
		System.out.println("Got a request for " + request);

		System.out.println("Got a request for " + request);
		Dag dt = new Dag(request);

		DataBase db = new DataBase();

		ResultSet r = db
				.runSelect(String
						.format("SELECT * FROM dag WHERE dag = %d AND maand = %d AND jaar = %d",
								request.getDag(), request.getMaand(),
								request.getJaar()));

		try {
			r.next();

			int[] zaaldienst = { 0, 0, 0 };
			zaaldienst[0] = r.getInt("dienstochtend");
			zaaldienst[1] = r.getInt("dienstmiddag");
			zaaldienst[2] = r.getInt("dienstavond");
			dt.setZaaldienst(zaaldienst);

			boolean[] open = { false, false, false };

			open[0] = r.getInt("ochtend") == 1 ? true : false;
			open[1] = r.getInt("middag") == 1 ? true : false;
			open[2] = r.getInt("avond") == 1 ? true : false;
			dt.setOpen(open);

		} catch (SQLException e) {
			System.out.println("No data in the database yet.");

			return API.createStandardDag(request);
		}

		db.closeDatabase();

		return dt;

	}

	@Override
	public Dag saveDag(Dag dag) {
		System.out.println("Got a request to save something...");
		return dag;
	}

	@Override
	public boolean deleteDag(DagRequest dag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ZaalDienst getZaalDienst(ZaalDienstRequest request) {
		System.out.println("Got a request for " + request);
		ZaalDienst dt = new ZaalDienst(request);

		DataBase db = new DataBase();

		ResultSet r = db.runSelect("SELECT * FROM zaaldienst WHERE id = "
				+ request.getId());

		try {
			r.next();
			dt.setEmail(r.getString("email"));
			dt.setNaam(r.getString("naam"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		db.closeDatabase();

		return dt;
	}

	@Override
	public ZaalDienst saveZaalDienst(ZaalDienst dienst) {
		// TODO Auto-generated method stub

		DataBase db = new DataBase();

		String sql = "";
		boolean nw = false;

		if (dienst.getId() == -1) {
			nw = true;
			sql += "INSERT INTO zaaldienst ";
		} else {
			sql += "UPDATE zaaldienst ";
		}
		sql += "SET ";

		sql += "naam = '%s', email = '%s', aantal = %d, maandag = %d"
				+ ", dinsdag = %d, woensdag = %d, donderdag = %d, "
				+ "vrijdag = %d, zaterdag = %d, zondag = %d";

		sql = String.format(sql, dienst.getNaam(), dienst.getEmail(),
				dienst.getAantal(), dienst.getDagi(0), dienst.getDagi(1),
				dienst.getDagi(2), dienst.getDagi(3), dienst.getDagi(4),
				dienst.getDagi(5), dienst.getDagi(6));

		System.out.println("Query to run: " + sql);

		boolean result = false;
		if (!nw) {
			sql += " WHERE id = " + dienst.getId();

			if (db.runUpdate(sql) != 0) {
				result = true;
			}
		} else {
			int id = (int) db.runInsert(sql);
			if (id != -1) {
				result = true;
				dienst.setId(id);
			}
		}

		if (result) {
			dienst.setSaved(true);
		}

		return dienst;
	}

	@Override
	public ZaalDienst[] getAlleZaalDiensten() {
		// TODO Auto-generated method stub

		System.out.println("Got a request for getting all zaaldienstne");

		DataBase db = new DataBase();
		ZaalDienst[] data;

		ResultSet r = db.runSelect("SELECT COUNT(id) as t FROM zaaldienst");

		int id;
		try {
			r.next();

			id = r.getInt("t");

			data = new ZaalDienst[id];

			r = db.runSelect("SELECT * FROM zaaldienst");

			r.next();
			int i = 0;
			while (true) {
				ZaalDienst tmp = new ZaalDienst();

				tmp.setNaam(r.getString("naam"));
				tmp.setEmail(r.getString("email"));
				tmp.setAantal(r.getInt("aantal"));
				
				tmp.setDag(0, r.getBoolean("maandag"));
				tmp.setDag(1, r.getBoolean("dinsdag"));
				tmp.setDag(2, r.getBoolean("woensdag"));
				tmp.setDag(3, r.getBoolean("donderdag"));
				tmp.setDag(4, r.getBoolean("vrijdag"));
				tmp.setDag(5, r.getBoolean("zaterdag"));
				tmp.setDag(6, r.getBoolean("zondag"));
				
				tmp.setId(r.getInt("id"));

				data[i] = tmp;
				i++;
				if (r.isLast()) {
					break;
				}
				r.next();
			}

		} catch (SQLException e) {
			System.out.println("No data in the database yet.");

			return null;
		}

		db.closeDatabase();
		return data;
	}
}