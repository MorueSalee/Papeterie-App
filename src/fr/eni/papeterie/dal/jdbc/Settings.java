package fr.eni.papeterie.dal.jdbc;

import java.util.Properties;

public class Settings {

	private static Properties properties;
	
	static {
		try {
			properties = new Properties();
			properties.load(Settings.class.getResourceAsStream("db.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String cle) {
		return properties.getProperty(cle);
	}
}
