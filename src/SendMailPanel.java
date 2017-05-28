import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SendMailPanel {
	private final static int DEFAULT_PADDING = 5;
	private final static int DEFAULT_WIDTH = 1;
	private final static int DEFAULT_HEIGHT = 1;
	
	private JTextField txtSMTPServer;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JTextField txtRecipient;
	private JTextField txtSubject;
	private JTextArea txtMessageArea;
	private JScrollPane messagePane;
	
	private JButton btnSend;
	
	
	SendMailPanel () {
		initComponents();
		addBehaviors();
	}
	
	/**
	 * Create and show GUI on screen
	 */
	void createAndShowGUI () {
		
		// Create form's frame and set layout
		JFrame frame  = new JFrame("Send Mail");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		// Create panel of send mail's form then set panel's size
		JPanel mailPanel = new JPanel();
		Dimension panelSize = mailPanel.getPreferredSize();
		panelSize.width = 700;
		panelSize.height = 700;
		mailPanel.setPreferredSize(panelSize);
		
		// Add all component to panel
		addComponents(mailPanel);
		
		// Add panel to frame
		frame.add(mailPanel);
		
		// Packing frame with components
		frame.pack();
		// Set location of frame after packing
		setFrameLocationOnScreen(frame);
		// Show the frame
		frame.setVisible(true);
	}
	
	/**
	 * Method to set location of a frame on screen when it's shown
	 * @param frame The frame object which will be set location
	 */
	private void setFrameLocationOnScreen(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width/2 - frame.getSize().width/2, 
						  screenSize.height/2 - frame.getSize().height/2);
	}
	
	/**
	 * Method to initialize components' variables
	 */
	private void initComponents() {
		txtSMTPServer = new JTextField("smtp.gmail.com");
		txtUser = new JTextField();
		txtPassword = new JPasswordField();
		txtRecipient = new JTextField();
		txtSubject = new JTextField();
		
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
		contentPane.setLayout(new GridBagLayout());
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		// Add SMTP server components
		addComponentToPane (contentPane, new JLabel("SMTP Server: "), 
							0, 0,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							0, 0,
							GridBagConstraints.HORIZONTAL);
		addComponentToPane (contentPane, txtSMTPServer, 
							1, 0,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							1, 0,
							GridBagConstraints.HORIZONTAL);
		
		// Add SMTP user name components
		addComponentToPane (contentPane, new JLabel("Username: "), 
							0, 1,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							0, 0,
							GridBagConstraints.HORIZONTAL);
		addComponentToPane (contentPane, txtUser, 
							1, 1,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							1, 0,
							GridBagConstraints.HORIZONTAL);
		
		// Add SMTP password components
		addComponentToPane (contentPane, new JLabel("Password: "), 
							0, 2,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							0, 0,
							GridBagConstraints.HORIZONTAL);
		addComponentToPane (contentPane, txtPassword, 
							1, 2,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							1, 0,
							GridBagConstraints.HORIZONTAL);
		
		// Add recipient components
		addComponentToPane (contentPane, new JLabel("To: "), 
							0, 3,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							0, 0,
							GridBagConstraints.HORIZONTAL);
		addComponentToPane (contentPane, txtRecipient, 
							1, 3,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							1, 0,
							GridBagConstraints.HORIZONTAL);
		
		// Add subject components
		addComponentToPane (contentPane, new JLabel("Subject: "), 
							0, 4,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							0, 0,
							GridBagConstraints.HORIZONTAL);
		addComponentToPane (contentPane, txtSubject, 
							1, 4,
							DEFAULT_WIDTH, DEFAULT_HEIGHT,
							1, 0,
							GridBagConstraints.HORIZONTAL);
		
		// Add message components
		addComponentToPane (contentPane, new JLabel("Message: "), 
							0, 5,
							2, DEFAULT_HEIGHT,
							0, 0,
							GridBagConstraints.HORIZONTAL);
		addComponentToPane (contentPane, messagePane, 
							0, 6,
							2, DEFAULT_HEIGHT,
							1.0, 1.0,
							GridBagConstraints.BOTH);
		
		// Add send button components
		addComponentToPane (contentPane, btnSend, 
							0, 7,
							2, DEFAULT_HEIGHT,
							0, 0,
							GridBagConstraints.BASELINE);
	}
	
	/**
	 * Method to add each component to content pane with constraint arguments
	 * 
	 * @param contentPane The content pane where component to be set to
	 * @param component The component to be set
	 * @param gridx The coordinate X
	 * @param gridy The coordinate Y
	 * @param gridwidth The number of columns that component spans
	 * @param gridheight The number of rows that component spans
	 * @param weightx The horizontal weight of component
	 * @param weighty The vertical weight of component
	 * @param constraintFill The constraint fill of GridBagConstraint
	 */
	private void addComponentToPane (Container contentPane, JComponent component, 
							int gridx, int gridy, 
							int gridwidth, int gridheight,
							double weightx, double weighty,
							int constraintFill) {
		// Create new GridBag constraint
		GridBagConstraints c = new GridBagConstraints();
		
		// Set constraint's position
		c.gridx = gridx;
		c.gridy = gridy;
		
		// Set constraint's dimension
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		
		// Set constraint weights
		c.weightx = weightx;
		c.weighty = weighty;
		
		// Set constraint's fill
		c.fill = constraintFill;
		
		// Set constraint Insets
		c.insets = new Insets(DEFAULT_PADDING,DEFAULT_PADDING,DEFAULT_PADDING,DEFAULT_PADDING);
		
		contentPane.add(component, c);
	}

}
