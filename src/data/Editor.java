package data;

import helpers.Clock;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static helpers.Artist.*;
import static helpers.Leveler.getMapNumber;
import static helpers.Leveler.loadMap;
import static helpers.Leveler.saveMap;

/**
 * Created by Bartek on 03.07.2017.
 */
public class Editor {


    private TileType[] types;
    private TileGrid grid;
    private int index;

    public Editor(){
        this.grid = new TileGrid();
//        this.grid = loadMap("map9800");
        this.index = 0;
        this.types = new TileType[3];
        this.types[0]=TileType.Grass;
        this.types[1]=TileType.Dirt;
        this.types[2]=TileType.Water;
    }

    public void update() {
        grid.draw();

        //Mouse input
        if(Mouse.isButtonDown(0)){
            setTile();
        }

        //Keyboard input
        while(Keyboard.next()){
            if(Keyboard.getEventKey()==Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
                moveIndex();
            }
            if(Keyboard.getEventKey()==Keyboard.KEY_S && Keyboard.getEventKeyState()){
                saveMap("map" + getMapNumber(),grid);
            }

        }
    }

    private void setTile(){
        grid.setTile((int)Math.floor(Mouse.getX()/64), (int) Math.floor(yPosition()/64),types[index]);
    }


    private void moveIndex(){
        index++;
        if(index > types.length -1)index=0;
    }
}
