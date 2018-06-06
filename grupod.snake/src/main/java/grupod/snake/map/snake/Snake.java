package grupod.snake.map.snake;


import grupod.snake.map.Map;
import grupod.snake.map.MapTile;

import java.util.ArrayList;

/**
 * @author agarcia
 *
 */
public class Snake {

    public enum Direction {UP, RIGHT, DOWN, LEFT};
    /*
     * UP:      snake is going up, tail pointing down
     * RIGHT:   snake is going right, tail pointing left
     */

    public enum EXIT_CODE {MOVED, COLISION, DEATH};
    protected int speed;
    protected Direction dir;
    public ArrayList<Segmento> Cuerpo;
    private int initialPosX, initialPosY, initialLenght;
    private Direction initialDir;
    protected Map my_map;

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
        this.initialDir = dir;
        this.dir = dir;

        this.Cuerpo = null;
        this.Cuerpo = new ArrayList<Segmento>();
        this.Cuerpo.add(new Segmento(0, PosX, PosY));
        this.my_map = map;

        for(int i=1; i<length; i++) {
            // adjust new position
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
            //generate segment
            this.Cuerpo.add(new Segmento(i, PosX, PosY));
            //update map
            this.my_map.setTile( PosX, PosY, MapTile.Tile.SNAKE );
        }
    }

    public ArrayList<Segmento> getSnake() {
        return this.Cuerpo;
    }

    boolean check_colision(Segmento seg, Direction pc_dir){
        //ofset
        int xo, yo;
        switch (pc_dir) {
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

    boolean check_valid_direction(Direction ndir){
        switch (this.dir)        {
            case UP:
                if(ndir == Direction.DOWN)
                    return false;
            case DOWN:
                if(ndir == Direction.UP)
                    return false;
            case RIGHT:
                if(ndir == Direction.LEFT)
                    return false;
            case LEFT:
                if(ndir == Direction.RIGHT)
                    return false;
                default:
                    return true;
        }
    }

    public EXIT_CODE move(){
        return move(this.dir);
    }

    public EXIT_CODE move(Direction new_dir)
    {
        if( ! check_valid_direction(new_dir))
            System.out.println("Fuck you");
        else
            this.dir = new_dir;
        if( check_colision(Cuerpo.get(0), new_dir) ){
            this.die();
            return EXIT_CODE.DEATH;
        }
        System.out.println("Cabeza X: "+Cuerpo.get(0).getY()+
                "Cabeza Y: " +Cuerpo.get(0).getY());

        switch (new_dir) {
            case UP:
                Cuerpo.get(0).setY(Cuerpo.get(0).posY - 1);
                break;
            case DOWN:
                Cuerpo.get(0).setY(Cuerpo.get(0).posY + 1);
                break;
            case LEFT:
                Cuerpo.get(0).setX(Cuerpo.get(0).posX - 1);
                break;
            case RIGHT:
                Cuerpo.get(0).setX(Cuerpo.get(0).posX + 1);
                break;
        }
        System.out.println("Cabeza X: "+Cuerpo.get(0).getY()+
                "Cabeza Y: " +Cuerpo.get(0).getY());

        my_map.setTile(Cuerpo.get(0).beforeX, Cuerpo.get(0).beforeY, MapTile.Tile.UNFILLED);
        my_map.setTile(Cuerpo.get(0).posX, Cuerpo.get(0).posY, MapTile.Tile.SNAKE);
         for(int i=1; i< this.Cuerpo.size(); i++){
            Cuerpo.get(i).setX(Cuerpo.get(i-1).beforeX);
            Cuerpo.get(i).setY(Cuerpo.get(i-1).beforeY);
         }
         for(int i=0; i<this.Cuerpo.size(); i++)
         {
             my_map.setTile(Cuerpo.get(i).beforeX, Cuerpo.get(i).beforeY, MapTile.Tile.UNFILLED);
             my_map.setTile(Cuerpo.get(i).posX, Cuerpo.get(i).posY, MapTile.Tile.SNAKE);
         }
         return EXIT_CODE.MOVED;
    }
    public void moveUP(){   this.move(Direction.UP);   }
    public void moveDOWN(){ this.move(Direction.DOWN); }
    public void moveLEFT(){ this.move(Direction.LEFT); }
    public void moveRIGHT(){this.move(Direction.RIGHT);}

    void die(){
        System.out.println("Moriste!");
    }


}
