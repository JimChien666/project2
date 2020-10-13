package jim;

import javax.servlet.http.*;

public class SessionCounter implements HttpSessionListener 
{
   public static int activeSessions = 0;

   public SessionCounter() 
   {
	super();
   }

   public void sessionCreated(HttpSessionEvent evt) 
   {
	  activeSessions++;
	  System.out.println("No. of active sessions at:"+ 
			   new java.util.Date()+" : "+activeSessions);
   }      

   public void sessionDestroyed (HttpSessionEvent evt) 
   {
	  activeSessions--;
     System.out.println("Sessions desdroyed at:"+ 
			   new java.util.Date()+"  "+"session id: " + evt.getSession().getId());
   }   
}
