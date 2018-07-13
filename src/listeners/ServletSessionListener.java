package listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class ServletSessionListener implements HttpSessionListener {

	public static int onlineUsers=0;
    
    public void sessionCreated(HttpSessionEvent arg0)  {   onlineUsers=onlineUsers+1; }

	
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         
    	System.out.println("destroyed call");
    	arg0.getSession().invalidate();
    	onlineUsers=onlineUsers-1;
    }
	
}
