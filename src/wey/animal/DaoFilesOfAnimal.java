package wey.animal;

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

public class DaoFilesOfAnimal {
	private DataSource dataSource;
	public DataSource getDataSource() {
		if (this.dataSource == null) {
			try {
				InitialContext ctxt = new InitialContext();
				DataSource dataSource = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
				return dataSource;
			} catch (NamingException e) {
				System.out.println("DaoFilesOfAnimal/getDatasource出問題");
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	
	public List<ValueObjectFilesOfAnimal> listFilesOfAnimal(){
		List<ValueObjectFilesOfAnimal> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Files where ANIMAL_ID>0 order by ANIMAL_ID");
				){
			while (rs.next()) {
				ValueObjectFilesOfAnimal valueObjectFilesOfAnimal = new ValueObjectFilesOfAnimal();
				valueObjectFilesOfAnimal.setFileType(rs.getString("FILE_TYPE"));
				valueObjectFilesOfAnimal.setFileUrl(rs.getString("FILE_URL"));
				valueObjectFilesOfAnimal.setAnimalId(rs.getInt("ANIMAL_ID"));
				valueObjectFilesOfAnimal.setFileBlob(rs.getBlob("FILE_BLOB"));
				list.add(valueObjectFilesOfAnimal);
			}
		} catch (Exception e) {
			System.out.println("DaoFilesOfAnimal.java/listFilesOfAnimal error");
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean createFilesOfAnimal(ValueObjectFilesOfAnimal valueObjectFilesOfAnimal) {
		String sql1 = "insert into FILES(FILE_TYPE,FILE_URL,ANIMAL_ID,FILE_BLOB) values(?,?,?,?)";
		InputStream blobStream = null;
		Boolean success = false;
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
				PreparedStatement pStmt = conn.prepareStatement(sql1);
				pStmt.setString(1, valueObjectFilesOfAnimal.getFileType());
				pStmt.setString(2, valueObjectFilesOfAnimal.getFileUrl());
				pStmt.setInt(3, valueObjectFilesOfAnimal.getAnimalId());
				if(valueObjectFilesOfAnimal.getFileBlob()!=null) {
					blobStream = valueObjectFilesOfAnimal.getFileBlob().getBinaryStream();
				}
				pStmt.setBlob(4, blobStream);
				pStmt.executeUpdate();
				conn.commit();
				success = true;
				return success;
			} catch (Exception e) {
				System.out.println("DaoFilesOfAnimal.java/createFilesOfAnimal error");
				conn.rollback();
				e.printStackTrace();
				return success;
			}
		} catch (SQLException e) {
			System.out.println("DaoFilesOfAnimal.java/createFilesOfAnimal error");
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
