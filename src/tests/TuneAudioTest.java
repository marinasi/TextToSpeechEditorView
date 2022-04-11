package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import commands.CommandsFactory;
import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.NewDocument;
import commands.SaveDocument;
import commands.TuneAudio;
import commands.TuneEncoding;
import text2speechapis.FakeTextToSpeechAPI;
import view.InitializeDocument;
import view.SaveToFile;
import view.SelectAudioParameters;
import view.SelectEncodingStrategy;
import view.TextToSpeechEditorView;

class TuneAudioTest {
	private static TextToSpeechEditorView editorView;
	private static DocumentToSpeech documentToSpeech;
	private static FakeTextToSpeechAPI audioManager;
	private static TuneAudio tuneAudio;
	private static SelectEncodingStrategy selectEncodingStrategy;
	private static SelectAudioParameters audioParameters;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		editorView = new TextToSpeechEditorView("Fake Text to Speech");
		editorView.setTestMode(true);
		audioManager = (FakeTextToSpeechAPI) editorView.getAudioManager();
		CommandsFactory commandsFactory = new CommandsFactory(editorView);
		audioParameters = new SelectAudioParameters(commandsFactory);
		selectEncodingStrategy = new SelectEncodingStrategy(commandsFactory);
		
		audioParameters.setVolume(75);
		audioParameters.setPitch(120);
		audioParameters.setRate(130);
		tuneAudio = (TuneAudio) commandsFactory.createCommand("Tune Audio");
		tuneAudio.actionPerformed(null);	
	}

	@Test
	void testActionPerformedVolume() {
		int expected = 75;
		int actual = audioManager.getVolume();
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testActionPerformedPitch() {
		int expected = 120;
		int actual = audioManager.getPitch();
		assertEquals(expected, actual);
	}
	
	@Test
	void testActionPerformedRate() {
		int expected = 130;
		int actual = audioManager.getRate();
		assertEquals(expected, actual);
	}
}
