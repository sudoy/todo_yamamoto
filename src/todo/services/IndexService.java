package todo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import todo.forms.IndexForm;
import todo.forms.UpdateForm;
import todo.utils.DBUtils;
import todo.utils.HTMLUtils;

public class IndexService {
	/**
	 * indexに表示するデータの取得
	 * @param did 1が未完了、その他はすべてを指定する
	 * @param nowPage 現在のページ数(limitの指定に使う)
	 * @return List<IndexForm>型のデータ
	 * @throws ServletException
	 */
	public List<IndexForm> getDB(String did,String nowPage) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		int limit = (Integer.valueOf(nowPage) - 1) * 10;
		List<IndexForm> pack = new ArrayList<>();
		// DB操作
		try {
			con = DBUtils.getConnection();
			if (did.equals("1")) {
				sql = "SELECT id,title,value,limitdate,did FROM mainlist where did = ? ORDER BY id LIMIT 10 OFFSET ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "1");
				ps.setInt(2, limit);
			} else {
				sql = "SELECT id,title,value,limitdate,did FROM mainlist ORDER BY id LIMIT 10 OFFSET ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, limit);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				// 取得したデータの変換
				int id = rs.getInt("id");
				String title = XSS(rs.getString("title"));
				String value = HTMLUtils.valueFormat(rs.getInt("value"));
				String limitdate = XSS(HTMLUtils.limitdateFormat(rs.getString("limitdate")));
				String did1 = rs.getString("did");
				pack.add(new IndexForm(id, title, value, limitdate, did1));
			}
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return pack;
	}

	/**
	 * DBのdidの更新を行うメソッド
	 * @param id 表示されているidのString配列
	 * @param checked チェックが付いているidのString配列
	 * @param didVal 表示されているidのdidの値のString配列
	 * @return 成功メッセージのList<String>
	 * @throws ServletException
	 */
	public List<String> updateDB(String[] id, String[] checked,String[] didVal) throws ServletException {
		List<String> success = new ArrayList<>();
		String message = "";
		Map<String, String> map = new HashMap<>();
		if(id!=null) {
			for(int i =0;i<id.length;i++) {
				map.put(id[i], didVal[i]);
			}
			UpdateService us = new UpdateService();

			// checkのついたデータのdidを2にする
			if(checked!=null) {
				for(String c:checked) {
					 // 元が1のみ更新
					if(map.get(c).equals("1")) {
						UpdateForm uf = us.getDB(c);
						uf.setDid("2");
						us.updateDB(uf);
						message += c + " ";
					}
					map.remove(c);
				}
			}
			if(!message.equals("")) {
				success.add("No. " + message + "を完了にしました！");
			}
			message ="";

			// 残りのデータのdidを1にする
			for (String key : map.keySet()) {
				// 元が2のみ更新
				if(map.get(key).equals("2")) {
					UpdateForm uf = us.getDB(key);
					uf.setDid("1");
					us.updateDB(uf);
					message += key + " ";
				}
			}
			if(!message.equals("")) {
				success.add("No. " + message + "を未完了にしました！");
			}
		}
		return success;
	}

	/**
	 * 目的のsqlのデータの件数を求めるメソッド
	 * @param did didの比較(これから検索も追加する)
	 * @return 取得されるデータの件数
	 * @throws ServletException
	 */
	public double getDBLength(String did) throws ServletException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		double length = 0;
		// DB操作
		try {
			con = DBUtils.getConnection();
			if (did.equals("1")) {
				sql = "SELECT count(id) FROM mainlist where did = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, "1");
			} else {
				sql = "SELECT count(id) FROM mainlist";
				ps = con.prepareStatement(sql);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				// 取得したデータの変換
				length = rs.getDouble("count(id)");
			}
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			DBUtils.close(con, ps, rs);
		}
		return length;
	}

	/**
	 * XSS(クロスサイトスクリプティング)対策の置換処理
	 * [&,<,>,"]をそれぞれのエスケープに置換する
	 * @param text 置換前の文字列
	 * @return XSS対策の置換処理後の文字列
	 */
	public String XSS(String text) {
		text = text.replace("&","&amp;");
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&gt;");
		return text.replace("\"", "&quot;");

	}

}
