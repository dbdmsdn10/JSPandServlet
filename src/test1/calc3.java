package test1;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class calc3 extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");// 이것과 아래것은 이 java에 있는것 출력할때
		response.setContentType("text/html; charset=UTF-8");
		//request.setCharacterEncoding("UTF-8");// 이건 서버에 있는값을 가져올때 사용
//		ServletContext application = request.getServletContext();// 저장지역? 캐쉬?같은것
//		HttpSession session = request.getSession();

	
		Cookie[] cookies = request.getCookies();//이건 캐쉬, 위와는 다른것

		String strx = request.getParameter("v");
		String op = request.getParameter("oper");
		int v = 0;

		if (!strx.equals("")) {
			v = Integer.parseInt(strx);
		}
		if (op.equals("결과")) {
			// int x=(int) application.getAttribute("value");
//			int x = (int) session.getAttribute("value");
			int x = 0;

			for (Cookie c : cookies) {
				if (c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			int result = 0;
			int y = v;
			// String op2=(String) application.getAttribute("oper");
//			String op2 = (String) session.getAttribute("oper");
			String op2 = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("op")) {
					op2 = URLDecoder.decode(c.getValue(), "UTF-8"); 
					
					break;
				}
			}

			if (op2.equals("덧셈")) {
				result = x + y;
			} else {
				result = x - y;
			}
			response.getWriter().printf("result id %d\n ", result);
			System.out.println("op2= "+op2);
		} else {
//		application.setAttribute("value", v);
//		application.setAttribute("oper", op);
//			session.setAttribute("value", v);
//			session.setAttribute("oper", op);

			Cookie valuecookie = new Cookie("value", ""+v);
			Cookie opcookie = new Cookie("op",URLEncoder.encode(op,"UTF-8"));
			response.addCookie(valuecookie);
			response.addCookie(opcookie);

			response.getWriter().println("뒤로 가서 숫자입력후 결과를 눌러주세요");
			response.sendRedirect("Carcul3.html");
		}

	}

}
