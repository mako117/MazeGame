import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
import com.badlogic.gdx.graphics.*;
import org.junit.runner.RunWith;

import screens.MainMenuScreen;
import screens.MazeGame;

// import java.awt.AWTException;
// import java.awt.Robot;
// import java.awt.event.InputEvent;
// import java.awt.event.KeyEvent;

// @RunWith(GdxTestRunner.class)
public class MainMenuTest {
    final MazeGame testGame = mock(MazeGame.class);
    private MainMenuScreen testScreen;
    // private Robot robot;
    private HeadlessApplication app;

    // TODO: get the headless backend working; the current problem is getting OpenGL methods working
    @BeforeEach
    public void setup() {
        MockGraphics mockGraphics = new MockGraphics();
        Gdx.graphics = mockGraphics;

        Gdx.gl = mock(GL20.class);

        testScreen = new MainMenuScreen(testGame);
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        app = new HeadlessApplication(new ApplicationListener() {
            @Override
            public void create() {
                // Set the screen to MainMenuScreen directly
                testScreen = new MainMenuScreen(testGame); // Pass null or a mock game instance if necessary
            }

            @Override
            public void resize(int width, int height) {}

            @Override
            public void render() {
                testScreen.render(1 / 60f); // Simulate a frame render
            }

            @Override
            public void pause() {}

            @Override
            public void resume() {}

            @Override
            public void dispose() {
                testScreen.dispose();
            }
        }, config);
        // try{robot = new Robot();} catch (Exception e) {};
    }

    /**
     * Test to see if the start button works.
     */
    @Test
    public void startButtonWorks() {
        // doesn't click start button
        Button startButton = testScreen.getStartButton();
        assertEquals(false, startButton.isChecked());

        // clicks start button
        ((ChangeListener) (startButton.getListeners().first())).changed(new ChangeEvent(), startButton);
        assertEquals(true, startButton.isChecked());

        // robot.mouseMove((int)(startButton.getX() + startButton.getWidth()), (int)(startButton.getY() + startButton.getHeight()));
        // robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        // robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Test to see if the help button works.
     */
    @Test
    public void helpButtonWorks() {
        // doesn't click on help button
        Button helpButton = testScreen.getHelpButton();
        assertEquals(false, helpButton.isChecked());

        // clicks help button
        ((ChangeListener) (helpButton.getListeners().first())).changed(new ChangeEvent(), helpButton);
        assertEquals(true, helpButton.isChecked());
    }

    /**
     * Test to see if the exit button works.
     */
    @Test
    public void exitButtonWorks() {
        // doesn't click on exit button
        Button exitButton = testScreen.getExitButton();
        assertEquals(false, exitButton.isChecked());

        // clicks help button
        ((ChangeListener) (exitButton.getListeners().first())).changed(new ChangeEvent(), exitButton);
        assertEquals(true, exitButton.isChecked());
    }
}
