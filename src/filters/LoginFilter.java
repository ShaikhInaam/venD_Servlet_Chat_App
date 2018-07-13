package filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import hashing.MD5Coverter;
import pojo.Account;
import utilities.StringUtilities;
import validations.RegularExpressions;


@WebFilter("/LoginServlet")
public class LoginFilter implements Filter {

   	public void destroy() {}
   	
   	public void init(FilterConfig fConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			
		ArrayList list=new ArrayList();
	
		Map params = request.getParameterMap();
		Iterator i = params.keySet().iterator();
		  while ( i.hasNext() )
		  {
		    String key = (String) i.next();
		    String value = ((String[]) params.get( key ))[ 0 ];
		    list.add(value);
		  } 
		    
		String st = validateData(list);   
		if(StringUtilities.isEmpty(st))
		{
			Account pj=new Account();
			pj.setUsername(list.get(0).toString());
			pj.setPassword(MD5Coverter.convert(list.get(1).toString()));
				 
			request.setAttribute("account", pj);
			chain.doFilter(request, response);
		} 
		else
		{
			errorOut(response, st);
		}
		    
	}
	
	public String validateData(ArrayList list)
	{
			boolean bx=true;
			String st="";
			
			bx=RegularExpressions.validate(list.get(0).toString(),"[a-zA-z]{5,20}");
			if (!bx)
			{
				st = "username can not be null and contain numbers or spaces\\nLimit: 5 to 20 characters ";
				return st;
			}
		
			bx=RegularExpressions.validate(list.get(1).toString(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$");
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
			out.println("location='index.jsp';");
			out.println("</script>");
			
		}catch(Exception ex) {ex.printStackTrace();}	
	}
}
