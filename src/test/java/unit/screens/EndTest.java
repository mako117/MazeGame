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

// import screens.EndScreen;
// import screens.MazeGame;

// // @RunWith(GdxTestRunner.class)
// public class EndTest {
//     final MazeGame testGame = mock(MazeGame.class);
//     private EndScreen testScreen;
//     private HeadlessApplication app;

//     // TODO: get the headless backend working; the current problem is getting OpenGL methods working
//     // assume the setup gets us to the Game Over screen.
//     @BeforeEach
//     public void setup() {

//     }

//     /**
//      * Test to see if the Play Again button works.
//      */
//     @Test
//     public void playAgainButtonWorks() {
//         // doesn't click on Play Again button
//         Button playAgainButton = testScreen.getPlayAgainButton();
//         assertEquals(false, playAgainButton.isChecked());

//         // clicks Play Again button
//         ((ChangeListener) (playAgainButton.getListeners().first())).changed(new ChangeEvent(), playAgainButton);
//         assertEquals(true, playAgainButton.isChecked());
//     }

//     /**
//      * Test to see if the Try Again button works.
//      */
//     @Test
//     public void tryAgainButtonWorks() {
//         // doesn't click on Try Again button
//         Button tryAgainButton = testScreen.getTryAgainButton();
//         assertEquals(false, tryAgainButton.isChecked());

//         // clicks Try Again button
//         ((ChangeListener) (tryAgainButton.getListeners().first())).changed(new ChangeEvent(), tryAgainButton);
//         assertEquals(true, tryAgainButton.isChecked());
//     }

//     /**
//      * Test to see if the exit button works.
//      */
//     @Test
//     public void exitButtonWorks() {
//         // doesn't click on Exit button
//         Button exitButton = testScreen.getExitButton();
//         assertEquals(false, exitButton.isChecked());

//         // clicks Exit button
//         ((ChangeListener) (exitButton.getListeners().first())).changed(new ChangeEvent(), exitButton);
//         assertEquals(true, exitButton.isChecked());
//     }
// }