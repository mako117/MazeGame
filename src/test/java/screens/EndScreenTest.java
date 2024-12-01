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

public class EndScreenTest extends AbstractTestWithHeadlessGdxContext {
    TestGame endGame;
    @Test
    public void EndScreenIntegrationTest() {
        Runnable r = () -> {
            try{ Thread.sleep(1500); endGame.exitGame(); } catch(Exception e) {};
        };
        endGame = new TestGame(GameState.EndMenu, true, r);
        application = new Lwjgl3Application(endGame, config);
        // only buttons and no key input, so nothing to test except that it is an EndScreen object
        assertEquals(true, (endGame.getCurrentScreen() instanceof EndScreen));
    }
}