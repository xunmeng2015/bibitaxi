package dataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.java.swing.plaf.windows.resources.windows;

/**
 * Servlet implementation class DBS
 */
@WebServlet("/check")
public class DBS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		java.sql.Connection con=null;
		ResultSet rs=null;
		String sql;
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		String result = (new Date().getTime() + "").substring(0, 10);
		long newTime = Long.parseLong(result);
		long oldTime = newTime;
			try {
				con=connection.connectMysql();
				java.sql.Statement stmt = con.createStatement();
				sql = "select * from news where id=1";
				rs = stmt.executeQuery(sql);
				while (rs.next()){
					oldTime = rs.getLong("time");
				}
				if ((newTime - oldTime) > 14400){
					int num = 1;
				String urlString = "http://www.baidu.com/s?tn=baidurt&rtt=1&wd=%E6%BB%B4%E6%BB%B4&pnw=1&pbl=0&pbs=0&bsst=1&ie=utf-8";
				String html = connection.crawl(urlString);
				Pattern pat = Pattern.compile("(?<='F3':'54E5343F',).*?(?=<div class=\"realtime\">)");
				Matcher mat = pat.matcher(html);
				while (mat.find()&num <= 10){
					String href = "#";
					String title = "";
					String text = mat.group();
					Pattern pat1 = Pattern.compile("(?<=href=\").*?(?=\")");
					Matcher mat1 = pat1.matcher(text);
					mat1.find();
					href = mat1.group();
					Pattern pat2 = Pattern.compile("(?<=>).*?(?=<\\/h3>)");
					Matcher mat2 = pat2.matcher(text);
					mat2.find();
					title = mat2.group().replaceAll("<.*?>", "");
					System.out.println(title + href);
//					sql = "insert into news(title, href, time) values('"+title+"','"+href+"','"+result+"')";
					sql = "update news set title='"+title+"', href='"+href+"', time='"+newTime+"' where id="+num;
					stmt.executeUpdate(sql);
					num++;
				}
				}
				sql = "select * from "+type+" where phoneNumber='"+phone+"' and password='"+password+"'";
				rs = stmt.executeQuery(sql);
				rs.last();
				int count = rs.getRow();
				if (count == 0){
					response.setContentType("text/html;charset=\"utf-8\"");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('登录失败');");
					out.println("window.location.href='http://192.168.204.84:8080/bibitaxi/login.html'</script>");
				}
				else {
					session.setAttribute("phone", phone);
					session.setAttribute("type", type);
					response.sendRedirect("http://192.168.204.84:8080/bibitaxi/"+type+".html?phone="+phone+"&type="+type);
				}
				
				rs.close();
				stmt.close();
				con.close();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		doGet(request, response);
	}

}
