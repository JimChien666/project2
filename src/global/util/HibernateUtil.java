package global.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory factory = createSessionFactory();

	private static SessionFactory createSessionFactory() {
		SessionFactory factory = null;
		try {
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
			factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
			return factory;
		} catch (Exception e) {
			e.printStackTrace();
			return factory;
		}
	}
	//ppt 34 間接使用SessionFactory
	public static SessionFactory getSessionFactory() {
		return factory;
	}

	public static void closeSessionFactory() {
		if (factory != null) {
			factory.close();
		}
	}
}
