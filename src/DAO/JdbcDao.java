package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class JdbcDao {
	private DataSource dataSource;
	
	
	public DataSource getDataSource() {
		if (this.dataSource == null) {
			try {
				InitialContext ctxt = new InitialContext();
				DataSource dataSource = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/xe");
				
				return dataSource;
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	
	public int insertAnimal(Animals animal) {
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO animals (id,age,animal_type,breed,coat,sex,img_url,is_alive) VALUES (?,?,?,?,?,?,?,?)");){
		  	  int animalId = 1;
			    String getAnimalIdSql = "SELECT animal_id.nextval FROM DUAL";
		  	  
			    ResultSet rs = stmt.executeQuery(getAnimalIdSql);

		      if (rs.next()) animalId = rs.getInt(1);

		      rs.close();
		      
		      
		      pstmt.setInt(1, animalId);
		      pstmt.setString(2, animal.getAge());
		      pstmt.setString(3, animal.getAnimalType());
		      pstmt.setString(4, animal.getBreed());
		      pstmt.setString(5, animal.getCoat());
		      pstmt.setString(6, animal.getSex());
		      pstmt.setString(7, animal.getImgUrl());
		      pstmt.setBoolean(8, animal.isAlive());
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		      stmt.close();
		    return animalId;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean updateAnimal(Animals animal) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update animals set age=?,animal_type=?,breed=?,coat=?,sex,img_url=?,is_alive=? where id=?");) {
		  	  
		      
		      pstmt.setString(1, animal.getAge());
		      pstmt.setString(2, animal.getAnimalType());
		      pstmt.setString(3, animal.getBreed());
		      pstmt.setString(4, animal.getCoat());
		      pstmt.setString(5, animal.getSex());
		      pstmt.setString(6, animal.getImgUrl());
		      pstmt.setBoolean(7, animal.isAlive());
		      pstmt.setInt(8, animal.getId());
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Animals> listAnimals() {
		List<Animals> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from animals");){
		  	  
			while (rs.next()) {
				Animals animal = new Animals();
				animal.setId(rs.getInt("id"));
				animal.setAge(rs.getString("age"));
				animal.setAnimalType(rs.getString("animal_type"));
				animal.setBreed(rs.getString("breed"));
				animal.setCoat(rs.getString("coat"));
				animal.setSex(rs.getString("sex"));
				animal.setImgUrl(rs.getString("img_url"));
				animal.setAlive(rs.getBoolean("is_alive"));
				list.add(animal);
			}
			return list;
		      
		      
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertAdopedRecord(AdoptedRecords adoptedRecord) {
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO adopted_records (id,member_id,adopted_at,unadopted_at,animal_id,status) VALUES (?,?,?,?,?,?)");){
		  	  int adoptedRecordId = 1;
			    String getAdoptedRecordIdSql = "SELECT adopted_record_id.nextval FROM DUAL";
		  	  
			    ResultSet rs = stmt.executeQuery(getAdoptedRecordIdSql);

		      if (rs.next()) adoptedRecordId = rs.getInt(1);

		      rs.close();
		      
		      
		      pstmt.setInt(1, adoptedRecordId);
		      pstmt.setInt(2, adoptedRecord.getMemberId());
		      java.sql.Date adoptedAt = new java.sql.Date(adoptedRecord.getAdoptedAt().getTime());
		      pstmt.setDate(3, adoptedAt);
		      java.sql.Date nuadoptedAt = new java.sql.Date(adoptedRecord.getUnadoptedAt().getTime());
		      pstmt.setDate(4, nuadoptedAt);
		      
		      pstmt.setInt(5, adoptedRecord.getAnimalId());
		      pstmt.setString(6, adoptedRecord.getStatus());
		      
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		      stmt.close();
		    return adoptedRecordId;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean updateAdoptedRecord(AdoptedRecords adoptedRecord) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update adopted_records set member_id=?,adopted_at=?,unadopted_at=?,animal_id=?,status=? where id=?");) {
		  	  
		      
			  
		      pstmt.setInt(1, adoptedRecord.getMemberId());
		      java.sql.Date adoptedAt = new java.sql.Date(adoptedRecord.getAdoptedAt().getTime());
		      pstmt.setDate(2, adoptedAt);
		      java.sql.Date nuadoptedAt = new java.sql.Date(adoptedRecord.getUnadoptedAt().getTime());
		      pstmt.setDate(3, nuadoptedAt);
		      pstmt.setInt(4, adoptedRecord.getAnimalId());
		      pstmt.setString(5, adoptedRecord.getStatus());
		      pstmt.setInt(6, adoptedRecord.getId());    
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<AdoptedRecords> listAdoptedRecords() {
		List<AdoptedRecords> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from adopted_records");){
		  	  
			while (rs.next()) {
				AdoptedRecords adoptedRecord = new AdoptedRecords();
				adoptedRecord.setId(rs.getInt("id"));
				adoptedRecord.setMemberId(rs.getInt("member_id"));;
				adoptedRecord.setAdoptedAt(rs.getDate("adopted_at"));
				adoptedRecord.setUnadoptedAt(rs.getDate("unadopted_at"));
				adoptedRecord.setAnimalId(rs.getInt("animal_id"));
				adoptedRecord.setStatus(rs.getString("status"));
				
				list.add(adoptedRecord);
			}
			return list;
		      
		      
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
