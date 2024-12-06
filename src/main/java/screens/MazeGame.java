package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Screen;

/**
 * Manages the screens and therefore the game.
 */
public class MazeGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public Skin skin;
    public Texture backgroundTexture;
    public Texture playerWinsBackgroundTexture;
    public Texture playerLosesBackgroundTexture;

    protected TextureRegion RrewardTex;
    protected TextureRegion RpunishmentTex;
    protected TextureRegion BrewardTex;
    protected TextureRegion BpunishmentTex;
    protected TextureRegion movingEnemyTex;
    protected TextureRegion patrollingEnemeyTex;
    protected TextureRegion endblockTex;

    protected TextureRegion playerTex;
    
    protected OrthographicCamera camera;

    public GameState gameState;

    private boolean winOrLose;

    /**
     * MazeGame constructor.
     * @param status    Input for <code>this.status</code>.
     */
    public MazeGame(GameState status){
        gameState  = status;
        winOrLose = false;
    }

    /**
     * MazeGame constructor that can open the win or lose screens from within EndScreen.
     * @param status    Input for <code>this.status</code>.
     * @param condition Input for <code>winOrLose</code>.
     */
    public MazeGame(GameState status, boolean condition){
        gameState  = status;
        winOrLose = condition;
    }

    /**
     * Instantiates various objects used across all of the screens, sets the current screen depending on <code>this.status</code> and <code>winOrLose</code>.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
        skin = new Skin(Gdx.files.internal("skin-soldier/star-soldier-ui.json"));
        backgroundTexture = new Texture("Space Background.png");
        // playerWinsBackgroundTexture = new Texture("Meteor Hits Earth.png");
        playerWinsBackgroundTexture = new Texture("Killing the Dinosaurs.png");
        playerLosesBackgroundTexture = new Texture("Meteor Explodes.png");

        RrewardTex = new TextureRegion(new Texture("bomb.png"));
        BrewardTex = new TextureRegion(new Texture("dinosaur_egg.png"));

        RpunishmentTex = new TextureRegion(new Texture("baby_dinosaur.png"));
        BpunishmentTex = new TextureRegion(new Texture("alien.png"));

        endblockTex = new TextureRegion(new Texture("endPortal.png"));

        movingEnemyTex = new TextureRegion(new Texture("DinoSprite.png"),4,1,17,17);
        patrollingEnemeyTex = new TextureRegion(new Texture("ptero.png"), 0,0,31,16);
        playerTex = new TextureRegion(new Texture("character.png"));
        
        camera = new OrthographicCamera(); 

        if(gameState == GameState.MainMenu){
            setScreen(new MainMenuScreen(this));
        } else if (gameState == GameState.Game || gameState == GameState.Pause || gameState == GameState.DirectGame) {
            setScreen(new GameScreen(this));
        } else if(gameState == GameState.Help) {
            setScreen(new HelpScreen(this, new GameScreen(this)));
        }else if(gameState == GameState.EndMenu) {
            setScreen(new EndScreen(this, 0, 0, winOrLose));
        }
    }

    /**
     * Exits the game.
     */
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

    /**
     * Gets the current screen.
     * @return  The current Screen object.
     */
    protected Screen getCurrentScreen() {
        return super.getScreen();
    }

    /**
     * Gets the MazeGame's status.
     * @return  <code>this.gameState</code>.
     */
    public GameState getStatus() {
        return this.gameState;
    }

    /**
     * Resets the camera's position and zoom.
     */
    public void resetCamera() {
    	camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    	camera.zoom = 1.0f;
    	camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Sets the camera's position and zoom.
     * @param x Input for <code>camera.position.x</code>.
     * @param y Input for <code>camera.position.y</code>.
     * @param zoom  Input for <code>camera.zoom</code>.
     */
    public void setCamera(float x, float y, float zoom) {
    	camera.position.x = x;
    	camera.position.y = y;
    	camera.zoom = zoom;
    	camera.update();
    	batch.setProjectionMatrix(camera.combined);
    }
}
