package _01_register.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _01_register.model.MemberBean;
import _01_register.model.MemberFileBean;
import nn.service.GlobalService;
import nn.vo.FileBean;

public class InsertFileDao {
	private DataSource ds = null;
	private Connection conn = null;
	public InsertFileDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#建構子發生例外: " + ex.getMessage());
		}
	}
	
	public int insertFile(int memberId, Blob blob) {
		int id = 0;
		String sql = "insert into files " 
				+ " (member_id, file_blob) "
				+ " values (?, ?)";
		InputStream blobStream = null;
		Reader commentStream = null;
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql);
			java.sql.Statement stmt= con.createStatement();
		) {
			ps.setInt(1, memberId);
			if(blob != null) blobStream = blob.getBinaryStream();
			ps.setBlob(2, blobStream);
			ps.executeUpdate();
			ResultSet rs =stmt.executeQuery("select file_id from files where member_id =" +  memberId);
			while(rs.next()) {
				id = rs.getInt(1);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
