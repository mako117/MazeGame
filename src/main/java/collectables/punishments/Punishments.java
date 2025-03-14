package collectables.punishments;

import collectables.Collectable;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 * A punishment is a Collectable which decreases the score of any Character that enters its space.
 */
public class Punishments extends Collectable {

    /**
     * Initializes position to an invalid value, and the punishment score to 0.
     */
    public Punishments() {
        super();
    }
    /**
     * Initializes the punishment's position, the amount it decreases the character's score by, and the texture that represents it on the board.
     * @param inputX    Input for the punishment's <code>x</code>-coordinate.
     * @param inputY    Input for the punishment's <code>y</code>-coordinate.
     * @param inputScore  Input for the punishment's <code>score</code>.  
     * @param textureRegion Input for the punishment's <code>texture</code>.
     */
    public Punishments(int inputX, int inputY, int inputScore, TextureRegion textureRegion) {
        super(inputX,inputY,inputScore,textureRegion);
    }
}
