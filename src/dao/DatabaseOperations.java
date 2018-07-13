package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import pojo.Account;

public class DatabaseOperations {
	
	static SessionFactory sessionFactory;

	 public static void setup() {
	        // code to load Hibernate Session factory
		 
		 final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			        .configure() // configures settings from hibernate.cfg.xml
			        .build();
			try {
			    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			} catch (Exception ex) {
			    StandardServiceRegistryBuilder.destroy(registry);
			}
	    }
	 
	    public static void exit() {
	        // code to close Hibernate Session factory
	    	
	    	sessionFactory.close();
	    }
	 
	    public static void create(Object obj) {
	        // code to save
			 
			    Session session = sessionFactory.openSession();
			    session.beginTransaction();
			 
			    session.save(obj);
			 
			    session.getTransaction().commit();
			    session.close();
			    
			    System.out.println("done");
	    }
	 
	    public static List<?> read(String username,String password, Class c)throws Exception {
	        // code to get
	    	
	    	Session session=sessionFactory.openSession();
	        session.beginTransaction();
	       
	        Criteria cr = session.createCriteria(c);
		    cr.add(Restrictions.eq("username", username));
			cr.add(Restrictions.eq("password", password));
			List users = cr.list();
	          
			session.getTransaction().commit();     
	        session.close();  
	         
	        return users;
	         
	    }
	    
	    public static List<?> checkDuplicate(String data,String code,String className)
	    {
	    	Session session=sessionFactory.openSession();
	        session.beginTransaction();
	        List<?> users =null;
	        
	    	if(code.toLowerCase().equals("username"))
	    		users = (List<?>) session.createQuery("FROM "+className+" users WHERE users.username = '"+data+"'").list();
	    		
	    	else if(code.toLowerCase().equals("email"))
    			users = (List<?>) session.createQuery("FROM "+className+" users WHERE users.email = '"+data+"'").list();
	    	
	    	else if(code.toLowerCase().equals("mobile"))
    			users = (List<?>) session.createQuery("FROM "+className+" users WHERE users.mobile = '"+data+"'").list();
	    	
	    	else
	    	{
	    		try {
	    			throw new NoSuchFieldException("No such field found to check for uniqeness!");
	    		}catch(Exception ex) {System.out.println("Exception in DatabaseOperations class"); ex.printStackTrace();}
	    	}
    			
	    	
	    	session.getTransaction().commit();     
	        session.close();
	    	
	    	return users;
	    }
	 
	    protected void update() {
	        // code to modify
	    }
	 
	    protected void delete() {
	        // code to remove
	    }
	
}
