package test1;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//ServletContext를 이용해 잠시 저장하는 기능을함
@WebServlet("/calc")
public class calc extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");// 이것과 아래것은 이 java에 있는것 출력할때
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");// 이건 서버에 있는값을 가져올때 사용
		ServletContext application=request.getServletContext();//저장지역? 캐쉬?같은것
		
		String strx = request.getParameter("v");
		String op=request.getParameter("oper");
		int v = 0;
		
		 if(!strx.equals("")) {
			 v=Integer.parseInt(strx);
		 }
		if(op.equals("결과")) {
			int x=(int) application.getAttribute("value");
			int result = 0;
			int y=v;
			String op2=(String) application.getAttribute("oper");	
			
			if(op2.equals("덧셈")) {
				result=x+y;
			}
			else {
				result=x-y;
			}
			response.getWriter().printf("result id %d\n", result);
		}
		else {
		application.setAttribute("value", v);
		application.setAttribute("oper", op);
		response.getWriter().println("뒤로 가서 숫자입력후 결과를 눌러주세요");
		}
		
		
		 
		

	}

}
