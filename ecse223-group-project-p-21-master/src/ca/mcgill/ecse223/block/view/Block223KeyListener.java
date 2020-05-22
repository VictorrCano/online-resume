package ca.mcgill.ecse223.block.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ca.mcgill.ecse223.block.controller.InvalidInputException;


public class Block223KeyListener implements KeyListener{
	/**
	 * 'String input from keyboard - marked as volatile since it is shared by two
	 * threads
	 */
	private volatile String input = "";

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		try {
			keyInputs(e);
		} catch (InvalidInputException e1) {
			System.out.print(e1);
		}
	}

	private synchronized String keyInputs(KeyEvent e) throws InvalidInputException {
		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_LEFT) {
			input += "l";
		} else if (keycode == KeyEvent.VK_RIGHT) {
			input += "r";
		} else if (keycode == KeyEvent.VK_SPACE) {
			input += " ";
		} else {
			// ignore all other keys
		}
		return input;
	}

	/**
	 * Takes key inputs and clears the input string. marked as synchronized to make
	 * the key inputs as thread-safe
	 */
	public String takeInputs(){
		String result=input;
		input="";
		return result;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
