import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import screens.*;

/**
 * Launches MazeGame.
 */
public class Launcher {

    /**
     * The Main Class that runs the game
     * @param args No string arguments from the command line needed
     */
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle("JURASSIC METEOR");
        config.setWindowedMode(1280,720);
        config.setResizable(false);

        new Lwjgl3Application(new MazeGame(GameState.MainMenu), config);
    }
}