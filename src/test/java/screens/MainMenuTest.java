package screens;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import org.junit.Rule;
import org.junit.jupiter.api.*;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import org.junit.rules.ExpectedException;

import screens.*;
import screens.MazeGame;

public class MainMenuTest extends AbstractTestWithHeadlessGdxContext {
    TestGame mainMenu;
    @Test
    public void MainMenuScreenIntegrationTest() {
        Runnable r = () -> {
            try{
                Thread.sleep(1500);
                mainMenu.exitGame();
            } catch(Exception e) {};
        };
        mainMenu = new TestGame(GameState.MainMenu, r);
        application = new Lwjgl3Application(mainMenu, config);
        // only buttons and no key input, so nothing to test except that it is an MainMenuScreen object
        assertEquals(true, (mainMenu.getCurrentScreen() instanceof MainMenuScreen));
    }

    /** Empty default constructor to allow creation of Javadocs without errors. */
    public MainMenuTest() {};

}