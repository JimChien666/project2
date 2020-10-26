package johnny.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import johnny.model.bean.Article;
import johnny.model.bean.ArticleTypes;
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

	private void processAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();

		ArticleDAO aDAO = new ArticleDAO(session);

		String articletypesIdStr = request.getParameter("articletypesId");

		if (articletypesIdStr == null) {
			int articletypesId = 1;
			System.out.println(articletypesId);
			List<Article> ArticleList = aDAO.getAllArticles(articletypesId);
			request.setAttribute("ArticleList", ArticleList);
		} else if (articletypesIdStr != null) {
			int articletypesId = Integer.parseInt(articletypesIdStr);
			System.out.println(articletypesId);
			List<Article> ArticleList = aDAO.getAllArticles(articletypesId);
			request.setAttribute("ArticleList", ArticleList);
		}

		List<ArticleTypes> allArticleTypes = aDAO.getAllArticleTypes();

		request.setAttribute("allArticleTypes", allArticleTypes);
//		request.getRequestDispatcher("article/showAllArticles.jsp").forward(request, response);
		request.getRequestDispatcher("johnny/ShowAllArticle.jsp").forward(request, response);

	}

}
