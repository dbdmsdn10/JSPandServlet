package test1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class add2 extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");// 이것과 아래것은 이 java에 있는것 출력할때
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");// 이건 서버에 있는값을 가져올때 사용

		String[] strx = request.getParameterValues("y");
		int result = 0;
		for (int i = 0; i < strx.length; i++) {
			try {
				result += Integer.parseInt(strx[i]);
			} catch (Exception e) {
			}
		}
		response.getWriter().printf("result id %d\n", result);

	}

}
