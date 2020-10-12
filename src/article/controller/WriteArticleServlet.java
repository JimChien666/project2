package article.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import article.dao.ArticleBasicDAO;
import article.model.ArticleBean;

/**
 * Servlet implementation class WriteArticleServlet
 */
@WebServlet("/WriteArticleServlet")
public class WriteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

    public WriteArticleServlet() {
        super();
    }
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    
	    // To prevent caching 
	   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
	   response.setHeader("Pragma","no-cache"); // HTTP 1.0
	   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
	   
	   if (request.getParameter("submit")!=null)
		   gotoConfirmProcess(request, response);	//TODO	
//		     gotoSubmitProcess(request, response);
//		   else if (request.getParameter("confirm")!=null)
	}
	
//	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	  {
//		String id;
//		String title;
//		String activitysId;
//		String articleType;
//		String showArticle;
//		String article;
//		String memberId;
//		
//		
//		id = request.getParameter("id").trim();
//		title = request.getParameter("title").trim();
//		activitysId = request.getParameter("activitysId").trim();
//		articleType = request.getParameter("articleType").trim();
//		showArticle = request.getParameter("showArticle").trim();
//		article = request.getParameter("article").trim();
//		memberId = request.getParameter("memberId").trim();
//		
//		ArticleBean reg_article = new ArticleBean(id, title, activitysId, articleType, showArticle, memberId);
//	  }
	
	public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
	    
	    try {
		      ctxt = new InitialContext();
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/BookDataSQLver");
		      conn = ds.getConnection();
		      
				int id;
				String title;
				int activitysId;
				int articleType;
				int showArticle;
//				String article;
				int memberId;
				
				
				id = Integer.parseInt(request.getParameter("id").trim());  
				title = request.getParameter("title").trim();
				activitysId = Integer.parseInt(request.getParameter("activitysId").trim());
				articleType = Integer.parseInt(request.getParameter("articleType").trim());
				showArticle = Integer.parseInt(request.getParameter("showArticle").trim());
				memberId = Integer.parseInt(request.getParameter("memberId").trim());
		      
		      
		      ArticleBean reg_article = new ArticleBean(title, activitysId, articleType, showArticle, memberId);
			  request.getSession(true).setAttribute("reg_article",reg_article);		      
		      ArticleBasicDAO articleDAO = new ArticleBasicDAO(conn);
		      ArticleBean articleData = (ArticleBean)request.getSession(true).getAttribute("reg_article");
		      
		      if (articleDAO.insertArticle(articleData))
		        {
//		          System.out.println("Get some SQL commands done!");
		          request.getSession(true).invalidate();
		          request.getRequestDispatcher("/?????.jsp").forward(request,response);
		        }
		      
		      
		      
	    } catch (NamingException ne) {
		      System.out.println("Naming Service Lookup Exception");        
		} catch (Exception e) {
		    e.printStackTrace();  
			System.out.println("Database Connection Error"+ e); 
		      
		}finally {
		      try {
			        if (conn != null) conn.close();
			      } catch (Exception e) {
			        System.out.println("Connection Pool Error!");
			      }
			    }	    
	    
	  }
}
