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
		return data.getAllUsers().stream()
				.filter(e -> 
						(e.getUserName().equals(in) && e.getPassword().equals(pw))
						||
						(e.getEmail().equals(in) && e.getPassword().equals(pw))
						)
				.findFirst().orElse(null);
	}


	@Override
	public String forgotPassword(String in) {
		// TODO Auto-generated method stub
		
		User u  = data.getAllUsers().stream()
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

	
	@Override
	public boolean register(User u) {
		// TODO Auto-generated method stub
		System.out.println(data.dataSize());
		
		User test1 = data.getAllUsers().stream()
			.filter(t -> t.getUserName().equals(u.getUserName()) )
			.findFirst().orElse(null);

		User test2 = data.getAllUsers().stream()
			.filter(t -> t.getEmail().equals(u.getEmail()) )
			.findFirst().orElse(null);
	
		System.out.println("******************************************");
		
		if (test1 != null) {
			System.out.println("duplicated user name"+ test1);
			data.getAllUsers().stream().forEach(e-> System.out.println(e));
			return false;
		}
		else if (test2!=null){
			System.out.println("duplicated email"+ test2);
			data.getAllUsers().stream().forEach(e-> System.out.println(e));
			return false;
		}
		else {
			
			data.add(u);
			System.out.println("new user added");
			data.getAllUsers().stream().forEach(e-> System.out.println(e));
			return true;
	
		}
		
	}

	@Override
	public boolean removeAccount(User u) {
		// TODO Auto-generated method stub
		if (u == null) {
			System.out.println("non exist");
			return false;
		}
		else {		
			System.out.println("delete"+u);
			data.delete(u);
			if (login(u.getUserName(),u.getPassword()) == null) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProfile(User u) {
		// TODO Auto-generated method stub
		data.update(u);
		User test = login(u.getUserName(),u.getPassword());
		System.out.println(test);
		return(
				test.getEmail().equals(u.getEmail()) &&
				test.getFirstName().equals(u.getFirstName()) &&
				test.getLastName().equals(u.getLastName()) &&
				test.getUserName().equals(u.getUserName())
		)? true : false;
	}
	
	
	
}
