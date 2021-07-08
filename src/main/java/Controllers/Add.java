package Controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.*;

import org.json.JSONObject;
import Models.*;
import Database.*;

public class Add extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Database data = Database.getDatabase();
	private Manipulate manipulate = Manipulate.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// preparation for the json content
		System.out.println("&&&&&&&&&&&&&");
		StringBuffer text = new StringBuffer(); 
		BufferedReader reader = req.getReader();
		StringBuffer json = new StringBuffer();
		String line = "";
		while  ((line = reader.readLine()) != null) {
			json.append(line);
		}
		JSONObject jsonObject = new JSONObject(json.toString());
		
	    // json file start hear 
	 	text.append("{");
	 	text.append("\"connected\" : true ,");
	    if (req.getParameterMap().containsKey("type")){	    	
	    	switch ( req.getParameter("type") ) {
	    		case"user":{
	    			//add?type=user
	    			addUser(req,text,jsonObject);
	    			break;
	    		}
	    		case"employee":{
	    			//add?type=employee
	    			addEmployee(req,text,jsonObject);
	    			break;
	    		}
	    		case"manager":{
	    			//add?type=manager
	    			addManager(req,text,jsonObject);
	    			break;
	    		}
	    		case"mail":{
	    			//add?type=mail
	    			addMail(req,text,jsonObject);
	    			break;
	    		}
	    		case"request":{
	    			//add?type=request
	    			addRequest(req,text,jsonObject);
	    			break;
	    		}
	    		default:{
	    			text.append("\"errors\" : [\"incorrect data type\"]");
	    		}
		    }
	    }

		
		text.append("}");
		
		// publish json
		publishJson(res,text);
	}
	
	

	private void addRequest(HttpServletRequest req, StringBuffer text, JSONObject jsonObject) {
		// TODO Auto-generated method stub
		long userId = jsonObject.getLong("userId");
		Employee e = manipulate.getEmployee(userId);
		double cost = jsonObject.getDouble("cost");
		String content = jsonObject.getString("content");
		data.add(new Request(e,cost,content));
		text.append("\"status\" : \"success\"");
	}



	private void addMail(HttpServletRequest req, StringBuffer text, JSONObject jsonObject) {
		// TODO Auto-generated method stub
		long receiverId = jsonObject.getLong("receiver");
		User receiver = manipulate.getUser(receiverId);
		long senderId = jsonObject.getLong("sender");
		User sender = manipulate.getUser(senderId);
		String content = jsonObject.getString("content");
		data.add(new Mail(sender,receiver,content));
		text.append("\"status\" : \"success\"");
	}



	private void addManager(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		User u = manipulate.getUser(userId);
		Manager m = new Manager(u);
		data.add(m).delete(u);
		text.append("\"status\" : \"success\"");
	}



	private void addEmployee(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		long manId = json.getLong("managerId");
		User u = manipulate.getUser(userId);
		Manager m = manipulate.getManager(manId);
		Employee e= new Employee(u,m);
		data.add(e).delete(u);
		text.append("\"status\" : \"success\"");
	}



	private void addUser(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		String userName = json.getString("userName");
		String email = json.getString("email");
		String password = json.getString("password");
		String firstName = json.getString("firstName");
		String lastName = json.getString("lastName");
		User u = new User(userName,email,password,firstName,lastName);
		data.add(u);
		text.append("\"status\" : \"success\"");
	}



	private void publishJson(HttpServletResponse res, StringBuffer text) throws IOException {
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
	}
}
