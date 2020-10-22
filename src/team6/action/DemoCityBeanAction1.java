package team6.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import team6.model.HouseBean;
import team6.model.HouseBeanDAO;
import team6.nn.model.CityBean;
import team6.nn.model.CityBeanDAO;
import team6.util.HibernateUtil;

/**
 * Servlet implementation class DemoHibernateServletAction1
 */
@WebServlet("/DemoCityBeanAction1")
public class DemoCityBeanAction1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		CityBeanDAO cDao = new CityBeanDAO(session);
		CityBean cBean = cDao.select(1);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.write("HouseId:" + cBean.getId() + "<br>");
		out.write("HouseName:" + cBean.getName() + "<br>");
		
		out.close();
	}

}
