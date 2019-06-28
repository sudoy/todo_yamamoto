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
		sql = "SELECT title,details,value,limitdate FROM mainlist WHERE id = ?";
		try{
			con = DBUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				String title = rs.getString("title");
				String details = rs.getString("details");
				int value = rs.getInt("value");
				String limitdate =  HTMLUtils.limitdateFormat(rs.getString("limitdate"));
				pack = new UpdateForm(id,title, details, value, limitdate);
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con,ps,rs);
		}
		return pack;
	}
	public void updateDB(UpdateForm get) throws ServletException {
		String id = get.getId();
		String title = get.getTitle();
		String details = get.getDetails();
		String value = String.valueOf(get.getValue());
		String limitdate = get.getLimitdate();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		//DBに追加
		try {
			con = DBUtils.getConnection();
			sql = "UPDATE mainlist SET title = ?,details = ?,value = ?,limitdate = ? WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, details);
			ps.setString(3, value);
			ps.setString(4, limitdate);
			ps.setString(5, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			DBUtils.close(con, ps);
		}
	}
}
