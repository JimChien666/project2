package wey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import max.entity.FilesBean;
import wey.entity.Animals;

public class EDMTableReset {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) throws FileNotFoundException, IOException, SQLException {

		String line = "";

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1","project2","project2");
             Statement stmt = conn.createStatement();
        ) {

			File file = new File("data/animals.csv");
			// 1-2 由"data/animals.csv"逐筆讀入animals表格內的初始資料，然後依序新增
			// 到animals表格中
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					String[] token = line.split(",");
					Animals animals = new Animals();
					FilesBean filesBean = new FilesBean();
					animals.setAnimalId(Integer.parseInt(token[0].trim()));
					animals.setMemberId(Integer.parseInt(token[1].trim()));
					animals.setAcceptionId(token[2].trim());
					animals.setBreedId(Integer.parseInt(token[3].trim()));
					animals.setGender(Integer.parseInt(token[4].trim()));
					animals.setCoatColor(token[5].trim());
					animals.setIsAdoptionAvailable(Integer.parseInt(token[6].trim()));
					Blob blob = SystemUtils2018.fileToBlob(token[7].trim());
					filesBean.setFileBlob(blob);
					
					int n=insertCsvToAnimals(animals,filesBean, conn);
					System.out.println("新增一筆Book紀錄是否成功=" + n);
				}
				// 印出資料新增成功的訊息
				System.out.println("Book資料新增成功");
				conn.close();	
			}
 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				conn.close();	
			}
		}
	}
	public static int insertCsvToAnimals(Animals animals, FilesBean filesBean, Connection conn) {
		int n = 0;
		String sql1= "INSERT INTO animals (MEMBER_ID,ACCEPTION_ID,BREED_ID,GENDER,COAT_COLOR,IS_ADOPTION_AVAILABLE) VALUES (?,?,?,?,?,?)";
		String sql21= "insert into files (ANIMAL_ID, FILE_BLOB) VALUES (?,?)";
		String sql22= "select max(animal_id) from animals";
		try (	PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				PreparedStatement pstmt2 = conn.prepareStatement(sql21);
				){
			pstmt1.setInt(1, animals.getMemberId());
			pstmt1.setString(2, animals.getAcceptionId());
			pstmt1.setInt(3, animals.getBreedId());
			pstmt1.setInt(4, animals.getGender());
			pstmt1.setString(5, animals.getCoatColor());
			pstmt1.setInt(6, animals.getIsAdoptionAvailable());
			n = pstmt1.executeUpdate();
			pstmt1.clearParameters();
			
			ResultSet rs = pstmt1.executeQuery(sql22);
			while (rs.next()) {
				pstmt2.setInt(1, rs.getInt("max(animal_id)"));
				pstmt2.setBlob(2, filesBean.getFileBlob().getBinaryStream());
				pstmt2.executeUpdate();
				pstmt2.clearParameters();
			}
		    return n;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}	
}