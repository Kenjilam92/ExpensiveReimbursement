package Models;
import javax.persistence.*;

@Entity
public class Mail {
	private static long autoId = 0;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column (name="MAIL_ID", columnDefinition="serial primary key")
	protected long mailId;
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="SENDER_ID", referencedColumnName="USER_NAME", columnDefinition="CHAR")
	protected String senderId;
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="RECEIVER_ID", referencedColumnName="USER_NAME", columnDefinition="CHAR")
	protected String receiverId;
	protected String content;
	public Mail (String sender, String reciever, String cont) {
		senderId = sender;
		receiverId = reciever;
		content = cont;
		autoId++;
		mailId = autoId;
	}
	
	public String getSender() {
		return senderId;
	}
	public String getReceiver () {
		return receiverId; 
	}
	public String getContent() {
		return content;
	}
}
