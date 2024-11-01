import board.Board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import directions.Direction;
import entities.Character;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import entities.enemy.Enemies;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;


public class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;

    private SpriteBatch batch;

    // private TextureRegion blockTexture;
    private TextureRegion playerTexture;

    private Board gameboard;
    private Character player;
    private ArrayList<Enemies> enemies;

    private final int BOARD_WIDTH = 20;
    private final int BOARD_HEIGHT = 30;
    private final int TILE_SIZE = 100; // size of tile

    // Slow speed of input reading
    private final int INPUT_TIMEOUT = 50;
    private int inputDisplacement = 0;

    // For smooth movement
    private boolean playerMovingXDirection = false;
    private int playerMovementOffset = 0;
    private int enemyMovementOffset;

    private int score = 0;
    
    private float time = 0;

    private boolean paused;

    GameScreen() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new StretchViewport(BOARD_WIDTH*TILE_SIZE, BOARD_HEIGHT*TILE_SIZE, camera);
        camera.update();

//        background = new Texture();
        batch = new SpriteBatch();

        playerTexture = new TextureRegion(new Texture("Prototype_Character.png"));
        player = new Character(playerTexture);

        enemies = new ArrayList<Enemies>();
        enemies.add(new PatrollingEnemies(2, 2, Direction.Up, 1, 10, 1, 10, new TextureRegion(new Texture("temp_ptero.png"))));


        gameboard = new Board();
    }

    /**
     *
     */
    public void show() {

    }

    private void input() {

        if(inputDisplacement == 0){
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                System.out.println("PAUSED");
                paused = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                if(player.direction('W', gameboard)){
                    inputDisplacement = INPUT_TIMEOUT;
                    playerMovementOffset = -TILE_SIZE;
                    playerMovingXDirection = false;
                }
                System.out.println("UP");
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                if(player.direction('D', gameboard)){
                    inputDisplacement = INPUT_TIMEOUT;
                    playerMovementOffset = -TILE_SIZE;
                    playerMovingXDirection = true;
                }
                System.out.println("RIGHT");
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                if(player.direction('A', gameboard)){
                    inputDisplacement = INPUT_TIMEOUT;
                    playerMovementOffset = TILE_SIZE;
                    playerMovingXDirection = true;
                }
                System.out.println("LEFT");
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.S)){
                if(player.direction('S', gameboard)){
                    inputDisplacement = INPUT_TIMEOUT;
                    playerMovementOffset = TILE_SIZE;
                    playerMovingXDirection = false;
                }
                System.out.println("DOWN");
            }
        } else {
            inputDisplacement--;
        }


    }

    /**
     * @param delta
     */
    public void render(float delta) {
        if (paused){
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                System.out.println("UNPAUSED");
                paused = false;
            }
        } else

            logic();
        time+= Gdx.graphics.getDeltaTime();

        System.out.println(time);
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

        gameboard.draw(batch, time, TILE_SIZE);
        renderPlayer();
        renderEnemies();


        batch.end();
    }

    /**
     * Draw the player to the game screen
     */
    private void renderPlayer() {
//        System.out.println(playerMovementOffset);
        player.draw(batch,TILE_SIZE, playerMovementOffset);

        if( Math.abs(playerMovementOffset) - TILE_SIZE/INPUT_TIMEOUT < 0){
            playerMovementOffset = 0;
        }
        else if(playerMovementOffset > 0){
            playerMovementOffset -= TILE_SIZE/INPUT_TIMEOUT;
        }
        else if(playerMovementOffset < 0){
            playerMovementOffset += TILE_SIZE/INPUT_TIMEOUT;

        }

    }


    // TODO: Complete this function
    // it will only draw the enemies in the array list
    private void renderEnemies(){
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).draw(batch, TILE_SIZE);
        }
    }

    /**
     * This function will handle the game logic
     * This includes collision, moving enemies, checking/applying rewards/punishments
     */
    private void logic() {
        input();

        moveEnemies();
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
                playerLoses();
                break;
            }
        }
    }

    private void checkIfExitingMaze() {
        int playerX = player.getX();
        int playerY = player.getY();
        int playerRewardCnt = player.getRewardsCollected();
        if((gameboard.getEnd().getXPosition() == playerX) && (gameboard.getEnd().getYPosition() == playerY) && (playerRewardCnt == gameboard.getTotalRegRewardCnt())) {
            playerWins();
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
            playerLoses();
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
    private void playerWins() {

    }

    // TODO: write code for if player loses
    private void playerLoses() {

    }


    /**
     * @param width
     * @param height
     */
    public void resize(int width, int height) {
//        viewport.update(width, height,true);
//        batch.setProjectionMatrix(camera.combined);
    }

    /**
     *
     */
    public void pause() {

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
