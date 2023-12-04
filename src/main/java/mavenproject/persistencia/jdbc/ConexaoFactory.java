package mavenproject.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	DriverManager driverManager;

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/wesleydev", "postgres", "postgres");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
