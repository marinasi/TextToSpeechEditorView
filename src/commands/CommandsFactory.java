package commands;

import java.awt.event.ActionListener;

import view.InitializeDocument;
import view.PlayLine;
import view.SaveToFile;
import view.SelectAudioParameters;
import view.SelectEncodingStrategy;
import view.TextToSpeechEditorView;

public class CommandsFactory {
	private TextToSpeechEditorView editorView;
	private InitializeDocument init;
	private SaveToFile saveToFile;
	private PlayLine playLine;
	private SelectEncodingStrategy selectStrategy;
	private SelectAudioParameters audioParameters;

	public CommandsFactory(TextToSpeechEditorView editorView) {
		this.editorView = editorView;
	}

	public void setInit(InitializeDocument init) {
		this.init = init;
	}
	
	public void setSave(SaveToFile saveToFile) {
		this.saveToFile = saveToFile;
	}
	
	public void setPlayLine(PlayLine playLine) {
		this.playLine = playLine;
	}
	
	public void setSelectStrategy(SelectEncodingStrategy selectStrategy) {
		this.selectStrategy = selectStrategy;
	}
	
	public void setAudioParameters(SelectAudioParameters audioParameters) {
		this.audioParameters = audioParameters;
	}
	
	public ActionListener createCommand(String commandName) {
		
		if(commandName.contentEquals("New Document")) {
			return new NewDocument(editorView, init);
		}
		else if(commandName.contentEquals("Edit Document")) {
			return new EditDocument(editorView);
		}
		else if(commandName.contentEquals("Open Document")) {
			return new OpenDocument(editorView);
		}
		else if(commandName.contentEquals("Save Document")) {
			return new SaveDocument(editorView, saveToFile);
		}
		else if(commandName.contentEquals("Document to Speech")) {
			return new DocumentToSpeech(editorView);
		}
		else if(commandName.contentEquals("Line to Speech")) {
			return new LineToSpeech(editorView, playLine);
		}
		else if(commandName.contentEquals("Tune Audio")) {
			return new TuneAudio(editorView, audioParameters);
		}
		else if(commandName.contentEquals("Tune Encoding")) {
			return new TuneEncoding(editorView, selectStrategy);
		}
		else if(commandName.contentEquals("Replay Command")) {
			return new ReplayCommand(editorView);
		}
		
		return null;
	}
}
