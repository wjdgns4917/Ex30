package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBCon {
	static Connection con;
	public DBCon() throws Exception {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="video";
		String pass="123456";
		Class.forName("oracle.jdbc.driver.OracleDriver");
	
		con=DriverManager.getConnection(url,user,pass);
		System.out.println("¼º°ø");
	}
	public static Connection getConnection() throws Exception {
		if (con==null) {
			new DBCon();
		}
		return con;
	}
	
}
