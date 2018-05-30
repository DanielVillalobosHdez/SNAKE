package grupod.snake.map.snake;

import  grupod.snake.map.Map;

public class Snake {

    public enum Direccion {UP, RIGHT, DOWN, LEFT}

    ;
    private int speed;

    private Direccion dir;

    public Snake(Map map) {
        this(map, 4);
    }

    public Snake(Map map, int lenght) {
        this(map, lenght, map.getMapMaxX() / 2 - lenght / 2, map.getMapMaxY() / 2);
    }

    public Snake(Map map, int lenght, int PosX, int PosY) {
        this(map, lenght, map.getMapMaxX() / 2 - lenght / 2, map.getMapMaxY() / 2, Direccion.RIGHT);
    }

    public Snake(Map map, int lenght, int PosX, int PosY, Direccion dir) {

    }

    public Snake() {
    }


}
