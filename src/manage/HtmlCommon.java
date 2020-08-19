package manage;

import java.io.PrintWriter;

/**
 * Servlet implementation class HtmlCommon
 */

public class HtmlCommon {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HtmlCommon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void printHtmlHead(PrintWriter out, String title) {
		out.println("<HTML>");
		out.println("<head>");
		out.println("<meta http-equiv=\"content-type\" content=\"text/html;charset=euc-kr\">");
		out.println("<title>"+title+"</title>");
		out.println("</head>");
		out.println("<body>");
	}
	
	public void printHtmlEnd(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
	public void errMsgDisplay(PrintWriter out,String errMsg) {
		out.println("<br>");
		out.println("<FONT FACE=ARIAL size=3 color=red>");
		out.println(errMsg);
		out.println("</FONT>");
		out.println("<br>");
	}
	
	public void confirmMsgDisplay(PrintWriter out,String confirmMsg) {
		out.println("<br>");
		out.println("<FONT FACE=ARIAL size=3 color=blue>");
		out.println(confirmMsg);
		out.println("</FONT>");
		out.println("<br>");
	}
	

}
