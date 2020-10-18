package nn.controler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import nn.dao.InsertLikeDao;
import nn.vo.FavoriteBean;

/**
 * Servlet implementation class AttractionLikeServlet
 */
@WebServlet("/nn/controler/AttractionLikeServlet")
public class AttractionLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String attractionIdStr = request.getParameter("id");
		int attractionId = Integer.parseInt(attractionIdStr);
		PrintWriter out=response.getWriter();
		InsertLikeDao insertLikeDao = new InsertLikeDao();
		HttpSession session = request.getSession(false);
		MemberBean member = (MemberBean) session.getAttribute("LoginOK");
		if (member == null) {
			out.write("no");
			out.flush();
	        out.close();
			return;
		}
		int memberId = member.getId();
		if(insertLikeDao.checkFavoriteStatus(memberId, attractionId) == 1) {
			
		}
		
		
		FavoriteBean favorite = new FavoriteBean(attractionId, memberId, 1);
		
		insertLikeDao.insertFavorite(favorite);
		
        out.write("yes");
        out.flush();
        out.close();
	}

}
