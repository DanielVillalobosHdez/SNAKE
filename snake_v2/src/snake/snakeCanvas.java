//code inspired by the snake of BrandonioProductions

package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
import java.awt.image.*;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.util.LinkedList;
import java.util.Random;

import com.sun.media.sound.Toolkit;


public class snakeCanvas extends Canvas implements Runnable, KeyListener {

	Image fruits;
	Image logo;
	Image obstacle;
	Image fondo;
	
	private final int Box_Height = 25;
	private final int Box_Width = 25;
	private final int Grid_Height = 30;
	private final int Grid_Width = 30; 
	
	private LinkedList<Point> snake;
	private Point fruit;
	private Point Obst;
	private int direction = Direction.No_direction; 
	
	private Thread runThread;
	//private Graphics globalGraphics;
	private int score = 0;
	
	public snakeCanvas() {
		MediaTracker media = new MediaTracker(this);
		fruits = getToolkit().getImage("manzana_sprite.png");
		media.addImage(fruits, 0);
		MediaTracker media1 = new MediaTracker(this);
		logo = getToolkit().getImage("logo.png");
		media1.addImage(logo, 0);
		MediaTracker media2 = new MediaTracker(this);
		obstacle = getToolkit().getImage("bush_0.png");
		media2.addImage(obstacle, 0);
		MediaTracker media3 = new MediaTracker(this);
		fondo= getToolkit().getImage("fondo.jpg");
		media3.addImage(fondo, 0);
		try {
			media.waitForID(0);
		}
		catch (Exception e) {}
	}
	
	public void ImageCanvas(ImageProducer imageProducer) {
		fruits = createImage(imageProducer);
	}
	
	public void init(){
		//this.addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		
		//g.setColor(Color.BLACK);
		//g.drawRect(10, 10, Box_Width * Grid_Width, Box_Height * Grid_Height);
		
		this.setPreferredSize(new Dimension(1024, 768));
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
		g.drawString("Score: " + score, Grid_Width * Box_Width +100 , 200);
		g.drawImage(logo, Grid_Width * Box_Width +75, 5, 150, 150, this);
		g.setColor(Color.MAGENTA);
		g.drawString("Level 1" /*level*/, Grid_Width * Box_Width +100 , 175);
		g.setColor(Color.BLACK);
		
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
		g.drawImage(fruits, fruit.x  * Box_Width, fruit.y * Box_Height, Box_Width+5, Box_Height+5, this);
	}
	
	public void DrawObst(Graphics g) {
		g.drawImage(obstacle, Obst.x  * Box_Width, Obst.y * Box_Height, Box_Width, Box_Height, this);
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