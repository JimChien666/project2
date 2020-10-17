package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.ActivityRecords;
import DAO.Activitys;
import DAO.AdoptedLevels;
import DAO.AdoptedRecords;
import DAO.Animals;
import DAO.Comments;
import DAO.Forums;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.naming.java.javaURLContextFactory;

public class JdbcDao {
	private DataSource dataSource;

	public DataSource getDataSource() {
		System.out.println("�ڴN�O�ncommit");
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

	
	// 新增會員
	public boolean insertMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO members (name,income,phone,account,password,email,address,adoptedlevelid,type,sex) VALUES (?,?,?,?,?,?,?,?,?,?)");) {
//			int membersno = 1;
//			String getMembersIdSql = "SELECT member_id.nextval FROM DUAL";
//
//			ResultSet rs = stmt.executeQuery(getMembersIdSql);
//
//			if (rs.next())
//				membersno = rs.getInt(1);
//
//			rs.close();

//			pstmt.setInt(1, members.getId());
			pstmt.setString(1, members.getName());
			pstmt.setInt(2, members.getIncome());
			pstmt.setString(3, members.getPhone());
			pstmt.setString(4, members.getAccount());
			pstmt.setString(5, members.getPassword());
			pstmt.setString(6, members.getEmail());
			pstmt.setString(7, members.getAddress());
			pstmt.setInt(8, members.getAdoptedLevelId());
			pstmt.setString(9, members.getMemberType());
			pstmt.setString(10, members.getSex());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 查詢會員
	// ID查
	public Members queryMembers(String account) {
		Members member = new Members();
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from members where member_account=?");) {
//				PreparedStatement pstmt = conn.prepareStatement("select * from members where id=?,name=?,income=?,phone=?,account=?,password=?,email=?,address=?,adoptedlevelid=?,type=?");) {

			pstmt.setString(1, account);
//		      pstmt.setString(2, members.getName());
//		      pstmt.setInt(3, members.getIncome());
//		      pstmt.setString(4, members.getPhone());
//		      pstmt.setString(5, members.getAccount());
//		      pstmt.setString(6, members.getPassword());
//		      pstmt.setString(7, members.getEmail());
//		      pstmt.setString(8, members.getAddress());		      
//		      pstmt.setInt(9, members.getAdoptedLevelId());
//		      pstmt.setString(10, members.getMemberType());
//			  pstmt.setString(11, members.getSex());
			
			ResultSet rs = pstmt.executeQuery();
			// TODO
			if (rs.next()) {
				
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setIncome(rs.getInt("income"));
				member.setPhone(rs.getString("phone"));
				member.setPassword(rs.getString("password"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setAdoptedLevelId(rs.getInt("adoptedLevelId"));
				member.setMemberType(rs.getString("type"));
				member.setSex(rs.getString("sex"));
				
				
				System.out.print(rs.getInt("member_id"));
				System.out.print(rs.getString("member_name"));
				System.out.print(rs.getString("member_email"));
				System.out.print(rs.getString("member_password"));
				System.out.println(rs.getString("member_adopted_level_id"));

			}
			pstmt.clearParameters();

			return member;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	// 修改會員
	// ID查
	public boolean updateMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"update members set name=?,income=?,phone=?,account=?,password=?,email=?,address=?,adopted_level_id=?,member_type=? where id=?");) {

			pstmt.setString(1, members.getName());
			pstmt.setInt(2, members.getIncome());
			pstmt.setString(3, members.getPhone());
			pstmt.setString(4, members.getAccount());
			pstmt.setString(5, members.getPassword());
			pstmt.setString(6, members.getEmail());
			pstmt.setString(7, members.getAddress());
			pstmt.setInt(8, members.getAdoptedLevelId());
			pstmt.setString(9, members.getMemberType());
			pstmt.setString(10, members.getSex());
			pstmt.setInt(11, members.getId());
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
	public boolean deleteMembers(String account) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from members where account=?");) {
//					PreparedStatement pstmt = conn.prepareStatement("delete from members where id=?,name=?,income=?,phone=?,account=?,password=?,email=?,address=?,adopted_level_id=?,member_type=?");) {

			pstmt.setString(1, account);
//			      pstmt.setString(2, members.getName());
//			      pstmt.setInt(3, members.getIncome());
//			      pstmt.setString(4, members.getPhone());
//			      pstmt.setString(5, members.getAccount());
//			      pstmt.setString(6, members.getPassword());
//			      pstmt.setString(7, members.getEmail());
//			      pstmt.setString(8, members.getAddress());		      
//			      pstmt.setInt(9, members.getAdoptedLevelId());
//			      pstmt.setString(10, members.getMemberType());
//			      pstmt.setString(11, members.getSex());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 會員ArrayList
	public List<Members> listMembers() {
		List<Members> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from members");) {

			while (rs.next()) {
				Members member = new Members();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setIncome(rs.getInt("income"));
				member.setPhone(rs.getString("tel"));
				member.setAccount(rs.getString("account"));
				member.setPassword(rs.getString("password"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setAdoptedLevelId(rs.getInt("adopted_level_id"));
				member.setMemberType(rs.getString("member_type"));
				member.setSex(rs.getString("Sex"));
				list.add(member);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}}



