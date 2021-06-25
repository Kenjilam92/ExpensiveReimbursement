package General;
import tools.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePage extends HttpServlet implements Print {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HomePage() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		printBorder();
		println("HomePage run");
		RequestDispatcher view = request.getRequestDispatcher("/html/hompage1.html");
//		
//		PrintWriter out = response.getWriter();
//		
//		out.println(getTextFile("HomePage.html"));
		view.forward(request, response)	;
	}
}
