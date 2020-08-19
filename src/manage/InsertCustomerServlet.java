package manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertCustomerServlet
 */
@WebServlet("/InsertCustomerServlet")
public class InsertCustomerServlet extends HttpServlet {
	String driver = null;
	String url = null;
	String dbName = null;
	String user = null;
	String passwd = null;
	String submitType = null;
	HtmlCommon htmlCommon = new HtmlCommon();

	public void init() throws ServletException {
		ServletContext sc = getServletContext();
		driver = sc.getInitParameter("driver");
		url = sc.getInitParameter("url");
		dbName = sc.getInitParameter("dbName");
		user = sc.getInitParameter("user");
		passwd = sc.getInitParameter("passwd");
	}

	public void destroy() {
		driver = null;
		url = null;
		dbName = null;
		user = null;
		passwd = null;
		submitType = null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html:charset=euc-kr");//언어
		PrintWriter out=response.getWriter();
		
		boolean dataComplete=true;
		String errMsg=null;
		String confirmMsg=null;
		Customer customer=new Customer();
		
		CustomerSql customersql=new CustomerSql();
		if(!customersql.dbConnect(driver, url+dbName, user, passwd)) {
			return;
		}
		try {
			submitType = request.getParameter("makeSubmit");
			if(submitType==null||submitType.length()==0)
			{
				submitType="Reset";
			}
			if(submitType.compareTo("Insert")==0) {
				//----------------
				String customer_id=request.getParameter("id");
				errMsg=customersql.validCustomerId(customer_id);
				if(errMsg!=null)dataComplete=false;
				else customer.setid(customer_id);
				//-------------
				String customer_name=new String(((String)request.getParameter("name")).getBytes("iso-8859-1"),"euc-kr");
				customer.setname(customer_name);
				//-------------------
				String customer_tel=request.getParameter("phone");//?????????????????
				customer.settel(customer_tel);
				//----------------------------
				String customer_addr=new String(((String)request.getParameter("address")).getBytes("iso-8859-1"),"euc-kr");
				customer.setaddress(customer_addr);
				
				//데이터베이스에 레도드삽입
				if(dataComplete==true) {
					errMsg=customersql.insertCustomerRecord(customer);
					if(errMsg!=null)dataComplete=false;
					else confirmMsg=customer_id+" 고객이 정상으로 등록되었습니다<br> 새로운 고객정보를 입력하십시오";
				}
			}
		}catch(Exception e) {
			e.printStackTrace(out);
			errMsg="서블릿오류: "+e.getMessage();
			dataComplete=false;
		}finally {
			try {
				customersql.dbDisconnect();
			}catch(Exception e) {
				
			}
		}
		//화면표시
		htmlCommon.printHtmlHead(out, "고객정보 입력");
		if(dataComplete==true)displayInputScreen(out,confirmMsg);
		htmlCommon.printHtmlEnd(out);
	}

	private void displayInputScreen(PrintWriter out, String confirmMsg) {
		// TODO Auto-generated method stub
		if(confirmMsg!=null)htmlCommon.confirmMsgDisplay(out, confirmMsg);
		
		out.println("<form method='post' action='InsertCustomerServlet'>");
		out.println("<table border=1>");
		out.println("<tr>");
		out.println("<td colspan=2 align=center>[고객정보입력]</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>고객번호: </td>");
		out.println("<td><input type=text name=id size=6 maxlength=6></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>고객명: </td>");
		out.println("<td><input type=text name=name size=15</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>전화번호: </td>");
		out.println("<td><input type=text name=phone size=13 maxlength=13</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>주소: </td>");
		out.println("<td><input type=text name=address size=20</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td colspan=2 align=center>");
		out.println("<input type=submit name=reset value='Reset'>");
		out.println("<input type=submit name=makeSubmit value='Insert'>");
		out.println("<A HREF='CustomerMnagement'>Return</A>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
	}
	public void displayErrMsgScreen(PrintWriter out,String errMsg) {
		htmlCommon.errMsgDisplay(out, errMsg);
		out.println("<b>고객정보 입력을 계속하여면 다음을 클릭하십시오:");
		out.println("<A HREF='InsertCustomerServlet?'>Continue</A></b>");
	}

}
