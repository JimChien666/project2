package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DAO.Animals;
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
				DataSource dataSource = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

				return dataSource;
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataSource;
	}

	public int insertAnimal(Animals animal) {
		try (Connection conn = getDataSource().getConnection();

				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO animals (id,age,animal_type,breed,coat,sex,img_url,is_alive) VALUES (?,?,?,?,?,?,?,?)");) {

			int animalId = 1;
			String getAnimalIdSql = "SELECT animal_id.nextval FROM DUAL";
			ResultSet rs = stmt.executeQuery(getAnimalIdSql);
			if (rs.next())
				animalId = rs.getInt(1);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean updateAnimal(Animals animal) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"update animals set age=?,animal_type=?,breed=?,coat=?,sex,img_url=?,is_alive=?  where id=?");) {

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Animals> listAnimals() {
		List<Animals> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from animals order by id");) {

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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 新增會員
	public int insertMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO members (id,name,income,tel,account,password,email,address,adopted_level_id,member_type) VALUES (?,?,?,?,?,?,?,?,?,?)");) {
			int membersno = 1;
			String getMembersIdSql = "SELECT member_id.nextval FROM DUAL";

			ResultSet rs = stmt.executeQuery(getMembersIdSql);

			if (rs.next())
				membersno = rs.getInt(1);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 查詢會員
	// ID查
	public boolean queryMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
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
			while (rs.next()) {
				System.out.print(rs.getInt("id"));
				System.out.print(rs.getString("name"));
				System.out.print(rs.getString("email"));
				System.out.print(rs.getString("password"));
				System.out.println(rs.getString("adopted_level_id"));

			}
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 修改會員
	// ID查
	public boolean updateMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"update members set name=?,income=?,tel=?,account=?,password=?,email=?,address=?,adopted_level_id=?,member_type=? where id=?");) {

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 刪除會員
	// ID查
	public boolean deleteMembers(Members members) {
		try (Connection conn = getDataSource().getConnection();
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insertAdopedRecord(AdoptedRecords adoptedRecord) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO adopted_records (id,member_id,adopted_at,unadopted_at,animal_id,status) VALUES (?,?,?,?,?,?)");) {
			int adoptedRecordId = 1;
			String getAdoptedRecordIdSql = "SELECT adopted_record_id.nextval FROM DUAL";

			ResultSet rs = stmt.executeQuery(getAdoptedRecordIdSql);

			if (rs.next())
				adoptedRecordId = rs.getInt(1);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean updateAdoptedRecord(AdoptedRecords adoptedRecord) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"update adopted_records set member_id=?,adopted_at=?,unadopted_at=?,animal_id=?,status=? where id=?");) {

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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<AdoptedRecords> listAdoptedRecords() {
		List<AdoptedRecords> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from adopted_records");) {

			while (rs.next()) {
				AdoptedRecords adoptedRecord = new AdoptedRecords();
				adoptedRecord.setId(rs.getInt("id"));
				adoptedRecord.setMemberId(rs.getInt("member_id"));
				;
				adoptedRecord.setAdoptedAt(rs.getDate("adopted_at"));
				adoptedRecord.setUnadoptedAt(rs.getDate("unadopted_at"));
				adoptedRecord.setAnimalId(rs.getInt("animal_id"));
				adoptedRecord.setStatus(rs.getString("status"));

				list.add(adoptedRecord);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insertForum(Forums forum) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn
						.prepareStatement("INSERT INTO forums (id,member_Id,content,created_At) VALUES (?,?,?,?)");) {
			int deptno = 1;
			String getForumsIdSql = "SELECT forum_id.nextval FROM DUAL";

			// �ۨ��������o�s�����������N��
			ResultSet rs = stmt.executeQuery(getForumsIdSql);

			if (rs.next())
				deptno = rs.getInt(1);

			rs.close();
			pstmt.setInt(1, forum.getId());
			pstmt.setInt(2, forum.getMemberId());
			pstmt.setString(3, forum.getContent());
			java.sql.Date CreateAt = new java.sql.Date(forum.getCreateAt().getTime());
			pstmt.setDate(4, CreateAt);
			pstmt.executeUpdate();
			pstmt.clearParameters();

			stmt.close();
			return deptno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// �ק�
	public boolean updateForums(Forums forum) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("update forums member_Id=?,content=?,created_at=? where id=?");) {

			pstmt.setInt(1, forum.getMemberId());
			pstmt.setString(2, forum.getContent());
			java.sql.Date createAt = new java.sql.Date(forum.getCreateAt().getTime());
			pstmt.setDate(3, createAt);
			pstmt.setInt(4, forum.getId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Forums list
	public List<Forums> listForums() {
		List<Forums> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Forums");) {

			while (rs.next()) {
				Forums forum = new Forums();
				forum.setId(rs.getInt("id"));
				forum.setMemberId(rs.getInt("member_id"));
				forum.setContent(rs.getString("Content"));
				forum.setCreateAt(rs.getDate("create_at"));
				list.add(forum);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// �d��
	public boolean queryForums(Forums forum) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from forums where id=?");) {

			pstmt.setInt(1, forum.getId());
//		      pstmt.setInt(2, forum.getMemberId());
//		      pstmt.setString(3, forum.getContent());
//		      pstmt.setDate(4, forum.getCreateAt());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.print(rs.getInt("id"));
				System.out.print(rs.getString("MEMBER_ID"));
				System.out.print(rs.getString("CONTENT"));
				System.out.print(rs.getDate("CREATED_AT"));
			}
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// �R��
	public boolean deleteForums(Forums forum) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from forums where id=?");) {

			pstmt.setInt(1, forum.getId());
//		      pstmt.setInt(2, forum.getMemberId());
//		      pstmt.setString(3, forum.getContent());
//		      pstmt.setDate(4, forum.getCreateAt());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// fourms DAO end point TODO
	// comments DAO start point

	// �s�W
	public int insertComments(Comments comment) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn
						.prepareStatement("INSERT INTO comments (id,forum_Id,comment,member_Id) VALUES (?,?,?,?)");) {
			int deptno = 1;
			String getcommentsIdSql = "SELECT comments_id.nextval FROM DUAL";

			// �ۨ��������o�s�����������N��
			ResultSet rs = stmt.executeQuery(getcommentsIdSql);

			if (rs.next())
				deptno = rs.getInt(1);

			rs.close();

			pstmt.setInt(1, comment.getId());
			pstmt.setInt(2, comment.getForumId());
			pstmt.setString(3, comment.getComment());
			pstmt.setInt(4, comment.getMemberId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			stmt.close();
			return deptno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// �ק�
	public boolean updateComments(Comments comment) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("update forums_Id=?,comment=?,MEMBER_ID=? where id=?");) {
//			  java.sql.Date adoptedAt = new java.sql.Date(adoptedRecord.getAdoptedAt().getTime());
//		      pstmt.setDate(2, adoptedAt);

			pstmt.setInt(1, comment.getForumId());
			pstmt.setString(2, comment.getComment());
			pstmt.setInt(3, comment.getMemberId());
			pstmt.setInt(4, comment.getId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Comments list
	public List<Comments> listComments() {
		List<Comments> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from Forums");) {

			while (rs.next()) {
				Comments comment = new Comments();
				comment.setId(rs.getInt("id"));
				comment.setForumId(rs.getInt("FORUM_ID"));
				comment.setComment(rs.getString("comment"));
				comment.setMemberId(rs.getInt("MEMBER_ID"));
				list.add(comment);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// �d��
	public boolean queryComments(Comments comment) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from forums where id=?");) {

			pstmt.setInt(1, comment.getId());
//		      pstmt.setInt(2, comment.getForumId());
//		      pstmt.setString(3, comment.getComment());
//		      pstmt.setInt(4, comment.getMemberId());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.print(rs.getInt("id"));
				System.out.print(rs.getInt("MEMBER_ID"));
				System.out.print(rs.getString("CONTENT"));
				System.out.print(rs.getInt("CREATED_AT"));
			}
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// �R��
	public boolean deleteComments(Comments comment) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from forums where id=?");) {

			pstmt.setInt(1, comment.getId());
//		      pstmt.setInt(2, comment.getForumId());
//		      pstmt.setString(3, comment.getComment());
//		      pstmt.setInt(4, comment.getMemberId());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//////////////////////////// List
	public List<AdoptedLevels> listAdoptedLevels() {
		List<AdoptedLevels> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from ADOPTED_LEVELS");) {

			while (rs.next()) {
				AdoptedLevels adoptedLevels = new AdoptedLevels();
				adoptedLevels.setId(rs.getInt("id"));
				adoptedLevels.setMax(rs.getInt("max"));
				adoptedLevels.setMin(rs.getInt("min"));
				adoptedLevels.setLimitedAdopted(rs.getInt("LIMITED_ADOPTED"));// get table column名
				list.add(adoptedLevels);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/////////////////////////////////////// insert
	public int insertAdoptedLevels(AdoptedLevels adoptedLevels) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ADOPTED_LEVELS VALUES (?,?,?,?)");) {
			pstmt.setInt(1, adoptedLevels.getId());
			pstmt.setInt(2, adoptedLevels.getMax());
			pstmt.setInt(3, adoptedLevels.getMin());
			pstmt.setInt(4, adoptedLevels.getLimitedAdopted());
			pstmt.executeUpdate();
			pstmt.clearParameters();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	////////////////////////////////////// update
	public boolean updateAdoptedLevels(AdoptedLevels adoptedLevels) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("update ADOPTED_LEVELS set Max=?,Min=?,LIMITED_ADOPTED=? where id=?");) {

			pstmt.setInt(1, adoptedLevels.getMax());
			pstmt.setInt(2, adoptedLevels.getMin());
			pstmt.setInt(3, adoptedLevels.getLimitedAdopted());
			pstmt.setInt(4, adoptedLevels.getId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	///////////////////////////////// selectLevels
	public List<AdoptedLevels> selectAdoptedLevels() {
		List<AdoptedLevels> list = listAdoptedLevels();
		for (AdoptedLevels adoptedLevelslist : list) {
			if (adoptedLevelslist.getId() == 1) {
				System.out.println(adoptedLevelslist.toString());
			}
		}

		return list;
	}

	///////////////////////////////// selectAll
	public List<AdoptedLevels> selectAllAdoptedLevels() {
		List<AdoptedLevels> list = listAdoptedLevels();
		for (AdoptedLevels adoptedLevelslist : list) {
			System.out.println(adoptedLevelslist.toString());
		}

		return list;
	}

	////////////////////////////////// delete
	public boolean deleteAdoptedLevels(AdoptedLevels adoptedLevels) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from ADOPTED_LEVELS where id=?");) {

			pstmt.setInt(1, adoptedLevels.getId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}



	public int insertActivityRecords(ActivityRecords activityRecord) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO activityRecords (id, activity_id,  animal_id) VALUES  (?,?,?)");) {
			int activityRecordId = 1;
			String getAactivityRecordIdSql = "SELECT  animal_id.nextval FROM DUAL";

			ResultSet rs = stmt.executeQuery(getAactivityRecordIdSql);
			if (rs.next())
				activityRecordId = rs.getInt(1);
			rs.close();
			pstmt.setInt(1, activityRecordId);
			pstmt.setInt(2, activityRecord.getActivityId());
			pstmt.setInt(3, activityRecord.getActivityId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			stmt.close();
			return activityRecordId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	public boolean updateActivityRecords(ActivityRecords activityRecord) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement("update activity_records set activity_id=?, animal_id=? where id=?");) {

			pstmt.setInt(1, activityRecord.getActivityId());
			pstmt.setInt(2, activityRecord.getanilmalId());
			pstmt.setInt(3, activityRecord.getId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<ActivityRecords> listActivtyRecords() {
		List<ActivityRecords> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select *  from  activityRecords ");) {

			while (rs.next()) {
				ActivityRecords activityRecords = new ActivityRecords();
				activityRecords.setId(rs.getInt("id"));
				activityRecords.setActivityId(rs.getInt("activity_id"));
				activityRecords.setAnimalId(rs.getInt("animal_id"));

				list.add(activityRecords);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// insert Activitys Dao start

	public int insertActivitys(Activitys activitys) {
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO activitys (id, activeAt,  name, topic, content, location, limitedNum, limitedType) VALUES  (?,?,?,?,?,?,?,?)");) {
			int ActivityId = 1;
			String getActivityIdSql = "SELECT  animal_id.nextval FROM DUAL";

			// 自取號機取得新部門的部門代號
			ResultSet rs = stmt.executeQuery(getActivityIdSql);
			if (rs.next())
				ActivityId = rs.getInt(1);
			rs.close();

			pstmt.setInt(1, ActivityId);
			java.sql.Date ActiveAt = new java.sql.Date(activitys.getActiveAt().getTime());
			pstmt.setDate(2, ActiveAt);
			pstmt.setString(2, activitys.getName());
			pstmt.setString(3, activitys.getTopic());
			pstmt.setString(4, activitys.getContent());
			pstmt.setString(5, activitys.getLocation());
			pstmt.setInt(6, activitys.getLimitedNum());
			pstmt.setInt(7, activitys.getLimitedType());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			stmt.close();
			return ActivityId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// update Activitys
	public boolean updateActivitys(Activitys activitys) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(
						"update activitys set id=?, activtAt=?, name=?, topic=?, content=?, location=?, limitedNum=?, limitedType=? where id=?");) {

			pstmt.setInt(1, activitys.getId());
			java.sql.Date ActiveAt = new java.sql.Date(activitys.getActiveAt().getTime());
			pstmt.setDate(2, ActiveAt);
			pstmt.setString(2, activitys.getName());
			pstmt.setString(3, activitys.getTopic());
			pstmt.setString(4, activitys.getContent());
			pstmt.setString(5, activitys.getLocation());
			pstmt.setInt(6, activitys.getLimitedNum());
			pstmt.setInt(7, activitys.getLimitedType());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Activitys list select all ActivitysDao
	public List<Activitys> listActivitys() {
		List<Activitys> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select *  from  activitys ");) {

			while (rs.next()) {
				Activitys activitys = new Activitys();
				activitys.setId(rs.getInt("id"));
				activitys.setActiveAt(rs.getDate("active_at"));
				activitys.setName(rs.getString("name"));
				activitys.setTopic(rs.getString("topic"));
				activitys.setContent(rs.getString("content"));
				activitys.setLocation(rs.getString("location"));
				activitys.setLimitedNum(rs.getInt("limited_num"));
				activitys.setLimitedType(rs.getInt("limited_type"));
				list.add(activitys);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

// select activitysId 
	public boolean queryActivitys(Activitys activitys) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from activitys where id=?");) {

			pstmt.setInt(1, activitys.getId());
//		      java.sql.Date ActiveAt = new java.sql.Date(activitys.getActiveAt().getTime());
//		      pstmt.setDate(2, ActiveAt);
//              pstmt.setString(2, activitys.getName());
//              pstmt.setString(3, activitys.getTopic());
//              pstmt.setString(4, activitys.getContent());
//              pstmt.setString(5, activitys.getLocation());
//              pstmt.setInt(6, activitys.getLimitedNum());
//              pstmt.setInt(7, activitys.getLimitedType());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.print(rs.getInt("id"));
				System.out.print(rs.getDate("active_at"));
				System.out.print(rs.getString("topic"));
				System.out.print(rs.getString("content"));
				System.out.println(rs.getString("location"));
				System.out.println(rs.getInt("limited_num"));
				System.out.println(rs.getInt("limited_type"));

			}
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//delete activitysDao

	public boolean deleteActivitys(Activitys activitys) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from activitys where id=?");) {

			pstmt.setInt(1, activitys.getId());

			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("update products set name=?,price=? where id=?");) {

			pstmt.setString(1, product.getName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setInt(3, product.getId());
			pstmt.executeUpdate();
			pstmt.clearParameters();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public int searchProduct(Products product) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("select * from products where id=?");) {

			pstmt.setString(1, product.getName());
			pstmt.executeUpdate();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteProduct(Products product) {
		try (Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("delete from products where id=?");) {

			pstmt.setInt(1, product.getId());

			pstmt.executeUpdate();
			pstmt.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Products> listProducts() {
		List<Products> list = new ArrayList<>();
		try (Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from products");) {

			while (rs.next()) {
				Products product = new Products();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				list.add(product);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
