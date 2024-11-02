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

	public MainMenuScreen(final MazeGame game) {
		this.game = game;
	}
	
	public void render(float delta) {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		ScreenUtils.clear(Color.BLACK);
		
		

		batch.begin();
		//draw text. Remember that x and y are in meters
		font.getData().setScale(2, 2);
		font.draw(batch, "Game", Gdx.graphics.getWidth() / 2 - 20, Gdx.graphics.getHeight() / 2 + 40);
		font.getData().setScale(3, 3);
		font.draw(batch, "Click to Start", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 40);
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
