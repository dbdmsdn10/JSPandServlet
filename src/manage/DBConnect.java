package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	protected Connection con = null;

	public boolean dbConnect(String driver, String url, String user, String passwd) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
		} catch (java.lang.ClassNotFoundException e1) {

			System.err.println("ClassNotFoundException: ");
			System.err.println("드라이버 로딩오류: " + e1.getMessage());
			return false;

		} catch (SQLException e2) {
			System.err.println("데이터베이스 접속오류" + e2.getMessage());
			return false;
		}

		return true;
	}
	public void dbDisconnect() {
		try {
			if(this.con!=null)this.con.close();
		}catch(Exception e) {}
	}
}
