package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/admin/board/notice/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");// 이것과 아래것은 이 java에 있는것 출력할때
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");// 이건 서버에 있는값을 가져올때 사용
		String[] openIDs = request.getParameterValues("open-id");
		String[] delIDs = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");

		Connection con;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/xepdb?characterEncoding=UTF-8&serverTimezone=UTC", "root",
					"9009908dms");
			 
			
			switch (cmd) {
			case "일괄공개":
				for (String openid : openIDs) {
					System.out.println("open-id= " + openid + "\n");
					PreparedStatement st =con.prepareStatement("update notice set public=1 where id= "+openid);
					st.executeUpdate();
				}
				break;
			case "일괄삭제":
				for (String delid : delIDs) {
					
					System.out.println("del-id= " + delid + "\n");
					PreparedStatement st =con.prepareStatement("DELETE from notice where id= "+delid);
					st.executeUpdate();
				}
				break;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
		catch (Exception e) {
			System.out.println("오류= "+e.toString());
		}
		request.getRequestDispatcher("/admin/board/notice/list.jsp").forward(request, response);
	}

}
