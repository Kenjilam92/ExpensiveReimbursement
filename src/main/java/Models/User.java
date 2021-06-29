package Models;
import java.util.*;

import javax.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE) 
public class User {
	@Id
	@Column(name="USER_NAME", columnDefinition="serial primary key")
	protected String userName;
	@Column(name="PASSWORD")
	protected String password;
	@Column(name="EMAIL")
	protected String email;
	@Column(name="FIRST_NAME")
	protected String firstName;
	@Column(name="LAST_NAME")
	protected String lastName;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="MAILBOX", referencedColumnName = "MAIL_ID", columnDefinition = "INT" )
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













