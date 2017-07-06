
import java.util.EventObject;

class SendMailEvent extends EventObject {

	private static final long serialVersionUID = -3884219192927232301L;
	private String server;
	private String username;
	private String password;
	private String sender;
	private String recipient;
	private String subject;
	private String message;
	
	
	SendMailEvent (Object source) {
		super(source);
	}

	/**
	 * Method to sent authentication information to event
	 * @param server The SMTP server name
     * @param username The user name of email account
     * @param password The password of email account
	 */
	void setAuthenInfo(String server, String username, String password) {
		this.server = server;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Method to sent email content to event
	 * @param sender The email's sender
	 * @param recipient The email's recipient
	 * @param subject The email's subject
	 * @param message The email's message
	 */
	void setEmailContent(String sender, String recipient, String subject,String message) {
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.message = message;
	}
	
	/**
	 * Method to create a sending email thread
	 */
	void sendMail() {
		new SendMailThread(server, username, password, sender, recipient, subject, message)
		.start();
	}
	
}
