package todo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import todo.utils.DBUtils;

public class DeleteService {
	/**
	 * sqlのDELETEを行うメソッドです。
	 * @param id 削除するidを指定します。
	 * @throws ServletException
	 */
	public void deleteDB(String id) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM mainlist WHERE id = ?";
		ResultSet rs = null;
		try{
			con = DBUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con,ps,rs);
		}
		return;
	}
}
