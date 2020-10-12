package article.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import article.model.AllArticleBean;
import article.model.ArticleTypeBean;

public class ArticleShowImpl_Jdbc {
	
	private static final long serialVersionUID = 1L;
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/xe";
	DataSource ds = null;
	private int id = 0;
	
	
	public ArticleShowImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("BookDaoImpl_Jdbc()#建構子發生例外: " 
										+ ex.getMessage());
		}
	}
	
	public List<ArticleTypeBean> getAllArticleTypes() {
		List<ArticleTypeBean> list=new ArrayList<ArticleTypeBean>();
		String sql0 = "select id, ArticleType from articleTypes";//where show_article = true
		String sql = sql0;
		try (
				Connection connection = ds.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql);
			){
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					ArticleTypeBean articleTypeBean = new ArticleTypeBean();
					articleTypeBean.setId(rs.getInt(1));
					articleTypeBean.setArticleType(rs.getString(2));
					list.add(articleTypeBean);
				}
				ps.clearParameters();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("WriteArticleImpl_Jdbc()#getAllArticles()發生例外: " 
											+ ex.getMessage());
			}		
			return list;
		}	
		
	
	public List<AllArticleBean> getAllArticles(int articletypesId) {
		List<AllArticleBean> list=new ArrayList<AllArticleBean>();
//		" SELECT A.ID, A.TITLE, A.MEMBER_ID, "
//		+" F.CREATED_AT "
//		+" FROM ARTICLE A, FORUMS F "
//		+" WHERE A.ID = F.ARTICLE_ID(+) "
//		+" WHERE ACTIVITYS_ID = ? "
//		+" ORDER BY A.ID "
		String sql0 = "select title, member_id from article where articletypes_id = ? order by id desc";//where show_article = true
		String sql = sql0;
		
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		){
			ps.setInt(1, articletypesId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				AllArticleBean allArticleBean = new AllArticleBean();
				allArticleBean.setTitle(rs.getString(1));
				allArticleBean.setMemberId(rs.getInt(2));
				list.add(allArticleBean);				
			}
			ps.clearParameters();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("WriteArticleImpl_Jdbc()#getAllArticles()發生例外: " 
										+ ex.getMessage());
		}		
		return list;
	}	
}
