package nl.paul.sohier.ttv.server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.paul.sohier.ttv.DataBase.DataBase;
import nl.paul.sohier.ttv.libary.API;
import nl.paul.sohier.ttv.libary.Dag;
import nl.paul.sohier.ttv.libary.DagRequest;
import nl.paul.sohier.ttv.libary.ServerException;
import nl.paul.sohier.ttv.libary.ZaalDienst;
import nl.paul.sohier.ttv.libary.ZaalDienstRequest;

//Service Implementation
@WebService(endpointInterface = "nl.paul.sohier.ttv.server.Server")
public class ServerImpl implements Server {

	private SessionFactory sf = null;
	public ServerImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Dag getSavedDag(DagRequest request) throws ServerException {
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<Dag> dag = (List<Dag>)session.createQuery("from Dag where datum = ?").setDate(0, request.getDatum()).list();
		session.close();
		
		if (dag.size() == 0)
		{
			return API.createStandardDag(request);
		}
		return dag.get(0);

	}

	@Override
	public Dag saveDag(Dag dag) throws ServerException {
		DataBase db = null;
		try {
			db = new DataBase();

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

			sql += "dag = %d, maand = %d, jaar = %d, ochtend = %d, middag = %d, avond = %d, team = '%s', opmerkingen = '%s'";

/*			sql = String.format(sql, dag.getDag(), dag.getMaand(),
					dag.getJaar(), dag.getDeelOpeni(0), dag.getDeelOpeni(1),
					dag.getDeelOpeni(2), dag.getTeam(), dag.getOpmerkingen());
*/
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

/*				int[] o = dag.getDeelZaalDienst(0);
				int[] m = dag.getDeelZaalDienst(1);
				int[] a = dag.getDeelZaalDienst(2);
				int l = o.length + m.length + a.length;
*/
//				String[] s = new String[l];

				sql = "INSERT INTO dienst SET type = %d, user = %d, dag = %d";

				int c = 0;
/*
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
				}*/

//				for (int i = 0; i < s.length; i++) {
	//				db.runInsert(s[i]);
		//		}
			}

			if (result) {
		//		dag.setSaved(true);
			}

		} catch (SQLException e) {
		//	dag.setSaved(false);
			throw new ServerException(e);
		} finally {
			if (db != null) {
				db.closeDatabase();
			}
		}
		return dag;
	}

	@Override
	public boolean deleteDag(DagRequest dag) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ZaalDienst getZaalDienst(ZaalDienstRequest request)
			throws ServerException {
		Session session = sf.openSession();

		@SuppressWarnings("unchecked")
		List<ZaalDienst> dag = (List<ZaalDienst>)session.createQuery("from Zaaldienst where id = ?").setInteger(0, request.getId()).list();
		session.close();
		
		return dag.get(0);
		
	}

	@Override
	public ZaalDienst saveZaalDienst(ZaalDienst dienst) throws ServerException {
		DataBase db = null;
		try {
			db = new DataBase();

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

		/*	sql = String.format(sql, dienst.getNaam(), dienst.getEmail(),
					dienst.getAantal(), dienst.getDagi(0), dienst.getDagi(1),
					dienst.getDagi(2), dienst.getDagi(3), dienst.getDagi(4),
					dienst.getDagi(5), dienst.getDagi(6),
					(dienst.isCanlogin() ? 1 : 0), dienst.getPassword());*/

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
				//dienst.setSaved(true);
			}
		} catch (SQLException e) {
		//	dienst.setSaved(false);
			throw new ServerException(e);
		} finally {
			if (db != null) {
				db.closeDatabase();
			}
		}

		return dienst;
	}

	@Override
	public ZaalDienst[] getAlleZaalDiensten() throws ServerException {
		Session session = sf.openSession();
		@SuppressWarnings("unchecked")
		List<ZaalDienst> dag = (List<ZaalDienst>)session.createQuery("from Zaaldienst").list();
		session.close();
		
		return (ZaalDienst[])dag.toArray();
	}

	@Override
	public ZaalDienst login(String user, String password)
			throws ServerException {

		DataBase db = null;
		try {
			db = new DataBase();
			ResultSet r = db
					.runSelect(String
							.format("SELECT * FROM zaaldienst WHERE email = '%s' AND password = '%s'",
									user, password));

			if (r == null) {
				return null;
			}

			r.next();
			while (true) {
				ZaalDienst tmp = new ZaalDienst();

				tmp.setNaam(r.getString("naam"));
				tmp.setEmail(r.getString("email"));
				tmp.setAantal(r.getInt("aantal"));

			/*	tmp.setDag(0, r.getBoolean("maandag"));
				tmp.setDag(1, r.getBoolean("dinsdag"));
				tmp.setDag(2, r.getBoolean("woensdag"));
				tmp.setDag(3, r.getBoolean("donderdag"));
				tmp.setDag(4, r.getBoolean("vrijdag"));
				tmp.setDag(5, r.getBoolean("zaterdag"));
				tmp.setDag(6, r.getBoolean("zondag"));*/

				tmp.setId(r.getInt("id"));
				tmp.setPassword(r.getString("password"));
				tmp.setCanlogin(r.getBoolean("canlogin"));

				if (r.getInt("canlogin") == 0) {
					return null;
				}

				return tmp;
			}
		} catch (NullPointerException e2) {
			throw new ServerException(e2);
		} catch (SQLException e) {
			System.out.println("ERR: " + e);
			throw new ServerException(e);
		} finally {
			if (db != null) {
				db.closeDatabase();
			}
		}
	}
}