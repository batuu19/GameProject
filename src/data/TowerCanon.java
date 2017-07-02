package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.Delta;

/**
 * Created by Bartek on 01.07.2017.
 */
public class TowerCanon {

    private float x,y,timeSinceLastShot,firingSpeed;
    private int width,height,damage;
    private Texture baseTexture,cannonTexture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;

    public TowerCanon(Texture baseTexture,Tile startTile,int damage, ArrayList<Enemy> enemies){
        this.baseTexture = baseTexture;
        this.cannonTexture = QuickLoad("cannonGun");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = (int) startTile.getWidth();
        this.height = (int) startTile.getHeight();
        this.damage = damage;
        this.firingSpeed = 3;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<>();
        this.enemies = enemies;
    }

    private void Shoot(){
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(QuickLoad("bullet"),x+32,y + 32,100,10));
    }

    public void Update(){
        timeSinceLastShot += Delta();
        if(timeSinceLastShot > firingSpeed)
            Shoot();
        for (Projectile p :
                projectiles) {
            p.Update();
        }
        Draw();
    }

    public void Draw(){
        DrawQuadTex(baseTexture,x,y,width,height);
        DrawQuadTexRot(cannonTexture,x,y,width,height,100);
    }
}
