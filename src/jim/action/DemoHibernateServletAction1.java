package jim.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import global.util.HibernateUtil;
import jim.dao.ProductsDAO;
import jim.entity.Products;



/**
 * Servlet implementation class DemoHibernateServletAction1
 */
@WebServlet("/DemoProduct")
public class DemoHibernateServletAction1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		ProductsDAO PDao = new ProductsDAO(session);
		Products hBean = PDao.select(63);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.write("Name" + hBean.getName() + "<br/>");
		out.write("Price" + hBean.getPrice() + "<br/>");

		out.close();
	}

}
