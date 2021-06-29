package Models;

public class Mail {
	private static long autoId = 0;
	protected long mailId;
	protected int senderId;
	protected int receiverId;
	protected String content;
	public Mail (int sender, int reciever, String cont) {
		senderId = sender;
		receiverId = reciever;
		content = cont;
		autoId++;
		mailId = autoId;
	}
	
	public int getSender() {
		return senderId;
	}
	public int getReceiver () {
		return receiverId; 
	}
	public String getContent() {
		return content;
	}
}
