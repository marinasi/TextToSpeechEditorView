package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.NewDocument;
import commands.SaveDocument;
import commands.TuneEncoding;
import encodingstrategies.AtBashEncoding;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.Rot13Encoding;
import text2speechapis.FakeTextToSpeechAPI;
import view.InitializeDocument;
import view.SaveToFile;
import view.SelectEncodingStrategy;
import view.TextToSpeechEditorView;

class TuneEncodingTest {
	private static TextToSpeechEditorView editorView;
	private static TuneEncoding tuneEncoding;
	private static SelectEncodingStrategy selectEncodingStrategy;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		editorView = new TextToSpeechEditorView("Fake Text to Speech");
		editorView.setTestMode(true);
		
		CommandsFactory commandsFactory = new CommandsFactory(editorView);
		InitializeDocument init = new InitializeDocument(commandsFactory);
		selectEncodingStrategy = new SelectEncodingStrategy(commandsFactory);
		NewDocument newDocument = (NewDocument) commandsFactory.createCommand("New Document");
		newDocument.actionPerformed(null);
		
		tuneEncoding = (TuneEncoding) commandsFactory.createCommand("Tune Encoding");				
	}

	
	@Test
	void testActionPerformedEncodeAtBash() {
		editorView.setEncode(true);
		
		selectEncodingStrategy.setSelectedStrategy(0);
		tuneEncoding.actionPerformed(null);
		EncodingStrategy encodingStrategy = editorView.getCurrentDocument().getEncodingStrategy();
		
		assertTrue(encodingStrategy instanceof AtBashEncoding);
	}

	@Test
	void testActionPerformedEncodeRot13() {
		editorView.setEncode(true);
		
		selectEncodingStrategy.setSelectedStrategy(1);
		tuneEncoding.actionPerformed(null);
		EncodingStrategy encodingStrategy = editorView.getCurrentDocument().getEncodingStrategy();
		
		assertTrue(encodingStrategy instanceof Rot13Encoding);
	}
}
