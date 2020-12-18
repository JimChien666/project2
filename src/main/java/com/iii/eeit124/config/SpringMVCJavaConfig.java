package com.iii.eeit124.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.iii.eeit124.aop.controller.MyControllerAspect;

//import com.iii.eeit124.shopping.controller.CartController;
//import com.iii.eeit124.util.MyAspect;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.iii.eeit124")
@PropertySource(value = { "classpath:application.properties" })
@EnableAspectJAutoProxy
public class SpringMVCJavaConfig implements WebMvcConfigurer {
//	@Autowired
//	private Environment env;

	public SpringMVCJavaConfig() {
			
		}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
		registry.addResourceHandler("/assets/**").addResourceLocations("/WEB-INF/resources/assets/");
		
	}

	@Bean
	public InternalResourceViewResolver ViewResolver() {
		InternalResourceViewResolver myViewResolver = new InternalResourceViewResolver();
		myViewResolver.setPrefix("/WEB-INF/views/");
		myViewResolver.setSuffix(".jsp");
		return myViewResolver;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	@Bean
	public MyControllerAspect getMyControllerAspect() {
		return new MyControllerAspect();
	}
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername("eeit124team6@gmail.com");
	    mailSender.setPassword("eeit1246");
	    //生日2000/12/25
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
}