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

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IndexService is = new IndexService();
		HttpSession session = req.getSession();
		String did = (String) session.getAttribute("didValue");

		req.setAttribute("pack", is.selectDB(did));

		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
		session.setAttribute("err","");
		session.setAttribute("success","");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] checked = req.getParameterValues("check");
		String[] id = req.getParameterValues("id");
		String[] didVal = req.getParameterValues("did");
		IndexService is = new IndexService();
		String update = req.getParameter("update");
		HttpSession session = req.getSession();

		// 完了が押され、エラーがない場合のみ更新する
		if(update!=null) {
			List<String> err = validate(checked, id, didVal);
			session.setAttribute("err", err);
			if(err.size()==0) {
				is.updateDB(id, checked,didVal);
			}
		}

		String did = (String) session.getAttribute("didValue");
		req.setAttribute("pack", is.selectDB(did));
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
}
