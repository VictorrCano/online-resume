package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BallRendering extends JPanel{
	
	public int x;
	public int y;
	public int radius;
	
	public BallRendering(int x, int y, int radius) {
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.fillOval(0, 0, 10, 10);
	}


	public void setBackground(Color black) {
		super.setBackground(black);
		
	}

}
