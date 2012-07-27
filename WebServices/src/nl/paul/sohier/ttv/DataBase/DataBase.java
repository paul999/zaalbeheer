package nl.paul.sohier.ttv.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

	private Connection conn;

	public DataBase() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://mysql.hosthuis.nl/zd",
					"zd", "54RhqJ6dmLyePuXj");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public void closeDatabase() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet runSelect(String sql) {
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);

			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public int runUpdate(String sql) {
		try {
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {

			e.printStackTrace();
			return -1;
		}

	}

	public long runInsert(String sql) {
		long key = -1L;
		Statement statement;
		try {
			statement = conn.createStatement();

			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getLong(1);
			}
			return key;

		} catch (SQLException e) {

			e.printStackTrace();
			return key;
		}
	}
}
