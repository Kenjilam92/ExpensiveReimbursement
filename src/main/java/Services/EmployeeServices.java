package Services;
import Models.*;

public interface EmployeeServices {
	public Employee changeName(Employee e);
	public void updateRequest(Request updatedRequest );
	public void deleteRequest(Request select);
	public void createRequest(Request newRequest );
	public Request findRequest(Employee e, int requestId); 
}
