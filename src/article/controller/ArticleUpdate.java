package article.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import article.dao.ArticleDao;
import article.dao.impl.WriteArticleImpl_Jdbc;
import article.model.ArticleBean;

/**
 * Servlet implementation class ArticleUpdate
 */
@MultipartConfig
@WebServlet("/ArticleUpdate")
public class ArticleUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ArticleUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String aId = request.getParameter("id").trim();
		
		try {
			String title = "";		
			String idStr = "";
			int id = 0;
			int articleId = Integer.parseInt(aId);
			Collection<Part> parts = request.getParts();
			
			if (parts != null) { // 如果這是一個上傳資料的表單
				for (Part p : parts) {
					String fldName = p.getName();
					String value = request.getParameter(fldName);
					if (p.getContentType() == null) {   // 表示 p 為一般欄位而非上傳的表單
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
			ArticleDao articleDao = new WriteArticleImpl_Jdbc();
			
			ArticleBean ab = new ArticleBean(id, title, 1, 1 ,1 , 1);		
			
			articleDao.updateArticle(ab);
			request.getRequestDispatcher("article/showAllArticles.jsp").forward(request, response);

			
		} catch (Exception e) {
			e.printStackTrace(); 
			RequestDispatcher rd = request.getRequestDispatcher("/article/PostArticle");
			rd.forward(request, response);
		}				
//		WriteArticleImpl_Jdbc dao = new WriteArticleImpl_Jdbc();
	
//		dao.updateArticle(articleId);
//		dao.updateArticle(articleId);
//		request.getRequestDispatcher("article/showAllArticles.jsp").forward(request, response);		
	}

}
