package nn.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nn.dao.ShowAttractionInfosDao;
import nn.vo.AttractionIntroduction;

/**
 * Servlet implementation class ShowOneTypeAttractionServlet
 */
@WebServlet("/nn/controler/ShowOneTypeAttractionServlet")
public class ShowOneTypeAttractionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String attrIdstr = request.getParameter("attrId");
		int atrId = Integer.parseInt(attrIdstr);
		ShowAttractionInfosDao dao = new ShowAttractionInfosDao();
		List<AttractionIntroduction> AttractionIntroductionList =  dao.getAttractionIntroductionList("", atrId, 0, 3);
		for(AttractionIntroduction attractionIntroduction:AttractionIntroductionList) {
			request.setAttribute("attractionIntroduction", attractionIntroduction);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/nn/block.jsp");
	        dispatcher.include(request, response);
		}
	}


}
