package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import dao.DatabaseOperations;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    
    public void contextDestroyed(ServletContextEvent arg0)  { }

	
    public void contextInitialized(ServletContextEvent arg0)  { 
        
    	try
    	{
    		System.out.println("starting servlet context Listener.");
    		Thread.sleep(2000);
    		System.out.print(" ..");
    		Thread.sleep(2000);
    		
    		System.out.println("Configuring Hibernate connection to database");
    		Thread.sleep(3000);
    		DatabaseOperations.setup();
    		Thread.sleep(3000);
    		
    		System.out.println("Connection Successfull...");
    		System.out.println("Initializing Application");
    		Thread.sleep(5000);
    		
    	}catch(Exception ex)
    	{
    		System.out.println("Exception in Servlet Context");
    		ex.printStackTrace();
    	}
    	
    }
	
}
