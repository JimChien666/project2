package nn.dao;

import java.sql.Blob;
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

import nn.vo.AttractionIntroduction;
import nn.vo.AttractionTypeBean;
import nn.vo.CityBean;
import nn.vo.FileBean;

public class ShowAttractionInfosDao {
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
	
	public int getAttractionTypeNum(String Search,int atrId) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("select count(*) from attractions where attraction_type_id = ? and name like ? and deleted_at is null");) {
			pstmt.setInt(1, atrId);
			pstmt.setString(2, "%" + Search + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getAttractionTypeNum(String Search, int atrId, int cityId) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("select count(*) from attractions where attraction_type_id = ? and city_id = ? and name like ? and deleted_at is null");) {
			pstmt.setInt(1, atrId);
			pstmt.setInt(2, cityId);
			pstmt.setString(3, "%" + Search + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<AttractionIntroduction> getAttractionIntroductionList(String searchStr, int atrId, int initNum, int showNum){
		List<AttractionIntroduction> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"SELECT atr.id, atr.name,atr.tel, atr.address, f1.file_id, f2.file_id,c.name FROM attractions atr left join attraction_types at on at.id = atr.attraction_type_id left join files f1 on atr.id = f1.cover_attraction_id left join files f2 on atr.id = f2.content_attraction_id left join citys c on atr.city_id = c.id where at.id = ? and atr.name like ? order by id desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");) {
			pstmt.setInt(1, atrId);
			pstmt.setString(2, "%" + searchStr + "%");
			pstmt.setInt(3, initNum);
			
			pstmt.setInt(4, showNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AttractionIntroduction attractionIntroduction = new AttractionIntroduction(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(4), rs.getString(3), rs.getInt(5), rs.getInt(6));
				list.add(attractionIntroduction);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<AttractionIntroduction> getAttractionIntroductionList(String searchStr,int atrId, int initNum, int showNum, int cityId){
		List<AttractionIntroduction> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"SELECT atr.id, atr.name,atr.tel, atr.address, f1.file_id, f2.file_id,c.name f.is_favorite_available FROM attractions atr left join attraction_types at on at.id = atr.attraction_type_id left join files f1 on atr.id = f1.cover_attraction_id left join files f2 on atr.id = f2.content_attraction_id left join citys c on atr.city_id = c.id where at.id = ? and city_id=? and atr.name like ? order by id desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");) {
			pstmt.setInt(1, atrId);
			pstmt.setInt(2, cityId);
			pstmt.setString(3, "%" + searchStr + "%");
			pstmt.setInt(4, initNum);
			pstmt.setInt(5, showNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AttractionIntroduction attractionIntroduction = new AttractionIntroduction(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(4), rs.getString(3), rs.getInt(5), rs.getInt(6));
				list.add(attractionIntroduction);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Blob getFileBlob(int id) {
		Blob blob = null;
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"select file_blob from files where file_id = ?");) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				blob = rs.getBlob(1);
				return blob;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}
	
}
