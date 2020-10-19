package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.internal.ws.handler.ServerLogicalHandlerTube;

public class RequestCounterFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RequestCounterFilter.class);
	Map<String, Integer> requestCounterMap ;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("RequestCounterFilter.init()");
		requestCounterMap = new HashMap<String, Integer>();
		// requestCounterMap 이 request, session, application 스코프 중 하나에 저장해야함.
		// request : 요청이 유효하는 동한만 살아있음
		// session : 사용자가 유효하는 동안만 살아있음
		
		ServletContext sc = filterConfig.getServletContext();
		sc.setAttribute("requestCounterMap", requestCounterMap);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("RequestCounterFilter.doFilter()");

		HttpServletRequest req = (HttpServletRequest)request;
		logger.debug("uri : {} ", req.getRequestURI());
		
		// uri별 요청 횟수
		// /memberList 12
		// /jstl/jstl_fmt_date.jsp 20
		// 어떤 자료 구조를 사용하면 좋을까??
		// List, Set, Map => 키:벨류 형태이므로 Map이 좋음
		// 로직 : 1. map객체에서 uri에 해당하는 요청이 있었느지 확인
		// 		-> 없으면 ? 값을 1로 해서 새로운 key  추가
		// 		-> 있으면 ? 기존 값에서  1을 더해 값을 수정
		Integer value = requestCounterMap.get(req.getRequestURI());
		
		// 해당 uri로 최초요청
		if(value == null) {
			requestCounterMap.put(req.getRequestURI(), 1);
		}
		//해당 uri로 최소 한번이상 요청이 존재 했던 경우
		else {
			requestCounterMap.put(req.getRequestURI(), value + 1);
		}
		
		// 등록된 다른 필터로 요청 위임
		// 만약 더이상 등록된 Filter가 없을 경우 처리할 서블릿 / jsp 으로 요청 전달
		
		//전처리
		
		chain.doFilter(request, response);
		
//		후처리 - servlet  응답 생성 후 응답이 웹브라우저로 가는 단계에서 후속처리
	}

	@Override
	public void destroy() {
		
	}
	
}
