package grupod.snake.map.snake;

import com.sun.org.apache.xpath.internal.operations.Bool;
import grupod.snake.map.Map;
import grupod.snake.map.MapTile;

import java.util.ArrayList;

public class Snake {

    public enum Direction {UP, RIGHT, DOWN, LEFT};
    /*
     * UP:      snake is going up, tail pointing down
     * RIGHT:   snake is going right, tail pointing left
     */

    private int speed;
    private Direction dir;
    private ArrayList<Segmento> Cuerpo;
    private int initialPosX, initialPosY, initialLenght;
    Map my_map;

    public Snake(Map map) {
        this(map, 4);
    }

    public Snake(Map map, int length) {
        this(map, length, map.getMapMaxX() / 2 - length / 2, map.getMapMaxY() / 2);
    }

    public Snake(Map map, int length, int PosX, int PosY) {
        this(map, length, map.getMapMaxX() / 2 + length / 2, map.getMapMaxY() / 2, Direction.RIGHT);
    }

    public Snake(Map map, int length, int PosX, int PosY, Direction dir) {

        this.initialPosX = PosX;
        this.initialPosY = PosY;
        this.initialLenght = length;

        this.Cuerpo = null;
        this.Cuerpo = new ArrayList<Segmento>();
        this.Cuerpo.add(new Segmento(0, PosX, PosY));
        this.my_map = map;

        for(int i=1; i<length; i++) {
            this.Cuerpo.add(new Segmento(i, PosX, PosY));
            switch (dir) {
                case UP:
                    PosY--;
                    break;
                case DOWN:
                    PosY++;
                    break;
                case LEFT:
                    PosX++;
                    break;
                case RIGHT:
                    PosX--;
                    break;
            }
        }
    }

    public ArrayList<Segmento> getSnake() {
        return this.Cuerpo;
    }

    boolean check_colision(Segmento seg, Direction dir){
        //ofset
        int xo, yo;
        switch (dir) {
            case UP:
                xo=0;yo=-1; break;
            case DOWN:
                xo=0;yo=1; break;
            case LEFT:
                xo=-1;yo=0; break;
            case RIGHT:
                xo=1;yo=0; break;
            default:
                xo=0;yo=0;
        }

        if(
             my_map.whatsin(seg.getX()+xo, seg.getY()+yo) != MapTile.Tile.UNFILLED &&
             my_map.whatsin(seg.getX()+xo, seg.getY()+yo) != MapTile.Tile.FOOD
          )
            return true;
        else
            return false;
    }

    public void move(){
        move(this.dir);
    }

    public void move(Direction dir)
    {
        if( check_colision(Cuerpo.get(0), dir) );
            this.die();

        switch (dir) {
                case UP:
                    Cuerpo.get(0).posY--;
                    break;
                case DOWN:
                    Cuerpo.get(0).posY++;
                    break;
                case LEFT:
                    Cuerpo.get(0).posX--;
                    break;
                case RIGHT:
                    Cuerpo.get(0).posX++;
                    break;
            }
    }
    public void moveUP(){   this.move(Direction.UP);   }
    public void moveDOWN(){ this.move(Direction.DOWN); }
    public void moveLEFT(){ this.move(Direction.LEFT); }
    public void moveRIGHT(){this.move(Direction.RIGHT);}

    void die(){}


}
