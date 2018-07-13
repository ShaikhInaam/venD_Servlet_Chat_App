<%@page import="utilities.LogedUsersData"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="utilities.LogedUsersData" %>
<%@page import="servlets.LoginServlet"%>
<%@page import="listeners.ServletRequestListener"%>
<%@page import="listeners.ServletSessionListener"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="pragma" content="nocache">
<title>Home</title>



</head>
<body>

	<%
	
	if(session.getAttribute("username")==null)
 	{
		
 		response.sendRedirect("index.jsp");
 	}
	
	
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
	
	%>

	<table aligh="center"  border = "4">
	
		<tr>
   		 <th>ID</th>
  		 <th>Full Name</th> 
   		 <th>Username</th>
   		 <th>Email</th>
   		 <th>Cell#</th>
   		 <th>Password</th>
  		</tr>
  		
  		<tr>
  		 <td><%=session.getAttribute("id") %>  </td>
  		 <td><%=session.getAttribute("fullName") %>  </td>
  		 <td><%=session.getAttribute("username") %>  </td>
  		 <td><%=session.getAttribute("email") %>  </td>
  		 <td><%=session.getAttribute("mobile") %>  </td>
  		 <td><%=session.getAttribute("password") %>  </td>
  		 
  		</tr>
	
	</table>
	
	<br></br>
	
	
	<table aligh="center" border = "4">
	
		<tr>
   		 <th>Users Online</th>
  		 <th>Requests Served Yet</th> 
  		 <th>Loged Users</th>
   		 
  		</tr>
  		
  		<tr>
  		 <td><%=ServletSessionListener.onlineUsers %>  </td>
  		 <td><%=ServletRequestListener.requests %> </td>
  		 <td><%=LogedUsersData.mp.size() %></td>
  		</tr>
	
	</table>	
	
	<br></br>
	
	<table aligh="center" border = "4">
	
	<tr><th>Loged usernames</th></tr>
	
	<%
	 Map<Object,Object> map=LogedUsersData.mp;
	for (Map.Entry<Object, Object> entry : map.entrySet())
	{
		
	%>
	
	<tr><td><%=entry.getValue() %></td></tr>
	
	<%} %>
	
	</table>
	
	<br></br>
	
	<form>
        <input id="message" type="text">
        <input onclick="wsSendMessage();" value="Echo" type="button">
        <input onclick="wsCloseConnection();" value="Disconnect" type="button">
    </form>
	
	<br></br>
	
	<textarea id="echoText" rows="5" cols="30"></textarea>
	
	
	
	<br></br>
	<form method="post" action="LogoutServlet">
	
		<button name="logout" type="submit" value="logout">logout</button>
	
	</form>
	
	<script>
	var webSocket = new WebSocket("ws://localhost:8080/ServletsDemo/websocketendpoint");
	 var echoText = document.getElementById("echoText");
	 echoText.value = "";
	 var message = document.getElementById("message");
	 
	 webSocket.onopen = function(message){ 
		 wsOpen(message);
		 };
		 
	 webSocket.onmessage = function(message){ 
		 wsGetMessage(message);
		 };
		 
	 webSocket.onclose = function(message){ 
		 wsClose(message);
		 };
		 
	 webSocket.onerror = function(message){ 
		 wsError(message);
		 };
		 
		 
		function wsSendMessage(){
			var name="<%=session.getAttribute("fullName")%>"
			webSocket.send(name+' : '+message.value);
			echoText.value +=name+' : '+ message.value + "\n";
			message.value = "";
			}
		 
		function wsCloseConnection(){
			webSocket.close();
			}
		
		function wsGetMessage(message){
			echoText.value += message.data + "\n";
			alert(message.data);
			}
		
		function wsClose(message){
			echoText.value += "Disconnect ... \n";
			}
			  
		function wserror(message){
			echoText.value += "Error ... \n";
			}
	
</script>

</body>
</html>