package nn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import global.util.HibernateUtil;
import team6.nn.dao.GetOneAttractionTypeDataDAO;
import team6.nn.entity.Attractions;

/**
 * Servlet implementation class ShowOneTypeAttractionServlet
 */
@WebServlet("/ShowOneTypeAttractionServlet")
public class ShowOneTypeAttractionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
		String attrIdstr = request.getParameter("attrId");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		int attrId = Integer.parseInt(attrIdstr);
		GetOneAttractionTypeDataDAO getOneAttractionTypeDataDAO = new GetOneAttractionTypeDataDAO(session);
		List<Attractions> selectOneTypeAttraction = getOneAttractionTypeDataDAO.selectOneTypeAttraction(attrId);
		System.out.println(selectOneTypeAttraction.size());
		for(Attractions attractionIntroduction:selectOneTypeAttraction) {
			request.setAttribute("attractionIntroduction", attractionIntroduction);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/nn/block.jsp");
			dispatcher.include(request, response);
		}
		
		
//		ShowAttractionInfosDao dao = new ShowAttractionInfosDao();
//		List<AttractionIntroduction> AttractionIntroductionList =  dao.getAttractionIntroductionList("", attrId, 0, 3);
//		for(AttractionIntroduction attractionIntroduction:AttractionIntroductionList) {
//			request.setAttribute("attractionIntroduction", attractionIntroduction);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/nn/block.jsp");
//	        dispatcher.include(request, response);
//		}
	}


}
