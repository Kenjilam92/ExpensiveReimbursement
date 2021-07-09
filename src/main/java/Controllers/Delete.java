package Controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import org.json.JSONObject;
import Models.*;
import Database.*;

public class Delete extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Database data = Database.getDatabase();
	private Manipulate manipulate = Manipulate.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// preparation for the json content
		
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
	    			//delete?type=user
	    			deleteUser(req,text,jsonObject);
	    			break;
	    		}
	    		case"employee":{
	    			//delete?type=employee
	    			deleteEmployee(req,text,jsonObject);
	    			break;
	    		}
	    		case"manager":{
	    			//delete?type=manager
	    			deleteManager(req,text,jsonObject);
	    			break;
	    		}
	    		case"mail":{
	    			//delete?type=mail
	    			deleteMail(req,text,jsonObject);
	    			break;
	    		}
	    		case"request":{
	    			//delete?type=request
	    			deleteRequest(req,text,jsonObject);
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
	
	

	private void deleteRequest(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long requestId = json.getLong("requestId");
		Request r = manipulate.getRequest(requestId);
		data.delete(r);
		text.append("\"status\" : \"success\"");
	}



	private void deleteMail(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long mailId = json.getLong("mailId");
		Mail m = manipulate.getMail(mailId);
		data.delete(m);
		text.append("\"status\" : \"success\"");
	}

	/*
	 * user, manager, employee need to consider the delete method 
	 * because they still have relationship with mails
	 * solution: changing mail class structure. storing email instead of user key
	 */

	private void deleteManager(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		Manager u = manipulate.getManager(userId);
		List<Employee> team = manipulate.getTeam(u);
		team.stream().forEach(e -> e.setLeader(null) );
		data.delete(u);
		text.append("\"status\" : \"success\"");
	}



	private void deleteEmployee(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		Employee e = manipulate.getEmployee(userId);
		e.setLeader(null);		
		data.update(e).delete(e).add((User)e);
		text.append("\"status\" : \"success\"");
	}



	private void deleteUser(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		User e = manipulate.getUser(userId);
		data.delete(e);
		text.append("\"status\" : \"success\"");
	}



	private void publishJson(HttpServletResponse res, StringBuffer text) throws IOException {
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
	}
}
