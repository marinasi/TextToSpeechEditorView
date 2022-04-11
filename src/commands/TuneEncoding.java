package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import view.SelectEncodingStrategy;
import view.TextToSpeechEditorView;

public class TuneEncoding implements ActionListener {
	private TextToSpeechEditorView editorView;
	private SelectEncodingStrategy selectStrategy;
	
	public TuneEncoding(TextToSpeechEditorView editorView, SelectEncodingStrategy selectStrategy) {
		this.editorView = editorView;
		this.selectStrategy = selectStrategy;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(editorView.isReplayMode() == false)
			editorView.getReplayManager().addCommand(this);
		String encodingStrategyName = selectStrategy.getSelectedStrategy();
		StrategiesFactory strategiesFactory = new StrategiesFactory();

		EncodingStrategy encodingStrategy = strategiesFactory.createStrategy(encodingStrategyName);
		Document document = editorView.getCurrentDocument();
		if(document == null) {
			JOptionPane.showMessageDialog(null, "Create a document first");
		}
		else {
			document.tuneEncodingStrategy(encodingStrategy);
		}

		selectStrategy.setFrameVisible(false);
	}

}
