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
public class DemoHibernateServletAction2 extends HttpServlet {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		HttpSession hSession = request.getSession();
		System.out.println("try");
		try {
			String title = "";
			int activitysId = 0;
			int articleType = 0;
			int showArticle = 0;
			int memberId = 0;
//			int id = 0;
			String activitysIdStr = "";
			String articleTypeStr = "";
			String showArticleStr = "";
			String memberIdStr = "";
//			String idStr = "";

			Collection<Part> parts = request.getParts();
			System.out.println("if");
			if (parts != null) {
				for (Part p : parts) {
					String fldName = p.getName();
					String value = request.getParameter(fldName);

					if (p.getContentType() == null) {
						if (fldName.equals("title")) {
							title = value;
							request.setAttribute("title", title);
							
//							idStr = value;
//							idStr = idStr.trim();
//							System.out.println(idStr);
//							id = Integer.parseInt(idStr);
//							request.setAttribute("id", id);
//						} else if (fldName.equals("title")) {
//							title = value;
//							request.setAttribute("title", title);
						} 	
						else if (fldName.equals("activitysId")) {
							activitysIdStr = value;
							activitysIdStr = activitysIdStr.trim();
							System.out.println(activitysIdStr);
							activitysId = Integer.parseInt(activitysIdStr);
							request.setAttribute("activitysId", activitysId);
						} else if (fldName.equals("articleType")) {
							articleTypeStr = value;
							articleTypeStr = articleTypeStr.trim();
							articleType = Integer.parseInt(articleTypeStr);
							request.setAttribute("articleType", articleType);
						} else if (fldName.equals("showArticle")) {
							showArticleStr = value;
							showArticleStr = showArticleStr.trim();
							showArticle = Integer.parseInt(showArticleStr);
							request.setAttribute("showArticle", showArticle);
						} else if (fldName.equals("memberId")) {
							memberIdStr = value;
							memberIdStr = memberIdStr.trim();
							memberId = Integer.parseInt(memberIdStr);
							request.setAttribute("memberId", memberId);
						}
					} else {
						System.out.println("錯誤，缺少必要的資料");
					}

				}
			} else {
				System.out.println("錯誤，缺少必要的資料");
			}

//			ArticleDao articleDao = new WriteArticleImpl_Jdbc();
//
//			ArticleBean ab = new ArticleBean(id, title, activitysId, articleType, showArticle, memberId);
//
//			articleDao.saveArticle(ab);

			ArticleDAO aDAO = new ArticleDAO(session);

			Article article = new Article(title, 1, articleType, 1, 1);

			aDAO.insert(article);
			
			RequestDispatcher rd = request.getRequestDispatcher("/DemoHibernateServletAction1");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
