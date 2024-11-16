import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * This class create the main menu screen for the game.
 */
public class MainMenuScreen extends ScreenAdapter {
	final MazeGame game;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture backgroundTexture;

	private Skin skin;
	private Button startbutton;
	private Button exitbutton;
	private Button helpbutton;
	private Stage stage1;


	private int change_x = 0;
	private int change_y = 0;
	private int middle_x = Gdx.graphics.getWidth() / 2;
	private int middle_y = Gdx.graphics.getHeight() / 2;
	
	private boolean helppage = false;
	private boolean helppage1 = false;
	private boolean helppage2 = false;
	private boolean helppage3 = false;
	Music music;


	/**
	 * This method set the information for button, texture, skin for button, stage for the main menu screen.
	 * @param game
	 */
	public MainMenuScreen(final MazeGame game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
		backgroundTexture = new Texture("Space Background.png");

		music = Gdx.audio.newMusic(Gdx.files.internal("TitleMusic.mp3"));
		music.setLooping(true);
		music.play();
        
		stage1 = new Stage(new ScreenViewport());
		
		//start to set the button
		skin = new Skin(Gdx.files.internal("skin-soldier/star-soldier-ui.json"));
		
		change_x = -150;
		change_y = -60;
		
		startbutton = new TextButton("Start", skin);
		startbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		startbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		startbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new GameScreen(game));
						music.stop();
						dispose();
					}
			
		});
		
		change_x = -100;
		change_y = -220;
		
		exitbutton = new TextButton("Exit", skin);
		exitbutton.setSize(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 10);
		exitbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		exitbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						music.stop();
						game.dispose();
						Gdx.app.exit();
					}
			
		});
		
		change_x = -150;
		change_y = -140;
		
		helpbutton = new TextButton("Help", skin);
		helpbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		helpbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		helpbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						helppage = true;
						helppage1 = true;
					}
			
		});
	}

	/**
	 * This method show what main menu screen look like, print all the things for the main menu screen need.
	 */
	public void render(float delta) {
		
		ScreenUtils.clear(Color.BLACK);
		
		if(helppage) {
			game.setScreen(new HelpScreen(game,this));
			helppage = false;
		}
		else {
			Gdx.input.setInputProcessor(stage1);
			stage1.addActor(startbutton);
			stage1.addActor(helpbutton);
			stage1.addActor(exitbutton);
			
			
			batch.begin();
			
			change_x = -260;
			change_y = 150;
			
			batch.draw(backgroundTexture, 0, 0);
			font.getData().setScale(2, 2);
			font.draw(batch, "Jurassic Meteor", middle_x + change_x, middle_y + change_y);
			
			batch.end();
			
			stage1.act();
			stage1.draw();
			
		}
	}
	
	/**
	 * This method dispose the variable that need to dispose in the MainMenuScreen
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		font.dispose();
		backgroundTexture.dispose();
		skin.dispose();
		stage1.dispose();
		music.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
}
