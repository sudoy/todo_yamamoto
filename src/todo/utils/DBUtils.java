package todo.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class DBUtils {
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/todolist_yamamoto?useUnicode=true&amp;characterEncoding=utf8";
		String user = "root";
		String password="";
		return DriverManager.getConnection(url, user, password);
	}
	public static void close(Connection con,PreparedStatement ps) {
		try{
			if(con!=null){con.close();}
			if(ps!=null){ps.close();}
		}catch(Exception e){}
	}
	public static void close(Connection con,PreparedStatement ps,ResultSet rs) {
		try{
			if(con!=null){con.close();}
			if(ps!=null){ps.close();}
			if(rs!=null){rs.close();}
		}catch(Exception e){}
	}
}
