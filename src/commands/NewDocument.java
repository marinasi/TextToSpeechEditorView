package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Document;
import text2speechapis.TextToSpeechAPI;
import view.InitializeDocument;
import view.TextToSpeechEditorView;

public class NewDocument implements ActionListener {
	private TextToSpeechEditorView editorView;
	private InitializeDocument init;
	
	public NewDocument(TextToSpeechEditorView editorView, InitializeDocument init) {
		this.editorView = editorView;
		this.init = init;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(editorView.isReplayMode() == false)
			editorView.getReplayManager().addCommand(this);
		String title = init.getTitle();
		String author = init.getAuthor();
		
		if(title.equals("")  && editorView.isTestMode() == false) {
			JOptionPane.showMessageDialog(null, "Give a title");
		}
		else if(author.equals("")  && editorView.isTestMode() == false) {
			JOptionPane.showMessageDialog(null, "Give an author");
		}
		else {
			TextToSpeechAPI audioManager = editorView.getAudioManager();
			Document document = new Document(title, author, audioManager);
			editorView.setCurrentDocument(document);
			editorView.setInitFrameVisible(false);
		}
		
		
	}

}
