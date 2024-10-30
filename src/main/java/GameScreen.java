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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import entities.Character;
import org.lwjgl.system.windows.INPUT;

public class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;

    private SpriteBatch batch;

    private Texture background;
    private TextureRegion playerTexture;

    private Board gameboard;
    private Character player;

    private final int BOARD_WIDTH = 40*10;
    private final int BOARD_HEIGHT = 60*10;

    GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(BOARD_WIDTH, BOARD_HEIGHT, camera);

//        background = new Texture();
        batch = new SpriteBatch();

        playerTexture = new TextureRegion(new Texture("Prototype_Character.png"));
        player = new Character(playerTexture);

        gameboard = new Board();
    }

    /**
     *
     */
    public void show() {

    }

    private void input() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            player.direction('W', gameboard);
            System.out.println("UP");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.direction('D', gameboard);
            System.out.println("RIGHT");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.direction('A', gameboard);
            System.out.println("LEFT");
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            player.direction('S', gameboard);
            System.out.println("DOWN");
        }
    }

    /**
     * @param delta
     */
    public void render(float delta) {
        input();
        batch.begin();

        player.draw(batch);

        batch.end();
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
