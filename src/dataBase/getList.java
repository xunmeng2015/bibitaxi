package dataBase;

import java.io.IOException;
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

import servlet.JsonUtils;

/**
 * Servlet implementation class getList
 */
@WebServlet("/getList")
public class getList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String result = "<tr><td>姓名</td><td>位置</td><td>目的地</td><td>联系方式</td><td>订单时间</td></tr>";
		Connection con;
		try {
			con = connection.connectMysql();
			Statement stmt = con.createStatement();
			String sql;
			ResultSet rs = null;
			sql = "select * from list";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				result = result + "<tr><td>"+rs.getString("name")+"</td><td>"+rs.getString("place")+"</td><td>"+rs.getString("destination")+"</td><td>"+rs.getString("phoneNumber")+"</td><td>"+rs.getString("time")+"</td><td><a href=\"/bibi/get?phoneNumber="+rs.getString("phoneNumber")+"\">接单</a></td></tr>";
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
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("result", result);
		JsonUtils.writeJson(json, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}
}
