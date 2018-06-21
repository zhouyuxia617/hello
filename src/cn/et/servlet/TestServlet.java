package cn.et.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestServlet extends HttpServlet{

	private static final long serialVersionUID = -7856010116005850876L;

	public TestServlet() {}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		
		if("zs".equals(userName)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			
			request.getRequestDispatcher("/suc.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
}
