package Models;
import java.util.*;

public class Employee extends User{
	protected ArrayList<Request> requestsList;
	public Employee(String uN, String em, String pw, String fn, String ln) {
		super(uN, em, pw, fn, ln);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Request> getRequestsList(){
		return requestsList;
	}
}
