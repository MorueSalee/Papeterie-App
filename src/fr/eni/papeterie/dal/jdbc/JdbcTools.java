package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

	private static String URL;
	
	private static String USER;
	
	private static String PASSWORD;
	
	static {
		URL = Settings.getProperty("url");
		USER = Settings.getProperty("user");
		PASSWORD = Settings.getProperty("password");
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
}
