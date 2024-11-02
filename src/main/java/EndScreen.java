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

public class EndScreen implements Screen{
	
	int score = 0;
	float time = 0;
	MazeGame game;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private Viewport viewport;
	private Camera camera;
	
	public EndScreen(MazeGame game, int score, float time) {
		this.game = game;
		this.score = score;
		this.time = time;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new StretchViewport(2000, 3000, camera);
        camera.update();
		
		//System.out.println("111 " + viewport.getWorldHeight() + " 2222 " + Gdx.graphics.getHeight());
		ScreenUtils.clear(Color.BLACK);
		
		//viewport.apply();
		batch.setProjectionMatrix(viewport.getCamera().combined);
		
		if(score > 0) {
			batch.begin();
			font.draw(batch, "You Win", camera.position.x, camera.position.y);
			font.draw(batch, "Click to play again", camera.position.x, camera.position.y - 100);
			batch.end();
		}
		else {
			batch.begin();
			font.draw(batch, "You lose", camera.position.x, camera.position.y);
			font.draw(batch, "Click to try again", camera.position.x, camera.position.y - 100);
			batch.end();
		}
		
		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		
		
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
