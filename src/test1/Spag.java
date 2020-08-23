package test1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Spag
 */
@WebServlet("/Spag")
public class Spag extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=0;
		String num_=request.getParameter("n");
		if(num_!=null&&!num_.equals("")) {
			num=Integer.parseInt(num_);
		}
		String result="초기화값";
		if(num%2==0) {
			result="짝수";
		}else {
			result="홀수";
		}
		request.setAttribute("result", result);
		String[] list= {"하나", "둘","셋"};
		request.setAttribute("list", list);
		RequestDispatcher dispatcher=request.getRequestDispatcher("nana.jsp");
		dispatcher.forward(request, response);
	}
}
