package Members;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MemberDAO {
	private Connection conn;
	

	public MemberDAO(Connection conn) {
		this.conn = conn;
	}	
		
		
	public boolean insertMembers(MemberBean memberData) {
		System.out.println("hi");
		try {
			String sqlString = "insert into Members (name,income,tel,account,password,email,address,membertype,sex) values ('"
					+memberData.getName()+"','"
					+memberData.getIncome()+"','"
				    +memberData.getTel()+"','"
				    +memberData.getAccount()+"','"
				    +memberData.getPassword()+"','"
				    +memberData.getEmail()+"','"
				    +memberData.getAddress()+"','"
				    +memberData.getMemberType()+"','"
					+memberData.getSex()+"')";
		
			  Statement stmt = conn.createStatement();
		      System.out.println(sqlString);
			    int updatecount = stmt.executeUpdate(sqlString);
		      stmt.close();
		      if (updatecount >= 1) return true;
		      else                  return false;
			  } catch (Exception e) {
			    System.err.println("新增會員資料失敗:" + e);
				  return false;
		    }
		  }

	public boolean updateMembers(MemberBean memberData) {	
		
		PreparedStatement psmt = null;
		
		
		try {
			String sqlString = "update Members set Name=?, Income=?, Tel=?, Email=? ,Address=? ,Sex=? where ID=?";
		
			  psmt=conn.prepareStatement(sqlString);
			  
			  
			  
			    System.out.println(memberData.getName());
				psmt.setString(1, memberData.getName());
				psmt.setString(2, memberData.getIncome());
				psmt.setString(3, memberData.getTel());
				psmt.setString(4, memberData.getEmail());
				psmt.setString(5, memberData.getAddress());
				psmt.setString(6,memberData.getSex());
				psmt.setInt(7, memberData.getId());
				
		      
			  int executeUpdate = psmt.executeUpdate();
			  
			    
		      System.out.println(executeUpdate);

		      
		      if (executeUpdate >= 1) return true;
		      
		      else  return false;
			  } 
		      catch (Exception e) {
			    System.err.println("更新會員失敗" + e);
				  return false;
		    }finally {
		    	try {
					psmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	}

	public boolean deleteMembers(String Id) {
		try {
			String sqlString = "delete from Members where Id='"+Id+"'";
		
			  Statement stmt = conn.createStatement();
		      System.out.println(sqlString);
			    int updatecount = stmt.executeUpdate(sqlString);
		      stmt.close();
		      if (updatecount >= 1) return true;
		      else                  return false;
			  } catch (Exception e) {
			    System.err.println("刪除會員失敗:" + e);
				  return false;
		    }
	}

	public List<MemberBean> getAllMembers(){
		List<MemberBean> list = new ArrayList<MemberBean>();
		try {
			String sqlString ="select * from Members";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			while(rs.next()) {
				MemberBean memberBean= new MemberBean(); 
				memberBean.setId(rs.getInt("id"));
				memberBean.setName(rs.getString("name"));
				memberBean.setIncome(rs.getString("income"));
				memberBean.setTel(rs.getString("tel"));
				memberBean.setAccount(rs.getString("account"));
				memberBean.setPassword(rs.getString("password"));
				memberBean.setEmail(rs.getString("email"));
				memberBean.setAddress(rs.getString("address"));
				memberBean.setMemberType(rs.getString("memberType"));
				memberBean.setSex(rs.getString("sex"));
				
				list.add(memberBean);
			}
			System.out.println(list.size());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
		
	}



//	public List<MemberBean> getArticleSearch(String keyword) {
//		List<ArticleBean> atlsrh = new ArrayList<ArticleBean>();	
//		try {
//			String sqlString ="select * from Article where title like+'%"+keyword+"%'"+"order by articleid";		
//			PreparedStatement prepareStatement = conn.prepareStatement(sqlString);
////			prepareStatement.setString(1, );
//			ResultSet rs = prepareStatement.executeQuery();
//			System.out.println(sqlString);
//			
//			while(rs.next()) {
//				ArticleBean articleBean= new ArticleBean(); 
//				articleBean.setMemberid(rs.getString("memberid"));
//				articleBean.setText(rs.getString("text"));
//				articleBean.setTitle(rs.getString("title"));
//				atlsrh.add(articleBean);
//			}
//			System.out.println(atlsrh.size());
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return atlsrh;
//		
//	}
//
//
//


	}

