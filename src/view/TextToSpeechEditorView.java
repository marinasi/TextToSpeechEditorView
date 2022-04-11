
package view;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import commands.CommandsFactory;
import commands.ReplayManager;
import model.Document;
import text2speechapis.FreeTTSAdapter;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;

public class TextToSpeechEditorView {

	private JFrame frame;
	private Document currentDocument;
	private ReplayManager replayManager = new ReplayManager();
	private JTextArea textArea = new JTextArea();
	private TextToSpeechAPI audioManager;
	private CommandsFactory commandsFactory = new CommandsFactory(this);
	private InitializeDocument init = new InitializeDocument(commandsFactory);
	private SaveToFile saveToFile = new SaveToFile(commandsFactory);
	private PlayLine playLine = new PlayLine(commandsFactory, this);
	private SelectEncodingStrategy selectStrategy = new SelectEncodingStrategy(commandsFactory);
	private SelectAudioParameters audioParameters = new SelectAudioParameters(commandsFactory);
	private JFileChooser chooser = new JFileChooser(".");
	private JCheckBoxMenuItem menuEncode = new JCheckBoxMenuItem("Encoded");
	private JCheckBoxMenuItem menuReverse = new JCheckBoxMenuItem("Reverse");
	private boolean testMode = false;
	private boolean replayMode = false;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextToSpeechEditorView window = new TextToSpeechEditorView("Free TTS Adapter");
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
	public TextToSpeechEditorView(String apiName) {
		TextToSpeechAPIFactory apiFactory = new TextToSpeechAPIFactory();
		this.audioManager = apiFactory.createTTSAPI(apiName);
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 597, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 583, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem newFile = new JMenuItem("New");
		newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init.setFrameVisible(true);
			}
		});
		file.add(newFile);
		
		JMenuItem openFile = new JMenuItem("Open file");
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int button = chooser.showDialog(null, "Open file");
				if(button == JFileChooser.APPROVE_OPTION) {
					ActionListener openDocument = commandsFactory.createCommand("Open Document");
					openDocument.actionPerformed(null);
				}
			}
		});
		file.add(openFile);
		
		JMenuItem saveFile = new JMenuItem("Save");
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentDocument == null) {
					JOptionPane.showMessageDialog(null, "Create a document first");
				}
				else {
					saveToFile.setFrameVisible(true);
				}
			}
		});
		file.add(saveFile);
		
		JMenuItem edit = new JMenuItem("Edit");
		edit.addActionListener(commandsFactory.createCommand("Edit Document"));
		menuBar.add(edit);
		
		JMenuItem playDocument = new JMenuItem("Play document");
		playDocument.addActionListener(commandsFactory.createCommand("Document to Speech"));
		menuBar.add(playDocument);
		
		JMenuItem menuPlayLine = new JMenuItem("Play Line");
		menuPlayLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentDocument == null) {
					JOptionPane.showMessageDialog(null, "Create a document first");
				}
				else if(currentDocument.contentsSize() == 0) {
					JOptionPane.showMessageDialog(null, "The document has no lines");
				}
				else {
					playLine.setFrameVisible(true);
					playLine.setItems();
				}
			}
		});
		menuBar.add(menuPlayLine);
		
		JMenu mnNewMenu = new JMenu("Play type");
		menuBar.add(mnNewMenu);
		menuEncode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuReverse.setSelected(false);
				if(menuEncode.isSelected() == true)
					selectStrategy.setFrameVisible(true);
			}
		});
		
		mnNewMenu.add(menuReverse);
		
		mnNewMenu.add(menuEncode);
		
		JMenuItem tuneAudio = new JMenuItem("Tune audio");
		tuneAudio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				audioParameters.setFrameVisible(true);
			}
		});
		menuBar.add(tuneAudio);
		
		JMenuItem menuReplay = new JMenuItem("Replay");
		menuReplay.addActionListener(commandsFactory.createCommand("Replay Command"));
		menuBar.add(menuReplay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 563, 284);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(textArea);
	}

	public TextToSpeechAPI getAudioManager() {
		// TODO Auto-generated method stub
		return audioManager;
	}

	public void setCurrentDocument(Document document) {
		// TODO Auto-generated method stub
		this.currentDocument = document;
	}

	public void setInitFrameVisible(boolean b) {
		// TODO Auto-generated method stub
		init.setFrameVisible(b);
	}
	public void setSaveFrameVisible(boolean b) {
		// TODO Auto-generated method stub
		saveToFile.setFrameVisible(b);
	}

	public String getText() {
		// TODO Auto-generated method stub
		return textArea.getText();
	}

	public Document getCurrentDocument() {
		// TODO Auto-generated method stub
		return currentDocument;
	}
	
	public String getFilename() {
		return chooser.getSelectedFile().getAbsolutePath();
	}

	public void setFilename(String filename) {
		File file = new File(filename);
		chooser.setSelectedFile(file);
	}
	public void setText(String text) {
		// TODO Auto-generated method stub
		textArea.setText(text);
	}
	
	public boolean isReversed() {
		return menuReverse.isSelected();
	}
	
	public boolean isEncoded() {
		return menuEncode.isSelected();
	}

	public void setAudioFrameVisible(boolean b) {
		// TODO Auto-generated method stub
		audioParameters.setFrameVisible(false);
	}
	
	public boolean isTestMode() {
		return testMode;
	}
	
	public void setTestMode(boolean testMode) {
		this.testMode = testMode;
	}

	public void setReversed(boolean b) {
		// TODO Auto-generated method stub
		menuReverse.setSelected(b);
	}

	public void setEncode(boolean b) {
		// TODO Auto-generated method stub
		menuEncode.setSelected(b);
	}
	
	public ReplayManager getReplayManager() {
		return replayManager;
	}

	public boolean isReplayMode() {
		return replayMode;
	}

	public void setReplayMode(boolean replayMode) {
		this.replayMode = replayMode;
	}
	
}
