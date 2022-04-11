package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.NewDocument;
import commands.OpenDocument;
import commands.SaveDocument;
import commands.TuneEncoding;
import encodingstrategies.AtBashEncoding;
import encodingstrategies.EncodingStrategy;
import text2speechapis.FakeTextToSpeechAPI;
import view.InitializeDocument;
import view.SaveToFile;
import view.SelectEncodingStrategy;
import view.TextToSpeechEditorView;

class ReplayManagerTest {
	private static TextToSpeechEditorView editorView;
	private static DocumentToSpeech documentToSpeech;
	private static FakeTextToSpeechAPI audioManager;
	private static TuneEncoding tuneEncoding;
	private static SelectEncodingStrategy selectEncodingStrategy;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		editorView = new TextToSpeechEditorView("Fake Text to Speech");
		editorView.setTestMode(true);
		audioManager = (FakeTextToSpeechAPI) editorView.getAudioManager();
		editorView.setFilename("test.txt");
		
		CommandsFactory commandsFactory = new CommandsFactory(editorView);
		OpenDocument openDocument = (OpenDocument) commandsFactory.createCommand("Open Document");
		openDocument.actionPerformed(null);
		
		documentToSpeech = (DocumentToSpeech) commandsFactory.createCommand("Document to Speech");
		documentToSpeech.actionPerformed(null);
		
		selectEncodingStrategy = new SelectEncodingStrategy(commandsFactory);

		selectEncodingStrategy.setSelectedStrategy(0);
		tuneEncoding = (TuneEncoding) commandsFactory.createCommand("Tune Encoding");
		tuneEncoding.actionPerformed(null);
		
		SaveToFile saveToFile = new SaveToFile(commandsFactory);
		saveToFile.setFilename("test2.txt");
		SaveDocument saveDocument = (SaveDocument) commandsFactory.createCommand("Save Document");
		saveDocument.actionPerformed(null);		
	}

	@Test
	void testActionPerformedOpen() {
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
	
	@Test
	void testActionPerformedDocumentToSpeech() {
		
		String expected = editorView.getCurrentDocument().toString();
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}
	
	@Test
	void testActionPerformedEncodeAtBash() {
		editorView.setEncode(true);
		EncodingStrategy encodingStrategy = editorView.getCurrentDocument().getEncodingStrategy();
		
		assertTrue(encodingStrategy instanceof AtBashEncoding);
	}
	
	@Test
	void testActionPerformedSaveDocument() {
		String expected = editorView.getCurrentDocument().toString();

		
		String filename = "test2.txt";
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
