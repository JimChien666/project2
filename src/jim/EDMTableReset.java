package jim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jim.model.ProductsBean;

public class EDMTableReset {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) throws FileNotFoundException, IOException, SQLException {

		String line = "";

		int count = 0;
//		BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
//        dataSource.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
//        dataSource.setUsername("project2");
//        dataSource.setPassword("project2");
//        dataSource.setMaxTotal(50); //設定最多connection上線,超過使用量必須等待
//        dataSource.setMaxIdle(50);   //設定最多idle的connection,超過的connection會被執行connection.close()

        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1","project2","project2");
             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("select * from pm25 ");
//             PreparedStatement pstmt = conn.prepareStatement("update pm25 set concentration=? where sitename=?")
        ) {


			File file = new File("data/white.csv");
			// 1-2 由"data/book.dat"逐筆讀入Book表格內的初始資料，然後依序新增
			// 到Book表格中
			try (FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "UTF8");
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					System.out.println("line=" + line);
					// 去除 UTF8_BOM: \uFEFF
//					if (line.startsWith(UTF8_BOM)) {
//						line = line.substring(1);
//					}
					String[] token = line.split(",");
					ProductsBean product = new ProductsBean();					
					product.setName(token[0].trim());
					product.setPrice(Integer.parseInt(token[1].trim()));		
					// 讀取圖片檔
					Blob blob = SystemUtils2018.fileToBlob(token[2].trim());
					product.setImg(blob);
//					product.setImg(token[2].trim());
					product.setDescript(token[3].trim());
					product.setQuantity(0);
					product.setSpecialPrice(0);
					product.setRewardpoints(null);
					product.setIsThumb(false);
					product.setMemberId(0);
					product.setAnimalTypeId(0);
					product.setCategoryId(0);
//					book.setFileName(SystemUtils2018.extractFileName(token[5].trim()));
//					book.setBookNo(token[6].trim());
//					book.setStock(Integer.parseInt(token[7].trim()));
//					book.setCategory(token[8].trim());
					int n=insertCsvToProducts(product,conn);
//					int n = saveBook(book, con);
					System.out.println("新增一筆Book紀錄是否成功=" + n);
				}
				// 印出資料新增成功的訊息
				System.out.println("Book資料新增成功");
				conn.close();	
			}
			// 2. BookCompany表格
 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				conn.close();	
			}
		}
	}
	public static int insertCsvToProducts(ProductsBean products, Connection conn) {
		int n = 0;
		String sqlS= "INSERT INTO products (name,price,img,descript,quantity,special_price,rewardpoints,is_thumb,member_id,animal_type_id,category_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try (	PreparedStatement pstmt = conn.prepareStatement(sqlS);
				){
				
		      pstmt.setString(1, products.getName());
		      pstmt.setInt(2, products.getPrice());
//		      pstmt.setString(3, products.getImg());
		      pstmt.setBlob(3, products.getImg().getBinaryStream());
		      pstmt.setString(4, products.getDescript());
		      pstmt.setInt(5, products.getQuantity());
		      pstmt.setInt(6, products.getSpecialPrice());
		      pstmt.setString(7, products.getRewardpoints());		      
		      pstmt.setBoolean(8, products.getIsThumb());
		      pstmt.setInt(9, products.getMemberId());
		      pstmt.setInt(10, products.getAnimalTypeId());
		      pstmt.setInt(11, products.getCategoryId());
//		      pstmt.setInt(12, products.getId());
		      n=pstmt.executeUpdate();
			  pstmt.clearParameters();
		    return n;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}	
}