package Models;
import java.util.*;

import javax.persistence.*;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS) 
//@MappedSuperclass
@Table(name ="USERS_TEST")
public class User {
	public User() {
		super();
	}
	@Id
	@SequenceGenerator(name="userIdSeq", sequenceName="hibernate_sequence", allocationSize = 1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator ="userIdSeq")
    @Column(name="USER_ID")
	protected long userId; 
	@Column(name="USER_NAME")
	protected String userName;
	@Column(name="PASSWORD")
	protected String password;

	@Column(name="EMAIL")
	protected String email;
	@Column(name="FIRST_NAME")
	protected String firstName;
	@Column(name="LAST_NAME")
	protected String lastName;
	
	
	public User (String userN, String em, String pass) {
		userName = userN;
		password = pass;
		email = em;
		firstName = "";
		lastName = "";
	}
	
	public User (String uN, String em, String pw, String fn, String ln) {
		this(uN,em,pw);
		firstName = fn;
		lastName = ln;
	}
	
	public long getId() {
		return userId;
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
	
	@Override
	public String toString() {
		return 	this.getClass().getName() + " {"+userId+", "+ userName + ", "+ password + ", "+ firstName+ ", "+ lastName+ ", "+ email+"}"  ;
	}
	
	public String toJson() {
		return "{ "
				+ "\"userId\" : " + userId + ","
				+ "\"userName\" :" + "\"" + userName + "\","
//				+ "\"password\" :" + "\"" + password + "\","
				+ "\"email\" :" + "\"" + email + "\","
				+ "\"firstName\" :" + "\"" + firstName + "\","
				+ "\"lastName\" :" + "\"" + lastName + "\","
				+ "\"title\" :" + "\"User\""
				+ "}";
	}
	
	public String toJsonSecret() {
		return "{ "
				+ "\"userId\" : " + userId + ","
				+ "\"userName\" :" + "\"" + userName + "\","
				+ "\"password\" :" + "\"" + password + "\","
				+ "\"email\" :" + "\"" + email + "\","
				+ "\"firstName\" :" + "\"" + firstName + "\","
				+ "\"lastName\" :" + "\"" + lastName + "\","
				+ "\"title\" :" + "\"User\""
				+ "}";
	}

	public User setUserName(String uN) {
		// TODO Auto-generated method stub
		userName =uN;
		return this;
	}
	public User setEmail(String uN) {
		// TODO Auto-generated method stub
		email =uN;
		return this;
	}
	public User setPassword(String uN) {
		// TODO Auto-generated method stub
		password =uN;
		return this;
	}
	public User setFirstName(String uN) {
		// TODO Auto-generated method stub
		firstName =uN;
		return this;
	}
	public User setLastName(String uN) {
		// TODO Auto-generated method stub
		lastName =uN;
		return this;
	}
	
}













