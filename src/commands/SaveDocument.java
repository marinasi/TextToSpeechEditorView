package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import model.Document;
import view.SaveToFile;
import view.TextToSpeechEditorView;

public class SaveDocument implements ActionListener {
	private TextToSpeechEditorView editorView;
	private SaveToFile saveToFile;
	
	public SaveDocument(TextToSpeechEditorView editorView, SaveToFile saveToFile) {
		this.editorView = editorView;
		this.saveToFile = saveToFile;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(editorView.isReplayMode() == false)
			editorView.getReplayManager().addCommand(this);
		Document document = editorView.getCurrentDocument();
		
		String filename = saveToFile.getFilename();

		saveToFile.setFrameVisible(false);

		try {
			FileOutputStream stream = new FileOutputStream(filename);
			PrintWriter writer = new PrintWriter(stream);
			String documentContents = document.toString();
			writer.print(documentContents);
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File not found");
		}
	}

}
