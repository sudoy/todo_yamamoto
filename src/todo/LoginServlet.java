package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.forms.LoginForm;
import todo.forms.PersonalForm;
import todo.services.LoginService;
@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("name") != null) {
			resp.sendRedirect("index.html");
			return;
		}
		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("err","");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		if(session.getAttribute("name") != null) {
			resp.sendRedirect("index.html");
			return;
		}
		LoginService ls = new LoginService();
		PersonalForm personal = ls.checkDB(email, pass);

		// 名前が出力されない場合エラー
		if(personal.getName().equals("")) {
			session.setAttribute("err","メールアドレス、又はパスワードが間違っています。");
			req.setAttribute("pack",new LoginForm(email));
			getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
			return;
		}
		// ログイン成功
		session.setAttribute("name",personal.getName());
		session.setAttribute("personal_id", personal.getId());
		resp.sendRedirect("index.html");
	}
}
