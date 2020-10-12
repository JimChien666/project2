package wey;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Collection;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ServletCreateAnimal")
@MultipartConfig(location = "", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024
* 1024 * 500 * 5)
public class ServletCreateAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
	public ServletCreateAnimal() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    // To prevent caching 
	   response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
	   response.setHeader("Pragma","no-cache"); // HTTP 1.0
	   response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
	   
	   if(request.getParameter("submit")!=null) {
		   gotoConfirmProcess(request, response);
	   }
	}
	
	public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		try {
		int animalId = 0;
		int memberId = 0;
		String acceptionId = "";
		int breedId = 0;
		int gender = 0;
		String coatColor = "";
		int isAdoptionAvailable = 0;
		String note = "";
//		int animalId = Integer.parseInt(request.getParameter("animalId").trim());
//		int memberId = Integer.parseInt(request.getParameter("memberId").trim());
//		String acceptionId = request.getParameter("acceptionId").trim();
//		int breedId = Integer.parseInt(request.getParameter("breedId").trim());
//		int gender = Integer.parseInt(request.getParameter("gender").trim());
//		String coatColor = request.getParameter("coatColor").trim();
//		int isAdoptionAvailable = Integer.parseInt(request.getParameter("isAdoptionAvailable").trim());
//		String note = request.getParameter("note").trim();
			
//			String time = "2020-10-8";
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			simpleDateFormat.setLenient(false);
//			Date date = simpleDateFormat.parse(time);
//			Date createdAt = date;
//			Date updatedAt = date;
//			Date deletedAt = date;
			Date createdAt = null;
			Date updatedAt = null;
			Date deletedAt = null;
			String fileName = "";
			long sizeInBytes = 0;
			InputStream is = null;
			Collection<Part> parts = request.getParts();
			
			if (parts != null) {
				for (Part p : parts) {
					String fldName = p.getName();
					String value = request.getParameter(fldName);
					if (p.getContentType() == null) {
						if(fldName.equals("animalId")) {
							animalId = Integer.parseInt(value);
							request.setAttribute("animalId", animalId);
						}else if (fldName.equals("memberId")) {
							memberId = Integer.parseInt(value);
							request.setAttribute("memberId", memberId);
						}else if (fldName.equals("acceptionId")) {
							request.setAttribute("acceptionId", value);
						}else if (fldName.equals("breedId")) {
							request.setAttribute("breedId", Integer.parseInt(value));
						}else if (fldName.equals("gender")){
							request.setAttribute("gender", Integer.parseInt(value));
						}else if (fldName.equals("coatColor")) {
							request.setAttribute("coatColor", value);
						}else if (fldName.equals("isAdoptionAvailable")) {
							request.setAttribute("isAdoptionAvailable", Integer.parseInt(value));
						}else if (fldName.equals("note")) {
							request.setAttribute("note", value);
						}
					}else {
						fileName = GlobalService.getFileName(p);
						fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}
				}
			}
			
			Blob blob = SystemUtils2018.fileToBlob(is, sizeInBytes);
			DaoAnimal daoAnimal = new DaoAnimal();
			ValueObjectAnimal reg_Animal = new ValueObjectAnimal(animalId, memberId, acceptionId, breedId, gender, coatColor, isAdoptionAvailable, note, createdAt, updatedAt, deletedAt);
			if (daoAnimal.createAnimal(reg_Animal))
	        {
	          System.out.println("Get some SQL commands done!");
	          request.getSession(true).invalidate();
	          request.getRequestDispatcher("/ServletReadAnimal").forward(request,response);
	        }
			
		} catch (Exception e) {
			e.printStackTrace(); 
			RequestDispatcher rd = request.getRequestDispatcher("CreateAnimal.jsp");
			rd.forward(request, response);
		}
	}

}
