package jim;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This servlet just create a session to illustrate the use of the HttpSessionListener interface implemented by SessionCounter class.
 * 
 */
public class TestServlet extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException 
	{

    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		HttpSession session = req.getSession();
		out.println("Session retrieved. Session ID = "+session.getId());
		out.println("<br>Number of session are "+SessionCounter.activeSessions);		
		out.println("</body>");
		out.println("</html>");
		
	}
}
