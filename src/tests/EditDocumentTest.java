package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.EditDocument;
import commands.NewDocument;
import view.InitializeDocument;
import view.TextToSpeechEditorView;

class EditDocumentTest {
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
	}

	@Test
	void testActionPerformed() {
		String expected = "A simple text\nto test if\nedit document works";
		String actual = editorView.getCurrentDocument().toString();
		
		assertEquals(expected, actual);
	}

}
