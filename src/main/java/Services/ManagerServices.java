package Services;
import Models.*;

public interface ManagerServices{
	public boolean approveRequest(Request req);
	public boolean dennyRequest (Request req);
	public boolean restorePassword (Employee e);
}
