package data;

import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static helpers.Artist.*;
import static helpers.Clock.Delta;

/**
 * Created by Bartek on 05.07.2017.
 */
public abstract class Tower implements Entity{

    private float x,y,firingSpeed,timeSinceLastShot,angle;
    private int width,height,damage,range;
    private Enemy target;
    private Texture[] textures;
    private ArrayList<Enemy> enemies;
    private boolean targeted;
    private ArrayList<Projectile> projectiles;

    public Tower(TowerType type,Tile startTile,ArrayList<Enemy> enemies) {
        this.textures = type.textures;
        this.damage = type.damage;
        this.range = type.range;
        angle = 0;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = startTile.getWidth();
        this.height = startTile.getHeight();
        this.firingSpeed = type.firingSpeed;
        this.enemies = enemies;
        this.targeted = false;
        timeSinceLastShot = 0f;
        this.projectiles = new ArrayList<>();
    }

    private boolean isInRange(Enemy e){
        float   xDistance = Math.abs(e.getX() - x),
                yDistance = Math.abs(e.getY() - y);
        return xDistance < range && yDistance < range;
    }

    private float findDistance(Enemy e){
        float   xDistance = Math.abs(e.getX() - x),
                yDistance = Math.abs(e.getY() - y);
        return xDistance + yDistance;

    }

    private float calculateAngle(){
        double angleTemp = Math.atan2(target.getY()-y,target.getX()-x);
        return (float)Math.toDegrees(angleTemp) - 90;

    }

    private void shoot(){
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(QuickLoad("bullet"),target,
                x+TILE_SIZE/2 - TILE_SIZE/4,
                y + TILE_SIZE/2 - TILE_SIZE/4,
                TILE_SIZE/2,TILE_SIZE/2,
                900,damage));
    }


    private Enemy acquireTarget(){
        Enemy closest = null;

        float closestDistance = 10000;

        for (Enemy e :
                enemies) {
            if(isInRange(e) && findDistance(e) < closestDistance){
                closestDistance = findDistance(e);
                closest = e;
            }
        }
        if(closest != null)
            targeted = true;
        return closest;
    }


    public void updateEnemyList(ArrayList<Enemy> newList){
        enemies = newList;
    }

    @Override
    public void update(){
        if(!targeted){
            target = acquireTarget();
        }

        if(target == null || !target.isAlive())
            targeted = false;



        timeSinceLastShot += Delta();
        if(timeSinceLastShot > firingSpeed)
            shoot();
        for (Projectile p :
                projectiles) {
            p.update();
        }
        angle = calculateAngle();
        draw();
    }

    @Override
    public void draw() {
        DrawQuadTex(textures[0],x,y,width,height);
        if(textures.length > 1)
            for (int i = 1; i < textures.length; i++)
                DrawQuadTexRot(textures[i], x, y, width, height,angle);
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
