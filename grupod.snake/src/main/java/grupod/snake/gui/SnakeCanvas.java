package grupod.snake.gui;

import grupod.snake.map.Map;
import grupod.snake.map.snake.Snake;

import java.awt.Canvas;
import java.awt.Point;


public class SnakeCanvas extends Canvas implements Runnable{
    private final int Box_Height;
    private final int Box_Width;
    private final int Grid_Height;
    private final int Grid_Width;

    public Map mapa;

    public SnakeCanvas() {
        Grid_Width = 30;
        Grid_Height = 30;
        Box_Width = 25;
        Box_Height = 25;
        mapa = new Map(Grid_Width * Box_Width,
                Grid_Height * Box_Height );
    }

    public void GenerateSnake() {

       Snake serpiente = new Snake(mapa,3);


    }

    @Override
    public void run() {
        while(true) {
            //Move();
            repaint();

            try {
                Thread.currentThread();
                Thread.sleep(150);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
