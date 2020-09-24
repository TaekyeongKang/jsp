package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿을 생성하는 방법
// 1. HttpServlet 클래스를 상속한다.
//		** servlet-api.jar 파일 넣어줘야 함. 
//			how?> 1) 프로젝트 우클릭 - [properties] - [project Facets] - javascript - runtime에 해당 프젝 체크해주기
// 2. doXXX 메소드를 구현한다.
// 3. servlet은 정적 자료와 다르게 이름이 없다. 
//	  ex) localhost/ServletTest/index.html 처럼 호출할 수가 없다.
// 	  => url 과 서블릿 매핑하는 작업 필요
//			: url을 직접 이름을 생성해줘야 한다. (in web.xml)
//				<servlet>
//					<servlet-name>basicServlet</servlet-name>
//					<servlet-class>kr.or.ddit.basic.BasicServlet</servlet-class>
//				</servlet>
//				<!-- /basicServlet으로 요청이 올 경우 basicServlet이라는 이름의 서블릿을 실행해라 -->
//				<servlet-mapping>
//					<servlet-name>basicServlet</servlet-name>
//					<url-pattern>/basicServlet</url-pattern>
//				</servlet-mapping>
//			을 작성
public class BasicServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=utf-8");
		// == resp.setCharacterEncoding("utf-8");
		
		//1. resp 객체에서 writer 가져오기
		PrintWriter writer = resp.getWriter();
		
		//2. writer 객체를 통해 html문서를 생성해준다. 
		// <html> <head></head>
		// 		<body> 현재시간 : ** 서버의 현재시간 (컨텐츠를 동적으로 계속 바꾼다.) </body> 
		// </html>
		writer.println("<html>");
		writer.println("	<head></head>");
		writer.println("	<body>현재시간 : "+ new Date()+ "</body>");
		writer.println("</html>");
		
		writer.flush();
		writer.close();
		
		
	}
}


