package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.NewDocument;
import view.InitializeDocument;
import view.TextToSpeechEditorView;

class NewDocumentTest {

	private static TextToSpeechEditorView editorView;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		editorView = new TextToSpeechEditorView("Free TTS Adapter");
		editorView.setTestMode(true);
		CommandsFactory commandsFactory = new CommandsFactory(editorView);
		InitializeDocument init = new InitializeDocument(commandsFactory);
		NewDocument newDocument = (NewDocument) commandsFactory.createCommand("New Document");
		newDocument.actionPerformed(null);
	}

	@Test
	void testActionPerformed() {
		int size = editorView.getCurrentDocument().contentsSize();
		assertEquals(0, size);
	}

}
