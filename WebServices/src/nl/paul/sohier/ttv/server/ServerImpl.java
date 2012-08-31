package nl.paul.sohier.ttv.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebService;

import nl.paul.sohier.ttv.DataBase.DataBase;
import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.Team;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;

//Service Implementation
@WebService(endpointInterface = "nl.paul.sohier.ttv.server.Server")
public class ServerImpl implements Server {

	@Override
	public Dag getSavedDag(DagRequest request) {
		Dag dt = new Dag(request);

		DataBase db = new DataBase();

		ResultSet r = db
				.runSelect(String
						.format("SELECT * FROM dag WHERE dag = %d AND maand = %d AND jaar = %d",
								request.getDag(), request.getMaand(),
								request.getJaar()));

		try {
			r.next();

			int[][] zaaldienst = new int[3][];

			int[] o = new int[100];
			int[] m = new int[100];
			int[] a = new int[100];

			int co = 0, ca = 0, cm = 0;

			String sql = "SELECT * FROM dienst WHERE dag = " + r.getInt("id");

			ResultSet r2 = db.runSelect(sql);

			try {

				r2.next();
				while (true) {
					switch (r2.getInt("type")) {
					case 0:
						o[co] = r2.getInt("user");
						co++;
						break;
					case 1:
						m[cm] = r2.getInt("user");
						cm++;
						break;
					case 2:
						a[ca] = r2.getInt("user");
						ca++;
						break;
					}
					if (r2.isLast()) {
						break;
					}
					r2.next();
				}
			} catch (SQLException e) {

			}

			zaaldienst[0] = new int[co];
			zaaldienst[1] = new int[cm];
			zaaldienst[2] = new int[ca];

			for (int i = 0; i < co; i++) {
				zaaldienst[0][i] = o[i];
			}
			for (int i = 0; i < cm; i++) {
				zaaldienst[1][i] = m[i];
			}
			for (int i = 0; i < ca; i++) {
				zaaldienst[2][i] = a[i];
			}

			dt.setZaaldienst(zaaldienst);

			boolean[] open = { false, false, false };

			open[0] = r.getInt("ochtend") == 1 ? true : false;
			open[1] = r.getInt("middag") == 1 ? true : false;
			open[2] = r.getInt("avond") == 1 ? true : false;
			dt.setOpen(open);

			dt.setId(r.getInt("id"));
			dt.setTeam(r.getString("team"));
			dt.setChanged(false);

		} catch (SQLException e) {

			return API.createStandardDag(request);
		} finally {
			db.closeDatabase();
		}

		return dt;

	}

	@Override
	public Dag saveDag(Dag dag) {
		DataBase db = new DataBase();

		String sql = "";
		boolean nw = false;
		int id = dag.getId();

		if (id == -1) {
			nw = true;
			sql += "INSERT INTO dag ";
		} else {
			sql += "UPDATE dag ";
		}
		sql += "SET ";

		sql += "dag = %d, maand = %d, jaar = %d, ochtend = %d, middag = %d, avond = %d, team = '%s'";

		sql = String.format(sql, dag.getDag(), dag.getMaand(), dag.getJaar(),
				dag.getDeelOpeni(0), dag.getDeelOpeni(1), dag.getDeelOpeni(2),
				dag.getTeam());

		System.out.println("SQL: " + sql);

		boolean result = false;
		if (!nw) {
			sql += " WHERE id = " + dag.getId();

			if (db.runUpdate(sql) != 0) {
				result = true;
			}
		} else {
			id = (int) db.runInsert(sql);
			if (id != -1) {
				result = true;
				dag.setId(id);
			}
		}

		if (id != -1) {
			sql = "DELETE FROM dienst WHERE dag = " + id;
			db.runUpdate(sql);

			int[] o = dag.getDeelZaalDienst(0);
			int[] m = dag.getDeelZaalDienst(1);
			int[] a = dag.getDeelZaalDienst(2);
			int l = o.length + m.length + a.length;

			String[] s = new String[l];

			sql = "INSERT INTO dienst SET type = %d, user = %d, dag = %d";

			int c = 0;

			for (int i = 0; i < o.length; i++) {
				s[c] = String.format(sql, 0, o[i], id);
				c++;
			}

			for (int i = 0; i < m.length; i++) {
				s[c] = String.format(sql, 1, m[i], id);
				c++;
			}

			for (int i = 0; i < a.length; i++) {
				s[c] = String.format(sql, 2, a[i], id);
				c++;
			}

			for (int i = 0; i < s.length; i++) {
				db.runInsert(s[i]);
			}
		}

		if (result) {
			dag.setSaved(true);
		}

		return dag;

	}

	@Override
	public boolean deleteDag(DagRequest dag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ZaalDienst getZaalDienst(ZaalDienstRequest request) {
		ZaalDienst dt = new ZaalDienst(request);

		DataBase db = new DataBase();

		ResultSet r = db.runSelect("SELECT * FROM zaaldienst WHERE id = "
				+ request.getId());

		try {
			r.next();
			dt.setEmail(r.getString("email"));
			dt.setNaam(r.getString("naam"));
			dt.setChanged(false);
		} catch (SQLException e) {

			return null;
		} finally {

			db.closeDatabase();
		}

		return dt;
	}

	@Override
	public ZaalDienst saveZaalDienst(ZaalDienst dienst) {
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
				+ "vrijdag = %d, zaterdag = %d, zondag = %d, canlogin = %d, password = '%d'";

		sql = String.format(sql, dienst.getNaam(), dienst.getEmail(),
				dienst.getAantal(), dienst.getDagi(0), dienst.getDagi(1),
				dienst.getDagi(2), dienst.getDagi(3), dienst.getDagi(4),
				dienst.getDagi(5), dienst.getDagi(6), (dienst.isCanlogin() ? 1 : 0), dienst.getPassword());

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
				tmp.setPassword(r.getString("password"));
				tmp.setCanlogin(r.getBoolean("canlogin"));

				data[i] = tmp;
				i++;
				if (r.isLast()) {
					break;
				}
				r.next();
			}

		} catch (SQLException e) {

			return null;
		}

		db.closeDatabase();
		return data;
	}

	@Override
	public Team[] getAlleTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZaalDienst login(String user, String password) {
		DataBase db = new DataBase();

		try {
			ResultSet r = db.runSelect(String.format("SELECT * FROM zaaldienst WHERE email = '%s' AND password = '%s'", user, password));
			
			if (r == null)
			{
				db.closeDatabase();
				return null;
			}

			r.next();
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
				tmp.setPassword(r.getString("password"));
				tmp.setCanlogin(r.getBoolean("canlogin"));
				
				
				if (r.getInt("canlogin") == 0)
				{
					db.closeDatabase();
					return null;
				}
				
				db.closeDatabase();
				return tmp;
			}
		} catch (NullPointerException e2)
		{
			db.closeDatabase();
			return null;
		} catch (SQLException e) {
			db.closeDatabase();
			return null;
		}
	}
}