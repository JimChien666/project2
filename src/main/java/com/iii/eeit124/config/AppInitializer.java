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
		rootContext.register(SpringMVCJavaConfig.class); //���U�γ]�w���O
		rootContext.setServletContext(servletContext);
		ServletRegistration.Dynamic mvc = servletContext.addServlet("mvc", new DispatcherServlet(rootContext)); // �ʺA���U�]�w���
		mvc.setLoadOnStartup(1);									//servletcontext�W�r
		mvc.addMapping("/"); //servletŪ�����|
		FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("endcodingFilter", new CharacterEncodingFilter());
		filterRegistration.setInitParameter("encoding", "UTF-8");				//�ʺA���U�L�o��
		filterRegistration.setInitParameter("forceEncoding", "true");
		filterRegistration.addMappingForUrlPatterns(null, false, "/*"); //�������n�s�X��utf-8(��H)
		filterRegistration = servletContext.addFilter("OpenSessionViewFilter", OpenSessionViewFilter.class);
		filterRegistration.addMappingForUrlPatterns(null, true, "/*");
//		filterRegistration.addMappingForServletNames(null, true, "mvc");
		filterRegistration = servletContext.addFilter("LoginCheckingFilter", LoginCheckingFilter.class);
//		filterRegistration.setInitParameter("sessionFactoryBeanName", "sessionFactory");
		filterRegistration.addMappingForUrlPatterns(null, true, "/*");
		filterRegistration.setInitParameter("mustLogin1", "/product/CreateProduct");
		filterRegistration.setInitParameter("mustLogin2", "/saveArticle");
		filterRegistration.setInitParameter("mustLogin3", "/order/*");
		filterRegistration.setInitParameter("replpyArticleMustLogin", "/replyArticle/*");
		filterRegistration.setInitParameter("updateArticleMustLogin", "/UpdateArticle");		
		filterRegistration.setInitParameter("mustLoginMyPets", "/ReadAnimal");
		filterRegistration = servletContext.addFilter("FindUserPassword", FindUserPassword.class);
		filterRegistration.addMappingForUrlPatterns(null, true, "/login");
		filterRegistration.addMappingForServletNames(null, true, "mvc");
		servletContext.addListener(new ContextLoaderListener(rootContext)); //�]�wservletContext��ť��
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