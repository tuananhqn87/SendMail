
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UISetup {
	
	// Default UI padding
	private final static int DEFAULT_PADDING = 5;

	public UISetup(){
		
	}
	
	// Method to create panel
	JPanel createPanel(int width, int height) {
		// Create panel of send mail's form then set panel's size
		JPanel chatPanel = new JPanel();
		
		// Get panel's size
		Dimension panelSize = chatPanel.getPreferredSize();
		
		// Set panel size's dimensions
		panelSize.width = width;
		panelSize.height = height;
		
		// Change panel's size
		chatPanel.setPreferredSize(panelSize);
		// Set panel layout
		chatPanel.setLayout(new GridBagLayout());
		// Set panel's orientation
		chatPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		// Return the panel
		return chatPanel;
	}
	
	/**
	 * Method to create frame
	 * 
	 * @param title 			Title of frame
	 * @param closeOperation Close operation
	 * @param panel 			The panel of frame
	 * @return 				Return the frame
	 */
	JFrame createFrame(String title, int closeOperation, JPanel panel) {
		// Create form's frame and set layout
		JFrame frame  = new JFrame(title);
		frame.setDefaultCloseOperation(closeOperation);
		frame.setLayout(new BorderLayout());
		
		// Add panel to frame
		frame.add(panel);
		
		// Packing frame with components
		frame.pack();
		// Set location of frame after packing
		setFrameLocationOnScreen(frame);
		// Show the frame
		frame.setVisible(true);
		
		return frame;
	}
	
	/**
	 * Method to set location of a frame on screen when it's shown
	 * 
	 * @param frame 		The frame object which will be set location
	 */
	private void setFrameLocationOnScreen(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width/2 - frame.getSize().width/2, 
						  screenSize.height/2 - frame.getSize().height/2);
	}
	
	/**
	 * Method to get GridBagConstraints for a component with given values
	 * 
	 * @param gridx 			Column x
	 * @param gridy 			Row y
	 * @param gridwidth 		Number of column which component will take
	 * @param gridheight 	Number of row which component will take
	 * @param weightx 		Horizontal component's weight
	 * @param weighty 		Vertical component's weight
	 * @param constraintFill Component's fill
	 * 
	 * @return 				GridBagConstraints object of a component
	 */
	GridBagConstraints getContraints(int gridx, int gridy, 
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
		
		return c;
	}
}
