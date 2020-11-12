package kr.or.ddit.config.spring;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//다른 자바파일에 있는것을 참조하고 싶을 때 : DataSourceContext.java 에 등록된 dataSource 스프링 빈을 주입받기 위해 선언
// @Import({DataSourceContext.class})  
// @EnableTransactionManagement  : 스프링 빈중에 PlatformTransactionManager 타입의 빈이 있으면 해당 빈을 
// 							       트랜잭션 메니저로 등록해줌 
@EnableTransactionManagement // 자바기반으로 트랜잭션관리하겠다고 알려주는 어노테이션
@Configuration
public class TransactionContext {
	
	
	/*
	 <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	 								<!-- 다른설정파일에 설정되어있는 빈객체이더라도 참조할 때 같은 파일에 있는 것처럼  
										  참조할 빈 객체의 이름을 가져다 쓸 수 있다. -->
	 	<property name="dataSource" ref="dataSource"></property>
	 </bean>
	 */
	
	@Resource(name = "dataSource")
	private DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		// DataSourceTransactionManager가 PlatformTransactionManager 인터페이스를 구현한 클래스
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		return transactionManager;
	}
	
	
	/*
	 <!-- 2 -->
	 <tx:advice id="txAdvisor" transaction-manager="transactionManager">
	 	<tx:attributes>
	 		<!-- tx:method name : 트랜잭션을 적용할 메소드의 이름, 패턴 적용 
	 							  select* (select로 시작하는 메소드들)
	 							  get*	  (get로 시작하는 메소드들)
	 							  insert* (insert로 시작하는 메소드들)
	 							  * 	  (메소드 전체)				   
	 		-->
	 		<tx:method name="*" />
	 				<!-- propagation="REQUIRED" :  
	 					 read-only="false" :  select만 할 때는 읽기만 하기때문에 "true"로 설정하면 속도가 올라감
	 					 rollback-for=""  : 선언적 트랜잭션에서는 예외가 발생하면 rollback을 수행하는데 rollback을 수행할 예외를 지정하는 것
	 					 no-rollback-for="" : 지정 예외에 대해서는 예외가 발생해도 rollback을 하지 않고 넘어가겠다. 
	 					 timeout="-1" : 지정한 시간 내에서 수행될때만 정상, 지정시간을 넘어가면 rollback하겠다, -1 : 무제한
	 				-->
	 	</tx:attributes>
	 </tx:advice>
	

	
	<!-- 3 -->
	<aop:config>
		<!-- execution, within - 특정 패키지에 담겨있는 클래스를 찾을 때, bean - 스프링빈 -->
		<aop:advisor advice-ref="txAdvisor" pointcut="within(kr.or.ddit..service.*)"/>
	</aop:config>

	 */
	// 위 두 부분에 해당하는 java 설정은 없음
	// @Transactional 어노테이션을 트랜잭션으로 삼을 메소드나 클래스 단위로 붙여준다
	// like 스프링 빈을 <bean> 엘레먼트를 통해 일일이 등록 ==> @Controller, @Service 어노테이션으로 대체한 방식과
	// ==> memberService 가서 @Transactional 어노테이션을 붙여준다

}
