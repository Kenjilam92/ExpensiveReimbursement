package Models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
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
	
	public Manager(Employee e) {
		this( (User)e );		
	}
	
	public void changeTeamSize(int size) {
		teamSize = size;
	}
	
	
}
