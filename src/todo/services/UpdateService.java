package todo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import todo.forms.UpdateForm;
import todo.utils.DBUtils;
import todo.utils.HTMLUtils;

public class UpdateService {
	/**
	 * 指定したIDのデータを取得するメソッド
	 * @param id 取得したいSQL番号
	 * @return UpdateForm型のデータ
	 * @throws ServletException
	 */
	public UpdateForm getDB(String id,String personal_id) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		UpdateForm pack = null;
		sql = "SELECT title,details,value,limitdate,did FROM mainlist WHERE id = ? AND personal_id = ?";
		try{
			con = DBUtils.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, personal_id);
			rs = ps.executeQuery();
			while(rs.next()){
				String title = rs.getString("title");
				String details = rs.getString("details");
				int value = rs.getInt("value");
				String limitdate =  HTMLUtils.limitdateFormat(rs.getString("limitdate"));
				String did= rs.getString("did");
				if(limitdate=="") {
					limitdate = null;
				}
				pack = new UpdateForm(id,title, details, value, limitdate,did);
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con,ps,rs);
		}
		return pack;
	}

	/**
	 * SQLを更新するメソッド
	 * @param get UpdateForm型の更新したい情報データ
	 * @throws ServletException
	 */
	public int updateDB(UpdateForm get,String personal_id) throws ServletException {
		String id = get.getId();
		String title = get.getTitle();
		String details = get.getDetails();
		String value = String.valueOf(get.getValue());
		String limitdate = get.getLimitdate();
		String did = get.getDid();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		int change = 0;
		//DB更新
		try {
			con = DBUtils.getConnection();
			sql = "UPDATE mainlist SET title = ?,details = ?,value = ?,limitdate = ?,did = ? WHERE id = ? AND personal_id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, details);
			ps.setString(3, value);
			ps.setString(4, limitdate);
			ps.setString(5, did);
			ps.setString(6, id);
			ps.setString(7, personal_id);
			change = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//close処理
			DBUtils.close(con, ps);
		}
		return change;
	}
}
