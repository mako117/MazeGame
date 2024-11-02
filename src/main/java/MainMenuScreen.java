import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {
	final MazeGame game;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private Viewport viewport;
	private Camera camera;

	public MainMenuScreen(final MazeGame game) {
		this.game = game;
	}
	
	public void render(float delta) {
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new StretchViewport(2000, 3000, camera);
        camera.update();
		
		//font.setUseIntegerPositions(false);
		//font.getData().setScale(viewport.getWorldHeight()/Gdx.graphics.getHeight());
		
		//System.out.println("111 " + viewport.getWorldHeight() + " 2222 " + Gdx.graphics.getHeight());
		ScreenUtils.clear(Color.BLACK);
		
		//viewport.apply();
		batch.setProjectionMatrix(viewport.getCamera().combined);

		batch.begin();
		//draw text. Remember that x and y are in meters
		font.draw(batch, "Hello", camera.position.x, camera.position.y);
		font.draw(batch, "Hello", camera.position.x, camera.position.y + 10);
		//font.draw(batch, "Welcome to Drop!!! ", -0.5f,0);
		//font.draw(batch, "Tap anywhere to begin!", -0.5f,-0.5f);
		batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		font.dispose();
		
	}
}
