import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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
	
	private int change_x = 0;
	private int change_y = 0;
	private int middle_x = Gdx.graphics.getWidth() / 2;
	private int middle_y = Gdx.graphics.getHeight() / 2;
	
	
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
		
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		
		change_x = -140;
		change_y = -120;
		
		playagainbutton = new TextButton("Play Again", skin, "small");
		playagainbutton.setSize(Gdx.graphics.getWidth() / 12 * 2, Gdx.graphics.getHeight() / 12);
		playagainbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
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
		tryagainbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		tryagainbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new GameScreen(game));
						dispose();
					}
			
		});
		stage2.addActor(tryagainbutton);
		
		change_x = -85;
		change_y = -200;
		
		winexitbutton = new TextButton("Exit", skin, "small");
		winexitbutton.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() / 12);
		winexitbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		winexitbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new MainMenuScreen(game));
						dispose();
					}
			
		});
		stage1.addActor(winexitbutton);
		
		lossexitbutton = new TextButton("Exit", skin, "small");
		lossexitbutton.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() / 12);
		lossexitbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
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
		change_x = -160;
		change_y = 240;
		if(condition) {
			font.getData().setScale(2, 2);
			font.draw(batch, "You Win", middle_x + change_x, middle_y + change_y);
		}
		else {
			font.getData().setScale(2, 2);
			font.draw(batch, "You lose", middle_x + change_x, middle_y + change_y);
		}
		
		change_x = -100;
		change_y = 80;
		
		font.getData().setScale(1, 1);
		font.draw(batch, String.format("%s%.1f", "Time: ", time), middle_x + change_x, middle_y + change_y);
		
		change_x = -100;
		change_y = 20;
		
		font.getData().setScale(1, 1);
		font.draw(batch, "Score: " + score, middle_x + change_x, middle_y + change_y);
		
		batch.end();
		
		if(condition) {
			Gdx.input.setInputProcessor(stage1);
			stage1.act();
			stage1.draw();
		}
		else {
			Gdx.input.setInputProcessor(stage2);
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
