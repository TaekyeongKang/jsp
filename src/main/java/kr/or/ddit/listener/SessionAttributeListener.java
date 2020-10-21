package kr.or.ddit.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVO;

public class SessionAttributeListener implements HttpSessionAttributeListener{

	private static final Logger logger = LoggerFactory.getLogger(SessionAttributeListener.class);
	
	//			userid	MemberVO
	private Map<String, MemberVO> userMap = new HashMap<String, MemberVO>();
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		if("S_MEMBER".equals(event.getName())) {
			
			//HttpSession session = event.getSession();
			//MemberVO memberVO = (MemberVO)session.getAttribute("S_MEMBER");
			// ==
			MemberVO memberVO = (MemberVO)event.getValue();
			
			logger.debug("사용자 로그인 : {}", memberVO.getUserid());
			
			userMap.put(memberVO.getUserid(), memberVO);
			
			ServletContext sc = event.getSession().getServletContext();
			sc.setAttribute("userMap", userMap);
			
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
		if("S_MEMBER".equals(event.getName())) {
			MemberVO memberVO = (MemberVO)event.getValue();
			
			logger.debug("사용자 로그아웃 : {}", memberVO.getUserid());
			userMap.remove(memberVO.getUserid());
			// application 에 저장하는 작업을 하지 않아도 된다.
			// 이유 : 이미 Heap메모리에 저장된 !!! userMap 을 바라본 후 그것을 수정한것이므로
			// 		 조회할 userMap.jsp에서 조회할 때도 같은 주소를 바라보고 그곳의 userMap을 읽어오는것이므로
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
	}

}
