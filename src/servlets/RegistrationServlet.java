package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DatabaseOperations;
import pojo.Account;
import utilities.MapToPojo;
import utilities.StringUtilities;
import validations.AccountUniqenessChecker;



public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		
		Account pj=MapToPojo.convertToPojo(request.getParameterMap(),true);
		String st = AccountUniqenessChecker.checkUnique(pj);
		
		if(StringUtilities.isEmpty(st))
		{
			DatabaseOperations.create(pj);
			alertMsg(out, "Sucessful! now you can login","index");
		}
		else
			alertMsg(out, st, "register");
	    
	}
	
	public void alertMsg(PrintWriter out,String msg,String location)
	{
		out.println("<script type=\"text/javascript\">");
		out.println("alert('"+msg+"');");
		out.println("location='"+location+".jsp';");
		out.println("</script>");
	}

}
