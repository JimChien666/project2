package article.dao;

import java.sql.Connection;
import java.sql.Statement;

import article.model.ArticleBean;

public class ArticleBasicDAO {

	private Connection conn;
	
	public ArticleBasicDAO(Connection conn) {
		this.conn =conn;
	}
	
	public boolean insertArticle(ArticleBean articleData) {
		try {
			String sqlString = "insert into ARTICLE values('"
                    +articleData.getTitle()+"','"
                    + articleData.getActivitysId()+"','"
                    + articleData.getArticleType()+"','"
                    + articleData.getShowArticle()+"','" 
                    + articleData.getMemberId()+ "')";
			Statement stmt = conn.createStatement();
		      System.out.println(sqlString);
			    int updatecount = stmt.executeUpdate(sqlString);
		      stmt.close();
		      if (updatecount >= 1) return true;
		      else                  return false;
			  } catch (Exception e) {
			    System.err.println("新增文章時發生錯誤:" + e);
				  return false;					
		}	
		
		
	}	
	
}
