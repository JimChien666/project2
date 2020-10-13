package article.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

import article.dao.ArticleDao;
import article.model.AllArticleBean;
import article.model.ArticleBean;

@MultipartConfig

public class WriteArticleImpl_Jdbc implements Serializable, ArticleDao {
	private static final long serialVersionUID = 1L;
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/xe";
	DataSource ds = null;
	private int id = 0;
	
	
	public WriteArticleImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("BookDaoImpl_Jdbc()#建構子發生例外: " 
										+ ex.getMessage());
		}
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
	
	
	//單筆查詢---	TODO
	public ArticleBean getArticleById() {
		ArticleBean ab = null;

		String sql = "SELECT * FROM Article WHERE title LIKE '%?%'";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					ab = new ArticleBean(
							rs.getInt(1),							
							rs.getString(2),
							rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("CompanyDaoImpl_Jdbc()#getArticleById()發生例外: " 
					+ ex.getMessage());
		}
		return ab;
	}

	
	//刪文---	 TODO
	public int deleteArticle(int no) {
		int n = 0;
		String sql = "DELETE FROM Article WHERE ID = ?";
		
		try (
			Connection connection = ds.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);
			){
			pStmt.setInt(1, no);
			n = pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc()#deleteArticle()發生例外: " 
					+ ex.getMessage());
		} 
		return n;
	}
	// 新增一筆記錄---  TODO
	
		public int saveArticle(ArticleBean ab) {
			int n = 0;

//			String sql = "INSERT INTO Article " 
//					+ " (id, title, ACTIVITYS_ID, SHOW_ARTICLE, "
//					+ " MEMBER_ID, articletypes_id) " 
//					+ " VALUES (?, ?, ?, ?, ?, ?)";
			
			String sql = "INSERT INTO Article " 
					+ " (title, ACTIVITYS_ID, SHOW_ARTICLE, "
					+ " MEMBER_ID, articletypes_id) " 
					+ " VALUES (?, ?, ?, ?, ?)";

//			InputStream blobStream = null;
			try (
				Connection connection = ds.getConnection();
				PreparedStatement pStmt = connection.prepareStatement(sql);
			) {
//				pStmt.setInt(1, ab.getId());
//				pStmt.setString(2, ab.getTitle());
//				pStmt.setInt(3, ab.getActivitysId());
//				pStmt.setInt(4, ab.getShowArticle());
//				pStmt.setInt(5, ab.getMemberId());
//				pStmt.setInt(6, ab.getArticleType());			
				
				pStmt.setString(1, ab.getTitle());
//				pStmt.setInt(2, ab.getActivitysId());
				pStmt.setInt(2, 1);
//				pStmt.setInt(3, ab.getShowArticle());
				pStmt.setInt(3, 1);
				pStmt.setInt(4, ab.getMemberId());
				pStmt.setInt(5, ab.getArticleType());			
								
				n = pStmt.executeUpdate();
			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new RuntimeException("WriteArticleImpl_Jdbc()#addArticle()發生例外: " 
											+ ex.getMessage());
			}finally {
				
			}
			return n;
		}	
 
		
		// 修改一筆記錄---  TODO
		
			public int updateArticle(ArticleBean ab) {
				int n = 0;

//				String sql = "INSERT INTO Article " 
//						+ " (id, title, ACTIVITYS_ID, SHOW_ARTICLE, "
//						+ " MEMBER_ID, articletypes_id) " 
//						+ " VALUES (?, ?, ?, ?, ?, ?)";
				
				String sql = "UPDATE Article SET" 
						+ " title=?"
						+ " WHERE ID = ?";

				try (
					Connection connection = ds.getConnection();
					PreparedStatement pStmt = connection.prepareStatement(sql);
				) {
//					pStmt.setInt(1, ab.getId());
//					pStmt.setString(2, ab.getTitle());
//					pStmt.setInt(3, ab.getActivitysId());
//					pStmt.setInt(4, ab.getShowArticle());
//					pStmt.setInt(5, ab.getMemberId());
//					pStmt.setInt(6, ab.getArticleType());					
//					pStmt.setInt(2, ab.getActivitysId());					
					
					pStmt.setString(1, ab.getTitle());
					pStmt.setInt(2, ab.getId());				
					n = pStmt.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
					throw new RuntimeException("WriteArticleImpl_Jdbc()#addArticle()發生例外: " 
												+ ex.getMessage());
				}finally {
					
				}
				return n;
			}	
		
		
		
}
