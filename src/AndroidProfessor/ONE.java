package AndroidProfessor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ONE
 */
@WebServlet("/ONE")
public class ONE extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int total = 0;
		 for(int cnt=1; cnt<101; cnt++){
		 total+=cnt;
		 }
		 PrintWriter out=response.getWriter();
		 out.println("<html> ");
		 out.println("<head><title>Hundred Servlet</title></head>");
		 out.println("<body>");
		 out.printf("1 + 2 + 3 + ... + 100 = %d", total);
		 out.println("</body>");
		 out.println("</html>");

	}

	

}
