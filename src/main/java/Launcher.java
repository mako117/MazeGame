import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import screens.*;

public class Launcher {
    // initial class (may remove/change)


    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle("JURASSIC METEOR");
        config.setWindowedMode(1280,720);

        new Lwjgl3Application(new MazeGame(GameState.MainMenu), config);
    }


}