package listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletRequestListener
 *
 */
@WebListener
public class ServletRequestListener implements javax.servlet.ServletRequestListener {

	public static int requests=0;
   
    public void requestDestroyed(ServletRequestEvent arg0)  { }

	
    public void requestInitialized(ServletRequestEvent arg0)  { requests++; }
	
}
