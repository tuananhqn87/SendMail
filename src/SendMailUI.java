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
	private final static int EXIT_ON_CLOSE = JFrame.EXIT_ON_CLOSE;
	private final static int DEFAULT_WIDTH = 1;
	private final static int DEFAULT_HEIGHT = 1;
	
	private final static int PANEL_WIDTH = 700;
	private final static int PANEL_HEIGHT = 700;
	
	private final static String TITLE = "Send Mail";
	private final static int FILL_HORIZONTAL = GridBagConstraints.HORIZONTAL;
	private final static int FILL_BOTH = GridBagConstraints.BOTH;
	private final static int FILL_NONE = GridBagConstraints.NONE;
	
	private JLabel lblSMTPServer;
	
	private JTextField txtSMTPServer;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JTextField txtRecipient;
	private JTextField txtSubject;
	private JTextArea txtMessageArea;
	private JScrollPane messagePane;
	
	private JButton btnSend;
	private UISetup setup;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblRecipient;
	private JLabel lblSubject;
	private JLabel lblMessage;
	
	
	SendMailUI () {
		setup = new UISetup();
		initComponents();
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
	 * Add behaviors of components
	 */
	private void addBehaviors() {
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String smtpServer = txtSMTPServer.getText().trim();
				String username = txtUser.getText().trim();
				
				@SuppressWarnings("deprecation")
				String password = txtPassword.getText();
				String recipient = txtRecipient.getText().trim();
				String subject = txtSubject.getText();
				String message = txtMessageArea.getText();
				
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
		
		for (int i = 0; i < components.length; i++) {
			contentPane.add(components[i], constraints[i]);
		}
	}
}
