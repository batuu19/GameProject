package helpers;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glMatrixMode;

/**
 * Created by Bartek on 30.06.2017.
 */
public class Artist {

    public static final int WIDTH = 1280,HEIGHT = 960,TILE_SIZE = 64;
    public static final int SCREEN_WIDTH = Display.getDesktopDisplayMode().getWidth(), SCREEN_HEIGHT = Display.getDesktopDisplayMode().getHeight();
    public static void BeginSession(){
        Display.setTitle("Witaj "+System.getProperty("user.name"));
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }


        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,WIDTH,HEIGHT,0,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);

    }

    public static boolean CheckCollision(float x1, float y1, float width1, float height1,
                                         float x2, float y2, float width2, float height2){
        if(x1 + width1 > x2 && x1< x2 + width2 &&
                y1 + height1 > y2 && y1 < y2 + height2)
            return true;
        return false;
    }

    public static void DrawQuad(float x,float y,float width,float height){

        glBegin(GL_QUADS);
        glVertex2f(x,y);    //top left
        glVertex2f(x + width,y);    //top right
        glVertex2f(x + width,y + height);    //bottom right
        glVertex2f(x,y + height);    //bottom left
        glEnd();
    }

    public static void DrawQuadTex(Texture tex,float x, float y, float width, float height){
        tex.bind();
        glTranslatef(x,y,0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f(width,0);
        glTexCoord2f(1,1);
        glVertex2f(width,height);
        glTexCoord2f(0,1);
        glVertex2f(0,height);
        glEnd();
        glLoadIdentity();
    }
    public static void DrawQuadTexRot(Texture tex,float x, float y, float width, float height,float angle){
        tex.bind();
        glTranslatef(x + width/2,y +height/2,0);
        glRotatef(angle,0,0,1);
        glTranslatef(-width / 2,-width/2,0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f(width,0);
        glTexCoord2f(1,1);
        glVertex2f(width,height);
        glTexCoord2f(0,1);
        glVertex2f(0,height);
        glEnd();
        glLoadIdentity();
    }

    public static Texture LoadTexture(String path,String fileType){
        Texture tex = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture(fileType,in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tex;
    }
    public static Texture QuickLoad(String name){
        Texture tex = null;
        tex = LoadTexture("res/" + name + ".png","PNG");
        return tex;
    }

    public static int yPosition(){
        return ((HEIGHT>SCREEN_HEIGHT?(HEIGHT - ((int) (((float) Mouse.getY() / (float) SCREEN_HEIGHT) * (HEIGHT-SCREEN_HEIGHT)))):HEIGHT)- Mouse.getY() -1);
    }
}
