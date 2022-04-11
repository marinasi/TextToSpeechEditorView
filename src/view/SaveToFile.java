package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import commands.CommandsFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SaveToFile {

	private JFrame frame;
	private JTextField filenameTextField;
	private CommandsFactory commandsFactory;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveDocument window = new SaveDocument();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public SaveToFile(CommandsFactory commandsFactory) {
		this.commandsFactory = commandsFactory;
		commandsFactory.setSave(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 217, 232);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filename");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(36, 31, 131, 26);
		frame.getContentPane().add(lblNewLabel);
		
		filenameTextField = new JTextField();
		filenameTextField.setBounds(36, 85, 131, 19);
		frame.getContentPane().add(filenameTextField);
		filenameTextField.setColumns(10);
		
		JButton saveButton = new JButton("Save Document");
		saveButton.addActionListener(commandsFactory.createCommand("Save Document"));
		saveButton.setBounds(36, 143, 131, 21);
		frame.getContentPane().add(saveButton);
	}
	
	public String getFilename() {
		return filenameTextField.getText();
	}
	
	public void setFrameVisible(boolean visible) {
		// TODO Auto-generated method stub
		frame.setVisible(visible);
	}

	public void setFilename(String filename) {
		filenameTextField.setText(filename);
	}
}
