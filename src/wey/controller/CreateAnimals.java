package wey.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import global.util.HibernateUtil;
import nn.entity.Files;
import wey.GlobalService;
import wey.SystemUtils2018;
import wey.entity.Animals;

@WebServlet("/CreateAnimals")
@MultipartConfig(location = "", 
fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 500, 
maxRequestSize = 1024 * 1024 * 500 * 5)
public class CreateAnimals extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/ReadAllAnimals").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
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
			
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session hSession = factory.getCurrentSession();
			
			Animals animals = new Animals(animalId, memberId, acceptionId, breedId, gender, coatColor, isAdoptionAvailable, note, createdAt, updatedAt, deletedAt);
			fileBlob = SystemUtils2018.fileToBlob(is, sizeInBytes);
			Set<Files> files = new HashSet<Files>();
			files.add(new Files("image", fileBlob));
			animals.setFiles(files);
			hSession.save(animals);
			request.getRequestDispatcher("/ReadAllAnimals").forward(request,response);
			
		} catch (Exception e) {
			System.out.println("AnimalsController error");
			e.printStackTrace(); 
			RequestDispatcher rd = request.getRequestDispatcher("/wey/animal/CreateAnimal.jsp");
			rd.forward(request, response);
		}
	}

}
