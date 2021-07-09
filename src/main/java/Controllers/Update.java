package Controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.*;

import org.json.JSONObject;
import Models.*;
import ServicesImpl.ManagerServicesImpl;
import Database.*;

public class Update extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Database data = Database.getDatabase();
	private Manipulate manipulate = Manipulate.getInstance();
	private ManagerServicesImpl manSv = ManagerServicesImpl.getInstance();
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
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json.toString());
		
	    // json file start hear 
	 	text.append("{");
	 	text.append("\"connected\" : true ,");
	    if (req.getParameterMap().containsKey("type")){	    	
	    	switch ( req.getParameter("type") ) {
	    		case"User":{
	    			//update?type=User
	    			updateUser(req,text,jsonObject);
	    			break;
	    		}
	    		case"Employee":{
	    			//update?type=Employee
	    			updateEmployee(req,text,jsonObject);
	    			break;
	    		}
	    		case"Manager":{
	    			//update?type=Manager
	    			updateManager(req,text,jsonObject);
	    			break;
	    		}
	    		case"Request":{
	    			updateRequest(req,text,jsonObject);
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
	
	private void updateRequest(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		long requestId = json.getInt("requestId");
		String action = json.getString("action");
		boolean result = false;
		Manager m = manipulate.getManager(userId);
		Request r = manipulate.getRequest(requestId);
		switch (action) {
			case("approve"):{
				result = manSv.approveRequest(m, r);
				break;
			}
			case("denny"):{
				result = manSv.dennyRequest(m, r);
				break;
			}
		}
		if (result) {
			text.append("\"status\" : \"success\"");
		}
		else {
			text.append("\"status\" : \"fail\"");
		}
	}

	private void updateManager(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		String userName = json.getString("userName");
		String email = json.getString("email");
		String firstName = json.getString("firstName");
		String lastName = json.getString("lastName");
		int teamSize = json.getInt("teamSize");
		Manager u = manipulate.getManager(userId);
		u.setTeamSize(teamSize).setUserName(userName).setEmail(email)
			.setFirstName(firstName).setLastName(lastName);
		data.update(u);
		text.append("\"status\" : \"success\"");
	}
	private void updateEmployee(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		long leaderId =  json.getLong("leaderId");
		String userName = json.getString("userName");
		String email = json.getString("email");
		String firstName = json.getString("firstName");
		String lastName = json.getString("lastName");
		Employee u = manipulate.getEmployee(userId);
		Manager m = manipulate.getManager(leaderId);
		u.setLeader(m).setUserName(userName).setEmail(email)
			.setFirstName(firstName).setLastName(lastName);
		data.update(u);
		text.append("\"status\" : \"success\"");
	}
	private void updateUser(HttpServletRequest req, StringBuffer text, JSONObject json) {
		// TODO Auto-generated method stub
		long userId = json.getLong("userId");
		String userName = json.getString("userName");
		String email = json.getString("email");
		String firstName = json.getString("firstName");
		String lastName = json.getString("lastName");
		User u = manipulate.getUser(userId);
		u.setUserName(userName).setEmail(email)
			.setFirstName(firstName).setLastName(lastName);
		data.update(u);
		text.append("\"status\" : \"success\"");
	}




	private void publishJson(HttpServletResponse res, StringBuffer text) throws IOException {
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
	}
}
