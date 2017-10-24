import javax.mail.MessagingException;
import javax.mail.Session;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
/**
 * 
 * @author anhtran
 * This class defines thread for action send mail. Extended from Thread class.
 */
class SendMailThread extends Thread {
	// The server name or IP address
	private String server;
	// Login username of email account
	private String username;
	// Login password of email account
	private String password;
	// Name of sender
	private String sender;
	// Recipient's email address
	private String recipient;
	// Email subject
	private String subject;
	// Email message
	private String message;
	
	SendMailThread(String server, String username, String password,
			String sender, String recipient, String subject,String message) {
		this.server = server;
		this.username = username;
		this.password = password;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.message = message;
	}
	
	// Method to run the thread
	public void run() {
		
		// Initialize send mail action
		SendMailAction action = new SendMailAction(sender, recipient, subject, message);
		
		// Initialize session to connect to SMTP server
		Session session = action.getSmtpSession(server, username, password);
		
		// Flag to check email is sent successfully
		boolean isSuccessful = true;
		try {
			// Send email
			action.sendMail(session);
		} catch (MessagingException e) {
			// Show message dialog when sending errors
			JOptionPane.showMessageDialog(new JDialog(),
				    "Sending failed!\n"
				    + "There was an error when sending the email!\n" 
				    + e.getMessage(),
				    "Send Mail Error",
				    JOptionPane.ERROR_MESSAGE);
			// Set flag to false when sending errors
			isSuccessful = false;
		}
		// If sending is OK then show successful sending dialog
		if(isSuccessful) {
			JOptionPane.showMessageDialog(new JDialog(),
				    "Email is sent!",
				    "Success",
				    JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
