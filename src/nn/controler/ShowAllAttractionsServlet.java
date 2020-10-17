package nn.controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nn.dao.ShowAttractionInfosDao;
import nn.vo.AttractionIntroduction;
import nn.vo.AttractionTypeBean;
import nn.vo.CityBean;

/**
 * Servlet implementation class ShowAllAttractionsServlet
 */
@WebServlet("/nn/controler/ShowAllAttractionsServlet")
public class ShowAllAttractionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
		String attrIdstr = request.getParameter("attrId");
		String pageString = request.getParameter("page");
		String showNumString = request.getParameter("showNum");
		int atrId = Integer.parseInt(attrIdstr);
		int page = Integer.parseInt(pageString);
		int showNum = Integer.parseInt(showNumString);
		int initNum = (page-1)*showNum;
		int maxPage = 0;
		int totalNum = 0;
		List<AttractionIntroduction> attractionIntroductionList = null;
		String cityIdString = request.getParameter("cityId");
		String searchStr = request.getParameter("search");
		if (searchStr == null) {
			searchStr ="";
		}
		searchStr = searchStr.trim();
		System.out.println(searchStr);
			
		
		
		PrintWriter out = response.getWriter();
		ShowAttractionInfosDao dao = new ShowAttractionInfosDao();
		List<AttractionTypeBean> attractionTypeList = dao.getAttractionTypeList();
		List<CityBean> cityList = dao.getCityList();
	    request.setAttribute("attractionTypeList", attractionTypeList);
	    request.setAttribute("cityList", cityList);
	    if (cityIdString!=null) {
	    	int cityId = Integer.parseInt(cityIdString);
	    	attractionIntroductionList =  dao.getAttractionIntroductionList(searchStr, atrId, initNum, showNum,cityId);
	    	totalNum = dao.getAttractionTypeNum(searchStr, atrId, cityId);
	    }else {
	    	attractionIntroductionList =  dao.getAttractionIntroductionList(searchStr, atrId, initNum, showNum);
	    	totalNum = dao.getAttractionTypeNum(searchStr, atrId);
	    }
	    
		
		if (totalNum%showNum == 0) {
			maxPage = totalNum/showNum;
		} else {
			maxPage = (int)(Math.round((float)totalNum/(float)showNum)) + 1;
		}
		request.setAttribute("totalNum", totalNum);
		RequestDispatcher rd = request.getRequestDispatcher("/nn/ShowAllAttraciotns.jsp");
        rd.include(request, response);
        out.println("<div class='row'  style=' padding: 30px; border-radius: 10px;'>");
        
        
        
        
		for(AttractionIntroduction attractionIntroduction:attractionIntroductionList) {
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
