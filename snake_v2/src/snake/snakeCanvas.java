//code inspired by the snake of BrandonioProductions

package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;


public class snakeCanvas extends Canvas implements Runnable, KeyListener {

	private final int Box_Height = 15;
	private final int Box_Width = 15;
	private final int Grid_Height = 25;
	private final int Grid_Width = 25; 
	
	private LinkedList<Point> snake;
	private Point fruit;
	private Point Obst;
	private int direction = Direction.No_direction; 
	
	private Thread runThread;
	//private Graphics globalGraphics;
	private int score = 0;
	
	public void init(){
		//this.addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		
		//g.setColor(Color.BLACK);
		//g.drawRect(10, 10, Box_Width * Grid_Width, Box_Height * Grid_Height);
		
		this.setPreferredSize(new Dimension(640, 480));
		this.addKeyListener(this);
		
		if(snake == null) {
			snake = new LinkedList<Point>();
			GenerateSnake();
			PlaceFruit();
			PlaceObst();
		}
		
		
		//globalGraphics = g.create();

		
		if (runThread == null) {
			runThread = new Thread(this);
			runThread.start();			
		}
		
		DrawGrid(g);
		DrawSnake(g);
		DrawFruit(g);
		DrawObst(g);
		DrawScore(g);
	}
	
	public void update(Graphics g){
		Graphics offScreenGraphics;
		BufferedImage offscreen = null;
		Dimension d = this.getSize();
		
		offscreen = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		offScreenGraphics = offscreen.getGraphics();
		offScreenGraphics.setColor(this.getBackground());
		offScreenGraphics.fillRect(0, 0, d.width, d.height);
		offScreenGraphics.setColor(this.getForeground());
		paint(offScreenGraphics);
		
		
		g.drawImage(offscreen, 0, 0, this);
	}
	
	
	public void GenerateSnake() {
		
		score = 0;
		snake.clear();
		
		
		snake.add(new Point(Grid_Width/2,Grid_Height/2-2));
		snake.add(new Point(Grid_Width/2,Grid_Height/2-1));
		snake.add(new Point(Grid_Width/2,Grid_Height/2));
		direction = Direction.No_direction;
	}
	
	
	public void Move(){
		
		Point head = snake.peekFirst();
		Point newPoint = head;
		
		switch (direction) {
			case Direction.Arriba:
				newPoint = new Point(head.x, head.y -1);
				break;
			case Direction.Abajo:
				newPoint = new Point(head.x, head.y +1);
				break;
			case Direction.Izquierda:
				newPoint = new Point(head.x-1, head.y);
				break;
			case Direction.Derecha:
				newPoint = new Point(head.x+1, head.y);
				break;
		}
		
		snake.remove(snake.peekLast());
		
		if(newPoint.equals(fruit)){
			
			score += 5;
			
			Point addPoint = (Point) newPoint.clone();
			
			switch (direction) {
				case Direction.Arriba:
					newPoint = new Point(head.x, head.y -1);
					break;
				case Direction.Abajo:
					newPoint = new Point(head.x, head.y +1);
					break;
				case Direction.Izquierda:
					newPoint = new Point(head.x-1, head.y);
					break;
				case Direction.Derecha:
					newPoint = new Point(head.x+1, head.y);
					break;
			}
			snake.push(addPoint);
			PlaceFruit();
			PlaceObst();
			
		} else if (newPoint.x < 0 || newPoint.x > (Grid_Width -1)) {
			GenerateSnake();
			PlaceFruit();
			PlaceObst();
			return;
		} else if (newPoint.y < 0 || newPoint.y > (Grid_Height -1)) {
			GenerateSnake();
			PlaceFruit();
			PlaceObst();
			return;
		} else if (snake.contains(newPoint)) {
			GenerateSnake();
			return;
		} else if(newPoint.equals(Obst)) {
			GenerateSnake();
			PlaceFruit();
			PlaceObst();
			return;
		}
			
		snake.push(newPoint);
	}
	
	public void DrawScore(Graphics g) {
		g.drawString("Score: " + score, 10, Grid_Height * Box_Height + 20);
		g.setColor(Color.RED);
		g.fillOval(10, Grid_Height * Box_Height + 60, Grid_Width, Grid_Height);
		g.setColor(Color.BLACK);
		g.setColor(Color.YELLOW);
		g.fillOval(70, Grid_Height * Box_Height + 60, Grid_Width, Grid_Height);
		g.setColor(Color.BLACK);
		g.drawString("Fruit     Obstacles", 10, Grid_Height * Box_Height + 55);
		
	}
	
	public void DrawGrid(Graphics g) {
		setBackground(Color.GREEN);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, Grid_Width * Box_Width, Grid_Height * Box_Height);
		for (int x = Box_Width; x < Grid_Width * Box_Width; x+= Box_Width) {
			g.setColor(Color.GREEN);
			g.drawLine(x, 5, x, Grid_Height * Box_Height);
		}
		
		for (int y = Box_Height; y < Grid_Height * Box_Height; y+= Box_Height) {
			g.setColor(Color.GREEN);
			g.drawLine(5, y, Grid_Width * Box_Width, y);
		}
	}
	
	public void DrawSnake(Graphics g) {
		
		g.setColor(Color.BLUE);
		for(Point p : snake) {
			g.fillRect(p.x * Box_Width, p.y * Box_Height, Box_Width, Box_Height);
		}
		g.setColor(Color.BLACK);
	}
	
	public void DrawFruit(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(fruit.x  * Box_Width, fruit.y * Box_Height, Box_Width, Box_Height);
		g.setColor(Color.BLACK);
	}
	
	public void DrawObst(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(Obst.x  * Box_Width, Obst.y * Box_Height, Box_Width, Box_Height);
		g.setColor(Color.BLACK);
	}
	
	public void PlaceFruit() {
		Random rand = new Random();
		int randomX = rand.nextInt(Grid_Width);
		int randomY = rand.nextInt(Grid_Height);
		Point randomPoint = new Point(randomX,randomY);
		while(snake.contains(randomPoint)) {
			randomX = rand.nextInt(Grid_Width);
			randomY = rand.nextInt(Grid_Height);
			randomPoint = new Point(randomX,randomY);
		}
		fruit = randomPoint;
	}
	
	public void PlaceObst() {
		Random rand = new Random();
		int randomX = rand.nextInt(Grid_Width);
		int randomY = rand.nextInt(Grid_Height);
		Point randomPoint = new Point(randomX,randomY);
		while(snake.contains(randomPoint) && fruit != randomPoint) {
			randomX = rand.nextInt(Grid_Width);
			randomY = rand.nextInt(Grid_Height);
			randomPoint = new Point(randomX,randomY);
		}
		Obst = randomPoint;
	}

	@Override
	public void run() {
		while(true) {
			Move();
			repaint();
			
			try {
				Thread.currentThread();
				Thread.sleep(100);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if(direction != Direction.Abajo)
					direction = Direction.Arriba;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				if(direction != Direction.Arriba)
					direction = Direction.Abajo;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				if(direction != Direction.Derecha)
					direction = Direction.Izquierda;
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				if(direction != Direction.Izquierda)
					direction = Direction.Derecha;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
