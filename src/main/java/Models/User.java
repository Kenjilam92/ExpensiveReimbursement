package Models;
import java.util.*;

public class User {
	protected String userName;
	protected String password;
	protected String email;
	protected String firstName;
	protected String lastName;
	protected ArrayList<Mail> mailbox;
	
	public User (String userN, String em, String pass) {
		userName = userN;
		password = pass;
		email = em;
	}
	public User (String uN, String em, String pw, String fn, String ln) {
		this(uN,em,pw);
		firstName = fn;
		lastName = ln;
	}
		
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public ArrayList<Mail> getMailbox(){
		return mailbox;
	}
}













