package test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/database")
public class database extends HttpServlet {
	String driver = null, url = null, dbName = null, user = null, passwd = null;

	public void init() throws ServletException {// 객체 생성시 실행
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:5555";
		dbName = "/tutorial?characterEncoding=UTF-8&serverTimezone=UTC";
		user = "root";
		passwd = "9009908dms";
		
		try {
			Class.forName(driver);
		} catch (Exception e) {
			System.out.println("===================================================="+e.getMessage());
		}
	}

	public void destroy() {
		driver = null;
		url = null;
		dbName = null;
		user = null;
		passwd = null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		String query = "select*from memo";
		try {
			
			con=DriverManager.getConnection(url+dbName,user,passwd);
			
			stmt=con.createStatement();
			result=stmt.executeQuery(query);
			
			out.println("고객정보<br>");
			out.println("====================================================<br>");
			out.println("고객번호&nbsp;&nbsp; 고객명&nbsp;&nbsp;");
			out.println("전화번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;주소<br>");
			out.println("====================================================<br>");
			while(result.next()) {
				String resultstr=result.getString(1);
				resultstr+="&nbsp;&nbsp;&nbsp;&nbsp;";
				resultstr+=result.getString(2)+"&nbsp;&nbsp;";
				resultstr+=result.getString(3)+"&nbsp;&nbsp;";
				resultstr+=result.getString(4);
				out.println(resultstr+"<br>");
			}
			out.println("====================================================<br>");
		}catch(Exception e) {
			out.println("오류발견!!!!!!");
			e.printStackTrace(out);
		}finally {
			try {
				if(result!=null)result.close();
				if(stmt!=null)stmt.close();
				if(con!=null)con.close();
			}catch(Exception e) {}
		}
		

		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
