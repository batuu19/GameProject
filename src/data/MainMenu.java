package data;

import UI.UI;
import helpers.StateManager;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

/**
 * Created by Bartek on 03.07.2017.
 */
public class MainMenu {

    private Texture background;
    private UI menuUI;

    public MainMenu() {
        float position = 0.35f;
        background = QuickLoad("mainmenu");
        menuUI = new UI();
        menuUI.addButton("Play","playButton",WIDTH/2-128,(int)(HEIGHT * position));
        menuUI.addButton("Editor","editorButton",WIDTH/2-128,(int)(HEIGHT * (position + 0.1f)));
        menuUI.addButton("Quit","quitButton",WIDTH/2-128,(int)(HEIGHT * (position + 0.2f)));

    }

    private void updateButtons(){
        if(Mouse.isButtonDown(0)){
            if(menuUI.isButtonClicked("Play"))
                StateManager.setState(StateManager.GameState.GAME);
            if(menuUI.isButtonClicked("Editor"))
                StateManager.setState(StateManager.GameState.EDITOR);
            if(menuUI.isButtonClicked("Quit"))
                System.exit(0);
        }
    }

    public void update(){
        DrawQuadTex(background,0,0,2048,1024);
        menuUI.draw();
        updateButtons();
    }
}
