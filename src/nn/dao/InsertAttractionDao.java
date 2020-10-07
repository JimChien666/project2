package nn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import nn.vo.AttractionBean;

public class InsertAttractionDao {
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
		
		public boolean InsertAttraction(AttractionBean attraction) {
			try (Connection conn = getDataSource().getConnection();

					Statement stmt = conn.createStatement();
					PreparedStatement pstmt = conn.prepareStatement(
							"INSERT INTO attractions (name, member_id, content, tel, email, address, created_at, city_id, attraction_type_id) VALUES (?,?,?,?,?,?,?,?,?)");) {
				pstmt.setString(1, attraction.getName());
				pstmt.setInt(2, attraction.getMemberId());
				pstmt.setString(3, attraction.getContent());
				pstmt.setString(4, attraction.getTel());
				pstmt.setString(5, attraction.getEmail());
				pstmt.setString(6, attraction.getAddress());
				java.sql.Date createdAt = new java.sql.Date(attraction.getCreatedAt().getTime());
				pstmt.setDate(7, createdAt);
				pstmt.setInt(8, attraction.getCityId());
				pstmt.setInt(9, attraction.getAttractionTypeId());
				pstmt.executeUpdate();
				pstmt.clearParameters();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

}
