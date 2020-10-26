package johnny.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import johnny.model.bean.Article;
import johnny.model.dao.ArticleDAO;
import johnny.util.HibernateUtil;

/**
 * Servlet implementation class DemoHibernateServletAction1
 */
@WebServlet("/DemoHibernateServletAction1")
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
		
		
		response.setContentType("text/html;charset=UTF-8");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		ArticleDAO aDAO = new ArticleDAO(session);
		Article aBean = aDAO.select(1003);
		
		PrintWriter out = response.getWriter();
		
	
		out.write(aBean.getId());
//		System.out.println(aBean.getId());
		
		out.write(aBean.getTitle());
		
		
		out.close();
		factory.close();
		
		
		
		
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		Session session = factory.getCurrentSession();
//
//		HouseBeanDAO hDao = new HouseBeanDAO(session);
//		HouseBean hBean = hDao.select(1001);
//
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//
//		out.write("HouseId:" + hBean.getHouseid() + "<br/>");
//		out.write("HouseName:" + hBean.getHousename() + "<br/>");
//
//		out.close();
	}

}
