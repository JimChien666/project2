package jim;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import jim.entity.Products;

@WebServlet("/UpdateProduct")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
    public UpdateProduct() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    // To prevent caching 
	    response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
	    response.setHeader("Pragma","no-cache"); // HTTP 1.0
	    response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
	    System.out.println(request.getParameter("submit"));
//	   if (request.getParameter("submit")!=null) {
		   gotoSubmitProcess(request, response);
//	   }
	}
	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
	    
        PrintWriter out = response.getWriter();
        int id;
		String name = ""; 
		int price = 0;
//		String img =null;
		Blob img =null;
		String descript = ""; 
		int quantity = 0;
		int specialPrice = 0;
		String rewardpoints = ""; 
		boolean isThumb = false;
		int memberId = 0; 
		int animalTypeId = 0; 
		int categoryId = 0;
		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;
	    
	    id = Integer.parseInt(request.getParameter("id").trim()); //把空白去掉!!
		name = request.getParameter("name").trim(); //把空白去掉!!
	    price = Integer.parseInt(request.getParameter("price").trim()); //把空白去掉!!
	    
		// 讀取圖片檔
		Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				// 1. 讀取使用者輸入資料
				if (p.getContentType() != null) {
					// 取出圖片檔的檔名
					fileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					} else {
						out.write( "必須挑選圖片檔");
					}					
				}				
			}	
		}
		try {
			//轉成二進位，塞進Img
			img = SystemUtils2018.fileToBlob(is, sizeInBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	    descript = request.getParameter("descript").trim(); //把空白去掉!!
	    quantity = Integer.parseInt(request.getParameter("quantity").trim());

	    Products product =  new Products(id,name,price,img,descript,quantity,specialPrice,
	    		rewardpoints,isThumb,memberId,animalTypeId,categoryId);
	    JdbcDao jdbcdao = new JdbcDao();

	    if (jdbcdao.updateProduct(product))  //更新成功
        {
          System.out.println("Get some SQL commands done!");
//          request.getSession(true).invalidate();
          response.sendRedirect("/Project2/jim/Thanks.jsp");
//          RequestDispatcher rd = request.getRequestDispatcher("/jim/Thanks.jsp");
//          rd.forward(request, response);
        }else {
        	 out.println("對不起，更新產品失敗!");
        }
	    
	  }    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


}
