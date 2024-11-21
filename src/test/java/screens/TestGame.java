package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class TestGame extends MazeGame {
    Runnable test;

    public TestGame(GameState status, Runnable test) {
        super(status);
        this.test = test;
    }

    @Override
    public void create() {
        super.create();
        Gdx.app.postRunnable(test);
    }
}
