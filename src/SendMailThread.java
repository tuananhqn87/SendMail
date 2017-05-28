import javax.mail.MessagingException;
import javax.mail.Session;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

 class SendMailThread extends Thread {
	private String server;
	private String username;
	private String password;
	private String sender;
	private String recipient;
	private String subject;
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
	
	public void run() {
		SendMailAction action = new SendMailAction(sender, recipient, subject, message);
		Session session = action.getSmtpSession(server, username, password);
		boolean isSuccessful = true;
		try {
			action.sendMail(session);
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(new JDialog(),
				    "Sending failed!\n"
				    + "There was an error when sending the email!\n" 
				    + e.getMessage(),
				    "Send Mail Error",
				    JOptionPane.ERROR_MESSAGE);
			isSuccessful = false;
		}
		if(isSuccessful) {
			JOptionPane.showMessageDialog(new JDialog(),
				    "Email is sent!",
				    "Success",
				    JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
