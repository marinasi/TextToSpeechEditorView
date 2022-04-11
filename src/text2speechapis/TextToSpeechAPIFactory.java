package text2speechapis;

public class TextToSpeechAPIFactory {
	
	public TextToSpeechAPI createTTSAPI(String apiName) {
		if(apiName.contentEquals("Free TTS Adapter")) {
			return new FreeTTSAdapter();
		}
		else if(apiName.contentEquals("Fake Text to Speech")) {
			return new FakeTextToSpeechAPI();
		}
		return null;
	}
}
