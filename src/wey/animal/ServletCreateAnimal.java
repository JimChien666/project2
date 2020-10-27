package wey.animal;

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

import wey.GlobalService;
import wey.SystemUtils2018;

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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/ReadAllAnimals").forward(request,response);
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
						if (fldName.equals("memberId")) {
							memberId = Integer.parseInt(value);
							request.setAttribute("memberId", memberId);
						}else if (fldName.equals("acceptionId")) {
							acceptionId = value;
							request.setAttribute("acceptionId", acceptionId);
						}else if (fldName.equals("breedId")) {
							breedId = Integer.parseInt(value);
							request.setAttribute("breedId", breedId);
						}else if (fldName.equals("gender")){
							gender = Integer.parseInt(value);
							request.setAttribute("gender", gender);
						}else if (fldName.equals("coatColor")) {
							coatColor = value;
							request.setAttribute("coatColor", coatColor);
						}else if (fldName.equals("isAdoptionAvailable")) {
							isAdoptionAvailable = Integer.parseInt(value);
							request.setAttribute("isAdoptionAvailable", isAdoptionAvailable);
						}else if (fldName.equals("note")) {
							note = value;
							request.setAttribute("note", note);
						}
					}else {
						fileName = GlobalService.getFileName(p);
						fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}
				}
			}
			//新增檔案到files資料庫
			String fileType = "";
			String fileUrl = "";
			Blob blob = SystemUtils2018.fileToBlob(is, sizeInBytes);
			
			DaoAnimal daoAnimal = new DaoAnimal();
			ValueObjectAnimal valueObjectAnimal = new ValueObjectAnimal(animalId, memberId, acceptionId, breedId, gender, coatColor, isAdoptionAvailable, note, createdAt, updatedAt, deletedAt, fileType, fileUrl, blob);
			int newAnimalId = daoAnimal.createAnimal(valueObjectAnimal);
			if (newAnimalId>0)
			{
				System.out.println("Create animal No."+newAnimalId+".");
				request.getSession(true).invalidate();
				request.getRequestDispatcher("/ReadAllAnimals").forward(request,response);
			}
			
			//TODO 合併到上面
			DaoFilesOfAnimal daoFilesOfAnimal = new DaoFilesOfAnimal();
			ValueObjectFilesOfAnimal valueObjectFilesOfAnimal = new ValueObjectFilesOfAnimal(fileType, fileUrl, newAnimalId, blob);
			boolean success = daoFilesOfAnimal.createFilesOfAnimal(valueObjectFilesOfAnimal);
			if (success) {
				System.out.println("File of animal No."+newAnimalId+" successfully saved to DB.");
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.println("ServletCreateAnimal error");
			RequestDispatcher rd = request.getRequestDispatcher("/wey/animal/CreateAnimal.jsp");
			rd.forward(request, response);
		}
	}

}
