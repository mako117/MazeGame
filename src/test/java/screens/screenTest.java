package screens;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.exit;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    HeadlessApplication application;

    AbstractTestWithHeadlessGdxContext() {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(this, conf);
        Gdx.gl = mock(GL20.class);

    }

    @Override
    public void render() {
        // no-op, prevents exception when trying to render since we are using a headless application
    }

    @AfterAll
    void afterAll() {
        application.exit();
    }
}

// @RunWith(GdxTestRunner.class)
public class screenTest extends AbstractTestWithHeadlessGdxContext {

    public @Rule
    ExpectedException exitCall = ExpectedException.none();

    private MazeGame testGame;
    private MainMenuScreen testScreen;
    // private Robot robot;

    // TODO: get the headless backend working; the current problem is getting OpenGL methods working
    @BeforeEach
    public void setup() {
        testGame = new MazeGame();
        testScreen = new MainMenuScreen(testGame);
    }

    /**
     * Test to see if the start button works.
     */
    @Test
    public void startButtonWorks() {

        Button startButton = testScreen.getStartButton();
        assertFalse(startButton.isChecked());

//        // what the start button does
//        testGame.startNewGame();
//        assertTrue(testGame.getCurrentScreen() instanceof GameScreen);

        // robot.mouseMove((int)(startButton.getX() + startButton.getWidth()), (int)(startButton.getY() + startButton.getHeight()));
        // robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        // robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

//     /**
//      * Test to see if the help button works.
//      */
//     @Test
//     public void helpButtonWorks() {
//         // doesn't click on help button
//         Button helpButton = testScreen.getHelpButton();
//         assertEquals(false, helpButton.isChecked());
//
//         // clicks help button
////         ((ChangeListener) (helpButton.getListeners().first())).changed(new ChangeListener.ChangeEvent(), helpButton);
//         testGame.showHelpScreen(testGame.getCurrentScreen());
//         assertTrue(testGame.getCurrentScreen() instanceof HelpScreen);
//     }
//
//     /**
//      * Test to see if the exit button works.
//      */
//     @Test
//     public void exitButtonWorks() {
//         // doesn't click on exit button
//         Button exitButton = testScreen.getExitButton();
//         assertEquals(false, exitButton.isChecked());
//
//         // clicks help button
////         ((ChangeListener) (exitButton.getListeners().first())).changed(new ChangeEvent(), exitButton);
//            testGame.exitGame();
//
//         assertEquals(true, exitButton.isChecked());
//     }
}
