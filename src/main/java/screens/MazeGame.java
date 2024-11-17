package screens;

import com.badlogic.gdx.*;

public class MazeGame extends Game {
    //GameScreen gameScreen;

    public void create(){
        //gameScreen = new GameScreen(this);
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        //gameScreen.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        //gameScreen.dispose();
    }
}