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

    private int score = 0;
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
    private Button backButton;
    private Button page1Button;
    private Button page2Button;
    private Button page3Button;
    private Button continueButton;
	private Stage stage1;
    private Stage stage0;
    private Stage stage2;
    private Stage stage3;
    private Stage stage4;
    private Stage missionStage;
	private boolean paused;
    private boolean helpMenu;
    private boolean page2;
    private boolean page3;
    private boolean missionStatement = true;

    private OrthographicCamera fullscreenCamera;
    private boolean fullScreenMode = true;
    private float fullscreenDuration = 5f;
    private float fullscreenTimer = 0f;

    private TextureRegion RrewardTex;
    private TextureRegion RpunishmentTex;
    private TextureRegion BrewardTex;
    private TextureRegion BpunishmentTex;
    private TextureRegion movingEnemyTex;
    private TextureRegion patrollingEnemeyTex;
    private TextureRegion endBlockTex;

    Music gameMusic;


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
        RrewardTex = new TextureRegion(new Texture("bomb.png"));
        BrewardTex = new TextureRegion(new Texture("dinosaur_egg.png"));
        RpunishmentTex = new TextureRegion(new Texture("baby_dinosaur.png"));
        BpunishmentTex = new TextureRegion(new Texture("alien.png"));
        movingEnemyTex = new TextureRegion(new Texture("DinoSprite.png"),4,1,17,17);
        patrollingEnemeyTex = new TextureRegion(new Texture("ptero.png"), 0,0,31,16);
        endBlockTex = new TextureRegion(new Texture("green.png"));

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

        stage2 = new Stage(viewport);
        backButton = new TextButton("Back", skin);
        backButton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                page2 = false;
                page3 = false;
                helpMenu = false;
            }
        });
        stage2.addActor(backButton);

        page2Button = new TextButton("Page 2", skin);
        page2Button.setSize(Gdx.graphics.getWidth()/10*2,Gdx.graphics.getHeight()/10);
        page2Button.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                page2 = true;
                page3 = false;
            }
        });
        stage2.addActor(page2Button);

        stage3 = new Stage(viewport);
        page1Button = new TextButton("Page 1", skin);
        page1Button.setSize(Gdx.graphics.getWidth()/10 * 2, Gdx.graphics.getHeight()/10);
        page1Button.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                page2 = false;
                page3 = false;
            }
        });
        stage3.addActor(page1Button);

        stage4 = new Stage(viewport);
        page3Button = new TextButton("Page 3", skin);
        page3Button.setSize(Gdx.graphics.getWidth()/10 * 2, Gdx.graphics.getHeight()/10);
        page3Button.addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                page3 = true;
                page2 = false;
            }
        });
        stage3.addActor(page3Button);

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
            if(helpMenu){
                if(page3){
                    page3Menu();
                    return;
                }

                if(page2){
                    page2Menu();
                    return;
                }

                helpMenu();
                return;
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
            moveEnemies();
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
        font.draw(batch, String.format("%s%d","Score: ", score),camera.position.x-viewport.getScreenWidth()/2+10, camera.position.y+viewport.getScreenHeight()/2);
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
        checkPlayerCollision();
        checkIfExitingMaze();
        checkScore();
        checkReward();
        checkPunishment();
        gameboard.genNewBonus(time);
    }


    /**
     * This function checks and allows the enemies to move
     */
    private void moveEnemies(){
        for(int i = 0; i < enemies.size(); i++) {
            boolean isMovingEnemy = (enemies.get(i)) instanceof Moving_Enemies; // Q: are objects in list of type Enemy or do they retain their subclass?
            if(isMovingEnemy == true) {
                Moving_Enemies anEnemy = (Moving_Enemies) enemies.get(i);
                canEnemyMove.set(i, (Boolean) anEnemy.direction((anEnemy.find_player(player, gameboard)), gameboard));
            } else {
                PatrollingEnemies anEnemy = (PatrollingEnemies) enemies.get(i);
                canEnemyMove.set(i, (Boolean)anEnemy.direction('I', gameboard)); // char input doesn't matter
            }
        }
        enemyMovementOffset = TILE_SIZE;
    }

    /**
     * Checks if the player and an enemy
     */
    private void checkPlayerCollision(){
        int playerX = player.getX();
        int playerY = player.getY();
        for(int i = 0; i < enemies.size(); i++) {
            Enemies anEnemy = enemies.get(i);
            int anEnemyX = anEnemy.getX();
            int anEnemyY = anEnemy.getY();
            if(playerX == anEnemyX && playerY == anEnemyY) {
                playerEnd(false);
                break;
            }
        }
    }

    /**
     * Checks to see if the player has reached the end
     */
    private void checkIfExitingMaze() {
        int playerX = player.getX();
        int playerY = player.getY();
        int playerRewardCnt = player.getRewardsCollected();
        if((gameboard.getEnd().getXPosition() == playerX) && (gameboard.getEnd().getYPosition() == playerY) && (playerRewardCnt == gameboard.getTotalRegRewardCnt())) {
            playerEnd(true);
        }
    }

    /**
     * Check if the player as reached a reward. <br>
     * If there is a reward, the reward will be collected and the score added to player score. <br>
     * The reward is removed from board after. <br>
     */
    public void checkReward() {
        int playerX = player.getX();
        int playerY = player.getY();
        int fromRegs = gameboard.regRewardCollect(playerX, playerY);
        int fromBons = gameboard.bonRewardCollect(playerX, playerY,time);
        int score = fromRegs + fromBons;
        if(fromRegs > 0) {
            player.addRegReward();
            // System.out.println("triggered, total reg rewards collected = " + player.getRewardsCollected());
        }
        this.add_score(score);
    }

    /**
     * Checks if the player's score is less than zero.
     */
    public void checkScore() {
        if(this.score < 0) {
            playerEnd(false);
        }
    }

    /**
     * Check if the player reached a punishment. <br>
     * If there is a punishment, the punishment will be given to the player. <br>
     * The punishment is removed after.<br>
     */
    public void checkPunishment() {
        int playerX = player.getX();
        int playerY = player.getY();
        int score = gameboard.regPunishmentCollect(playerX, playerY) + gameboard.bonPunishmentCollect(playerX, playerY, time);
        this.minus_score(score);
    }

    // TODO: write code for if the player wins

    /**
     * Sends the player to whichever screen depending on condition upon reaching the end.
     * @param condition
     */
    private void playerEnd(boolean condition) {
        gameMusic.stop();
    	game.setScreen(new EndScreen(game,score,time,condition));
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
     * This function controls the aspects of the help menu page 1.
     */
    public void helpMenu(){
        stage2.addActor(backButton);
        stage2.addActor(page2Button);
        Gdx.input.setInputProcessor(stage2);
        backButton.setPosition(camera.position.x - 128,camera.position.y - Gdx.graphics.getHeight()/2);
        page2Button.setPosition(camera.position.x + 128, camera.position.y - Gdx.graphics.getHeight()/2);

        batch.begin();
        batch.draw(pauseTexture, camera.position.x - (Gdx.graphics.getWidth())/2, camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        font.getData().setScale(2, 2);
        font.draw(batch, "How to play", camera.position.x - 150, camera.position.y + 220);

        font.getData().setScale(1,1);
        font.draw(batch, "Movement: Use 'W', 'A', 'S', 'D' to move in the game", camera.position.x - (Gdx.graphics.getWidth())/2 + 50, camera.position.y + 90);
        font.draw(batch, "Stop: Use 'Esc' can stop in the game", camera.position.x - (Gdx.graphics.getWidth())/2 + 50 ,camera.position.y);
        font.draw(batch, "Win the Game: Collect all the rewards show on the board and go to the end block", camera.position.x - (Gdx.graphics.getWidth())/2 + 50 ,camera.position.y - 90);
        font.draw(batch, "Lose the Game: Monster catch you or point lower than 0", camera.position.x - (Gdx.graphics.getWidth())/2 + 50,camera.position.y - 180);

        batch.end();

        stage2.act();
        stage2.draw();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            helpMenu = false;
        }
    }

    /**
     * This function controls the aspects of the help menu page 2
     */
    public void page2Menu(){
        stage3.addActor(backButton);
        backButton.setPosition(camera.position.x - 128,camera.position.y - Gdx.graphics.getHeight()/2);
        page1Button.setPosition(camera.position.x - 384,camera.position.y - Gdx.graphics.getHeight()/2);
        page3Button.setPosition(camera.position.x + 128,camera.position.y - Gdx.graphics.getHeight()/2);
        Gdx.input.setInputProcessor(stage3);

        batch.begin();
        batch.draw(pauseTexture, camera.position.x - (Gdx.graphics.getWidth())/2, camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.draw(RrewardTex, camera.position.x - (Gdx.graphics.getWidth())/2 + 50, camera.position.y + 30 , Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        batch.draw(BrewardTex, camera.position.x - (Gdx.graphics.getWidth())/2 + 50, camera.position.y - 60 , Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        batch.draw(RpunishmentTex, camera.position.x - (Gdx.graphics.getWidth())/2 + 50, camera.position.y - 150, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        batch.draw(BpunishmentTex, camera.position.x - (Gdx.graphics.getWidth())/2 + 50, camera.position.y - 240, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

        font.getData().setScale(2, 2);
        font.draw(batch, "How to play", camera.position.x - 150, camera.position.y + 220);

        font.getData().setScale(1, 1);
        font.draw(batch, ": Reward, collect can get 10 point", camera.position.x - (Gdx.graphics.getWidth())/2 + 210, camera.position.y + 90);
        font.draw(batch, ": Bonus reward, collect can get 10 point, appears on maps in fix time", camera.position.x - (Gdx.graphics.getWidth())/2 + 210, camera.position.y);
        font.draw(batch, ": Punishment, collecting loses 10 points", camera.position.x - (Gdx.graphics.getWidth())/2 + 210, camera.position.y - 90);
        font.draw(batch, ": Bonus Punishment, collect will loss 10 point, appear on map in fix time", camera.position.x - (Gdx.graphics.getWidth())/2 + 210, camera.position.y - 180);

        batch.end();
        stage3.draw();
        stage3.act();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            page2 = false;
            helpMenu = false;
        }
    }

    /**
     * This function controls the aspects of the helpe meny page 3
     */
    public void page3Menu(){
        stage4.addActor(backButton);
        stage4.addActor(page2Button);
        backButton.setPosition(camera.position.x - 128,camera.position.y - Gdx.graphics.getHeight()/2);
        page2Button.setPosition(camera.position.x - 384,camera.position.y - Gdx.graphics.getHeight()/2);
        Gdx.input.setInputProcessor(stage4);

        batch.begin();
        batch.draw(pauseTexture, camera.position.x - (Gdx.graphics.getWidth())/2, camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.draw(movingEnemyTex, camera.position.x - (Gdx.graphics.getWidth())/2 + 50, camera.position.y + 30 , Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        batch.draw(patrollingEnemeyTex, camera.position.x - (Gdx.graphics.getWidth())/2 + 50, camera.position.y - 120, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        batch.draw(endBlockTex, camera.position.x - (Gdx.graphics.getWidth())/2 + 50, camera.position.y - 240, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);


        font.getData().setScale(2, 2);
        font.draw(batch, "How to play", camera.position.x - 150, camera.position.y + 220);
        font.getData().setScale(1, 1);
        font.draw(batch, ": This is the mighty T.rex.\n  He will hunt you.",camera.position.x - (Gdx.graphics.getWidth())/2 + 210, camera.position.y + 90);
        font.draw(batch, ": The Pterodactyl is mighty territorial\n  Avoid his set path.", camera.position.x - (Gdx.graphics.getWidth())/2 + 210, camera.position.y - 60);
        font.draw(batch,": Come to this block to win when collect all the rewards.",camera.position.x - (Gdx.graphics.getWidth())/2 + 210, camera.position.y - 180);

        batch.end();
        stage4.draw();
        stage4.act();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            page3 = false;
            page2 = false;
            helpMenu = false;
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
        stage2.dispose();
        stage3.dispose();
        stage4.dispose();
        missionStage.dispose();
        gameMusic.dispose();
    }

    /**
     * Updates the entities.Character's score.
     * @param change the change in the entities.Character's score.
     */
    public void scorechange(int change) {
        this.score += change;
    }
    /**
     * Returns the entities.Character's <score> as an integer.
     * @return <score>
     */
    public int getScore() {
        return this.score;
    }
    public void add_score(int s) {
    	this.score += s;
    }
    public void minus_score(int s) {
    	this.score -= s;
    }
}
