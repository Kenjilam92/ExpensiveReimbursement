package General;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.boot.registry.*;
import Models.*;
import Database.*;
import java.util.*;
import tools.*;
import ServicesImpl.*;

public class Main {
	
	public static void main ( String[] varargs ) throws Exception {
		Database data = Database.getDatabase();
		UserServicesImpl userServices = UserServicesImpl.getInstance();
		EmployeeServicesImpl employeeServices = EmployeeServicesImpl.getInstance();
		ManagerServicesImpl managerServices = ManagerServicesImpl.getInstance();
		
//		
//		User kenji = new User("kenji","kenji@gmail.com","1234");
//		User keith = new User("keith","keith@gmail.com","1234","Keith","Law");
//		User trong = new User("trong","trong@revature.com","456789","Trong","Ho");
//		data.add(keith).add(trong).add(kenji);
//
//		printBorder();
//			
//		
//		User a = userServices.login("kenji", "1234");
//		User b = userServices.login("trong@revature.com","456789");		
//		User c = userServices.login("keith", "1234");
//		System.out.println(c.getClass().getName());
//		println(a);
//		println(b);
//		println(c);
//		printBorder();
//		
//		
//		Manager trongHired = new Manager (b);
//		println(trongHired);
//		data.delete(b).add(trongHired);
//		Manager b1 = managerServices.login("trong@revature.com","456789");	
//		
//		
//		Employee keithHired = new Employee(c, b1);
//		println(keithHired);
//		data.delete(c).add(keithHired);
//		Employee c1 = employeeServices.login("keith", "1234");
//		
//		println(a);
//		println(b1);
//		println(c1);
//		
		printBorder();
		List<User> users = data.getAllUsers();
		System.out.println(data.getAllEmployees().size());
		System.out.println(data.getAllManagers().size());
		
	}

	private static void println(User kenji) {
		// TODO Auto-generated method stub
		System.out.println(kenji);
	}

	private static void printBorder() {
		// TODO Auto-generated method stub
		System.out.println("****************************************************************************");		
	}

	

	
	
}
