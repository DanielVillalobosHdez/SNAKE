package grupod.snake.map.snake.laser_upgrade;

import grupod.snake.map.Map;
import grupod.snake.map.MapTile;
import grupod.snake.map.snake.Snake.Direction;

import java.util.ArrayList;

/**
 * @author agarcia
 *
 */
public class LaserSystem {

    Map asociated_map;

    ArrayList <Laser> Lista;

    LaserSystem(Map mapa){
        this.asociated_map= mapa;
    }

    void addLaser(int initalX, int initialY, Direction idir){
        this.Lista.add(new Laser(this.asociated_map, initalX, initialY, idir) );
    }

    void iterate(){
        for(int i=0; i<this.Lista.size(); i++)
            this.Lista.get(i).move();
    }
}
