package exp.gibin.app.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	
	@Value("${spring.datasource.driverClassName}")
	private String DRIVER;
	
	@Value("${spring.datasource.password}")
	private String PASSWORD;
 
	@Value("${spring.datasource.url}")
	private String URL;
 
	@Value("${spring.datasource.username}")
	private String USERNAME;
 
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String DIALECT;
 
	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;
 
	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Bean
	DataSource dataSource()
	{
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(DRIVER);
		dataSourceBuilder.password(PASSWORD);
		dataSourceBuilder.url(URL);
		dataSourceBuilder.username(USERNAME);
		return dataSourceBuilder.build();
	}
	
	
	//setup hibernate configurations
	
	@Bean
	LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource());
		localSessionFactoryBean.setPackagesToScan("exp.gibin.app.model");
		
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		localSessionFactoryBean.setHibernateProperties(hibernateProperties);
				
		return localSessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager tranctionManager()
	{
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		return hibernateTransactionManager;
	}
	
	
	
 //https://java2blog.com/spring-boot-hibernate-example/

}
