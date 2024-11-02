import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EndScreen implements Screen{
	
	private int score;
	private float time;
	private boolean condition;
	private MazeGame game;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture backgroundTexture;
	
	public EndScreen(MazeGame game, int score, float time, boolean condition) {
		this.game = game;
		this.score = score;
		this.time = time;
		this.condition = condition;
		backgroundTexture = new Texture("Space Background.png");
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(Color.BLACK);
		
		batch.begin();
		batch.draw(backgroundTexture, 0, 0);
		if(condition) {
			font.getData().setScale(2, 2);
			font.draw(batch, "You Win", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 150);
		}
		else {
			font.getData().setScale(2, 2);
			font.draw(batch, "You lose", Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 150);
		}
		
		font.getData().setScale(1, 1);
		font.draw(batch, String.format("%s%.1f", "Time: ", time), Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 10);
		font.getData().setScale(1, 1);
		font.draw(batch, "score: " + score, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 - 50);
		
		if(condition) {
			font.getData().setScale(1.2f, 1.2f);
			font.draw(batch, "Click to start again", Gdx.graphics.getWidth() / 2 - 180, Gdx.graphics.getHeight() / 2 - 120);
		}
		else {
			font.getData().setScale(1.2f, 1.2f);
			font.draw(batch, "Click to try again", Gdx.graphics.getWidth() / 2 - 180, Gdx.graphics.getHeight() / 2 - 120);
		}
		batch.end();
		
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
