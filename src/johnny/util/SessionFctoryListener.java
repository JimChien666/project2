package johnny.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SessionFctoryListener implements ServletContextListener {

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
