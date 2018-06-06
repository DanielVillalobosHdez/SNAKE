
import grupod.snake.map.Map;
import grupod.snake.map.snake.Segmento;
import grupod.snake.map.snake.Snake;
import org.junit.*;

import org.junit.Test;


public class SnakeTest {

    Segmento tester;

    @BeforeClass
    public static void testSetup() {
        //tester = new Map();
    }


    @Test
    public void MoveUP() {
        Map mapa = new Map();
        Snake serpy = new Snake(mapa);
        System.out.println(serpy.Cuerpo.get(0).getY());
        serpy.moveUP();
        System.out.println(serpy.Cuerpo.get(0).getY());


    }
}
