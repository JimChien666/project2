package jim;

import java.io.IOException;
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

public class DaoFilesOfProduct {
	private DataSource dataSource;
	public DataSource getDataSource() {
		if (this.dataSource == null) {
			try {
				InitialContext ctxt = new InitialContext();
				DataSource dataSource = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
				return dataSource;
			} catch (NamingException e) {
				System.out.println("DaoFilesOfProduct/getDatasource出問題");
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	
	public List<ValueObjectFilesOfProduct> listFilesOfProduct(){
		List<ValueObjectFilesOfProduct> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Files where product_id>0 order by product_id");
				){
			while (rs.next()) {
				ValueObjectFilesOfProduct valueObjectFilesOfProduct = new ValueObjectFilesOfProduct();
				valueObjectFilesOfProduct.setFileType(rs.getString("FILE_TYPE"));
				valueObjectFilesOfProduct.setFileUrl(rs.getString("FILE_URL"));
				valueObjectFilesOfProduct.setProductId(rs.getInt("PRODUCT_ID"));
				valueObjectFilesOfProduct.setFileBlob(rs.getBlob("FILE_BLOB"));
				list.add(valueObjectFilesOfProduct);
			}
		} catch (Exception e) {
			System.out.println("DaoFilesOfAnimal.java/listFilesOfAnimal error");
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean createFilesOfProduct(ValueObjectFilesOfProduct valueObjectFilesOfProduct) {
		String sql1 = "insert into FILES(FILE_TYPE,FILE_URL,PRODUCT_ID,FILE_BLOB) values(?,?,?,?)";
		InputStream blobStream = null;
		Boolean success = false;
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
				PreparedStatement pStmt = conn.prepareStatement(sql1);
				pStmt.setString(1, valueObjectFilesOfProduct.getFileType());
				pStmt.setString(2, valueObjectFilesOfProduct.getFileUrl());
				pStmt.setInt(3, valueObjectFilesOfProduct.getProductId());
				
				if(valueObjectFilesOfProduct.getFileBlob()!=null) {
					blobStream = valueObjectFilesOfProduct.getFileBlob().getBinaryStream();
				}
				pStmt.setBlob(4, blobStream);
				pStmt.executeUpdate();
				conn.commit();
				success = true;
				return success;
			} catch (Exception e) {
				System.out.println("DaoFilesOfProduct.java/createFilesOfProduct error");
				conn.rollback();
				e.printStackTrace();
				return success;
			}
		} catch (SQLException e) {
			System.out.println("DaoFilesOfProduct.java/createFilesOfProduct error");
			e.printStackTrace();
			return success;
		}finally {
			if(blobStream != null) {
				try {
					blobStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
