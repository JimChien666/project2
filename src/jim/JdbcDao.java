package jim;

import java.sql.Blob;
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
import jim.entity.Products;

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
//			pstmt.setString(3, members.getTel());
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

	public int insertProducts(ValueObjectProduct valueObjectProduct) {
		String sql1 = "INSERT INTO products (name,price,img,descript,quantity,special_price,rewardpoints,is_thumb,member_id,animal_type_id,category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String sql2 = "select max(id) from products";
		int id=0;
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql1);
				Statement stmt = conn.createStatement();
				pstmt.setString(1, valueObjectProduct.getName());
				pstmt.setInt(2, valueObjectProduct.getPrice());
//			      pstmt.setString(3, products.getImg());
				pstmt.setBlob(3, valueObjectProduct.getImg().getBinaryStream());
				pstmt.setString(4, valueObjectProduct.getDescript());
				pstmt.setInt(5, valueObjectProduct.getQuantity());
				pstmt.setInt(6, valueObjectProduct.getSpecialPrice());
				pstmt.setString(7, valueObjectProduct.getRewardpoints());
				pstmt.setBoolean(8, valueObjectProduct.getIsThumb());
				pstmt.setInt(9, valueObjectProduct.getMemberId());
				pstmt.setInt(10, valueObjectProduct.getAnimalTypeId());
				pstmt.setInt(11, valueObjectProduct.getCategoryId());
//			      pstmt.setInt(12, products.getId());
				pstmt.executeUpdate();
				conn.commit();
				ResultSet rs = stmt.executeQuery(sql2);
				while (rs.next()) {
				id = rs.getInt("max(id)");
				}
				return id;
