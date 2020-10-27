package johnny.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import johnny.model.bean.Article;
import johnny.model.dao.ArticleDAO;
import global.util.HibernateUtil;

/**
 * Servlet implementation class DemoHibernateServletAction1
 */
@MultipartConfig
@WebServlet("/ArticleDelete") // DemoHibernateServletAction3
public class ArticleDelete extends HttpServlet {
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

		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.getCurrentSession();
			String aId = request.getParameter("articleId").trim();

			int articleId = Integer.parseInt(aId);
//			WriteArticleImpl_Jdbc dao = new WriteArticleImpl_Jdbc();		
//			dao.deleteArticle(articleId);
			System.out.println(aId);
			System.out.println(articleId);
			ArticleDAO aDAO = new ArticleDAO(session);

			aDAO.delete(articleId);

			RequestDispatcher rd = request.getRequestDispatcher("/ShowArticle");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
