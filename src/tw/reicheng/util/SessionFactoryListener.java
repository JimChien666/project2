package tw.reicheng.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

public class SessionFactoryListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		HibernateUtil.closeSessionFactory();
		System.out.println("SessionFactory Closed.");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		HibernateUtil.getSessionFactory();
		System.out.println("SessionFactory Created.");
	}
	
}
