package com.iii.eeit124.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//@WebFilter("/*")
public class OpenSessionViewFilter implements Filter {
	private WebApplicationContext context;
	private SessionFactory sessionFactory;
	@Override
	public void init(FilterConfig config) throws ServletException {
		//String sessionFactoryBeanName = config.getInitParameter("sessionFactoryBeanName");
		ServletContext application = config.getServletContext();
		context = WebApplicationContextUtils.getWebApplicationContext(application);
		sessionFactory = (SessionFactory)context.getBean("sessionFactory");
	}
	
//	@Override
//	public void init(FilterConfig config) throws ServletException {
//		String sessionFactoryBeanName = config.getInitParameter("sessionFactoryBeanName");
//		ServletContext application = config.getServletContext();
//		context = WebApplicationContextUtils.getWebApplicationContext(application);
//		sessionFactory = (SessionFactory)context.getBean(sessionFactoryBeanName);
//	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println("Transaction begin.");
			
			chain.doFilter(request, response);
			
			session.getTransaction().commit();
			System.out.println("Transaction Commit.");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Transaction Rollbabk.");
			e.printStackTrace();
		} finally {
			System.out.println("Session Closed.");
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
