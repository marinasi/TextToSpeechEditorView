package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Document;
import view.TextToSpeechEditorView;

public class DocumentToSpeech implements ActionListener {
	private TextToSpeechEditorView editorView;
	
	
	public DocumentToSpeech(TextToSpeechEditorView editorView) {
		this.editorView = editorView;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(editorView.isReplayMode() == false)
			editorView.getReplayManager().addCommand(this);
		Document document = editorView.getCurrentDocument();
		if(document == null) {
			JOptionPane.showMessageDialog(null, "Create a document first");
		}
		else {
			if(editorView.isEncoded()) {
				document.playEncodedContents();
			}
			else if(editorView.isReversed()) {
				document.playReverseContents();
			}
			else {
				document.playContents();
			}
		}
	}

}
