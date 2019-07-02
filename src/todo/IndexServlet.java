package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.forms.UpdateForm;
import todo.services.IndexService;
import todo.services.UpdateService;
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IndexService is = new IndexService();
		HttpSession session = req.getSession();
		String did = (String)session.getAttribute("didValue");
		if(did.equals("1")) {
			req.setAttribute("pack",is.getDB("1"));
		}else {
			req.setAttribute("pack",is.getDB(""));
		}
		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] checked = req.getParameterValues("check");
		String[] id = req.getParameterValues("id");
		UpdateService us = new UpdateService();
		if(id!=null) {
			for(String i:id) {
				UpdateForm uf = us.getDB(i);
				uf.setDid("1");
				us.updateDB(uf);
			}
		}
		if(checked!=null) {
			for(String c:checked) {
				UpdateForm uf = us.getDB(c);
				uf.setDid("2");
				us.updateDB(uf);
			}
		}

		IndexService is = new IndexService();
		HttpSession session = req.getSession();
		String did = (String)session.getAttribute("didValue");
		if(did.equals("1")) {
			req.setAttribute("pack",is.getDB("1"));
		}else {
			req.setAttribute("pack",is.getDB(""));
		}
		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
}
