package websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import servlets.LoginServlet;
import utilities.GetHttpSessionConfigurator;


@ServerEndpoint(value = "/websocketendpoint")
public class WsServer {
	
	private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void onOpen(Session session){
		
		System.out.println("Open Connection ...");
		sessions.add(session);
		System.out.println("Connected : "+sessions.size());
		
	}
	
	     
	
	@OnClose
	public void onClose(Session session){
	    System.out.println("Close Connection ...");
	    sessions.remove(session);
	    System.out.println("Connected : "+sessions.size());
	}
	
	     
	
	@OnMessage
	public void onMessage(String message,Session currentSession)throws Exception{
	    
	    for (Session s : sessions) {
	    	if(!s.equals(currentSession))	
	         s.getBasicRemote().sendText(message);             
	      }	    
	  
	}
	
	 
	
	@OnError
	public void onError(Throwable e){ System.out.println("Error in socket server"); e.printStackTrace(); }

	

}
