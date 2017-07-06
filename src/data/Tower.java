package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.DrawQuadTex;

/**
 * Created by Bartek on 05.07.2017.
 */
public abstract class Tower implements Entity{

    private float x,y,firingSpeed;
    private int width,height,damage;
    private Enemy target;
    private Texture[] textures;

    public Tower(TowerType type,Tile startTile) {
        this.textures = type.textures;
        this.damage = type.damage;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = startTile.getWidth();
        this.height = startTile.getHeight();
        this.firingSpeed = type.firingSpeed;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setX(float x) {
        this.x=x;
    }

    @Override
    public void setY(float y) {
        this.y=y;
    }

    @Override
    public void setWidth(int width) {
        this.width=width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        for (int i = 0; i < textures.length; i++) {
            DrawQuadTex(textures[i],x,y,width,height);
        }
    }

    public boolean checkList(ArrayList<Tower> list){
        for (Tower t :
                list) {
            if (this.x == t.x && this.y == t.y)
                return false;
        }
        return true;
    }


    public float increaseSpeed(){
        return firingSpeed /=2;
    }

    public float decreaseSpeed(){
        return firingSpeed *=2;
    }
}
