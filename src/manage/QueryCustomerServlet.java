package manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryCustomerServlet
 */
@WebServlet("/QueryCustomerServlet")
public class QueryCustomerServlet extends HttpServlet {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html:charset=euckr");
		PrintWriter out=response.getWriter();
		
		boolean dataComplete=false;
		String customer_id=null;
		String customer_name=null;
		Vector <Customer>customers=new Vector<Customer>();
		
		//데이터베이스 연결
		CustomerSql customersql=new CustomerSql();
		if(!customersql.dbConnect(driver,url+dbName,user,passwd)) {
			return;
		}
		try {
			submitType=request.getParameter("makesubmit");
			if(submitType==null||submitType.length()==0) {
				submitType="Reset";
			}
			if(submitType.compareTo("Query")==0) {
				customer_id=request.getParameter("id");
				customer_name=new String(((String)request.getParameter("name")).getBytes("iso-8859-1"),"euc-kr");
				
				String query=customersql.compositeQuerry(customer_id, customer_name);
				customers=customersql.selectCustomerRecord(query);
				dataComplete=true;
			}
		}catch(Exception e) {
			e.printStackTrace(out);
			dataComplete=false;
		}finally {
			try {
				customersql.dbDisconnect();
			}catch(Exception e) {}
		}
		htmlCommon.printHtmlHead(out, "고객정보 검색화면");
		if(dataComplete==false)displayInputScreen(out);
		else displayQueryResult(out,customers);
		htmlCommon.printHtmlEnd(out);
	}

	private void displayQueryResult(PrintWriter out, Vector<Customer> customers) {
		int size=customers.size();
		if(size==0) {
			htmlCommon.errMsgDisplay(out, "조건에 해당되는 고객이 없습니다");
		}
		else {
			out.println("[고객정보 질의 결과]<br>");
			out.println("===============================<br>");
			out.println("고객번호&nbsp;&nbsp; 고객명&nbsp;&nbsp;");
			out.println("전화번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			out.println("&nbsp;&nbsp;&nbsp; 주소<br>");
			out.println("====================================<br>");
			for(int i=0;i<size;i++) {
				Customer acustomer=(Customer)customers.elementAt(i);
				String resultstr="&nbsp;:"+acustomer.getid();
				resultstr+="&nbsp;&nbsp;&nbsp;&nbsp;";
				resultstr+=acustomer.getname()+"&nbsp;&nbsp;";
				resultstr+=acustomer.gettel()+"&nbsp;&nbsp;";
				resultstr+=acustomer.getaddress();
				out.println(resultstr+"<br>");
			}
			out.println("==============================================<br>");
			out.println(size+"개의 레코드가 검색되었습니다<br><br>");
		}
		out.println("<b>고객정보 검색을 계속하려면 다음을 클릭하시오:");
		out.println("<A HREF='QueryCustomerServlet'>Continue</A></b>");
		
	}

	private void displayInputScreen(PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

}
