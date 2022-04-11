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
import text2speechapis.FakeTextToSpeechAPI;
import view.InitializeDocument;
import view.SaveToFile;
import view.SelectEncodingStrategy;
import view.TextToSpeechEditorView;

class DocumentToSpeechTest {
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
		CommandsFactory commandsFactory = new CommandsFactory(editorView);
		InitializeDocument init = new InitializeDocument(commandsFactory);
		selectEncodingStrategy = new SelectEncodingStrategy(commandsFactory);
		NewDocument newDocument = (NewDocument) commandsFactory.createCommand("New Document");
		newDocument.actionPerformed(null);
		
		editorView.setText("A simple text\nto test if\nedit document works");
		EditDocument editDocument = (EditDocument) commandsFactory.createCommand("Edit Document");
		editDocument.actionPerformed(null);
		
		documentToSpeech = (DocumentToSpeech) commandsFactory.createCommand("Document to Speech");
		tuneEncoding = (TuneEncoding) commandsFactory.createCommand("Tune Encoding");
				
	}

	@Test
	void testActionPerformed() {
		documentToSpeech.actionPerformed(null);
		String expected = editorView.getCurrentDocument().toString();
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testActionPerformedReverse() {
		editorView.setEncode(false);
		editorView.setReversed(true);
		documentToSpeech.actionPerformed(null);
		String expected = editorView.getCurrentDocument().reverseString();
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}
	
	@Test
	void testActionPerformedEncodeAtBash() {
		editorView.setEncode(true);
		editorView.setReversed(false);
		
		selectEncodingStrategy.setSelectedStrategy(0);
		tuneEncoding.actionPerformed(null);
		documentToSpeech.actionPerformed(null);
		String expected = "Z hrnkov gvcg\ngl gvhg ru\nvwrg wlxfnvmg dliph";
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}

	@Test
	void testActionPerformedEncodeRot13() {
		editorView.setEncode(true);
		editorView.setReversed(false);
		
		selectEncodingStrategy.setSelectedStrategy(1);
		tuneEncoding.actionPerformed(null);
		documentToSpeech.actionPerformed(null);
		String expected = "N fvzcyr grkg\ngb grfg vs\nrqvg qbphzrag jbexf";
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}
}
