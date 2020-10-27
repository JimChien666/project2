package nn.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import team6.nn.dao.GetAllAttractionsDataDAO;
import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Attractions;
import team6.nn.entity.Citys;

/**
 * Servlet implementation class ShowAllAttractionsServlet
 */
@WebServlet("/ShowAllAttractionsServlet")
public class ShowAllAttractionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
		String attrIdstr = request.getParameter("attrId");
		String pageString = request.getParameter("page");
		int atrId = Integer.parseInt(attrIdstr);
		int page = Integer.parseInt(pageString);
		int showNum = 9;
		int maxPage = 0;
		int totalNum = 0;
		List<Attractions> attractionIntroductionList = null;
		String cityIdString = request.getParameter("cityId");
		String searchStr = request.getParameter("search");
		if (searchStr == null) {
			searchStr ="";
		}
		PrintWriter out = response.getWriter();
		GetAllAttractionsDataDAO dao = new GetAllAttractionsDataDAO(session);
		List<AttractionTypes> attractionTypeList = dao.selectAllAttractionTypes();
		List<Citys> cityList = dao.selectAllCitys();
	    request.setAttribute("attractionTypeList", attractionTypeList);
	    request.setAttribute("cityList", cityList);
	    System.out.println(atrId + "," + page + ", " + searchStr + ", ");
	    if (cityIdString!=null) {
	    	int cityId = Integer.parseInt(cityIdString);
	    	attractionIntroductionList =  dao.selectOneTypeAttractionsForOnePage(atrId,cityId,searchStr, page);
	    	totalNum = dao.selectOneTypeAttractions(atrId,cityId,searchStr).size();
	    }else {
	    	attractionIntroductionList =  dao.selectOneTypeAttractionsForOnePage(atrId,searchStr, page);
	    	totalNum = dao.selectOneTypeAttractions(atrId, searchStr).size();
	    }
	    
	    System.out.println(attractionIntroductionList);
	    System.out.println(totalNum);
		
		if (totalNum%showNum == 0) {
			maxPage = totalNum/showNum;
		} else {
			maxPage = (int)(Math.round((float)totalNum/(float)showNum)) + 1;
		}
		request.setAttribute("totalNum", totalNum);
		RequestDispatcher rd = request.getRequestDispatcher("/nn/ShowAllAttraciotns.jsp");
        rd.include(request, response);
        out.println("<div class='row'  style=' padding: 30px; border-radius: 10px;'>");
        
        
        
        
		for(Attractions attractionIntroduction:attractionIntroductionList) {
			request.setAttribute("attractionIntroduction", attractionIntroduction);
			
			rd = request.getRequestDispatcher("/nn/block.jsp");
			rd.include(request, response);
			
		}
		out.println("</div>");
		request.setAttribute("maxPage", maxPage);
		RequestDispatcher rd2 = request.getRequestDispatcher("/nn/Page.jsp");
		rd2.include(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
