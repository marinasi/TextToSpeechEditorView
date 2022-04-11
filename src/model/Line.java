package model;

import java.util.ArrayList;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

public class Line {
	private ArrayList<String> words;
	private TextToSpeechAPI audioManager;
	private EncodingStrategy encodingStrategy;
	
	public Line(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
		words = new ArrayList<String>();
	}
	
	public void playLine() {
		String playString = toString();
		audioManager.play(playString);	
	}

	public void playReverseLine() {
		String playString = reverseString();
		audioManager.play(playString);	
		
	}

	public void playEncodedLine() {
		String playString = encodingStrategy.encode(toString());
		audioManager.play(playString);	
	}
	
	public void tuneEncodingStrategy(EncodingStrategy encodingStrategy) {
		this.encodingStrategy = encodingStrategy;
	}

	public void addWord(String string) {
		// TODO Auto-generated method stub
		words.add(string);
	}
	
	public String toString() {
		String str = "";
		if(words.size() == 0) {
			return str;
		}
		else {
			str = words.get(0);
			for(int i = 1; i < words.size(); i++) {
				str = str + " " + words.get(i);
			}
			return str;
		}
	}

	public String reverseString() {
		// TODO Auto-generated method stub
		String str = "";
		if(words.size() == 0) {
			return str;
		}
		else {
			int last = words.size() - 1;
			str = words.get(last);
			for(int i = last - 1; i >= 0; i--) {
				str = str + " " + words.get(i);
			}
			return str;
		}
	}
}
