package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() throws Exception {
		// class 이름으로 사용 가능
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String id="java", pw="1234";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		Connection con = DriverManager.getConnection(url,id,pw);
		return con;
	}
}
