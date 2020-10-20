package kr.or.ddit.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UnitHttpServlerRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> parameterMap;
	
	public UnitHttpServlerRequestWrapper(HttpServletRequest request) {	// tomcat에서 만든 HttpServletRequest 객체를 인자로 받는 생성자
		super(request);
		
		parameterMap = new HashMap<String, String[]>(request.getParameterMap());
		setUnit();
		
	}

	@Override
	public String getParameter(String name) {

		// String[] vals = new String[]{} ;  ==> 값이 없는 배열도 있음 ==> 배열의 길이도 체크해줘야 함
		String[] values = parameterMap.get(name);	
		
		if(values != null && values.length>=1)
			return values[0];
		else 
			return null;
		
		//return values[0]; // nullPointException 발생 위험 -> 조건연산하여 반환
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		// map 객체의 key 값을 Enumeration type으로 리턴
		 // collection type의 객체를 인자로 받아 enumeration 객체로 변환해주는 메소드
		return Collections.enumeration(parameterMap.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		return parameterMap.get(name);
	}
	
	public void setUnit() {
		// 파라미터로 UNT_CD 가 있는지 조사
		// 1. 있으면
		//		별다른 작업 없음
		// 2. 없으면
		//		UNT_CD 파라미터로 DDIT 라는 문자열 값을 파라미터로 추가
		if(parameterMap.get("UNT_CD") == null) {
			parameterMap.put("UNT_CD", new String[] {"DDIT"});
		}
	}

}
