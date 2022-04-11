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

public class SelectAudioParameters {

	private JFrame frame;
	private JTextField volumeTextField;
	private JTextField pitchTextField;
	private JTextField rateTextField;
	private CommandsFactory commandsFactory;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectAudioParameters window = new SelectAudioParameters();
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
	public SelectAudioParameters() {
		initialize();
	}

	public SelectAudioParameters(CommandsFactory commandsFactory) {
		this.commandsFactory = commandsFactory;
		this.commandsFactory.setAudioParameters(this);
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Volume");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 46, 90, 13);
		frame.getContentPane().add(lblNewLabel);
		
		volumeTextField = new JTextField();
		volumeTextField.setBounds(150, 45, 96, 19);
		frame.getContentPane().add(volumeTextField);
		volumeTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pitch");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(23, 109, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		pitchTextField = new JTextField();
		pitchTextField.setBounds(150, 108, 96, 19);
		frame.getContentPane().add(pitchTextField);
		pitchTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Rate");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(23, 169, 45, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		rateTextField = new JTextField();
		rateTextField.setBounds(150, 168, 96, 19);
		frame.getContentPane().add(rateTextField);
		rateTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tune audio");
		btnNewButton.addActionListener(commandsFactory.createCommand("Tune Audio"));
		btnNewButton.setBounds(79, 220, 117, 21);
		frame.getContentPane().add(btnNewButton);
	}
	
	public void setFrameVisible(boolean visible) {
		// TODO Auto-generated method stub
		frame.setVisible(visible);
	}
	
	public int getVolume() {
		try {
			return Integer.parseInt(volumeTextField.getText());
		}
		catch(NumberFormatException e) {
			return -1;
		}	
	}
	
	public int getPitch() {
		try {
			return Integer.parseInt(pitchTextField.getText());
		}
		catch(NumberFormatException e) {
			return -1;
		}	
	}
	public int getRate() {
		try {
			return Integer.parseInt(rateTextField.getText());
		}
		catch(NumberFormatException e) {
			return -1;
		}	
	}

	public void setVolume(int i) {
		// TODO Auto-generated method stub
		volumeTextField.setText(i+"");
	}

	public void setPitch(int i) {
		// TODO Auto-generated method stub
		pitchTextField.setText(i+"");
	}

	public void setRate(int i) {
		// TODO Auto-generated method stub
		rateTextField.setText(i+"");
	}
}
