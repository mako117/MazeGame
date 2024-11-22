package screens;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
    /**
 * Create context for libgdx components in board. T-T <br>
 * By extending this in our test class, we will override the create method, allowing Gdx textures to be initialized. <br>
 * Solution from: <a href="https://www.reddit.com/r/libgdx/comments/1by4jgn/loading_assets_for_tests_in_libgdx/">Loading assets for tests in libgdx?</a>
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractTestWithHeadlessGdxContext extends ApplicationAdapter {
    Lwjgl3ApplicationConfiguration config;
    Lwjgl3Application application;

    AbstractTestWithHeadlessGdxContext() {
        config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("JURASSIC METEOR");
        config.disableAudio(true);
        config.setWindowedMode(1280,720);
     }

}
