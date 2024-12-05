package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Creates a MazeGame that takes runnables.
 */
public class TestGame extends MazeGame {
    Runnable test;

    /**
     * Default constructor.
     * @param status    Sets which window MazeGame will open.
     * @param test      The runnable.
     */
    public TestGame(GameState status, Runnable test) {
        super(status);
        this.test = test;
    }

    /**
     * EndScreen constructor.
     * @param status    Sets which window MazeGame will open.
     * @param condition Determines whether it will open the win or lose version of EndScreen.
     * @param test      The runnable.
     */
    public TestGame(GameState status, boolean condition, Runnable test) {
        super(status, condition);
        this.test = test;
    }

    @Override
    public void create() {
        super.create();
        Gdx.app.postRunnable(test);
    }

}
