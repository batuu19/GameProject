package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static helpers.Artist.HEIGHT;

/**
 * Created by Bartek on 01.07.2017.
 */
public class Player {

    private TileGrid grid;
    private TileType[] types;
    private int index;
    private WaveManager waveManager;
    private ArrayList<TowerCannon> towerList;

    public Player(TileGrid grid,WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0]=TileType.Grass;
        this.types[1]=TileType.Dirt;
        this.types[2]=TileType.Water;
        this.index = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<>();
    }

    public void SetTile(){
        grid.setTile((int)Math.floor(Mouse.getX()/64), (int) Math.floor((HEIGHT - Mouse.getY()-1)/64),types[index]);
    }

    public void Update(){
        //Mouse input
        if(Mouse.isButtonDown(0)){
            SetTile();
        }
        //Keyboard input
        while(Keyboard.next()){
            if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
            MoveIndex();
            }
        }
    }

    private void MoveIndex(){
        index++;
        if(index > types.length -1)index=0;
    }
}
