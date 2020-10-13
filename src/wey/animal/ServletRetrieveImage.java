package wey.animal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 程式功能：
// 本Servlet 類別會依據傳入的主鍵呼叫Service元件以讀取該主鍵所對應的紀錄，取出該紀錄內的BLOB欄，
// 進而讀取存放在BLOB欄內的圖片資料，然後傳回給提出請求的瀏覽器。

@WebServlet("/ServletRetrieveImage")
public class ServletRetrieveImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入ServletRetrieveImage");
		OutputStream os = null;
		InputStream is = null;
		String fileName = null;
		String mimeType = null;
		Blob blob = null;
		try {
			// 讀取瀏覽器傳送來的主鍵
			String id = request.getParameter("id");
			// 讀取瀏覽器傳送來的type，以分辨要處理哪個表格
			String type = request.getParameter("type"); 
			System.out.println(type);
			//switch(type.toUpperCase()){}希望is有值
			switch(type.toUpperCase()){
				case "ANIMAL":
					
					DaoAnimal daoAnimal = new DaoAnimal();
					int nId = 0;
					try {
						nId = Integer.parseInt(id);
					} catch(NumberFormatException ex) {
						System.out.println("ServletRetrieveImage line42");
						ex.printStackTrace();
					}
					ValueObjectAnimal valueObjectAnimal = daoAnimal.getAnimal(nId);
					System.out.println(valueObjectAnimal.getFileBlob());
					if (valueObjectAnimal != null) {
						blob = valueObjectAnimal.getFileBlob();
						if (blob != null) {
							is = blob.getBinaryStream();
						}
						fileName = valueObjectAnimal.getFileName();
					}
					break;
//				case "MEMBER":
//					MemberService memberService = new MemberServiceImpl();
//					MemberBean bean2 = memberService.queryMember(id);
//					if (bean2 != null) {
//						blob = bean2.getMemberImage();
//						if (blob != null) { 
//							is = blob.getBinaryStream();
//						}
//						fileName = bean2.getFileName();
//					}
//					break;
			}
			
			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)	
			if (is == null) {
				fileName = "NoImage.png" ; 
				is = getServletContext().getResourceAsStream(//getResourceAsStream開啟一個檔案
						"jim/images/" + fileName);// /images/為webapp/images/
			}
			
			
			// 由圖片檔的檔名來得到檔案的MIME型態，mimeType又叫media type、content type
			mimeType = getServletContext().getMimeType(".jpg");  //若fileName為apple.jpg，會回傳image/jpg
			// 設定輸出資料的MIME型態
			response.setContentType(mimeType);
			// 取得能寫出非文字資料的OutputStream物件
			os = response.getOutputStream();	
			// 由InputStream讀取位元組，然後由OutputStream寫出
			int len = 0;
			byte[] bytes = new byte[8192];
			while ((len = is.read(bytes)) != -1) {
				os.write(bytes, 0, len);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ImageServletRetrieve#doGet()發生SQLException: " + ex.getMessage());
		} finally{
			if (is != null) 
				is.close();
			if (os != null) 
				os.close();
			
		}
	}
}