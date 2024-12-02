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
 * The public game class MazeGame
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
     * Used to create a new Maze Game
     * @param status the current Game state of the game
     */
    public MazeGame(GameState status){
        gameState  = status;
        winOrLose = false;
    }

    /**
     * Used to create a new game based on the condition
     * @param status the current Game state of the game
     * @param condition determines if win or lose
     */
    public MazeGame(GameState status, boolean condition){
        gameState  = status;
        winOrLose = condition;
    }

    /**
     * Creates many of the Textures, camera, and gamestates
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
     * Used to exit the application
     */
    public void exitGame(){
        Gdx.app.exit();
    }

    /**
     * Used to render objects
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Used to dispose
     */
    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        font.dispose();
        skin.dispose();
    }

    /**
     * Gets the current screen
     * @return the current screen
     */
    protected Screen getCurrentScreen() {
        return super.getScreen();
    }

    /**
     * gets the current game state
     * @return the current game state
     */
    public GameState getStatus() {
        return this.gameState;
    }

    /**
     * Resets the current camera
     */
    public void resetCamera() {
    	camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    	camera.zoom = 1.0f;
    	camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Sets the camera given the x-position y-position and the zoom level
     * @param x the desired x position
     * @param y the desired y position
     * @param zoom the desired zoom level.
     */
    public void setCamera(float x, float y, float zoom) {
    	camera.position.x = x;
    	camera.position.y = y;
    	camera.zoom = zoom;
    	camera.update();
    	batch.setProjectionMatrix(camera.combined);
    }
}
