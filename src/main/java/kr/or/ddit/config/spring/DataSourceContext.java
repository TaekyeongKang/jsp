package kr.or.ddit.config.spring;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
	//<context:property-placeholder location="classpath:kr/or/ddit/config/db/db.properties"/>
	// ${key} ==> key
@PropertySource("classpath:kr/or/ddit/config/db/db.properties")
public class DataSourceContext {

	@Autowired
	private Environment env;
	
	
	/*
	 	<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
			<property name="url" value="${jdbc.url}"/>
			<property name="driverClassName" value="${jdbc.driver}"/>
			<property name="username"  value="${jdbc.username}"/>
			<property name="password" value="${jdbc.password}"/>
		</bean>
	 */
	// <bean> == > @Bean method
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		return dataSource;
	}
	
	
	
	/*
	<!-- mybatisUtill 역할 -->
	<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="configLocation" value="classpath:kr/or/ddit/config/db/mybatis-config.xml" />
		<property name="dataSource" ref="dataSource" />
	</bean>
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("kr/or/ddit/config/db/mybatis-config.xml"));
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}
	
	
	/*
	 <!-- sqlSession -->
	<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
		<!--  인자가 0개인 기본생성자가 없어서 오류남 => 최소한 sqlSessionFactory 는 인자로 넘겨야 함 -->
		<constructor-arg ref="sqlSessionFactoryBean" />
	</bean>
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean());
		// sqlSessionFactory 를  인자로 하는 sqlSessionTemplate 생성자가 없음... xml과 동일하게 할 수없음 오류 발생
		// ∵ sqlSessionFactoryBean 은 FactoryBean 인터페이스를 구현한 것, 
		// => sqlsessionFactoryBean.getObject() 가 리턴하는 것을 빈 객체로 생성하는것인데
		//    getObject() 가 리턴한는 값은 sqlSessionFactory 타입 
		// => sqlSessionFactoryBean -> slqsessionFactory 타입으로 변경해주어 일치시켜주어야 함.
		return sqlSessionTemplate;
	}
	
}
