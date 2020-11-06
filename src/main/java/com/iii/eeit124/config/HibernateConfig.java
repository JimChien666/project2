package com.iii.eeit124.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.iii.eeit124")})
public class HibernateConfig {

	Environment env;
	
	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}

	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
		jndiBean.setJndiName("java:comp/env/jdbc/xe");		
		jndiBean.setProxyInterface(DataSource.class);
		jndiBean.setLookupOnStartup(false);
		jndiBean.afterPropertiesSet();
		DataSource ds = (DataSource)jndiBean.getObject();
		System.out.println("ds:" + ds);
		
//		ComboPooledDataSource ds = new ComboPooledDataSource();
//		ds.setUser(env.getProperty("spring.database.user"));
//		ds.setPassword(env.getProperty("spring.database.password"));
//		try {
//			ds.setDriverClass(env.getProperty("spring.database.driverclass"));
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		}
//		ds.setJdbcUrl(env.getProperty("spring.database.url"));
//		ds.setInitialPoolSize(Integer.parseInt((env.getProperty("spring.database.initialpoolsize"))));
//		ds.setMaxPoolSize(Integer.parseInt((env.getProperty("spring.database.maxpoolsize"))));
		return ds;
	}
	
	@Bean(destroyMethod = "destroy")
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan(new String[] { "com.iii.eeit124" });
		factory.setHibernateProperties(additionalProperties());
		return factory;
	}
	/*
	 * 
	 * <property name="hibernate.c3p0.min_size">5</property>
		<property name="hibernate.c3p0.max_size">20</property>
		<property name="hibernate.c3p0.acquire_increment">2</property>
		<property name="hibernate.c3p0.acquire_increment">1800</property>
		<property name="hibernate.c3p0.max_statements">150</property>
	 * */
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
		properties.put("hibernate.show_sql", Boolean.TRUE);
		properties.put("hibernate.format_sql", Boolean.TRUE);
		properties.put("hibernate.current_session_context_class", "thread");
		return properties;
	}
	
	
	@Bean(name = "transactionManager")
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
