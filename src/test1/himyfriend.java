package test1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class himyfriend
 */
@WebServlet("/himyfriend")
public class himyfriend extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		response.setCharacterEncoding("UTF-8");//이것과 아래것
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		String fname=request.getParameter("first_name");
		String lname=request.getParameter("last_name");
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>hellow 확인, "+fname+"  "+lname+"</h1>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

}
