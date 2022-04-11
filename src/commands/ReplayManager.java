package commands;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReplayManager {
	private ArrayList<ActionListener> commandsList;
	
	public ReplayManager() {
		commandsList = new ArrayList<ActionListener>();
	}
	
	public void addCommand(ActionListener command) {
		commandsList.add(command);
	}
	
	public void replay() {
		for(int i = 0; i < commandsList.size(); i++) {
			ActionListener command = commandsList.get(i);
			command.actionPerformed(null);
		}
	}
}
