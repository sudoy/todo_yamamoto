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
	public List<IndexForm> getDB() throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<IndexForm> pack = new ArrayList<>();
		sql = "SELECT id,title,value,limitdate FROM mainlist ORDER BY id";
		try{
			con = DBUtils.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String value = HTMLUtils.valueFormat(rs.getInt("value"));
				String limitdate =  HTMLUtils.limitdateFormat(rs.getString("limitdate"));
				pack.add(new IndexForm(id, title,"", value, limitdate));
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			DBUtils.close(con,ps,rs);
		}
		return pack;
	}
}
