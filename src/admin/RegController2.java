package admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)
@WebServlet("/admin/board/notice/reg")
public class RegController2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/admin/board/notice/reg.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");// 이것과 아래것은 이 java에 있는것 출력할때
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");// 이건 서버에 있는값을 가져올때 사용

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");

		Part filePart = request.getPart("file");// 파일 받기
		String filename=filePart.getSubmittedFileName();//파일 이름받기
		
		filePart.getInputStream();

		InputStream fis=filePart.getInputStream();//파일 읽기
		
		String realpath = request.getServletContext().getRealPath("/upload");
		System.out.println(realpath);
		String filepath=realpath+File.separator+filename;// \대신 separator를쓰자
		FileOutputStream fos=new FileOutputStream(filepath);
//		int b;
//		while((b=fis.read())!=-1) {//read란것은 바이트 단위로 읽는것을 의미한다
//			fos.write(b);
//		}
		//위것을 써도되는데 바이트씩해서 매우 오래걸림 ex)티스푼으로 항아리 채우기
		byte buf[]=new byte[1024];
		int size=0;
		while((size=fis.read(buf))!=-1) {//이때 read는 길이를 의미한다
			fos.write(buf,0,size);//내용,시작,끝  포스경로에 읽은파일을 다시쓰는것을 의미함
		}
		fos.close();
		fis.close();
		
		PrintWriter out = response.getWriter();
		out.printf("title: %s<br>", title);
		out.printf("content: %s<br>", content);
		out.printf("isOpen: %s<br>", isOpen);
		int is = 0;
		if (isOpen != null) {
			is = 1;
		}
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
		Calendar time = Calendar.getInstance();
		String format_time2 = format2.format(time.getTime());

		Connection con;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/xepdb?characterEncoding=UTF-8&serverTimezone=UTC", "root",
					"9009908dms");

			PreparedStatement st = con
					.prepareStatement("insert into notice(title,content,writer_id,public,regfate) value(?,?,?,?,?)");
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "초기값");
			st.setInt(4, is);
			st.setString(5, format_time2);
			//st.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println("오류= " + e.toString());
		}

	}
}
