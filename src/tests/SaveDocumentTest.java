package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.EditDocument;
import commands.NewDocument;
import commands.SaveDocument;
import view.InitializeDocument;
import view.SaveToFile;
import view.TextToSpeechEditorView;

class SaveDocumentTest {
	private static TextToSpeechEditorView editorView;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		editorView = new TextToSpeechEditorView("Free TTS Adapter");
		editorView.setTestMode(true);
		CommandsFactory commandsFactory = new CommandsFactory(editorView);
		InitializeDocument init = new InitializeDocument(commandsFactory);
		NewDocument newDocument = (NewDocument) commandsFactory.createCommand("New Document");
		newDocument.actionPerformed(null);
		
		editorView.setText("A simple text\nto test if\nedit document works");
		EditDocument editDocument = (EditDocument) commandsFactory.createCommand("Edit Document");
		editDocument.actionPerformed(null);
		
		SaveToFile saveToFile = new SaveToFile(commandsFactory);
		saveToFile.setFilename("test.txt");
		SaveDocument saveDocument = (SaveDocument) commandsFactory.createCommand("Save Document");
		saveDocument.actionPerformed(null);
	}

	@Test
	void testActionPerformed() {
		String expected = editorView.getCurrentDocument().toString();

		
		String filename = "test.txt";
		String actual = "";
		try {
			FileInputStream stream = new FileInputStream(filename);
			Scanner scanner = new Scanner(stream);
			if(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				actual =  line ;
			}
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				actual = actual + "\n" + line;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		assertEquals(expected, actual);
	}

}
