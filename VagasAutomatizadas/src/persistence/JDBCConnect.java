package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnect {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost/estacAutomac", "root", "admin");
			return conexao;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean closeConnection(Connection conexao) {
		try {
			conexao.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return true;
		}
	}

}