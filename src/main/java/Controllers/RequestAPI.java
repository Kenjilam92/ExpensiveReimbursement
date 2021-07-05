package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.Database;
import Database.Manipulate;
import Models.*;

public class RequestAPI extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Database data = Database.getDatabase();
	private Manipulate manipulate = Manipulate.getInstance();
	
	public RequestAPI() throws Exception {
		super();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// preparation for the json content
		
		StringBuilder text = new StringBuilder(); 
		
		// json file start hear 
		text.append("{");
		
		if ( req.getParameterMap().containsKey("Id") ) {
			// /api/requests?Id=...
			long id = Long.parseLong(req.getParameter("Id"));
			showOneRequestAPI(text,id);
		}
		else{
			// /api/requests/
			showAllRequestsAPI(text);
		}
			
		
		text.append("}");
		
		///// start to render the json content 
		
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
		
	}
	
	private void showAllRequestsAPI (StringBuilder text) {
		List<Request> requests = data.requests;
		try {
			// this element to confirm data connected
			text.append("\"connected\" : true ,");
			// this element is used to contains all user database 
			text.append("\"data\" : [");
			for (Request r : requests ) {
				text.append(r.toJson()+",");	
			}
			text.append("]");

		} catch (Exception e) {
			
			// this will report what happened at the backend and show to frontend 
			
			e.printStackTrace();
			text.append("\"connected\" : false ,");
			text.append("\"stackTrace\": \"");
			text.append(e.getStackTrace().toString()+"\"");
		}		
		
	}
	
	private void showOneRequestAPI ( StringBuilder text, long id) {
		Request request = manipulate.getRequest(id);
		text.append("\"connected\" : true ,");
		if(request!=null) {
			text.append("\"mail\" : ");			
			text.append(request.toJson());	
		}
		else {
			text.append("\"errors\" : [\"data does not exist\"]");
		}
	}
}
