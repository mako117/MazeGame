import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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
 * This class create the main menu for the game.
 */
public class MainMenuScreen extends ScreenAdapter {
	final MazeGame game;
	
	private SpriteBatch batch;
	private BitmapFont font;
	private Texture backgroundTexture;
	
	private TextureRegion RrewardTex;
	private TextureRegion RpunishmentTex;
	private TextureRegion BrewardTex;
	private TextureRegion BpunishmentTex;
	private TextureRegion movingEnemyTex;
	private TextureRegion patrollingEnemeyTex;
	private TextureRegion endblockTex;
	
	private Skin skin;
	private Button startbutton;
	private Button exitbutton;
	private Button helpbutton;
	private Button helppage1button;
	private Button helppage2button;
	private Button helppage3button;
	private Button helpbackbutton;
	private Stage stage1;
	private Stage stage2;
	private Stage stage3;
	private Stage stage4;
	
	private int change_x = 0;
	private int change_y = 0;
	private int middle_x = Gdx.graphics.getWidth() / 2;
	private int middle_y = Gdx.graphics.getHeight() / 2;
	
	private boolean helppage = false;
	private boolean helppage1 = false;
	private boolean helppage2 = false;
	private boolean helppage3 = false;
	
	/**
	 * This method set the information for button, texture, skin for button, stage for the main menu screen.
	 * @param game
	 */
	public MainMenuScreen(final MazeGame game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("comic_sans.fnt"));
		backgroundTexture = new Texture("Space Background.png");
		
		RrewardTex = new TextureRegion(new Texture("bomb.png"));
		BrewardTex = new TextureRegion(new Texture("dinosaur_egg.png"));
		RpunishmentTex = new TextureRegion(new Texture("baby_dinosaur.png"));
		BpunishmentTex = new TextureRegion(new Texture("alien.png"));
		endblockTex = new TextureRegion(new Texture("green.png"));
        movingEnemyTex = new TextureRegion(new Texture("DinoSprite.png"),4,1,17,17);
        patrollingEnemeyTex = new TextureRegion(new Texture("ptero.png"), 0,0,31,16);
        
		stage1 = new Stage(new ScreenViewport());
		stage2 = new Stage(new ScreenViewport());
		stage3 = new Stage(new ScreenViewport());
		stage4 = new Stage(new ScreenViewport());
		
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
		
