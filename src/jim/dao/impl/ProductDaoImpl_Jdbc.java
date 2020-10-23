package jim.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.jasper.runtime.JspSourceImports;
import jim.GlobalService;
import jim.entity.Products;


// 本類別使用純JDBC的技術來存取資料庫。
// 所有SQLException都以catch區塊捕捉，然後一律再次丟出RuntimeException。
// 對SQLException而言，即使catch下來，程式依然無法正常執行，所以捕捉SQLException，再次丟出
// RuntimeException。
public class ProductDaoImpl_Jdbc implements Serializable  {

	private static final long serialVersionUID = 1L;
	private int bookId = 0; 	// 查詢單筆商品會用到此代號
	private int pageNo = 0;		// 存放目前顯示之頁面的編號
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE; // 預設值：每頁三筆
	private int totalPages = -1;
	DataSource ds = null;
	
//	private String tagName = "";
	String selected = "";

	public ProductDaoImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("BookDaoImpl_Jdbc()#建構子發生例外: " 
										+ ex.getMessage());
		}
	}
	
	// 計算販售的商品總共有幾頁
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));

		return totalPages;
	}
	
	// 查詢某一頁的商品(書籍)資料，執行本方法前，一定要先設定實例變數pageNo的初值
	public List<Products> getPageBooks() {
		List<Products> list = new ArrayList<Products>();

		String sql0 = "select * from( "
				+"select rownum as rn,id,name,price,img,descript,quantity,special_price,rewardpoints,is_thumb,member_id,animal_type_id,category_id " 
				+"from products "				
				+"ORDER BY id) "
				+"WHERE rn >= ? AND rn <= ?";

//		String sql0 = "SELECT  * FROM (SELECT  ROW_NUMBER() OVER (ORDER BY BOOKID)"
//				+ " AS RowNum, b.BookId, b.author, b.bookNo, b.category, b.TITLE, b.PRICE, "
//				+ " b.discount, b.companyID, b.fileName, b.coverImage, bc.name "
//				+ " FROM Book b JOIN BookCompany bc ON  b.companyID = bc.id )"
//				+ " AS NewTable WHERE RowNum >= ? AND RowNum <= ?";
		
//		String sql1 = "SELECT b.BookId, b.author, b.bookNo, b.category, b.TITLE, "
//				+ "b.PRICE, b.discount, b.companyID, b.fileName, b.coverImage, "
//				+ "bc.name FROM Book b JOIN BookCompany bc ON  b.companyID = bc.id "
//						+ " LIMIT ?, ?";
		String sql = sql0;
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (pageNo - 1) * recordsPerPage + 1;
		int endRecordNo = (pageNo) * recordsPerPage;
		// 由頁碼推算出該頁是由哪一筆紀錄開始(0 based)		
//		int startRecordNo = (pageNo - 1) * recordsPerPage;
//		int endRecordNo = recordsPerPage;
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setBigDecimal(1, new BigDecimal(startRecordNo));
			ps.setBigDecimal(2, new BigDecimal(endRecordNo));
			try (
				ResultSet rs = ps.executeQuery();
			) {
				// 只要還有紀錄未取出，rs.next()會傳回true
				// 迴圈內將逐筆取出ResultSet內的紀錄
				while (rs.next()) {
					Products product = new Products();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getInt("price"));
//					product.setImg(rs.getString("img"));
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
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("BookDaoImpl_Jdbc()#getPageBooks()發生例外: " 
										+ ex.getMessage());
		}
		return list;
	}

	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String sql = "SELECT count(1) FROM PRODUCTS";
		try (
			Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#getRecordCounts()發生例外: " 
										+ ex.getMessage());
		}
		return count;
	}
	
	public List<String> getCategory() {
		String sql = "SELECT DISTINCT Category_id FROM PRODUCTS";
		List<String> list = new ArrayList<>();
		try (
			Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			while (rs.next()) {
				String cate = rs.getString(1);
				if (cate != null) {
					list.add(cate);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#getCategory()發生例外: " 
										+ ex.getMessage());
		}
		return list;
	}
	
//	public String getCategoryTag() {
//		String ans = "";
//		List<String> list = getCategory();
//		ans += "<SELECT name='category'>";
//		for (String cate : list) {
//			if (cate.equals(selected)) {
//				ans += "<option value='" + cate + "' selected>" + cate + "</option>";
//			} else {
//				ans += "<option value='" + cate + "'>" + cate + "</option>";
//			}
//		}
//		ans += "</SELECT>";
//		return ans;
//	}
	
	// 修改一筆書籍資料
//	public int updateBook(BookBean bean, long sizeInBytes) {
//		int n = 0;
//		String sql = "UPDATE Book SET " 
//				+ " title=?,  author=?,  price=?, discount = ?, coverImage = ?, "
//				+ " fileName=?, bookNo=?, stock=?, companyID=? , category = ? WHERE bookID = ?";
//		if (sizeInBytes == -1) { // 不修改圖片
//			n = updateBook(bean);
//			return n;
//		}
//		InputStream blobStream = null;
//		try (
//			Connection connection = ds.getConnection(); 
//			PreparedStatement ps = connection.prepareStatement(sql);
//		) {
//			ps.setString(1, bean.getTitle());
//			ps.setString(2, bean.getAuthor());
//			ps.setDouble(3, bean.getPrice());
//			ps.setDouble(4, bean.getDiscount());
//
//			if(bean.getCoverImage() != null) blobStream = bean.getCoverImage().getBinaryStream();
//			ps.setBlob(5, blobStream);
//			ps.setString(6, bean.getFileName());
//			ps.setString(7, bean.getBookNo());
//			ps.setInt(8, bean.getStock());
//			ps.setInt(9, bean.getCompanyId());
//			ps.setString(10, bean.getCategory());
//			ps.setInt(11, bean.getBookId());
//			n = ps.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc()#updateBook(BookBean, long)發生例外: " 
//										+ ex.getMessage());
//		}finally {
//			if(blobStream!=null) {
//				try {
//					blobStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return n;
//	}

	// 修改一筆書籍資料，不改圖片
//	public int updateBook(BookBean bean) {
//		int n = 0;
//		String sql = "UPDATE Book SET " 
//				+ " title=?,  author=?,  price=?, discount=?,  bookNo=?, "
//				+ " stock=?,  companyID=?,  category=?  WHERE bookId = ?";
//		try (
//			Connection connection = ds.getConnection(); 
//			PreparedStatement ps = connection.prepareStatement(sql);
//		) {
//			ps.clearParameters();
//			ps.setString(1, bean.getTitle());
//			ps.setString(2, bean.getAuthor());
//			ps.setDouble(3, bean.getPrice());
//			ps.setDouble(4, bean.getDiscount());
//			ps.setString(5, bean.getBookNo());
//			ps.setInt(6, bean.getStock());
//			ps.setInt(7, bean.getCompanyId());
//			ps.setString(8, bean.getCategory());
//			ps.setInt(9, bean.getBookId());
//
//			n = ps.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc()#updateBook(BookBean)發生例外: " 
//										+ ex.getMessage());
//		}
//		return n;
//	}

	// 依bookID來刪除單筆記錄
//	public int deleteBook(int no) {
//		int n = 0;
//		String sql = "DELETE FROM Book WHERE bookId = ?";
//
//		try (
//			Connection connection = ds.getConnection(); 
//			PreparedStatement pStmt = connection.prepareStatement(sql);
//		) {
//			pStmt.setInt(1, no);
//			n = pStmt.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc()#deleteBook()發生例外: " 
//										+ ex.getMessage());
//		}
//		return n;
//	}

	// 新增一筆記錄---
//	public int saveBook(BookBean bean) {
//		int n = 0;
//
//		String sql = "INSERT INTO Book " 
//				+ " (title,  author,  price, discount, "
//				+ " companyId, fileName, bookNo, coverImage, stock, category) " 
//				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//		InputStream blobStream = null;
//		try (
//			Connection connection = ds.getConnection();
//			PreparedStatement pStmt = connection.prepareStatement(sql);
//		) {
//			pStmt.setString(1, bean.getTitle());
//			pStmt.setString(2, bean.getAuthor());
//			pStmt.setDouble(3, bean.getPrice());
//			pStmt.setDouble(4, bean.getDiscount());
//			pStmt.setInt(5, bean.getCompanyId());
//			pStmt.setString(6, bean.getFileName());
//			pStmt.setString(7, bean.getBookNo());
//
//			if(bean.getCoverImage() != null) blobStream = bean.getCoverImage().getBinaryStream();
//			pStmt.setBlob(8, blobStream);
//
//			pStmt.setInt(9, bean.getStock());
//			pStmt.setString(10, bean.getCategory());
//			n = pStmt.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc()#saveBook()發生例外: " 
//										+ ex.getMessage());
//		}finally {
//			if(blobStream != null) {
//				try {
//					blobStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return n;
//	}

	
	

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

//	public BookBean getBook(int bookId) {
//		BookBean bean = null;
//		String sql = "SELECT * FROM Book WHERE bookId = ?";
//
//		try (
//			Connection connection = ds.getConnection(); 
//			PreparedStatement ps = connection.prepareStatement(sql);
//		) {
//			ps.setInt(1, bookId);
//			try (ResultSet rs = ps.executeQuery();) {
//				if (rs.next()) {
//					bean = new BookBean();
//					bean.setBookId(rs.getInt("bookId"));
//					bean.setTitle(rs.getString(2));
//					bean.setAuthor(rs.getString(3));
//					bean.setPrice(rs.getDouble(4));
//					bean.setDiscount(rs.getDouble(5));
//					bean.setCoverImage(rs.getBlob(6));
//					bean.setFileName(rs.getString(7));
//					bean.setBookNo(rs.getString(8));
//					bean.setStock(rs.getInt(9));
//					bean.setCategory(rs.getString("category"));
//					bean.setCompanyId(rs.getInt(11));
//				}
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc()#queryBook()發生例外: " 
//										+ ex.getMessage());
//		}
//		return bean;
//	}

}