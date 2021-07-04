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
	private User author;
	
	@Column (name="IS_APPROVED")
	private boolean isApproved;
	@Column (name="COST")
	private double cost;
	@Column (name="CONTENT")
	private String content;
	
	
	public Request(User e, double price, String cont){
		isApproved = false;
		cost = price;
		content = cont;
		author = e;
	}
	
	public long getRequestId() {
		return requestId;
	}
	
	public User getAuthor() {
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
}
