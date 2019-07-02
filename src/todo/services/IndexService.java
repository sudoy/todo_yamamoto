package todo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import todo.forms.IndexForm;
import todo.utils.DBUtils;
import todo.utils.HTMLUtils;

public class IndexService {
	/**
	 * indexに表示するデータの取得
	 * @param did 1が未完了、その他はすべてを指定する
	 * @return List<IndexForm>型のデータ
	 * @throws ServletException
	 */
	public List<IndexForm> getDB(String did) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<IndexForm> pack = new ArrayList<>();
		// DB操作
		try{
			con = DBUtils.getConnection();
			if(did.equals("1")) {
				sql = "SELECT id,title,value,limitdate,did FROM mainlist where did = ? ORDER BY id";
				ps = con.prepareStatement(sql);
				ps.setString(1, "1");
			}else {
				sql = "SELECT id,title,value,limitdate,did FROM mainlist ORDER BY id";
				ps = con.prepareStatement(sql);
			}
			rs = ps.executeQuery();
			while(rs.next()){
				// 取得したデータの変換
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String value = HTMLUtils.valueFormat(rs.getInt("value"));
				String limitdate =  HTMLUtils.limitdateFormat(rs.getString("limitdate"));
				String did1 = rs.getString("did");
				pack.add(new IndexForm(id, title, value, limitdate,did1));
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con,ps,rs);
		}
		return pack;
	}
}
