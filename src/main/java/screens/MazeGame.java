package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.tools.javac.Main;


public class MazeGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public Skin skin;
    public Texture backgroundTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
        skin = new Skin(Gdx.files.internal("skin-soldier/star-soldier-ui.json"));
        backgroundTexture = new Texture("Space Background.png");

        setScreen(new MainMenuScreen(this));
    }

    public void exitGame(){
        Gdx.app.exit();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        font.dispose();
        skin.dispose();
    }
}
