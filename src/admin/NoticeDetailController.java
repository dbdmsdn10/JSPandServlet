package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/board/notice/NoticeDetailController")
public class NoticeDetailController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con;
		PreparedStatement st;
		ResultSet rs;
		ResultSet rs2;
		int pre = 0;
		int next = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/xepdb?characterEncoding=UTF-8&serverTimezone=UTC", "root",
					"9009908dms");
			st = con.prepareStatement("select* from notice where id=?");
			st.setString(1, request.getParameter("id"));
			
			rs = st.executeQuery();
			rs.next();

			st = con.prepareStatement("SELECT COUNT(*) FROM notice");
			rs2 = st.executeQuery();
			rs2.next();
			int count = rs2.getInt("COUNT(*)");

			st = con.prepareStatement(
					"select * from (select @rownum:=@rownum+1 AS num,n. *from (select *from notice order by regfate) n,(select @rownum:=0) r)  b where id=?");
			st.setString(1, request.getParameter("id"));
			rs2 = st.executeQuery();
			rs2.next();
			int now = rs2.getInt("num");

			String nexttitle = null;
			String pretitle = null;
			st = con.prepareStatement(
					"select * from (select @rownum:=@rownum+1 as num,n. *from (select *from notice order by regfate) n, (select @rownum:=0) r) b where num=?");
			if (now - 1 > 0) {
				st.setInt(1, now - 1);
				rs2 = st.executeQuery();
				rs2.next();
				pre = rs2.getInt("id");
				pretitle = rs2.getString("title");
				st = con.prepareStatement(
						"select * from (select @rownum:=@rownum+1 as num,n. *from (select *from notice order by regfate) n, (select @rownum:=0) r) b where num=?");
			}
			if (now + 1 < count + 1) {
				st.setInt(1, now + 1);
				rs2 = st.executeQuery();
				rs2.next();
				next = rs2.getInt("id");
				nexttitle = rs2.getString("title");
				st = con.prepareStatement(
						"select * from (select @rownum:=@rownum+1 as num,n. *from (select *from notice order by regfate) n, (select @rownum:=0) r) b where num=?");
			}
			
			request.setAttribute("title", rs.getString("title"));
			request.setAttribute("regfate", rs.getString("regfate"));
			request.setAttribute("writer_id", rs.getString("writer_id"));
			request.setAttribute("hit", rs.getString("hit"));
			request.setAttribute("filse", rs.getString("filse"));
			request.setAttribute("content", rs.getString("content"));
			
			request.setAttribute("pre", pre);
			request.setAttribute("pretitle", pretitle);
			request.setAttribute("next", next);
			request.setAttribute("nexttitle", nexttitle);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("오류1"+e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("오류2"+e.toString());
		}
		catch(Exception e) {
			System.out.println("오류3"+e.toString());
		}
		
		request.getRequestDispatcher("/admin/board/notice/detail.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
