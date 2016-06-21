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
 * Servlet implementation class finish
 */
@WebServlet("/finish")
public class finish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public finish() {
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
		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession();
//		String phone = (String) session.getAttribute("phone");
		String time = request.getParameter("time");
		int evaluation = Integer.parseInt(request.getParameter("evaluation"));
//		System.out.println(time);
		try {
			Connection con = connection.connectMysql();
			Statement stmt = con.createStatement();
			String sql;
			ResultSet rs = null;
			sql = "update userRecord set evaluation='"+evaluation+"', is_finish='1' where time='" + time+"'";
			stmt.executeUpdate(sql);
			sql = "update driverRecord set evaluation='"+evaluation+"', is_finish='1' where time='" + time+"'";
			stmt.executeUpdate(sql);
			out.println("<script>alert('操作成功');");
			out.println("history.back()</script>");
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
