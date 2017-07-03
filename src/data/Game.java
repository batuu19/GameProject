package data;

import static helpers.Artist.QuickLoad;

/**
 * Created by Bartek on 02.07.2017.
 */
public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    public static final int TILE_SIZE = 64;

    //temp variables


    public Game(int [][] map){
        grid = new TileGrid(map);
        waveManager = new WaveManager(new Enemy(QuickLoad("enemy"),grid.getTile(3,7),grid,64,64,70),
                2,2);
        player = new Player(grid,waveManager);


    }

    public void update(){
        grid.draw();
        waveManager.update();
        player.Update();

    }
}
