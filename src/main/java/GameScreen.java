import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import entities.Character;

public class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;

    private SpriteBatch batch;

    private Texture background;
    private TextureRegion[] playerTexture;


    private Character player;

    private final int BOARD_WIDTH = 40*10;
    private final int BOARD_HEIGHT = 60*10;

    GameScreen() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(BOARD_WIDTH, BOARD_HEIGHT, camera);

        background = new Texture("Prototype_Character.png");
        batch = new SpriteBatch();


        playerTexture = new TextureRegion[2];

        Character player = new Character(playerTexture);
    }

    /**
     *
     */
    public void show() {

    }

    /**
     * @param delta
     */
    public void render(float delta) {
        batch.begin();

        batch.draw(background, 0, 0);

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
