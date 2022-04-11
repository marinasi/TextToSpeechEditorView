package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Document;
import view.TextToSpeechEditorView;

public class EditDocument implements ActionListener {
	private TextToSpeechEditorView editorView;
	
	
	public EditDocument(TextToSpeechEditorView editorView) {
		this.editorView = editorView;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		if(editorView.isReplayMode() == false)
			editorView.getReplayManager().addCommand(this);
		Document document = editorView.getCurrentDocument();
		if(document == null && editorView.isTestMode() == false) {
			JOptionPane.showMessageDialog(null, "Create a document first");
		}
		else {
			document.clearContents();
			String textArea = editorView.getText();
			String[] lines = textArea.split("\n");
			
			for(int i = 0; i < lines.length; i++) {
				document.addLine(lines[i]);
			}
			
			if(editorView.isTestMode() == false)
				JOptionPane.showMessageDialog(null, "Edit complete");
		}
	}
}
