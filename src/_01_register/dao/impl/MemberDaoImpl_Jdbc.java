package _01_register.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;


import _01_register.dao.MemberDao;
import _01_register.model.MemberBean;
import nn.service.GlobalService;
import oracle.jdbc.internal.OracleBlob;
import oracle.jdbc.rowset.OracleSerialBlob;

// 本類別使用為標準的JDBC技術來存取資料庫。
public class MemberDaoImpl_Jdbc implements MemberDao {

	private DataSource ds = null;
	private Connection conn = null;
	public MemberDaoImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#建構子發生例外: " + ex.getMessage());
		}
	}
	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	public int saveMember(MemberBean mb) {
		String sql = "insert into Members " 
				+ " (account, name, password, address, email, "
				+ " tel, type, sex, income) "
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int n = 0;
		InputStream blobStream = null;
		Reader commentStream = null;
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setString(1, mb.getAccount());
			ps.setString(2, mb.getName());
			
			ps.setString(3, mb.getPassword());
			ps.setString(4, mb.getAddress());
			ps.setString(5, mb.getEmail());
			ps.setString(6, mb.getTel());
			ps.setString(7, mb.getType());
			ps.setString(8, mb.getSex());
			ps.setInt(9, mb.getIncome());
//			ps.setTimestamp(8, mb.getRegisterTime());
			
//			ps.setDouble(9, mb.getTotalAmt());
			// SerialBlob can't cast to OracleBlob
			// java.lang.ClassCastException: javax.sql.rowset.serial.SerialBlob incompatible with oracle.jdbc.OracleBlob
//			if(mb.getMemberImage() != null) blobStream = mb.getMemberImage().getBinaryStream();
//			ps.setBlob(10, blobStream);

//			ps.setString(11, mb.getFileName());
//
//			if(mb.getComment()!=null) commentStream = mb.getComment().getCharacterStream();
//			ps.setClob(12, commentStream);
//			ps.setDouble(13, mb.getUnpaid_amount());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#saveMember()發生例外: " 
										+ ex.getMessage());
		} finally {
			try {
				if (blobStream != null) blobStream.close();
				if (commentStream != null) commentStream.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		return n;
	}
	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		String sql = "SELECT * FROM Members WHERE account = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					exist = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#idExists()發生例外: " 
					+ ex.getMessage());
		}
		return exist;
	}
	
	// 由參數 id (會員帳號) 到Member表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	@Override
	public MemberBean queryMember(String id) {
		MemberBean mb = null;
		String sql = "SELECT * FROM Members WHERE memberID = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setString(1, id);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setId(rs.getInt("id"));
					mb.setAccount(rs.getString("account"));
					mb.setName(rs.getString("name"));
					mb.setPassword(rs.getString("password"));
					mb.setAddress(rs.getString("address"));
					mb.setEmail(rs.getString("email"));
					mb.setTel(rs.getString("tel"));
					mb.setType(rs.getString("type"));
//					mb.setRegisterTime(rs.getTimestamp("RegisterTime"));
//					mb.setTotalAmt(rs.getDouble("totalAmt"));
//					mb.setMemberImage(rs.getBlob("MemberImage"));
//					mb.setComment(rs.getClob("Comment"));
//					mb.setFileName(rs.getString("filename"));
//					mb.setUnpaid_amount(rs.getDouble("Unpaid_amount"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#queryMember()發生例外: " 
					+ ex.getMessage());
		}
		return mb;
	}
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@Override
	public MemberBean checkIDPassword(String userId, String password) {
		MemberBean mb = null;
		String sql = "SELECT * FROM Members m WHERE m.account = ? and m.password = ?";
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql);
		) {
			ps.setString(1, userId);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					mb = new MemberBean();
					mb.setId(rs.getInt("id"));
					mb.setAccount(rs.getString("account"));
					mb.setName(rs.getString("name"));
					mb.setPassword(rs.getString("password"));
					mb.setAddress(rs.getString("address"));
					mb.setEmail(rs.getString("email"));
					mb.setTel(rs.getString("tel"));
					mb.setType(rs.getString("type"));
//					mb.setRegisterTime(rs.getTimestamp("RegisterTime"));
//					mb.setTotalAmt(rs.getDouble("totalAmt"));
//					mb.setMemberImage(rs.getBlob("MemberImage"));
//					mb.setComment(rs.getClob("Comment"));
//					mb.setFileName(rs.getString("filename"));
//					mb.setUnpaid_amount(rs.getDouble("Unpaid_amount"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#checkIDPassword()發生SQL例外: " 
					+ ex.getMessage());
		}
		return mb;
	}
	/*
	 * 功能：更新客戶的未付款訂購金額。
	 * 說明：處理客戶訂單時，[訂單的總金額 + 該客戶的未付款餘額]不能超過限額，
	 * 此限額定義在 GlobalService類別的常數: ORDER_AMOUNT_LIMIT
	 * 步驟：
	 * 1. 取出Member表格內的 Member#unpaid_amount欄位(未付款餘額) 
	 * 2. unpaid_amount加上本訂單的總金額後，檢查該數值是否超過限額
	 *    (GlobalService.ORDER_AMOUNT_LIMIT)。 
	 *    如果超過限額， 則
	 *    		該訂單不予處裡， 丟出UnpaidOrderAmountExceedingException，
	 * 	    否則更新Member表格的unpaid_amount欄位: Member#unpaid_amount += currentAmount;
	 */
//	@Override
//	public void updateUnpaidOrderAmount(OrderBean ob) {
//		double currentAmount = ob.getTotalAmount(); // 取出該訂單的總金額
//		Double unpaidAmount = 0.0;
//		// 讀取Member表格中，該客戶的未付款金額(unpaid_amount)
//		String sql = "SELECT unpaid_amount FROM Member m WHERE m.memberId = ? ";
//		try (
//			PreparedStatement ps = conn.prepareStatement(sql);
//		) {
//			ps.setString(1, ob.getMemberId());
//			try (ResultSet rs = ps.executeQuery();) {
//				if (rs.next()) {
//					unpaidAmount = rs.getDouble(1);
//				}
//			}
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc類別#updateUnpaidOrderAmount()發生SQL例外: " + ex.getMessage());
//		}
//        // 如果該客戶的最新未付款總額 大於 公司規定之允許未付款總額
//		if (currentAmount + unpaidAmount > GlobalService.ORDER_AMOUNT_LIMIT) {
//			throw new UnpaidOrderAmountExceedingException("未付款金額超過限額: " + (currentAmount + unpaidAmount));
//		} else {
//			;
//		}
//		// 更新Member表格之未付款餘額欄位 unpaid_amount
//		String sql1 = "UPDATE Member SET unpaid_amount = unpaid_amount + ? " 
//		            + " WHERE memberId = ?";
//		
//		try (
//			PreparedStatement ps1 = conn.prepareStatement(sql1);
//		) {
//			ps1.setDouble(1, currentAmount);
//			ps1.setString(2, ob.getMemberId());
//			ps1.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc類別#updateUnpaidOrderAmount()發生SQL例外: " + ex.getMessage());
//		}
//	}
	
	@Override
	public void setConnection(Connection conn) {
        this.conn = conn;
	}
}
