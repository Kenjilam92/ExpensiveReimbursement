package Controllers;

import java.io.IOException;

import javax.servlet.http.*;

public class Logout extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Cookie[] cookies = req.getCookies();
		if (cookies.length > 0) {
			for (Cookie c: cookies) {
				System.out.println(c.getValue());
				c.setValue("");
				c.setPath("/");
				c.setMaxAge(1);
				System.out.println(c.getValue());
				res.addCookie(c);
			}
			
		}
		StringBuilder text = new StringBuilder(); 
		text.append("{");
		text.append("\"connected\" : true ,");
		text.append("\"status\" : \"ok\"");
		text.append("}");
		res.setContentType("application/json");
		res.setStatus(200);
		res.getWriter().write(text.toString());
	}
}
