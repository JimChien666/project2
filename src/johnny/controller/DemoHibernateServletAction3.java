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
import johnny.util.HibernateUtil;

/**
 * Servlet implementation class DemoHibernateServletAction1
 */
@MultipartConfig
@WebServlet("/DemoHibernateServletAction2")
public class DemoHibernateServletAction3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		String aId = request.getParameter("id").trim();
		
		try {
			String title = "";		
			String idStr = "";
			int id = 0;
			int articleId = Integer.parseInt(aId);
			Collection<Part> parts = request.getParts();
			
			if (parts != null) { 
				for (Part p : parts) {
					String fldName = p.getName();
					String value = request.getParameter(fldName);
					if (p.getContentType() == null) {   
						if (fldName.equals("id")) {
							idStr = value;
							idStr = idStr.trim();
							id = Integer.parseInt(idStr);								
							request.setAttribute("id", id);					
						}else if (fldName.equals("title")) {
							title = value;
							request.setAttribute("title", title);
						}
					}
				}
			}
		
			ArticleDAO aDAO = new ArticleDAO(session);

			Article article = new Article(id,title, 1, 1, 1, 1);
			aDAO.update(article.getId(), article.getTitle(), 1, 1, 1, 1);
		
		}catch (Exception e) {
			e.printStackTrace(); 
			RequestDispatcher rd = request.getRequestDispatcher("/DemoHibernateServletAction1");
			rd.forward(request, response);
		}
		
		
		
		
		

	}

}
