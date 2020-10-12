package wey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
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
				System.out.println("MyAdoptions getDatasource出問題");
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	
	//可參考/eDMoee/src/main/java/_03_listBooks/dao/impl/BookDaoImpl_Jdbc.java line61
	public List<ValueObjectAnimal> listAnimals(){
		List<ValueObjectAnimal> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from ANIMALS order by ANIMAL_ID");
				){
			while(rs.next()) {
				ValueObjectAnimal animals = new ValueObjectAnimal();
				animals.setAnimalId(rs.getInt("ANIMAL_ID"));
				animals.setMemberId(rs.getInt("MEMBER_ID"));
				animals.setAcceptionId(rs.getString("ACCEPTION_ID"));
				animals.setGender(rs.getInt("GENDER"));
				animals.setBreedId(rs.getInt("BREED_ID"));
				animals.setCoatColor(rs.getString("COAT_COLOR"));
				animals.setIsAdoptionAvailable(rs.getInt("IS_ADOPTION_AVAILABLE"));
				animals.setNote(rs.getString("NOTE"));
				list.add(animals);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("listAdoptionRecords出問題");
			e.printStackTrace();
		}
		return list;
	}
	
	//create改回傳AnimalId
	public boolean createAnimal(ValueObjectAnimal valueObjectAnimal) {
		String sql = "insert into ANIMALS(member_id,acception_id,breed_id,gender,coat_color,is_adoption_available,note) values(?,?,?,?,?,?,?)";
		try (
				Connection conn = getDataSource().getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql);
				){
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
			int updatecount = pStmt.executeUpdate();
			if(updatecount>=1)return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DaoAnimal.java/createAnimal error");
			return false;
		}
	}

	//查詢一筆資料修改刪除
	public ValueObjectAnimal getAnimal(int animalId) {
		ValueObjectAnimal valueObjectAnimal = null;
		String sql = "select * from ANIMALS where ANIMAL_ID = ?";
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
		String sql = "delete from ANIMALS where Animal_Id=?";
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, animalId);
			pStmt.executeUpdate();
			pStmt.clearParameters();
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
