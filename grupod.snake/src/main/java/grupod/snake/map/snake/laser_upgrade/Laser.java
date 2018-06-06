package grupod.snake.map.snake.laser_upgrade;

import grupod.snake.map.Map;
import grupod.snake.map.MapTile;
import grupod.snake.map.snake.Snake.Direction;

/**
 * @author agarcia
 *
 */
public class Laser {
    Map asociated_map;
    int posX, posY;
    int initialX, initialY;
    Direction dir;

    Laser(Map mapa, int iX, int iY, Direction iDir){
        this.asociated_map = mapa;
        this.initialX = this.posX = iX;
        this.initialY =  this.posY = iY;
    }

    boolean check_colision(Laser la, Direction pc_dir){

        if(
             asociated_map.whatsin(la.posX,la.posY) != MapTile.Tile.UNFILLED &&
             asociated_map.whatsin(la.posX,la.posY) != MapTile.Tile.FOOD
          )
            return true;
        else
            return false;
    }

    public void move(){
        move(this.dir);
    }

    void move(Direction dir){

        if(check_colision(this, this.dir)) {

        }
    }
}
