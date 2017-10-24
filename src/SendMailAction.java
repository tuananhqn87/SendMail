
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class SendMailAction {
	/* Information to send toEmail */
	private String fromEmail;
    private String toEmail;
    private String subject;
    private String message;
    
    SendMailAction(String fromEmail, String toEmail, String subject, String message) {
			this.fromEmail = fromEmail;
			this.toEmail = toEmail;
			this.subject = subject;
			this.message = message;
    }
    
    /**
     * Method is to open the SMTP session from SMTP server
     * @param server The SMTP server name
     * @param username The user name of email account
     * @param password The password of email account
     * @return Return the session from SMTP server 
     */
    Session getSmtpSession(String server, String username, String password) {
    		
    		// Security values of SMTP connection
        final String JAVA_SSL_SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
        final String SECURED_SMTP_PORT = "465";
        final String ENABLE = "true";
        
        // Set SMTP connection properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", server);
        properties.put("mail.smtp.socketFactory.port", SECURED_SMTP_PORT);
        properties.put("mail.smtp.socketFactory.class", JAVA_SSL_SOCKET_FACTORY);
        properties.put("mail.smtp.auth", ENABLE);
        properties.put("mail.smtp.port", SECURED_SMTP_PORT);

        // Create the connection session with SMTP server
        Session session = Session.getInstance(properties,

                // Create an authenticator that is used to authenticate against server
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        return session;
    }

    
    /**
     * Method is to create MIME message and send the message
     * @param session The session to send email to.
     */
    void sendMail(Session session) throws MessagingException {
    	
        // Declares MIME message which will be sent
        MimeMessage mm = new MimeMessage(session);

        // Set email address which sends the mail from
        mm.setFrom(new InternetAddress(fromEmail));

        // Add recipients
        mm.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

        // Set the email subject
        mm.setSubject(subject, "utf-8");

        // Set the body content of email
        mm.setText(message, "utf-8", "html");
        // Set sent date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        mm.setSentDate(currentDate);
        
        //Send email
        Transport.send(mm);
    }
}
