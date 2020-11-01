package tw.wey.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "tw")
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();//啟用，可取代mvc-servlet.xml和web.xml
		addProperties();
	}
	
	@Bean
	public InternalResourceViewResolver ViewResolver() {
		InternalResourceViewResolver myViewResolver = new InternalResourceViewResolver();
		myViewResolver.setPrefix("/WEB-INF/pages/");
		myViewResolver.setSuffix(".jsp");
		return myViewResolver;
	}
	
	//取代beans.config.xml的oracledatasource
	//@Bean後面可透過(name = )取bean別名
	@Bean
	public DataSource getJndiObjectFactoryBean() {
		JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
		factoryBean.setJndiName("java:comp/env/jdbc/xe");
		DataSource ds = (DataSource)factoryBean.getObject();
		return ds;
	}
	
	//取代beans.config.xml的sessionFactory
	@Bean(destroyMethod = "destory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getJndiObjectFactoryBean());
		sessionFactory.setPackagesToScan("tw.wey");
		
		Properties p1 = new Properties();
		p1.put("hibernate.dialect", org.hibernate.dialect.Oracle12cDialect.class);
		p1.put("hibernate.current_session_context_class","thread");
		p1.put("hibernate.show_sql",Boolean.TRUE);
		p1.put("hibernate.format_sql",Boolean.TRUE);
		
		sessionFactory.setHibernateProperties(p1);
		
		return sessionFactory;
	}

	private void addProperties() {
		String driverClassName = env.getRequiredProperty("datasource.driverClassName");
		String url = env.getRequiredProperty("datasource.url");
		String username = env.getRequiredProperty("datasource.username");
		String password = env.getRequiredProperty("datasource.password");
		
		System.out.println("driverClassName:"+driverClassName);
		System.out.println("url:"+url);
		System.out.println("username:"+username);
		System.out.println("password:"+password);
	}
	
	@Bean
	public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");
		return cmr;
	}
}
