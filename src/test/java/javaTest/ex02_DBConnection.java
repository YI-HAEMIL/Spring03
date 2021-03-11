package javaTest;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

//** @ 종류
//=> @Before - @Test - @After
//=> @ 적용 메서드 : non static, void 로 정의 해야 함.

public class ex02_DBConnection {
	
	// 1) nonStatic, void Test
	public void getConnectionNon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			DriverManager.getConnection(url,"test","1234");
			System.out.println("** DB 연결 성공 **"); // green
		} catch (Exception e) {
			System.out.println("** DB 연결 실패 Excception => "+e.toString());
			// green
		} 
	} //getConnectionNon()
	
	// 2) static Test
	// => test method 를 작성해서 Test
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
	
	@Test
	// => 연결성공 : NotNull / 실패 : Null
	public void connectionTest() {
		System.out.println("** Connection => "+getConnection());
		assertNotNull(getConnection());  // 성공 :green , 실패:red
	}

} // class
