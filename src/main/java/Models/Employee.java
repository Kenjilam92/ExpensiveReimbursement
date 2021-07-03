package Models;
import java.util.*;

import javax.persistence.*;


@Entity
//@Table(name="EMPLOYEE")
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
	
	public void setLeader(Manager m) {
		leader = m;
	}
	
	
	@Override
	public String toString() {
		return this.getClass().getName() +" , " + userId + " { " + userName + " : " + leader.firstName + " }";
	}
}
