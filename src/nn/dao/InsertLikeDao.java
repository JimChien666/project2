package nn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import nn.vo.AttractionTagRefsBean;
import nn.vo.FavoriteBean;

public class InsertLikeDao {
	private DataSource dataSource;

	public DataSource getDataSource() {
		if (this.dataSource == null) {
			try {
				InitialContext ctxt = new InitialContext();
				DataSource dataSource = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

				return dataSource;
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	
	public int checkFavoriteStatus(int memberId, int attractionId) {
		try (Connection conn = getDataSource().getConnection();

				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"select is_favorite_available from favorites where member_id = ? and attraction_id = ?");) {
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, attractionId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				return rs.getInt(1);
			}
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void insertFavorite(FavoriteBean favorite) {
		try (Connection conn = getDataSource().getConnection();

				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO favorites (member_id, attraction_id, is_favorite_available) VALUES (?,?, 1)");) {
			pstmt.setInt(1, favorite.getMemberId());
			pstmt.setInt(2, favorite.getAttractionId());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
