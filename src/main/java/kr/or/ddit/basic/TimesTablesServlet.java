package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimesTablesServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		
		//1. resp 객체에서 writer 가져오기
		PrintWriter writer = resp.getWriter();
		
		//2. html문서 작성
		writer.println("<html>");
		writer.println("<head></head>");
		writer.println("<body>");
		// 구구단 표
		writer.println("<table border='1'>");
		for(int i = 1; i <= 9; i++) {
			writer.print("<tr>");
			for(int j=2; j<=9; j++) {
				writer.print("<td>"+j + " * "+i+" = "+j*i+"</td>");
			}
			writer.print("</tr>");
		}
		writer.println("</table>");
		
		writer.println("</body>");
		writer.println("</html>");
		
		writer.flush();
		writer.close();
		
		
		
	}
}
