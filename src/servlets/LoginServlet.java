package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.DatabaseOperations;
import pojo.Account;
import utilities.LogedUsersData;
import utilities.StringUtilities;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //public static int logedUsers=0;   
    
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList list=(ArrayList)request.getAttribute("list");
		Account pj = (Account)request.getAttribute("account");
		
		try
		{
			List<Account> data =  (List<Account>) DatabaseOperations.read(pj.getUsername(), pj.getPassword(),Account.class);
			HttpSession session=request.getSession();
				
			 if (data.size()!=0) {
				 		
	                for (Account user : data) {
	 
	                  session.setAttribute("id", user.getId());
	                  session.setAttribute("fullName", user.getFullname());
	                  session.setAttribute("username", user.getUsername());
	                  session.setAttribute("email", user.getEmail());
	                  session.setAttribute("mobile", user.getMobile());
	                  session.setAttribute("password", user.getPassword());
	                  
	                  LogedUsersData.mp.put(session.getAttribute("id"), session.getAttribute("username"));              
	                  response.sendRedirect("home.jsp");
	                  
	                }
	         }
			 
			 else
			 {
				 
				    PrintWriter out=response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('no such user found');");
					out.println("location='index.jsp';");
					out.println("</script>");
				 
			 }
			 
		}catch(Exception ex)
		{
			System.out.println("exception in login servlet");
			ex.printStackTrace();
		}
	}

}
