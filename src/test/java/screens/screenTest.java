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
                robot.delay(5000);
                long time = 120l;
                /* for (int i = 0; i < 10; i++) */ while(time > 0l) {
                    robot.keyPress(68);
                    robot.keyRelease(68);
                    robot.delay(1000);
                    time -= 1;  
                }
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        };

        TestGame game = new TestGame(GameState.Game, r);
        application = new Lwjgl3Application(game, config);
    }
}
