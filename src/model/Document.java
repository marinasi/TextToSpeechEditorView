package model;

import java.util.ArrayList;
import java.util.Date;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

public class Document {
	private EncodingStrategy encodingStrategy;
	private TextToSpeechAPI audioManager;
	private ArrayList<Line> contents;
	private String title;
	private String author;
	private Date creationDate;
	private Date saveDate;
	
	public Document(String title, String author, TextToSpeechAPI audioManager) {
		this.title = title;
		this.author = author;
		this.audioManager = audioManager;
		this.creationDate = new Date();
		this.contents = new ArrayList<Line>();
	}
	
	public void playContents() {
		String playString = toString();
		audioManager.play(playString);
	}
	
	public void playReverseContents() {
		String playString = reverseString();
		audioManager.play(playString);
	}

	public void playEncodedContents() {
		String playString = encodingStrategy.encode(toString());
		audioManager.play(playString);
	}

	public void playLine(int num) {
		Line line = contents.get(num);
		line.playLine();
	}

	public void playReverseLine(int num) {
		Line line = contents.get(num);
		line.playReverseLine();		
	}

	public void playEncodedLine(int num) {
		Line line = contents.get(num);
		line.playEncodedLine();	
	}
	
	public void tuneEncodingStrategy(EncodingStrategy encodingStrategy) {
		this.encodingStrategy = encodingStrategy;
		for(int i = 0; i < contents.size(); i++) {
			Line line = contents.get(i);
			line.tuneEncodingStrategy(encodingStrategy);
		}
	}

	public void addLine(String string) {
		// TODO Auto-generated method stub
		Line line = new Line(audioManager);
		String[] words = string.split(" ");
		
		for(int i = 0; i < words.length; i++) {
			line.addWord(words[i]);
		}
		contents.add(line);
	}
	
	public int contentsSize() {
		return contents.size();
	}
	
	public String toString() {
		String str = "";
		if(contents.size() == 0) {
			return str;
		}
		else {
			Line line = contents.get(0);
			str = line.toString();
			for(int i = 1; i < contents.size(); i++) {
				line = contents.get(i);
				str = str + "\n" + line.toString();
			}
			return str;
		}
		
	}
	public String reverseString() {
		// TODO Auto-generated method stub
		String str = "";
		if(contents.size() == 0) {
			return str;
		}
		else {
			int last = contents.size() - 1;
			Line line = contents.get(last);
			str = line.reverseString();
			for(int i = last - 1; i >= 0; i--) {
				line = contents.get(i);
				str = str + "\n" + line.reverseString();
			}
			return str;
		}
	}

	public ArrayList<Line> getContents() {
		// TODO Auto-generated method stub
		return contents;
	}

	public void clearContents() {
		// TODO Auto-generated method stub
		contents.clear();
	}

	public EncodingStrategy getEncodingStrategy() {
		// TODO Auto-generated method stub
		return encodingStrategy;
	}
}
