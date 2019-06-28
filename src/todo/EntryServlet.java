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
		List<String> err = validate(title, value, limitdate);

		// errに文字が入っているか。エラーの判定
		if(err.size()>0) {
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
			return;
		}

		EntryForm get = new EntryForm(title, details, value, limitdate);
		EntryService es = new EntryService();
		es.setDB(get); // SQL出力

		resp.sendRedirect("index.html");
	}

	/**
	 * 入力の判定
	 * @param title 入力があるか、入力文字数の判定
	 * @param value 1~3以内かの判定
	 * @param limitdate 「YYYY/MM/DD」形式かの判定
	 * @return エラー出力文字のList
	 */
	private List<String> validate(String title,int value,String limitdate) {
		List<String> err = new ArrayList<>();
        // Date型変換
		if(title=="") {
        	err.add("題名は必須入力です。");
        }else if(100<title.length()) {
        	err.add("題名は100文字以内にしてください。");
        }
		if(value<1||3<value) {
			err.add("重要度の入力エラーが発生しました。");
		}
		if(limitdate != "") {
			try {
        	if(!limitdate.matches("[0-9]{4}/([0-9]|[0-9]{2})/([0-9]|[0-9]{2})")) {
        		System.out.println("d");
        		throw new Exception();
        	}
        	DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        	format.setLenient(false);
            format.parse(limitdate);
			} catch (Exception e) {
				System.out.println("k");
				err.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}

        return err;
	}
}
