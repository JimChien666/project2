package article.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.dao.impl.ArticleShowImpl_Jdbc;
import article.model.AllArticleBean;
import article.model.ArticleTypeBean;

/**
 * Servlet implementation class NewArtilceShow
 */
@WebServlet("/NewArtilceShow")
public class NewArtilceShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("456");
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    ArticleShowImpl_Jdbc articleDao = new ArticleShowImpl_Jdbc();
	    
	    
		String articletypesIdStr = request.getParameter("articletypesId");
		System.out.println(articletypesIdStr);
		if (articletypesIdStr == null) {
			int articletypesId = 1;
			System.out.println(articletypesId);
			Collection<AllArticleBean> ArticleList = articleDao.getAllArticles(articletypesId);
			request.setAttribute("ArticleList", ArticleList);
		}
		else if(articletypesIdStr != null) {
			int articletypesId = Integer.parseInt(articletypesIdStr);
			System.out.println(articletypesId);
			Collection<AllArticleBean> ArticleList = articleDao.getAllArticles(articletypesId);
			request.setAttribute("ArticleList", ArticleList);
		}
		
		
		List<ArticleTypeBean> allArticleTypes = articleDao.getAllArticleTypes();
				
		request.setAttribute("allArticleTypes", allArticleTypes);
		request.getRequestDispatcher("article/showAllArticles.jsp").forward(request, response);
	}


}
