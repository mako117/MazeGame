package screens;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;

/**
 * "Unit" test for the HelpScreen class.
 */
public class HelpScreenTest extends AbstractTestWithHeadlessGdxContext {
    TestGame gameHelp;

    /**
     * Tests if the HelpScreen class runs.
     */
    @Test
    public void HelpScreenIntegrationTest() {
        Runnable r = () -> {
            try{
                Thread.sleep(1500);
                gameHelp.exitGame();
            } catch(Exception e) {};
        };
        gameHelp = new TestGame(GameState.Help, r);
        application = new Lwjgl3Application(gameHelp, config);
        // only buttons and no key input, so nothing to test except that it is an MainMenuScreen object
        assertEquals(true, (gameHelp.getCurrentScreen() instanceof HelpScreen));
    }

    /** Empty default constructor to allow creation of Javadocs without errors. */
    public HelpScreenTest() {};
}