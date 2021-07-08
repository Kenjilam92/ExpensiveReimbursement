package ServicesImpl;
import Services.*;

import java.util.List;

import Database.*;
import Models.Employee;
import Models.Mail;
import Models.Manager;
import Models.Request;
import Models.User;

public class EmployeeServicesImpl implements EmployeeServices {
	private static EmployeeServicesImpl obj = new EmployeeServicesImpl();
	private Database data = Database.getDatabase();
	private Manipulate man = Manipulate.getInstance();
	private EmployeeServicesImpl () {}
	
	public static EmployeeServicesImpl getInstance() {
		return obj;
	}

	
	@Override
	public void deleteRequest(Request select) {
		// TODO Auto-generated method stub
		data.delete(select);
	}

	@Override
	public void createRequest(Request newRequest) {
		// TODO Auto-generated method stub
		data.add(newRequest);
	}

	public Employee login(String in, String pw) {
		// TODO Auto-generated method stub
		return data.getAllEmployees().stream()
				.filter(e -> 
						(e.getUserName().equals(in) && e.getPassword().equals(pw))
						||
						(e.getEmail().equals(in) && e.getPassword().equals(pw))
						)
				.findFirst().orElse(null);
	}
}
