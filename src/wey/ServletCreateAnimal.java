package wey;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletCreateAnimal")
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
			int animalId = Integer.parseInt(request.getParameter("animalId").trim());
			int memberId = Integer.parseInt(request.getParameter("memberId").trim());
			String acceptionId = request.getParameter("acceptionId").trim();
			int breedId = Integer.parseInt(request.getParameter("breedId").trim());
			int gender = Integer.parseInt(request.getParameter("gender").trim());
			String coatColor = request.getParameter("coatColor").trim();
			int isAdoptionAvailable = Integer.parseInt(request.getParameter("isAdoptionAvailable").trim());
			String note = request.getParameter("note").trim();
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
			
			ValueObjectAnimal reg_Animal = new ValueObjectAnimal(animalId, memberId, acceptionId, breedId, gender, coatColor, isAdoptionAvailable, note, createdAt, updatedAt, deletedAt);
			DaoAnimal daoAnimal = new DaoAnimal();
			if (daoAnimal.createAnimal(reg_Animal))
	        {
	          System.out.println("Get some SQL commands done!");
	          request.getSession(true).invalidate();
	          request.getRequestDispatcher("/ServletReadAnimal").forward(request,response);
	        }
	}

}
