package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.DrawQuadTex;
import static helpers.Clock.Delta;

/**
 * Created by Bartek on 02.07.2017.
 */
public class Projectile {

    Texture texture;
    float x, y,speed;
    int damage;

    public Projectile(Texture texture, float x, float y, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    public void Update(){
        x += Delta() * speed;
        Draw();
    }

    public void Draw(){
        DrawQuadTex(texture,x,y,32,32);
    }
}
