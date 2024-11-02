import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Launcher {
    // initial class (may remove/change)
    public static void main(String args[]) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
//        config.setIdleFPS(60);
//        config.useVsync(true);
        config.setTitle("JURASSIC METEOR");

        config.setWindowedMode(1280,720);
        config.setResizable(false);
        new Lwjgl3Application(new MazeGame(), config);
    }
}