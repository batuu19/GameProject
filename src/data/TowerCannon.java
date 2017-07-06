package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.Delta;

/**
 * Created by Bartek on 01.07.2017.
 */
public class TowerCannon{

    private float x,y,timeSinceLastShot,firingSpeed,angle;
    private int width,height,damage,range;
    private Texture baseTexture,cannonTexture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Enemy target;
    private boolean targeted;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage,int range,float firingSpeed, ArrayList<Enemy> enemies){
        this.baseTexture = baseTexture;
        this.cannonTexture = QuickLoad("cannonGun");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = (int) startTile.getWidth();
        this.height = (int) startTile.getHeight();
        this.damage = damage;
        this.range = range;
        this.firingSpeed = firingSpeed;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<>();
        this.enemies = enemies;
        this.targeted = false;
//        this.target = acquireTarget();
//        this.angle = calculateAngle();
    }








    public void draw(){
        DrawQuadTex(baseTexture,x,y,width,height);
        DrawQuadTexRot(cannonTexture,x,y,width,height,angle);
    }





}
