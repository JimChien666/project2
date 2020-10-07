package wey;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoReadAnimal {
	private DataSource dataSource;

	public DataSource getDataSource() {
		if (this.dataSource == null) {
			try {
				InitialContext ctxt = new InitialContext();
				DataSource dataSource = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
				return dataSource;
			} catch (NamingException e) {
				System.out.println("MyAdoptions getDatasource出問題");
				e.printStackTrace();
			}
		}
		return dataSource;
	}
	
	public List<ValueObjectAnimal> listAnimals(){
		List<ValueObjectAnimal> list = new ArrayList<>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from ANIMALS order by ANIMAL_ID");
				){
			while(rs.next()) {
				ValueObjectAnimal animals = new ValueObjectAnimal();
				animals.setAnimalId(rs.getInt("ANIMAL_ID"));
				animals.setMemberId(rs.getInt("MEMBER_ID"));
				animals.setAcceptionId(rs.getString("ACCEPTION_ID"));
				animals.setGender(rs.getInt("GENDER"));
				animals.setBreedId(rs.getInt("BREED_ID"));
				animals.setCoatColor(rs.getString("COAT_COLOR"));
				animals.setIsAdoptionAvailable(rs.getInt("IS_ADOPTION_AVAILABLE"));
				animals.setNote(rs.getString("NOTE"));
				list.add(animals);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("listAdoptionRecords出問題");
			e.printStackTrace();
		}
		return list;
	}
}
