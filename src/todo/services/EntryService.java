package todo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import todo.forms.EntryForm;
import todo.utils.DBUtils;

public class EntryService {
	/**
	 * SQLに書き込み(INSERT)するメソッドです。
	 * @param get EntryForm型の書き込みたい情報
	 * @return 書き込みを行ったID番号
	 * @throws ServletException
	 */
	public int setDB(EntryForm get,String personal_id) throws ServletException {
		String title = get.getTitle();
		String details = get.getDetails();
		String value = get.getValue();
		String limitdate = get.getLimitdate();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		int id  = 0;
		//DBに追加
		try{
			con = DBUtils.getConnection();
			// select文
			sql = "INSERT INTO mainlist (title,details,value,limitdate,personal_id) VALUES (?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, details);
			ps.setString(3, value);
			ps.setString(4, limitdate);
			ps.setString(5, personal_id);
			ps.executeUpdate();
			// 追加したデータのidの取得
			rs = con.prepareStatement("SELECT LAST_INSERT_ID()").executeQuery();
			rs.next();
			id = rs.getInt("LAST_INSERT_ID()");
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con, ps, rs);;
		}
		return id;
	}
}
