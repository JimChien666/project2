package nn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.Animals;
import nn.vo.AttractionBean;
import nn.vo.AttractionIntroduction;
import nn.vo.AttractionTypeBean;
import nn.vo.CityBean;
import nn.vo.FileBean;

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
		
		
		public List<AttractionIntroduction> getAttractionIntroductionList(){
			List<AttractionIntroduction> list = new ArrayList<>();
			try (Connection conn = getDataSource().getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT atr.id, atr.name,atr.tel, atr.address, f1.file_url, f2.file_url,c.name FROM attractions atr left join files f1 on atr.id = f1.cover_attraction_id left join files f2 on atr.id = f2.content_attraction_id left join citys c on atr.city_id = c.id");) {
				while (rs.next()) {
					AttractionIntroduction attractionIntroduction = new AttractionIntroduction(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(4), rs.getString(3), rs.getString(5), rs.getNString(6));
					list.add(attractionIntroduction);
				}
				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		
		
		public int insertAttraction(AttractionBean attraction) {
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
				ResultSet rs = stmt.executeQuery("select id from attractions order by id desc");
				if (rs.next()) {
					int id = rs.getInt(1);
					return id;
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		
		public boolean insertCoverAttractionFile(FileBean file) {
			try (Connection conn = getDataSource().getConnection();

					Statement stmt = conn.createStatement();
					PreparedStatement pstmt = conn.prepareStatement(
							"INSERT INTO files (file_type, file_url, cover_attraction_id) VALUES (?,?,?)");) {
				pstmt.setString(1, "image");
				pstmt.setString(2, file.getFileUrl());
				pstmt.setInt(3, file.getCoverAttractionId());
				pstmt.executeUpdate();
				pstmt.clearParameters();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean insertContentAttractionFile(FileBean file) {
			try (Connection conn = getDataSource().getConnection();

					Statement stmt = conn.createStatement();
					PreparedStatement pstmt = conn.prepareStatement(
							"INSERT INTO files (file_type, file_url, content_attraction_id) VALUES (?,?,?)");) {
				pstmt.setString(1, "image");
				pstmt.setString(2, file.getFileUrl());
				pstmt.setInt(3, file.getContentAttractionId());
				pstmt.executeUpdate();
				pstmt.clearParameters();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
		public List<CityBean> getCityList() {
			List<CityBean> list = new ArrayList<>();
			try (Connection conn = getDataSource().getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select id, name from citys order by id");) {

				while (rs.next()) {
					CityBean bean = new CityBean();
					bean.setId(rs.getInt(1));
					bean.setName(rs.getString(2));
					list.add(bean);
				}
				return list;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public List<AttractionTypeBean> getAttractionTypeList() {
			List<AttractionTypeBean> list = new ArrayList<>();
			try (Connection conn = getDataSource().getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select id, name from attraction_types order by id");) {

				while (rs.next()) {
					AttractionTypeBean bean = new AttractionTypeBean();
					bean.setId(rs.getInt(1));
					bean.setName(rs.getString(2));
					list.add(bean);
				}
				return list;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		

}
