package kr.or.ddit.jsp;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ScopeServlet
 */
@WebServlet("/scopeServlet")
public class ScopeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/jsp/scopeView.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String param = req.getParameter("param");
		
		// request, session, application 객체에 각각 속성을 저장
		req.setAttribute("requestAttr", param);

		HttpSession session = req.getSession();
		session.setAttribute("sessionAttr", param);

		ServletContext sc = getServletContext();
		sc.setAttribute("applicationAttr", param);

		req.getRequestDispatcher("/jsp/scopeView.jsp").forward(req, resp);
	}


}
