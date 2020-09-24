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


public int insertProduct(Products product) {
	try (
			Connection conn = getDataSource().getConnection();
			Statement stmt = conn.createStatement();
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products (id,name,price) VALUES (?,?,?)");){
	  	  int productId = 1;
		    String getProductIdSql = "SELECT product_id.nextval FROM DUAL";
	  	  
	  	  //自取號機取得新部門的部門代號
		  ResultSet rs = stmt.executeQuery(getProductIdSql);

	      if (rs.next()) productId = rs.getInt(1);

	      rs.close();
	      
	      
	      pstmt.setInt(1, productId);
	      pstmt.setString(2, product.getName());
	      pstmt.setInt(3, product.getPrice());
	      pstmt.executeUpdate();
		  pstmt.clearParameters();
	      
	      stmt.close();
	    return productId;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public boolean updateProduct(Products product) {
	try(
			Connection conn = getDataSource().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("update products set name=?,price=? where id=?");) {
	  	  
	      
	      pstmt.setString(1, product.getName());
	      pstmt.setInt(2, product.getPrice());
	      pstmt.setInt(3, product.getId());
	      pstmt.executeUpdate();
		  pstmt.clearParameters();
	      
	    return true;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return false;
}

public int searchProduct(Products product) {
	try (Connection conn = getDataSource().getConnection();
	      PreparedStatement pstmt = conn.prepareStatement("select * from products where id=?");){
	  	 
		  pstmt.setString(1, product.getName());
	      pstmt.executeUpdate();
		  pstmt.clearParameters();
	      

	}catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}

public int deleteProduct(Products product) {
	try (Connection conn = getDataSource().getConnection();
	      PreparedStatement pstmt = conn.prepareStatement("delete from products where id=?");){
	  	 
		  pstmt.setInt(1, product.getId());

	      pstmt.executeUpdate();
		  pstmt.clearParameters();
	      

	}catch (Exception e) {
		e.printStackTrace();
	}
	return 0;
}











public List<Products> listProducts() {
	List<Products> list = new ArrayList<>();
	try (
			Connection conn = getDataSource().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from products");){
	  	  
		while (rs.next()) {
			Products product = new Products();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("price"));
			list.add(product);
		}
		return list;
	      
	      
	}catch (Exception e) {
		e.printStackTrace();
	}
	return list;
}}




















