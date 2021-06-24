package tools;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PostPage {
		void doPost(HttpServletRequest request, HttpServletResponse response);
}
