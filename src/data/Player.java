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
    private int index;
    private WaveManager waveManager;
    private ArrayList<TowerCannon> towerList;
    private boolean leftMouseButtonDown;

    //temporary
    private float towerCannonFiringSpeed;

    public Player(TileGrid grid,WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0]=TileType.Grass;
        this.types[1]=TileType.Dirt;
        this.types[2]=TileType.Water;
        this.index = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<>();
        this.leftMouseButtonDown = false;

        this.towerCannonFiringSpeed = 1f;
    }



    public void Update() {
        for (TowerCannon t :
                towerList) {
            t.update();
        }


        //Mouse input
        if(Mouse.isButtonDown(0) && !leftMouseButtonDown){
            int x;
            x= Mouse.getX();


            int     xPlace = x /64,
                    yPlace =(yPosition()/64);
            TowerCannon towerCannon = new TowerCannon(
                    QuickLoad("cannonBase"),
                    grid.getTile(
                            xPlace,
                            yPlace),
                    10,
                    towerCannonFiringSpeed,
                    waveManager.getCurrentWave().getEnemyList());

            if(towerCannon.checkList(towerList) && grid.getTile(xPlace,yPlace).getType() == TileType.Grass)
                towerList.add(towerCannon);

        }

        leftMouseButtonDown = Mouse.isButtonDown(0);
        //Keyboard input
        while(Keyboard.next()){
            if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
                Clock.ChangeMultiplier(0.2f);
            }
            if(Keyboard.getEventKey()==Keyboard.KEY_LEFT && Keyboard.getEventKeyState()){
                Clock.ChangeMultiplier(-0.2f);
            }
            if(Keyboard.getEventKey()==Keyboard.KEY_UP && Keyboard.getEventKeyState()){
                for (TowerCannon t:
                     towerList) {
                    towerCannonFiringSpeed = t.increaseSpeed();
                }
            }
            if(Keyboard.getEventKey()==Keyboard.KEY_DOWN && Keyboard.getEventKeyState()){
                for (TowerCannon t:
                        towerList) {
                    towerCannonFiringSpeed = t.decreaseSpeed();
                }
            }
            if(Keyboard.getEventKey()==Keyboard.KEY_T && Keyboard.getEventKeyState()){
                towerList.add(new TowerCannon(
                        QuickLoad("cannonBase"),
                        grid.getTile(4,13),10,towerCannonFiringSpeed,
                        waveManager.getCurrentWave().getEnemyList()));
            }
        }
    }

}
