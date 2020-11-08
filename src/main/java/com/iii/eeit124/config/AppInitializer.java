package com.iii.eeit124.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.iii.eeit124.util.FindUserPassword;
import com.iii.eeit124.util.LoginCheckingFilter;
import com.iii.eeit124.util.OpenSessionViewFilter;



//Web.xml
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {



	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {HibernateConfig.class};
	}

	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringMVCJavaConfig.class);
		rootContext.setServletContext(servletContext);
		ServletRegistration.Dynamic mvc = servletContext.addServlet("mvc", new DispatcherServlet(rootContext));
		mvc.setLoadOnStartup(1);
		mvc.addMapping("/");
		FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("endcodingFilter", new CharacterEncodingFilter());
		filterRegistration.setInitParameter("encoding", "UTF-8");
		filterRegistration.setInitParameter("forceEncoding", "true");
		filterRegistration.addMappingForUrlPatterns(null, false, "/*");
		filterRegistration = servletContext.addFilter("OpenSessionViewFilter", OpenSessionViewFilter.class);
		filterRegistration.addMappingForUrlPatterns(null, true, "/*");
//		filterRegistration.addMappingForServletNames(null, true, "mvc");
		filterRegistration = servletContext.addFilter("LoginCheckingFilter", LoginCheckingFilter.class);
//		filterRegistration.setInitParameter("sessionFactoryBeanName", "sessionFactory");
		filterRegistration.addMappingForUrlPatterns(null, true, "/*");
		filterRegistration.setInitParameter("mustLogin1", "/product/CreateProduct");
		filterRegistration = servletContext.addFilter("FindUserPassword", FindUserPassword.class);
		filterRegistration.addMappingForUrlPatterns(null, true, "/login");
		filterRegistration.addMappingForServletNames(null, true, "mvc");

		servletContext.addListener(new ContextLoaderListener(rootContext));
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}