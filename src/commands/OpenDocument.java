package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Document;
import text2speechapis.TextToSpeechAPI;
import view.TextToSpeechEditorView;

public class OpenDocument implements ActionListener {
	private TextToSpeechEditorView editorView;
	
	
	public OpenDocument(TextToSpeechEditorView editorView) {
		this.editorView = editorView;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(editorView.isReplayMode() == false)
			editorView.getReplayManager().addCommand(this);
		String title = "";
		String author = "";
		TextToSpeechAPI audioManager = editorView.getAudioManager();
		Document document = new Document(title, author, audioManager);
		editorView.setCurrentDocument(document);
		
		String filename = editorView.getFilename();
		String text = "";
		try {
			FileInputStream stream = new FileInputStream(filename);
			Scanner scanner = new Scanner(stream);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				document.addLine(line);
				text = text + line + "\n";
			}
			
			editorView.setText(text);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
