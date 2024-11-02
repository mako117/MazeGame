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

public class MainMenuScreen implements Screen {
	final MazeGame game;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture backgroundTexture;
	
	private Skin skin;
	private Button startbutton;
	private Button exitbutton;
	private Stage stage;
	
	private int change_x = 0;
	private int change_y = 0;
	private int middle_x = Gdx.graphics.getWidth() / 2;
	private int middle_y = Gdx.graphics.getHeight() / 2;

	public MainMenuScreen(final MazeGame game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
		backgroundTexture = new Texture("Space Background.png");
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
		change_x = -120;
		change_y = -100;
		
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		startbutton = new TextButton("Start", skin, "small");
		startbutton.setSize(Gdx.graphics.getWidth() / 12 * 2, Gdx.graphics.getHeight() / 12);
		startbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		startbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new GameScreen(game));
						dispose();
					}
			
		});
		stage.addActor(startbutton);
		
		change_x = -70;
		change_y = -180;
		
		exitbutton = new TextButton("Exit", skin, "small");
		exitbutton.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getHeight() / 12);
		exitbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		exitbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						Gdx.app.exit();
						dispose();
					}
			
		});
		stage.addActor(exitbutton);
	}
	
	public void render(float delta) {
		
		ScreenUtils.clear(Color.BLACK);

		change_x = -100;
		change_y = 150;
		
		batch.begin();
		//draw text. Remember that x and y are in meters
		batch.draw(backgroundTexture, 0, 0);
		font.getData().setScale(2, 2);
		font.draw(batch, "Game", middle_x + change_x, middle_y + change_y);
		batch.end();
		
		//draw the button
		stage.act();
		stage.draw();
		
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
		backgroundTexture.dispose();
		skin.dispose();
		stage.dispose();
	}
}
