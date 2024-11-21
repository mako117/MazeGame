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


/**
 * Create context for libgdx components in board. T-T <br>
 * By extending this in our test class, we will override the create method, allowing Gdx textures to be initialized. <br>
 * Solution from: <a href="https://www.reddit.com/r/libgdx/comments/1by4jgn/loading_assets_for_tests_in_libgdx/">Loading assets for tests in libgdx?</a>
 */

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

// @RunWith(GdxTestRunner.class)
public class screenTest extends AbstractTestWithHeadlessGdxContext{

    /**
     * Test to see if the start button works.
     */
    @Test
    public void integrationTest() {
        Runnable r = () -> {
            try{
                Robot robot = new Robot();
                /* for (int i = 0; i < 10; i++) */ while(true) {
                    robot.keyPress(68);
                    robot.keyRelease(68);
                    robot.delay(1000);
                }
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        };

        TestGame game = new TestGame(GameState.Game, r);
        application = new Lwjgl3Application(game, config);
    }
}
