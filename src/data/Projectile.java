package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.CheckCollision;
import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.TILE_SIZE;
import static helpers.Clock.Delta;

/**
 * Created by Bartek on 02.07.2017.
 */
public class Projectile implements Entity {

    private Texture texture;
    private float x, y,speed,xVelocity,yVelocity;
    private int width,height,damage;
    private Enemy target;
    private boolean alive;

    public Projectile(Texture texture, Enemy target, float x, float y,int width,int height, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.alive = true;
        this.xVelocity = 0f;
        this.yVelocity = 0f;
        calculateDirection();
    }

    private void calculateDirection(){
        float totalAllowedMovement = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX()-x+TILE_SIZE/2);
        float yDistanceFromTarget = Math.abs(target.getY()-y+TILE_SIZE/2);
        float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
        float xPercentOfMovement = xDistanceFromTarget/totalDistanceFromTarget;
        xVelocity = xPercentOfMovement;
        yVelocity = totalAllowedMovement-xPercentOfMovement;
        if(target.getX()<x)
            xVelocity *= -1;
        if(target.getY()<y)
            yVelocity *= -1;

    }

    public void update(){
        if(alive) {
            x += xVelocity * speed * Delta();
            y += yVelocity * speed * Delta();
            if (CheckCollision(x, y, width, height, target.getX(), target.getY(), target.getWidth(), target.getHeight())) {
                target.damage(damage);
                alive = false;
            }

//            System.out.println("check");
            draw();
        }
    }

    public void draw(){
        DrawQuadTex(texture,x,y,32,32);
    }


    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
