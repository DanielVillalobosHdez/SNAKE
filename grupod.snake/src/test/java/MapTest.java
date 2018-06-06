import grupod.snake.map.Map;

import grupod.snake.map.MapTile;
import org.junit.*;

import org.junit.Test;


public class MapTest {

    Map tester;

    @BeforeClass
    public static void testSetup() {
        //tester = new Map();
    }

    @Test
    public void CreateMapSimple() {
        tester = new Map();
        Assert.assertEquals(tester.getMapMaxX(), 10);
        Assert.assertEquals(tester.getMapMaxY(), 10);
    }

    @Test
    public void CreateMapDim() {
        tester = new Map(25,45);
        Assert.assertEquals(tester.getMapMaxX(), 25);
        Assert.assertEquals(tester.getMapMaxY(), 45);
    }

    @Test
    public void MapWFrame() {
        tester = new Map();
        for(int i =0; i< tester.getMapMaxX(); i++)
            for(int j =0; j< tester.getMapMaxY(); j++)
                if(i==0||j==0||i==(tester.getMapMaxX()-1) ||j==(tester.getMapMaxY()-1 ))
                    Assert.assertEquals(tester.whatsin(i,j).ordinal(), MapTile.Tile.WALL.ordinal());
                else
                    Assert.assertEquals(tester.whatsin(i,j).ordinal(), MapTile.Tile.UNFILLED.ordinal());
    }

    @Test
    public void MapWOFrame() {

    }
}
