package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import commands.CommandsFactory;
import model.Line;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SelectEncodingStrategy {

	private JFrame frame;
	private CommandsFactory commandsFactory;
	private JComboBox comboBox = new JComboBox();
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayLine window = new PlayLine();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/


	/**
	 * Create the application.
	 */
	
	public SelectEncodingStrategy() {
		initialize();
	}
	/**
	 * @wbp.parser.constructor
	 */

	public SelectEncodingStrategy(CommandsFactory commandsFactory) {
		// TODO Auto-generated constructor stub
		this.commandsFactory = commandsFactory;
		this.commandsFactory.setSelectStrategy(this);
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 360, 160);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox.setBounds(20, 33, 310, 21);

		setItems();
		frame.getContentPane().add(comboBox);
		
		JButton encodeButton = new JButton("Set encoding strategy");
		encodeButton.addActionListener(commandsFactory.createCommand("Tune Encoding"));
		encodeButton.setBounds(91, 82, 138, 21);
		frame.getContentPane().add(encodeButton);
	}

	public void setItems() {
		comboBox.removeAllItems();
		comboBox.addItem("AtBash");
		comboBox.addItem("Rot13");
	}
	
	public String getSelectedStrategy() {
		return (String) comboBox.getSelectedItem();
	}

	public void setFrameVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}

	public void setSelectedStrategy(int index) {
		comboBox.setSelectedIndex(index);
	}
}
