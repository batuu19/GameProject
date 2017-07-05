package data;

import helpers.StateManager;
import org.lwjgl.input.Keyboard;

import static helpers.Artist.QuickLoad;
import static helpers.Leveler.loadMap;

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
//        grid = new TileGrid(map);
        grid = loadMap("maps\\main.map");
        waveManager = new WaveManager(new Enemy(QuickLoad("enemy"),grid.getTile(3,7),grid,64,64,70,25),
                2,2);
        player = new Player(grid,waveManager);


    }

    public void update(){

        while(Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) {
                StateManager.setState(StateManager.GameState.MAINMENU);
            }
        }
        grid.draw();
        waveManager.update();
        player.update();
    }
}
