package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;
import view.PlayLine;
import view.TextToSpeechEditorView;

public class LineToSpeech implements ActionListener {
	private TextToSpeechEditorView editorView;
	private PlayLine playLine;
	
	
	public LineToSpeech(TextToSpeechEditorView editorView, PlayLine playLine) {
		this.editorView = editorView;
		this.playLine = playLine;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(editorView.isReplayMode() == false)
			editorView.getReplayManager().addCommand(this);
		Document document = editorView.getCurrentDocument();
		int num = playLine.getSelectedLine();
		if(editorView.isEncoded()) {
			document.playEncodedLine(num);
		}
		else if(editorView.isReversed()) {
			document.playReverseLine(num);
		}
		else {
			document.playLine(num);
		}
	}

}
