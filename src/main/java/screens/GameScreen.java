package screens;

import board.Board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import directions.Direction;
import entities.Character;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import entities.enemy.Enemies;
import java.util.ArrayList;
import java.util.Collections;

public class GameScreen extends ScreenAdapter {
    final MazeGame game;

    private OrthographicCamera camera;
    private Viewport viewport;

    private final int TILE_SIZE = 50;
    private final TextureRegion playerTexture = new TextureRegion(new Texture("Prototype_Character.png"));
    private final TextureRegion movingEnemyTex = new TextureRegion(new Texture("DinoSprite.png"),4,1,17,17);
    private final TextureRegion patrollingEnemeyTex = new TextureRegion(new Texture("ptero.png"), 0,0,31,16);
    private final Music gameMusic;

    private float time = 0;
    private final float TICKSPEED = 0.4f;
    private float tickCount = TICKSPEED;
    private float INPUT_TIMEOUT = TICKSPEED;    // Slow speed of input reading

    // For smooth movement
    private boolean playerMovingXDirection = false;
    private float playerMovementOffset = 0;
    private ArrayList<Boolean> canEnemyMove;
    private float enemyMovementOffset = 0;

    protected PauseScreen pauseScreen;
    protected ReadyScreen readyScreen;
    private GameLogic gameLogic;

    private Board gameboard;
    private Character player;
    private ArrayList<Enemies> enemies;


    public GameScreen(final MazeGame game) {
        this.game = game;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        camera.update();

        gameLogic = new GameLogic();
        gameboard = new Board();
        player = new Character(playerTexture, 1, 1);
        createEnemies();

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Game Music.mp3"));
        gameMusic.setLooping(true);
        gameMusic.play();

        pauseScreen = new PauseScreen();
        readyScreen = new ReadyScreen();

        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Creates the enemies on the board.
     */
    private void createEnemies(){
        enemies = new ArrayList<Enemies>();
        enemies.add(new PatrollingEnemies(1, 21, Direction.Up, 1, 21, 1, 21, patrollingEnemeyTex));
        enemies.add(new PatrollingEnemies(1, 9, Direction.Right, 1, 21, 1, 21, patrollingEnemeyTex));
        enemies.add(new Moving_Enemies(16, 14, movingEnemyTex));
        enemies.add(new Moving_Enemies(3, 20, movingEnemyTex));
        System.out.println(enemies.size());
        canEnemyMove = new ArrayList<Boolean>(Collections.nCopies(enemies.size(), Boolean.FALSE));
    }


    /**
     * Used to check the user input on the keyboard
     */
    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            System.out.println("PAUSED");
            pauseScreen.paused = true;
        }

