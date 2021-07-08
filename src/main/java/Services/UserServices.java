package Services;
import Models.*;

public interface UserServices {
	public User login(String userNameOrEmail, String pw );
	public boolean logout();
	public String forgotPassword(String userNameOEmail);
	public boolean register(User u);
	public boolean removeAccount(User u);
	public boolean updateProfile(User u);
}
