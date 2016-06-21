package dataBase;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;


public class connection {
	public static Connection connectMysql() throws ClassNotFoundException, SQLException{
//		String mysqlUrl="jdbc:mysql://localhost:3306/database?user=xunmeng&password=xunmengg&useUnicode=true&characterEncoding=UTF8";
		String mysqlUrl="jdbc:mysql://192.168.204.84:3306/database?user=xunmeng&password=xunmengg&useUnicode=true&characterEncoding=UTF8";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(mysqlUrl);
			return con;
}
	public static String crawl(String urlString) throws IOException{			//获取网页源码
		URL url=new URL(urlString);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		StringBuilder sb=new StringBuilder();
		BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
		String line;
		while ((line=br.readLine())!=null){
			sb.append(line);
		}
		return sb.toString();
	}
}