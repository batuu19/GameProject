package helpers;


import data.Editor;
import data.Game;
import data.MainMenu;

/**
 * Created by Bartek on 03.07.2017.
 */
public class StateManager {

    public static enum GameState {
        MAINMENU,GAME,EDITOR
    }

    public static GameState gameState = GameState.GAME;
    public static MainMenu mainMenu;
    public static Game game;
    public static Editor editor;

    static int map[][] = {
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

    public static void update(){
        switch (gameState){
            case MAINMENU:
                if(mainMenu == null){
                    mainMenu = new MainMenu();
                }
                mainMenu.update();
                break;
            case GAME:
                if(game == null)
                    game = new Game(map);
                game.update();
                break;
            case EDITOR:

                break;

        }
    }

    public static void setState(GameState newState){
        gameState = newState;
    }

}