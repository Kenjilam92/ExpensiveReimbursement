package Models;
import javax.persistence.*;


@Entity
@Table(name="REQUESTS")
public class Request {
	public Request() {
		super();
	}
	
	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@SequenceGenerator(name="requestSeq", sequenceName="hibernate_sequence_1",allocationSize = 1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator ="requestSeq")
    
	@Column( name="REQUEST_ID")
	private long requestId;
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn( name = "AUTHOR", referencedColumnName = "USER_ID", columnDefinition = "INT")
	private Employee author;
	
	@Column (name="IS_APPROVED")
	private boolean isApproved;
	@Column (name="IS_DENNIED")
	private boolean isDennied;
	@Column (name="COST")
	private double cost;
	@Column (name="CONTENT")
	private String content;
	
	
	public Request(Employee e, double price, String cont){
		isApproved = false;
		isDennied = false;
		cost = price;
		content = cont;
		author = e;
	}
	
	public long getId() {
		return requestId;
	}
	
	public Employee getAuthor() {
		return author;
	}
	public boolean getIsApprove() {
		return isApproved;
	}
	public String getContent() {
		return content;
	}
	public double getCost() {
		return cost;
	}
	@Override
	public String toString() {
		return author.userName+ " requested $"+cost+ " for " + content;
	}
	
	public String toJson() {
		return "{"
				+"\"requestId\" : "+requestId+","
				+"\"authorUrl\" : \"/api/users?Id="+ author.getId() + "\""+","
				+"\"cost\" : "+ cost+","
				+"\"content\" : \"" + content + "\","
				+ "\"authorFirstName\" : \""+ author.getFirstName() + "\","
				+ "\"authorLastName\" : \"" + author.getLastName() + "\","
				+ "\"isApproved\" : " + isApproved+","
				+ "\"isDennied\" : " + isDennied
				+ "}";
	}

	public void setIsApproved(boolean b) {
		// TODO Auto-generated method stub
		isApproved = b;
		
	}
	public void setIsDennied(boolean b) {
		// TODO Auto-generated method stub
		isDennied = b;
	}
}
