package Models;
import javax.persistence.*;

@Entity
@Table(name="MAILS")
public class Mail {
	public Mail() {
		super();
	}	
	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@SequenceGenerator(name="mailSeq", sequenceName="hibernate_sequence_2",allocationSize = 1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator ="mailSeq")    
    @Column (name="MAIL_ID")
	protected long mailId;
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="SENDER", referencedColumnName="USER_ID", columnDefinition="INT")
	protected User sender;
	@OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="RECEIVER", referencedColumnName="USER_ID", columnDefinition="INT")
	protected User receiver;
	@Column (name="CONTENT")
	protected String content;
	public Mail (User s, User r, String cont) {
		sender = s;
		receiver = r;
		content = cont;
	}
	
	public User getSender() {
		return sender;
	}
	public User getReceiver () {
		return receiver; 
	}
	public String getContent() {
		return content;
	}
	
	@Override
	public String toString() {
		return 	"Sender: "+sender.userName+ "\n" +
				"Receiver: "+ receiver.userName + "\n"+
				"Content: "+ content;
	}
}
