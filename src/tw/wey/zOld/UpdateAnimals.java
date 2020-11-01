package tw.wey.zOld;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import global.service.SystemUtils2018;
import wey.GlobalService;
import wey.entity.Animals;

@WebServlet("/UpdateAnimals")
@MultipartConfig(location = "", 
fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 500, 
maxRequestSize = 1024 * 1024 * 500 * 5)
public class UpdateAnimals extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Animals animals;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/ReadAllAnimals").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (!session.isNew())  {
			animals = (Animals) session.getAttribute("animals");
		} else {
			animals = new Animals();
		}
		String pageNo = "1";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
		int animalId = 0;
		int memberId = 0;
		String acceptionId = "";
		int breedId = 0;
		int gender = 0;
		String coatColor = "";
		int isAdoptionAvailable = 0;
		String note = "";
		Date createdAt = null;
		Date updatedAt = null;
		Date deletedAt = null;
		
		Blob fileBlob = null;
		
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
						animals.setMemberId(memberId);
						request.setAttribute("memberId", memberId);
					}else if (fldName.equals("acceptionId")) {
						acceptionId = value;
						animals.setAcceptionId(acceptionId);
						request.setAttribute("acceptionId", acceptionId);
					}else if (fldName.equals("breedId")) {
						breedId = Integer.parseInt(value);
						animals.setBreedId(breedId);
						request.setAttribute("breedId", breedId);
					}else if (fldName.equals("gender")){
						gender = Integer.parseInt(value);
						animals.setGender(gender);
						request.setAttribute("gender", gender);
					}else if (fldName.equals("coatColor")) {
						coatColor = value;
						animals.setCoatColor(coatColor);
						request.setAttribute("coatColor", coatColor);
					}else if (fldName.equals("isAdoptionAvailable")) {
						isAdoptionAvailable = Integer.parseInt(value);
						animals.setIsAdoptionAvailable(isAdoptionAvailable);
						request.setAttribute("isAdoptionAvailable", isAdoptionAvailable);
					}else if (fldName.equals("note")) {
						note = value;
						animals.setNote(note);
						request.setAttribute("note", note);
					}
				}else {
					fileName = GlobalService.getFileName(p);
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					} else {
						sizeInBytes = -1;
					}
				}
			}
		}
		if (sizeInBytes != -1){
				fileBlob = SystemUtils2018.fileToBlob(is, sizeInBytes);
		}
		Animals newAnimals = new Animals(animals.getAnimalId(), memberId, acceptionId, breedId, gender, coatColor, isAdoptionAvailable, note, createdAt, updatedAt, deletedAt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
