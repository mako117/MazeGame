package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * The main menu of the game.
 */
public class MainMenuScreen extends ScreenAdapter {
    final screens.MazeGame game;
    private Stage stage;

    private Button startbutton;
    private Button exitbutton;
    private Button helpbutton;

    private boolean helpPage = false;

    private int change_x = 0;
    private int change_y = 0;
    private int middle_x = Gdx.graphics.getWidth() / 2;
    private int middle_y = Gdx.graphics.getHeight() / 2;

    private Music music;

    /**
     * MainMenuScreen constructor that passes in the current MazeGame object.
     * @param game  The current MazeGame object.
     */
    public MainMenuScreen(final MazeGame game) {
        System.out.println("Create new main menu");
        this.game = game;

        try{
            music = Gdx.audio.newMusic(Gdx.files.internal("TitleMusic.mp3"));
            music.setLooping(true);
            music.play();
        } catch (NullPointerException e){
            System.out.println("Music is null.");
        }

        createButtons();

        try {
            stage = new Stage(new ScreenViewport());
            stage.addActor(startbutton);
            stage.addActor(helpbutton);
            stage.addActor(exitbutton);
            Gdx.input.setInputProcessor(stage);
        } catch (Exception e){
            System.out.println("Cannot set main menu stage");
            Gdx.input.setInputProcessor(stage);
        }
    }

    /**
     * Creates buttons and listeners.
     */
    private void createButtons(){
        //start to set the button
        try {
            change_x = -150;
            change_y = -60;

            startbutton = new TextButton("Start", game.skin);
            startbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
            startbutton.setPosition(middle_x + change_x, middle_y + change_y);

            change_x = -100;
            change_y = -220;

            exitbutton = new TextButton("Exit", game.skin);
            exitbutton.setSize(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 10);
            exitbutton.setPosition(middle_x + change_x, middle_y + change_y);

            change_x = -150;
            change_y = -140;

            helpbutton = new TextButton("Help", game.skin);
            helpbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
            helpbutton.setPosition(middle_x + change_x, middle_y + change_y);
        } catch (NullPointerException e) {
            System.out.println("Main menu button akin asset null");
            startbutton = new Button();
            exitbutton = new Button();
            helpbutton = new Button();
        }


        // listener for touch button
        startbutton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                music.stop();
                dispose();
            }
        });
        // listener for touch button
        exitbutton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                music.stop();
                game.exitGame();
            }

        });
        // listener for touch button
        helpbutton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                helpPage = true;
            }
        });
    }

    /**
     * Render the objects to the window.
     * @param delta TODO: Delta.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        if(helpPage){
            game.setScreen(new HelpScreen(game,this));
            helpPage = false;
        } else {
            Gdx.input.setInputProcessor(stage);
            stage.addActor(startbutton);
            stage.addActor(helpbutton);
            stage.addActor(exitbutton);

            game.batch.begin();

            change_x = -260;
            change_y = 150;

            game.batch.draw(game.backgroundTexture, 0, 0);
            game.font.getData().setScale(2, 2);
            game.font.draw(game.batch, "Jurassic Meteor", middle_x + change_x, middle_y + change_y);

            game.batch.end();

            stage.act();
            stage.draw();
        }
    }

    /**
     * This function is used to dispose the screen elements.
     */
    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
    }
}
