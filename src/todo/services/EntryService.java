package todo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;

import todo.forms.EntryForm;
import todo.utils.DBUtils;

public class EntryService {
	public void setDB(EntryForm get) throws ServletException {
		String title = get.getTitle();
		String details = get.getDetails();
		String value = get.getValue();
		String limitdate = get.getLimitdate();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		//DBに追加
		try{
			con = DBUtils.getConnection();
			// select文
			sql = "INSERT INTO mainlist (title,details,value,limitdate) VALUES (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, details);
			ps.setString(3, value);
			ps.setString(4, limitdate);
			ps.executeUpdate();
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con, ps);
		}
	}
}
