package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import commands.CommandsFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InitializeDocument {

	private JFrame frame;
	private JTextField titleTextField;
	private JTextField authorTextField;
	private CommandsFactory commandsFactory;
	
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitializeDocument window = new InitializeDocument();
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
	public InitializeDocument(CommandsFactory commandsFactory) {
		this.commandsFactory = commandsFactory;
		commandsFactory.setInit(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 260, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(28, 33, 80, 13);
		frame.getContentPane().add(lblNewLabel);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(118, 32, 96, 19);
		frame.getContentPane().add(titleTextField);
		titleTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Author");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(28, 109, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		authorTextField = new JTextField();
		authorTextField.setBounds(118, 108, 96, 19);
		frame.getContentPane().add(authorTextField);
		authorTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Create Document");
		btnNewButton.addActionListener(commandsFactory.createCommand("New Document"));
		btnNewButton.setBounds(53, 163, 136, 21);
		frame.getContentPane().add(btnNewButton);
	}

	public void setFrameVisible(boolean visible) {
		// TODO Auto-generated method stub
		frame.setVisible(visible);
	}

	public String getTitle() {
		return titleTextField.getText();
	}
	public String getAuthor() {
		return authorTextField.getText();
	}
}
