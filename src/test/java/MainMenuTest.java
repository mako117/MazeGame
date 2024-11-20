// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import org.junit.jupiter.api.*;
// import org.mockito.Mock;
// import org.mockito.Mockito;

// import com.badlogic.gdx.scenes.scene2d.InputEvent;
// import com.badlogic.gdx.scenes.scene2d.ui.Button;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.Application;
// import com.badlogic.gdx.ApplicationListener;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Graphics;
// import com.badlogic.gdx.backends.headless.HeadlessApplication;
// import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
// import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
// import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
// import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
// import com.badlogic.gdx.graphics.*;
// import org.junit.runner.RunWith;
// import com.badlogic.gdx.ApplicationAdapter;

// import screens.MainMenuScreen;
// import screens.MazeGame;

// // @RunWith(GdxTestRunner.class)
// public class MainMenuTest {
//     Launcher launch;
//     private MazeGame testGame;
//     private MainMenuScreen testScreen;

//     // TODO: get the headless backend working; the current problem is getting OpenGL methods working
//     @BeforeEach
//     public void setup() {
//         Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
// //        config.setIdleFPS(60);
// //        config.useVsync(true);
//         config.setTitle("JURASSIC METEOR");

//         config.setWindowedMode(1280,720);
// //        config.setResizable(false);
//         testGame = new MazeGame();
//         new Lwjgl3Application(testGame, config);


//     }

//     /**
//      * Test to see if the start button works.
//      */
//     @Test
//     public void startButtonWorks() {
//         // doesn't click start button
//         Button startButton = testScreen.getStartButton();
//         assertEquals(false, startButton.isChecked());

//         // clicks start button
//         ((ChangeListener) (startButton.getListeners().first())).changed(new ChangeEvent(), startButton);
//         assertEquals(true, startButton.isChecked());
//     }

//     // /**
//     //  * Test to see if the help button works.
//     //  */
//     // @Test
//     // public void helpButtonWorks() {
//     //     // doesn't click on help button
//     //     Button helpButton = testScreen.getHelpButton();
//     //     assertEquals(false, helpButton.isChecked());

//     //     // clicks help button
//     //     ((ChangeListener) (helpButton.getListeners().first())).changed(new ChangeEvent(), helpButton);
//     //     assertEquals(true, helpButton.isChecked());
//     // }

//     // /**
//     //  * Test to see if the exit button works.
//     //  */
//     // @Test
//     // public void exitButtonWorks() {
//     //     // doesn't click on exit button
//     //     Button exitButton = testScreen.getExitButton();
//     //     assertEquals(false, exitButton.isChecked());

//     //     // clicks help button
//     //     ((ChangeListener) (exitButton.getListeners().first())).changed(new ChangeEvent(), exitButton);
//     //     assertEquals(true, exitButton.isChecked());
//     // }
// }
