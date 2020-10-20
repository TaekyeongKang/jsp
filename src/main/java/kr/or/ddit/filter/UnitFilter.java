package kr.or.ddit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class UnitFilter
 */
@WebFilter("/UnitFilter")
public class UnitFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//새로운 request 객체를 생성
		UnitHttpServlerRequestWrapper req  = new UnitHttpServlerRequestWrapper((HttpServletRequest)request);
		
		// UNT_CD v파라미터를 새롭게 추가
		req.setUnit();
		
		chain.doFilter(req, response);	// ******** 새로 만든 객체인 "req" 로 파라미더 변경하기!!!!!!!!!!!!!!
		//chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
