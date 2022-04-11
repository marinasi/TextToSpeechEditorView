package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding {

	@Override
	protected char mapCharacter(char encodeChar) {
		// TODO Auto-generated method stub
		if(isLower(encodeChar)) {
			int distance = encodeChar - 'a';
			return (char) ('z' - distance);
		}
		else {
			int distance = encodeChar - 'A';
			return (char) ('Z' - distance);
		}
	}

}
