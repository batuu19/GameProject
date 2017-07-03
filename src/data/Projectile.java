package data;

import org.newdawn.slick.opengl.Texture;

import static data.Game.TILE_SIZE;
import static helpers.Artist.DrawQuadTex;
import static helpers.Clock.Delta;

/**
 * Created by Bartek on 02.07.2017.
 */
public class Projectile {

    private Texture texture;
    private float x, y,speed,xVelocity,yVelocity;
    private int damage;
    private Enemy target;

    public Projectile(Texture texture, Enemy target, float x, float y, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
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

    public void Update(){
        x += xVelocity*speed*Delta();
        y += yVelocity*speed*Delta();
        Draw();
    }

    public void Draw(){
        DrawQuadTex(texture,x,y,32,32);
    }
}
