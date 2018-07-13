package filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.DatabaseOperations;
import hashing.MD5Coverter;
import pojo.Account;
import utilities.MapToPojo;
import utilities.StringUtilities;
import validations.RegularExpressions;


public class RegisterFilter implements Filter {

	public void destroy() {}
	
	public void init(FilterConfig fConfig) throws ServletException {}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			   
		 Account pj=MapToPojo.convertToPojo(request.getParameterMap(),false);   
		 String st = validateData(pj);   
		 
		 if(StringUtilities.isEmpty(st))	
			 chain.doFilter(request, response);
		 
		 else
			 errorOut(response, st);  
	}

	public String validateData(Account pj)
	{
		
		boolean bx=true;
		String st="";
		
		bx=RegularExpressions.validate(pj.getFullname(),"[a-zA-z- ]{10,50}");
		if (!bx)
		{
			st= "Full name can not be null and contain numbers \\nLimit: 10 to 50 characters";
			return st;
		}
		
		bx=RegularExpressions.validate(pj.getUsername(),"[a-zA-z]{5,20}");
		if (!bx)
		{
			st = "username can not be null and contain numbers or spaces\\nLimit: 5 to 20 characters ";
			return st;
		}
		
		bx=RegularExpressions.validate(pj.getMobile(),"[0-9]{11,15}");
		if (!bx)
		{
			st = "Cell# should be a number\\nLimit: 11 to 15 numbers";
			return st;
		}
		
		bx=RegularExpressions.validate(pj.getPassword(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$");
		if (!bx)
		{
			st = "The password policy is:\\n"+
				 "At least 8 chars\\n"+
				 "Contains at least one digit\\n"+
				 "Contains at least one lower alpha char and one upper alpha char\\n"+
				 "Contains at least one char within a set of special chars (@#%$^ etc.)\\n"+
				 "Does not contain space, tab, etc.\\n"+
				 "Limit: 8 to 15 characters";
			
			return st;
		}
		
		return st;
	}
	
	public void errorOut(ServletResponse response,String msg)
	{
		try
		{
			PrintWriter out=response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('"+msg+"');");
			out.println("location='register.jsp';");
			out.println("</script>");
			
		}catch(Exception ex) {ex.printStackTrace();}	
	}

}
