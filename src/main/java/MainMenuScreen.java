import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
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

public class MainMenuScreen extends ScreenAdapter {
	final MazeGame game;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture backgroundTexture;
	
	private Skin skin;
	private Button startbutton;
	private Button exitbutton;
	private Button helpbutton;
	private Button helppage1button;
	private Button helppage2button;
	private Button helpbackbutton;
	private Stage stage1;
	private Stage stage2;
	private Stage stage3;
	
	private int change_x = 0;
	private int change_y = 0;
	private int middle_x = Gdx.graphics.getWidth() / 2;
	private int middle_y = Gdx.graphics.getHeight() / 2;
	
	private boolean helppage = false;
	private boolean helppage1 = false;
	private boolean helppage2 = false;

	public MainMenuScreen(final MazeGame game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
		backgroundTexture = new Texture("Space Background.png");
		
		stage1 = new Stage(new ScreenViewport());
		stage2 = new Stage(new ScreenViewport());
		stage3 = new Stage(new ScreenViewport());
		
		
		change_x = -150;
		change_y = -60;
		
		skin = new Skin(Gdx.files.internal("skin-soldier/star-soldier-ui.json"));
		startbutton = new TextButton("Start", skin);
		startbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		startbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		startbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(new GameScreen(game));
						dispose();
					}
			
		});
		stage1.addActor(startbutton);
		
		change_x = -100;
		change_y = -220;
		
		exitbutton = new TextButton("Exit", skin);
		exitbutton.setSize(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 10);
		exitbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		exitbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						game.dispose();
						Gdx.app.exit();
					}
			
		});
		stage1.addActor(exitbutton);
		
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
		stage1.addActor(helpbutton);
		
		change_x = -450;
		change_y = -300;
		
		helppage1button = new TextButton("Page 1", skin);
		helppage1button.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		helppage1button.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		helppage1button.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						helppage1 = true;
						helppage2 = false;
					}
			
		});
		stage3.addActor(helppage1button);
		
		change_x = 300;
		change_y = -300;
		
		helppage2button = new TextButton("Page 2", skin);
		helppage2button.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		helppage2button.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		helppage2button.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						helppage1 = false;
						helppage2 = true;
					}
			
		});
		stage2.addActor(helppage2button);
		
		change_x = -150;
		change_y = -300;
		
		helpbackbutton = new TextButton("Back", skin);
		helpbackbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		helpbackbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		helpbackbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						helppage = false;
						helppage2 = false;
					}
			
		});
		stage3.addActor(helpbackbutton);
	}

	public void render(float delta) {
		
		ScreenUtils.clear(Color.BLACK);
		
		if(helppage) {
			if(helppage1) {
				Gdx.input.setInputProcessor(stage2);
				batch.begin();
				
				change_x = -180;
				change_y = 300;
				//draw text. Remember that x and y are in meters
				batch.draw(backgroundTexture, 0, 0);
				font.getData().setScale(2, 2);
				font.draw(batch, "How to play", middle_x + change_x, middle_y + change_y);
				
				change_x = -(middle_x) + 50;
				change_y = 200;
				font.getData().setScale(1,1);;
				font.draw(batch, "Movement: Use 'W', 'A', 'S', 'D' to move in the game page 1", middle_x + change_x, middle_y + change_y);
				
				change_y = 100;
				font.draw(batch, "Stop: Use 'Ese' can stop in the game", middle_x + change_x, middle_y + change_y);
				
				change_y = 0;
				font.draw(batch, "Win the Game: Collect all the rewards show on the board and go to the end block", middle_x + change_x, middle_y + change_y);
				
				change_y = -100;
				font.draw(batch, "Loss the Game: Monster catch you or point lower than 0", middle_x + change_x, middle_y + change_y);
				
				batch.end();
				
				stage2.act();
				stage2.draw();
			}
			else if(helppage2) {
				Gdx.input.setInputProcessor(stage3);
				batch.begin();
				
				change_x = -180;
				change_y = 300;
				//draw text. Remember that x and y are in meters
				batch.draw(backgroundTexture, 0, 0);
				font.getData().setScale(2, 2);
				font.draw(batch, "How to play", middle_x + change_x, middle_y + change_y);
				/*
				change_x = -(middle_x) + 50;
				change_y = 200;
				font.getData().setScale(1,1);;
				font.draw(batch, "Movement: Use 'W', 'A', 'S', 'D' to move in the game page 2", middle_x + change_x, middle_y + change_y);
				
				change_y = 100;
				font.draw(batch, "Stop: Use 'Ese' can stop in the game", middle_x + change_x, middle_y + change_y);
				
				change_y = 0;
				font.draw(batch, "Win the Game: Collect all the rewards show on the board and go to the end block", middle_x + change_x, middle_y + change_y);
				
				change_y = -100;
				font.draw(batch, "Loss the Game: Monster catch you or point lower than 0", middle_x + change_x, middle_y + change_y);
				*/
				batch.end();
				
				stage3.act();
				stage3.draw();
			}
		}
		else {
			Gdx.input.setInputProcessor(stage1);
			
			batch.begin();
			
			change_x = -100;
			change_y = 150;
			//draw text. Remember that x and y are in meters
			batch.draw(backgroundTexture, 0, 0);
			font.getData().setScale(2, 2);
			font.draw(batch, "Game", middle_x + change_x, middle_y + change_y);
			
			batch.end();
			
			//draw the button
			stage1.act();
			stage1.draw();
			
		}
	}

	@Override
	public void show() {
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
