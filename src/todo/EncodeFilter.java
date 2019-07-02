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

@WebFilter("/*")
public class EncodeFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 文字コードをUTF-8に変更
		req.setCharacterEncoding("UTF-8");
		String target = ((HttpServletRequest)req).getRequestURI();
		System.out.println(target);
		HttpSession session = ((HttpServletRequest)req).getSession();
		if(!target.equals("/todo_yamamoto/login.html")&&session.getAttribute("name")==null) {
			session.setAttribute("err", "不正なアクセスです");
			((HttpServletResponse)resp).sendRedirect("login.html");
			return;
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
