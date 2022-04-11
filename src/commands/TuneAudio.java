package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import text2speechapis.TextToSpeechAPI;
import view.SelectAudioParameters;
import view.TextToSpeechEditorView;

public class TuneAudio implements ActionListener {
	private TextToSpeechEditorView editorView;
	private SelectAudioParameters audioParameters;
	
	public TuneAudio(TextToSpeechEditorView editorView, SelectAudioParameters audioParameters) {
		this.editorView = editorView;
		this.audioParameters = audioParameters;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(editorView.isReplayMode() == false)
			editorView.getReplayManager().addCommand(this);
		TextToSpeechAPI audioManager = editorView.getAudioManager();
		int pitch = audioParameters.getPitch();
		int rate = audioParameters.getRate();
		int volume = audioParameters.getVolume();
		boolean allOk = true;
		
		if (pitch <= 0) {
			allOk = false;
			JOptionPane.showMessageDialog(null, "Give a valid value for pitch");
		}
		else
			audioManager.setPitch(pitch);
		
		if(rate >= 30)
			audioManager.setRate(rate);
		else if (pitch == -1) {
			JOptionPane.showMessageDialog(null, "Give a valid value for rate");
			allOk = false;
		}
		else {
			JOptionPane.showMessageDialog(null, "Rate should be >= 30");
			allOk = false;
		}
			
		
		if(volume >= 0 && volume <= 100)
			audioManager.setVolume(volume);
		else if (volume == -1) {
			JOptionPane.showMessageDialog(null, "Give a valid value for volume");
			allOk = false;
		}
		else {
			JOptionPane.showMessageDialog(null, "Volume should be between 0 and 100");
			allOk = false;
		}
		if(allOk == true)
			editorView.setAudioFrameVisible(false);
	}

}
