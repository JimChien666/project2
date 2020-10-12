package article.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.dao.impl.WriteArticleImpl_Jdbc;

/**
 * Servlet implementation class ArticleDelete
 */
@WebServlet("/ArticleDelete")
public class ArticleDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ArticleDelete() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		Enumeration<String> attributeNames = request.getAttributeNames();
//		while(attributeNames.hasMoreElements()) {
//			String nameString=attributeNames.nextElement();
//			System.out.println(nameString);
//		}

		String aId = request.getParameter("articleId").trim();
		
		int articleId = Integer.parseInt(aId);
		WriteArticleImpl_Jdbc dao = new WriteArticleImpl_Jdbc();
		
		dao.deleteArticle(articleId);

		request.getRequestDispatcher("article/showAllArticles.jsp").forward(request, response);
				
		doGet(request, response);
	}

}
