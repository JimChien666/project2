package nn.controler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import nn.dao.InsertAttractionDao;
import nn.service.GlobalService;
import nn.vo.AttractionBean;
import nn.vo.AttractionTagRefsBean;
import nn.vo.AttractionTypeBean;
import nn.vo.CityBean;
import nn.vo.FileBean;
import nn.vo.TagBean;

/**
 * Servlet implementation class CheckInsertAttractionServlet
 */
@WebServlet("/nn/controler/CheckInsertAttractionServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class CheckInsertAttractionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	private static final String SAVE_DIR = "nn/uploadFiles";
	
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
		InsertAttractionDao dao = new InsertAttractionDao();
		List<CityBean> cityList = dao.getCityList();
		List<TagBean> tagList = dao.getTagList();
		List<AttractionTypeBean> attractionTypeList = dao.getAttractionTypeList();
		HttpSession session = request.getSession();
		session.setAttribute("attractionTypeList", attractionTypeList);
		session.setAttribute("cityList", cityList);
		session.setAttribute("tagList", tagList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/nn/insertAttraction.jsp");
		rd.forward(request, response);
		
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    String appPath = request.getServletContext().getRealPath("");
	    String savePath = appPath + File.separator + SAVE_DIR;
	    String writePath = request.getContextPath() + File.separator + SAVE_DIR;
	    Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);
		HttpSession session = request.getSession();
		File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
		
		
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("MsgMap", errorMsg);
		
		String type = "";
		String name = "";
		String email = "";
		String city = "";
		String address = "";
		String tel = "";
		String content = "";
		String contentFileName = "";
		String coverFileUrl = "";
		String coverFileName = "";
		List<List<Object>> contentUrlList = new ArrayList<>();
		long contentSizeInBytes = 0;
		InputStream contentIs = null;
		long coverSizeInBytes = 0;
		InputStream coverIs = null;
		
		String[] tagList = request.getParameterValues("tag");
		
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				if (p.getContentType() == null) {
					if (fldName.equals("type")) {
						type = value;
					} else if (fldName.equals("name")) {
						name = value;
					} else if (fldName.equals("email")) {
						email = value;
					} else if (fldName.equals("city")) {
						city = value;
					} else if (fldName.equals("address")) {
						address = value;
					} else if (fldName.equals("tel")) {
						tel = value;
					} else if (fldName.equals("content")) {
						content = value;
					}
				} else if (p.getContentType() != null && fldName.equals("coverimg")) {
					
					// 取出圖片檔的檔名
					coverFileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					coverFileName = GlobalService.adjustFileName(coverFileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (coverFileName != null && coverFileName.trim().length() > 0) {
						
						coverSizeInBytes = p.getSize();
						coverIs = p.getInputStream();
						p.write(savePath + File.separator + coverFileName);
						coverFileUrl = File.separator + SAVE_DIR + File.separator + coverFileName;
						
					} else {
						errorMsg.put("errCoverImg", "必須挑選圖片檔");
					}
				} else {
					
					// 取出圖片檔的檔名
					List<Object> list = new ArrayList<>();
					contentFileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					contentFileName = GlobalService.adjustFileName(contentFileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (contentFileName != null && contentFileName.trim().length() > 0) {
						contentSizeInBytes = p.getSize();
						
						contentIs = p.getInputStream();
						p.write(savePath + File.separator + contentFileName);
						
						list.add(contentSizeInBytes);
						list.add(contentIs);
						
						list.add(File.separator + SAVE_DIR + File.separator + contentFileName);
						contentUrlList.add(list);
					} else {
						errorMsg.put("errContentImg", "必須挑選圖片檔");
					}
				}
			}
			
			if (type == null || type.trim().length() == 0) {
				errorMsg.put("errorType", "類型欄必須輸入");
			}
			if (name == null || name.trim().length() == 0) {
				errorMsg.put("errorName", "名稱欄必須輸入");
			}
			if (email == null || email.trim().length() == 0) {
				errorMsg.put("errorEmail", "email欄必須輸入");
			}
			if (city == null || city.trim().length() == 0) {
				errorMsg.put("errorCity", "城市欄必須輸入");
			}
			if (address == null || address.trim().length() == 0) {
				errorMsg.put("errorAddress", "地址欄必須輸入");
			}
			if (tel == null || tel.trim().length() == 0) {
				errorMsg.put("errorTel", "電話欄必須輸入");
			}
			if (content == null || content.trim().length() == 0) {
				errorMsg.put("errorContent", "內容欄必須輸入");
			}
		}
		else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}
		if (!errorMsg.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			System.out.println(errorMsg);
			RequestDispatcher rd = request.getRequestDispatcher("/nn/insertAttraction.jsp");
			rd.forward(request, response);
			return;
		}
		
		//寫進資料庫
		System.out.println("寫進資料庫");
		//insert attraction
		int typeId=Integer.parseInt(type);
		int cityId=Integer.parseInt(city);
		Date createAt = new Date();
		AttractionBean attractionBean = new AttractionBean(name, 1, typeId, content, tel, email, address, cityId, createAt);
		InsertAttractionDao dao = new InsertAttractionDao();
		int attractionId = dao.insertAttraction(attractionBean);
		Blob coverBlob = null;
		try {
			coverBlob = nn.service.SystemUtils2018.fileToBlob(coverIs, coverSizeInBytes);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//insert COVERFILES
		FileBean coverFileBean = new FileBean("image", coverFileUrl, attractionId, 0, coverBlob);
		FileBean contetnFileBean = new FileBean();
		contetnFileBean.setFileType("image");
		contetnFileBean.setContentAttractionId(attractionId);
		dao.insertCoverAttractionFile(coverFileBean);
		for(List<Object> list:contentUrlList) {
			contetnFileBean.setFileUrl((String)list.get(2));
			Blob contentBlob = null;
			try {
				contentBlob = nn.service.SystemUtils2018.fileToBlob((InputStream)list.get(1), (Long)list.get(0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contetnFileBean.setFileBlob(contentBlob);
			dao.insertContentAttractionFile(contetnFileBean);
		}
		//inset attraction_tag_refs
		for(String tagIdString:tagList) {
			int tagId = Integer.parseInt(tagIdString);
			AttractionTagRefsBean attractionTagRefs = new AttractionTagRefsBean(tagId, attractionId);
			dao.insertAttractionTagRefs(attractionTagRefs);
		}
		
        PrintWriter out = response.getWriter();
        out.println("<script type=text/javascript>alert('hi');</script>");
        response.sendRedirect("/Project2/nn/controler/ShowIndexServlet");
		return;
	}
}