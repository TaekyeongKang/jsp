package kr.or.ddit;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//테스트 대상 : repository + service : roo-context.xml
//repaository 에서 주입받는 sqlSessionTemplate : datasource-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/root-context.xml",
								   "classpath:kr/or/ddit/config/spring/ioc/datasource-context_dev.xml",
								   "classpath:kr/or/ddit/config/spring/transaction-context.xml"})
public class ModelTestConfig {

	@Resource(name = "dataSource")
	private DataSource dataSource;
	
	@Ignore
	@Test
	public void dummy() {
	}

	
	@Before
	public void setup() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		
		// 실행할 스크립트 넣기
			// 리소스를 반환해주는 클래스 , 인자로는 사용할 스크립트 파일의 경로를 적어줌
		populator.addScripts(new ClassPathResource("/kr/or/ddit/config/db/initData.sql"));
		populator.setContinueOnError(false); //스크립트 실행 중 에러 발생시 멈춤 지정
		// 실행
		DatabasePopulatorUtils.execute(populator, dataSource);
												// dataSource 는 스프링 빈으로 등록 해둠 -> 주입받으면 됨
	}
}
