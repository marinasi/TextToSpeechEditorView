package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding {

	@Override
	protected char mapCharacter(char encodeChar) {
		// TODO Auto-generated method stub
		if(isLower(encodeChar)) {
			int distance = encodeChar - 'a';
			distance = (distance + 13)%26;
			return (char) ('a' + distance);
		}
		else {
			int distance = encodeChar - 'A';
			distance = (distance + 13)%26;
			return (char) ('A' + distance);
		}
	}

}
