package screens;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Unit" test for the GameScreen, PauseScreen, and ReadyScreen classes.
 */
public class GameScreenTest extends AbstractTestWithHeadlessGdxContext{
    TestGame game;

    /**
     * Test if game runs.
     */
    @Test
    public void GameScreenIntegrationTest() {
        Runnable r = () -> {
            assertEquals(true, game.getCurrentScreen() instanceof GameScreen);
            Robot robot;
            try{
                Thread.sleep(1000);
                robot = new Robot();
                robot.keyPress(KeyEvent.VK_W);
                game.exitGame();
            } catch (AWTException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        game = new TestGame(GameState.DirectGame, r);
        application = new Lwjgl3Application(game, config);
    }

    /**
     * Test if game with ready screen runs.
     */
    @Test
    public void ReadyScreenIntegrationTest() {
        Runnable r = () -> {
            assertEquals(true, game.getCurrentScreen() instanceof GameScreen);
            try{
                Thread.sleep(1000);
                game.exitGame();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        game = new TestGame(GameState.Game, r);
        application = new Lwjgl3Application(game, config);
    }

    /**
     * Test if game with pause screen runs.
     */
    @Test
    public void PauseScreenIntegrationTest() {
        Runnable r = () -> {
            assertEquals(true, game.getCurrentScreen() instanceof GameScreen);
            try{
                Thread.sleep(1000);
                game.exitGame();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        game = new TestGame(GameState.Pause, r);
        application = new Lwjgl3Application(game, config);
    }

    /** Empty default constructor to allow creation of Javadocs without errors. */
    public GameScreenTest() {};
}
