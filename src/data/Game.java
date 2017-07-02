package data;

import static helpers.Artist.QuickLoad;

/**
 * Created by Bartek on 02.07.2017.
 */
public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;

    //temp variables
    TowerCanon tower;


    public Game(int [][] map){
        grid = new TileGrid(map);
        player = new Player(grid);
//        wave = new Wave(4,new Enemy(QuickLoad("enemy"),grid.GetTile(3,7),grid,64,64,40));
        waveManager = new WaveManager(new Enemy(QuickLoad("enemy"),grid.GetTile(3,7),grid,64,64,70),
                2,2);


    }

    public void Update(){
        grid.Draw();
        waveManager.Update();
        player.Update();

        tower.Update();
    }
}
