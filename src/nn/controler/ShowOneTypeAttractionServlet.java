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

import nn.dao.ShowIndexDao;
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
		ShowIndexDao dao = new ShowIndexDao();
		PrintWriter out = response.getWriter();
		List<AttractionIntroduction> AttractionIntroductionList =  dao.getAttractionIntroductionList(atrId);
		for(AttractionIntroduction attractionIntroduction:AttractionIntroductionList) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("attractionIntroduction", attractionIntroduction);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/nn/block.jsp");
	        dispatcher.include(request, response);
		}
	}


}
