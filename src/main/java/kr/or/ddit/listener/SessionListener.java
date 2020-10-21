package kr.or.ddit.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener {

	
	private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);
	
	// 서버에 새로운 세션이 생성될 때
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
		logger.debug("sessionCreated");
	}

	// 서버에 있는 세션이 소멸될 때
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.debug("sessionDestroyed");
	}

}
