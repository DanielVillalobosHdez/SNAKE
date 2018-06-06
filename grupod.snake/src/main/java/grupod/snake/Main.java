package grupod.snake;


import grupod.snake.map.Map;
import grupod.snake.map.MapTile;
import grupod.snake.map.snake.Snake;

public class Main {

	public static void main(String[] args) {
		//Map miMapa = new Map();
		//miMapa.map();

		Map mapa = new Map(10,10);

		System.out.println( "max X: "+ mapa.getMapMaxX());
		System.out.println( "max Y: "+ mapa.getMapMaxY());
		//System.out.println( "max X: "+ mapa.generateFood() );

		System.out.println();

		mapa.generateFood();

		MapTile.Tile[][] mapo = mapa.getMap();

		//mapa.generateObstacle();
		//mapa.generateObstacle();

		/*
		for(int i=0; i< mapa.getMapMaxX(); i++) {
			for(int j=0; j< mapa.getMapMaxY(); j++) {
				System.out.println(
						" pos ("+i+","+j+") = "+ mapo[i][j]);
			}
		}*/

        for (int i=0; i< mapa.getMapMaxX(); i++) {
            for(int j=0; j< mapa.getMapMaxY(); j++) {
                System.out.print(mapo[i][j].ordinal()+" ");
            }
            System.out.print("\n");
        }

        Snake serpy = new Snake(mapa);

		for (int i=0; i< mapa.getMapMaxX(); i++) {
			for(int j=0; j< mapa.getMapMaxY(); j++) {
				System.out.print(mapo[i][j].ordinal()+" ");
			}
			System.out.print("\n");
		}
			System.out.print("\n"+serpy.Cuerpo.get(0).getX());



		serpy.moveUP();
		//serpy.moveDOWN();
		//serpy.move();

        for (int i=0; i< mapa.getMapMaxX(); i++) {
            for(int j=0; j< mapa.getMapMaxY(); j++) {
                System.out.print(mapo[i][j].ordinal()+" ");
            }
            System.out.print("\n");
        }
			System.out.println(serpy.Cuerpo.get(0).getX());


	}

}