package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Screen;


public class MazeGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public Skin skin;
    public Texture backgroundTexture;

    public GameState gameState;

    public MazeGame(GameState status){
        gameState  = status;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
        skin = new Skin(Gdx.files.internal("skin-soldier/star-soldier-ui.json"));
        backgroundTexture = new Texture("Space Background.png");

        if(gameState == GameState.MainMenu){
            setScreen(new MainMenuScreen(this));
        } else if (gameState == GameState.Game || gameState == GameState.Pause || gameState == GameState.DirectGame) {
            setScreen(new GameScreen(this));
        } else if(gameState == GameState.Help) {
            setScreen(new HelpScreen(this, new GameScreen(this)));
        }else if(gameState == GameState.EndMenu) {
            setScreen(new EndScreen(this, 0, 0, false));
        }
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

    protected Screen getCurrentScreen() {
        return super.getScreen();
    }

    public GameState getStatus() {
        return this.gameState;
    }

}
