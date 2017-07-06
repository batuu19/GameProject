package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.QuickLoad;
import static helpers.Artist.TILE_SIZE;

/**
 * Created by Bartek on 30.06.2017.
 */
public class Tile {

    private float x,y;
    private int width,height;
    private Texture texture;
    private TileType type;

    public Tile(float x, float y, int width, int height, TileType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.texture = QuickLoad(type.textureName);
    }

    public void draw(){
        DrawQuadTex(texture,x,y,width,height);
    }


    public float getX() {
        return x;
    }

    public int getXPlace(){
        return (int) (x/TILE_SIZE);
    }

    public float getY() {
        return y;
    }
    public int getYPlace(){
        return (int) (y/TILE_SIZE);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Texture getTexture() {
        return texture;
    }

    public TileType getType() {
        return type;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}
