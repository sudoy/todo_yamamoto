package todo.utils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/**
 * DB接続の共通化
 */
public class DBUtils {
	/**
	 * Connectionの初期設定のメソッド
	 * @return 設定済みのConnection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("todolist_yamamoto");
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
	/**
	 * SHA-256を用いた暗号化
	 * @param pass 入力されたパスワード
	 * @param name sqlから取得した名前
	 * @return 暗号化されたハッシュ
	 */
	public static String encryption(String pass,String name) {
		String hash="";
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] result = digest.digest((pass+name).getBytes());
            hash = String.format("%040x", new BigInteger(1, result));
        } catch (Exception e){
            e.printStackTrace();
        }
		return hash;
	}
}
