package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy{

	@Override
	public String encode(String encodeString) {
		// TODO Auto-generated method stub
		char[] encodeChars = encodeString.toCharArray();
		String result = "";
		for(int i = 0; i < encodeChars.length; i++) {
			if(needsEncoding(encodeChars[i]))
				result = result + mapCharacter(encodeChars[i]);
			else
				result = result + encodeChars[i];
		}
		return result;
	}

	private boolean needsEncoding(char c) {
		// TODO Auto-generated method stub
		if(isUpper(c) || isLower(c))
			return  true;
		return false;
	}

	public boolean isUpper(char c) {
		// TODO Auto-generated method stub
		if(c >= 'A' && c <= 'Z')
			return true;
		return false;
	}
	public boolean isLower(char c) {
		// TODO Auto-generated method stub
		if(c >= 'a' && c <= 'z')
			return true;
		return false;
	}
	
	protected abstract char mapCharacter(char encodeChar);
}
