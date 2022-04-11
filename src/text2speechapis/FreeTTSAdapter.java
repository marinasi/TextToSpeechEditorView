package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI{

	private VoiceManager vm;
	private Voice voice;
	
	public FreeTTSAdapter() {
		// TODO Auto-generated constructor stub
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		vm = VoiceManager.getInstance();
		voice = vm.getVoice("kevin16");
		voice.allocate();
	}
	
	@Override
	public void play(String playString) {
		// TODO Auto-generated method stub
		voice.speak(playString);
	}

	@Override
	public void setVolume(int volume) {
		// TODO Auto-generated method stub
		float vol = (float) (volume/100.0);
		voice.setVolume(vol);
	}

	@Override
	public void setPitch(int pitch) {
		// TODO Auto-generated method stub
		voice.setPitch(pitch);
	}

	@Override
	public void setRate(int rate) {
		// TODO Auto-generated method stub
		voice.setRate(rate);
	}

}
