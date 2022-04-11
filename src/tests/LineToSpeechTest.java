package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.LineToSpeech;
import commands.NewDocument;
import commands.SaveDocument;
import commands.TuneEncoding;
import text2speechapis.FakeTextToSpeechAPI;
import view.InitializeDocument;
import view.PlayLine;
import view.SaveToFile;
import view.SelectEncodingStrategy;
import view.TextToSpeechEditorView;

class LineToSpeechTest {
	private static TextToSpeechEditorView editorView;
	private static LineToSpeech lineToSpeech;
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
		
		PlayLine playLine = new PlayLine(commandsFactory, editorView);
		playLine.setItems();
		playLine.setSelectedLine(2);
		lineToSpeech = (LineToSpeech) commandsFactory.createCommand("Line to Speech");
		tuneEncoding = (TuneEncoding) commandsFactory.createCommand("Tune Encoding");
				
	}

	@Test
	void testActionPerformed() {
		lineToSpeech.actionPerformed(null);
		String expected = editorView.getCurrentDocument().getContents().get(2).toString();
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testActionPerformedReverse() {
		editorView.setEncode(false);
		editorView.setReversed(true);
		lineToSpeech.actionPerformed(null);
		String expected = editorView.getCurrentDocument().getContents().get(2).reverseString();
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}
	
	@Test
	void testActionPerformedEncodeAtBash() {
		editorView.setEncode(true);
		editorView.setReversed(false);
		
		selectEncodingStrategy.setSelectedStrategy(0);
		tuneEncoding.actionPerformed(null);
		lineToSpeech.actionPerformed(null);
		String expected = "vwrg wlxfnvmg dliph";
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}

	@Test
	void testActionPerformedEncodeRot13() {
		editorView.setEncode(true);
		editorView.setReversed(false);
		
		selectEncodingStrategy.setSelectedStrategy(1);
		tuneEncoding.actionPerformed(null);
		lineToSpeech.actionPerformed(null);
		String expected = "rqvg qbphzrag jbexf";
		String actual = audioManager.getPlayString();
		assertEquals(expected, actual);
	}
}
