package Models;
import javax.persistence.*;


@Entity
public class Request {
	private static long autoId = 0;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	
	@Column( name="REQUEST_ID", columnDefinition ="serial primary key")
	private long requestId;
	
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinColumn( name = "AUTHOR", referencedColumnName = "AUTHOR_ID", columnDefinition = "INT")
	private int authorId;
	@Column (name="IS_APPROVED")
	private boolean isApproved;
	@Column (name="COST")
	private double cost;
	@Column (name="CONTENT")
	private String content;
	
	
	public Request(int id, double price, String cont){
		isApproved = false;
		cost = price;
		content = cont;
		authorId = id;
		autoId++;
		requestId = autoId;
	}
	
	public long getRequestId() {
		return requestId;
	}
	
	public int getAuthorId() {
		return authorId;
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
}
