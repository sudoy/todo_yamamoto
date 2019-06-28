package todo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.forms.UpdateForm;
import todo.services.UpdateService;
@WebServlet("/update.html")
public class UpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UpdateService us = new UpdateService();
		req.setAttribute("pack", us.getDB(req.getParameter("id")));
		getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String details = req.getParameter("details");
		String value = req.getParameter("value");
		String limitdate = req.getParameter("limitdate");
		if(limitdate.equals("")) {
			limitdate = null;
		}
		List<String> err = validate(id,title, value, limitdate);
		req.setAttribute("err", err);

		// errに文字が入っているか。エラーの判定
		if(err.size()>0) {
			req.setAttribute("pack", new UpdateForm(id,title, details,Integer.valueOf(req.getParameter("value")), limitdate));
			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
			return;
		}
		// SQL出力
		UpdateService us = new UpdateService();
		us.updateDB(new UpdateForm(id,title, details, Integer.valueOf(req.getParameter("value")), limitdate));
		resp.sendRedirect("index.html");
	}

	private List<String> validate(String id,String title,String value,String limitdate) {
		List<String> err = new ArrayList<>();
		if(id.equals("")) {
			err.add("idが存在しません。");
		}
		if(title.equals("")) {
        	err.add("題名は必須入力です。");
        }else if(100<title.length()) {
        	err.add("題名は100文字以内にしてください。");
        }
		if(value==null||!(value.equals("1")||value.equals("2")||value.equals("3"))) {
			err.add("重要度の入力エラーが発生しました。");
		}
		if(limitdate != null) {
			try {
        	if(!limitdate.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}")) {
        		throw new Exception();
        	}
        	DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        	format.setLenient(false);
            format.parse(limitdate);
			} catch (Exception e) {
				err.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}
        return err;
	}
}
