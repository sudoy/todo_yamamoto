package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<Integer> id = new ArrayList<>();
		List<String> title = new ArrayList<>();
		List<Integer> value = new ArrayList<>();
		List<String> limitdate = new ArrayList<>();
		sql = "SELECT id,title,value,limitdate FROM mainlist ORDER BY id";
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				id.add(rs.getInt("id"));
				title.add(rs.getString("title"));
				value.add(rs.getInt("value"));
				limitdate.add(rs.getString("limitdate"));
			}
		}catch(Exception e){
			throw new ServletException(e);
		}finally{
			try{
				if(con!=null){con.close();}
				if(ps!=null){ps.close();}
				if(rs!=null){rs.close();}
			}catch(Exception e){}
		}
		req.setAttribute("id", id);
		req.setAttribute("title", title);
		req.setAttribute("value", value);
		req.setAttribute("limitdate", limitdate);
		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
}
