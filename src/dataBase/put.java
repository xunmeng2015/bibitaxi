package dataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Servlet implementation class put
 */
@WebServlet("/put")
public class put extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public put() {
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
		String place = request.getParameter("place");
		String destination = request.getParameter("destination");
		String name = request.getParameter("userName");
		String phone = request.getParameter("phoneNumber");
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		String time = format.format(date);
		PrintWriter out = response.getWriter();
		try {
			Connection con = connection.connectMysql();
			String sql;
			Statement stmt = con.createStatement();
			ResultSet rs = null;
			sql = "select * from userRecord where userPhone='"+phone+"' and is_finish='0'";
			rs = stmt.executeQuery(sql);
			rs.last();
			int count1 = rs.getRow();
			if (count1 == 0){
//			out.println("55");
			sql = "select * from list where phoneNumber='"+phone+"'";
			rs = stmt.executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			if (count > 0){
				out.println("<script>alert(\"请不要重复提交订单\");");
				out.println("history.back();</script>");
			}
			else {
				sql = "insert into list(name, place, destination, phoneNumber,time) values('"+name+"','"+place+"','"+destination+"','"+phone+"','"+time+"')";
//				out.println(sql);
				stmt.executeUpdate(sql);
				sql = "insert into userRecord(name, place, destination, userPhone,time, is_finish) values('"+name+"','"+place+"','"+destination+"','"+phone+"','"+time+"', '0')";
//				out.println(sql);
				stmt.executeUpdate(sql);
			out.println("<script>alert('提交成功');");
			out.println("window.location.href='http://192.168.204.84:8080/bibitaxi/user.html'</script>");
			}
			}
			else {
				out.println("<script>alert(\"你还有未评价的订单\");");
				out.println("history.back();</script>");
			}
//			rs.close();
			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
