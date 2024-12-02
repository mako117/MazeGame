import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import screens.*;

/**
 * The Main Class Launcher
 */
public class Launcher {
    // initial class (may remove/change)

    /**
     * The launcher of the game
     * @param args Command line arguments not required
     */
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle("JURASSIC METEOR");
        config.setWindowedMode(1280,720);
        config.setResizable(false);

        new Lwjgl3Application(new MazeGame(GameState.MainMenu), config);
    }


}