package kr.or.ddit.config.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import kr.or.ddit.mvc.SessionCheckInterceptor;
import kr.or.ddit.mvc.interceptor.PerformanceCheckIntercetor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.or.ddit.mvc.view.DownloadView;
import kr.or.ddit.mvc.view.ExcelDownloadView;
import kr.or.ddit.mvc.view.ProfileImgView;

/*
	<context:component-scan base-package="kr.or.ddit" use-default-filters="false">
		<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
		<context:include-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
	</context:component-scan>
*/
@Configuration // 설정파일임을 알려주는 어노테이션
@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = false, 
			   includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class})})
// <mvc:annotarion-driven/>
@EnableWebMvc 
public class ApplicationContext extends WebMvcConfigurerAdapter {
								// 	<mvc:default-servlet-handler/> ==> extends 상속 (WebMvcConfigurerAdapter)
	@Override	// WebMvcConfigurerAdapter 메서드 오버라이드
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/*
	 view
	<bean id="jsonView" class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
	<bean id="profileImgView" class="kr.or.ddit.mvc.view.ProfileImgView"/>
	<bean id="downloadView" class="kr.or.ddit.mvc.view.DownloadView"/>
	<bean id="excelView" class="kr.or.ddit.mvc.view.ExcelDownloadView"/>
	 */
	
	@Bean // 빈 이름을 설정할 수 있음, 설정하지 않을 시 메소드 이름과 동일하게 생성됨
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	@Bean 
	public ProfileImgView profileImgView() {
		return new ProfileImgView(); 
	}
	@Bean 
	public DownloadView downloadView() {
		return new DownloadView(); 
	}
	@Bean 
	public ExcelDownloadView excelView() {
		return new ExcelDownloadView();
	}
	
	/*
	 <bean class ="org.springframework.web.servlet.view.BeanNameViewResolver">
		<property name="order" value="1"></property>
	</bean>		
	 */
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(1);
		return beanNameViewResolver;
	}
	
	
	
	/*
	<!-- tiles view resolver 추가
		1. tiles 설정 작업 ==> TilesConfigure 를 통해 설정
		2. tiles view resolver 등록
	 -->
	 <!-- 1 -->
	 <bean id="tilesConfigure" class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
	 	<property name="definitions">
	 		<list>
	 			<value>classpath:kr/or/ddit/config/tiles/tiles-definition.xml</value>
	 		</list>
	 	</property>
	 </bean>
	 <!-- 2 -->
	 <bean class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
	 	<property name="order" value="0"></property>
	 	<property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"></property>
	 </bean>
	 */
	
	@Bean
	public TilesConfigurer tilesConfigure() {
		TilesConfigurer tilesConfigure = new TilesConfigurer();
		tilesConfigure.setDefinitions("classpath:kr/or/ddit/config/tiles/tiles-definition.xml");
		return tilesConfigure;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(0);
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}
	
	
	/*
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="order" value="2"></property>
		<property name="prefix" value="/WEB-INF/views/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setOrder(2);
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
	
	
	/*
	<!-- 파일 업로드  처리를 위한 multipartResolver 설정 -->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		return multipartResolver;
	}
	
	
	/*
	<!-- localeResolver -->
	<bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"></bean>
	 */
	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	
	// 상속받은 WebMvcConfigurerAdapter 클래스의 addInterceptors() 메소드를 오버라이드 하여 
	// 인터셉터 등록
	/*
	 <!-- locale 변경 감지 interceptor -->
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
				<property name="paramName" value="lang"></property>
			</bean>
		</mvc:interceptor>
	</mvc:interceptors>
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		
		registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**");
	}
	
	/*
	<!-- 정적자원 매핑 처리
		1. url 단축을 목적
		2. WEB-INF :외부에서 접근 불가능한 공간에 저장된 자원을 접근 매핑
	 -->
	 <!-- localhost/resources/style.css  로 요청을 하면 loaction에 지정한 곳에 가서 찾아라-->
	 <!--  -->
	 <mvc:resources mapping="/resources/**" location="/WEB-INF/views/exception/"></mvc:resources>
	 								<!-- ** : 세그먼트, 여러개의 폴더구조로 되어있다.  -->
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/WEB-INF/views/exception/");
	}
}

