package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.Database;
import Database.Manipulate;
import Models.*;

public class MailAPI extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Database data = Database.getDatabase();
	private Manipulate manipulate = Manipulate.getInstance();
	
	public MailAPI() throws Exception {
		super();
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// preparation for the json content
		
		StringBuilder text = new StringBuilder(); 
		
		// json file start hear 
		text.append("{");
		
		if ( req.getParameterMap().containsKey("Id") ) {
			// /api/mail?Id=...
			long id = Long.parseLong(req.getParameter("Id"));
			showOneMailAPI(text,id);
		}
		else{
			// /api/mails/
			showAllMailsAPI(text);
		}
			
		
		text.append("}");
		
		///// start to render the json content 
		
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
		
	}
	
	private void showAllMailsAPI (StringBuilder text) {
		List<Mail> mails = data.mails;
		try {
			// this element to confirm data connected
			text.append("\"connected\" : true ,");
			// this element is used to contains all user database 
			text.append("\"data\" : [");
			for (Mail u : mails ) {
				text.append(u.toJson()+",");	
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
	
	private void showOneMailAPI ( StringBuilder text, long id) {
		Mail mail = manipulate.getMail(id);
		text.append("\"connected\" : true ,");
		if(mail!=null) {
			text.append("\"mail\" : ");			
			text.append(mail.toJson());	
		}
		else {
			text.append("\"errors\" : [\"data does not exist\"]");
		}
	}
}
