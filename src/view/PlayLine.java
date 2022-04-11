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

public class PlayLine {

	private JFrame frame;
	private CommandsFactory commandsFactory;
	private JComboBox comboBox = new JComboBox();
	private TextToSpeechEditorView editorView;
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
	public PlayLine() {
		initialize();
	}
	/**
	 * @wbp.parser.constructor
	 */

	public PlayLine(CommandsFactory commandsFactory, TextToSpeechEditorView editorView) {
		// TODO Auto-generated constructor stub
		this.commandsFactory = commandsFactory;
		commandsFactory.setPlayLine(this);
		this.editorView = editorView;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 171);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox.setBounds(10, 25, 416, 21);
		
		frame.getContentPane().add(comboBox);
		
		JButton playButton = new JButton("Play Line");
		playButton.addActionListener(commandsFactory.createCommand("Line to Speech"));
		playButton.setBounds(174, 88, 85, 21);
		frame.getContentPane().add(playButton);
	}

	public void setItems() {
		comboBox.removeAllItems();
		ArrayList<Line> contents = editorView.getCurrentDocument().getContents();
		//comboBox = new JComboBox(contents);
		for(int i = 0; i < contents.size(); i++) {
			Line line = contents.get(i);
			comboBox.addItem(line.toString());
		}

	}
	public int getSelectedLine() {
		return comboBox.getSelectedIndex();
	}

	public void setFrameVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(true);
	}

	public void setSelectedLine(int i) {
		// TODO Auto-generated method stub
		comboBox.setSelectedIndex(i);
	}
}
