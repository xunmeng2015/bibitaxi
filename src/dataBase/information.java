package dataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.JsonUtils;

/**
 * Servlet implementation class information
 */
@WebServlet("/information")
public class information extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public information() {
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
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session != null){
		String phone = (String) session.getAttribute("phone");
		String type = (String) session.getAttribute("type");
		String name = null;
		String address = null;
		String result = "<tr><td>姓名</td><td>";
		try {
			Connection con = connection.connectMysql();
			Statement stmt = con.createStatement();
			String sql;
			ResultSet rs = null;
			sql = "select * from " + type + " where phoneNumber='" + phone + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				result = result + rs.getString("name") +"</td></tr>" + "<tr><td>联系方式</td><td>"+ phone +"</td>"
						+ "</tr>" + "<tr><td>住址</td><td>"+ rs.getString("address") +"</td></tr>";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("information", result);
		JsonUtils.writeJson(json, request, response);
		}
		else{
			out.println("<script>alert('请先登录');");
			out.println("window.location.href='http://192.168.204.84:8080/bibitaxi/login.html'</script>");
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
