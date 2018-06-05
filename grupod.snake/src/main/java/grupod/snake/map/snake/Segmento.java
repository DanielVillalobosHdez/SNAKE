package grupod.snake.map.snake;

public class Segmento {

    int posX, posY;
    private int posID;

    public Segmento(int id, int posX, int posY)
    {
        this.posID = id;
        this.posX = posX;
        this.posY = posY;
    }

    public int getID(){
        return this.posID;
    }
    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }
}
