package todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.services.DeleteService;
@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		List<String> err = validate(id);
		if(err.size()>0) {
			resp.sendRedirect("index.html");
			return;
		}
		DeleteService ds = new DeleteService();
		ds.deleteDB(id);
		resp.sendRedirect("index.html");
	}


	private List<String> validate(String id) {
		List<String> err = new ArrayList<>();
		if(id==null||id.equals("")) {
			err.add("idが存在しません。");
		}
        return err;
	}
}
