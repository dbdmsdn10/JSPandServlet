package test1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class add extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");//이것과 아래것은 이 java에 있는것 출력할때
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");//이건 서버에 있는값을 가져올때 사용
		String strx=request.getParameter("x");
		String stry=request.getParameter("y");
		String add=request.getParameter("oper");
		
		
		int x=0,y=0;
		if(!strx.equals("")) {
			x=Integer.parseInt(strx);
		}
		if(!stry.equals("")) {
			y=Integer.parseInt(stry);
		}
		int result=0;
		if(add.equals("덧셈")) {
			result=x+y;
		}
		else if(add.equals("뺄셈")) {
			result=x-y;
		}
		response.getWriter().printf("result id %d\n", result);
		response.getWriter().printf(add);
	}

}
