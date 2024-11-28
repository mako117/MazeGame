package screens;

import board.Board;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;


/**
     * This class has the ready screen information.
     */
    public class ReadyScreen extends ScreenAdapter {
        private Stage missionStage;
        private Button continueButton;
        protected boolean missionStatement = true;

        //public OrthographicCamera fullscreenCamera;
        protected boolean fullScreenMode = true;
        protected float fullscreenDuration = 5f;
        protected float fullscreenTimer = 0f;

        private Viewport viewport;
        private MazeGame game;

        ReadyScreen(final MazeGame game){
            this.game = game;
//            fullscreenCamera = new OrthographicCamera();
            game.resetCamera();
            viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), game.camera);

            missionStage = new Stage(viewport);
            continueButton = new TextButton("Continue", game.skin);
            continueButton.setSize(Gdx.graphics.getWidth()/10 * 2, Gdx.graphics.getHeight()/10);
            continueButton.addListener(new ChangeListener() {
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    missionStatement = false;
                }
            });
            missionStage.addActor(continueButton);
            viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        /**
         * This function displays the mission statements before the start of the game.
         */
        public void missionMenu(){
            continueButton.setPosition(game.camera.position.x - 128, game.camera.position.y - Gdx.graphics.getHeight()/2);
            Gdx.input.setInputProcessor(missionStage);
            game.batch.begin();
            game.batch.draw(game.backgroundTexture, game.camera.position.x - (Gdx.graphics.getWidth())/2, game.camera.position.y - (Gdx.graphics.getHeight())/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.font.getData().setScale(2, 2);
            game.font.draw(game.batch, "The Mission", game.camera.position.x - 150, game.camera.position.y + 220);
            game.font.getData().setScale(1, 1);
            game.font.draw(game.batch, "The Dinosaurs are trying to stop their demise.\n" +
                    "Collect All the bombs to ensure their extinction.\n" +
                    "The fate of Humanity Rests in your hands.", game.camera.position.x - (Gdx.graphics.getWidth())/2 + 300, game.camera.position.y + 90);
            game.batch.end();
            missionStage.act();
            missionStage.draw();
        }

        /**
         * This functions displays the entire map before commencing the game.
         *
         * @param delta
         */
        public void fullScreen(float delta, float time, int TILE_SIZE, Board gameboard, GameScreen nextScreen){
            game.camera.position.x = Gdx.graphics.getWidth()/2;
            game.camera.position.y = Gdx.graphics.getHeight()/2;
            game.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.camera.zoom = 1.8f;
            game.camera.update();
            game.batch.setProjectionMatrix(game.camera.combined);

            game.batch.begin();
            game.batch.draw(game.backgroundTexture, 0, 0, (Gdx.graphics.getWidth() * 1.8f), (Gdx.graphics.getHeight() * 1.8f));
            gameboard.draw(game.batch, time, TILE_SIZE);
            nextScreen.renderPlayer(delta);
            readyText();
            nextScreen.renderEnemies(delta);
            game.batch.end();
        }

        /**
         *Renders The Text for the Initial Screen
         */
        private void readyText(){
            game.font.getData().setScale(1.5f,1.5f);
            game.font.draw(game.batch,String.format("READY!"), game.camera.position.x + viewport.getScreenWidth()/10 ,game.camera.position.y + viewport.getScreenHeight()/2 );
            game.font.draw(game.batch,String.format("Disarm all the bombs.\n\n" +
                    "Avoiding all the dinosaurs.\n\n" +
                    "Smash the dinosaur eggs for bonus points.\n\n" +
                    "Avoid falling into a dinosaur nest.\n\n" +
                    "Reach the end."), game.camera.position.x + viewport.getScreenWidth()/10, game.camera.position.y + viewport.getScreenHeight()/2 - 120);
            game.font.getData().setScale(1,1);
        }

        /**
         * Dispose of gdx dependent objects.
         */
        public void dispose() {
            missionStage.dispose();
        }

        // public Button getContinueButton() {
        //     return this.continueButton;
        // }
    }
