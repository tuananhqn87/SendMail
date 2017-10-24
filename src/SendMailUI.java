import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SendMailUI {
	
	// Exit on close button attribute
	private final static int EXIT_ON_CLOSE = JFrame.EXIT_ON_CLOSE;
	
	// Default dimensions of components of Grid Bag Constraint layout
	private final static int DEFAULT_WIDTH = 1;
	private final static int DEFAULT_HEIGHT = 1;
	
	// Panel's initial dimension values
	private final static int PANEL_WIDTH = 700;
	private final static int PANEL_HEIGHT = 700;
	
	// Title of frame
	private final static String TITLE = "Send Mail";
	
	// GridBagConstaints fill values
	private final static int FILL_HORIZONTAL = GridBagConstraints.HORIZONTAL;
	private final static int FILL_BOTH = GridBagConstraints.BOTH;
	private final static int FILL_NONE = GridBagConstraints.NONE;
	
	// UISetup object to setup UI
	private UISetup setup;
	
	// Labels' variables
	private JLabel lblSMTPServer, lblUsername, lblPassword, lblRecipient, lblSubject, lblMessage;
	// Text fields' variables
	private JTextField txtSMTPServer, txtUser, txtRecipient, txtSubject;
	
	// Password field
	private JPasswordField txtPassword;
	
	// Text field of message area
	private JTextArea txtMessageArea;
	// Scroll pane for message
	private JScrollPane messagePane;
	
	// Send button
	private JButton btnSend;
	
	
	SendMailUI () {
		// Initialize UISetup object
		setup = new UISetup();
		// Initialize UI components
		initComponents();
		// Add behaviors to components
		addBehaviors();
	}
	
	/**
	 * Create and show GUI on screen
	 */
	void createAndShowGUI () {
		
		// Create panel of send mail's form then set panel's size
		JPanel mailPanel = setup.createPanel(PANEL_WIDTH, PANEL_HEIGHT);
		// Add all component to panel
		addComponents(mailPanel);
		
		// Create the frame
		setup.createFrame(TITLE, EXIT_ON_CLOSE, mailPanel);
	}
	
	/**
	 * Method to initialize components' variables
	 */
	private void initComponents() {
		lblSMTPServer = new JLabel("SMTP Server: ");
		txtSMTPServer = new JTextField("smtp.gmail.com");
		
		lblUsername = new JLabel("Username: ");
		txtUser = new JTextField();
		
		lblPassword = new JLabel("Password: ");
		txtPassword = new JPasswordField();
		
		lblRecipient = new JLabel("To: ");
		txtRecipient = new JTextField();
		
		lblSubject = new JLabel("Subject: ");
		txtSubject = new JTextField();
		
		lblMessage = new JLabel("Message: ");
		txtMessageArea = new JTextArea();
		txtMessageArea.setFont(new Font("Serif", Font.TRUETYPE_FONT, 16));
		txtMessageArea.setLineWrap(true);
		txtMessageArea.setWrapStyleWord(true);
		messagePane = new JScrollPane(txtMessageArea);
		
		btnSend = new JButton("SEND");
	}
	
	/**
	 * Method to add behaviors to components
	 */
	private void addBehaviors() {
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// Get all strings in text fields
				String smtpServer = txtSMTPServer.getText().trim();
				String username = txtUser.getText().trim();
				
				@SuppressWarnings("deprecation")
				String password = txtPassword.getText();
				String recipient = txtRecipient.getText().trim();
				String subject = txtSubject.getText();
				String message = txtMessageArea.getText();
				
				// Initialize send mail event
				SendMailEvent sendMailEvent = new SendMailEvent(this);
				sendMailEvent.setAuthenInfo(smtpServer, username, password);
				sendMailEvent.setEmailContent(username, recipient, subject, message);
				sendMailEvent.sendMail();
				
			}
		});
	}
	
	/**
	 * Method to add all components to content pane, 
	 * components will be arranged accordingly on the pane
	 * @param contentPane The content pane where components are arranged on.
	 */
	private void addComponents(Container contentPane) {
		JComponent components[] = { lblSMTPServer,
									txtSMTPServer,
									lblUsername,
									txtUser,
									lblPassword,
									txtPassword,
									lblRecipient,
									txtRecipient,
									lblSubject,
									txtSubject,
									lblMessage,
									messagePane,
									btnSend
								};

		GridBagConstraints constraints[] = {
				// lblSMTPServer constraints
				setup.getContraints(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, 0.0, 0.0, FILL_HORIZONTAL),
				// txtSMTPServer constraints
				setup.getContraints(1, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1.0, 0.0, FILL_HORIZONTAL),
				// lblUsername constraints
				setup.getContraints(0, 1, DEFAULT_WIDTH, DEFAULT_HEIGHT, 0, 0, FILL_HORIZONTAL),
				// txtUser constraints
				setup.getContraints(1, 1, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1.0, 0, FILL_HORIZONTAL),
				// lblPassword constraints
				setup.getContraints(0, 2, DEFAULT_WIDTH, DEFAULT_HEIGHT, 0, 0, FILL_HORIZONTAL),
				// txtPassword constraints
				setup.getContraints(1, 2, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1, 0, FILL_HORIZONTAL),
				// lblRecipient constraints
				setup.getContraints(0, 3, DEFAULT_WIDTH, DEFAULT_HEIGHT, 0.0, 0.0, FILL_HORIZONTAL),
				// txtRecipient constraints
				setup.getContraints(1, 3, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1.0, 0, FILL_HORIZONTAL),
				// lblSubject constraints
				setup.getContraints(0, 4, DEFAULT_WIDTH, DEFAULT_HEIGHT, 0, 0, FILL_HORIZONTAL),
				// txtSubject constraints
				setup.getContraints(1, 4, DEFAULT_WIDTH, DEFAULT_HEIGHT, 1, 0, FILL_HORIZONTAL),
				// lblMessage constraints
				setup.getContraints(0, 5, 2, DEFAULT_HEIGHT, 0.0, 0.0, FILL_HORIZONTAL),
				// messagePane constraints
				setup.getContraints(0, 6, 2, DEFAULT_HEIGHT, 1.0, 1.0, FILL_BOTH),
				// btnSend constraints
				setup.getContraints(0, 7, 2, DEFAULT_HEIGHT, 0.0, 0.0, FILL_NONE)
			};
		
		// Combine components with their constraints and add them to pane
		for (int i = 0; i < components.length; i++) {
			contentPane.add(components[i], constraints[i]);
		}
	}
}
