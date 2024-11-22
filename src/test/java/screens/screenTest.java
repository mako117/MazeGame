package screens;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import org.junit.jupiter.api.*;

// @RunWith(GdxTestRunner.class)
public class screenTest extends AbstractTestWithHeadlessGdxContext{
    TestGame game;

    /**
     * Test if game runs.
     */
    @Test
    public void InputTest() {
        Runnable r = () -> {
            try{
                Robot robot = new Robot();
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_W);
                game.exitGame();
            } catch (AWTException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        game = new TestGame(GameState.Game, r);
        application = new Lwjgl3Application(game, config);
    }
}
