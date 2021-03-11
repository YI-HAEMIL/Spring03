package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			return DriverManager.getConnection(url,"test","1234");
		} catch (Exception e) {
			System.out.println("** DB 연결 실패 => "+e.toString());
			return null;
		}
	} //getConnection

} // class
