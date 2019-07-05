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
import todo.utils.ConstantUtils;
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
	public List<IndexForm> getDB(String did, String nowPage, String search,String personal_id,String sort) throws ServletException {
		List<Object> input = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String[] wordlist = null;
		if (search != null && !search.matches("^[ 　]*$")) {
			wordlist = search.split("[ 　]+");
		}
		int limit = (Integer.valueOf(nowPage) - 1) * ConstantUtils.DISPLAY_LINE;
		List<IndexForm> pack = new ArrayList<>();

		// DB操作
		try {
			con = DBUtils.getConnection();
			// sqlの作成
			sql = "SELECT id,title,value,limitdate,did FROM mainlist　";

			// WHERE句
			sql += "WHERE 1 = 1";
			if (did.equals("1")) {
				sql += "AND did = ? ";
				input.add("1");
			}
			if (wordlist != null) {
				for (int i = 0; i < wordlist.length; i++) {
					sql += "AND title LIKE ? ";
					input.add("%" + wordlist[i] + "%");

				}
			}
			sql += "AND personal_id = ? ";
			input.add(personal_id);
			// ORDER句
			sql += "ORDER BY " + sort + " LIMIT " + ConstantUtils.DISPLAY_LINE + " OFFSET ?";
			input.add(limit);

			ps = con.prepareStatement(sql);
			for (int i = 1; i <= input.size(); i++) {
				ps.setObject(i, input.get(i - 1));
			}
			// DB出力
			rs = ps.executeQuery();
			while (rs.next()) {
				// 取得したデータの変換
				int id = rs.getInt("id");
				String title = XSS(rs.getString("title"));
				String value = HTMLUtils.valueFormat(rs.getInt("value"));
				String limitdate = HTMLUtils.limitdateFormat(rs.getString("limitdate"));
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
	public List<String> updateDB(String[] id, String[] checked, String[] didVal,String personal_id) throws ServletException {
		List<String> success = new ArrayList<>();
		String message = "";
		Map<String, String> map = new HashMap<>();
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				map.put(id[i], didVal[i]);
			}
			UpdateService us = new UpdateService();

			// checkのついたデータのdidを2にする
			if (checked != null) {
				for (String c : checked) {
					// 元が1のみ更新
					if (map.get(c).equals("1")) {
						UpdateForm uf = us.getDB(c,personal_id);
						uf.setDid("2");
						us.updateDB(uf,personal_id);
						message += c + " ";
					}
					map.remove(c);
				}
			}
			if (!message.equals("")) {
				success.add("No. " + message + "を完了にしました！");
			}
			message = "";

			// 残りのデータのdidを1にする
			for (String key : map.keySet()) {
				// 元が2のみ更新
				if (map.get(key).equals("2")) {
					UpdateForm uf = us.getDB(key,personal_id);
					uf.setDid("1");
					us.updateDB(uf,personal_id);
					message += key + " ";
				}
			}
			if (!message.equals("")) {
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
	public double getDBLength(String did,String search,String personal_id) throws ServletException {
		List<Object> input = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		double length = 0;
		String[] wordlist = null;
		if (search != null && !search.matches("^[ 　]*$")) {
			wordlist = search.split("[ 　]+");
		}
		// DB操作
		try {
			con = DBUtils.getConnection();
			// sqlの作成
			sql = "SELECT count(id) FROM mainlist WHERE personal_id = ? ";
			input.add(personal_id);
			if (did.equals("1")) {
				sql += "AND did = ? ";
				input.add("1");
			}
			if (wordlist != null) {
				for (int i = 0; i < wordlist.length; i++) {
					sql += "AND title LIKE ? ";
					input.add("%" + wordlist[i] + "%");
				}
			}
			ps = con.prepareStatement(sql);
			for (int i = 1; i <= input.size(); i++) {
				ps.setObject(i, input.get(i - 1));
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
		text = text.replace("&", "&amp;");
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&gt;");
		return text.replace("\"", "&quot;");

	}



}