//				pstmt.clearParameters();
				} catch (Exception e) {
					System.out.println("JdbcDao.java/insertProducts error");
					conn.rollback();
					e.printStackTrace();
//					return false;
					return -1;
			}
		}catch (Exception e) {
			System.out.println("JdbcDao.java/insertProducts error");
			e.printStackTrace();		
			return -1;
		}	
	}
	
	public boolean updateProduct(Products product) {
		try (Connection conn = getDataSource().getConnection();								
				PreparedStatement pstmt = conn.prepareStatement("update products set name=?,price=?,img=?,descript=?,quantity=?,special_price=? where id=?");
			) {

			pstmt.setString(1, product.getName());
			pstmt.setInt(2, product.getPrice());
		    pstmt.setBlob(3, product.getImg().getBinaryStream());						
			pstmt.setString(4, product.getDescript());
		    pstmt.setInt(5, product.getQuantity());
		    pstmt.setInt(6, product.getSpecialPrice());
		    pstmt.setInt(7, product.getId());
		    
//		    pstmt.setString(7, product.getRewardpoints());		      
//		    pstmt.setBoolean(8, product.getIsThumb());
//		    pstmt.setInt(9, product.getMemberId());
//		    pstmt.setInt(10, product.getAnimalTypeId());
//		    pstmt.setInt(11, product.getCategoryId());			
		    
			pstmt.executeUpdate();
			pstmt.clearParameters();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
//	讀BLOB用
	// 由參數 id 到Product表格中 取得某個產品所有資料，傳回值為一個ProductBean物件，
	// 如果找不到對應的產品資料，傳回值為null。
	public Products queryProduct(String id) {
		Products pb=null;
		String sql="select * from products where id=?";
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {

			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					pb = new Products();
					pb.setId(rs.getInt("id"));
					pb.setName(rs.getString("name"));
					pb.setPrice(rs.getInt("price"));
//					pb.setImg(rs.getString("img"));
					pb.setImg(rs.getBlob("img"));
					pb.setDescript(rs.getString("descript"));
					pb.setQuantity(rs.getInt("quantity"));
					pb.setSpecialPrice(rs.getInt("special_price"));
					pb.setRewardpoints(rs.getString("rewardpoints"));
					pb.setIsThumb(rs.getBoolean("is_thumb"));
					pb.setMemberId(rs.getInt("member_id"));
					pb.setAnimalTypeId(rs.getInt("animal_type_id"));
					pb.setCategoryId(rs.getInt("Category_id"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("JdbaDao.queryProduct()發生例外: " 
					+ e.getMessage());
		}
		return pb;
	}
	
//		public List<ProductsBean> searchProducts() {
//			List<ProductsBean> list = new ArrayList<>();
//			try (Connection conn = getDataSource().getConnection();
//					PreparedStatement pstmt = conn.prepareStatement("select * from products where name like ? order by id");
//
//					) {
//				ResultSet rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					ProductsBean product = new ProductsBean();
//					product.setId(rs.getInt("id"));
//					product.setName(rs.getString("name"));
//					product.setPrice(rs.getInt("price"));
////					product.setImg(rs.getString("img"));
//					product.setImg(rs.getBlob("img"));
//					product.setDescript(rs.getString("descript"));
//					product.setQuantity(rs.getInt("quantity"));
//					product.setSpecialPrice(rs.getInt("special_price"));
//					product.setRewardpoints(rs.getString("rewardpoints"));
//					product.setIsThumb(rs.getBoolean("is_thumb"));
//					product.setMemberId(rs.getInt("member_id"));
//					product.setAnimalTypeId(rs.getInt("animal_type_id"));
//					product.setCategoryId(rs.getInt("Category_id"));
//					
//					list.add(product);
//				}
//				return list;
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			return list;
//		}
		
	public int searchProduct(Products product) {
		int n=0;
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from products where name like ? order by id");) {

			pstmt.setString(1, "%"+product.getName()+"%");
			n=pstmt.executeUpdate();
			pstmt.clearParameters();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public int deleteProduct(Products product) {
		int n=0;
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from products where id=?");
				) {

			pstmt.setInt(1, product.getId());

			n=pstmt.executeUpdate();
			pstmt.clearParameters();
			pstmt.close();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	//產品List
	public List<Products> listProducts() {
		List<Products> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from products order by id");) {

			while (rs.next()) {
				Products product = new Products();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
//				product.setImg(rs.getString("img"));
				product.setImg(rs.getBlob("img"));
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
	//查詢一筆資料修改
	public ValueObjectProduct getProduct(int productId) {
		ValueObjectProduct valueObjectProduct = null; 
		String sql = "select a.id,a.name,a.price,a.descript,a.quantity,a.special_price,a.rewardpoints,a.is_thumb,a.member_id,a.animal_type_id,a.category_id, f.file_blob from products a left join files f on f.product_id=a.id where a.id= ?";
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setInt(1, productId);
			try(ResultSet rs=pstmt.executeQuery();){
			while (rs.next()) {
				valueObjectProduct = new ValueObjectProduct();
				valueObjectProduct.setId(rs.getInt("id"));
				valueObjectProduct.setName(rs.getString(2));
				valueObjectProduct.setPrice(rs.getInt(3));
//				valueObjectProduct.setImg(rs.getString("img"));
//				valueObjectProduct.setImg(rs.getBlob(4));
				valueObjectProduct.setDescript(rs.getString(4));
				valueObjectProduct.setQuantity(rs.getInt(5));
				valueObjectProduct.setSpecialPrice(rs.getInt(6));
				valueObjectProduct.setRewardpoints(rs.getString(7));
				valueObjectProduct.setIsThumb(rs.getBoolean(8));
				valueObjectProduct.setMemberId(rs.getInt(9));
				valueObjectProduct.setAnimalTypeId(rs.getInt(10));
				valueObjectProduct.setCategoryId(rs.getInt(11));
				valueObjectProduct.setFileBlob(rs.getBlob(12));
				
				}
			}
		} catch (SQLException e) {
			System.out.println("JdbcDao.getProduct error");
			e.printStackTrace();
		}
		return valueObjectProduct;
	}
	
}
