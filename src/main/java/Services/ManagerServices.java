package Services;
import Models.*;

public interface ManagerServices{
	public boolean approveRequest(Manager m, Request req);
	public boolean dennyRequest (Manager m, Request req);
	public boolean restorePassword (Employee e);
}
