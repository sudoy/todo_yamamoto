package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.forms.EntryForm;
import todo.services.EntryService;
@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String details = req.getParameter("details");
		int value = Integer.parseInt(req.getParameter("value"));
		String limitdate = req.getParameter("limitdate");
		EntryForm get = new EntryForm(title, details, value, limitdate);
		EntryService es = new EntryService();
		es.setDB(get);
		resp.sendRedirect("index.html");
	}
}
