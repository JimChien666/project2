package Members;

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

public class DaoMembers {
	private DataSource dataSource;

	public DataSource getDataSource() {
		if (this.dataSource == null) {
			try {
				InitialContext ctxt = new InitialContext();
				DataSource dataSource = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
				return dataSource;
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	
	
	public List<ValueObjectMembers> listMembers(){
		List<ValueObjectMembers> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Members order by ID");
				){
			while(rs.next()) {
				ValueObjectMembers members = new ValueObjectMembers();
				members.setId(rs.getInt("ID"));
				members.setName(rs.getString("Name"));
				members.setIncome(rs.getString("Income"));
				members.setTel(rs.getString("Tel"));
				members.setAccount(rs.getString("Account"));
				members.setPassword(rs.getString("Password"));
				members.setEmail(rs.getString("Email"));
				members.setAddress(rs.getString("Address"));
				members.setAdoptedLevelId(rs.getInt("AdoptedLevelId"));
				members.setMemberType(rs.getString("MemberType"));
				members.setSex(rs.getString("Sex"));
				list.add(members);
			}
			return list;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	
	public boolean createMembers(ValueObjectMembers valueObjectMembers) {
		String sql = "insert into Members values(?,?,?,?,?,?,?,?,?,?,?)";
		try (
				Connection conn = getDataSource().getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql);
				){
			pStmt.setInt   (1, valueObjectMembers.getId());
			pStmt.setString(2, valueObjectMembers.getName());
			pStmt.setString(3, valueObjectMembers.getIncome());
			pStmt.setString(4, valueObjectMembers.getTel());
			pStmt.setString(5, valueObjectMembers.getAccount());
			pStmt.setString(6, valueObjectMembers.getPassword());
			pStmt.setString(7, valueObjectMembers.getEmail());
			pStmt.setString(8, valueObjectMembers.getAddress());
			pStmt.setInt   (9, valueObjectMembers.getAdoptedLevelId());
			pStmt.setString( 10, valueObjectMembers.getEmail());
			pStmt.setString( 11, valueObjectMembers.getSex());
			int updatecount = pStmt.executeUpdate();
			if(updatecount>=1)return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}
	}

	//查詢一筆資料修改刪除
	public ValueObjectMembers getMembers(int Id) {
		ValueObjectMembers valueObjectMembers = null;
		String sql = "select * from Members where ID = ?";
		try (
				Connection conn = getDataSource().getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql);
				){
			pStmt.setInt(1, Id);
			try(ResultSet rs = pStmt.executeQuery();){
				if(rs.next()) {					
			valueObjectMembers = new ValueObjectMembers();
			valueObjectMembers.setId(rs.getInt("Id"));
			valueObjectMembers.setName(rs.getString(2));
			valueObjectMembers.setIncome(rs.getString(3));
			valueObjectMembers.setTel(rs.getString(4));
			valueObjectMembers.setAccount(rs.getString(5));
			valueObjectMembers.setPassword(rs.getString(6));
			valueObjectMembers.setEmail(rs.getString(7));
			valueObjectMembers.setAddress(rs.getString(8));
			valueObjectMembers.setAdoptedLevelId(rs.getInt(9));
			valueObjectMembers.setEmail(rs.getString(10));
			valueObjectMembers.setSex(rs.getString(11));
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return valueObjectMembers;
	}
	

	public boolean updateMembers(ValueObjectMembers valueObjectMembers) {
		String sql = "update Members set ID=?, Name=?, Income=?, Tel=?, Account=?, Password=?, Email=? Address=? AdoptedLevelId=? Email=? Sex=?";
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt   (1, valueObjectMembers.getId());
			pStmt.setString(2, valueObjectMembers.getName());
			pStmt.setString(3, valueObjectMembers.getIncome());
			pStmt.setString(4, valueObjectMembers.getTel());
			pStmt.setString(5, valueObjectMembers.getAccount());
			pStmt.setString(6, valueObjectMembers.getPassword());
			pStmt.setString(7, valueObjectMembers.getEmail());
			pStmt.setString(8, valueObjectMembers.getAddress());
			pStmt.setInt   (9, valueObjectMembers.getAdoptedLevelId());
			pStmt.setString(10,valueObjectMembers.getEmail());
			pStmt.setString(11,valueObjectMembers.getSex());
			
			
			pStmt.executeUpdate();
			pStmt.clearParameters();
			conn.commit();
			return true;
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean deleteMembers(int Id) {
		String sql = "delete from Members where Id=?";
		try (
				Connection conn = getDataSource().getConnection();
				){
			conn.setAutoCommit(false);
			try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, Id);
			pStmt.executeUpdate();
			pStmt.clearParameters();
			conn.commit();
			return true;
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}