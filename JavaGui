package cscd467;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;



public class JavaGui {

	private JFrame frame;
	private String pathToFile = "";
	private JTextArea zipLocationJTextArea;
	private JTextArea gradingJTextArea;
	private JButton btnSubmit;

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
				
					String filename = File.separator+"tmp";
					JFileChooser fc = new JFileChooser(new File(filename));
					// Show open dialog; this method does not return until the dialog is closed
					int value = fc.showOpenDialog(frame);
					if(value == JFileChooser.APPROVE_OPTION)
					{
						File file = fc.getSelectedFile();
						pathToFile = file.getAbsolutePath();
						if(isZipFile(file, pathToFile))
						{
						zipLocationJTextArea.setText(pathToFile);
						btnSubmit.setEnabled(true);
						}
						else
						{
						zipLocationJTextArea.setText("File must be of type zip");		
						btnSubmit.setEnabled(false);
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
				//NetClient netClient = new NetClient(pathToFile);
				//netClient.start();
				gradingJTextArea.setText("Your score was: 100/100");
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
	
	}


