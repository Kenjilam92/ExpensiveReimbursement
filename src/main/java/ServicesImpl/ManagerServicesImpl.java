package ServicesImpl;
import Database.Database;
import Models.*;
import Services.*;
import java.util.*;

public class ManagerServicesImpl implements UserServices, EmployeeServices, ManagerServices {
	
	private static ManagerServicesImpl obj = new ManagerServicesImpl();
	private Database data = Database.getDatabase();
	
	private ManagerServicesImpl () {}
	
	public static ManagerServicesImpl getInstance() {
		return obj;
	}
	
	
	@Override
	public boolean approveRequest(Request req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dennyRequest(Request req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean restorePassword(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee changeName(Employee e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRequest(Request updatedRequest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRequest(Request select) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRequest(Request newRequest) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Request findRequest(Employee e, int requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager login(String in, String pw) {
		// TODO Auto-generated method stub
		try {
			List<Manager> managers = data.getAllManagers();
			System.out.println(managers.size());
			for (Manager temp : managers) {
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
		System.out.println("no Manager match");
		return null;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mail forgotPassword(String userNameOEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
