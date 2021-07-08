package Controllers;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import Models.*;
import Database.*;
import ServicesImpl.*;

public class Login  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Manipulate manipulate = Manipulate.getInstance();
	private UserServicesImpl userSv = UserServicesImpl.getInstance();
	private EmployeeServicesImpl empSv = EmployeeServicesImpl.getInstance();
	private ManagerServicesImpl manSv = ManagerServicesImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res ) throws IOException {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//		res.addHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
//		res.addHeader("Access-Control-Allow-Methods", "GET,POST");
		BufferedReader reader = req.getReader();
		StringBuffer json = new StringBuffer();
		String line = "";
		String in = null;
		String pw = null; 
	
		while  ((line = reader.readLine()) != null) {
			json.append(line);
		}
		
		try {
			System.out.println(json.toString());
			JSONObject jsonObject = new JSONObject(json.toString());
			in = jsonObject.getString("userName");
			pw = jsonObject.getString("password");
		}
		catch (Exception e){
			e.printStackTrace();
			
		}
		
		
		// preparation for the json content
		StringBuilder text = new StringBuilder(); 
		
		// json file start hear 
		text.append("{");
		text.append("\"connected\" : true ,");
		if ( manSv.login(in, pw)!=null ) {
			renderManager(manSv.login(in, pw), text);
		}
		else if( empSv.login(in, pw) != null) {
			renderEmployee(empSv.login(in, pw), text);
		}
		else if( userSv.login(in,pw) != null){
			renderUser(userSv.login(in, pw), text);
		}
		else {
			text.append("\"errors\" : [\"Wrong password or username. Please try again or register!\"]");
		}
		
		text.append("}");
		
		publishJson(res,text);
		
	}
	
	private void renderManager(Manager m, StringBuilder text) {
		List<Employee> team = manipulate.getTeam(m);
		List<Request> pendingRequests = manipulate.getPendingRequest(m);
		List<Mail> inbox = manipulate.getInboxMails(m);
		List<Mail> sent = manipulate.getSentMails(m);
		List<Request> requests = manipulate.getSentRequest(m);
		List<User> users = manipulate.getUnHiredUser();
		
		text.append("\"profile\" : ");			
		text.append(m.toJsonSecret() + ",");
		
		text.append("\"teamMate\" :");
		text.append("[");
		team.stream().forEach(e -> text.append(e.toJson()+","));
		if(team.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"unhired\" :");
		text.append("[");
		users.stream().forEach(e -> text.append(e.toJson()+","));
		if(users.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"inbox\" :");
		text.append("[");
		inbox.stream().forEach(e -> text.append(e.toJson()+","));
		if (inbox.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"sent\" :");
		text.append("[");
		sent.stream().forEach(e -> text.append(e.toJson()+","));
		if (sent.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"pendingRequests\" :");
		text.append("[");
		pendingRequests.stream().forEach(e -> text.append(e.toJson()+","));
		if (pendingRequests.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"requests\" :");
		text.append("[");
		requests.stream().forEach(e -> text.append(e.toJson()+","));
		if (requests.size()>0) text.deleteCharAt(text.length()-1);
		text.append("]");
	}
	
	private void renderEmployee(Employee m, StringBuilder text) {
		List<Employee> team = manipulate.getTeam(m.getLeader());
		List<Mail> inbox = manipulate.getInboxMails(m);
		List<Mail> sent = manipulate.getSentMails(m);
		List<Request> requests = manipulate.getSentRequest(m);
		text.append("\"profile\" : ");			
		text.append(m.toJsonSecret() + ",");
		
		text.append("\"teamMate\" :");
		text.append("[");
		team.stream().forEach(e -> text.append(e.toJson()+","));
		if (team.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"inbox\" :");
		text.append("[");
		inbox.stream().forEach(e -> text.append(e.toJson()+","));
		if (inbox.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"sent\" :");
		text.append("[");
		sent.stream().forEach(e -> text.append(e.toJson()+","));
		if (team.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"requests\" :");
		text.append("[");
		requests.stream().forEach(e -> text.append(e.toJson()+","));
		if (requests.size()>0) text.deleteCharAt(text.length()-1);
		text.append("]");
	}
	
	private void renderUser(User m, StringBuilder text) {
		List<Mail> inbox = manipulate.getInboxMails(m);
		List<Mail> sent = manipulate.getSentMails(m);
		
		text.append("\"profile\" : ");			
		text.append(m.toJsonSecret() + ",");
		
		
		text.append("\"inbox\" :");
		text.append("[");
		inbox.stream().forEach(e -> text.append(e.toJson()+","));
		if (inbox.size()>0) text.deleteCharAt(text.length()-1);
		text.append("],");
		
		text.append("\"sent\" :");
		text.append("[");
		sent.stream().forEach(e -> text.append(e.toJson()+","));
		if (sent.size()>0) text.deleteCharAt(text.length()-1);
		text.append("]");

	}
	
	private void publishJson(HttpServletResponse res, StringBuilder text) throws IOException {
		
		
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
	}
	
}
