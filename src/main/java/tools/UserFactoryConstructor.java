package tools;
import Models.*;

public interface UserFactoryConstructor {
	public default User userFactory(User u) {
		switch ( u.getStatus() ) {
			case "User" : {
				return u;	
			}
			case "Employee" : {
				String uN = u.getUserName();
				String em = u.getEmail();
				String pw = u.getPassword();
				String fn = u.getFirstName();
				String ln = u.getLastName();
				return new Employee(u);
			}
			case "Manager" : {
				String uN = u.getUserName();
				String em = u.getEmail();
				String pw = u.getPassword();
				String fn = u.getFirstName();
				String ln = u.getLastName();
				return new Manager(u);
			}
			default : {
				return null;
			}			
		}
	}
}
