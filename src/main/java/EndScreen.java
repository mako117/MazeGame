import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class EndScreen implements Screen{
	
	int score = 0;
	float time = 0;
	MazeGame game;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private FitViewport viewport;
	
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
		viewport = new FitViewport(8,5);
		
		font.setUseIntegerPositions(false);
		font.getData().setScale(viewport.getWorldHeight()/Gdx.graphics.getHeight());
		
		//System.out.println("111 " + viewport.getWorldHeight() + " 2222 " + Gdx.graphics.getHeight());
		ScreenUtils.clear(Color.BLACK);
		
		//viewport.apply();
		batch.setProjectionMatrix(viewport.getCamera().combined);

		batch.begin();
		//draw text. Remember that x and y are in meters
		
		
		
		if(score > 0) {
			font.draw(batch, "Game Win, End Screen ", -0.5f,0);
			font.draw(batch, "Tap anywhere to try again begin!", -0.5f,-0.5f);
			batch.end();
			if (Gdx.input.isTouched()) {
				game.setScreen(new GameScreen(game));
				dispose();
			}
		}
		else {
			font.draw(batch, "Game Loss, End Screen ", -0.5f,0);
			font.draw(batch, "Tap anywhere to try again begin!", -0.5f,-0.5f);
			batch.end();
			if (Gdx.input.isTouched()) {
				game.setScreen(new GameScreen(game));
				dispose();
			}
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
		
	}

}
