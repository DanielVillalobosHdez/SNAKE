import grupod.snake.map.snake.Segmento;
import org.junit.*;

import org.junit.Test;
import sun.misc.ASCIICaseInsensitiveComparator;


public class SegmentoTest {

    Segmento tester;

    @BeforeClass
    public static void testSetup() {
        //tester = new Map();
    }


    @Test
    public void MoveUP() {
        tester = new Segmento(0, 10, 10);
        tester.setX(4);
        Assert.assertEquals(4, tester.getX() );
        tester.setX(5);
        Assert.assertEquals(4, tester.getBX() );

    }
}
