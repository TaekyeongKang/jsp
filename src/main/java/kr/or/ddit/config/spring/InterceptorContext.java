package kr.or.ddit.config.spring;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kr.or.ddit.mvc.SessionCheckInterceptor;
import kr.or.ddit.mvc.interceptor.PerformanceCheckIntercetor;

//@Configuration // 설정파일임을 알려주는 어노테이션
@EnableWebMvc 
public class InterceptorContext extends WebMvcConfigurerAdapter {
	
	
	// 상속받은 WebMvcConfigurerAdapter 클래스의 addInterceptors() 메소드를 오버라이드 하여 
	// 인터셉터 등록
	/*
	<!-- interceptor 설정 -->
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**"/>  <!-- /** : 전체경로로 설정   -->
			<bean class="kr.or.ddit.mvc.interceptor.PerformanceCheckIntercetor"></bean>
		</mvc:interceptor>
		
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<mvc:exclude-mapping path="/login/**"/>
			<mvc:exclude-mapping path="/js/**"/>
			<mvc:exclude-mapping path="/css/**"/>
			<mvc:exclude-mapping path="/resources/**"/>
			<bean class="kr.or.ddit.mvc.SessionCheckInterceptor"></bean>
		</mvc:interceptor>
	</mvc:interceptors>
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new PerformanceCheckIntercetor()).addPathPatterns("/**");
		registry.addInterceptor(new SessionCheckInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/login/**",
									 "/js/**", 
									 "/css/**", 
									 "/resources/**" );
	}
	
}
	