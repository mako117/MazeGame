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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractTestWithHeadlessGdxContext extends ApplicationAdapter {
    Lwjgl3ApplicationConfiguration config;
    Lwjgl3Application application;

    AbstractTestWithHeadlessGdxContext() {
        config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("JURASSIC METEOR");
        config.setWindowedMode(1280,720);
     }

    @AfterAll
    void afterAll() {
        application.exit();
    }
}

public class EndTest extends AbstractTestWithHeadlessGdxContext {

    @Test
    public void integrationTest() {
        MazeGame endGame = new MazeGame(GameState.EndMenu);
        application = new Lwjgl3Application(endGame, config);
        assertEquals(true, (endGame.getCurrentScreen() instanceof EndScreen));
    }

    // // TODO: get the headless backend working; the current problem is getting OpenGL methods working
    // // assume the setup gets us to the Game Over screen.
    // @BeforeEach
    // public void setup() {
    // }

    // /**
    //  * Test to see if the Play Again button works.
    //  */
    // @Test
    // public void playAgainButtonWorks() {
    // }

    // /**
    //  * Test to see if the Try Again button works.
    //  */
    // @Test
    // public void tryAgainButtonWorks() {
    // }

    // /**
    //  * Test to see if the exit button works.
    //  */
    // @Test
    // public void exitButtonWorks() {
    // }
}