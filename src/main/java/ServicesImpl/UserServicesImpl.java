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
		try {
			List<User> users = data.getAllUsers();
			for (User temp : users) {
				if(
						( temp.getUserName().equals(in) && temp.getPassword().equals(pw) ) 
						||
						( temp.getEmail().equals(in) && temp.getPassword().equals(pw) )
				) 
				{
					return temp;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("no User match");
		return null;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mail forgotPassword(String in) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	
	
	
}
