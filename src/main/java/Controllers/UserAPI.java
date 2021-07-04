package Controllers;
import Models.*;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;


import tools.*;
import Database.*;

public class UserAPI extends HttpServlet implements Print {
	
	private Database data = Database.getDatabase();
	
	
	public UserAPI() throws Exception {
		super();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// preparation for the json content
		
		ObjectMapper om = new ObjectMapper();
		StringBuilder text = new StringBuilder(); 
		
		// json file start hear 
		text.append("{");
		// this element is used to contains all user database 
		try {
			text.append("\"data\" : [");
			List<User> users = data.getAllUsers();
			for (User u : users ) {
				text.append(u.toJson()+",");	
			}
			text.append("],");
			
			// this element to confirm data connected
			
			text.append("\"connected\" : true");
		} catch (Exception e) {
			
			// this will report what happened at the backend and show to frontend 
			
			e.printStackTrace();
			text.append("\"stackTrace\": \"");
			text.append(e.getStackTrace().toString()+"\",");
			text.append("\"connected\" : false");
		}		
		text.append("}");
		
		///// start to render the json content 
		
		println(text);
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
		
	}
}
