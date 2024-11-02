import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
	
	private Skin skin;
	private Button tryagainbutton;
	private Button playagainbutton;
	private Button winexitbutton;
	private Button lossexitbutton;
	
	private Stage stage1;
	private Stage stage2;
	
	public EndScreen(MazeGame game, int score, float time, boolean condition) {
		this.game = game;
		this.score = score;
		this.time = time;
		this.condition = condition;
		
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
		backgroundTexture = new Texture("Space Background.png");
		
		stage1 = new Stage(new ScreenViewport());
		stage2 = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage1);
		Gdx.input.setInputProcessor(stage2);
		
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		
		playagainbutton = new TextButton("Play Again", skin, "small");
		playagainbutton.setSize(Gdx.graphics.getWidth() / 12 * 2, Gdx.graphics.getHeight() / 12);
		playagainbutton.setPosition(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 - 120);
		
		// listener for touch button
		playagainbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new GameScreen(game));
						dispose();
					}
			
		});
		stage1.addActor(playagainbutton);
		
		tryagainbutton = new TextButton("Try Again", skin, "small");
		tryagainbutton.setSize(Gdx.graphics.getWidth() / 12 * 2, Gdx.graphics.getHeight() / 12);
		tryagainbutton.setPosition(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 - 120);
		
		// listener for touch button
		tryagainbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new GameScreen(game));
						dispose();
					}
			
		});
		stage2.addActor(tryagainbutton);
		
		winexitbutton = new TextButton("Exit", skin, "small");
		winexitbutton.setSize(Gdx.graphics.getWidth() / 12 * 2, Gdx.graphics.getHeight() / 12);
		winexitbutton.setPosition(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 - 200);
		
		// listener for touch button
		winexitbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new MainMenuScreen(game));
						dispose();
					}
			
		});
		stage1.addActor(winexitbutton);
		
		lossexitbutton = new TextButton("Exit", skin, "small");
		lossexitbutton.setSize(Gdx.graphics.getWidth() / 12 * 2, Gdx.graphics.getHeight() / 12);
		lossexitbutton.setPosition(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 - 200);
		
		// listener for touch button
		lossexitbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new MainMenuScreen(game));
						dispose();
					}
			
		});
		stage2.addActor(lossexitbutton);
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
			font.draw(batch, "You Win", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 + 240);
		}
		else {
			font.getData().setScale(2, 2);
			font.draw(batch, "You lose", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 + 240);
		}
		
		font.getData().setScale(1, 1);
		font.draw(batch, String.format("%s%.1f", "Time: ", time), Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 80);
		font.getData().setScale(1, 1);
		font.draw(batch, "score: " + score, Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 20);
		
		batch.end();
		
		if(condition) {
			stage1.act();
			stage1.draw();
		}
		else {
			stage2.act();
			stage2.draw();
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
		backgroundTexture.dispose();
		skin.dispose();
		stage1.dispose();
		stage2.dispose();
	}

}
