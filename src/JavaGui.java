import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;

public class JavaGui {

	private JFrame frame;
	private String pathToFile = "";
	private JTextArea zipLocationJTextArea;
	private JTextArea gradingJTextArea;
	private JButton btnSubmit;
	private String filename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaGui window = new JavaGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel DescriptionJLabel = new JLabel("Please submit a single .zip file");
		springLayout.putConstraint(SpringLayout.NORTH, DescriptionJLabel, 20, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, DescriptionJLabel, 113, SpringLayout.WEST, frame.getContentPane());
		DescriptionJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(DescriptionJLabel);
		
		final JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				
					filename = File.separator+"tmp";
					JFileChooser fc = new JFileChooser(new File(filename));
					// Show open dialog; this method does not return until the dialog is closed
					int value = fc.showOpenDialog(frame);
					if(value == JFileChooser.APPROVE_OPTION)
					{
						File file = fc.getSelectedFile();
						pathToFile = file.getAbsolutePath();
						if(isZipFile(file, pathToFile)) //if Zip file
						{
							zipLocationJTextArea.setText(pathToFile); //Show the user the path to the file
							btnSubmit.setEnabled(true);	//allow submissions
						}
						else //Not a zip file
						{
							zipLocationJTextArea.setText(""); //reset path
							JOptionPane.showMessageDialog(getFrame(), "File must be .zip");	//display message
							btnSubmit.setEnabled(false); //disallow submissions
						}
					}
				}
				catch(Exception ex) {}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnBrowse, 32, SpringLayout.SOUTH, DescriptionJLabel);
		springLayout.putConstraint(SpringLayout.EAST, btnBrowse, -80, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnBrowse);
		
		zipLocationJTextArea = new JTextArea();
		zipLocationJTextArea.setEditable(false);
		zipLocationJTextArea.setLineWrap(true);
		springLayout.putConstraint(SpringLayout.NORTH, zipLocationJTextArea, 33, SpringLayout.SOUTH, DescriptionJLabel);
		springLayout.putConstraint(SpringLayout.WEST, zipLocationJTextArea, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, zipLocationJTextArea, 251, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(zipLocationJTextArea);
		
		gradingJTextArea = new JTextArea();
		gradingJTextArea.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, gradingJTextArea, 37, SpringLayout.SOUTH, btnBrowse);
		springLayout.putConstraint(SpringLayout.WEST, gradingJTextArea, 76, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, gradingJTextArea, -6, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, gradingJTextArea, 136, SpringLayout.EAST, DescriptionJLabel);
		frame.getContentPane().add(gradingJTextArea);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSubmit.setEnabled(false);
				btnBrowse.setEnabled(false);
				//Client netClient = new Client(filename, pathToFile);
				//netClient.start();
				NetClient netClient = new NetClient(pathToFile);
				netClient.start();
				JOptionPane.showMessageDialog(getFrame(), ".zip successfully submitted");	
				gradingJTextArea.setText("Your estimated score is: ");
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnSubmit, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, -52, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnSubmit);
		}
		catch(Exception exp) {}
	}
	public static boolean isZipFile(File file, String filename) throws IOException {
	    return filename.toLowerCase().endsWith(".zip");
	  }
	
	

	public JFrame getFrame()
	{
		return this.frame;
	}
}

