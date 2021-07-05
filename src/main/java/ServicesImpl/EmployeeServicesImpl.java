package ServicesImpl;
import Services.*;

import java.util.List;

import Database.Database;
import Models.Employee;
import Models.Mail;
import Models.Manager;
import Models.Request;
import Models.User;

public class EmployeeServicesImpl implements UserServices,EmployeeServices {
	private static EmployeeServicesImpl obj = new EmployeeServicesImpl();
	private Database data = Database.getDatabase();
	
	private EmployeeServicesImpl () {}
	
	public static EmployeeServicesImpl getInstance() {
		return obj;
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
	public Employee login(String in, String pw) {
		// TODO Auto-generated method stub
		return data.employees.stream()
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
	public String forgotPassword(String userNameOEmail) {
		// TODO Auto-generated method stub
		
		
		return null;
	}
	
}
