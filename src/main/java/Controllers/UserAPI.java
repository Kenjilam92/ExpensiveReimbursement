package Controllers;
import Models.*;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.*;


import tools.*;
import Database.*;

public class UserAPI extends HttpServlet implements Print {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Database data = Database.getDatabase();
	private Manipulate manipulate = Manipulate.getInstance();
	
	public UserAPI() throws Exception {
		super();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// preparation for the json content
//		res.addHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
//		res.addHeader("Access-Control-Allow-Methods", "GET");
		StringBuilder text = new StringBuilder(); 
		
		// json file start hear 
		text.append("{");
		
		if ( req.getParameterMap().containsKey("Id") ) {
			// /api/users?Id=...
			long id = Long.parseLong(req.getParameter("Id"));
			showOneUserAPI(text,id);
		}
		else{
			// /api/users/
			showAllUsersAPI(text);
		}
			
		
		text.append("}");
		
		///// start to render the json content 
		
		println(text);
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
		
	}
	
	private void showAllUsersAPI (StringBuilder text) {
		List<User> users = data.getAllUsers();
		try {
			// this element to confirm data connected
			text.append("\"connected\" : true ,");
			// this element is used to contains all user database 
			text.append("\"data\" : [");
			for (User u : users ) {
				text.append(u.toJson()+",");	
			}
			if (users.size()>0) text.deleteCharAt(text.length()-1);
			text.append("]");

		} catch (Exception e) {
			
			// this will report what happened at the backend and show to frontend 
			
			e.printStackTrace();
			text.append("\"stackTrace\": \"");
			text.append(e.getStackTrace().toString()+"\",");
			text.append("\"connected\" : false");
		}		
		
	}
	
	private void showOneUserAPI ( StringBuilder text, long id) {
		User u = manipulate.getUser(id);
		text.append("\"connected\" : true ,");
		if (u!=null) {
			List<Mail> inbox = manipulate.getInboxMails(u);
			List<Mail> sent = manipulate.getSentMails(u);
			text.append("\"profile\" : ");
			text.append(u.toJsonSecret()+",");	
			
			text.append("\"inbox\" : [");
			for (Mail m : inbox ) {
				text.append(m.toJson()+",");	
			}
			if (inbox.size()>0) text.deleteCharAt(text.length()-1);
			text.append("],");
			
			text.append("\"sent\" : [");
			for (Mail m : sent ) {
				text.append(m.toJson()+",");	
			}
			if (sent.size()>0) text.deleteCharAt(text.length()-1);
			text.append("]");
			
			
		}
		else {
			text.append("\"errors\" : [\"data does not exist\"]");
		}
	}
	
	
}
