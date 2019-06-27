package todo.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class DBUtils {
	public static Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
		return ds.getConnection();
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
