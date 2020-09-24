package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
		  	  int deptno = 1;
			    String getAnimalIdSql = "SELECT animal_id.nextval FROM DUAL";
		  	  
		  	  //��������������隞����
			    ResultSet rs = stmt.executeQuery(getAnimalIdSql);

		      if (rs.next()) deptno = rs.getInt(1);

		      rs.close();
		      
		      
		      pstmt.setInt(1, animal.getId());
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
		    return deptno;
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
	//新增會員
	public int insertMembers(Members members) {
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO members (id,name,income,tel,account,password,email,address,adopted_level_id,member_type) VALUES (?,?,?,?,?,?,?,?,?,?)");){
		  	  int membersno = 1;
			    String getMembersIdSql = "SELECT member_id.nextval FROM DUAL";
		  	  
		  	  //��������������隞����
			    ResultSet rs = stmt.executeQuery(getMembersIdSql);

		      if (rs.next()) membersno = rs.getInt(1);

		      rs.close();		      
		      
		      pstmt.setInt(1, members.getId());
		      pstmt.setString(2, members.getName());
		      pstmt.setInt(3, members.getIncome());
		      pstmt.setString(4, members.getTel());
		      pstmt.setString(5, members.getAccount());
		      pstmt.setString(6, members.getPassword());
		      pstmt.setString(7, members.getEmail());
		      pstmt.setString(8, members.getAddress());		      
		      pstmt.setInt(9, members.getAdoptedLevelId());
		      pstmt.setString(10, members.getMemberType());
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		      stmt.close();
		    return membersno;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}	
	//查詢會員
		//ID查
	public boolean queryMembers(Members members) {
		try(
				Connection conn = getDataSource().getConnection();
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
		      while(rs.next()){            
		          System.out.print(rs.getInt("id"));
		          System.out.print(rs.getString("name"));
		          System.out.print(rs.getString("email"));
		          System.out.print(rs.getString("password"));
		          System.out.println(rs.getString("adopted_level_id"));
        
		      }
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//修改會員
		//ID查
	public boolean updateMembers(Members members) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update members set name=?,income=?,tel=?,account=?,password=?,email=?,address=?,adopted_level_id=?,member_type=? where id=?");) {
		  	  
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//刪除會員
			//ID查
		public boolean deleteMembers(Members members) {
			try(
					Connection conn = getDataSource().getConnection();
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
			}catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	//會員ArrayList
	public List<Members> listMembers() {
		List<Members> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from members");){
		  	  
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
		      
		      
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
