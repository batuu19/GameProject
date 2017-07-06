package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;

/**
 * Created by Bartek on 06.07.2017.
 */
public enum TowerType {

    CannonRed(new Texture[]{QuickLoad("cannonBase"),QuickLoad("cannonGun")},10,1),
    CannonBlue(new Texture[]{QuickLoad("cannonBaseBlue"),QuickLoad("cannonGunBlue")},30,3)
    ;

    Texture [] textures;
    int damage;
    float firingSpeed;

    TowerType(Texture[] textures,int damage,float firingSpeed){
        this.textures = textures;
        this.damage = damage;
        this.firingSpeed = firingSpeed;
    }

}
