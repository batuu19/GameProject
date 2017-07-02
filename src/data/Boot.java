package data;

import helpers.Artist;
import helpers.Clock;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Bartek on 30.06.2017.
 */
public class Boot {

    public Boot() {

        BeginSession();

        int map[][] = {
                {0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,2,2,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1},
                {0,0,2,2,0,0,1,1,0,1,0,1,1,0,0,0,0,0,0,1},
                {0,0,2,2,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,1},
                {0,0,2,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,1,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,1,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1}
        };

        TileGrid grid = new TileGrid(map);
        grid.SetTile(3,4,grid.GetTile(2,4).getType());
        Enemy e = new Enemy(QuickLoad("enemy"),grid.GetTile(3,7),grid,64,64,10);
        Wave wave = new Wave(20,e);
        Player player = new Player(grid);
        TowerCanon tower = new TowerCanon(QuickLoad("cannonBase"),grid.GetTile(2,7),10);

        while(!Display.isCloseRequested()){
            Clock.update();

            grid.Draw();
            wave.Update();
            player.Update();
            tower.Update();

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    public static void main(String[] args) {
        new Boot();
    }
}