        if(INPUT_TIMEOUT <= 0){
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
     * This function will handle the game logic
     * This includes collision, moving enemies, checking/applying rewards/punishments
     */
    private void logic() {
        if(gameLogic.checkPlayerCollision(player, enemies)) {
            playerEnd(false);
        }
        // checkPlayerCollision();

        if(gameLogic.checkIfExitingMaze(player, gameboard)) {
            playerEnd(true);
        }
        // checkIfExitingMaze();

        if(gameLogic.checkScore(player)) {
            playerEnd(false);
        }
        // checkScore();

        gameLogic.checkReward(player, gameboard, time);
        // checkReward();

        gameLogic.checkPunishment(player, gameboard, time);
        // checkPunishment();

        gameboard.genNewBonus(time);
    }

    /**
     * Sends the player to whichever screen depending on condition upon reaching the end.
     * @param condition
     */
    private void playerEnd(boolean condition) {
        gameMusic.stop();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.setProjectionMatrix(camera.combined);
        game.setScreen(new EndScreen(game, player.getScore(), time ,condition));
        dispose();
    }

    /**
     * Used to render all aspects of GameScreen
     *
     * @param delta
     */
    public void render(float delta) {


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(readyScreen.missionStatement){
            readyScreen.missionMenu();
            return;
        }

        if (readyScreen.fullScreenMode) {
            readyScreen.fullscreenTimer += delta;
            if (readyScreen.fullscreenTimer >= readyScreen.fullscreenDuration) {
                readyScreen.fullScreenMode = false;
                readyScreen.fullscreenTimer = 0;
            } else {
                readyScreen.fullScreen(delta);
                return;
            }
        }

        if(pauseScreen.paused){
            // reset camera
            camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.batch.setProjectionMatrix(camera.combined);

            if(pauseScreen.helpMenu) {
                game.setScreen(new HelpScreen(game, this));
                pauseScreen.helpMenu = false;
            }
            pause();
            return;
        }

        time+= Gdx.graphics.getDeltaTime();
        tickCount -= delta;

        if(tickCount < 0){
            tickCount = TICKSPEED;
            enemyMovementOffset = gameLogic.moveEnemies(enemies, player, gameboard, canEnemyMove, TILE_SIZE);
        }

        // System.out.println(time);
        moveCameraToPlayer();
        logic();
        input();
        INPUT_TIMEOUT -= delta;



        game.batch.begin();
        game.batch.draw(game.backgroundTexture, camera.position.x-viewport.getScreenWidth()/2,camera.position.y-viewport.getScreenHeight()/2,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameboard.draw(game.batch, time, TILE_SIZE);
        renderPlayer(delta);
        renderEnemies(delta);
        renderText();
        game.batch.end();

        pauseScreen.pauseButton.setSize(Gdx.graphics.getWidth() /10 + 40,Gdx.graphics.getHeight()/10);
        pauseScreen.pauseButton.setPosition(camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth() - pauseScreen.pauseButton.getWidth(),camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight() - pauseScreen.pauseButton.getHeight());
        Gdx.input.setInputProcessor(pauseScreen.stage0);
        pauseScreen.stage0.act();
        pauseScreen.stage0.draw();
    }

    /**
     * Moves camera focus point to player
     */
    private void moveCameraToPlayer(){
        // update camera position
        if(playerMovingXDirection){
            camera.position.x = (player.getX()*TILE_SIZE + playerMovementOffset) + TILE_SIZE/2;
            camera.position.y = (player.getY()*TILE_SIZE) + TILE_SIZE/2;
        } else {
            camera.position.x = (player.getX()*TILE_SIZE) + TILE_SIZE/2;
            camera.position.y = (player.getY()*TILE_SIZE + playerMovementOffset) + TILE_SIZE/2;
        }
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

    }

    private void renderText(){
        game.font.draw(game.batch,String.format("%s%.1f","Time: ", time) , camera.position.x-viewport.getScreenWidth()/2+10, camera.position.y+viewport.getScreenHeight()/2-game.font.getLineHeight());
        game.font.draw(game.batch, String.format("%s%d","Score: ", player.getScore()),camera.position.x-viewport.getScreenWidth()/2+10, camera.position.y+viewport.getScreenHeight()/2);

    }

    /**
     * Draws the player accounting for his movements to the game screen
     * @param delta
     */
    private void renderPlayer(float delta) {
//        System.out.println(playerMovementOffset);
        player.draw(game.batch,TILE_SIZE, playerMovementOffset);

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
                enemies.get(i).draw(game.batch, TILE_SIZE, enemyMovementOffset);
            } else {
                enemies.get(i).draw(game.batch, TILE_SIZE, 0);
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
     * Pause the game.
     */
    @Override
    public void pause() {
        pauseScreen.paused();
    }

    /**
     * This function is used to dispose the screen elements.
     */
    @Override
    public void dispose() {
        pauseScreen.dispose();
        readyScreen.dispose();
        gameMusic.dispose();
    }

    //*** Utility functions ***//
    public Button getContinueButton() {
        return readyScreen.continueButton;
    }
    public Button getPauseButton() {
        return pauseScreen.pauseButton;
    }
    public Button getResumeButton() {
        return pauseScreen.resumeButton;
    }
    public Button getHelpButton() {
        return pauseScreen.helpButton;
    }
    public Button getRestartButton() {
        return pauseScreen.restartButton;
    }
    public Button getExitButton() {
        return pauseScreen.exitButton;
    }
    public float getFullScreenDuration() {
        return readyScreen.fullscreenDuration;
    }

    /**
     * This class contains the pause screen.
     */
    private class PauseScreen{
        private Button pauseButton;
        private Button resumeButton;
        private Button exitButton;
        private Button helpButton;
        private Button restartButton;
        private Stage stage1;
        private Stage stage0;
        private TextureRegion pauseTexture;

        protected boolean helpMenu;
        private boolean paused = false;

        PauseScreen(){
            createButtons();

            pauseTexture = new TextureRegion(game.backgroundTexture);
            System.out.println("Pause texture loaded: " + (pauseTexture.getTexture() != null));
            System.out.println("Pause texture width: " + pauseTexture.getRegionWidth() + ", height: " + pauseTexture.getRegionHeight());
        }

        /**
         * Creates buttons and event listeners for pause screen.
         */
        private void createButtons(){
            stage0 = new Stage(viewport);
            pauseButton = new TextButton("PAUSE" , game.skin);
            pauseButton.addListener(new ChangeListener() {
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    paused = true;
                }
            });
            stage0.addActor(pauseButton);

            stage1 = new Stage(viewport);
            Gdx.input.setInputProcessor(stage1);

            resumeButton = new TextButton("Resume", game.skin);
            resumeButton.setSize(Gdx.graphics.getWidth() / 6 * 2, Gdx.graphics.getHeight() / 6);
            // listener for touch button
            resumeButton.addListener(new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    paused = false;
                }

            });
            stage1.addActor(resumeButton);

            exitButton = new TextButton("Exit", game.skin);
            exitButton.setSize(Gdx.graphics.getWidth() / 6 * 2, Gdx.graphics.getHeight() / 6);
            exitButton.addListener(new ChangeListener() {
                public void changed(ChangeEvent event, Actor actor) {
                    game.setScreen(new MainMenuScreen(game));
                    gameMusic.stop();
                    dispose();
                }
            });
            stage1.addActor(exitButton);

            helpButton = new TextButton("Help", game.skin);
            helpButton.setSize(Gdx.graphics.getWidth()/6 * 2,Gdx.graphics.getHeight() /6 );
            helpButton.addListener(new ChangeListener() {
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    helpMenu = true;
                }
            });
            stage1.addActor(helpButton);

            restartButton = new TextButton("Restart", game.skin);
            restartButton.setSize(Gdx.graphics.getWidth()/6 * 2,Gdx.graphics.getHeight() /6 );
            restartButton.addListener(new ChangeListener() {
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    gameMusic.stop();
                    game.setScreen(new GameScreen(game));
                }
            });
            stage1.addActor(restartButton);
        }

        /**
         * This function controls the aspects of the pause menu, including drawing the buttons.
         */
        public void paused() {
            Gdx.input.setInputProcessor(stage1);
            game.batch.begin();
            game.batch.draw(pauseTexture, camera.position.x - (Gdx.graphics.getWidth())/2, camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.batch.end();
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
         * Dispose of gdx dependent objects.
         */
        void dispose(){
            stage0.dispose();
            stage1.dispose();
        }
    }

    /**
     * This class has the ready screen information.
     */
    private class ReadyScreen{
        private Stage missionStage;
        private Button continueButton;
        protected boolean missionStatement = true;

        public OrthographicCamera fullscreenCamera;
        protected boolean fullScreenMode = true;
        protected float fullscreenDuration = 5f;
        protected float fullscreenTimer = 0f;

        ReadyScreen(){
            fullscreenCamera = new OrthographicCamera();

            missionStage = new Stage(viewport);
            continueButton = new TextButton("Continue", game.skin);
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
         * This function displays the mission statements before the start of the game.
         */
        public void missionMenu(){
            continueButton.setPosition(camera.position.x - 128,camera.position.y - Gdx.graphics.getHeight()/2);
            Gdx.input.setInputProcessor(missionStage);
            game.batch.begin();
            game.batch.draw(game.backgroundTexture, camera.position.x - (Gdx.graphics.getWidth())/2, camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.font.getData().setScale(2, 2);
            game.font.draw(game.batch, "The Mission", camera.position.x - 150, camera.position.y + 220);
            game.font.getData().setScale(1, 1);
            game.font.draw(game.batch, "The Dinosaurs are trying to stop their demise.\n" +
                    "Collect All the bombs to ensure their extinction.\n" +
                    "The fate of Humanity Rests in your hands.", camera.position.x - (Gdx.graphics.getWidth())/2 + 300, camera.position.y + 90);
            game.batch.end();
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
            game.batch.setProjectionMatrix(fullscreenCamera.combined);
            game.batch.begin();
            game.batch.draw(game.backgroundTexture, 0, 0, (Gdx.graphics.getWidth() * 1.8f), (Gdx.graphics.getHeight() * 1.8f));
            gameboard.draw(game.batch, time, TILE_SIZE);
            renderPlayer(delta);
            readyText();
            renderEnemies(delta);
            game.batch.end();
        }

        /**
         *Renders The Text for the Initial Screen
         */
        private void readyText(){
            game.font.getData().setScale(1.5f,1.5f);
            game.font.draw(game.batch,String.format("READY!"), fullscreenCamera.position.x + viewport.getScreenWidth()/10 ,fullscreenCamera.position.y + viewport.getScreenHeight()/2 );
            game.font.draw(game.batch,String.format("Disarm all the bombs.\n\n" +
                    "Avoiding all the dinosaurs.\n\n" +
                    "Smash the dinosaur eggs for bonus points.\n\n" +
                    "Avoid falling into a dinosaur nest.\n\n" +
                    "Reach the end."), fullscreenCamera.position.x + viewport.getScreenWidth()/10, fullscreenCamera.position.y + viewport.getScreenHeight()/2 - 120);
            game.font.getData().setScale(1,1);
        }

        /**
         * Dispose of gdx dependent objects.
         */
        void dispose(){
            missionStage.dispose();
        }
    }


}
