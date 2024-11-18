package screens;

import board.Board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import directions.Direction;
import entities.Character;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import entities.enemy.Enemies;
import org.lwjgl.opengl.GL20;
import java.util.ArrayList;
import java.util.Collections;

public class GameScreen extends ScreenAdapter {
	final MazeGame game;

    private OrthographicCamera camera;
    private Viewport viewport;

    private SpriteBatch batch;
    private BitmapFont font;
    private Texture backgroundTexture;
    // private TextureRegion blockTexture;
    private TextureRegion playerTexture;
    //private TextureRegion pauseTexture
    private TextureRegion pauseTexture;

    private Board gameboard;
    private Character player;
    private ArrayList<Enemies> enemies;
    private final int TILE_SIZE = 50; // size of tile

    // For smooth movement
    private boolean playerMovingXDirection = false;
    private float playerMovementOffset = 0;
    private ArrayList<Boolean> canEnemyMove;
    private float enemyMovementOffset = 0;

    private float time = 0;

    private final float TICKSPEED = 0.4f;
    private float tickCount = TICKSPEED;
    // Slow speed of input reading
    private float INPUT_TIMEOUT = TICKSPEED;

    private Skin skin;
	private Button pauseButton;
	private Button resumeButton;
    private Button exitButton;
    private Button helpButton;
    private Button restartButton;
    private Button continueButton;
	private Stage stage1;
    private Stage stage0;
    private Stage missionStage;
	private boolean paused;
    private boolean helpMenu;
    private boolean missionStatement = true;

    private OrthographicCamera fullscreenCamera;
    private boolean fullScreenMode = true;
    private float fullscreenDuration = 5f;
    private float fullscreenTimer = 0f;

    private TextureRegion movingEnemyTex;
    private TextureRegion patrollingEnemeyTex;

    Music gameMusic;

    private GameLogic logicObject = new GameLogic();


