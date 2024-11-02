import board.Board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import directions.Direction;
import entities.Character;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import entities.enemy.Enemies;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;


public class GameScreen implements Screen {
	final MazeGame game;

    private Camera camera;
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
    private float enemyMovementOffset = 0;

    private int score = 0;
    
    private float time = 0;

    private final float TICKSPEED = 0.4f;
    private float tickCount = TICKSPEED;

    // Slow speed of input reading
    private float INPUT_TIMEOUT = TICKSPEED;

    private boolean paused;

    private TextureRegion resumeButton;
    private float resumeX, resumeY, resumeWidth, resumeHeight;

    GameScreen(MazeGame game) {
    	this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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

        gameboard = new Board();

        pauseTexture = new TextureRegion(new Texture("temp_pause.jpg"));
        System.out.println("Pause texture loaded: " + (pauseTexture.getTexture() != null));
        System.out.println("Pause texture width: " + pauseTexture.getRegionWidth() + ", height: " + pauseTexture.getRegionHeight());
        resumeButton = new TextureRegion(new Texture("temp_resume_button.png"));
        resumeWidth = resumeButton.getRegionWidth();
        resumeHeight = resumeButton.getRegionHeight();
        resumeX = (Gdx.graphics.getWidth() - resumeWidth) / 2;
        resumeY = (Gdx.graphics.getHeight() - resumeHeight) / 2;

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

        if(paused){
            float centerX = (camera.position.x - (Gdx.graphics.getWidth())/2);
            float centerY = (camera.position.y - (Gdx.graphics.getHeight())/2);

            batch.begin();
            batch.draw(pauseTexture, centerX, centerY, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.draw(resumeButton, centerX,centerY, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
            pause();
            return;
        }

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
    }

    private void renderText(){
//        String timeText = String.format("%.1f",time);
        font.draw(batch,String.format("%s%.1f","Time: ", time) , camera.position.x-viewport.getScreenWidth()/2+10, camera.position.y+viewport.getScreenHeight()/2-font.getLineHeight());
        font.draw(batch, String.format("%s%d","Score: ", score),camera.position.x-viewport.getScreenWidth()/2+10, camera.position.y+viewport.getScreenHeight()/2);
//        String scoreText = String.format("%d",score);
//        font.draw(batch, scoreText, camera.position.x, camera.position.y);
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
            enemies.get(i).draw(batch, TILE_SIZE, enemyMovementOffset);
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
                anEnemy.direction((anEnemy.find_player(player, gameboard)), gameboard);
            } else {
                PatrollingEnemies anEnemy = (PatrollingEnemies) enemies.get(i);
                anEnemy.direction('I', gameboard); // char input doesn't matter
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
        if(Gdx.input.isTouched()){
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            if(touchX > resumeX && touchX < resumeX + resumeWidth && touchY > resumeY && touchY < resumeY + resumeHeight){
                System.out.println("UNPAUSED");
                paused = false;
            }


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
