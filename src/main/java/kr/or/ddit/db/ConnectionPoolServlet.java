package kr.or.ddit.db;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionPoolServlet extends HttpServlet{

	private static final Logger logger = LoggerFactory.getLogger(ConnectionPoolServlet.class);
	// tomcat 서버가 작동을 할 때 호출되는 init메서드 재정의
	@Override
	public void init() throws ServletException {	// request랑 response 객체는 사용 못함.. 
													// application영역에 저장
		
		logger.debug("ConnectionPoolServlet init()");
		// ConnectionPollServlet 초기화 될 때 zjsprtus vnfdmf todtjdgotj
		// application 영역에 저장
		// 다른 jsp, servlet에서는 application 영역을 통해 커넥션 풀을 접근
		
		// 커넥션 pool 생성
		BasicDataSource bd = new BasicDataSource();
		// 커넥션 pool을 생성할 때도 oracle에 접속을 하는 것이기 때문에 아래 4가지 정보가 필요함
		bd.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		bd.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
		bd.setUsername("KTK");
		bd.setPassword("java");
		bd.setInitialSize(20);// connection pool을 만들 때 초기 커넥션 생성 개수를 지정
		
		
		// application 영역에 저장
		ServletContext sc = getServletContext(); // ServletContext : 서블릿에서의 application 객체
		sc.setAttribute("bd", bd);
		
	}
}
