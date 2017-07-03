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
    }

    public void SetTile(){
        grid.setTile((int)Math.floor(Mouse.getX()/64), (int) Math.floor((HEIGHT - Mouse.getY()-1)/64),types[index]);
    }

    public void Update() {
        for (TowerCannon t :
                towerList) {
            t.update();
        }


        //Mouse input
        if(Mouse.isButtonDown(0) && !leftMouseButtonDown){
            int x,y;
            x= Mouse.getX();
            y=Mouse.getY();

            PrintWriter out = null;
            //for debuging
            try {
                out = new PrintWriter(new File("output.txt"));
                out.print("x =  " + x +
                            "\ny = " + y +
                            "\nxTile = " + x/64 +
                            "\nyTile = " + (HEIGHT - y - 1)/64 +
                            "\nHeight = " + HEIGHT +
                                    "");
                out.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            towerList.add(new TowerCannon(
                    QuickLoad("cannonBase"),
                    grid.getTile(
                            x /64,
//                            (HEIGHT - y -1)/64),
                            ((HEIGHT>SCREEN_HEIGHT?(HEIGHT - ((int) (((float) y / (float) SCREEN_HEIGHT) * (HEIGHT-SCREEN_HEIGHT)))):HEIGHT)- y -1)/64),
                    10,
                    waveManager.getCurrentWave().getEnemyList()));

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
                    t.increaseSpeed();
                }
            }
            if(Keyboard.getEventKey()==Keyboard.KEY_DOWN && Keyboard.getEventKeyState()){
                for (TowerCannon t:
                        towerList) {
                    t.decreaseSpeed();
                }
            }
            if(Keyboard.getEventKey()==Keyboard.KEY_T && Keyboard.getEventKeyState()){
                towerList.add(new TowerCannon(
                        QuickLoad("cannonBase"),
                        grid.getTile(4,13),10,
                        waveManager.getCurrentWave().getEnemyList()));
            }
        }
    }

    private void moveIndex(){
        index++;
        if(index > types.length -1)index=0;
    }
}
