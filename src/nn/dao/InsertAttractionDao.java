package nn.dao;

import java.io.InputStream;
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

import nn.vo.AttractionBean;
import nn.vo.AttractionTagRefsBean;
import nn.vo.AttractionTypeBean;
import nn.vo.CityBean;
import nn.vo.FileBean;
import nn.vo.TagBean;

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
			InputStream blobStream = null;
			try (Connection conn = getDataSource().getConnection();

					Statement stmt = conn.createStatement();
					PreparedStatement pstmt = conn.prepareStatement(
							"INSERT INTO files (file_type, file_url, cover_attraction_id, file_blob) VALUES (?,?,?,?)");) {
				pstmt.setString(1, "image");
				pstmt.setString(2, file.getFileUrl());
				pstmt.setInt(3, file.getCoverAttractionId());
				if(file.getFileBlob() != null) blobStream = file.getFileBlob().getBinaryStream();
				pstmt.setBlob(4, blobStream);
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
			InputStream blobStream = null;
			try (Connection conn = getDataSource().getConnection();

					Statement stmt = conn.createStatement();
					PreparedStatement pstmt = conn.prepareStatement(
							"INSERT INTO files (file_type, file_url, content_attraction_id, file_blob) VALUES (?,?,?,?)");) {
				pstmt.setString(1, "image");
				pstmt.setString(2, file.getFileUrl());
				pstmt.setInt(3, file.getContentAttractionId());
				if(file.getFileBlob() != null) blobStream = file.getFileBlob().getBinaryStream();
				pstmt.setBlob(4, blobStream);
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
		
		public List<TagBean> getTagList() {
			List<TagBean> list = new ArrayList<>();
			try (Connection conn = getDataSource().getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select id, name from tags order by id");) {

				while (rs.next()) {
					TagBean bean = new TagBean();
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
		
		public void insertAttractionTagRefs(AttractionTagRefsBean attractionTagRefs) {
			try (Connection conn = getDataSource().getConnection();

					Statement stmt = conn.createStatement();
					PreparedStatement pstmt = conn.prepareStatement(
							"INSERT INTO attraction_tag_refs (tag_id, attraction_id) VALUES (?,?)");) {
				pstmt.setInt(1, attractionTagRefs.getTagId());
				pstmt.setInt(2, attractionTagRefs.getAttractionId());

				pstmt.executeUpdate();
				pstmt.clearParameters();

				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

}
