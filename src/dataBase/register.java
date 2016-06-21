package dataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

import servlet.JsonUtils;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String type = request.getParameter("type");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
//		String mysqlUrl="jdbc:mysql://192.168.204.84:3306/database?user=xunmeng&password=xunmengg&useUnicode=true&characterEncoding=UTF8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=connection.connectMysql();
			java.sql.Statement stmt = con.createStatement();
//			Connection con = connection.connectMysql();
			String sql;
			ResultSet rs = null;
//			Statement stmt = con.createStatement();
			sql = "select * from "+type+" where phoneNumber='"+phoneNumber+"'";
			rs = stmt.executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			if (count > 0){
				out.println("<script>alert(\"该手机号码已被注册\");");
				out.println("history.back();</script>");
			}
			else {
				sql = "select * from "+type+" where email='"+email+"'";
				rs = stmt.executeQuery(sql);
				rs.last();
				int num = rs.getRow();
				if (num > 0){
					out.println("<script>alert(\"该邮箱已被注册\");");
					out.println("history.back();</script>");
				}
				else {
			sql = "insert into "+type+"(name, password, phoneNumber, email, address) values('"+name+"','"+password+"','"+phoneNumber+"','"+email+"','"+address+"')";
			stmt.executeUpdate(sql);
			out.println("<script>alert(\"注册成功\");");
			out.println("window.location.href='http://192.168.204.84:8080/bibitaxi/login.html'</script>");	
			}
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Map<String, Object> json = new HashMap<String, Object>();
//		json.put("type", type);
//		json.put("user", name);
//		json.put("pass", password);
//		json.put("sql", sql);
//		JsonUtils.writeJson(json, request, response);
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
