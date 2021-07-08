package Controllers;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import Models.*;
import Database.*;
import ServicesImpl.*;

public class Cookies  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res ) throws IOException {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

		// preparation for the json content
		StringBuilder text = new StringBuilder(); 
		Cookie[] cookies = req.getCookies();
		// json file start hear 
		text.append("{");
		text.append("\"connected\" : true ,");
		for (Cookie c : cookies) {
			text.append("\""+c.getName().toString() +"\" :" );
			text.append(c.getValue());
			text.append(",");
		}
		if(cookies.length>0) text.deleteCharAt(text.length()-1);
		text.append("}");
		
		System.out.println(text.toString());
		System.out.println(req.getHeader("Origin"));
		System.out.println(res.getHeaders("Access-Control-Allow-Origin"));
		publishJson(res,text);
		
	}
	
	
	
	private void publishJson(HttpServletResponse res, StringBuilder text) throws IOException {
	
		
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
	}
	
}
