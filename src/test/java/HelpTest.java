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

import screens.HelpScreen;
import screens.MazeGame;
import com.badlogic.gdx.ScreenAdapter;

// @RunWith(GdxTestRunner.class)
public class HelpTest {
    final MazeGame testGame = mock(MazeGame.class);
    private HelpScreen testScreen;
    private HeadlessApplication app;

    // TODO: get the headless backend working; the current problem is getting OpenGL methods working
    // assume the setup gets us to the help window.
    @BeforeEach
    public void setup() {

    }

    /**
     * Test to see if the back button works.
     */
    @Test
    public void backButtonWorks() {
        // doesn't click on back button
        Button backButton = testScreen.getBackButton();
        assertEquals(false, backButton.isChecked());

        // clicks back button
        ((ChangeListener) (backButton.getListeners().first())).changed(new ChangeEvent(), backButton);
        assertEquals(true, backButton.isChecked());
    }

    /**
     * Test to see if the Page 1 button works.
     */
    @Test
    public void page1ButtonWorks() {
        // doesn't click on page 1 button
        Button page1Button = testScreen.getPage1Button();
        assertEquals(false, page1Button.isChecked());

        // clicks page 1 button
        ((ChangeListener) (page1Button.getListeners().first())).changed(new ChangeEvent(), page1Button);
        assertEquals(true, page1Button.isChecked());
    }

    /**
     * Test to see if the Page 2 button works.
     */
    @Test
    public void page2ButtonWorks() {
        // doesn't click on page 1 button
        Button page2Button = testScreen.getPage2Button();
        assertEquals(false, page2Button.isChecked());

        // clicks page 1 button
        ((ChangeListener) (page2Button.getListeners().first())).changed(new ChangeEvent(), page2Button);
        assertEquals(true, page2Button.isChecked());
    }

    /**
     * Test to see if the Page 3 button works.
     */
    @Test
    public void page3ButtonWorks() {
        // doesn't click on page 1 button
        Button page3Button = testScreen.getPage3Button();
        assertEquals(false, page3Button.isChecked());

        // clicks page 1 button
        ((ChangeListener) (page3Button.getListeners().first())).changed(new ChangeEvent(), page3Button);
        assertEquals(true, page3Button.isChecked());
    }
    
}
