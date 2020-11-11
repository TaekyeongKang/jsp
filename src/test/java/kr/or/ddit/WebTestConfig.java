package kr.or.ddit;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/root-context.xml",
								   "classpath:kr/or/ddit/config/spring/application-context.xml",
								   "classpath:kr/or/ddit/config/spring/ioc/datasource-context_dev.xml",
								   "classpath:kr/or/ddit/config/spring/transaction-context.xml"}) // 설정 파일 지정
@WebAppConfiguration		// 스프링 컨테이너를 웹기반에서 동작하는 컨테이너로 생성하는 옵션 
							// 왜 필요한가 : 스프링 컨테이너를 만드는 방법은 여러가지가 있는데 그 중에서도 
							//@Controller, @RequestMapping은 웹에서만 돌아가기 때문에 웹 기반에서 만들어달라고 설정하는 것.
public class WebTestConfig {

	@Resource(name = "dataSource")
	private DataSource dataSource;
	
	
	// 테스트 대상 클래스 : LoginController
	//					  --> MemberService
	// 							--> MemberRepository
	// LoginController 스프링 빈을 생성하기 위해서는  MemberSerice, MemberRepository 스프링 빈이 필요
	// 즉 service, repository 빈을 스캔하는 설정과  controller를 스캔하는 설정 두개가 필요
	
	// 스프링 프레임워크의 컨트롤러 테스트 시나리오
	// 1. 웹기반의 스프링 컨테이너를 구성 후 
	// 2. dispatcherServlet역할을 하는 객체를 생성
	// 3. dispathcerServlet 역할을 하는 객체를 통해 url, 파라미터 등을 첨부하여 요청 전송
	// 4. 응답이 원하는 형태로 나오는지 체크(viewName, model 에 담긴 내용)
	
	
	// MockMvc를 생성하는데에 필요한 인자
	// 스프링 빈, 타입으로 스프링 빈을 찾아서 주입하기 위해 @Autowired
	@Autowired
	private WebApplicationContext context;
	
	// dispatcherServlet 역할을 하는 것 = MockMvc 객체
	protected MockMvc mockMvc;
	
	/*
	  @Before(setup) ==> @Test ==> @After(tearDown)
	 */
	
	@Before
	public void setup() { // @Before 에서 mockMvc 객체 초기화
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); 
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		
		// 실행할 스크립트 넣기
			// 리소스를 반환해주는 클래스 , 인자로는 사용할 스크립트 파일의 경로를 적어줌
		populator.addScripts(new ClassPathResource("/kr/or/ddit/config/db/initData.sql"));
		populator.setContinueOnError(false); //스크립트 실행 중 에러 발생시 멈춤 지정
		// 실행
		DatabasePopulatorUtils.execute(populator, dataSource);
												// dataSource 는 스프링 빈으로 등록 해둠 -> 주입받으면 됨
		
	}
	
	// get(), post() : get/post 요청
		// param(파라미터명, 파라미터 값) : 요청시 보낼 파라미터
		
		// 응답
		// status() : 스프링 프레임워크에 의해 요청이 처리되고 생성된 응답 코드
		// view() : 스프링 프레임워크에 의해 요청이 처리되고 생성된 viewName
		// model() : 컨트롤러에서 설정한 속성값을 담는 객체
		// request() : 요청 객체

	
	@Ignore  // => skip 함
	@Test
	public void test() {
	}

}
