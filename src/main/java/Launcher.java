import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Launcher {
    // initial class (may remove/change)
    public static void main(String args[]) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setIdleFPS(60);
        config.useVsync(true);
        config.setTitle("PLACEHOLDER");

        config.setWindowedMode(600,400);

        new Lwjgl3Application(new MazeGame(), config);
    }
}