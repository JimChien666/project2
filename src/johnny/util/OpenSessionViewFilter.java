package johnny.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.sun.net.httpserver.Filter.Chain;

public class OpenSessionViewFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			System.out.println("Transaction Begin.");
			
			
			chain.doFilter(request, response);

			session.getTransaction().commit();
			System.out.println("Transaction Commit.");
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			System.out.println("Transaction Rollback.");
			e.printStackTrace();
		} finally {
			System.out.println("Session Closed");
		}

	}

}
