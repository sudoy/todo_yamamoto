package todo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import todo.forms.UpdateForm;
import todo.utils.DBUtils;
import todo.utils.HTMLUtils;

public class UpdateService {
	public UpdateForm getDB(String id) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		UpdateForm pack = null;
		sql = "SELECT title,details,value,limitdate FROM mainlist WHERE " + id;
		try{
			con = DBUtils.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String title = rs.getString("title");
				String details = rs.getString("details");
				int value = rs.getInt("value");
				String limitdate =  HTMLUtils.limitdateFormat(rs.getString("limitdate"));
				pack = new UpdateForm(title, details, value, limitdate);
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con,ps,rs);
		}
		return pack;
	}
}
