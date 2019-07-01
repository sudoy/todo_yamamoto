package todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.services.DeleteService;
@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// データの設定
		HttpSession session = req.getSession();
		session.setAttribute("err",null);
		session.setAttribute("success",null);
		String id = req.getParameter("id");
		List<String> err = validate(id);
		// errに文字が入っているか。(エラーの判定)
		if(err.size()>0) {
			session.setAttribute("err", "No."+id+" の削除に失敗しました");
			resp.sendRedirect("index.html");
			return;
		}
		// SQLに出力
		DeleteService ds = new DeleteService();
		ds.deleteDB(id);
		session.setAttribute("success", "No."+id+" の更新に成功しました");
		resp.sendRedirect("index.html");
	}

	/**
	 * 入力チェックメソッド
	 * @param id 入力のチェック
	 * @return エラーメッセージ
	 */
	private List<String> validate(String id) {
		List<String> err = new ArrayList<>();
		if(id==null||id.equals("")) {
			err.add("idが存在しません。");
		}
        return err;
	}
}
