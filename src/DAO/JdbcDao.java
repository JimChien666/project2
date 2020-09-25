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

import org.apache.naming.java.javaURLContextFactory;


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
		  	  
		  	  //自取號機取得新部門的部門代號
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// fourms DAO start point TODO
	// 新增
	public int insertForum(Forums forum) {
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO forums (id,member_Id,content,create_At) VALUES (?,?,?,?)");){
		  	  int deptno = 1;
			    String getForumsIdSql = "SELECT forums_id.nextval FROM DUAL";
		  	  
		  	  //自取號機取得新部門的部門代號
			    ResultSet rs = stmt.executeQuery(getForumsIdSql);

		      if (rs.next()) deptno = rs.getInt(1);

		      rs.close();		      
		      java.sql.Date createAt = new java.sql.Date(forum.getCreateAt().getTime());
		      pstmt.setInt(1, forum.getId());
		      pstmt.setInt(2, forum.getMemberId());
		      pstmt.setString(3, forum.getContent());
		      pstmt.setDate(4, createAt);
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		      stmt.close();
		    return deptno;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// 修改
	public boolean updateForums(Forums forum) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update forums member_Id=?,content=?,create_At=? where id=?");) {
		      
		      pstmt.setInt(1, forum.getMemberId());
		      pstmt.setString(2, forum.getContent());
		      java.sql.Date createAt = new java.sql.Date(forum.getCreateAt().getTime());
		      pstmt.setDate(3, createAt);
		      pstmt.setInt(4, forum.getId());
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	// Forums list
	public List<Forums> listForums() {
		List<Forums> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Forums");){
		  	  
			while (rs.next()) {
				Forums forum = new Forums();
				forum.setId(rs.getInt("id"));
				forum.setMemberId(rs.getInt("member_id"));
				forum.setContent(rs.getString("Content"));
				forum.setCreateAt(rs.getDate("create_at"));
				list.add(forum);
			}
			return list;		      
		      
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//查詢
	public boolean queryForums(Forums forum) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from forums where id=?");) {
		  	  
		      pstmt.setInt(1, forum.getId());
//		      pstmt.setInt(2, forum.getMemberId());
//		      pstmt.setString(3, forum.getContent());
//		      pstmt.setDate(4, forum.getCreateAt());

		      ResultSet rs = pstmt.executeQuery(); 

		      while(rs.next()){            
		          System.out.print(rs.getInt("id"));
		          System.out.print(rs.getString("MEMBER_ID"));
		          System.out.print(rs.getString("CONTENT"));
		          System.out.print(rs.getDate("CREATED_AT"));        
		      }
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//刪除
	public boolean deleteForums(Forums forum) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from forums where id=?");) {
		  	  
			  pstmt.setInt(1, forum.getId());
//		      pstmt.setInt(2, forum.getMemberId());
//		      pstmt.setString(3, forum.getContent());
//		      pstmt.setDate(4, forum.getCreateAt());

		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}		
	
	
	
	// fourms DAO end point TODO
	// comments DAO start point
	
	
	// 新增
	public int insertComments(Comments comment) {
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO comments (id,forum_Id,comment,member_Id) VALUES (?,?,?,?)");){
		  	  int deptno = 1;
			    String getcommentsIdSql = "SELECT comments_id.nextval FROM DUAL";
		  	  
		  	  //自取號機取得新部門的部門代號
			    ResultSet rs = stmt.executeQuery(getcommentsIdSql);

		      if (rs.next()) deptno = rs.getInt(1);

		      rs.close();		      
		      
		      pstmt.setInt(1, comment.getId());
		      pstmt.setInt(2, comment.getForumId());
		      pstmt.setString(3, comment.getComment());
		      pstmt.setInt(4, comment.getMemberId());
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		      stmt.close();
		    return deptno;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// 修改
	public boolean updateComments(Comments comment) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update forums_Id=?,comment=?,MEMBER_ID=? where id=?");) {
//			  java.sql.Date adoptedAt = new java.sql.Date(adoptedRecord.getAdoptedAt().getTime());
//		      pstmt.setDate(2, adoptedAt);
		      
		      pstmt.setInt(1, comment.getForumId());
		      pstmt.setString(2, comment.getComment());
		      pstmt.setInt(3, comment.getMemberId());
		      pstmt.setInt(4, comment.getId());
		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	// Comments list
	public List<Comments> listComments() {
		List<Comments> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Forums");){
		  	  
			while (rs.next()) {
				Comments comment = new Comments();
				comment.setId(rs.getInt("id"));
				comment.setForumId(rs.getInt("FORUM_ID"));
				comment.setComment(rs.getString("comment"));
				comment.setMemberId(rs.getInt("MEMBER_ID"));
				list.add(comment);
			}
			return list;
		      
		      
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查詢
	public boolean queryComments(Comments comment) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from forums where id=?");) {
		  	  
		      pstmt.setInt(1, comment.getId());
//		      pstmt.setInt(2, comment.getForumId());
//		      pstmt.setString(3, comment.getComment());
//		      pstmt.setInt(4, comment.getMemberId());

		      ResultSet rs = pstmt.executeQuery(); 

		      while(rs.next()){            
		          System.out.print(rs.getInt("id"));
		          System.out.print(rs.getInt("MEMBER_ID"));
		          System.out.print(rs.getString("CONTENT"));
		          System.out.print(rs.getInt("CREATED_AT"));        
		      }
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//刪除
	public boolean deleteComments(Comments comment) {
		try(
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from forums where id=?");) {
		  	  
			  pstmt.setInt(1, comment.getId());
//		      pstmt.setInt(2, comment.getForumId());
//		      pstmt.setString(3, comment.getComment());
//		      pstmt.setInt(4, comment.getMemberId());

		      pstmt.executeUpdate();
			  pstmt.clearParameters();
		      
		    return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}		
	
	
}
