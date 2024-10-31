import board.Board;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import directions.Direction;
import entities.Character;
import entities.enemy.*;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.windows.INPUT;

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


    private final int INPUT_TIMEOUT = 20;
    private int inputDisplacement = 0;

    GameScreen() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new StretchViewport(BOARD_WIDTH*TILE_SIZE, BOARD_HEIGHT*TILE_SIZE, camera);
        camera.update();

//        background = new Texture();
        batch = new SpriteBatch();

        playerTexture = new TextureRegion(new Texture("Prototype_Character.png"));
        player = new Character(playerTexture);

        enemies = new ArrayList<Enemies>();
        enemies.add(new PatrollingEnemies(0, 0, Direction.North, 10, 10, new TextureRegion()));


        gameboard = new Board();
    }

    /**
     *
     */
    public void show() {

    }

    private void input() {

        if(inputDisplacement == 0){
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                player.direction('W', gameboard);
                System.out.println("UP");
                inputDisplacement = INPUT_TIMEOUT;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                player.direction('D', gameboard);
                System.out.println("RIGHT");
                inputDisplacement = INPUT_TIMEOUT;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                player.direction('A', gameboard);
                System.out.println("LEFT");
                inputDisplacement = INPUT_TIMEOUT;
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.S)){
                player.direction('S', gameboard);
                System.out.println("DOWN");
                inputDisplacement = INPUT_TIMEOUT;
            }
        } else {
            inputDisplacement--;
        }


    }

    /**
     * @param delta
     */
    public void render(float delta) {
        logic();

        // update camera position
        camera.position.x = player.getX()*TILE_SIZE + TILE_SIZE/2;
        camera.position.y = player.getY()*TILE_SIZE + TILE_SIZE/2;
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        gameboard.draw(batch);
        player.draw(batch, TILE_SIZE);



        batch.end();
    }


    /**
     * This function will handle the game logic
     * This includes collision, moving enemies, checking/applying rewards/punishments
     */
    private void logic() {
        input();

        moveEnemies();
        checkPlayerCollision();

        checkReward();
        checkPunishment();
    }


    // TODO: Complete this function
    // it will only draw the enemies in the array list
    private void renderEnemies(){

    }

    // TODO: add movement methods of the enemies in the list
    private void moveEnemies(){

    }

    // TODO: check if player coordinates are the same with any enemies and act accordingly
    private void checkPlayerCollision(){

    }

    /**
     * Check if the player as reached a reward. <br>
     * If there is a reward, the reward will be collected and the score added to player score. <br>
     * The reward is removed from board after. <br>
     */
    public void checkReward() {
        int playerX = player.getX();
        int playerY = player.getY();
        int score = gameboard.rewardCollect(playerX, playerY);
        player.scorechange(score);
    }

    /**
     * Check if the player reached a punishment. <br>
     * If there is a punishment, the punishment will be given to the player. <br>
     * The punishment is removed after.<br>
     */
    public void checkPunishment() {
        int playerX = player.getX();
        int playerY = player.getY();
        int score = gameboard.punishmentCollect(playerX, playerY);
        player.minus_score(score);
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


}
