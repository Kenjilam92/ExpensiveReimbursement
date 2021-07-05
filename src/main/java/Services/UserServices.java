package Services;
import Models.*;

public interface UserServices {
	public User login(String userNameOrEmail, String pw );
	public void logout();
	public String forgotPassword(String userNameOEmail);
	
	
}
