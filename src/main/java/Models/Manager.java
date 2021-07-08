package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="MANAGERS")
public class Manager extends Employee{
	@Column( name = "TEAM_SIZE")
	protected int teamSize = 5;
	public Manager () {
		super();
	}
	
	
	public Manager(User u) {
		super(u);
		// TODO Auto-generated constructor stub
		userId = u.userId;
		setLeader(this);
	}
	
	public Manager setTeamSize(int size) {
		teamSize = size;
		return this;
	}
	public int getTeamSize() {
		return teamSize;
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
				+ "\"title\" :" + "\"Manager\","
				+ "\"leader_url\" : \"/api/users?Id="+ leader.userId + "\","
				+ "\"teamSize\" : "+ teamSize
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
				+ "\"title\" :" + "\"Manager\","
				+ "\"leader_url\" : \"/api/users?Id="+ leader.userId + "\","
				+ "\"teamSize\" : "+ teamSize
				+ "}";
	}
}
