package Models;
import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="EMPLOYEES")
//@PrimaryKeyJoinColumn(name="USER_ID")
public class Employee extends User{
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "LEADER", referencedColumnName = "USER_ID", columnDefinition = "INT" )
	protected Manager leader; 
	public Employee () {
		super();
	}
	
	public Employee(User u) {
		super(u.userName,u.email,u.password,u.firstName,u.lastName);
		userId = u.userId;
	}
	
	
	public Employee(User u, Manager m) {
		this(u);
		leader = m;		
	}
	
	public Manager getLeader() {
		return leader;
	}
	
	public Employee setLeader(Manager m) {
		leader = m;
		return this;
	}
	
	
	@Override
	public String toString() {
		return this.getClass().getName() +" , " + userId + " { " + userName + " : " + leader.firstName + " }";
	}
	
	@Override
	public String toJson() {
		return "{ "
				+ "\"userId\" : " + userId + ","
				+ "\"userName\" :" + "\"" + userName + "\","
//				+ "\"password\" :" + "\"" + password + "\","
				+ "\"email\" :" + "\"" + email + "\","
				+ "\"firstName\" :" + "\"" + firstName + "\","
				+ "\"lastName\" :" + "\"" + lastName + "\","
				+ "\"title\" :" + "\"Employee\","
				+ "\"leader_url\" : \"/api/users?Id="+ leader.userId + "\""
				+ "}";
	}
	@Override
	public String toJsonSecret() {
		return "{ "
				+ "\"userId\" : " + userId + ","
				+ "\"userName\" :" + "\"" + userName + "\","
				+ "\"password\" :" + "\"" + password + "\","
				+ "\"email\" :" + "\"" + email + "\","
				+ "\"firstName\" :" + "\"" + firstName + "\","
				+ "\"lastName\" :" + "\"" + lastName + "\","
				+ "\"title\" :" + "\"Employee\","
				+ "\"leader_url\" : \"/api/users?Id="+ leader.userId + "\""
				+ "}";
	}
}
