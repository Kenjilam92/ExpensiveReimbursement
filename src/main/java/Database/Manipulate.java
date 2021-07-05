package Database;

import java.util.*;
import java.util.stream.Collectors;

import Models.Employee;
import Models.Mail;
import Models.Manager;
import Models.Request;
import Models.User;

public class Manipulate {
	public static Manipulate manipulate = new Manipulate();
	private Database data = Database.getDatabase();
	private Manipulate() {
	}
	public static Manipulate getInstance(){
		return manipulate;
	}
	
	///Show One elements
	
	public User getUser(long x) {
        return data.users.stream().filter( u -> u.getId() == x).findFirst().orElse(null);
	}
	
	public Employee getEmployee(long x) {
		return data.employees.stream().filter( u -> u.getId() == x).findFirst().orElse(null);
	}
	
	public Manager getManager(long x) {
		return data.managers.stream().filter( u -> u.getId() == x).findFirst().orElse(null);
	}
	
	public Mail getMail(long x) {
        return data.mails.stream().filter( u -> u.getId() == x).findFirst().orElse(null);
	}
	
	public Request getRequest(long x) {
		return data.requests.stream().filter( u -> u.getId() == x).findFirst().orElse(null);
	}
	
	/// Get data base on relationships
	
	public List<Employee> getTeam (Manager m){
		return data.employees.stream()
				.filter( e -> e.getLeader().getId() == m.getId())
				.collect(Collectors.toList());
	}
	
	public List<Mail> getSentMails (User u){
		return data.mails.stream()
				.filter( e -> e.getSender().getId() == u.getId())
				.collect(Collectors.toList());
	}
	public List<Mail> getInboxMails (User u){
		return data.mails.stream()
				.filter( e -> e.getReceiver().getId() == u.getId())
				.collect(Collectors.toList());
	}
	
	public List<Request> getSentRequest (Employee u){
		return data.requests.stream()
				.filter( e -> e.getAuthor().getId() == u.getId())
				.collect(Collectors.toList());
	}
	
	public List<Request> getPendingRequest (Manager u){
		return data.requests.stream()
				.filter( e -> e.getAuthor().getLeader().getId() == u.getId())
				.collect(Collectors.toList());
	}
}
