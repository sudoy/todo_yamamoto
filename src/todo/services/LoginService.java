package todo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import todo.utils.DBUtils;

public class LoginService {
	/**
	 * 入力値がSQL内のデータと一致すれば名前、違えば空文字を返す
	 * @param email 入力Email
	 * @param pass 入力Pass
	 * @return 名前or空文字
	 * @throws ServletException
	 */
	public String checkDB(String email,String pass) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		String name = "";
		String getpass = "";
		//DBに追加
		try{
			con = DBUtils.getConnection();
			// select文
			sql = "SELECT name,pass from loginlist where email = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()) {
				getpass = rs.getString("pass");
				name = rs.getString("name");
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con, ps, rs);
		}
		if(getpass.equals(DBUtils.encryption(pass, name))) {
			return name;
		}
		return "";
	}
}
