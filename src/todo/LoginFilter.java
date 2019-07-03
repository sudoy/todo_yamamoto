package todo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({ "/index.html", "/entry.html", "/update.html", "/delete.html" })
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) req).getSession();
		if (session.getAttribute("name") == null) {
			session.setAttribute("err", "不正なアクセスです");
			((HttpServletResponse) resp).sendRedirect("login.html");
			return;
		}
		if (session.getAttribute("didValue") == null) {
			session.setAttribute("didValue", "1");
		} else {
			if (req.getParameter("did1") != null) {
				session.setAttribute("didValue", "1");
			}
			if (req.getParameter("did2") != null) {
				session.setAttribute("didValue", "2");
			}
		}

		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
