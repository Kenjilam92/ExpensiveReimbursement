package ServicesImpl;
import Models.*;
import Services.*;
import Database.*;
import java.util.*;

public class UserServicesImpl implements UserServices{
	private static UserServicesImpl obj = new UserServicesImpl();
	private Database data = Database.getDatabase();
	
	private UserServicesImpl () {}
	
	public static UserServicesImpl getInstance() {
		return obj;
	}

	@Override
	public User login(String in, String pw) {
		// TODO Auto-generated method stub
		return data.users.stream()
				.filter(e -> 
						(e.getUserName().equals(in) && e.getPassword().equals(pw))
						||
						(e.getEmail().equals(in) && e.getPassword().equals(pw))
						)
				.findFirst().orElse(null);
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String forgotPassword(String in) {
		// TODO Auto-generated method stub
		User u  = data.users.stream()
			.filter(e -> 
					(e.getUserName().equals(in))
					||
					(e.getEmail().equals(in))
					)
			.findFirst().orElse(null);
		
		if (u==null) {
			return null;			
		}
		return u.getEmail();
	}
	
	
	
}
