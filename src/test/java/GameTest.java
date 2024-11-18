// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.*;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;

// import com.badlogic.gdx.scenes.scene2d.InputEvent;
// import com.badlogic.gdx.scenes.scene2d.ui.Button;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.ApplicationListener;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.backends.headless.HeadlessApplication;
// import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
// import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics;
// import com.badlogic.gdx.graphics.*;
// import org.junit.runner.RunWith;

// import screens.GameScreen;
// import screens.MazeGame;

// // @RunWith(GdxTestRunner.class)
// public class GameTest {
//     final MazeGame testGame = mock(MazeGame.class);
//     private GameScreen testScreen;
//     private HeadlessApplication app;

//     // TODO: get the headless backend working; the current problem is getting OpenGL methods working
//     // Assume the setup gets us past the main menu.
//     @BeforeEach
//     public void setup() {
//     }

//     /**
//      * test to see if the continue button works when left-clicked on by the mouse.
//      */
//     @Test
//     public void continueButtonWorks() {
//         // doesn't click start button
//         Button continueButton = testScreen.getContinueButton();
//         assertEquals(false, continueButton.isChecked());

//         // clicks pause button
//         ((ChangeListener) (continueButton.getListeners().first())).changed(new ChangeEvent(), continueButton);
//         assertEquals(true, continueButton.isChecked());
//     }

//     /**
//      * Test to see if the pause button works.
//      */
//     @Test
//     public void pauseButtonWorks() {
//         toPlayingStage();
        
//         // doesn't click pause button
//         Button pauseButton = testScreen.getPauseButton();
//         assertEquals(false, pauseButton.isChecked());

//         // clicks pause button
//         ((ChangeListener) (pauseButton.getListeners().first())).changed(new ChangeEvent(), pauseButton);
//         assertEquals(true, pauseButton.isChecked());
//     }

//     /**
//      * Test to see if the resume button works.
//      */
//     @Test
//     public void resumeButtonWorks() {
//         toPauseStage();

//         // doesn't click resume button
//         Button resumeButton = testScreen.getResumeButton();
//         assertEquals(false, resumeButton.isChecked());

//         // clicks resume button
//         ((ChangeListener) (resumeButton.getListeners().first())).changed(new ChangeEvent(), resumeButton);
//         assertEquals(true, resumeButton.isChecked());
//     }

//     /**
//      * Test to see if the help button works.
//      */
//     @Test
//     public void helpButtonWorks() {
//         toPauseStage();

//         // doesn't click help button
//         Button helpButton = testScreen.getHelpButton();
//         assertEquals(false, helpButton.isChecked());

//         // clicks help button
//         ((ChangeListener) (helpButton.getListeners().first())).changed(new ChangeEvent(), helpButton);
//         assertEquals(true, helpButton.isChecked());
//     }

//     /**
//      * Test to see if the restart button works.
//      */
//     @Test
//     public void restartButtonWorks() {
//         toPauseStage();

//         // doesn't click restart button
//         Button restartButton = testScreen.getRestartButton();
//         assertEquals(false, restartButton.isChecked());

//         // clicks restart button
//         ((ChangeListener) (restartButton.getListeners().first())).changed(new ChangeEvent(), restartButton);
//         assertEquals(true, restartButton.isChecked());
//     }

//     /**
//      * Test to see if the exit button works when left-clicked on by the mouse.
//      */
//     @Test
//     public void exitButtonWorks() {
//         toPauseStage();

//         // doesn't click exit button
//         Button exitButton = testScreen.getExitButton();
//         assertEquals(false, exitButton.isChecked());

//         // clicks exit button
//         ((ChangeListener) (exitButton.getListeners().first())).changed(new ChangeEvent(), exitButton);
//         assertEquals(true, exitButton.isChecked());
//     }

//     //*** Utility functions ***//
//     private void toPlayingStage() {
//         // gets past missionStage
//         Button continueButton = testScreen.getContinueButton();
//         ((ChangeListener) (continueButton.getListeners().first())).changed(new ChangeEvent(), continueButton); // programmatically press continue
//         try {wait((long)(testScreen.getFullScreenDuration()));} catch (InterruptedException e) {}; // wait for full screen at start of game to be done
//     }

//     private void toPauseStage() {
//         toPlayingStage();

//         // pauses the game
//         Button pauseButton = testScreen.getPauseButton();
//         ((ChangeListener) (pauseButton.getListeners().first())).changed(new ChangeEvent(), pauseButton);
//     }

// }