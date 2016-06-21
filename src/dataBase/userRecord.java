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
import javax.servlet.http.HttpSession;

import servlet.JsonUtils;

/**
 * Servlet implementation class userRecord
 */
@WebServlet("/userRecord")
public class userRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userRecord() {
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
		String phone = (String) session.getAttribute("phone");
		String record = "<tr><td>时间</td><td>起始地</td><td>目的地</td><td>时间</td><td>是否完成</td></tr>";
		try {
			Connection con = connection.connectMysql();
			Statement stmt = con.createStatement();
			String sql;
			ResultSet rs = null;
			sql = "select * from userRecord";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				int is_finish = rs.getInt("is_finish");
				String fin = "";
				if (is_finish == 0)	fin = "<p><form action='/bibi/finish?time="+rs.getString("time")+"' method='post'><input type='number' name='evaluation' min=0 max=5 placeholder='请输入评分1-5' required><input type='submit' value='已完成' style='cursor : pointer;'></form></p>";
				else fin = "是";
				record = record + "<tr><td>"+rs.getString("name")+"</td><td>"+rs.getString("place")+"</td><td>"+rs.getString("destination")+"</td><td>"+rs.getString("time")+"</td><td>"+fin+"</td></tr>";
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
		json.put("record", record);
		JsonUtils.writeJson(json, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
