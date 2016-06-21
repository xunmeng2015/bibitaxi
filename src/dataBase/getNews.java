package dataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import servlet.JsonUtils;

/**
 * Servlet implementation class getNews
 */
@WebServlet("/getNews")
public class getNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getNews() {
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
		String sql;
		ResultSet rs = null;
		String result = "";
//		out.println(session.getAttribute("phone")+"  ");
		if (session != null){
			String phone = (String) session.getAttribute("phone");
			String type = (String) session.getAttribute("type");
			String name = null;
			String address = null;
			String infor = "<tr><td>姓名</td><td>";
			try {
			Connection con = connection.connectMysql();
			Statement stmt = con.createStatement();
			sql = "select * from " + type + " where phoneNumber='" + phone + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				infor = infor + rs.getString("name") +"</td></tr>" + "<tr><td>联系方式</td><td>"+ phone +"</td>"
						+ "</tr>" + "<tr><td>住址</td><td>"+ rs.getString("address") +"</td></tr>";
			}
			sql = "select * from news";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				result = result + "<p><a href="+rs.getString("href")+" target=\"_blank\">"+rs.getString("title")+"</a></p>";
			}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("news", result);
		json.put("infor", infor);
		JsonUtils.writeJson(json, request, response);
		}
		else{
			String infor = "0";
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("infor", infor);
			JsonUtils.writeJson(json, request, response);
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
