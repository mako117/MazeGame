package screens;

import javax.swing.event.ChangeEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * This class contains the end screen for the game.
 */
public class EndScreen extends ScreenAdapter {
    final MazeGame game;

    private int score;
    private float time;
    private boolean condition;

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

    /**
     * This method sets the button, its skin, the score, time, and stage for the end screen., while also passing in the current MazeGame object
     * @param game  The current MazeGame object.
     * @param score Input for <code>score</code>.
     * @param time  Input for <code>time</code>.
     * @param condition Input for <code>condition</code>.
     */
    public EndScreen(final MazeGame game, int score, float time, boolean condition){
        this.game = game;
		this.score = score;
		this.time = time;
		this.condition = condition;

        try {
            stage1 = new Stage(new ScreenViewport());
            stage2 = new Stage(new ScreenViewport());
            createButtons();
        } catch (Exception e){
            System.out.println("Cannot set end screen stage");
        }
    }

    /**
     * Create the buttons in the end screen.
     */
    private void createButtons(){

        change_x = -150;
        change_y = -120;

        playagainbutton = new TextButton("Play Again", game.skin);
        playagainbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
        playagainbutton.setPosition(middle_x + change_x, middle_y + change_y);

        // listener for touch button
        playagainbutton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                dispose();
            }

        });
        stage1.addActor(playagainbutton);

        tryagainbutton = new TextButton("Try Again", game.skin);
        tryagainbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
        tryagainbutton.setPosition(middle_x + change_x, middle_y + change_y);

        // listener for touch button
        tryagainbutton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                dispose();
            }

        });
        stage2.addActor(tryagainbutton);

        change_x = -100;
        change_y = -220;

        winexitbutton = new TextButton("Exit", game.skin);
        winexitbutton.setSize(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 10);
        winexitbutton.setPosition(middle_x + change_x, middle_y + change_y);

        // listener for touch button
        winexitbutton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }

        });
        stage1.addActor(winexitbutton);

        lossexitbutton = new TextButton("Exit", game.skin);
        lossexitbutton.setSize(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 10);
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

    /**
     * This method renders the end screen.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.batch.begin();
        if(condition) {
            game.batch.draw(game.playerWinsBackgroundTexture, 0, 0,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else {
            game.batch.draw(game.playerLosesBackgroundTexture, 0, 0,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        change_x = -140;
        change_y = 240;
        if(condition) {
            game.font.getData().setScale(2, 2);
            game.font.draw(game.batch, "You Win", middle_x + change_x, middle_y + change_y);
        }
        else {
            game.font.getData().setScale(2, 2);
            game.font.draw(game.batch, "You lose", middle_x + change_x, middle_y + change_y);
        }

        change_x = -90;
        change_y = 80;

        game.font.getData().setScale(1, 1);
        game.font.draw(game.batch, String.format("%s%.1f", "Time: ", time), middle_x + change_x, middle_y + change_y);

        change_y = 20;

        game.font.getData().setScale(1, 1);
        game.font.draw(game.batch, "Score: " + score, middle_x + change_x, middle_y + change_y);

        game.batch.end();

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

    /**
     * This method disposes of some of EndScreen's variables.
     */
    @Override
    public void dispose() {
        stage1.dispose();
        stage2.dispose();
    }
}
