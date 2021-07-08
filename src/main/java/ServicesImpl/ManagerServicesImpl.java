package ServicesImpl;
import Database.Database;
import Models.*;
import Services.*;
import java.util.*;

public class ManagerServicesImpl implements ManagerServices {
	
	private static ManagerServicesImpl obj = new ManagerServicesImpl();
	private Database data = Database.getDatabase();
	
	private ManagerServicesImpl () {}
	
	public static ManagerServicesImpl getInstance() {
		return obj;
	}
	
	public Manager login(String in, String pw) {
		// TODO Auto-generated method stub
		return data.getAllManagers().stream()
				.filter(e -> 
						(e.getUserName().equals(in) && e.getPassword().equals(pw))
						||
						(e.getEmail().equals(in) && e.getPassword().equals(pw))
						)
				.findFirst().orElse(null);
	}

	@Override
	public boolean approveRequest(Manager m, Request req) {
		// TODO Auto-generated method stub
		if(req.getAuthor().getLeader().getId() != m.getId())
			return false;
		else {
			req.setIsApproved(true);
			req.setIsDennied(false);
			data.update(req);
			return true;
		}
	}

	@Override
	public boolean dennyRequest(Manager m, Request req) {
		// TODO Auto-generated method stub
		if(req.getAuthor().getLeader().getId() != m.getId())
			return false;
		else {
			req.setIsApproved(false);
			req.setIsDennied(true);
			data.update(req);
			return true;
		}
	}

	@Override
	public boolean restorePassword(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
