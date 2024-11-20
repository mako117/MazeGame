package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HelpScreen extends ScreenAdapter {

    final screens.MazeGame game;
    private ScreenAdapter prevScreen;

    private TextureRegion RrewardTex;
    private TextureRegion RpunishmentTex;
    private TextureRegion BrewardTex;
    private TextureRegion BpunishmentTex;
    private TextureRegion movingEnemyTex;
    private TextureRegion patrollingEnemeyTex;
    private TextureRegion endblockTex;

    private Button helppage1button;
    private Button helppage2button;
    private Button helppage3button;
    private Button helpbackbutton;
    private Stage stage1;
    private Stage stage2;
    private Stage stage3;

    private int change_x = 0;
    private int change_y = 0;
    private int middle_x = Gdx.graphics.getWidth() / 2;
    private int middle_y = Gdx.graphics.getHeight() / 2;

    private boolean helppage1 = true;
    private boolean helppage2 = false;
    private boolean helppage3 = false;



    public HelpScreen(final MazeGame game, ScreenAdapter prev) {
        this.game = game;
        this.prevScreen = prev;

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
        createButtons();
    }


    private void createButtons(){
        helppage1button = new TextButton("Page 1", game.skin);
        helppage1button.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);

        helppage2button = new TextButton("Page 2", game.skin);
        helppage2button.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);

        helppage3button = new TextButton("Page 3", game.skin);
        helppage3button.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);

        change_x = -140;
        change_y = -300;

        helpbackbutton = new TextButton("Back", game.skin);
        helpbackbutton.setSize(Gdx.graphics.getWidth() / 10 * 2, Gdx.graphics.getHeight() / 10);
        helpbackbutton.setPosition(middle_x + change_x, middle_y + change_y);

        // listener for touch button
        helppage1button.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                helppage1 = true;
                helppage2 = false;
            }

        });

        // listener for touch button
        helppage2button.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                helppage1 = false;
                helppage2 = true;
                helppage3 = false;
            }

        });

        // listener for touch button
        helppage3button.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                helppage2 = false;
                helppage3 = true;
            }

        });

        // listener for touch button
        helpbackbutton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                helppage1 = false;
                helppage2 = false;
                helppage3 = false;
                game.setScreen(prevScreen);
                dispose();
            }

        });
    }

    public void setPrevScreen(ScreenAdapter prev){
        if(prev != null){
            prevScreen = prev;
        } else {
            throw new RuntimeException("Prev screen is null");
        }
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);
        game.batch.begin();

        change_x = -180;
        change_y = 300;

        game.batch.draw(game.backgroundTexture, 0, 0);
        game.font.getData().setScale(2, 2);
        game.font.draw(game.batch, "How to play", middle_x + change_x, middle_y + change_y);

        game.batch.end();

        if (helppage1){
            showPage1();
        }
        else if (helppage2){
            showPage2();
        } else if (helppage3){
            showPage1();
        }

    }


    private void showPage1(){
        Gdx.input.setInputProcessor(stage1);
        change_y = -300;

        stage1.addActor(helpbackbutton);
        change_x = 300;
        helppage2button.setPosition(middle_x + change_x, middle_y + change_y);
        stage1.addActor(helppage2button);

        game.batch.begin();

        change_x = -(middle_x) + 30;
        change_y = 200;
        game.font.getData().setScale(1,1);;
        game.font.draw(game.batch, "Movement" + ": Use 'W', 'A', 'S', 'D' to move in the game", middle_x + change_x, middle_y + change_y);

        change_y = 100;
        game.font.draw(game.batch, "Stop" + ": Use 'Ese' can stop in the game", middle_x + change_x, middle_y + change_y);

        change_y = 0;
        game.font.draw(game.batch, "Win the Game" + ": Collect all the rewards show on ready board \nand go to the end block win the game", middle_x + change_x, middle_y + change_y);

        change_y = -150;
        game.font.draw(game.batch, "Loss the Game" + ": Monster catch you or point lower than 0", middle_x + change_x, middle_y + change_y);

        game.batch.end();

        stage1.act();
        stage1.draw();
    }

    private void showPage2(){
        Gdx.input.setInputProcessor(stage2);
        change_y = -300;

        stage2.addActor(helpbackbutton);
        change_x = -560;
        helppage1button.setPosition(middle_x + change_x, middle_y + change_y);
        stage2.addActor(helppage1button);
        change_x = 300;
        helppage3button.setPosition(middle_x + change_x, middle_y + change_y);
        stage2.addActor(helppage3button);

        game.batch.begin();

        change_x = -(middle_x) + 30;
        change_y = 120;

        game.batch.draw(RrewardTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

        change_y = 20;

        game.batch.draw(BrewardTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

        change_y = -80;

        game.batch.draw(RpunishmentTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

        change_y = -180;

        game.batch.draw(BpunishmentTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

        change_x = -(middle_x) + 210;
        change_y = 190;

        game.font.getData().setScale(1, 1);
        game.font.draw(game.batch, ": Reward \n Collect can get 10 point", middle_x + change_x, middle_y + change_y);

        change_y = 90;

        game.font.draw(game.batch, ": Bonus reward \n Collect can get 10 point, appear on mape in fix time", middle_x + change_x, middle_y + change_y);

        change_y = -10;

        game.font.draw(game.batch, ": Punishment \n Collect will loss 10 point", middle_x + change_x, middle_y + change_y);

        change_y = -110;

        game.font.draw(game.batch, ": Bonus Punishment \n Collect will loss 10 point, appear on map in fix time.", middle_x + change_x, middle_y + change_y);

        game.batch.end();

        stage2.act();
        stage2.draw();
    }

    private void showPage3(){
        Gdx.input.setInputProcessor(stage3);
        change_y = -300;

        stage3.addActor(helpbackbutton);
        change_x = -560;
        helppage2button.setPosition(middle_x + change_x, middle_y + change_y);
        stage3.addActor(helppage2button);

        game.batch.begin();

        change_x = -(middle_x) + 30;
        change_y = 150;

        game.batch.draw(movingEnemyTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

        change_y = 0;

        game.batch.draw(patrollingEnemeyTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

        change_y = -150;

        game.batch.draw(endblockTex, middle_x + change_x, middle_y + change_y, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);

        change_x = -(middle_x) + 210;
        change_y = 200;

        game.font.getData().setScale(1,1);
        game.font.draw(game.batch, ": This is the mighty T.rex.\n  He will hunt you.", middle_x + change_x, middle_y + change_y);

        change_y = 50;

        game.font.draw(game.batch, ": The Pterodactyl is mighty territorial\n  Avoid his set path.", middle_x + change_x, middle_y + change_y);

        change_y = -100;

        game.font.draw(game.batch, ": Come to this block to win when collect all the rewards.", middle_x + change_x, middle_y + change_y);

        game.batch.end();

        stage3.act();
        stage3.draw();
    }


    @Override
    public void dispose() {
        stage1.dispose();
        stage2.dispose();
        stage3.dispose();
    }
}
