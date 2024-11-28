package screens;

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

import board.Board;
import directions.Direction;
import entities.Character;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import entities.enemy.Enemies;

import java.util.ArrayList;
import java.util.Collections;

public class GameScreen extends ScreenAdapter {
    final MazeGame game;

    private Viewport viewport;

    private final int TILE_SIZE = 50;

    private final Music gameMusic;

    private float time = 0;
    private final float TICKSPEED = 0.4f;
    private float tickCount = TICKSPEED;
    protected float INPUT_TIMEOUT = TICKSPEED;    // Slow speed of input reading

    // For smooth movement
    private boolean playerMovingXDirection = false;
    private float playerMovementOffset = 0;
    private ArrayList<Boolean> canEnemyMove;
    private float enemyMovementOffset = 0;

    protected PauseScreen pauseScreen;
    protected ReadyScreen readyScreen;
    protected GameLogic gameLogic;

    private Board gameboard;
    private Character player;
    private ArrayList<Enemies> enemies;


    public GameScreen(final MazeGame game) {
        this.game = game;
        
        
        viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), game.camera);
        
        gameLogic = new GameLogic();
        gameboard = new Board();
        player = new Character(game.playerTex, 1, 1);
        createEnemies();

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("Game Music.mp3"));
        gameMusic.setLooping(true);
        gameMusic.play();

        pauseScreen = new PauseScreen();
        readyScreen = new ReadyScreen(game);

        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Creates the enemies on the board.
     */
    private void createEnemies(){
        enemies = new ArrayList<Enemies>();
        enemies.add(new PatrollingEnemies(1, 21, Direction.Up, 1, 21, 1, 21, game.patrollingEnemeyTex));
        enemies.add(new PatrollingEnemies(1, 9, Direction.Right, 1, 21, 1, 21, game.patrollingEnemeyTex));
        enemies.add(new Moving_Enemies(16, 14, game.movingEnemyTex));
        enemies.add(new Moving_Enemies(3, 20, game.movingEnemyTex));
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
        game.resetCamera();
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

        if(game.gameState != GameState.DirectGame && game.gameState != GameState.Pause) {
            if (readyScreen.missionStatement) {
                readyScreen.missionMenu();
                return;
            }

            if (readyScreen.fullScreenMode) {
                readyScreen.fullscreenTimer += delta;
                if (readyScreen.fullscreenTimer >= readyScreen.fullscreenDuration) {
                    readyScreen.fullScreenMode = false;
                    readyScreen.fullscreenTimer = 0;
                } else {
                    readyScreen.fullScreen(delta, time, TILE_SIZE, gameboard, this);
                    return;
                }
            }
        }

        if(pauseScreen.paused){
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
        game.batch.draw(game.backgroundTexture, game.camera.position.x-viewport.getScreenWidth()/2,game.camera.position.y-viewport.getScreenHeight()/2,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameboard.draw(game.batch, time, TILE_SIZE);
        renderPlayer(delta);
        renderEnemies(delta);
        renderText();
        game.batch.end();


        pauseScreen.renderPauseButton();
    }

    /**
     * Moves camera focus point to player
     */
    private void moveCameraToPlayer(){
        // update camera position
        if(playerMovingXDirection){
        	game.setCamera((player.getX()*TILE_SIZE + playerMovementOffset) + TILE_SIZE/2, (player.getY()*TILE_SIZE) + TILE_SIZE/2, 1.0f);
            /*
        	game.camera.position.x = (player.getX()*TILE_SIZE + playerMovementOffset) + TILE_SIZE/2;
            game.camera.position.y = (player.getY()*TILE_SIZE) + TILE_SIZE/2;
            */
        } else {
        	game.setCamera((player.getX()*TILE_SIZE) + TILE_SIZE/2, (player.getY()*TILE_SIZE + playerMovementOffset) + TILE_SIZE/2, 1.0f);
        	/*
            game.camera.position.x = (player.getX()*TILE_SIZE) + TILE_SIZE/2;
            game.camera.position.y = (player.getY()*TILE_SIZE + playerMovementOffset) + TILE_SIZE/2;
        	*/
        }
        //camera.update();
        //game.batch.setProjectionMatrix(camera.combined);

    }

    private void renderText(){
        game.font.draw(game.batch,String.format("%s%.1f","Time: ", time) , game.camera.position.x-viewport.getScreenWidth()/2+10, game.camera.position.y+viewport.getScreenHeight()/2-game.font.getLineHeight());
        game.font.draw(game.batch, String.format("%s%d","Score: ", player.getScore()),game.camera.position.x-viewport.getScreenWidth()/2+10, game.camera.position.y+viewport.getScreenHeight()/2);

    }

    /**
     * Draws the player accounting for his movements to the game screen
     * @param delta
     */
    protected void renderPlayer(float delta) {
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
    protected void renderEnemies(float delta){
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
        // reset camera
        game.resetCamera();

        if(pauseScreen.helpMenu) {
            game.setScreen(new HelpScreen(game, this));
            pauseScreen.helpMenu = false;
        }

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

    // //*** Utility functions ***//
    // public Button getPauseButton() {
    //     return pauseScreen.pauseButton;
    // }
    // public Button getResumeButton() {
    //     return pauseScreen.resumeButton;
    // }
    // public Button getHelpButton() {
    //     return pauseScreen.helpButton;
    // }
    // public Button getRestartButton() {
    //     return pauseScreen.restartButton;
    // }
    // public Button getExitButton() {
    //     return pauseScreen.exitButton;
    // }
    // public float getFullScreenDuration() {
    //     return readyScreen.fullscreenDuration;
    // }

    /**
     * This class contains the pause screen.
     */
    private class PauseScreen{
        private Button pauseButton;
        private Button resumeButton;
        private Button exitButton;
        private Button helpButton;
        private Button restartButton;
        Stage stage1;
        private Stage stage0;
        private TextureRegion pauseTexture;

        protected boolean helpMenu;
        private boolean paused = false;

        PauseScreen(){
            if(game.gameState == GameState.Pause) {
                paused = true;
            }
            createButtons();

            pauseTexture = new TextureRegion(game.backgroundTexture);
//            System.out.println("Pause texture loaded: " + (pauseTexture.getTexture() != null));
//            System.out.println("Pause texture width: " + pauseTexture.getRegionWidth() + ", height: " + pauseTexture.getRegionHeight());
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
            game.batch.draw(pauseTexture, game.camera.position.x - (Gdx.graphics.getWidth())/2, game.camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.batch.end();
            resumeButton.setPosition(game.camera.position.x - (Gdx.graphics.getWidth())/2 + (Gdx.graphics.getWidth() - resumeButton.getWidth())/2, game.camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight()/2 + resumeButton.getHeight());
            exitButton.setPosition(game.camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2,game.camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight()/2 - exitButton.getHeight() * 2);
            helpButton.setPosition(game.camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth()/2 - helpButton.getWidth()/2, game.camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight()/2);
            restartButton.setPosition(game.camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth()/2 - helpButton.getWidth()/2, game.camera.position.y - (Gdx.graphics.getHeight())/2 +Gdx.graphics.getHeight()/2 - restartButton.getHeight());
            stage1.act();
            stage1.draw();

            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                System.out.println("UNPAUSED");
                paused = false;
            }
        }

        private void renderPauseButton(){
            pauseButton.setSize(Gdx.graphics.getWidth() /10 + 40,Gdx.graphics.getHeight()/10);
            pauseButton.setPosition(game.camera.position.x - (Gdx.graphics.getWidth())/2 + Gdx.graphics.getWidth() - pauseButton.getWidth(),game.camera.position.y - (Gdx.graphics.getHeight())/2 + Gdx.graphics.getHeight() - pauseButton.getHeight());
            Gdx.input.setInputProcessor(pauseScreen.stage0);
            stage0.act();
            stage0.draw();
        }

        /**
         * Dispose of gdx dependent objects.
         */
        void dispose(){
            stage0.dispose();
            stage1.dispose();
        }
    }

}
