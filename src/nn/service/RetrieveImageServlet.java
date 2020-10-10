package nn.service;

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

import nn.dao.ShowIndexDao;

// 
// 程式功能：
// 本Servlet 類別會依據傳入的主鍵呼叫Service元件以讀取該主鍵所對應的紀錄，取出該紀錄內的BLOB欄，
// 進而讀取存放在BLOB欄內的圖片資料，然後傳回給提出請求的瀏覽器。

@WebServlet("/nn/service/RetrieveImageServlet")
public class RetrieveImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InputStream is = null;
	String mimeType = null;
	OutputStream os = null;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int nId = Integer.parseInt(id);
		ShowIndexDao dao = new ShowIndexDao();
		try {
			Blob blob = dao.getFileBlob(nId);
			if (blob != null) {

				is = blob.getBinaryStream();
	
				mimeType = getServletContext().getMimeType("NN.jpg");
				response.setContentType(mimeType);
				os = response.getOutputStream();	
				int len = 0;
				byte[] bytes = new byte[512];
				while ((len = is.read(bytes)) != -1) {
					os.write(bytes, 0, len);
				}	
			}
			if (is != null) 
				is.close();
			if (os != null) 
				os.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("_00_init.util.RetrieveImageServlet#doGet()發生SQLException: " + ex.getMessage());
		}
//		finally{
//			if (is != null) 
//				is.close();
//			if (os != null) 
//				os.close();
//		}
	}

}