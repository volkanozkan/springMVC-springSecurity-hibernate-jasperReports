package ozkan.volkan.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class JasperConnection {
	public Connection getConn() {
		try {
			String url = "jdbc:mysql://localhost:3306/volkan_ozkan_obss";
			String user = "root";
			String password = "";

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}