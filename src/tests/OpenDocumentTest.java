package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.OpenDocument;
import view.TextToSpeechEditorView;

class OpenDocumentTest {
	private static TextToSpeechEditorView editorView;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		editorView = new TextToSpeechEditorView("Free TTS Adapter");
		editorView.setFilename("test.txt");
		
		CommandsFactory commandsFactory = new CommandsFactory(editorView);
		OpenDocument openDocument = (OpenDocument) commandsFactory.createCommand("Open Document");
		openDocument.actionPerformed(null);
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
