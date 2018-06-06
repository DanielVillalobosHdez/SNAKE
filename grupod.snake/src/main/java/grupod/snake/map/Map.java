package grupod.snake.map;


import java.util.Random;

import grupod.snake.map.MapTile.Tile;

/**
 * @author agarcia
 *
 */

public class Map {

	/**
	 *
	 */
	public Map() {
		this(10,10); // If we don't get dimensions passed, use default ones;
	}
	public Map(int dim)
	{
		// Create a square map of given dimension
		this(dim, dim);
	}
	public Map(int cols, int rows)
	{
		this(cols,rows,1);
	}
	public Map(int cols, int rows, int frame) {
		// Set map dimensions
		MapMaxX = cols;
		MapMaxY = rows;
		mapa = new Tile[MapMaxX][MapMaxY];
		// Allocate map with set dimensions
		for (int i = 0; i <MapMaxX; i++)
	        for (int j = 0; j <MapMaxY; j++)
	            mapa[i][j] = Tile.UNFILLED;
		// then we create the frame if we are instructed to do so
		if(frame != 0)
			this.generateMapFrame();
	}

	// The array containing the map
	private Tile [][] mapa;

	// The XY dimensions of the map
	private int MapMaxX, MapMaxY;

	// this will create
	public int [] generateFood(){
		Random rand = new Random();
		int FoodCoordX;
		int FoodCoordY;
		do {
			FoodCoordX =  rand.nextInt(MapMaxX);
			FoodCoordY =  rand.nextInt(MapMaxY);
		}while(mapa[FoodCoordX][FoodCoordY] != Tile.UNFILLED);
		mapa[FoodCoordX][FoodCoordY] = Tile.FOOD;
		return new int [] {FoodCoordX,FoodCoordY};
	}
	public int [] generateObstacle(){
		Random rand = new Random();
		int ObsCoordX;
		int ObsCoordY;
		do {
			ObsCoordX =  rand.nextInt(MapMaxX);
			ObsCoordY =  rand.nextInt(MapMaxY);
		}while(mapa[ObsCoordX][ObsCoordY] != Tile.UNFILLED);
		mapa[ObsCoordX][ObsCoordY] = Tile.OBSTACLE;
		return new int [] {ObsCoordX,ObsCoordY};
	}
	private void generateMapFrame()
	{
		for (int i = 0; i <MapMaxX; i++)
	        for (int j = 0; j <MapMaxY; j++)
	        	if(i==0  || j==0 || i==(MapMaxX-1) || j==(MapMaxY-1) )
	        		mapa[i][j]=Tile.WALL;
	}

	public int getMapMaxX() {
		return MapMaxX;
	}
	public int getMapMaxY() {
		return MapMaxY;
	}
	public Tile[][] getMap()
	{
		return mapa;
	}
	public Tile whatsin(int X, int Y)
	{
		return this.mapa[X][Y];
	}

	public void setTile(int X, int Y, Tile type) {
	    mapa[X][Y]=type;
    }


}