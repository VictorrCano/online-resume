package ca.mcgill.ecse223.block.view;

import java.awt.event.KeyListener;

import ca.mcgill.ecse223.block.controller.TOHallOfFameEntry;

public interface Block223PlayModeInterface {
	
	public String takeInputs();
	
	public void refresh();

	void endGame(int nrOfLives, TOHallOfFameEntry hof);

}
