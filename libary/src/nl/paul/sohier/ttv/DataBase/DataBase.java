package nl.paul.sohier.ttv.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

	private Connection conn;

	public DataBase() throws SQLException {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost/ttv",
					"ttv", "ttv");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		catch (SQLException e)
		{
			System.out.println("SQL Exception: " + e);
			throw e;
		}
	}

	public void closeDatabase()   {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ResultSet runSelect(String sql) throws SQLException {
		ResultSet rs = conn.createStatement().executeQuery(sql);

		return rs;

	}

	public int runUpdate(String sql) throws SQLException {
		return conn.createStatement().executeUpdate(sql);

	}

	public long runInsert(String sql) throws SQLException {
		long key = -1L;
		Statement statement;

		statement = conn.createStatement();

		statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = statement.getGeneratedKeys();
		if (rs != null && rs.next()) {
			key = rs.getLong(1);
		}
		return key;

	}
}
