package jim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import org.apache.naming.java.javaURLContextFactory;

import DAO.ActivityRecords;
import DAO.Activitys;
import DAO.AdoptedLevels;
import DAO.AdoptedRecords;
import DAO.Animals;
import DAO.Comments;
import DAO.Forums;
import DAO.Members;
import model.ProductsBean;

public class JdbcDao {
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

	

	// 查詢會員
	// ID查
	public boolean queryMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from members where id=?");) {
//				PreparedStatement pstmt = conn.prepareStatement("select * from members where id=?,name=?,income=?,tel=?,account=?,password=?,email=?,address=?,adopted_level_id=?,member_type=?");) {

			pstmt.setInt(1, members.getId());
//		      pstmt.setString(2, members.getName());
//		      pstmt.setInt(3, members.getIncome());
//		      pstmt.setString(4, members.getTel());
//		      pstmt.setString(5, members.getAccount());
//		      pstmt.setString(6, members.getPassword());
//		      pstmt.setString(7, members.getEmail());
//		      pstmt.setString(8, members.getAddress());		      
//		      pstmt.setInt(9, members.getAdoptedLevelId());
//		      pstmt.setString(10, members.getMemberType());

			ResultSet rs = pstmt.executeQuery();
			// TODO
			while (rs.next()) {
				System.out.print(rs.getInt("id"));
				System.out.print(rs.getString("name"));
				System.out.print(rs.getString("email"));
				System.out.print(rs.getString("password"));
				System.out.println(rs.getString("adopted_level_id"));

			}
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 修改會員
	// ID查
	public boolean updateMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"update members set name=?,income=?,tel=?,account=?,password=?,email=?,address=?,adopted_level_id=?,member_type=? where id=?");) {

			pstmt.setString(1, members.getName());
			pstmt.setInt(2, members.getIncome());
			pstmt.setString(3, members.getTel());
			pstmt.setString(4, members.getAccount());
			pstmt.setString(5, members.getPassword());
			pstmt.setString(6, members.getEmail());
			pstmt.setString(7, members.getAddress());
			pstmt.setInt(8, members.getAdoptedLevelId());
			pstmt.setString(9, members.getMemberType());
			pstmt.setInt(10, members.getId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 刪除會員
	// ID查
	public boolean deleteMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from members where id=?");) {
//					PreparedStatement pstmt = conn.prepareStatement("delete from members where id=?,name=?,income=?,tel=?,account=?,password=?,email=?,address=?,adopted_level_id=?,member_type=?");) {

			pstmt.setInt(1, members.getId());
//			      pstmt.setString(2, members.getName());
//			      pstmt.setInt(3, members.getIncome());
//			      pstmt.setString(4, members.getTel());
//			      pstmt.setString(5, members.getAccount());
//			      pstmt.setString(6, members.getPassword());
//			      pstmt.setString(7, members.getEmail());
//			      pstmt.setString(8, members.getAddress());		      
//			      pstmt.setInt(9, members.getAdoptedLevelId());
//			      pstmt.setString(10, members.getMemberType());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 會員ArrayList
	public List<Members> listMembers() {
		List<Members> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from members");) {

			while (rs.next()) {
				Members member = new Members();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setIncome(rs.getInt("income"));
				member.setTel(rs.getString("tel"));
				member.setAccount(rs.getString("account"));
				member.setPassword(rs.getString("password"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setAdoptedLevelId(rs.getInt("adopted_level_id"));
				member.setMemberType(rs.getString("member_type"));
				list.add(member);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	
	public boolean insertProducts(ProductsBean products) {
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products (name,price,img,descript,quantity,special_price,rewardpoints,is_thumb,member_id,animal_type_id,category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");){
	      
		      pstmt.setString(1, products.getName());
		      pstmt.setInt(2, products.getPrice());
		      pstmt.setString(3, products.getImg());
		      pstmt.setString(4, products.getDescript());
		      pstmt.setInt(5, products.getQuantity());
		      pstmt.setInt(6, products.getSpecialPrice());
		      pstmt.setString(7, products.getRewardpoints());		      
		      pstmt.setBoolean(8, products.getIsThumb());
		      pstmt.setInt(9, products.getMemberId());
		      pstmt.setInt(10, products.getAnimalTypeId());
		      pstmt.setInt(11, products.getCategoryId());
		      
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		      stmt.close();
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
//
//	public boolean updateProduct(Products product) {
//		try (Connection conn = getDataSource().getConnection();
//				PreparedStatement pstmt = conn.prepareStatement("update products set name=?,price=? where id=?");) {
//
//			pstmt.setString(1, product.getName());
//			pstmt.setInt(2, product.getPrice());
//			pstmt.setInt(3, product.getId());
//			pstmt.executeUpdate();
//			pstmt.clearParameters();
//
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public int searchProduct(Products product) {
//		try (Connection conn = getDataSource().getConnection();
//				PreparedStatement pstmt = conn.prepareStatement("select * from products where id=?");) {
//
//			pstmt.setString(1, product.getName());
//			pstmt.executeUpdate();
//			pstmt.clearParameters();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	public int deleteProduct(Products product) {
//		try (Connection conn = getDataSource().getConnection();
//				PreparedStatement pstmt = conn.prepareStatement("delete from products where id=?");) {
//
//			pstmt.setInt(1, product.getId());
//
//			pstmt.executeUpdate();
//			pstmt.clearParameters();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}

	//產品List
	public List<ProductsBean> listProducts() {
		List<ProductsBean> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from products");) {

			while (rs.next()) {
				ProductsBean product = new ProductsBean();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setImg(rs.getString("img"));
				product.setDescript(rs.getString("descript"));
				product.setQuantity(rs.getInt("quantity"));
				product.setSpecialPrice(rs.getInt("special_price"));
				product.setRewardpoints(rs.getString("rewardpoints"));
				product.setIsThumb(rs.getBoolean("is_thumb"));
				product.setMemberId(rs.getInt("member_id"));
				product.setAnimalTypeId(rs.getInt("animal_type_id"));
				product.setCategoryId(rs.getInt("Category_id"));

				list.add(product);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
