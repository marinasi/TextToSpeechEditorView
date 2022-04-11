package text2speechapis;

public interface TextToSpeechAPI {
	public void play(String playString);
	public void setVolume(int volume);
	public void setPitch(int pitch);
	public void setRate(int rate);
}
