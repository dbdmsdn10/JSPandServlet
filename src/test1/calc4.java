package test1;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc4")
public class calc4 extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8");// 이것과 아래것은 이 java에 있는것 출력할때
//		response.setContentType("text/html; charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");// 이건 서버에 있는값을 가져올때 사용
		Cookie[] cookies = request.getCookies();// 이건 캐쉬, 위와는 다른것

		String value = request.getParameter("value");
		String oper = request.getParameter("oper");
		String dot = request.getParameter("dot");
		int v = 0;

		String exp = "";
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		}
		if (oper != null && oper.equals("=")) {
			ScriptEngine engine=new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp=String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			exp += (value == null) ? "" : value;
			exp += (oper == null) ? "" : oper;
			exp += (dot == null) ? "" : dot;
		}
		Cookie expCookie = new Cookie("exp", exp);
		if (oper != null && oper.equals("c")) {
			exp="";
			expCookie.setMaxAge(0);
		}
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
	}

}
