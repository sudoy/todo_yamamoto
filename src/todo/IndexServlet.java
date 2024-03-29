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

import todo.services.IndexService;
import todo.utils.ConstantUtils;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IndexService is = new IndexService();
		HttpSession session = req.getSession();
		String did = (String) session.getAttribute("didValue");
		String personal_id = (String) session.getAttribute("personal_id");
		if(session.getAttribute("sort")==null) {
			session.setAttribute("sort","id");
		}
		String sort = (String) session.getAttribute("sort");

		String nowPage = req.getParameter("page");
		if(nowPage==null) {
			nowPage = "1";
			session.setAttribute("search","");
		}
		String search = (String)session.getAttribute("search");

		// sqlのデータの長さの取得
		double length = is.getDBLength(did,search,personal_id);

		// 入力検査
		if(session.getAttribute("err")==null) {
			List<String> err = checkPage(nowPage, length);
			session.setAttribute("err", err);
			if(err.size()!=0) {
				nowPage = "1";
			}
		}
		req.setAttribute("nowPage",nowPage);


		req.setAttribute("page", (int)Math.ceil(length/ConstantUtils.DISPLAY_LINE));

		req.setAttribute("pack", is.getDB(did,nowPage,search,personal_id,sort));

		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

		session.setAttribute("err","");
		session.setAttribute("success","");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String[] checked = req.getParameterValues("check");
		String[] id = req.getParameterValues("id");
		String[] didVal = req.getParameterValues("did");
		IndexService is = new IndexService();
		String update = req.getParameter("update");
		String nowPage = req.getParameter("page");
		String sort;
		String search;
		String personal_id = (String) session.getAttribute("personal_id");

		if(req.getParameter("sort")!=null) {
			sort = checkSort(req.getParameter("sort"), (String)session.getAttribute("sort"));
			session.setAttribute("sort", sort);
		}else {
			sort = (String)session.getAttribute("sort");
		}

		if(req.getParameter("searchbutton")!=null) {
			search = req.getParameter("search");
			session.setAttribute("search", search);
		}else {
			search = (String)session.getAttribute("search");
		}

		if(nowPage==null) {
			nowPage = "1";
		}

		String did = (String) session.getAttribute("didValue");
		double length = is.getDBLength(did,search,personal_id);

		// 完了が押され、エラーがない場合のみ更新する
		if(update!=null) {
			List<String> err = validate(checked, id, didVal);
			session.setAttribute("err", err);
			if(err.size()==0) {
				session.setAttribute("success",is.updateDB(id, checked,didVal,personal_id));
			}
		}

		// 入力検査
		List<String> err = checkPage(nowPage, length);
		session.setAttribute("err", err);
		if(err.size()!=0) {
			nowPage = "1";
		}

		req.setAttribute("nowPage",nowPage);
		req.setAttribute("page", (int)Math.ceil(length/ConstantUtils.DISPLAY_LINE));

		req.setAttribute("pack", is.getDB(did,nowPage,search,personal_id,sort));
		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

		session.setAttribute("err","");
		session.setAttribute("success","");
	}

	public static List<String> validate(String[] checked,String[] id,String[] didVal){
		List<String> err = new ArrayList<>();
		if(id!=null&&!checkList(id, "[0-9]+")) {
			err.add("不正なIDが入力されました。");
		}
		if(didVal!=null&&!checkList(didVal, "[12]+")) {
			err.add("不正なdidが入力されました。");
		}
		if(id!=null&&didVal!=null&&id.length!=didVal.length) {
			err.add("入力数が一致しません");
		}
		if(checked!=null&&!matchId(checked, id)) {
			err.add("不正なcheckが入力されました。");
		}
		return err;
	}

	/**
	 * 配列に対する正規表現の検査
	 * @param list 検査するString配列
	 * @param match 正規表現
	 * @return 中身がすべて正しければtrueその他はfalse
	 */
	public static boolean checkList(String[] list,String match) {
		for(String s:list) {
			if(!s.matches(match)) {
				return false;
			}
		}
		return true;
	}

	public static boolean matchId(String[] checked,String[] id) {
		redo:
		for(String c :checked) {
			for(String i:id) {
				if(c.equals(i)) {
					continue redo;
				}
			}
			return false;
		}
		return true;
	}

	public static List<String> checkPage(String nowPage,Double length){
		List<String> err = new ArrayList<>();
		try {
			int num = Integer.valueOf(nowPage);
			if(!(1<=num&&num<=(int)Math.ceil(length/ConstantUtils.DISPLAY_LINE))) {
				err.add("不正なページ番号です。");
			}
		} catch (Exception e) {
			err.add("不正なページ番号です。");
		}
		return err;
	}

	public static String checkSort(String reqSort,String sesSrot) {
		if(!(reqSort.equals("id")||reqSort.equals("title")||reqSort.equals("value")||reqSort.equals("limitdate"))) {
			return "id";
		}
		if(reqSort.equals(sesSrot)) {
			return reqSort + " DESC";
		}
		return reqSort;
	}

}
