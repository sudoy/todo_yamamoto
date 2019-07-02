package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.services.LoginService;
@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("err","");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		System.out.println(email + ":" + pass);
		LoginService ls = new LoginService();
		String name = ls.checkDB(email, pass);
		if(name.equals("")) {
			session.setAttribute("err","メールアドレス、又はパスワードが間違っています。");
			getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		}
		session.setAttribute("name", name);
		resp.sendRedirect("index.html");
	}
}
