package kr.or.ddit.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceCheckIntercetor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceCheckIntercetor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	// handler : (==controller) 정보
			throws Exception {
		
		long start = System.currentTimeMillis();  //1/1000 초
				//		System.nanoTime();	// 1/10억 초
		
		
		// start가 지역변수인 문제를 해결하기 위해 request 객체에 저장
		request.setAttribute("start", start);
		// true : 다음 인터셉터 호출, 인터셉터가 없을 경우 handler(controller 호출)
		// false : 요청 처리를 멈춘다 -> 컨트롤러까지 안간다.
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// 요청마다 생성되는  start값을 꺼내온다.
		long start = (long)request.getAttribute("start");
		
		long end = System.currentTimeMillis();
		
		logger.debug("{} end-start : {}" , handler,end-start);
		
	}
}
