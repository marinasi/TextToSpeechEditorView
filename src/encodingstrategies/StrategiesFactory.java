package encodingstrategies;

public class StrategiesFactory {
	public EncodingStrategy createStrategy(String strategyName) {
		if(strategyName.equals("Rot13")) {
			return new Rot13Encoding();
		}
		else if(strategyName.equals("AtBash")){
			return new AtBashEncoding();
		}
		return null;
	}
}
