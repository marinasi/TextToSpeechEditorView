package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TextToSpeechEditorView;

public class ReplayCommand implements ActionListener {
	private TextToSpeechEditorView editorView;
	
	public ReplayCommand(TextToSpeechEditorView editorView) {
		this.editorView = editorView;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		editorView.setReplayMode(true);
		ReplayManager replayManager = editorView.getReplayManager();
		replayManager.replay();
		editorView.setReplayMode(false);
	}

}
