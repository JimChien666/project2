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
		rootContext.register(SpringMVCJavaConfig.class); //註冊及設定類別
		rootContext.setServletContext(servletContext);
		ServletRegistration.Dynamic mvc = servletContext.addServlet("mvc", new DispatcherServlet(rootContext)); // 動態註冊設定文件
		mvc.setLoadOnStartup(1);									//servletcontext名字
		mvc.addMapping("/"); //servlet讀取路徑
		FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("endcodingFilter", new CharacterEncodingFilter());
		filterRegistration.setInitParameter("encoding", "UTF-8");				//動態註冊過濾器
		filterRegistration.setInitParameter("forceEncoding", "true");
		filterRegistration.addMappingForUrlPatterns(null, false, "/*"); //全部都要編碼為utf-8(對象)
		filterRegistration = servletContext.addFilter("OpenSessionViewFilter", OpenSessionViewFilter.class);
//		filterRegistration.setInitParameter("sessionFactoryBeanName", "sessionFactory");
		filterRegistration.addMappingForUrlPatterns(null, true, "/*");
		filterRegistration.addMappingForServletNames(null, true, "mvc"); // 設定映射servlet

		servletContext.addListener(new ContextLoaderListener(rootContext)); //設定servletContext監聽者
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