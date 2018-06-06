package grupod.snake.map.snake;

/**
 * @author agarcia
 *
 */
public class Segmento {

    int posX, posY;
    int beforeX, beforeY;
    private int posID;

    public Segmento(int id, int posX, int posY)
    {
        this.posID = id;
        this.posX = posX;
        this.posY = posY;
        this.beforeX = this.posX;
        this.beforeY = this.posY;
    }

    public int getID(){
        return this.posID;
    }
    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }    public int getBX(){
        return beforeX;
    }
    public int getBY(){
        return beforeY;
    }


    public void setX(int nX) {
        this.beforeX=this.posX;
        this.posX = nX;
    }
    public  void setY(int nY) {
        this.beforeY = this.posY;
        this.posY = nY;
    }
}
