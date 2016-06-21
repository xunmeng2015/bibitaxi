package dataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class get
 */
@WebServlet("/get")
public class get extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public get() {
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
		HttpSession session = request.getSession();
		String driverPhone = (String) session.getAttribute("phone");
		String phone = request.getParameter("phoneNumber");
		PrintWriter out = response.getWriter();
//		out.println(phone);
		String name ="";
		String destination ="";
		String place ="";
		String time ="";
		try {
			Connection con = connection.connectMysql();
			Statement stmt = con.createStatement();
			String sql;
			ResultSet rs = null;
			sql = "select * from driverRecord where driverPhone='"+driverPhone+"' and is_finish='0'";
			rs = stmt.executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			if (count == 0){
			sql = "select * from list where phoneNumber='"+phone+"'";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				name = rs.getString("name");
				place = rs.getString("place");
				destination = rs.getString("destination");
				time = rs.getString("time");
			}
//			out.println(sql);
			sql = "insert into driverRecord(userName, place, destination, driverPhone, userPhone, time, is_finish) values('"+name+"','"+place+"','"+destination+"','"+driverPhone+"','"+phone+"','"+time+"','0')";
//			out.println(sql);
			stmt.executeUpdate(sql);
//			out.println(sql);
			sql = "delete from list where phoneNumber='"+phone+"'";
			stmt.executeUpdate(sql);
			out.println("<script>alert(\"接单成功\");");
			out.println("history.back();</script>");
			}
			else {
				out.println("<script>alert(\"您还有未完成的订单\");");
				out.println("history.back();</script>");
			}
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
		doGet(request, response);
	}

}
