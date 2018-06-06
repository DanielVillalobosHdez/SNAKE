package grupod.snake.map.snake.laser_upgrade;

import grupod.snake.map.Map;
import grupod.snake.map.MapTile;
import grupod.snake.map.snake.Snake;

import java.util.ArrayList;

/**
 * @author agarcia
 *
 */
public class Snake_laser extends Snake {

    protected int laser_cooldown;
    protected int laser_level;
    protected Map_laser my_map;


    public Snake_laser(Map_laser map) {
        this(map, 4);
    }
    public Snake_laser(Map_laser map, int length) {
        this(map, length, map.getMapMaxX() / 2 - length / 2, map.getMapMaxY() / 2);
    }
    public Snake_laser(Map_laser map, int length, int PosX, int PosY) {
        this(map, length, map.getMapMaxX() / 2 + length / 2, map.getMapMaxY() / 2, Direction.RIGHT);
    }
    public Snake_laser(Map_laser map, int length, int PosX, int PosY, Direction dir) {
        this(map, length, PosX, PosY, dir, 0);
    }

    public Snake_laser(Map_laser map, int length, int PosX, int PosY, Direction dir, int cooldown) {
        this(map, length, PosX, PosY, dir, 0,0);
    }
    public Snake_laser(Map_laser map, int length, int PosX, int PosY, Direction dir, int cooldown, int laser_lv) {
        super(map, length, PosX, PosY, dir);
        my_map = map;
        this.laser_cooldown=cooldown;
        this.laser_level=laser_lv;
    }

    public void dispara_laser(){
        my_map.LSI.addLaser(this.Cuerpo.get(0).getX(), this.Cuerpo.get(0).getY(),this.dir);
    }

}
