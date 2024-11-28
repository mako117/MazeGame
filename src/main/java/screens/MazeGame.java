package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Screen;


public class MazeGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public Skin skin;
    public Texture backgroundTexture;

    protected TextureRegion RrewardTex;
    protected TextureRegion RpunishmentTex;
    protected TextureRegion BrewardTex;
    protected TextureRegion BpunishmentTex;
    protected TextureRegion movingEnemyTex;
    protected TextureRegion patrollingEnemeyTex;
    protected TextureRegion endblockTex;

    protected TextureRegion playerTex;

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

        RrewardTex = new TextureRegion(new Texture("bomb.png"));
        BrewardTex = new TextureRegion(new Texture("dinosaur_egg.png"));

        RpunishmentTex = new TextureRegion(new Texture("baby_dinosaur.png"));
        BpunishmentTex = new TextureRegion(new Texture("alien.png"));

        endblockTex = new TextureRegion(new Texture("green.png"));

        movingEnemyTex = new TextureRegion(new Texture("DinoSprite.png"),4,1,17,17);
        patrollingEnemeyTex = new TextureRegion(new Texture("ptero.png"), 0,0,31,16);
        playerTex = new TextureRegion(new Texture("Prototype_Character.png"));

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
