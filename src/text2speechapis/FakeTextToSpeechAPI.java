package text2speechapis;

public class FakeTextToSpeechAPI implements TextToSpeechAPI{
	private String playString;
	private int volume;
	private int pitch;
	private int rate;
	
	@Override
	public void play(String playString) {
		// TODO Auto-generated method stub
		this.playString = playString;
	}

	@Override
	public void setVolume(int volume) {
		// TODO Auto-generated method stub
		this.volume = volume;
	}

	@Override
	public void setPitch(int pitch) {
		// TODO Auto-generated method stub
		this.pitch = pitch;
	}

	@Override
	public void setRate(int rate) {
		// TODO Auto-generated method stub
		this.rate = rate;
	}

	public String getPlayString() {
		return playString;
	}

	public int getVolume() {
		return volume;
	}

	public int getPitch() {
		return pitch;
	}

	public int getRate() {
		return rate;
	}
	
}
