package data;

import helpers.Clock;
import helpers.StateManager;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

import java.io.File;

import static helpers.Artist.*;

/**
 * Created by Bartek on 30.06.2017.
 */
public class Boot {

    public Boot() {

        BeginSession();



//        Game game = new Game(map);
        while(!Display.isCloseRequested()){
            Clock.update();

//            game.update();
            StateManager.update();


//            System.out.println("SCREEN_WIDTH = " + Display.getDesktopDisplayMode().getWidth());

            Display.update();
            Display.sync(60);
        }


        Display.destroy();
    }

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File("lib\\natives").getAbsolutePath());
        new Boot();
    }
}