		helppage1button = new TextButton("Page 1", skin);
		helppage1button.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		// listener for touch button
		helppage1button.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						helppage1 = true;
						helppage2 = false;
					}
			
		});
		
		helppage2button = new TextButton("Page 2", skin);
		helppage2button.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		// listener for touch button
		helppage2button.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						helppage1 = false;
						helppage2 = true;
						helppage3 = false;
					}
			
		});
		
		helppage3button = new TextButton("Page 2", skin);
		helppage3button.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		// listener for touch button
		helppage3button.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						helppage2 = false;
						helppage3 = true;
					}
			
		});
		
		change_x = -140;
		change_y = -300;
		
		helpbackbutton = new TextButton("Back", skin);
		helpbackbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
		helpbackbutton.setPosition(middle_x + change_x, middle_y + change_y);
		
		// listener for touch button
		helpbackbutton.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						helppage = false;
						helppage1 = false;
						helppage2 = false;
						helppage3 = false;
					}
			
		});
	}

	/**
	 * This method show what main menu screen look like, print all the things for the main menu need.
	 */
	public void render(float delta) {
		
		ScreenUtils.clear(Color.BLACK);
		
		if(helppage) {
			
			batch.begin();
			
			change_x = -180;
			change_y = 300;
			
			batch.draw(backgroundTexture, 0, 0);
			font.getData().setScale(2, 2);
			font.draw(batch, "How to play", middle_x + change_x, middle_y + change_y);
			
			batch.end();
			
			if(helppage1) {
				Gdx.input.setInputProcessor(stage2);
				change_y = -300;
				
				stage2.addActor(helpbackbutton);
				change_x = 300;
				helppage2button.setPosition(middle_x + change_x, middle_y + change_y);
				stage2.addActor(helppage2button);
				
				batch.begin();
				
				change_x = -(middle_x) + 30;
				change_y = 200;
				font.getData().setScale(1,1);;
				font.draw(batch, "Movement" + ": Use 'W', 'A', 'S', 'D' to move in the game", middle_x + change_x, middle_y + change_y);
				
				change_y = 100;
				font.draw(batch, "Stop" + ": Use 'Ese' can stop in the game", middle_x + change_x, middle_y + change_y);
				
				change_y = 0;
				font.draw(batch, "Win the Game" + ": Collect all the rewards show on ready board \nand go to the end block win the game", middle_x + change_x, middle_y + change_y);
				
				change_y = -150;
				font.draw(batch, "Loss the Game" + ": Monster catch you or point lower than 0", middle_x + change_x, middle_y + change_y);
				
				batch.end();
				
				stage2.act();
				stage2.draw();
			}
			else if(helppage2) {
				Gdx.input.setInputProcessor(stage3);
				change_y = -300;
				
				stage3.addActor(helpbackbutton);
				change_x = -560;
				helppage1button.setPosition(middle_x + change_x, middle_y + change_y);
				stage3.addActor(helppage1button);
				change_x = 300;
				helppage3button.setPosition(middle_x + change_x, middle_y + change_y);
				stage3.addActor(helppage3button);
				
				batch.begin();
				
				change_x = -(middle_x) + 30;
				change_y = 120;
				
				batch.draw(RrewardTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
				
				change_y = 20;
				
				batch.draw(BrewardTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
				
				change_y = -80;
				
				batch.draw(RpunishmentTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
				
				change_y = -180;
				
				batch.draw(BpunishmentTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
				
				change_x = -(middle_x) + 210;
				change_y = 190;
				
				font.getData().setScale(1, 1);
				font.draw(batch, ": Reward \n Collect can get 10 point", middle_x + change_x, middle_y + change_y);
				
				change_y = 90;
				
				font.draw(batch, ": Bonus reward \n Collect can get 10 point, appear on mape in fix time", middle_x + change_x, middle_y + change_y);
				
				change_y = -10;
				
				font.draw(batch, ": Punishment \n Collect will loss 10 point", middle_x + change_x, middle_y + change_y);
				
				change_y = -110;
				
				font.draw(batch, ": Bonus Punishment \n Collect will loss 10 point, appear on map in fix time.", middle_x + change_x, middle_y + change_y);
				
				batch.end();
				
				stage3.act();
				stage3.draw();
			}
			else if(helppage3) {
				Gdx.input.setInputProcessor(stage4);
				change_y = -300;
				
				stage4.addActor(helpbackbutton);
				change_x = -560;
				helppage2button.setPosition(middle_x + change_x, middle_y + change_y);
				stage4.addActor(helppage2button);
				
				batch.begin();
				
				change_x = -(middle_x) + 30;
				change_y = 150;
				
				batch.draw(movingEnemyTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
				
				change_y = 0;
				
				batch.draw(patrollingEnemeyTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
				
				change_y = -150;
				
				batch.draw(endblockTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
				
				change_x = -(middle_x) + 210;
				change_y = 200;
				
				font.getData().setScale(1,1);
				font.draw(batch, ": This is the mighty T.rex.\n  He will hunt you.", middle_x + change_x, middle_y + change_y);
				
				change_y = 50;

                font.draw(batch, ": The Pterodactyl is mighty territorial\n  Avoid his set path.", middle_x + change_x, middle_y + change_y);
                
                change_y = -100;
                
                font.draw(batch, ": Come to this block to win when collect all the rewards.", middle_x + change_x, middle_y + change_y);
		
				batch.end();
				
				stage4.act();
				stage4.draw();
			}
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
		stage2.dispose();
		stage3.dispose();
		stage4.dispose();
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
