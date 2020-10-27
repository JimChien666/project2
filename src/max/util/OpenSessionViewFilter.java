package max.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OpenSessionViewFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain Chain)
			throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			System.out.println("Transaction begin!");
			
			Chain.doFilter(request, response);
			
			session.getTransaction().commit();
			System.out.println("Transaction commit!");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Transaction Rollback!");
			
			e.printStackTrace();
		}finally {
			System.out.println("Session Closed!");
		}

	}

}
