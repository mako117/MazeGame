package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sun.tools.javac.Main;


public class MazeGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;
    public Skin skin;
    public Texture backgroundTexture;


    protected GameScreen gameScreen;
    protected MainMenuScreen mainMenuScreen;
    protected HelpScreen helpScreen;
    protected EndScreen endScreen;

    private ScreenAdapter currentScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin = new Skin(Gdx.files.internal("skin-soldier/star-soldier-ui.json"));
        backgroundTexture = new Texture("Space Background.png");

        gameScreen = new GameScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        helpScreen = new HelpScreen(this);
        endScreen = new EndScreen(this);

        showMainMenuScreen();
    }


    public void showMainMenuScreen(){
        currentScreen = mainMenuScreen;
        setScreen(new MainMenuScreen(this));
    }

    public void startNewGame(){
        gameScreen = new GameScreen(this);
        currentScreen = gameScreen;
        setScreen(gameScreen);
    }

    public void showHelpScreen(ScreenAdapter prev){
        helpScreen.setPrevScreen(prev);
        currentScreen = helpScreen;
        setScreen(helpScreen);
    }

    public void showEndScreen(){

    }

    public void exitGame(){
        Gdx.app.exit();
    }

    public ScreenAdapter getCurrentScreen(){
        return currentScreen;
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        gameScreen.dispose();
        mainMenuScreen.dispose();
        helpScreen.dispose();
        endScreen.dispose();
        batch.dispose();
        font.dispose();
        backgroundTexture.dispose();
        skin.dispose();
    }
}