    /**
     *
     *
     * @param game
     */
    GameScreen(final MazeGame game) {
    	this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        fullscreenCamera = new OrthographicCamera();
        viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
//        viewport = new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        camera.update();

//        background = new Texture();
        batch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));

        backgroundTexture = new Texture("Space Background.png");
        movingEnemyTex = new TextureRegion(new Texture("DinoSprite.png"),4,1,17,17);
        patrollingEnemeyTex = new TextureRegion(new Texture("ptero.png"), 0,0,31,16);

        gameboard = new Board();

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Game Music.mp3"));
        gameMusic.setLooping(true);
        gameMusic.play();


        playerTexture = new TextureRegion(new Texture("Prototype_Character.png"));
        player = new Character(playerTexture, gameboard.getStart().getXPosition(), gameboard.getStart().getYPosition());

        enemies = new ArrayList<Enemies>();
        enemies.add(new PatrollingEnemies(1, 21, Direction.Up, 1, 21, 1, 21, patrollingEnemeyTex));
        enemies.add(new PatrollingEnemies(1, 9, Direction.Right, 1, 21, 1, 21, patrollingEnemeyTex));
        enemies.add(new Moving_Enemies(16, 14, movingEnemyTex));
        enemies.add(new Moving_Enemies(3, 20, movingEnemyTex));
        System.out.println(enemies.size());
        canEnemyMove = new ArrayList<Boolean>(Collections.nCopies(enemies.size(), Boolean.FALSE));

        pauseTexture = new TextureRegion(new Texture("Space Background.png"));
        System.out.println("Pause texture loaded: " + (pauseTexture.getTexture() != null));
        System.out.println("Pause texture width: " + pauseTexture.getRegionWidth() + ", height: " + pauseTexture.getRegionHeight());

        skin = new Skin(Gdx.files.internal("skin-soldier/star-soldier-ui.json"));
        stage0 = new Stage(viewport);
        pauseButton = new TextButton("PAUSE" , skin);
        pauseButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                paused = true;
            }
        });
        stage0.addActor(pauseButton);

        stage1 = new Stage(viewport);
		Gdx.input.setInputProcessor(stage1);

		resumeButton = new TextButton("Resume", skin);
        resumeButton.setSize(Gdx.graphics.getWidth() / 6 * 2, Gdx.graphics.getHeight() / 6);
		// listener for touch button
		resumeButton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						paused = false;
					}
			
		});
		stage1.addActor(resumeButton);
        
        exitButton = new TextButton("Exit", skin);
        exitButton.setSize(Gdx.graphics.getWidth() / 6 * 2, Gdx.graphics.getHeight() / 6);
        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
                gameMusic.stop();
                dispose();
            }
        });
        stage1.addActor(exitButton);

        helpButton = new TextButton("Help", skin);
        helpButton.setSize(Gdx.graphics.getWidth()/6 * 2,Gdx.graphics.getHeight() /6 );
        helpButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                helpMenu = true;
            }
        });
        stage1.addActor(helpButton);

        restartButton = new TextButton("Restart", skin);
        restartButton.setSize(Gdx.graphics.getWidth()/6 * 2,Gdx.graphics.getHeight() /6 );
        restartButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                gameMusic.stop();
                game.setScreen(new GameScreen(game));
            }
        });
        stage1.addActor(restartButton);

        missionStage = new Stage(viewport);
        continueButton = new TextButton("Continue", skin);
        continueButton.setSize(Gdx.graphics.getWidth()/10 * 2, Gdx.graphics.getHeight()/10);
        continueButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                missionStatement = false;
            }
        });
        missionStage.addActor(continueButton);



        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    /**
     *
     */
    public void show() {

    }

    /**
     * Used to check the user input on the keyboard
     */
    private void input() {

        if(INPUT_TIMEOUT <= 0){

            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                System.out.println("PAUSED");
                paused = true;

            } else



            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                if(player.direction('W', gameboard)){
                    playerMovementOffset = -TILE_SIZE;
                    playerMovingXDirection = false;
                    INPUT_TIMEOUT = TICKSPEED;
                }
                System.out.println("UP");
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                if(player.direction('D', gameboard)){
                    playerMovementOffset = -TILE_SIZE;
                    playerMovingXDirection = true;
                    INPUT_TIMEOUT = TICKSPEED;
                }
                System.out.println("RIGHT");
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                if(player.direction('A', gameboard)){
                    playerMovementOffset = TILE_SIZE;
                    playerMovingXDirection = true;
                    INPUT_TIMEOUT = TICKSPEED;
                }
                System.out.println("LEFT");
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.S)){
                if(player.direction('S', gameboard)){
                    playerMovementOffset = TILE_SIZE;
                    playerMovingXDirection = false;
                    INPUT_TIMEOUT = TICKSPEED;
                }
                System.out.println("DOWN");
            }
        }


    }

    /**
     * Used to render all aspects of GameScreen
     *
     * @param delta
     */
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(missionStatement){
            missionMenu();
            return;

        }

        if(paused){
            if(helpMenu) {
            	game.setScreen(new HelpScreen(game,this));
            	helpMenu = false;
            }
            pause();
            return;
        }

        if (fullScreenMode) {
            fullscreenTimer += delta;
            if (fullscreenTimer >= fullscreenDuration) {
                fullScreenMode = false;
                fullscreenTimer = 0;
            } else {
                fullScreen(delta);
                return;
            }
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        time+= Gdx.graphics.getDeltaTime();
        tickCount -= delta;

        if(tickCount < 0){
            tickCount = TICKSPEED;
            enemyMovementOffset = logicObject.moveEnemies(enemies, player, gameboard, canEnemyMove, TILE_SIZE);
        }

        logic();

        input();
        INPUT_TIMEOUT -= delta;

        // System.out.println(time);
        // update camera position
        if(playerMovingXDirection){
            camera.position.x = (player.getX()*TILE_SIZE + playerMovementOffset) + TILE_SIZE/2;
            camera.position.y = (player.getY()*TILE_SIZE) + TILE_SIZE/2;
        } else {
            camera.position.x = (player.getX()*TILE_SIZE) + TILE_SIZE/2;
            camera.position.y = (player.getY()*TILE_SIZE + playerMovementOffset) + TILE_SIZE/2;
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(backgroundTexture, camera.position.x-viewport.getScreenWidth()/2,camera.position.y-viewport.getScreenHeight()/2,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameboard.draw(batch, time, TILE_SIZE);
        renderPlayer(delta);
        renderEnemies(delta);
        renderText();

        batch.end();

        pauseButton.setSize(Gdx.graphics.getWidth() /10 + 40,Gdx.graphics.getHeight()/10);
        pauseButton.setPosition(camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth() - pauseButton.getWidth(),camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight() - pauseButton.getHeight());
        Gdx.input.setInputProcessor(stage0);
        stage0.act();
        stage0.draw();

    }

    private void renderText(){
//        String timeText = String.format("%.1f",time);
        font.draw(batch,String.format("%s%.1f","Time: ", time) , camera.position.x-viewport.getScreenWidth()/2+10, camera.position.y+viewport.getScreenHeight()/2-font.getLineHeight());
        font.draw(batch, String.format("%s%d","Score: ", player.getScore()),camera.position.x-viewport.getScreenWidth()/2+10, camera.position.y+viewport.getScreenHeight()/2);
//        String scoreText = String.format("%d",score);
//        font.draw(batch, scoreText, camera.position.x, camera.position.y);
    }

    /**
     *Renders The Text for the Initial Screen
     */
    private void readyText(){
        font.getData().setScale(1.5f,1.5f);
        font.draw(batch,String.format("READY!"), fullscreenCamera.position.x + viewport.getScreenWidth()/10 ,fullscreenCamera.position.y + viewport.getScreenHeight()/2 );
        font.draw(batch,String.format("Disarm all the bombs.\n\n" +
                "Avoiding all the dinosaurs.\n\n" +
                "Smash the dinosaur eggs for bonus points.\n\n" +
                "Avoid falling into a dinosaur nest.\n\n" +
                "Reach the end."), fullscreenCamera.position.x + viewport.getScreenWidth()/10, fullscreenCamera.position.y + viewport.getScreenHeight()/2 - 120);
        font.getData().setScale(1,1);
    }

    /**
     * Draws the player accounting for his movements to the game screen
     *
     * @param delta
     */
    private void renderPlayer(float delta) {
//        System.out.println(playerMovementOffset);
        player.draw(batch,TILE_SIZE, playerMovementOffset);

        if( Math.abs(playerMovementOffset) - TILE_SIZE/TICKSPEED*delta < 0){
            playerMovementOffset = 0;
        }
        else if(playerMovementOffset > 0){
            playerMovementOffset -= TILE_SIZE/TICKSPEED*delta;
        }
        else if(playerMovementOffset < 0){
            playerMovementOffset += TILE_SIZE/TICKSPEED*delta;

        }

    }


    /**
     * Draws all the enemies to the Game Screen
     *
     * @param delta
     */
    private void renderEnemies(float delta){
        for (int i = 0; i < enemies.size(); i++){
            if(canEnemyMove.get(i) == Boolean.TRUE){
                enemies.get(i).draw(batch, TILE_SIZE, enemyMovementOffset);
            } else {
                enemies.get(i).draw(batch, TILE_SIZE, 0);
            }
        }
        if(enemyMovementOffset < 0){
            enemyMovementOffset = 0;
        }
        else{
            enemyMovementOffset -= TILE_SIZE/TICKSPEED*delta;
        }
    }

    /**
     * This function will handle the game logic
     * This includes collision, moving enemies, checking/applying rewards/punishments
     */
    private void logic() {
        if(logicObject.checkPlayerCollision(player, enemies)) {
            playerEnd(false);
        }
        // checkPlayerCollision();

        if(logicObject.checkIfExitingMaze(player, gameboard)) {
            playerEnd(true);
        }
        // checkIfExitingMaze();

        if(logicObject.checkScore(player)) {
            playerEnd(false);
        }
        // checkScore();

        logicObject.checkReward(player, gameboard, time);
        // checkReward();

        logicObject.checkPunishment(player, gameboard, time);
        // checkPunishment();

        gameboard.genNewBonus(time);
    }

    /**
     * Sends the player to whichever screen depending on condition upon reaching the end.
     * @param condition
     */
    private void playerEnd(boolean condition) {
        gameMusic.stop();
    	game.setScreen(new EndScreen(game,player.getScore(),time,condition));
    }


    /**
     * @param width
     * @param height
     */
    public void resize(int width, int height) {
        viewport.update(width, height, true);
//        batch.setProjectionMatrix(camera.combined);
    }

    /**
     * This function controls the aspects of the pause menu, including drawing the buttons.
     */
    public void pause() {
        Gdx.input.setInputProcessor(stage1);
        batch.begin();
        batch.draw(pauseTexture, camera.position.x - (Gdx.graphics.getWidth())/2, camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        resumeButton.setPosition(camera.position.x - (Gdx.graphics.getWidth())/2 + (Gdx.graphics.getWidth() - resumeButton.getWidth())/2, camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight()/2 + resumeButton.getHeight());
        exitButton.setPosition(camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2,camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight()/2 - exitButton.getHeight() * 2);
        helpButton.setPosition(camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth()/2 - helpButton.getWidth()/2, camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight()/2);
        restartButton.setPosition(camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth()/2 - helpButton.getWidth()/2, camera.position.y - (Gdx.graphics.getHeight())/2 +Gdx.graphics.getHeight()/2 - restartButton.getHeight());
        stage1.act();
        stage1.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            System.out.println("UNPAUSED");
            paused = false;
        }
    }

    /**
     * This function displays the mission statements before the start of the game.
     */
    public void missionMenu(){
        continueButton.setPosition(camera.position.x - 128,camera.position.y - Gdx.graphics.getHeight()/2);
        Gdx.input.setInputProcessor(missionStage);
        batch.begin();
        batch.draw(pauseTexture, camera.position.x - (Gdx.graphics.getWidth())/2, camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.getData().setScale(2, 2);
        font.draw(batch, "The Mission", camera.position.x - 150, camera.position.y + 220);
        font.getData().setScale(1, 1);
        font.draw(batch, "The Dinosaurs are trying to stop their demise.\n" +
                "Collect All the bombs to ensure their extinction.\n" +
                "The fate of Humanity Rests in your hands.", camera.position.x - (Gdx.graphics.getWidth())/2 + 300, camera.position.y + 90);
        batch.end();
        missionStage.act();
        missionStage.draw();
    }

    /**
     * This functions displays the entire map before commencing the game.
     *
     * @param delta
     */
    public void fullScreen(float delta){
        fullscreenCamera.position.x = Gdx.graphics.getWidth()/2;
        fullscreenCamera.position.y = Gdx.graphics.getHeight()/2;
        fullscreenCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fullscreenCamera.zoom = 1.8f;
        fullscreenCamera.update();
        batch.setProjectionMatrix(fullscreenCamera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth() * 1.8f, Gdx.graphics.getHeight() * 1.8f);
        gameboard.draw(batch, time, TILE_SIZE);
        renderPlayer(delta);
        readyText();
        renderEnemies(delta);
        batch.end();
    }

    /**
     * This function is used to dispose the screen elements.
     */
    public void dispose() {
        batch.dispose();
        font.dispose();
        backgroundTexture.dispose();
        stage1.dispose();
        stage0.dispose();
        missionStage.dispose();
        gameMusic.dispose();
    }


    //*** Utility functions ***//
    public Button getContinueButton() {
        return this.continueButton;
    }
    public Button getPauseButton() {
        return this.pauseButton;
    }
    public Button getResumeButton() {
        return this.resumeButton;
    }
    public Button getHelpButton() {
        return this.helpButton;
    }
    public Button getRestartButton() {
        return this.restartButton;
    }
    public Button getExitButton() {
        return this.exitButton;
    }
    public float getFullScreenDuration() {
        return this.fullscreenDuration;
    }
}
