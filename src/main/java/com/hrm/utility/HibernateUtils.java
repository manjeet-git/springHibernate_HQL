package com.hrm.utility;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.hrm.entity.Project;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class HibernateUtils {
	
	@Value("${connection.driver_class}")
	private String driverClassName;
	
	@Value("${connection.url}")
	private String url;
	
	@Value("${connection.username}")
	private String username;
	
	@Value("${connection.password}")
	private String password;
	
	@Value("${show_sql}")
	private boolean showSQL;
	
	@Value("${hbm2ddl.auto}")
	private String autoDdl;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		datasource.setUrl(url);
		datasource.setDriverClassName(driverClassName);
		datasource.setUsername(username);
		datasource.setPassword(password);
		return   datasource;
	} 
	
	@Bean(autowire = Autowire.BY_TYPE)
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDataSource());
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		sessionFactoryBean.setAnnotatedClasses(new Class<?>[] {com.hrm.entity.Employee.class,
			com.hrm.entity.Email.class,
			com.hrm.entity.PanCard.class,
			com.hrm.entity.Project.class});
		return sessionFactoryBean;
		
	}
	
	
	public Properties getHibernateProperties() {
		Properties props=new Properties();
		props.put("show_sql",showSQL);
		props.put("hbm2ddl.auto","create");
		props.put("hibernate.dialect",dialect);
		return props;
	}
	
	 @Bean
	  public HibernateTransactionManager transactionManager() {
	    HibernateTransactionManager transactionManager = 
	        new HibernateTransactionManager();
	    transactionManager.setSessionFactory(getSessionFactory().getObject());
	    return transactionManager;
	  }
	
}
