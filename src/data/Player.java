package data;

import helpers.Clock;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.io.*;
import java.util.ArrayList;

import static helpers.Artist.*;

/**
 * Created by Bartek on 01.07.2017.
 */
public class Player {

    private TileGrid grid;
    private TileType[] types;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private boolean leftMouseButtonDown,rightMouseButtonDown;

    //temporary
    private float towerCannonFiringSpeed;

    public Player(TileGrid grid,WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0]=TileType.Grass;
        this.types[1]=TileType.Dirt;
        this.types[2]=TileType.Water;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<>();
        this.leftMouseButtonDown  = false;
        this.rightMouseButtonDown = false;

        this.towerCannonFiringSpeed = 1f;
    }




    public void update() {
        for (Tower t :
                towerList) {
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }


        //Mouse input
        if(Mouse.isButtonDown(0) && !leftMouseButtonDown){
            placeTower(Mouse.getX() / TILE_SIZE,yPosition()/TILE_SIZE,
                    new TowerCannonBlue(
                    TowerType.CannonBlue,
                    grid.getTile(Mouse.getX() / TILE_SIZE,yPosition()/TILE_SIZE),
                    waveManager.getCurrentWave().getEnemyList()));

        }
        if(Mouse.isButtonDown(1) && !rightMouseButtonDown){
            placeTower(Mouse.getX() / TILE_SIZE,yPosition()/TILE_SIZE,
                    new TowerCannonBlue(
                    TowerType.CannonRed,
                    grid.getTile(Mouse.getX() / TILE_SIZE,yPosition()/TILE_SIZE),
                    waveManager.getCurrentWave().getEnemyList()));

        }


        leftMouseButtonDown = Mouse.isButtonDown(0);
        rightMouseButtonDown = Mouse.isButtonDown(1);
        //Keyboard input
        while(Keyboard.next()){
            if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
                Clock.ChangeMultiplier(0.2f);
            }
            if(Keyboard.getEventKey()==Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
                Clock.ChangeMultiplier(-0.2f);
            }
        }
    }

    private void placeTower(int x,int y,Tower tower){
        if(tower.checkList(towerList) && grid.getTile(x, y).getType() == TileType.Grass)
            towerList.add(tower);
    }

}
