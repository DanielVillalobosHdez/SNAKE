//code inspired by the snake of BrandonioProductions

package snake;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class snakeApplet extends Applet {

	private snakeCanvas c;
	
	
	public void init() {
		

		//setBackground(Color.WHITE);
		c = new snakeCanvas();
		c.setPreferredSize(new Dimension(1024, 768));
		c.setVisible(true);
		c.setFocusable(true);
		this.add(c);
		this.setVisible(true);
		this.setSize(new Dimension(1024, 768));
	
		
	}
	
	public void paint(Graphics g) {
		this.setSize(new Dimension(1024, 768));
	}
}
