import board.Board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
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
    private final int BOARD_WIDTH = 15;
    private final int BOARD_HEIGHT = 20;
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
	private Stage stage1;
    private Stage stage0;
	private int change_x = 0;
	private int change_y = 0;
	private int middle_x = Gdx.graphics.getWidth() / 2;
	private int middle_y = Gdx.graphics.getHeight() / 2;
    private int left_x = 0;
    private int top_y = Gdx.graphics.getHeight();

    private int resumeX;
    private int resumeY;

    private boolean paused;

    private OrthographicCamera fullscreenCamera;
    private boolean fullScreenMode = true;
    private float fullscreenDuration = 5f;
    private float fullscreenTimer = 0f;
    private float zoomFactor = 2;

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

        playerTexture = new TextureRegion(new Texture("Prototype_Character.png"));
        player = new Character(playerTexture);

        enemies = new ArrayList<Enemies>();
        enemies.add(new PatrollingEnemies(2, 2, Direction.Up, 1, 10, 1, 10, new TextureRegion(new Texture("temp_ptero.png"))));
        enemies.add(new Moving_Enemies(1, 10, new TextureRegion(new Texture("rock.png"))));
        System.out.println(enemies.size());
        canEnemyMove = new ArrayList<Boolean>(Collections.nCopies(enemies.size(), Boolean.FALSE));

        gameboard = new Board();

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
		change_x = -780;
		change_y = -400;

		resumeButton = new TextButton("Resume", skin);
        resumeButton.setSize(Gdx.graphics.getWidth() / 6 * 2, Gdx.graphics.getHeight() / 6);
		// listener for touch button
		resumeButton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						paused = false;
					}
			
		});
        resumeX = (Gdx.graphics.getWidth() - (int)resumeButton.getWidth()) / 2;
        resumeY = (Gdx.graphics.getHeight() - (int)resumeButton.getHeight()) / 2;
		stage1.addActor(resumeButton);
        
        exitButton = new TextButton("Exit", skin);
        exitButton.setSize(Gdx.graphics.getWidth() / 6 * 2, Gdx.graphics.getHeight() / 6);
        exitButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        stage1.addActor((exitButton));


    }

    /**
     *
     */
    public void show() {

    }

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
     * @param delta
     */
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float centerX = (camera.position.x - (Gdx.graphics.getWidth())/2);
        float centerY = (camera.position.y - (Gdx.graphics.getHeight())/2);
        if(paused){
            Gdx.input.setInputProcessor(stage1);
            batch.begin();
            batch.draw(pauseTexture, centerX, centerY, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            resumeButton.setPosition(centerX + (Gdx.graphics.getWidth() - resumeButton.getWidth())/2, centerY + (Gdx.graphics.getHeight() - resumeButton.getHeight())/2);

            exitButton.setPosition(centerX + Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2,centerY + Gdx.graphics.getHeight()/5);
            stage1.act();
            stage1.draw();

            pause();
            return;
        }

        if (fullScreenMode) {
            fullscreenTimer += delta;

            if (fullscreenTimer >= fullscreenDuration) {
                fullScreenMode = false;
                fullscreenTimer = 0;
            } else {
                fullscreenCamera.position.x = Gdx.graphics.getWidth()/2;
                fullscreenCamera.position.y = Gdx.graphics.getHeight()/2;
                fullscreenCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                fullscreenCamera.zoom = 1.5f;
                fullscreenCamera.update();
                batch.setProjectionMatrix(fullscreenCamera.combined);
                batch.begin();
                batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth() * 1.5f, Gdx.graphics.getHeight() * 1.5f);
                gameboard.draw(batch, time, TILE_SIZE);
                renderPlayer(delta);
                readyText();
                renderEnemies(delta);
                batch.end();
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
        pauseButton.setPosition(centerX + Gdx.graphics.getWidth() - pauseButton.getWidth(),centerY + Gdx.graphics.getHeight() - pauseButton.getHeight());
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

    private void readyText(){
        font.draw(batch,String.format("READY!"), fullscreenCamera.position.x ,fullscreenCamera.position.y + viewport.getScreenHeight()/4 );
    }

    /**
     * Draw the player to the game screen
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


    // it will only draw the enemies in the array list
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
    }


    // add movement methods of the enemies in the list
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

    // check if player coordinates are the same with any enemies and act accordingly
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
        if(gameboard.regRewardCollect(playerX, playerY) > 0) {
            player.addRegReward();
            // System.out.println("triggered, total reg rewards collected = " + player.getRewardsCollected());
        }
        int score = gameboard.regRewardCollect(playerX, playerY) + gameboard.bonRewardCollect(playerX, playerY,time);
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
    private void playerEnd(boolean condition) {
    	game.setScreen(new EndScreen(game,score,time,condition));
    }

    // TODO: write code for if player loses
    private void playerLoses() {

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
     *
     */
    public void pause() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            System.out.println("UNPAUSED");
            paused = false;
        }
    }

    /**
     *
     */
    public void resume() {

    }

    /**
     *
     */
    public void hide() {

    }

    /**
     *
     */
    public void dispose() {
        batch.dispose();
        font.dispose();
        backgroundTexture.dispose();
        stage1.dispose();
        stage0.dispose();
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
