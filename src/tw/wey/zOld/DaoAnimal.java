package tw.wey.zOld;

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

public class DaoAnimal {
	private DataSource dataSource;

	public DataSource getDataSource() {
		if (this.dataSource == null) {
			try {
				InitialContext ctxt = new InitialContext();
				DataSource dataSource = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
				return dataSource;
			} catch (NamingException e) {
				System.out.println("DaoAnimal/getDatasource出問題");
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	
	//可參考/eDMoee/src/main/java/_03_listBooks/dao/impl/BookDaoImpl_Jdbc.java line61
	public List<ValueObjectAnimal> listAnimals(){
		List<ValueObjectAnimal> list = new ArrayList<>();
		String sql = "select * from ANIMALS a, files f where a.animal_id = f.animal_id(+) order by a.ANIMAL_ID desc";
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				){
			while(rs.next()) {
				ValueObjectAnimal valueObjectAnimal = new ValueObjectAnimal();
				valueObjectAnimal.setAnimalId(rs.getInt("ANIMAL_ID"));
				valueObjectAnimal.setMemberId(rs.getInt("MEMBER_ID"));
				valueObjectAnimal.setAcceptionId(rs.getString("ACCEPTION_ID"));
				valueObjectAnimal.setGender(rs.getInt("GENDER"));
				valueObjectAnimal.setBreedId(rs.getInt("BREED_ID"));
				valueObjectAnimal.setCoatColor(rs.getString("COAT_COLOR"));
				valueObjectAnimal.setIsAdoptionAvailable(rs.getInt("IS_ADOPTION_AVAILABLE"));
				valueObjectAnimal.setNote(rs.getString("NOTE"));
				valueObjectAnimal.setFileType(rs.getString("FILE_TYPE"));
				valueObjectAnimal.setFileUrl(rs.getString("FILE_URL"));
				valueObjectAnimal.setFileBlob(rs.getBlob("FILE_BLOB"));
				list.add(valueObjectAnimal);
			}
		} catch (SQLException e) {
			System.out.println("listAnimals error");
			e.printStackTrace();
		}
		return list;
	}
	
	//create
	public int createAnimal(ValueObjectAnimal valueObjectAnimal) {
		String sql1 = "insert into ANIMALS(member_id,acception_id,breed_id,gender,coat_color,is_adoption_available,note) values(?,?,?,?,?,?,?)";
		String sql2 = "select max(animal_id) from animals";
		int animalId = 0;
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
			PreparedStatement pStmt = conn.prepareStatement(sql1);
			Statement stmt = conn.createStatement();
			pStmt.setInt(1, valueObjectAnimal.getMemberId());
			pStmt.setString(2, valueObjectAnimal.getAcceptionId());
			pStmt.setInt(3, valueObjectAnimal.getBreedId());
			pStmt.setInt(4, valueObjectAnimal.getGender());
			pStmt.setString(5, valueObjectAnimal.getCoatColor());
			pStmt.setInt(6, valueObjectAnimal.getIsAdoptionAvailable());
			pStmt.setString(7, valueObjectAnimal.getNote());
//			pStmt.setDate(9, null);
//			pStmt.setDate(10, null);
//			pStmt.setDate(11, null);
			pStmt.executeUpdate();
			conn.commit();
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
			animalId = rs.getInt("max(animal_id)");
			}
			return animalId;
			} catch (Exception e) {
				System.out.println("DaoAnimal.java/createAnimal error");
				conn.rollback();
				e.printStackTrace();
				return -1;
			}
		} catch (SQLException e) {
			System.out.println("DaoAnimal.java/createAnimal error");
			e.printStackTrace();
			return -1;
		}
	}

	//查詢一筆資料修改刪除
	public ValueObjectAnimal getAnimal(int animalId) {
		ValueObjectAnimal valueObjectAnimal = null;
		String sql = "select a.animal_id,a.member_id,a.acception_id,a.breed_id,a.gender,a.coat_color,a.is_adoption_available,a.note, f.file_blob from ANIMALS a left join Files f on f.animal_id = a.animal_id where a.ANIMAL_ID = ?";
		try (
				Connection conn = getDataSource().getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql);
				){
			pStmt.setInt(1, animalId);
			try(ResultSet rs = pStmt.executeQuery();){
				if(rs.next()) {					
			valueObjectAnimal = new ValueObjectAnimal();
			valueObjectAnimal.setAnimalId(rs.getInt("Animal_Id"));
			valueObjectAnimal.setMemberId(rs.getInt(2));
			valueObjectAnimal.setAcceptionId(rs.getString(3));
			valueObjectAnimal.setBreedId(rs.getInt(4));
			valueObjectAnimal.setGender(rs.getInt(5));
			valueObjectAnimal.setCoatColor(rs.getString(6));
			valueObjectAnimal.setIsAdoptionAvailable(rs.getInt(7));
			valueObjectAnimal.setNote(rs.getString(8));
			valueObjectAnimal.setFileBlob(rs.getBlob(9));
				}
			}
		} catch (SQLException e) {
			System.out.println("DaoAnimal.java/getAnimal error");
			e.printStackTrace();
		}
		return valueObjectAnimal;
	}
	
	//更新動物
	public boolean updateAnimal(ValueObjectAnimal valueObjectAnimal) {
		String sql = "update ANIMALS set MEMBER_ID=?, ACCEPTION_ID=?, BREED_ID=?, GENDER=?, COAT_COLOR=?, IS_ADOPTION_AVAILABLE=?, NOTE=? where Animal_Id=?";
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, valueObjectAnimal.getMemberId());
			pStmt.setString(2, valueObjectAnimal.getAcceptionId());
			pStmt.setInt(3, valueObjectAnimal.getBreedId());
			pStmt.setInt(4, valueObjectAnimal.getGender());
			pStmt.setString(5, valueObjectAnimal.getCoatColor());
			pStmt.setInt(6, valueObjectAnimal.getIsAdoptionAvailable());
			pStmt.setString(7, valueObjectAnimal.getNote());
			pStmt.setInt(8, valueObjectAnimal.getAnimalId());
			pStmt.executeUpdate();
			pStmt.clearParameters();
			conn.commit();
			return true;
			} catch (Exception e) {
				System.out.println("DaoAnimal.java/updateAnimal error");
				conn.rollback();
				e.printStackTrace();
				return false;
			}
		} catch (SQLException e) {
			System.out.println("DaoAnimal.java/updateAnimal error");
			e.printStackTrace();
			return false;
		}
	}
	
	//刪除動物
	public boolean deleteAnimal(int animalId) {
		String sql1 = "delete from ANIMALS where Animal_Id=?";
		String sql2 = "delete from FILES where Animal_Id=?";
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
			PreparedStatement pStmt1 = conn.prepareStatement(sql1);
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			pStmt1.setInt(1, animalId);
			pStmt2.setInt(1, animalId);
			pStmt1.executeUpdate();
			pStmt2.executeUpdate();
			pStmt1.clearParameters();
			pStmt2.clearParameters();
			conn.commit();
			return true;
			} catch (Exception e) {
				System.out.println("DaoAnimal.java/deleteAnimal error");
				conn.rollback();
				e.printStackTrace();
				return false;
			}
		} catch (SQLException e) {
			System.out.println("DaoAnimal.java/deleteAnimal error");
			e.printStackTrace();
			return false;
		}
	}
}
