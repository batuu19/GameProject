package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.DrawQuadTex;
import static helpers.Clock.Delta;

/**
 * Created by Bartek on 01.07.2017.
 */
public class Enemy {
    private int width,height,health, currentCheckpoint;
    private float speed,x,y;
    private Texture texture;
    private Tile startTile;
    private boolean first = true;
    private TileGrid grid;

    private ArrayList<Checkpoint> checkpoints;
    private int[] directions;

    public Enemy(Texture texture,Tile startTile,TileGrid grid, int width, int height,float speed ) {
        this.texture = texture;
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.grid = grid;

        this.checkpoints = new ArrayList<>();
        this.directions = new int[2];
        this.directions[0] = 0; //X direction
        this.directions[1] = 0; //Y direction

        directions = FindNextD(startTile);
        this.currentCheckpoint = 0;
        PopulateCheckpointList();
    }

    public void Update(){
        if(first)
            first = false;
        else{
            if(CheckpointReached())
            {
                if(currentCheckpoint + 1 == checkpoints.size())
                    System.out.println("Enemy reached end of maze");
                else
                    currentCheckpoint++;
            }
            else{
                x += Delta() * checkpoints.get(currentCheckpoint).getXDirection() * speed;
                y += Delta() * checkpoints.get(currentCheckpoint).getYDirection() * speed;

            }
        }
    }

     private boolean CheckpointReached(){
        boolean reached = false;
        Tile t = checkpoints.get(currentCheckpoint).getTile();
        //check if position reached tile within variance of 3 (arbitrary)
        if(     x > t.getX() - 3 &&
                x < t.getX() + 3 &&
                y > t.getY() - 3 &&
                y < t.getY() + 3 ){

            reached = true;
            x = t.getX();
            y = t.getY();
        }

         return reached;
     }

    private void PopulateCheckpointList(){
        checkpoints.add(FindNextC(startTile,directions = FindNextD(startTile)));

        int counter = 0;
        boolean cont = true;
        while (cont){
            int[] currentD = FindNextD(checkpoints.get(counter).getTile());
            //Check if a next direction/checkpoint exist, end after 20 checkpoints (arbitrary)
            if(currentD[0] == 2 || counter == 20){
                cont = false;
            }
            else
                checkpoints.add(FindNextC(checkpoints.get(counter).getTile(),
                    directions = FindNextD(checkpoints.get(counter).getTile())));
            counter++;
            System.out.println("counter = " + counter);
        }
    }

    private Checkpoint FindNextC(Tile s,int[] dir){
        Tile next = null;
        Checkpoint c = null;

        //for while loop
        boolean found = false;
        int counter = 1;

        while (!found) {
            if(     s.getXPlace()+dir[0]*counter==grid.getTilesWide() ||
                    s.getYPlace() + dir[1] * counter == grid.getTilesHigh() ||
                    s.getType() !=
                    grid.GetTile(s.getXPlace()+dir[0]*counter,
                    s.getYPlace() + dir[1] *counter).getType())
            {
                found = true;
                //step back
                counter --;
                next = grid.GetTile(s.getXPlace()+dir[0]*counter,
                        s.getYPlace() + dir[1] *counter);
            }
            counter++;
        }

        c = new Checkpoint(next,dir[0],dir[1]);
        return c;
    }

    private int[] FindNextD(Tile s){
        int [] dir = new int[2];
        Tile u = grid.GetTile(s.getXPlace(),s.getYPlace()-1);
        Tile r = grid.GetTile(s.getXPlace()+1,s.getYPlace());
        Tile d = grid.GetTile(s.getXPlace(),s.getYPlace()+1);
        Tile l = grid.GetTile(s.getXPlace()-1,s.getYPlace());

        if(s.getType() == u.getType() && directions[1] != 1){
            dir[0] = 0;
            dir[1] = -1;
        }
        else if(s.getType() == r.getType() && directions[0] != -1){
            dir[0] = 1;
            dir[1] = 0;
        }
        else if(s.getType() == d.getType() && directions[1] != -1){
            dir[0] = 0;
            dir[1] = 1;
        }
        else if(s.getType() == l.getType() && directions[0] != 1){
            dir[0] = -1;
            dir[1] = 0;
        }
        else{
            dir[0] = 2;
            dir[1] = 2;
            System.out.println("NO DIRECTION FOUND");
        }


        return dir;
    }



    public void Draw(){
        DrawQuadTex(texture,x,y,width,height);
    }

    public TileGrid getTileGrid() {
        return grid;
    }




    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public boolean isFirst() {
        return first;
    }

    public void setGrid(TileGrid grid) {
        this.grid = grid;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
}
